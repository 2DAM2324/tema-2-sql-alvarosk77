
package Modelo;

import java.util.ArrayList;

/**
 * 
 * @author alvar
 */
public class ConexionMock extends Conexion{
    private ArrayList<Jugador> jugadoresBd;
    private ArrayList<Entrenador> entrenadoresBd;

    public ConexionMock(){
        
        jugadoresBd = new ArrayList<>();
        entrenadoresBd = new ArrayList<>();
    }

    public ArrayList<Jugador> getJugadoresBd() {
        return jugadoresBd;
    }

    public void setJugadoresBd(ArrayList<Jugador> jugadoresBd) {
        this.jugadoresBd = jugadoresBd;
    }

    public ArrayList<Entrenador> getEntrenadoresBd() {
        return entrenadoresBd;
    }

    public void setEntrenadoresBd(ArrayList<Entrenador> entrenadoresBd) {
        this.entrenadoresBd = entrenadoresBd;
    }
    
    //Simulacion metodos CRUD para la base de datos simulada
    
    
    //Metodos CRUD Jugador
    @Override
    public void insertarJugadorBd(Jugador jugador){
         
        jugadoresBd.add(jugador);
        
    }    
    
    /*@Override
    public void modificarJugadorBd(Jugador jugador){
        for(int i = 0; i < jugadoresBd.size(); i++){
            
        }
    }*/
    
    @Override
    public void eliminarJugadorBd(String id){
        for (int i = 0; i < jugadoresBd.size(); i++) {
            if(jugadoresBd.get(i).getId() == Integer.parseInt(id)){
                jugadoresBd.remove(i);
            }
        }
    }
    
    //Metodos CRUD Entrenador
    @Override
     public void insertarEntrenadorBd(Entrenador entrenador){
        
        this.entrenadoresBd.add(entrenador);
    }
     
    @Override
    public void eliminarEntrenadorBd(String id){
        for (int i = 0; i < entrenadoresBd.size(); i++) {
            if(entrenadoresBd.get(i).getId().equals(id)){
                entrenadoresBd.remove(i);
            }
        }
    }
}
