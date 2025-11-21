package Vista;

import Modelo.Ficha;
import Modelo.Jugador;
import Modelo.Ronda;

import java.util.ArrayList;
import java.util.Scanner;

public class VistaConsola {
    private Scanner sc=new Scanner(System.in);

    public void mostrarMenuRonda(){
        System.out.println("--------- MENU JUEGO ---------");
        System.out.println("1. Robar ficha");
        System.out.println("2. Colocar ficha");
        System.out.println("3. Mostrar mesa");
        System.out.println("4. Mostrar mano");
        System.out.println("seleccione una opcion: ");
    }

    public int obtenerOpcion(){
        return sc.nextInt();
    }

    public void mostrarMesa(Ronda r){
        for (Ficha f:r.getFichasJugadas()){
            System.out.println(f.toString());
        }
    }
    public void mostrarMano(Jugador j) {
        ArrayList<Ficha> mano = j.getMano();
        for (int i = 0; i < mano.size(); i++) {
            System.out.println(i + "\n" + mano.get(i).toString());
        }
    }

    public void mostrarJugador(Ronda r){
        System.out.println(r.getJugadorActual().getNombre());
    }
    public int pedirIndiceFicha() {
        System.out.print("Elige el índice de la ficha a jugar: ");
        return sc.nextInt();
    }


    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
}