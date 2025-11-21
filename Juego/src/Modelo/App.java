package Modelo;

import Modelo.Observer.Observable;
import Modelo.Observer.Observador;

import java.util.ArrayList;

public class App implements Observable {
    private Partida partida;
    private Ronda ronda;
    private ArrayList<Observador> observadores = new ArrayList<>();

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
        observadores.add(o);
    }

    @Override
    public void sacarObservador(Observador o) {

    }

    @Override
    public void notificar() {
        for (Observador o : observadores) {
            o.actualizar();
        }
    }
}
