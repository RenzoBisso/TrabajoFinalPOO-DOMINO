package Controlador;

import Modelo.Observer.Observador;
import Modelo.Ronda;
import Vista.VistaConsola;

public class ControladorConsola implements Observador {
    private Ronda modelo;
    private VistaConsola vista;

    public ControladorConsola(Ronda modelo, VistaConsola vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.modelo.agregarObservador(this);
    }

    @Override
    public void actualizar() {

    }
}
