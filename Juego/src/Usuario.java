import java.util.ArrayList;
import java.util.LinkedList;

public class Usuario {

    private String email;
    private String nombreUsuario;
    private String password;
    private Integer puntajeTotal;
    private Mano manoActual=new Mano();

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getPuntajeTotal() {
        return puntajeTotal;
    }
    public void setPuntajeTotal(Integer puntajeTotal) {
        this.puntajeTotal = puntajeTotal;
    }
    public Mano getManoActual() {
        return manoActual;
    }
    public void setManoActual(Mano manoActual) {
        this.manoActual = manoActual;
    }

    public void sacarFichaDelPozo(Pozo pozo){
        Ficha fichaSacada=pozo.getFichasEnPozo().getFirst();
        this.getManoActual().getFichasEnMano().add(fichaSacada);
    }

    public void ponerFichaEnMesa(Mesa mesa, Ficha ficha,Partida partida) {
        LinkedList<Ficha> jugadas = mesa.getFichasJugadas();

        if (jugadas.isEmpty()) {
            jugadas.add(ficha);
            System.out.println(this.getNombreUsuario() + " coloca la primera ficha: " + ficha);
        }
        else {
            Ficha primera = jugadas.getFirst();
            Ficha ultima = jugadas.getLast();
            boolean agregada = false;
        while(!agregada){
            // Agregar al inicio
            if (ficha.getLado2().equals(primera.getLado1())) {
                jugadas.addFirst(ficha);
                agregada = true;
            } else if (ficha.getLado1().equals(primera.getLado1())) {
                ficha.rotar();
                jugadas.addFirst(ficha);
                agregada = true;
            }

            // Agregar al final (si aún no se agregó)
            if (!agregada) {
                if (ficha.getLado1().equals(ultima.getLado2())) {
                    jugadas.addLast(ficha);
                    agregada = true;
                } else if (ficha.getLado2().equals(ultima.getLado2())) {
                    ficha.rotar();
                    jugadas.addLast(ficha);
                    agregada = true;
                }
            }

            if (!agregada) {
                System.out.println("No se puede agregar, valores incompatibles, debes tomar ficha del pozo");
                this.sacarFichaDelPozo(mesa.getPozo());
                break;
            }
        }
        partida.getRondas().getLast().siguienteTurno(partida);

        }
    }
}
