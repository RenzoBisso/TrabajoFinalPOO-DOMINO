package Modelo;

import java.util.ArrayList;

public class Jugador {

    private String nombre;
    private ArrayList<Ficha> mano = new ArrayList<>();
    private Integer puntosPartida = 0;

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
    public Ficha seleccionarFicha(int indice) {
        if (indice >= 0 && indice < this.getMano().size()) {
            return this.getMano().get(indice);
        }
        System.out.println("Posición inválida.");
        return null;
    }




    public boolean colocarFicha(Ronda ronda, int indice) {

        if (indice < 0 || indice >= this.getMano().size()) {
            System.out.println("Posición inválida");
            return false;
        }

        Ficha fichaSeleccionada = this.getMano().get(indice);

        if (ronda.getFichasJugadas().isEmpty()) {
            ronda.getFichasJugadas().add(fichaSeleccionada);
            this.getMano().remove(fichaSeleccionada);
            return true;
        }

        Ficha primer = ronda.getFichasJugadas().getFirst();
        Ficha ultima = ronda.getFichasJugadas().getLast();

        if (primer.getLado1().equals(fichaSeleccionada.getLado2())) {
            ronda.getFichasJugadas().addFirst(fichaSeleccionada);
            this.getMano().remove(fichaSeleccionada);
            return true;
        }

        if (primer.getLado1().equals(fichaSeleccionada.getLado1())) {
            fichaSeleccionada.rotarFicha();
            ronda.getFichasJugadas().addFirst(fichaSeleccionada);
            this.getMano().remove(fichaSeleccionada);
            return true;
        }

        if (ultima.getLado2().equals(fichaSeleccionada.getLado1())) {
            ronda.getFichasJugadas().addLast(fichaSeleccionada);
            this.getMano().remove(fichaSeleccionada);
            return true;
        }

        if (ultima.getLado2().equals(fichaSeleccionada.getLado2())) {
            fichaSeleccionada.rotarFicha();
            ronda.getFichasJugadas().addLast(fichaSeleccionada);
            this.getMano().remove(fichaSeleccionada);
            return true;
        }

        System.out.println("Ficha incompatible.");
        return false;
    }


    public void robarFicha(Ficha f) {

        this.getMano().add(f);
    }

}
