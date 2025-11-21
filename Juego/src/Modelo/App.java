package Modelo;

import Modelo.Observer.Observable;
import Modelo.Observer.Observador;

public class App implements Observable {
    private Partida partida;
    private Ronda ronda;

    public App(Partida partida, Ronda ronda) {
        this.partida = partida;
        this.ronda = ronda;
    }

    public Partida getPartida() {
        return partida;
    }

    public Ronda getRonda() {
        return ronda;
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
