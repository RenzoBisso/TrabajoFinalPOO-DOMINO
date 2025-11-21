package Controlador;

import Modelo.App;
import Modelo.Jugador;
import Modelo.Observer.Observador;
import Modelo.Ronda;
import Vista.VistaConsola;

public class ControladorConsola implements Observador {
    private App modelo;
    private VistaConsola vista;

    public ControladorConsola(App modelo, VistaConsola vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.modelo.agregarObservador(this);
    }

    public void iniciarRonda() {
        while (!this.getModelo().getRonda().rondaFinalizada()) {
            vista.mostrarJugador(this.getModelo().getRonda());
            Jugador actual = modelo.getRonda().getJugadorActual();
            vista.mostrarMano(actual);
            boolean colocada = false;

            while (!colocada) {
                int indice = vista.pedirIndiceFicha();
                boolean resultado = modelo.getRonda().jugarTurno(indice);

                if (!resultado) {
                    vista.mostrarMensaje("Ficha incompatible. Elegí otra.");
                } else {
                    colocada = true;

                    vista.mostrarMensaje("Mesa actualizada:");
                    vista.mostrarMesa(modelo.getRonda());
                }
            }
        }
        vista.mostrarMensaje("Ronda finalizada.");
    }

    public App getModelo() {
        return modelo;
    }

    public void setModelo(App modelo) {
        this.modelo = modelo;
    }

    public VistaConsola getVista() {
        return vista;
    }

    public void setVista(VistaConsola vista) {
        this.vista = vista;
    }

    @Override
    public void actualizar() {

    }
}
