import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 🔹 Crear jugadores
        Usuario j1 = new Usuario();
        j1.setNombreUsuario("Ana");
        Usuario j2 = new Usuario();
        j2.setNombreUsuario("Luis");
        Usuario j3 = new Usuario();
        j3.setNombreUsuario("Marta");


        // 🔹 Crear mesa con los jugadores
        ArrayList<Usuario> jugadores = new ArrayList<>();
        jugadores.add(j1);
        jugadores.add(j2);
        jugadores.add(j3);
        Mesa mesa = new Mesa(jugadores);


        // 🔹 Crear partida y ronda inicial
        Partida partida = new Partida(100, mesa);
        Ronda ronda = new Ronda(j1, partida); // j1 es jugador mano
        partida.getRondas().add(ronda);

        // 🔹 Simular algunas jugadas
        System.out.println("Comienza la Ronda " + ronda.getNroRonda());
        System.out.println("Jugador inicial: " + ronda.getJugadorMano().getNombreUsuario());

        // Turno 1
        ronda.setJugadorActual(j1);
        System.out.println("Turno de: " + ronda.getJugadorActual().getNombreUsuario());
        partida.getRondas().getLast().siguienteTurno(partida);
        j1.getManoActual().mostrarFichas();
        System.out.println("------------------------------------------------------------");
        j1.ponerFichaEnMesa(mesa,j1.getManoActual().getFichasEnMano().getFirst(),partida);
        mesa.mostrarFichasJugadas();
        System.out.println("------------------------------------------------------------");
        j1.getManoActual().mostrarFichas();


        // Turno 2
        System.out.println("Turno de: " + ronda.getJugadorActual().getNombreUsuario());
        partida.getRondas().getLast().siguienteTurno(partida);
        j2.getManoActual().mostrarFichas();
        System.out.println("------------------------------------------------------------");
        j2.ponerFichaEnMesa(mesa,j2.getManoActual().getFichasEnMano().getFirst(),partida);
        mesa.mostrarFichasJugadas();
        System.out.println("------------------------------------------------------------");
        j2.getManoActual().mostrarFichas();


        // Turno 3
        System.out.println("Turno de: " + ronda.getJugadorActual().getNombreUsuario());
        partida.getRondas().getLast().siguienteTurno(partida);
        j3.getManoActual().mostrarFichas();
        System.out.println("------------------------------------------------------------");
        j3.ponerFichaEnMesa(mesa,j3.getManoActual().getFichasEnMano().getFirst(),partida);
        mesa.mostrarFichasJugadas();
        System.out.println("------------------------------------------------------------");
        j3.getManoActual().mostrarFichas();


        // Turno 4 (vuelve al primero)
        System.out.println("Turno de: " + ronda.getJugadorActual().getNombreUsuario());

        mesa.mostrarFichasJugadas();

    }
}
