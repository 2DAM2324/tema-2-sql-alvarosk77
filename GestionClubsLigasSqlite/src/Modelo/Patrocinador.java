
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author alvar
 */
public class Patrocinador{
    private String id_patrocinador;
    private String nombre_empresa;
    private String tipo_patrocinio;
    private int duracion_contrato; //El tiempo del contrato se mide en semanas
    private ArrayList<Club>clubes = new ArrayList<>();

    public Patrocinador(String id_patrocinador, String nombre_empresa, String tipo_patrocinio, int duracion_contrato) {
        this.id_patrocinador = id_patrocinador;
        this.nombre_empresa = nombre_empresa;
        this.tipo_patrocinio = tipo_patrocinio;
        this.duracion_contrato = duracion_contrato;
    }

    public Patrocinador() {
        this.id_patrocinador = "NULL";
        this.nombre_empresa = "NULL";
        this.tipo_patrocinio = "NULL";
        this.duracion_contrato = 0;
    }

    public String getId_patrocinador() {
        return id_patrocinador;
    }

    public void setId_patrocinador(String id_patrocinador) {
        this.id_patrocinador = id_patrocinador;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getTipo_patrocinio() {
        return tipo_patrocinio;
    }

    public void setTipo_patrocinio(String tipo_patrocinio) {
        this.tipo_patrocinio = tipo_patrocinio;
    }

    public int getDuracion_contrato() {
        return duracion_contrato;
    }

    public void setDuracion_contrato(int duracion_contrato) {
        this.duracion_contrato = duracion_contrato;
    }

    public ArrayList<Club> getClubes() {
        return clubes;
    }

    public void setClubes(ArrayList<Club> clubes) {
        this.clubes = clubes;
    }

    
    
    public void mostrarDatos(){
        
        System.out.println("ID= " + this.getId_patrocinador() + " Nombre Empresa= " + this.getNombre_empresa() + " Tipo Patrocinio= " + this.getTipo_patrocinio() + " Duracion Contrato= " + this.getDuracion_contrato());
        
        System.out.println("Patrocinadores Club");
        for (Club c : this.clubes) {
            System.out.println("idClub = " + c.getId());
        }
    }
    
    
}
