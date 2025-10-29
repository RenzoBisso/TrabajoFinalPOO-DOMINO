public class Ficha {

    private Integer lado1;
    private Integer lado2;
    private boolean doble;

    public Ficha(Integer lado1, Integer lado2) {
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.doble = this.lado1.equals(this.lado2);
    }

    public Integer getLado1() {
        return lado1;
    }

    public void setLado1(Integer lado1) {
        this.lado1 = lado1;
    }

    public Integer getLado2() {
        return lado2;
    }

    public void setLado2(Integer lado2) {
        this.lado2 = lado2;
    }

    public boolean isDoble() {
        return doble;
    }

    public void setDoble(boolean doble) {
        this.doble = doble;
    }

    public Integer valorFicha(){
        return this.lado1+this.lado2;
    }

    public void rotar(){
        Integer aux=this.getLado1();
        this.setLado1(this.getLado2());
        this.setLado2(aux);
    }

    @Override
    public String toString() {
        if(this.isDoble()){
            return this.lado1+"\n_"+this.lado2;
        }else{
            return this.lado1 + "|" + this.lado2;
        }
    }
}
