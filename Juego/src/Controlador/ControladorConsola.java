package Controlador;

import Modelo.Ficha;
import Modelo.Observer.Observador;
import Modelo.Ronda;
import Vista.Vista;

import java.util.ArrayList;

public class ControladorConsola implements Observador {

    private Ronda modelo;
    private ArrayList<Vista> vistas;
    private int turno = 0;

    public ControladorConsola(Ronda modelo, ArrayList<Vista> vistas) {
        this.modelo = modelo;
        this.vistas = vistas;
        this.modelo.agregarObservador(this);
    }

    public void jugada() {
        while (true) {

            Vista vistaActual = vistas.get(turno);
            vistaActual.mostrarMensaje("================================");
            vistaActual.mostrarMensaje("Turno de:");
            vistaActual.mostrarJugador(modelo.getJugadorActual());
            vistaActual.mostrarMensaje("================================");

            vistaActual.menuAcciones();
            int opcion = vistaActual.obtenerOpcion();

            switch (opcion) {

                case 1:
                    vistaActual.mostrarFichasEnMano(
                            modelo.getJugadorActual().getMano()
                    );
                    break;

                case 2:
                    vistaActual.mostrarFichasEnMesa(
                            modelo.getTablero().getFichasJugadas()
                    );
                    break;

                case 3:
                    vistaActual.mostrarFichasPozo(
                            modelo.getTablero().getPozo().getFichas()
                    );
                    String indice = vistaActual.solicitarDato("Indice de la ficha");
                    modelo.pedirFicha(Integer.parseInt(indice));
                    break;

                case 4:
                    vistaActual.mostrarFichasEnMano(
                            modelo.getJugadorActual().getMano()
                    );

                    String pos = vistaActual.solicitarDato("Indice de la ficha");
                    String s = vistaActual.solicitarDato("Lado a colocar (true=derecha, false=izquierda)");

                    Ficha f = modelo.seleccionarFichaMano(Integer.parseInt(pos));

                    boolean flag = modelo.verificarFichaValida(f);

                    if (flag) {
                        vistaActual.mostrarMensaje("Se coloco la ficha");
                        modelo.colocarFicha(f, Boolean.parseBoolean(s));
                        modelo.pasar();
                        avanzarTurno();
                    } else {
                        vistaActual.mostrarMensaje("No se puede colocar la ficha");
                    }
                    break;

                case 5:
                    modelo.pasar();
                    avanzarTurno();
                    break;

                case 6:
                    vistaActual.mostrarFichasPozo(
                            modelo.getTablero().getPozo().getFichas()
                    );
                    break;

                default:
                    vistaActual.mostrarMensaje("Opcion invalida");
            }
        }
    }

    private void avanzarTurno() {
        turno++;
        if (turno >= vistas.size()) {
            turno = 0;
        }
    }

    @Override
    public void actualizar() {
        for (Vista v : vistas) {
            v.mostrarFichasEnMesa(
                    modelo.getTablero().getFichasJugadas()
            );
        }
    }
}
