package Modelo.Observer;

import java.util.ArrayList;

public class Tablero {
    private ArrayList<Ficha> fichasJugadas=new ArrayList<>();
    private Ficha fichaIzq;
    private Ficha fichaDer;

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
}
