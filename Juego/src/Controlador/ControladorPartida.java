package Controlador;

import Modelo.*;

public class ControladorPartida {

    private Partida partida;

    public ControladorPartida(Partida partida) {
        this.partida = partida;
    }

    public void iniciarPartida() {

        System.out.println("=== INICIA LA PARTIDA ===");
        System.out.println("Puntos para ganar: " + partida.getPtosGanar());

        while (true) {

            System.out.println("\n=== NUEVA RONDA ===");

            Ronda ronda = new Ronda(partida.getJugadores());

            ControladorRonda controladorRonda = new ControladorRonda(ronda);
            controladorRonda.iniciarRonda();

            partida.verificarGanador();

            if (partida.getGanador() != null) {
                System.out.println("\n=== FIN DE LA PARTIDA ===");
                System.out.println("GANADOR FINAL: " + partida.getGanador().getNombre());
                return;
            }

            System.out.println("\n--- PUNTAJES ACTUALES ---");
            for (Jugador j : partida.getJugadores()) {
                System.out.println(j.getNombre() + ": " + j.getPuntosPartida());
            }
        }
    }
}
