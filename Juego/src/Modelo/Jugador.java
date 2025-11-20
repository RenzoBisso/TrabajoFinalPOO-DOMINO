package Modelo;

import java.util.ArrayList;

public class Jugador {

    private String nombre;
    private ArrayList<Ficha> mano=new ArrayList<>();
    private Integer puntosPartida=0;

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

    public Ficha seleccionarFicha(int indice){
        if (indice >= 0 && indice < this.getMano().size()) {
            return this.getMano().get(indice);
        } else {
            System.out.println("Posición inválida");
            return null;
        }
    }

    public void colocarFicha(Ronda ronda, int indice){

        Ficha f = this.seleccionarFicha(indice);

        if (f == null) return;

        if (ronda.getFichasJugadas().isEmpty()) {
            ronda.getFichasJugadas().add(f);
            this.getMano().remove(indice);
            return;
        }

        Ficha primerFicha = ronda.getFichasJugadas().getFirst();
        Ficha ultimaFicha = ronda.getFichasJugadas().getLast();

        if (primerFicha.getLado1().equals(f.getLado2())) {
            ronda.getFichasJugadas().addFirst(f);
            this.getMano().remove(indice);
            return;
        }
        if (primerFicha.getLado1().equals(f.getLado1())) {
            f.rotarFicha();
            ronda.getFichasJugadas().addFirst(f);
            this.getMano().remove(indice);
            return;
        }

        if (ultimaFicha.getLado2().equals(f.getLado1())) {
            ronda.getFichasJugadas().addLast(f);
            this.getMano().remove(indice);
            return;
        }
        if (ultimaFicha.getLado2().equals(f.getLado2())) {
            f.rotarFicha();
            ronda.getFichasJugadas().addLast(f);
            this.getMano().remove(indice);
            return;
        }

        System.out.println("Ficha incompatible, pruebe con otra.");
    }

    public void robarFicha(Ronda ronda){
        Ficha f=ronda.getPozo().getPozo().getFirst();
        this.getMano().add(f);
        ronda.getPozo().getPozo().removeFirst();
    }
    public void mostrarMano(){
        System.out.println("fichas jugador"+ this.getNombre());
        int count=0;
        for(Ficha f:this.getMano()){
            System.out.println(count);
            String string = f.toString();
            System.out.println(string);
            count++;
        }
    }

}
