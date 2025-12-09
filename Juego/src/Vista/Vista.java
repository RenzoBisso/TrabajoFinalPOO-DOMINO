package Vista;

import Modelo.Ficha;
import Modelo.Jugador;

import java.util.ArrayList;

public interface Vista {
    int obtenerOpcion();
    void menuSesion();
    void menuAcciones();
    void menuLogin();

    void mostrarJugador(Jugador j);
    void mostrarFichasEnMesa(ArrayList<Ficha> fichas);
    void mostrarFichasEnMano(ArrayList<Ficha> fichas);
    void mostrarFichasPozo(ArrayList<Ficha> fichas);
    void mostrarPtos(ArrayList<Jugador>jugadores);


    String solicitarDato(String dato);
    void mostrarMensaje(String s);
}
