import Modelo.Ficha;
import Modelo.Jugador;
import Modelo.Partida;
import Modelo.Ronda;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        Jugador j1=new Jugador();
        Jugador j2=new Jugador();
        j1.setNombre("Renzo");
        j2.setNombre("Ivan");

        LinkedList<Jugador> lj=new ArrayList<>();
        lj.add(j1);
        lj.add(j2);

        Partida p=new Partida(100,lj);
        Ronda r=new Ronda(lj);

        for (Jugador j:r.getJugadores()){
            System.out.println("fichas jugador"+ j.getNombre());
            j.mostrarMano();
        }

        j1.colocarFicha(r,0);
        System.out.println("fichas jugadas");
        r.mostrarFichasJugadas();
        j1.mostrarMano();


    }
}
