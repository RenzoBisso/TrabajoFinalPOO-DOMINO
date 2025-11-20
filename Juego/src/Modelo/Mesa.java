package Modelo;

import Modelo.Observer.Observable;
import Modelo.Observer.Observador;

import java.util.ArrayList;

public class Mesa implements Observable {

    private ArrayList<Ficha> pozo;
    private ArrayList<Ficha> fichasJugadas;
    private ArrayList<Observador> observadores = new ArrayList<>();
    public ArrayList<Observador> getObservadores() {
        return observadores;
    }


    public ArrayList<Ficha> getPozo() {
        return pozo;
    }

    public void setPozo(ArrayList<Ficha> pozo) {
        this.pozo = pozo;
    }

    public ArrayList<Ficha> getFichasJugadas() {
        return fichasJugadas;
    }

    public void setFichasJugadas(ArrayList<Ficha> fichasJugadas) {
        this.fichasJugadas = fichasJugadas;
    }

    public void setObservadores(ArrayList<Observador> observadores) {
        this.observadores = observadores;
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
