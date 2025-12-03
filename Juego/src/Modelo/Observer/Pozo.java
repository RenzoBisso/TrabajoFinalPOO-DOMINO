package Modelo.Observer;

import java.util.ArrayList;
import java.util.Collections;

public class Pozo {
    private ArrayList<Ficha> fichas;

    public Pozo(ArrayList<Ficha> fichas) {
        this.fichas = fichas;
    }

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
            }
        }
        Collections.shuffle(this.getFichas());
    }

    public Ficha sacarFicha(int pos){
        if(pos<0 || pos>this.getFichas().size()){
            System.out.println("Error, posicion invalida.");
            return null;
        }else{
            return this.getFichas().get(pos);
        }
    }
    public Ficha sacarFicha(){
        Ficha f=this.getFichas().getFirst();
        this.getFichas().removeFirst();
        return f;

    }
}
