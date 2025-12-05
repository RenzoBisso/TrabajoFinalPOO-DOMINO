package Vista;

import Modelo.Ficha;

import java.util.ArrayList;
import java.util.Scanner;

public class VistaConsola {
    private Scanner sc=new Scanner(System.in);


    public int obtenerOpcion(){
        return sc.nextInt();
    }





    public void menuSesion(){
        System.out.println("##########MENU##########");
        System.out.println("1. Jugar");
        System.out.println("2. Como se juega");
        System.out.println("0. Salir");
    }



    public void mostrarFichasEnMesa(ArrayList<Ficha> fichas){
        System.out.println("FICHAS EN LA MESA");
        if(fichas.isEmpty()){
            System.out.println("No hay fichas jugadas");
        }else{
            for (Ficha f:fichas){
                if (f.isDoble()){
                    System.out.println("["+f.getLadoA()+"]");
                    System.out.println("["+f.getLadoB()+"]");
                }else{
                    System.out.print("[" + f.getLadoA() + "|" + f.getLadoB() + "]");
                }
            }
        }
    }
    public void mostrarFichasEnMano(ArrayList<Ficha> fichas){
        System.out.println("FICHAS EN LA MANO");
        if(fichas.isEmpty()){
            System.out.println("No hay fichas en la mano");
        }else{
            for (Ficha f:fichas){
                if (f.isDoble()){
                    System.out.println("["+f.getLadoA()+"]");
                    System.out.println("["+f.getLadoB()+"]");
                }else{
                    System.out.print("[" + f.getLadoA() + "|" + f.getLadoB() + "]");
                }
            }
        }
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
    }

    public String solicitarDato(String dato){
        System.out.println("Ingrese "+ dato+":");
        sc.nextLine();
        return sc.nextLine();
    }


}
