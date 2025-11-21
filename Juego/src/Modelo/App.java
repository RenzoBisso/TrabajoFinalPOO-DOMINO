package Modelo;

import Modelo.Observer.Observable;
import Modelo.Observer.Observador;

public class App implements Observable {
    private Ronda ronda;
    private Partida partida;

    public App(Ronda ronda, Partida partida) {
        this.ronda = ronda;
        this.partida = partida;
    }

    public Ronda getRonda() {
        return ronda;
    }

    public void setRonda(Ronda ronda) {
        this.ronda = ronda;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    @Override
    public void agregarObservador(Observador o) {

    }

    @Override
    public void sacarObservador(Observador o) {

    }

    @Override
    public void notificar() {

    }
}
