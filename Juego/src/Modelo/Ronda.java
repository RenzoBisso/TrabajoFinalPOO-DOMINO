package Modelo;

import Modelo.Observer.Observable;
import Modelo.Observer.Observador;

import java.util.ArrayList;

public class Ronda implements Observable{
    private static Integer nroRonda=0;
    private ArrayList<Turno> turnosJugados;
    private ArrayList<Jugador> jugadoresEnOrden=new ArrayList<>();
    private ArrayList<Observador> observadores = new ArrayList<>();

    public Ronda(ArrayList<Jugador> jugadoresEnOrden) {
        this.jugadoresEnOrden=jugadoresEnOrden;
    }

    public ArrayList<Observador> getObservadores() {
        return observadores;
    }


    public void generarTurnos(){
        for (int j=0;j<this.getJugadoresEnOrden().size();j++){

        }
    }

    public static Integer getNroRonda() {
        return nroRonda;
    }

    public static void setNroRonda(Integer nroRonda) {
        Ronda.nroRonda = nroRonda;
    }

    public ArrayList<Turno> getTurnosJugados() {
        return turnosJugados;
    }

    public void setTurnosJugados(ArrayList<Turno> turnosJugados) {
        this.turnosJugados = turnosJugados;
    }

    public ArrayList<Jugador> getJugadoresEnOrden() {
        return jugadoresEnOrden;
    }

    public void setJugadoresEnOrden(ArrayList<Jugador> jugadoresEnOrden) {
        this.jugadoresEnOrden = jugadoresEnOrden;
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
