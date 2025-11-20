package Controlador;

import Modelo.*;

import java.util.Scanner;

public class ControladorRonda {

    private Ronda ronda;
    private int turnoActual = 0;
    private int jugadoresQuePasaron = 0;
    private final Scanner sc = new Scanner(System.in);

    public ControladorRonda(Ronda ronda) {
        this.ronda = ronda;
    }

    public void iniciarRonda() {

        while (true) {

            Jugador j = ronda.getJugadores().get(turnoActual);

            System.out.println("\n==============================");
            System.out.println("Turno de: " + j.getNombre());
            System.out.println("==============================");

            mostrarEstadoRonda(j);

            if (ronda.getFichasJugadas().isEmpty()) {
                jugarPrimeraFicha(j);
                if (j.getMano().isEmpty()) {
                    ronda.verificarFinRonda();
                    break;
                }
                pasarTurno();
                continue;
            }

            if (puedeJugar(j)) {
                if (jugarFicha(j)) {
                    if (j.getMano().isEmpty()) {
                        ronda.verificarFinRonda();
                        break;
                    }
                    jugadoresQuePasaron = 0;
                    pasarTurno();
                    continue;
                }

                continue;
            }


            if (!ronda.getPozo().getPozo().isEmpty()) {
                j.robarFicha(ronda);
                System.out.println(j.getNombre() + " robó una ficha.");
                continue;
            }

            System.out.println(j.getNombre() + " no puede jugar y NO puede robar → pasa.");
            jugadoresQuePasaron++;

            if (jugadoresQuePasaron == ronda.getJugadores().size()) {
                System.out.println("\n*** LA RONDA ESTÁ BLOQUEADA ***");
                ronda.verificarFinRonda();
                break;
            }

            pasarTurno();
        }
    }

    private void pasarTurno() {
        turnoActual = (turnoActual + 1) % ronda.getJugadores().size();
    }

    private boolean puedeJugar(Jugador j) {
        int izquierda = ronda.getFichasJugadas().getFirst().getLado1();
        int derecha   = ronda.getFichasJugadas().getLast().getLado2();

        for (Ficha f : j.getMano()) {
            if (f.getLado1().equals(izquierda) || f.getLado2().equals(izquierda)) return true;
            if (f.getLado1().equals(derecha) || f.getLado2().equals(derecha)) return true;
        }

        return false;
    }

    private boolean jugarFicha(Jugador j) {

        System.out.println("Seleccione el índice de la ficha a jugar:");
        int indice = leerEntero();

        if (indice < 0 || indice >= j.getMano().size()) {
            System.out.println("Índice inválido.");
            return false;
        }

        Ficha f = j.seleccionarFicha(indice);

        int antes = ronda.getFichasJugadas().size();
        j.colocarFicha(ronda, indice);
        int despues = ronda.getFichasJugadas().size();

        if (antes == despues) {
            System.out.println("Esa ficha NO encaja. Intente otra.");
            return false;
        }

        return true;
    }

    private void jugarPrimeraFicha(Jugador j) {
        System.out.println("Seleccione la primera ficha a tirar:");
        int indice = leerEntero();

        if (indice < 0 || indice >= j.getMano().size()) {
            System.out.println("Índice inválido.");
            return;
        }

        Ficha f = j.seleccionarFicha(indice);
        ronda.getFichasJugadas().add(f);
        j.getMano().remove(indice);
    }

    private int leerEntero() {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.println("Ingrese un número válido:");
        }
        return sc.nextInt();
    }

    private void mostrarEstadoRonda(Jugador j) {
        System.out.println("\nFichas jugadas en mesa:");
        ronda.mostrarFichasJugadas();

        System.out.println("\nTu mano:");
        j.mostrarMano();

        System.out.println("\nFichas restantes en pozo: " + ronda.getPozo().getPozo().size());
    }
}
