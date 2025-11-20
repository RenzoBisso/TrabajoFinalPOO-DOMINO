package Modelo;

import java.util.ArrayList;
import java.util.LinkedList;

public class Partida{
    private Jugador ganador;
    private final Integer ptosGanar;
    private LinkedList<Jugador> jugadores;

    public Partida(Integer ptosGanar, LinkedList<Jugador> jugadores) {
        this.ptosGanar = ptosGanar;
        this.jugadores = jugadores;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }

    public Integer getPtosGanar() {
        return ptosGanar;
    }

    public LinkedList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(LinkedList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }
    public void verificarGanador(){
        for (Jugador j:this.getJugadores()){
            if(j.getPuntosPartida().equals(this.getPtosGanar())){
                this.setGanador(j);
                break;
            }
        }
    }
}
