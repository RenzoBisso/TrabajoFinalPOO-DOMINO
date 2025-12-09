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
            vistaActual.mostrarFichasEnMesa(modelo.getTablero().getFichasJugadas());
            vistaActual.mostrarFichasEnMano(modelo.getJugadorActual().getMano());
            vistaActual.menuAcciones();
            int opcion = vistaActual.obtenerOpcion();
            switch (opcion) {
                case 1:
                    vistaActual.mostrarFichasPozo(modelo.getTablero().getPozo().getFichas());
                    vistaActual.mostrarMensaje("Tomando Ficha");
                    String indice = vistaActual.solicitarDato("Indice de la ficha");
                    boolean ok = modelo.pedirFicha(Integer.parseInt(indice));
                    if (!ok) {
                        vistaActual.mostrarMensaje("La ficha no sirve. Intente con otra.");
                    } else {
                        vistaActual.mostrarMensaje("Tomaste la ficha correctamente.");
                    }
                    break;
                case 2:
                    vistaActual.mostrarFichasEnMesa(modelo.getTablero().getFichasJugadas());
                    vistaActual.mostrarMensaje("Colocando Ficha");
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
                        vistaActual.mostrarPtos(modelo.getTablero().getJugadores());
                        avanzarTurno();
                    } else {
                        vistaActual.mostrarMensaje("No se puede colocar la ficha");
                    }
                    break;
                case 3:
                    vistaActual.mostrarMensaje("Saliendo de la partida");
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
