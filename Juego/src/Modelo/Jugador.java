package Modelo;

import java.util.ArrayList;

public class Jugador {

    private String nombre;
    private ArrayList<Ficha> mano;
    private Integer puntosPartida;
    private Integer puntosRonda;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Ficha> getMano() {
        return mano;
    }

    public void setMano(ArrayList<Ficha> mano) {
        this.mano = mano;
    }

    public Integer getPuntosPartida() {
        return puntosPartida;
    }

    public void setPuntosPartida(Integer puntosPartida) {
        this.puntosPartida = puntosPartida;
    }

    public Integer getPuntosRonda() {
        return puntosRonda;
    }

    public void setPuntosRonda(Integer puntosRonda) {
        this.puntosRonda = puntosRonda;
    }
}
