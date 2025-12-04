package Vista;

import Modelo.Ficha;

import java.util.ArrayList;
import java.util.Scanner;

public class VistaConsola {
    private Scanner sc=new Scanner(System.in);


    public int obtenerOpcion(){
        return sc.nextInt();
    }



    public void menuPrincipal(){
        System.out.println("##########DOMINÓ##########");
        System.out.println("##########MENU INICIAL##########");
        System.out.println("1. Registrarse");
        System.out.println("2. Jugar");
        System.out.println("3. Como se juega");
        System.out.println("0. Salir");
    }





    public void mostrarFichas(ArrayList<Ficha> fichas){
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

    public void mostrarRegistro(){
        System.out.println("##########REGISTRARSE##########");

    }

}
