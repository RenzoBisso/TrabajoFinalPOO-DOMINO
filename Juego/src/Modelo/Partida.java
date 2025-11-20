package Modelo;

import Modelo.Observer.Observable;
import Modelo.Observer.Observador;

import java.util.ArrayList;
import java.util.Collections;

public class Partida implements Observable {

    private Mesa mesa;
    private final Integer ptosPartida;
    private Jugador ganador;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Ronda>rondasJugadas=new ArrayList<>();
    private ArrayList<Observador> observadores = new ArrayList<>();

    public Partida(ArrayList<Jugador> jugadores,Integer ptosPartida, Mesa mesa) {
        this.jugadores = jugadores;
        this.ptosPartida = ptosPartida;
        this.mesa = mesa;
        this.generarFichas();
        this.repartirFichas();
        this.calcularJugadorInicial();

        notificar();
    }

    public ArrayList<Observador> getObservadores() {
        return observadores;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Integer getPtosPartida() {
        return ptosPartida;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public ArrayList<Ronda> getRondasJugadas() {
        return rondasJugadas;
    }

    public void setRondasJugadas(ArrayList<Ronda> rondasJugadas) {
        this.rondasJugadas = rondasJugadas;
    }

    private void generarFichas(){

        for(int i=0; i<=6; i++){
            for(int j=i; j<=6; j++){
                this.mesa.getPozo().add(new Ficha(i, j));
            }
        }
        Collections.shuffle(this.getMesa().getPozo());
    }

    private void repartirFichas(){
        for (Jugador j:this.getJugadores()){
            for(int k=0;k<7;k++){
                Ficha f=this.getMesa().getPozo().getFirst();
                j.getMano().add(f);
                this.getMesa().getPozo().removeFirst();
            }
        }
    }

    private void calcularJugadorInicial(){
        Integer max=-1;
        Jugador candidato=null;
        for (Jugador j:this.getJugadores()){
            for (Ficha f:j.getMano()){
                if(f.getValorTotal()>max){
                    max=f.getValorTotal();
                    candidato=j;
                }
            }
        }
        while (!this.getJugadores().getFirst().equals(candidato)) {
            Jugador j = this.getJugadores().removeFirst();
            this.getJugadores().add(j);
        }
    }

    private void iniciarRonda() {
        Ronda r = new Ronda(this.getJugadores());
        this.rondasJugadas.add(r);
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
