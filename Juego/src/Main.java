import Controlador.ControladorConsola;
import Modelo.*;
import Vista.VistaConsola;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Jugador>jugadores=new ArrayList<>();

        Jugador j1=new Jugador("Renzo");
        Jugador j2=new Jugador("Francisco");
        Jugador j3=new Jugador("Juan");
        jugadores.add(j1);
        jugadores.add(j2);
        jugadores.add(j3);

        Tablero tablero=new Tablero(jugadores);
        Partida partida=new Partida(1,tablero,50, Estado.EN_CURSO,100);
        Ronda ronda=new Ronda(partida,tablero);

        VistaConsola vista=new VistaConsola();
        ControladorConsola controladorConsola=new ControladorConsola(ronda,vista);
        controladorConsola.jugada();

        



    }
}

