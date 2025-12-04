package Modelo;

public class Ficha {
    private Integer ladoA;
    private Integer ladoB;
    private boolean doble;
    private Integer valor;

    public Ficha(Integer ladoA, Integer ladoB) {
        this.ladoA = ladoA;
        this.ladoB = ladoB;
        if(ladoA.equals(ladoB)){
            this.doble=true;
        }else{
            this.doble=false;
        }
        this.valor = ladoA+ladoB;
    }

    public Integer getLadoA() {
        return ladoA;
    }

    public void setLadoA(Integer ladoA) {
        this.ladoA = ladoA;
    }

    public Integer getLadoB() {
        return ladoB;
    }

    public void setLadoB(Integer ladoB) {
        this.ladoB = ladoB;
    }

    public boolean isDoble() {
        return doble;
    }

    public void setDoble(boolean doble) {
        this.doble = doble;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public void rotar(){
        Integer aux=this.getLadoA();
        this.setLadoA(this.getLadoB());
        this.setLadoB(aux);
    }


}
