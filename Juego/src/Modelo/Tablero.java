package Modelo;

import Modelo.Observer.Observable;
import Modelo.Observer.Observador;

import java.util.ArrayList;

public class Tablero implements Observable {
    private ArrayList<Ficha> fichasJugadas=new ArrayList<>();
    private Ficha fichaIzq;
    private Ficha fichaDer;
    private ArrayList<Observador> observers = new ArrayList<>();
    private Pozo pozo;
    private ArrayList<Jugador> jugadores;

    public Tablero(ArrayList<Jugador>jugadores) {
        this.pozo=new Pozo();
        this.jugadores=jugadores;
        this.getPozo().cargarFichas();
        this.getPozo().repartirFichas(this.getJugadores());
    }

    public ArrayList<Observador> getObservers() {
        return observers;
    }

    public void setObservers(ArrayList<Observador> observers) {
        this.observers = observers;
    }

    public Pozo getPozo() {
        return pozo;
    }

    public void setPozo(Pozo pozo) {
        this.pozo = pozo;
    }

    public Tablero(ArrayList<Ficha> fichasJugadas, Ficha fichaIzq, Ficha fichaDer) {
        this.fichasJugadas = fichasJugadas;
        this.fichaIzq = fichaIzq;
        this.fichaDer = fichaDer;
    }

    public ArrayList<Ficha> getFichasJugadas() {
        return fichasJugadas;
    }

    public void setFichasJugadas(ArrayList<Ficha> fichasJugadas) {
        this.fichasJugadas = fichasJugadas;
    }

    public Ficha getFichaIzq() {
        return fichaIzq;
    }

    public void setFichaIzq(Ficha fichaIzq) {
        this.fichaIzq = fichaIzq;
    }

    public Ficha getFichaDer() {
        return fichaDer;
    }

    public void setFichaDer(Ficha fichaDer) {
        this.fichaDer = fichaDer;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public void actualizarLados(){
        this.setFichaDer(this.getFichasJugadas().getLast());
        this.setFichaIzq(this.getFichasJugadas().getFirst());
        notificar();
    }

    public void cargarPozo(){
        this.getPozo().cargarFichas();
        notificar();
    }

    public Ficha sacarDelPozo(){
        Ficha f=this.getPozo().getFichas().getFirst();
        this.getPozo().getFichas().removeFirst();
        notificar();
        return f;
    }
    public Ficha sacarDelPozo(int pos){
        Ficha f = this.getPozo().getFichas().get(pos);
        this.getPozo().getFichas().remove(pos);
        return f;
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

    public void reiniciarTablero() {
        fichasJugadas.clear();
        fichaIzq = null;
        fichaDer = null;
        for (Jugador j : jugadores) {
            j.getMano().clear();
        }
        this.getPozo().getFichas().clear();
        this.getPozo().cargarFichas();
        this.getPozo().repartirFichas(this.getJugadores());
    }
}
