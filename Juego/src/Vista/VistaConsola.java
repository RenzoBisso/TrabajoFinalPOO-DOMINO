package Vista;

import Modelo.Ronda;

import java.util.Scanner;

public class VistaConsola {
    private Scanner sc=new Scanner(System.in);

    public void mostrarMenuJuego(){
        System.out.println("--------- MENU JUEGO ---------");
        System.out.println("1. Robar ficha");
        System.out.println("2. Colocar ficha");
        System.out.println("3. Mostrar mesa");
        System.out.println("4. mostrar mano");
        System.out.println("seleccione una opcion: ");
    }

    public int obtenerOpcion(){
        return sc.nextInt();
    }

    public void mostrarMesa(Ronda r){

    }



}