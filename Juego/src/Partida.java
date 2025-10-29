import java.time.LocalDate;
import java.util.ArrayList;

public class Partida {
    private static int nroPartidaGlobal=1;
    private int nroPartidaActual;
    private LocalDate fecha;
    private Integer ptosAConseguir;
    private Mesa mesa;
    private EstadoPartida estadoPartida;
    private ArrayList<Ronda> rondas=new ArrayList<>();

    public Partida(Integer ptosAConseguir, Mesa mesa) {

        this.nroPartidaActual = nroPartidaGlobal;
        nroPartidaGlobal++;
        this.fecha = LocalDate.now();
        this.ptosAConseguir = ptosAConseguir;
        this.mesa = mesa;
        this.estadoPartida=EstadoPartida.EN_CURSO;
    }

    public static int getNroPartidaGlobal() {
        return nroPartidaGlobal;
    }

    public static void setNroPartidaGlobal(int nroPartidaGlobal) {
        Partida.nroPartidaGlobal = nroPartidaGlobal;
    }

    public int getNroPartidaActual() {
        return nroPartidaActual;
    }

    public void setNroPartidaActual(int nroPartidaActual) {
        this.nroPartidaActual = nroPartidaActual;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getPtosAConseguir() {
        return ptosAConseguir;
    }

    public void setPtosAConseguir(Integer ptosAConseguir) {
        this.ptosAConseguir = ptosAConseguir;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

}
