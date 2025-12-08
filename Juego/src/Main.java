import Controlador.ControladorConsola;
import Modelo.*;
import Vista.Vista;
import Vista.VistaConsolaSwing;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Jugador> jugadores = new ArrayList<>();

        Jugador j1 = new Jugador("Renzo");
        Jugador j2 = new Jugador("Francisco");


        jugadores.add(j1);
        jugadores.add(j2);


        Tablero tablero = new Tablero(jugadores);
        Partida partida = new Partida(1, tablero, 50, Estado.EN_CURSO, 100);
        Ronda ronda = new Ronda(partida, tablero);


        ArrayList<Vista> vistas = new ArrayList<>();
        vistas.add(new VistaConsolaSwing("Ventana 1"));
        vistas.add(new VistaConsolaSwing("Ventana 2"));

        ControladorConsola controlador =
                new ControladorConsola(ronda, vistas);

        new Thread(() -> controlador.jugada()).start();
    }
}


