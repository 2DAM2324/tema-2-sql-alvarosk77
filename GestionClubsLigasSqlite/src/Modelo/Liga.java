
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author alvar
 */
public class Liga implements Serializable{
    private String id;
    private String nombre;
    private String pais;
    private int temporada;
    private ArrayList<Club>clubes = new ArrayList<>();

    public Liga(String id, String nombre, String pais, int temporada) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.temporada = temporada;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }



    public ArrayList<Club> getClubes() {
        return clubes;
    }

    public void setClubes(ArrayList<Club> clubes) {
        this.clubes = clubes;
    }


    public void mostrarDatos(){
        
        System.out.println("ID= " + this.getId() + " Nombre = " + this.getNombre() + " Pais = " + this.getPais() + " Temporada = " + this.getTemporada());
    }
    
}
