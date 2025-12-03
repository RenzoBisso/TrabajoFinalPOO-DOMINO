package Modelo.Observer;

import java.util.ArrayList;

public class Jugador {

    private String nombre;
    private Integer indice;
    private ArrayList<Ficha> mano=new ArrayList<>();
    private Integer xp=0;
    private Integer ptos=0;


    public Jugador(String nombre) {
        this.nombre = nombre;
    }

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

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public Integer getIndice() {
        return indice;
    }

    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    public Integer getPtos() {
        return ptos;
    }

    public void setPtos(Integer ptos) {
        this.ptos = ptos;
    }
}
