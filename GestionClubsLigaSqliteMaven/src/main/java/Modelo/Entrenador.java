
package Modelo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author alvar
 */
public class Entrenador {
    private String id;
    private String nombre;
    private String apellido;
    private String anio_nacimiento;
    private String nacionalidad;

    public Entrenador(String id, String nombre, String apellido, String anio_nacimiento, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.anio_nacimiento = anio_nacimiento;
        this.nacionalidad = nacionalidad;
    }
    
    public Entrenador(String nombre, String apellido, String anio_nacimiento, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.anio_nacimiento = anio_nacimiento;
        this.nacionalidad = nacionalidad;
    }
    
    public Entrenador(String id) {
        this.id = id;
        this.nombre = "NULL";
        this.apellido = "NULL";
        this.anio_nacimiento = "NULL";
        this.nacionalidad = "NULL";
    }
    
    public Entrenador() {
        this.id = "NULL";
        this.nombre = "NULL";
        this.apellido = "NULL";
        this.anio_nacimiento = "NULL";
        this.nacionalidad = "NULL";
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAnio_nacimiento() {
        return anio_nacimiento;
    }

    public void setAnio_nacimiento(String anio_nacimiento) {
        this.anio_nacimiento = anio_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }


    public void mostrarDatosEntrenador(){
        
        System.out.println("ID= " + this.getId() + " Nombre= " + this.getNombre() + " Apellido= " + this.getApellido() + " Año Nacimiento= " + this.getAnio_nacimiento() + " Nacionalidad= " + this.getNacionalidad());
        
    }
    
}
