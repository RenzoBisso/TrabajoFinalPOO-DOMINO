import java.util.ArrayList;
import java.util.Collections;

public class Mesa {
    private Pozo pozo;
    private ArrayList<Ficha> fichasJugadas=new ArrayList<>();
    private ArrayList<Usuario>jugadores;

    public Mesa(ArrayList<Usuario> jugadores) {
        this.jugadores=jugadores;
        this.cargarFichas();
        this.repartirFichas();
    }

    public Pozo getPozo() {
        return pozo;
    }

    public void setPozo(Pozo pozo) {
        this.pozo = pozo;
    }

    public ArrayList<Ficha> getFichasJugadas() {
        return fichasJugadas;
    }

    public void setFichasJugadas(ArrayList<Ficha> fichasJugadas) {
        this.fichasJugadas = fichasJugadas;
    }

    public ArrayList<Usuario> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Usuario> jugadores) {
        this.jugadores = jugadores;
    }


    private void cargarFichas(){
        while(this.getPozo().getFichasEnPozo().size()<=28){
            for(int k=0;k<6;k++){
                for(int j=0;j<6;j++){
                    Ficha ficha=new Ficha(k,j);
                    this.getPozo().getFichasEnPozo().add(ficha);
                }
            }
        }
        //Mezcla las fichas en el pozo
        Collections.shuffle(this.getPozo().getFichasEnPozo());
    }

    public void repartirFichas(){
        for(Usuario x:this.getJugadores()){
            Mano mano=new Mano();
            for(int k=0;k<7;k++){
                Ficha ficha=this.getPozo().getFichasEnPozo().getFirst();
                mano.getFichasEnMano().add(ficha);
            }
            x.setManoActual(mano);
        }
    }


}
