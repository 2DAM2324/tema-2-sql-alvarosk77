
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author alvar
 */
public class Club{
    private String id;
    private String nombre;
    private int anio_fundacion;
    private ArrayList<Jugador>jugadores = new ArrayList<>();
    private Entrenador entrenador = new Entrenador();
    private ArrayList<Patrocinador>patrocinadores = new ArrayList<>();

    public Club(String id, String nombre, int anio_fundacion, Entrenador entrenador) {
        this.id = id;
        this.nombre = nombre;
        this.anio_fundacion = anio_fundacion;
        this.entrenador = entrenador;
    }
    
    public Club() {
        this.id = "NULL";
        this.nombre = "NULL";
        this.anio_fundacion = 0;
    }

    public Club(String id, String nombre, int anio_fundacion) {
        this.id = id;
        this.nombre = nombre;
        this.anio_fundacion = anio_fundacion;
    }

    public Club(String id, String nombre, int anio_fundacion, ArrayList<Jugador> jugadores, Entrenador entrenador) {
        this.id = id;
        this.nombre = nombre;
        this.anio_fundacion = anio_fundacion;
        this.jugadores = jugadores;
        this.entrenador = entrenador;
    }


    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAnio_fundacion() {
        return anio_fundacion;
    }

    public void setAnio_fundacion(int anio_fundacion) {
        this.anio_fundacion = anio_fundacion;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }
    
    public int obtenerSizeArrayJugadores(){
        
        return this.jugadores.size();
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }
    


    public ArrayList<Patrocinador> getPatrocinadores() {
        return patrocinadores;
    }

    public int obtenerSizeArrayPatrocinadores(){
        
        return this.patrocinadores.size();
    }
    
    public void setPatrocinadores(ArrayList<Patrocinador> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }
    
    

    public void mostrarDatos(){
        
        System.out.println("ID= " + this.getId() + " Nombre= " + this.getNombre() + " Año Fundacion= " + this.getAnio_fundacion() + " Id_entrenador= " + this.entrenador.getId());
        
        System.out.println("Jugadores Club");
        for (Jugador jugadore : this.jugadores) {
            System.out.println("idJugador= " + jugadore.getId());
        }
        
        System.out.println("Patrocinadores Club");
        for (Patrocinador p : this.patrocinadores) {
            System.out.println("idPatrocinador= " + p.getId_patrocinador());
        }
        
    }
    
    
    
}
