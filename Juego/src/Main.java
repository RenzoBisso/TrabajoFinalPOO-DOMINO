
import Controlador.ControladorPartida;

import Vista.VistaConsola;


public class Main {
    public static void main(String[] args) {
        VistaConsola vista = new VistaConsola();
        ControladorPartida controlador = new ControladorPartida(vista);
        controlador.iniciar();
    }
}

