package Modelo.Observer;

import java.util.ArrayList;

public class Pozo {
    private ArrayList<Ficha> fichas;

    public Pozo(ArrayList<Ficha> fichas) {
        this.fichas = fichas;
    }

    public ArrayList<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(ArrayList<Ficha> fichas) {
        this.fichas = fichas;
    }
}
