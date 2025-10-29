import java.util.ArrayList;

public class Mano {
    private ArrayList<Ficha> fichasEnMano;

    public ArrayList<Ficha> getFichasEnMano() {
        return fichasEnMano;
    }
    public void setFichasEnMano(ArrayList<Ficha> fichasEnMano) {
        this.fichasEnMano = fichasEnMano;
    }
    public Integer ptsEnMano(){
        Integer count=0;
        for (Ficha f:this.fichasEnMano){
            count+=f.valorFicha();
        }
        return count;
    }

}
