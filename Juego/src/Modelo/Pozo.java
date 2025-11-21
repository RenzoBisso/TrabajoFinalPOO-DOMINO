package Modelo;

import java.util.ArrayList;
import java.util.LinkedList;

public class Pozo {

    private LinkedList<Ficha> pozo=new LinkedList<>();


    public LinkedList<Ficha> getPozo() {
        return pozo;
    }

    public void setPozo(LinkedList<Ficha> pozo) {
        this.pozo = pozo;
    }


    public Ficha devolverPrimera(){
        Ficha f=this.getPozo().getFirst();
        this.getPozo().removeFirst();
        return f;
    }
}
