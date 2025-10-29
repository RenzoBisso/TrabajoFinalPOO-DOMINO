import java.util.ArrayList;

public class Ronda {
    private static int nroRondaGlobal=1;
    private int nroRonda;
    private Integer ptosDeLaRonda;
    private Usuario jugadorMano;
    private Usuario jugadorActual;
    private Usuario ganadorRonda;
    private Integer turnoActual=0;


    public Ronda(Usuario jugadorMano) {
        this.nroRonda = nroRondaGlobal;
        nroRondaGlobal++;
        this.jugadorMano = jugadorMano;
    }



    public static int getNroRondaGlobal() {
        return nroRondaGlobal;
    }

    public static void setNroRondaGlobal(int nroRondaGlobal) {
        Ronda.nroRondaGlobal = nroRondaGlobal;
    }

    public int getNroRonda() {
        return nroRonda;
    }

    public void setNroRonda(int nroRonda) {
        this.nroRonda = nroRonda;
    }

    public Integer getPtosDeLaRonda() {
        return ptosDeLaRonda;
    }

    public void setPtosDeLaRonda(Integer ptosDeLaRonda) {
        this.ptosDeLaRonda = ptosDeLaRonda;
    }

    public Usuario getJugadorMano() {
        return jugadorMano;
    }

    public void setJugadorMano(Usuario jugadorMano) {
        this.jugadorMano = jugadorMano;
    }

    public Usuario getJugadorActual() {
        return jugadorActual;
    }

    public void setJugadorActual(Usuario jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public Usuario getGanadorRonda() {
        return ganadorRonda;
    }

    public void setGanadorRonda(Usuario ganadorRonda) {
        this.ganadorRonda = ganadorRonda;
    }

    public Integer getTurnoActual() {
        return turnoActual;
    }

    public void setTurnoActual(Integer turnoActual) {
        this.turnoActual = turnoActual;
    }

    public void calcularPuntosEnRonda(Partida partida, Usuario usuario){
        ArrayList<Usuario> perdedores = new ArrayList<>(partida.getMesa().getJugadores());
        perdedores.remove(usuario);
        Integer count=0;
        for (Usuario x:perdedores){
            count+=x.getManoActual().ptsEnMano();
        }
        Integer aux=usuario.getPuntajeTotal();
        usuario.setPuntajeTotal(aux+count);
        this.setPtosDeLaRonda(count);
    }



    public Usuario nuevaMano(Partida partida) {
        ArrayList<Usuario> jugadores = partida.getMesa().getJugadores();
        int posActual = jugadores.indexOf(this.jugadorMano);
        int posSiguiente = (posActual + 1) % jugadores.size();
        return jugadores.get(posSiguiente);
    }

    public Ronda nuevaRonda(Partida partida) {
        Usuario nuevoMano = this.nuevaMano(partida);
        Ronda ronda = new Ronda(nuevoMano);
        partida.getRondas().add(ronda);
        return ronda;
    }

    public void verificarGanadorRonda(Partida partida) {
        for (Usuario jugador : partida.getMesa().getJugadores()) {
            if (jugador.getManoActual().getFichasEnMano().isEmpty()) {
                this.ganadorRonda = jugador;
                calcularPuntosEnRonda(partida, jugador);
                partida.verificarGanadorPartida();
                break;
            }
        }
    }
    public void siguienteTurno(Partida partida) {
        ArrayList<Usuario> jugadores = partida.getMesa().getJugadores();
        int cantidadJugadores = jugadores.size();

        turnoActual = (turnoActual + 1) % cantidadJugadores;
        jugadorActual = jugadores.get(turnoActual);
    }
}

