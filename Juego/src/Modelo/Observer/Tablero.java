package Modelo.Observer;

import java.util.ArrayList;

public class Tablero {
    private ArrayList<Ficha> fichasJugadas=new ArrayList<>();
    private Ficha fichaIzq;
    private Ficha fichaDer;
    private Pozo pozo;
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


    public void actualizarLados(){
        this.setFichaDer(this.getFichasJugadas().getLast());
        this.setFichaIzq(this.getFichasJugadas().getFirst());
    }

    public void cargarPozo(){
        this.getPozo().cargarFichas();
    }

    public void repartirFichas(Ronda ronda){
        for (Jugador j:ronda.getJugadores()){
            for (int f=0;f<=7;f++){
                Ficha ficha=this.getPozo().getFichas().getFirst();
                this.getPozo().getFichas().removeFirst();
                j.getMano().add(ficha);
            }
        }
    }







}
