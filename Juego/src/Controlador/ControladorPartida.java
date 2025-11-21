package Controlador;

import Modelo.App;
import Modelo.Jugador;
import Modelo.Partida;
import Modelo.Ronda;
import Vista.VistaConsola;

import java.util.LinkedList;

public class ControladorPartida {

    private VistaConsola vista;

    public ControladorPartida(VistaConsola vista) {
        this.vista = vista;
    }

    public void iniciar() {
        boolean salir = false;

        while (!salir) {
            vista.mostrarMenuInicial();
            int opcion = vista.obtenerOpcion();

            switch (opcion) {
                case 1:
                    crearPartida();
                    break;
                case 2:
                    System.out.println("Saliendo del juego...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void crearPartida() {
        int puntos = vista.pedirPuntosParaGanar();

        LinkedList<Jugador> jugadores = new LinkedList<>();
        jugadores.add(new Jugador());
        jugadores.get(0).setNombre(vista.pedirNombreJugador(1));

        jugadores.add(new Jugador());
        jugadores.get(1).setNombre(vista.pedirNombreJugador(2));

        Partida partida = new Partida(puntos, jugadores);
        Ronda ronda = new Ronda(jugadores);

        ControladorConsola controladorRonda = new ControladorConsola(
                new App(partida, ronda),
                vista
        );

        controladorRonda.iniciarRonda();
    }
}
