import java.time.LocalDate;
import java.util.ArrayList;

public class Partida {
    private LocalDate fecha;
    private Integer ptosAConseguir;
    private Mesa mesa;
    private EstadoPartida estadoPartida;
    private ArrayList<Ronda> rondas=new ArrayList<>();
    private Usuario usuarioGanadorDePartida;

    public Partida(Integer ptosAConseguir, Mesa mesa) {
        this.fecha = LocalDate.now();
        this.ptosAConseguir = ptosAConseguir;
        this.mesa = mesa;
        this.estadoPartida=EstadoPartida.EN_CURSO;
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

    public Usuario getUsuarioGanadorDePartida() {
        return usuarioGanadorDePartida;
    }

    public void setUsuarioGanadorDePartida(Usuario usuarioGanadorDePartida) {
        this.usuarioGanadorDePartida = usuarioGanadorDePartida;
    }

    public EstadoPartida getEstadoPartida() {
        return estadoPartida;
    }

    public void setEstadoPartida(EstadoPartida estadoPartida) {
        this.estadoPartida = estadoPartida;
    }

    public ArrayList<Ronda> getRondas() {
        return rondas;
    }

    public void setRondas(ArrayList<Ronda> rondas) {
        this.rondas = rondas;
    }

    public void verificarGanadorPartida(){
        for (Usuario x:this.getMesa().getJugadores()){
            if(x.getPuntajeTotal().equals(this.ptosAConseguir)){
                this.setUsuarioGanadorDePartida(x);
                break;
            }
        }
    }


}
