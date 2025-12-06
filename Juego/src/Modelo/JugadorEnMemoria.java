package Modelo;

public class JugadorEnMemoria {
    static private Jugador jugadorEnMemoria;




    public static void iniciarSesion(Jugador jugador) {
        jugadorEnMemoria = jugador;
    }

    public static Jugador getJugadorActual() {
        return jugadorEnMemoria;
    }

    public static boolean haySesion() {
        return jugadorEnMemoria != null;
    }

    public static void cerrarSesion() {
        jugadorEnMemoria = null;
    }


}
