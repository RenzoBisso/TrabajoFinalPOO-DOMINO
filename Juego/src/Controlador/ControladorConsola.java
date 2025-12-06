package Controlador;

import Modelo.Ficha;
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

    public void jugada(){
        while(true){
            vista.mostrarJugador(modelo.getJugadorActual());
            vista.menuAcciones();
            int opcion=vista.obtenerOpcion();
            switch (opcion){
                case 1:
                    vista.mostrarFichasEnMano(modelo.getJugadorActual().getMano());
                    break;
                case 2:
                    vista.mostrarFichasEnMesa(modelo.getTablero().getFichasJugadas());
                    break;
                case 3:
                    vista.mostrarFichasPozo(modelo.getTablero().getPozo().getFichas());
                    String indice=vista.solicitarDato("Indice de la ficha");
                    modelo.pedirFicha(Integer.parseInt(indice));
                    break;
                case 4:
                    vista.mostrarFichasEnMano(modelo.getJugadorActual().getMano());
                    String pos=vista.solicitarDato("Indice de la ficha");
                    String s=vista.solicitarDato("Lado a colocar (true=derecha, false=izquierda)");
                    Ficha f=modelo.seleccionarFichaMano(Integer.parseInt(pos));
                    boolean flag= modelo.verificarFichaValida(f);
                    if(flag){
                        vista.mostrarMensaje("Se coloco la ficha");
                        modelo.colocarFicha(f,Boolean.parseBoolean(s));
                    }else{
                        vista.mostrarMensaje("No se puede colocar la ficha");
                    }
                    break;
                case 5:
                    modelo.pasar();
                    break;
                case 6:
                    vista.mostrarFichasPozo(modelo.getTablero().getPozo().getFichas());
                    break;
                default:
                    vista.mostrarMensaje("Opcion invalida");
            }
        }
    }



    @Override
    public void actualizar() {
        vista.mostrarFichasEnMesa(modelo.getTablero().getFichasJugadas());
    }
}
