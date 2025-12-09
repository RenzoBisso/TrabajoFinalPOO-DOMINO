package Vista;

import Modelo.Ficha;
import Modelo.Jugador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class VistaConsolaSwing extends JFrame implements Vista {

    private JTextArea areaSalida;
    private JTextField campoEntrada;
    private JButton botonEnviar;

    private final BlockingQueue<String> colaEntrada = new LinkedBlockingQueue<>();

    public VistaConsolaSwing(String titulo) {
        super(titulo);

        SwingUtilities.invokeLater(() -> {
            setSize(500, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            areaSalida = new JTextArea();
            areaSalida.setEditable(false);
            areaSalida.setFont(new Font("Consolas", Font.PLAIN, 14));
            JScrollPane scroll = new JScrollPane(areaSalida);

            campoEntrada = new JTextField();
            botonEnviar = new JButton("Enviar");

            JPanel panelInferior = new JPanel(new BorderLayout());
            panelInferior.add(campoEntrada, BorderLayout.CENTER);
            panelInferior.add(botonEnviar, BorderLayout.EAST);

            setLayout(new BorderLayout());
            add(scroll, BorderLayout.CENTER);
            add(panelInferior, BorderLayout.SOUTH);

            ActionListener enviarAccion = e -> {
                String texto = campoEntrada.getText().trim();
                if (!texto.isEmpty()) {
                    campoEntrada.setText("");
                    try {
                        colaEntrada.put(texto);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            };

            campoEntrada.addActionListener(enviarAccion);
            botonEnviar.addActionListener(enviarAccion);

            setVisible(true);
        });
    }



    @Override
    public int obtenerOpcion() {
        mostrarMensaje("Seleccione una opcion: ");
        try {
            return Integer.parseInt(leer());
        } catch (NumberFormatException e) {
            mostrarMensaje("Opcion invalida");
            return obtenerOpcion();
        }
    }

    @Override
    public void menuSesion() {
        mostrarMensaje("##########MENU##########");
        mostrarMensaje("1. Jugar");
        mostrarMensaje("2. Como se juega");
        mostrarMensaje("0. Salir");
    }

    @Override
    public void menuAcciones() {
        mostrarMensaje("##########MENU##########");
        mostrarMensaje("1. Tomar ficha del pozo");
        mostrarMensaje("2. Colocar ficha");
        mostrarMensaje("3. Salir");
    }

    @Override
    public void mostrarJugador(Jugador j) {
        mostrarMensaje(j.getNombre());
    }




    @Override
    public void mostrarFichasEnMesa(ArrayList<Ficha> fichas) {
        mostrarMensaje("FICHAS EN LA MESA");
        if (fichas.isEmpty()) {
            mostrarMensaje("No hay fichas jugadas");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Ficha f : fichas) {
                if (f.isDoble()) {
                    sb.append("[").append(f.getLadoA()).append("]");
                } else {
                    sb.append("[").append(f.getLadoA()).append("|").append(f.getLadoB()).append("]");
                }
            }
            mostrarMensaje(sb.toString());
        }
    }

    @Override
    public void mostrarFichasEnMano(ArrayList<Ficha> fichas) {
        mostrarMensaje("FICHAS EN LA MANO");
        int count = 0;

        if (fichas.isEmpty()) {
            mostrarMensaje("No hay fichas en la mano");
        } else {
            for (Ficha f : fichas) {
                if (f.isDoble()) {
                    mostrarMensaje("Indice:" + count + " [" + f.getLadoA() + "]");
                } else {
                    mostrarMensaje("Indice:" + count + " [" + f.getLadoA() + "|" + f.getLadoB() + "]");
                }
                count++;
            }
        }
        mostrarMensaje(" ");
    }

    @Override
    public void menuLogin() {
        mostrarMensaje("##########DOMINÓ##########");
        mostrarMensaje("##########MENU INICIAL##########");
        mostrarMensaje("1. Registrarse");
        mostrarMensaje("2. Iniciar Sesion");
        mostrarMensaje("0. Salir");
    }

    @Override
    public void mostrarFichasPozo(ArrayList<Ficha> fichas) {
        mostrarMensaje("FICHAS EN EL POZO");
        int count = 0;

        if (fichas.isEmpty()) {
            mostrarMensaje("El pozo esta vacio");
        } else {
            for (Ficha f : fichas) {
                mostrarMensaje("[" + count + "]");
                count++;
            }
        }
        mostrarMensaje(" ");
    }

    @Override
    public void mostrarPtos(ArrayList<Jugador> jugadores) {
        mostrarMensaje("Puntos de jugadores");
        for (Jugador j:jugadores){
            mostrarMensaje(j.getNombre()+j.getPtos());
        }
    }

    @Override
    public String solicitarDato(String dato) {
        mostrarMensaje("Ingrese " + dato + ":");
        String input;
        while (true) {
            input = leer();
            if (input == null) {
                return null;
            }
            input = input.trim();
            if (!input.isEmpty()) return input;
            mostrarMensaje("Entrada vacía. Ingrese " + dato + " nuevamente:");
        }
    }

    @Override
    public void mostrarMensaje(String s) {
        SwingUtilities.invokeLater(() -> areaSalida.append(s + "\n"));
    }


    private String leer() {
        try {
            return colaEntrada.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
    }
}

