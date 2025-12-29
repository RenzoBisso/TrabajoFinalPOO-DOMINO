package Modelo;

import java.util.ArrayList;
import java.util.Collections;

public class Pozo {
    private ArrayList<Ficha> fichas=new ArrayList<>();

    public ArrayList<Ficha> getFichas() {
        return fichas;
    }

    public void setFichas(ArrayList<Ficha> fichas) {
        this.fichas = fichas;
    }

    public void cargarFichas(){
        for (int k=0;k<=6;k++){
            for(int j=k;j<=6;j++){
                Ficha f=new Ficha(k,j);
                this.getFichas().add(f);
            }
        }
        Collections.shuffle(this.getFichas());
    }

    public Ficha sacarFicha(int pos){
        if(pos < 0 || pos >= this.getFichas().size()){
            System.out.println("Error, posicion invalida.");
            return null;
        }else{
            Ficha f = this.getFichas().get(pos);
            this.getFichas().remove(pos);
            return f;
        }
    }
    public Ficha sacarFicha(){
        Ficha f=this.getFichas().getFirst();
        this.getFichas().removeFirst();
        return f;
    }

    public void repartirFichas(ArrayList<Jugador> jugadores) {
        for (Jugador j : jugadores) {
            for (int f = 0; f <= 6; f++) {
                Ficha ficha = sacarFicha();
                j.getMano().add(ficha);
            }
        }
    }
    public Ficha tomarFicha(int pos){
        return this.sacarFicha(pos);
    }
    public Ficha tomarFicha(){
        return this.sacarFicha();
    }

}
