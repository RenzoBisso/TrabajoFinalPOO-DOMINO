package Modelo.Observer;

import java.util.ArrayList;

public class Partida {
    private Tablero tablero;
    private Estado estado;
    private Integer tiempoPorTurno;
    private Jugador ganadorPartida;
    private Integer ptosPartida;


    public Partida(Tablero tablero, Integer tiempoPorTurno, Estado estado, Jugador ganadorPartida,Integer ptosPartida) {
        this.tablero = tablero;
        this.tiempoPorTurno = tiempoPorTurno;
        this.estado = estado;
        this.ganadorPartida = ganadorPartida;
        this.ptosPartida=ptosPartida;
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

    public Jugador getGanadorPartida() {
        return ganadorPartida;
    }

    public void setGanadorPartida(Jugador ganadorPartida) {
        this.ganadorPartida = ganadorPartida;
    }

    public Integer getPtosPartida() {
        return ptosPartida;
    }

    public void setPtosPartida(Integer ptosPartida) {
        this.ptosPartida = ptosPartida;
    }



}

