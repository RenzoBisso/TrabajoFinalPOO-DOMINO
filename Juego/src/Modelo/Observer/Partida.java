package Modelo.Observer;

import java.util.ArrayList;

public class Partida {
    private Tablero tablero;
    private Integer tiempoPorTurno;
    private Estado estado;
    private Integer ptosPartida;
    private ArrayList<Jugador> jugadores;
    private Jugador ganadorPartida;

    public Partida(Tablero tablero, Integer tiempoPorTurno, Estado estado, Integer ptosPartida, ArrayList<Jugador> jugadores, Jugador ganadorPartida) {
        this.tablero = tablero;
        this.tiempoPorTurno = tiempoPorTurno;
        this.estado = estado;
        this.ptosPartida = ptosPartida;
        this.jugadores = jugadores;
        this.ganadorPartida = ganadorPartida;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Integer getTiempoPorTurno() {
        return tiempoPorTurno;
    }

    public void setTiempoPorTurno(Integer tiempoPorTurno) {
        this.tiempoPorTurno = tiempoPorTurno;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getPtosPartida() {
        return ptosPartida;
    }

    public void setPtosPartida(Integer ptosPartida) {
        this.ptosPartida = ptosPartida;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Jugador getGanadorPartida() {
        return ganadorPartida;
    }

    public void setGanadorPartida(Jugador ganadorPartida) {
        this.ganadorPartida = ganadorPartida;
    }





}

