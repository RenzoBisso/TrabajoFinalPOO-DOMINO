package Modelo.Observer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Ronda {
    private Jugador jugadorActual;
    private Tablero tablero;
    private ArrayList<Jugador> jugadores;
    private Partida partida;

    public Ronda(Partida partida, ArrayList<Jugador> jugadores, Tablero tablero) {
        this.partida = partida;
        this.jugadores = jugadores;
        this.tablero = tablero;
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

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public void cargarIndices(){
        int count=0;
        for(Jugador j:this.getJugadores()){
            j.setIndice(count);
            count++;
        }
    }


    public void jugadorInicial(){
        int max=-1;
        Jugador jAux=null;
        for (Jugador j:this.getJugadores()){
            for (Ficha f:j.getMano()){
                if(f.getValor()>max){
                    max=f.getLadoA();
                    jAux=j;
                }
            }
        }
        this.setJugadorActual(jAux);
    }

    public void jugadorSiguiente(){
        int indiceActual=this.getJugadorActual().getIndice();
        if(indiceActual==this.getJugadores().size()){
            this.setJugadorActual(this.getJugadores().getFirst());
        }else{
            this.setJugadorActual(this.getJugadores().get(indiceActual+1));
        }
    }

    public void colocarFicha(Ficha ficha,boolean lado){
        if(this.getTablero().getFichasJugadas().isEmpty()){
            this.getTablero().getFichasJugadas().add(ficha);
            this.getJugadorActual().getMano().remove(ficha);
        }else{
            //lado == true Derecho || lado==false Izquierda
            if(lado){
                if(this.getTablero().getFichaDer().getLadoB().equals(ficha.getLadoA())){
                    this.getTablero().getFichasJugadas().addLast(ficha);
                    this.getTablero().actualizarLados();
                } else if (this.getTablero().getFichaDer().equals(ficha.getLadoB())) {
                    ficha.rotar();
                    this.getTablero().getFichasJugadas().addLast(ficha);
                    this.getTablero().actualizarLados();
                }
            }else{
                if(this.getTablero().getFichaIzq().getLadoA().equals(ficha.getLadoB())){
                    this.getTablero().getFichasJugadas().addFirst(ficha);
                    this.getTablero().actualizarLados();
                } else if (this.getTablero().getFichaIzq().equals(ficha.getLadoA())) {
                    ficha.rotar();
                    this.getTablero().getFichasJugadas().addFirst(ficha);
                    this.getTablero().actualizarLados();
                }
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
        }else{
                if(this.getTablero().getFichaDer().getLadoB().equals(ficha.getLadoA())){
                    this.getTablero().getFichasJugadas().addLast(ficha);
                    this.getTablero().actualizarLados();
                } else if (this.getTablero().getFichaDer().equals(ficha.getLadoB())) {
                    ficha.rotar();
                    this.getTablero().getFichasJugadas().addLast(ficha);
                    this.getTablero().actualizarLados();
                }
                if(this.getTablero().getFichaIzq().getLadoA().equals(ficha.getLadoB())){
                    this.getTablero().getFichasJugadas().addFirst(ficha);
                    this.getTablero().actualizarLados();
                } else if (this.getTablero().getFichaIzq().equals(ficha.getLadoA())) {
                    ficha.rotar();
                    this.getTablero().getFichasJugadas().addFirst(ficha);
                    this.getTablero().actualizarLados();
                }
            }
        if(this.getJugadorActual().getMano().isEmpty()){
            this.finalizarRonda();
        }
        this.jugadorSiguiente();

    }

    public void tomarFicha(int pos){
        boolean flag=false;
        while(!flag){
            Ficha f=this.getTablero().getPozo().sacarFicha(pos);
            this.getJugadorActual().getMano().add(f);
            flag=this.verificarFichaValida(f);
        }

    }
    public void tomarFicha(){
        boolean flag=false;
        while(!flag){
            Ficha f=this.getTablero().getPozo().sacarFicha();
            this.getJugadorActual().getMano().add(f);
            flag=this.verificarFichaValida(f);
        }

    }


    public void finalizarRonda(){
        int indiceActual=this.getJugadorActual().getIndice();
        int count=0;
        for (Jugador j:this.getJugadores()){
            for(Ficha f:j.getMano()){
                count+=f.getValor();
            }
        }
        this.getJugadorActual().setPtos(this.getJugadorActual().getPtos()+count);
        if(this.getJugadorActual().getPtos()>=this.getPartida().getPtosPartida()){
            this.finalizarPartida();
        }
        this.jugadorSiguiente();
    }


    public boolean verificarFichaValida(Ficha ficha){
        if(this.getTablero().getFichaDer().getLadoB().equals(ficha.getLadoA())||this.getTablero().getFichaDer().equals(ficha.getLadoB())){
            return true;
        } else if(this.getTablero().getFichaIzq().getLadoA().equals(ficha.getLadoB())||this.getTablero().getFichaIzq().equals(ficha.getLadoA())){
            return true;
        }
        return false;
    }


    public void contador(){
        AtomicInteger tiempo= new AtomicInteger(this.getPartida().getTiempoPorTurno());
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {

            tiempo.getAndDecrement();

            if (tiempo.get() < 0) {
                boolean flag=false;
                for (Ficha f:this.getJugadorActual().getMano()){
                    if(this.verificarFichaValida(f)){
                        this.colocarFicha(f);
                    }
                }
                this.tomarFicha();
                for (Ficha f:this.getJugadorActual().getMano()){
                    if(this.verificarFichaValida(f)){
                        this.colocarFicha(f);
                    }
                }
                scheduler.shutdown(); // detener
            }

        }, 0, 1, TimeUnit.SECONDS);
        this.jugadorSiguiente();
    }



    public void finalizarPartida(){
            int indice=this.getJugadorActual().getIndice();
            this.getJugadorActual().setXp(this.getJugadorActual().getXp()+this.getJugadores().size()*10);
            for(Jugador j:this.getJugadores()){
                if(j.getIndice()!=indice){
                    j.setXp(j.getXp()+5);
                }
            }
    }
}


