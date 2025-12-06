package Modelo;
import Modelo.Observer.Observable;
import Modelo.Observer.Observador;

import java.util.ArrayList;

public class Ronda implements Observable {
    private Jugador jugadorActual;
    private Tablero tablero;
    private ArrayList<Observador> observers = new ArrayList<>();
    private Partida partida;

    public Ronda(Partida partida,Tablero tablero) {
        this.partida = partida;
        this.tablero = tablero;
        this.cargarIndices();
        this.jugadorInicial();
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }


    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public ArrayList<Observador> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<Observador> observers) {
        this.observers = observers;
    }

    public void cargarIndices(){
        int count=0;
        for(Jugador j:this.getTablero().getJugadores()){
            j.setIndice(count);
            count++;
        }
    }


    public void jugadorInicial(){
        int max=-1;
        Jugador jAux=null;
        for (Jugador j:this.getTablero().getJugadores()){
            for (Ficha f:j.getMano()){
                if(f.getValor()>max){
                    max=f.getValor();
                    jAux=j;
                }
            }
        }
        this.setJugadorActual(jAux);
        notificar();
    }

    public void pasar(){
        int indiceActual=this.getJugadorActual().getIndice();
        if(indiceActual == this.getTablero().getJugadores().size() - 1){
            this.setJugadorActual(this.getTablero().getJugadores().getFirst());
        }else{
            this.setJugadorActual(this.getTablero().getJugadores().get(indiceActual+1));
        }
        notificar();
    }
    public Ficha seleccionarFichaMano(int pos){
        if(pos<0||pos>this.getJugadorActual().getMano().size()){
            return null;
        }
        Ficha f=this.getJugadorActual().getMano().get(pos);
        this.getJugadorActual().getMano().remove(pos);
        return f;
    }

    public void colocarFicha(Ficha ficha,boolean lado){
        if(this.getTablero().getFichasJugadas().isEmpty()){
            this.getTablero().getFichasJugadas().add(ficha);
            this.getJugadorActual().getMano().remove(ficha);
            this.getTablero().setFichaDer(ficha);
            this.getTablero().setFichaIzq(ficha);
            notificar();
        }else{
            //lado == true Derecho || lado==false Izquierda
            if(lado){
                if(this.getTablero().getFichaDer().getLadoB().equals(ficha.getLadoA())){
                    this.getTablero().getFichasJugadas().addLast(ficha);
                    this.getTablero().actualizarLados();
                } else if (this.getTablero().getFichaDer().getLadoB().equals(ficha.getLadoB())) {
                    ficha.rotar();
                    this.getTablero().getFichasJugadas().addLast(ficha);
                    this.getTablero().actualizarLados();
                }
                notificar();
            }else{
                if(this.getTablero().getFichaIzq().getLadoA().equals(ficha.getLadoB())){
                    this.getTablero().getFichasJugadas().addFirst(ficha);
                    this.getTablero().actualizarLados();
                } else if (this.getTablero().getFichaIzq().getLadoA().equals(ficha.getLadoA())) {
                    ficha.rotar();
                    this.getTablero().getFichasJugadas().addFirst(ficha);
                    this.getTablero().actualizarLados();
                }
                notificar();
            }
        }
        if(this.getJugadorActual().getMano().isEmpty()){
            this.finalizarRonda();
        }
    }
    public void colocarFicha(Ficha ficha){
        if(this.getTablero().getFichasJugadas().isEmpty()){
            this.getTablero().getFichasJugadas().add(ficha);
            this.getJugadorActual().getMano().remove(ficha);
            this.getTablero().setFichaDer(ficha);
            this.getTablero().setFichaIzq(ficha);
        }else{
                if(this.getTablero().getFichaDer().getLadoB().equals(ficha.getLadoA())){
                    this.getTablero().getFichasJugadas().addLast(ficha);
                    this.getTablero().actualizarLados();
                } else if (this.getTablero().getFichaDer().getLadoB().equals(ficha.getLadoB())) {
                    ficha.rotar();
                    this.getTablero().getFichasJugadas().addLast(ficha);
                    this.getTablero().actualizarLados();
                }
                if(this.getTablero().getFichaIzq().getLadoA().equals(ficha.getLadoB())){
                    this.getTablero().getFichasJugadas().addFirst(ficha);
                    this.getTablero().actualizarLados();
                } else if (this.getTablero().getFichaIzq().getLadoA().equals(ficha.getLadoA())) {
                    ficha.rotar();
                    this.getTablero().getFichasJugadas().addFirst(ficha);
                    this.getTablero().actualizarLados();
                }
            }
        if(this.getJugadorActual().getMano().isEmpty()){
            this.finalizarRonda();
        }
        notificar();
        this.pasar();

    }

    public void finalizarRonda(){
        int indiceActual=this.getJugadorActual().getIndice();
        int count=0;
        for (Jugador j:this.getTablero().getJugadores()){
            for(Ficha f:j.getMano()){
                count+=f.getValor();
            }
        }

        this.getPartida().sumarPuntos(this.getJugadorActual(),count);
        if(this.getJugadorActual().getPtos()>=this.getPartida().getPtosPartida()){
            this.getPartida().finalizarPartida(this.getJugadorActual());
        }
        notificar();
        this.pasar();
    }


    public boolean  verificarFichaValida(Ficha ficha){
        if(this.getTablero().getFichaDer()==null && this.getTablero().getFichaIzq()==null ){
            return true;
        }
        if(this.getTablero().getFichaDer().getLadoB().equals(ficha.getLadoA())||this.getTablero().getFichaDer().getLadoB().equals(ficha.getLadoB())){
            return true;
        } else if(this.getTablero().getFichaIzq().getLadoA().equals(ficha.getLadoB())||this.getTablero().getFichaIzq().getLadoA().equals(ficha.getLadoA())){
            return true;
        }
        return false;
    }

    public void pedirFicha(int pos){

        boolean flag=false;
        while(!flag){
            if (this.getTablero().getPozo().getFichas().isEmpty()) {
                this.pasar();
            }
            Ficha f=this.getTablero().getPozo().tomarFicha(pos);
            flag=this.verificarFichaValida(f);
            if(flag){
                this.getJugadorActual().getMano().add(f);
            }
        }
    }

    public void pedirFicha(){
        boolean flag=false;
        Ficha fAux;
        while(!flag){
            if (this.getTablero().getPozo().getFichas().isEmpty()) {
                this.pasar();
            }
            Ficha f=this.getTablero().getPozo().tomarFicha();
            flag=this.verificarFichaValida(f);
            if(flag){
                this.getJugadorActual().getMano().add(f);
            }
        }
    }

//    public void contador(){
//        AtomicInteger tiempo= new AtomicInteger(this.getPartida().getTiempoPorTurno());
//        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//
//        scheduler.scheduleAtFixedRate(() -> {
//
//            tiempo.getAndDecrement();
//
//            if (tiempo.get() < 0) {
//                boolean flag=false;
//                for (Ficha f:this.getJugadorActual().getMano()){
//                    if(this.verificarFichaValida(f)){
//                        this.colocarFicha(f);
//                    }
//                }
//                this.pedirFicha();
//                for (Ficha f:this.getJugadorActual().getMano()){
//                    if(this.verificarFichaValida(f)){
//                        this.colocarFicha(f);
//                    }
//                }
//                scheduler.shutdown(); // detener
//            }
//
//        }, 0, 1, TimeUnit.SECONDS);
//        this.pasar();
//    }

    @Override
    public void agregarObservador(Observador observador) {
        this.getObservers().add(observador);
    }

    @Override
    public void quitarObservador(Observador observador) {
        this.getObservers().remove(observador);
    }

    @Override
    public void notificar() {
        for (Observador o:this.getObservers()){
            o.actualizar();
        }
    }
}


