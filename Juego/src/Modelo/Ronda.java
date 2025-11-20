package Modelo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Ronda {
    private Pozo pozo=new Pozo();
    private LinkedList<Jugador> jugadores;
    private LinkedList<Ficha> fichasJugadas=new LinkedList<>();
    private int indiceJugadorActual = 0;
    private int jugadoresQuePasaron = 0;

    public Ronda(LinkedList<Jugador> jugadores) {
        this.jugadores = jugadores;
        this.generarFichas();
        this.repartirFichas();
    }

    public Pozo getPozo() {
        return pozo;
    }

    public void setPozo(Pozo pozo) {
        this.pozo = pozo;
    }

    public LinkedList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(LinkedList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public LinkedList<Ficha> getFichasJugadas() {
        return fichasJugadas;
    }

    public void setFichasJugadas(LinkedList<Ficha> fichasJugadas) {
        this.fichasJugadas = fichasJugadas;
    }

    public void verificarFinRonda() {
        Jugador ganadorRonda = null;

        for (Jugador j : this.jugadores) {
            if (j.getMano().isEmpty()) {
                ganadorRonda = j;
                break;
            }
        }

        if (ganadorRonda != null) {
            int total = 0;

            for (Jugador j : this.jugadores) {
                if (j != ganadorRonda) {
                    for (Ficha f : j.getMano()) {
                        total += f.getValorTotal();
                    }
                }
            }

            ganadorRonda.setPuntosPartida(
                    ganadorRonda.getPuntosPartida() + total
            );

            System.out.println("Ganador de la ronda: " + ganadorRonda.getNombre());
            System.out.println("Puntos sumados: " + total);

        } else {
            System.out.println("Todavía no hay ganador de la ronda");
        }
    }


    public void generarFichas(){

        for(int i=0; i<=6; i++){
            for(int j=i; j<=6; j++){
                this.getPozo().getPozo().add(new Ficha(i, j));
            }
        }
        Collections.shuffle(this.getPozo().getPozo());
    }

    public void repartirFichas(){
        for (Jugador j:this.getJugadores()){
            for(int k=0;k<7;k++){
                Ficha f=this.getPozo().getPozo().getFirst();
                j.getMano().add(f);
                this.getPozo().getPozo().removeFirst();
            }
        }
    }
    public void mostrarFichasJugadas(){
        for(Ficha f:this.getFichasJugadas()){
            String string = f.toString();
            System.out.println(string);
        }
    }


    public Jugador getJugadorActual() {
        return jugadores.get(indiceJugadorActual);
    }

    public void pasarAlSiguienteJugador() {
        indiceJugadorActual = (indiceJugadorActual + 1) % jugadores.size();
    }
    public boolean puedeJugar(Jugador j) {

        if (fichasJugadas.isEmpty()) {
            return true;
        }

        int izquierda = fichasJugadas.getFirst().getLado1();
        int derecha   = fichasJugadas.getLast().getLado2();

        for (Ficha f : j.getMano()) {
            if (f.getLado1().equals(izquierda) || f.getLado2().equals(izquierda)) {
                return true;
            }
            if (f.getLado1().equals(derecha) || f.getLado2().equals(derecha)) {
                return true;
            }
        }

        return false;
    }
    public void jugarTurno(int indiceFichaElegida) {

        Jugador actual = getJugadorActual();
        if (fichasJugadas.isEmpty()) {
            Ficha f = actual.seleccionarFicha(indiceFichaElegida);
            if (f != null) {
                fichasJugadas.add(f);
                actual.getMano().remove(indiceFichaElegida);
                jugadoresQuePasaron = 0;
                pasarAlSiguienteJugador();
            }
            return;
        }

        if (puedeJugar(actual)) {
            actual.colocarFicha(this, indiceFichaElegida);
            jugadoresQuePasaron = 0;

            if (actual.getMano().isEmpty()) {
                verificarFinRonda();
                return;
            }
            pasarAlSiguienteJugador();
            return;
        }

        if (!pozo.getPozo().isEmpty()) {
            actual.robarFicha(this);
            System.out.println(actual.getNombre() + " robó una ficha.");

            return;
        }

        jugadoresQuePasaron++;
        System.out.println(actual.getNombre() + " pasa.");

        if (jugadoresQuePasaron == jugadores.size()) {
            System.out.println("La ronda está bloqueada.");
            verificarFinRonda();
            return;
        }

        pasarAlSiguienteJugador();
    }

    public boolean rondaBloqueada() {
        return jugadoresQuePasaron == jugadores.size();
    }

}


