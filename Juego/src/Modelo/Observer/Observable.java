package Modelo.Observer;

public interface Observable {
    void agregarObservador(Observador o);
    void sacarObservador(Observador o);
    void notificar();

}
