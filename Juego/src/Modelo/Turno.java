package Modelo;

import Modelo.Observer.Observable;
import Modelo.Observer.Observador;

import java.util.ArrayList;

public class Turno implements Observable{

    private Jugador jugadorActual;
    private Ficha fichaJugada;
    private ArrayList<Observador> observadores = new ArrayList<>();

    public ArrayList<Observador> getObservadores() {
        return observadores;
    }

    @Override
    public void agregarObservador(Observador o) {
        this.getObservadores().add(o);
    }

    @Override
    public void sacarObservador(Observador o) {
        this.getObservadores().remove(o);
    }

    @Override
    public void notificar() {
        for (Observador o:this.getObservadores()){
            o.actualizar();
        }
    }
}
