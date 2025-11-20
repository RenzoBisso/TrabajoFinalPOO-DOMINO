package Modelo;

public class Ficha {
    private Integer lado1;
    private Integer lado2;
    private final boolean doble;
    private Integer valorTotal=0;

    public Ficha(Integer lado1,Integer lado2) {
        if(lado1.equals(lado2)){
            this.doble = true;
        }else{
            this.doble = false;
        }
        this.lado1=lado1;
        this.lado2=lado2;
        this.valorTotal=this.lado1+this.lado2;
    }

    public Integer getLado1() {
        return lado1;
    }

    public Integer getLado2() {
        return lado2;
    }

    public boolean isDoble() {
        return doble;
    }

    public Integer getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void rotarFicha(){
        Integer aux=this.lado1;
        this.lado1=this.lado2;
        this.lado2=aux;
    }

    @Override
    public String toString() {
        int count=0;
        if (this.isDoble()) {
            return String.format("""
╔═══╗
║ %d ║
╠═══╣
║ %d ║
╚═══╝""",this.lado1, this.lado2);
        } else {
            return String.format("""
╔═══╦═══╗
║ %d ║ %d ║
╚═══╩═══╝""",this.lado1, this.lado2);
        }
    }

}
