package Modelo;
import Modelo.Observer.Observable;
import Modelo.Observer.Observador;

import java.util.ArrayList;

public class Ronda implements Observable {
    private Jugador jugadorActual;
    private Tablero tablero;
    private ArrayList<Observador> observers = new ArrayList<>();
    private Partida partida;
    private int turno = 0;

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

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
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

    public void pasar() {
        this.setTurno(this.getTurno()+1);
        if (this.getTurno() >= this.getTablero().getJugadores().size()) {
            this.setTurno(0);
        }

        this.setJugadorActual(
                this.getTablero().getJugadores().get(this.getTurno())
        );
    }

    public Ficha seleccionarFichaMano(int pos) {
        if (pos < 0 || pos >= this.getJugadorActual().getMano().size()) {
            return null;
        }

        Ficha f = this.getJugadorActual().getMano().get(pos);

        if (this.verificarFichaValida(f)) {
            return this.getJugadorActual().getMano().remove(pos);
        } else {
            return null;
        }
    }

    public void colocarFicha(Ficha ficha, boolean lado) {
        if (ficha == null) return;

        boolean colocada = false;

        if (this.getTablero().getFichasJugadas().isEmpty()) {
            this.getTablero().getFichasJugadas().add(ficha);
            this.getTablero().setFichaDer(ficha);
            this.getTablero().setFichaIzq(ficha);
            colocada = true;
        } else {
            if (lado) {
                Ficha fd = this.getTablero().getFichaDer();
                if (fd.getLadoB().equals(ficha.getLadoA())) {
                    this.getTablero().getFichasJugadas().addLast(ficha);
                    colocada = true;
                } else if (fd.getLadoB().equals(ficha.getLadoB())) {
                    ficha.rotar();
                    this.getTablero().getFichasJugadas().addLast(ficha);
                    colocada = true;
                }
            } else {
                Ficha fi = this.getTablero().getFichaIzq();
                if (fi.getLadoA().equals(ficha.getLadoB())) {
                    this.getTablero().getFichasJugadas().addFirst(ficha);
                    colocada = true;
                } else if (fi.getLadoA().equals(ficha.getLadoA())) {
                    ficha.rotar();
                    this.getTablero().getFichasJugadas().addFirst(ficha);
                    colocada = true;
                }
            }
        }

        if (colocada) {
            this.getTablero().actualizarLados();

            if (this.getJugadorActual().getMano().isEmpty()) {
                this.finalizarRonda();
            } else {
                this.pasar();
                notificar();
            }
        }
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
        this.getPartida().iniciarNuevaRonda();
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

    public boolean pedirFicha(int pos) {
        if (this.getTablero().getPozo().getFichas().isEmpty()) {
            this.pasar();
            return false;
        }

        if (pos < 0 || pos > this.getTablero().getPozo().getFichas().size()) {
            return false;
        }

        Ficha f = this.getTablero().getPozo().tomarFicha(pos);

        boolean valida = this.verificarFichaValida(f);
        this.getJugadorActual().getMano().add(f);
        return valida;
    }


    public boolean pedirFicha() {

        if (this.getTablero().getPozo().getFichas().isEmpty()) {
            this.pasar();
            return false;
        }

        Ficha f = this.getTablero().getPozo().tomarFicha();

        if (this.verificarFichaValida(f)) {
            this.getJugadorActual().getMano().add(f);
            return true;
        }

        this.getTablero().getPozo().getFichas().add(f);

        return false;
    }
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


