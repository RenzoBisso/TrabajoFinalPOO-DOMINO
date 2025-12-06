package Modelo;

import Modelo.Observer.Observable;
import Modelo.Observer.Observador;

import java.util.ArrayList;

public class Partida implements Observable {
    private Integer idPartida;
    private Tablero tablero;
    private Estado estado;
    private Integer tiempoPorTurno;
    private Jugador ganadorPartida;
    private Integer ptosPartida;
    private ArrayList<Observador> observadores = new ArrayList<>();

    public Partida(Integer idPartida,Tablero tablero, Integer tiempoPorTurno, Estado estado,Integer ptosPartida) {
        this.idPartida=idPartida;
        this.tablero = tablero;
        this.tiempoPorTurno = tiempoPorTurno;
        this.estado = estado;
        this.ptosPartida=ptosPartida;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
        notificar();
    }

    public Integer getTiempoPorTurno() {
        return tiempoPorTurno;
    }

    public void setTiempoPorTurno(Integer tiempoPorTurno) {
        this.tiempoPorTurno = tiempoPorTurno;
        notificar();
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
        notificar();
    }

    public Jugador getGanadorPartida() {
        return ganadorPartida;
    }

    public void setGanadorPartida(Jugador ganadorPartida) {
        this.ganadorPartida = ganadorPartida;
        notificar();
    }

    public Integer getPtosPartida() {
        return ptosPartida;
    }

    public void setPtosPartida(Integer ptosPartida) {
        this.ptosPartida = ptosPartida;
    }

    public ArrayList<Observador> getObservadores() {
        return observadores;
    }

    public void setObservadores(ArrayList<Observador> observadores) {
        this.observadores = observadores;
    }

    public void sumarPuntos(Jugador jugador, int ptos){
        jugador.setPtos(jugador.getPtos()+ptos);
        notificar();
    }
    public void finalizarPartida(Jugador jugadorActual){
        int indice=jugadorActual.getIndice();
        jugadorActual.setXp(jugadorActual.getXp()+getTablero().getJugadores().size()*10);
        for(Jugador j:this.getTablero().getJugadores()){
            if(j.getIndice()!=indice){
                j.setXp(j.getXp()+5);
            }
        }
        setGanadorPartida(jugadorActual);
        setEstado(Estado.FINALIZADO);
    }


    @Override
    public void agregarObservador(Observador observador) {
        this.getObservadores().add(observador);
    }

    @Override
    public void quitarObservador(Observador observador) {
        this.getObservadores().remove(observador);
    }

    @Override
    public void notificar() {
        for (Observador o:this.getObservadores()){
            o.actualizar();
        }
    }
}

