
package Modelo;

import java.util.ArrayList;

/**
 * 
 * @author alvar
 */
public class ConexionMock extends Conexion{
    private ArrayList<Jugador> jugadoresBd;

    public ConexionMock(){
        
        jugadoresBd = new ArrayList<>();
    }

    public ArrayList<Jugador> getJugadoresBd() {
        return jugadoresBd;
    }

    public void setJugadoresBd(ArrayList<Jugador> jugadoresBd) {
        this.jugadoresBd = jugadoresBd;
    }
    
}
