package Vista;

import Modelo.Ficha;
import Modelo.Jugador;

import java.util.ArrayList;
import java.util.Scanner;

public class VistaConsola implements Vista{
    private Scanner sc=new Scanner(System.in);

    public VistaConsola() {
    }

    public int obtenerOpcion(){
        System.out.println("Seleccione una opcion: ");
        return sc.nextInt();
    }





    public void menuSesion(){
        System.out.println("##########MENU##########");
        System.out.println("1. Jugar");
        System.out.println("2. Como se juega");
        System.out.println("0. Salir");
    }

    public void menuAcciones(){
        System.out.println("##########MENU##########");
        System.out.println("1. Mostrar mano");
        System.out.println("2. Mostrar mesa");
        System.out.println("3. Tomar ficha");
        System.out.println("4. Colocar ficha");
        System.out.println("5. Pasar");
        System.out.println("6. Mostrar pozo");
        System.out.println("0. Salir");
    }

    public void mostrarJugador(Jugador j){
        System.out.println(j.getNombre());
    }

    public void mostrarFichasEnMesa(ArrayList<Ficha> fichas){
        System.out.println("FICHAS EN LA MESA");
        if(fichas.isEmpty()){
            System.out.println("No hay fichas jugadas");
        }else{
            for (Ficha f:fichas){
                if (f.isDoble()){
                    System.out.println("["+f.getLadoA()+"]");
                }else{
                    System.out.print("[" + f.getLadoA() + "|" + f.getLadoB() + "]");
                }
            }
        }
    }
    public void mostrarFichasEnMano(ArrayList<Ficha> fichas){
        System.out.println("FICHAS EN LA MANO");
        int count=0;
        if(fichas.isEmpty()){
            System.out.println("No hay fichas en la mano");
        }else{
            for (Ficha f:fichas){
                if (f.isDoble()){
                    System.out.print("Indice:"+count+"["+f.getLadoA()+"]");
                }else{
                    System.out.print("Indice:"+count+"[" + f.getLadoA() + "|" + f.getLadoB() + "]");
                }
                System.out.println(" ");
                count++;
            }
        }
        System.out.println(" ");
    }

    public void menuLogin(){
        System.out.println("##########DOMINÓ##########");
        System.out.println("##########MENU INICIAL##########");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar Sesion");
        System.out.println("0. Salir");
    }


    public void mostrarFichasPozo(ArrayList<Ficha> fichas){
        System.out.println("FICHAS EN EL POZO");
        int count=0;
        if(fichas.isEmpty()){
            System.out.println("El pozo esta vacio");
        }else{
            for (Ficha f:fichas){
                System.out.println("["+count+"]");
                count++;
            }
        }
        System.out.println(" ");

    }

    public String solicitarDato(String dato){
        System.out.println("Ingrese "+ dato+":");
        sc.nextLine();
        return sc.nextLine();
    }

    public void mostrarMensaje(String s){
        System.out.println(s);
    }

}
