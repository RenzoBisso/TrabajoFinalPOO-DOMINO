import Controlador.ControladorConsola;
import Modelo.App;
import Modelo.Jugador;
import Modelo.Partida;
import Modelo.Ronda;
import Vista.VistaConsola;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        Jugador j1=new Jugador();
        j1.setNombre("Renzo");

        Jugador j2=new Jugador();
        j2.setNombre("Ivan");

        LinkedList<Jugador> jugadores=new LinkedList<>();
        jugadores.add(j1);
        jugadores.add(j2);


        Ronda r=new Ronda(jugadores);
        Partida p=new Partida(100,jugadores);
        App app=new App(r,p);
        VistaConsola vc=new VistaConsola();
        ControladorConsola cc=new ControladorConsola(app,vc);
        cc.iniciarRonda();



    }
}
