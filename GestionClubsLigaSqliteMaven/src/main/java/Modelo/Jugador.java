
package Modelo;

import java.io.Serializable;



/**
 *
 * @author alvar
 */
public class Jugador {
    
    private int id;
    private String nombre;
    private String apellido;
    private String anio_nacimiento;
    private String nacionalidad;
    private String posicion;
    private double salario;
    private int id_club;

    public Jugador(int id, String nombre, String apellido, String anio_nacimiento, String nacionalidad, String posicion, double salario, int id_club) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.anio_nacimiento = anio_nacimiento;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
        this.salario = salario;
        this.id_club = id_club;
    }


    
    public Jugador(String nombre, String apellido, String anio_nacimiento, String nacionalidad, String posicion, double salario, int id_club) {
        
        this.nombre = nombre;
        this.apellido = apellido;
        this.anio_nacimiento = anio_nacimiento;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
        this.salario = salario;
        this.id_club = id_club;
    }
    
    public Jugador(String nombre, String apellido, String anio_nacimiento, String nacionalidad, String posicion, double salario) {
        
        this.nombre = nombre;
        this.apellido = apellido;
        this.anio_nacimiento = anio_nacimiento;
        this.nacionalidad = nacionalidad;
        this.posicion = posicion;
        this.salario = salario;
        this.id_club = id_club;
    }
    
 
    public Jugador() {
        
        this.id = 0;
        this.nombre = "NULL";
        this.apellido = "NULL";
        this.anio_nacimiento = "NULL";
        this.nacionalidad = "NULL";
        this.posicion = "NULL";
        this.salario = 0.0;
        this.id_club = 0;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getanio_nacimiento() {
        return anio_nacimiento;
    }

    public void setanio_nacimiento(String anio_nacimiento) {
        this.anio_nacimiento = anio_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getId_club() {
        return id_club;
    }

    public void setId_club(int id_club) {
        this.id_club = id_club;
    }
    

    @Override
    public String toString() {
        return "Jugador{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", anio_nacimiento=" + anio_nacimiento + ", nacionalidad=" + nacionalidad + ", posicion=" + posicion + ", salario=" + salario + '}';
    }
    
    public void mostrarDatos(){
        
        System.out.println("ID= " + this.getId() + " Nombre= " + this.getNombre() + " Apellido= " + this.getApellido() + " Año Nacimiento= " + this.getanio_nacimiento() + " Nacionalidad= " + this.getNacionalidad() + " Posicion= " + this.getPosicion() + " Salario= " + this.getSalario());
        
    }
    
 
    
}
