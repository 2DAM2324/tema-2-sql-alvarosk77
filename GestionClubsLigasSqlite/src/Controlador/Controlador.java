
package Controlador;

import Interfaz.Ventana1;
import Modelo.Club;
import Modelo.Conexion;
import Modelo.Entrenador;
import Modelo.Jugador;
import Modelo.Liga;
import Modelo.Patrocinador;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class Controlador {
    
    Conexion conexionbd;
    private ArrayList<Jugador>jugadores;
    private ArrayList<Entrenador>entrenadores;
    private ArrayList<Patrocinador>patrocinadores;
    private ArrayList<Club>clubes;
    private ArrayList<Liga>ligas;
    
    public Controlador() throws SQLException, ClassNotFoundException {

        this.jugadores = new ArrayList<>();
        this.entrenadores = new ArrayList<>();
        this.patrocinadores = new ArrayList<>();
        this.clubes = new ArrayList<>();
        this.ligas = new ArrayList<>();
        this.conexionbd = new Conexion();
        this.conexionbd.getConnection();
        this.CargarTablasBaseDatos();
    }
    
    public void configurarConexion(Conexion conexion) {
        this.conexionbd = conexion;

    }
    
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(ArrayList<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public ArrayList<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(ArrayList<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }

    public ArrayList<Patrocinador> getPatrocinadores() {
        return patrocinadores;
    }

    public void setPatrocinadores(ArrayList<Patrocinador> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }

    public ArrayList<Club> getClubes() {
        return clubes;
    }

    public void setClubes(ArrayList<Club> clubes) {
        this.clubes = clubes;
    }

    public ArrayList<Liga> getLigas() {
        return ligas;
    }
    
    public void setLigas(ArrayList<Liga> ligas) {
        this.ligas = ligas;
    }

    
    
    public void CargarTablasBaseDatos() throws SQLException, ClassNotFoundException{
        
        conexionbd.consultarJugadores(this.jugadores);

        conexionbd.consultarEntrenadores(this.entrenadores);
        
        conexionbd.consultarPatrocinadores(this.patrocinadores);
        
        conexionbd.consultarClubes(this.clubes);
        
        conexionbd.consultarLigas(this.ligas);
    }
    

    
    //Metodos CRUD Jugador
    
    public void cargarJugadoresBd() throws SQLException, ClassNotFoundException{
            conexionbd.consultarJugadores(this.jugadores);
    }
    
    public void cargarJugadoresLibresBd() throws SQLException, ClassNotFoundException{

        conexionbd.consultarJugadoresLibresBd(this.jugadores);
    }
    
    public void addJugador(String nombre, String apellido, String anio_nacimiento, String nacionalidad, String posicion, double salario) throws ClassNotFoundException, SQLException{

        Jugador jugador = new Jugador(nombre, apellido, anio_nacimiento, nacionalidad, posicion, salario);

        conexionbd.insertarJugadorBd(jugador);       
    }
    
    public void removeJugador(String id) throws ClassNotFoundException, SQLException{

        conexionbd.eliminarJugadorBd(id);
        
    }
    
    public void modificarJugador(int id, String nombre, String apellido, double salario, String anio_nacimiento, String nacionalidad, String posicion) throws ClassNotFoundException, SQLException{

        Jugador jugador = new Jugador(nombre, apellido, anio_nacimiento, nacionalidad, posicion, salario);
        
        this.conexionbd.modificarJugadorBd(jugador);
    }
    
    //Metodos CRUD Entrenador
    
    public void cargarEntrenadoresBd() throws SQLException, ClassNotFoundException{
        conexionbd.consultarEntrenadores(this.entrenadores);
    }
    
    public void cargarEntrenadoresLibresBd() throws SQLException, ClassNotFoundException{
        conexionbd.consultarEntrenadoresLibresBd(this.entrenadores);
    }
    
    public void addEntrenador(String nombre, String apellido, String anio_nacimiento, String nacionalidad) throws ClassNotFoundException, SQLException{

        Entrenador entrenador = new Entrenador(nombre, apellido, anio_nacimiento, nacionalidad);
        
        this.conexionbd.insertarEntrenadorBd(entrenador);
        
    }
    
    public void removeEntrenador(String id) throws ClassNotFoundException, SQLException{

        this.conexionbd.eliminarEntrenadorBd(id);

    }
    
    public void modificarEntrenador(String id, String nombre, String apellido, String anio_nacimiento, String nacionalidad) throws ClassNotFoundException, SQLException{
        
        Entrenador entrenador = new Entrenador(id, nombre, apellido, anio_nacimiento, nacionalidad);
        
        this.conexionbd.modificarEntrenadorBd(entrenador);
    }
    
    //Metodos CRUD Patrocinador
    
    public void cargarClubesPatrocinador(int id_patrocinador) throws SQLException, ClassNotFoundException{
        this.conexionbd.consultarClubesPatrocinador(id_patrocinador, this.clubes);
    }
    
    public void cargarPatrocinadoresLibresParaUnClub(int id_club) throws SQLException, ClassNotFoundException{
        conexionbd.consultarPatrocinadoresLibresParaUnClubBd(id_club, this.patrocinadores);
    }
    
    public void addPatrocinador(String nombre_empresa, String tipo_patrocinador, int duracion_contrato) throws ClassNotFoundException, SQLException{

        Patrocinador patrocinador = new Patrocinador(nombre_empresa, tipo_patrocinador, duracion_contrato);
        
        this.conexionbd.insertarPatrocinadorBd(patrocinador);

    }
    
    public void removePatrocinador(String id) throws ClassNotFoundException, SQLException{
        
        this.conexionbd.eliminarPatrocinadorBd(id);
        
    }
    
    public void modificarPatrocinador(String id, String nombre_empresa, String tipo_patrocinio, int duracion_contrato) throws ClassNotFoundException, SQLException{
        
        Patrocinador patrocinador = new Patrocinador(id, nombre_empresa, tipo_patrocinio, duracion_contrato);
        
        this.conexionbd.modificarPatrocinadorBd(patrocinador);
    }
    
    //Metodos CRUD Club
    
    public void cargarClubesBd() throws SQLException, ClassNotFoundException{
        conexionbd.consultarClubes(this.clubes);
    }
    
    public void cargarClubesLibres() throws SQLException, ClassNotFoundException{
        
        this.conexionbd.consultarClubesLibresBd(this.clubes);
    }
    
    public void cargarJugadoresClub(int id_club) throws SQLException, ClassNotFoundException{
        this.conexionbd.consultarJugadoresClubBd(id_club, this.jugadores);
    }
    
    public void cargarPatrocinadoresClub(int id_club) throws SQLException, ClassNotFoundException{
        this.conexionbd.consultarPatrocinadoresClubBd(id_club, this.patrocinadores);
    }
    
    public void addClub(String nombre, int anio_fundacion) throws ClassNotFoundException, SQLException{

        Club club = new Club(nombre, anio_fundacion);
        
        this.conexionbd.insertarClubBd(club);


    }
    
    public void removeClub(String id) throws ClassNotFoundException, SQLException{
        this.conexionbd.desasignarJugadoresClub(Integer.parseInt(id));
        this.conexionbd.desasignarPatrocinadoresClub(Integer.parseInt(id));
        this.conexionbd.eliminarClubBd(id);

    }
    
    public void modificarClub(String id, String nombre, int anio_fundacion) throws ClassNotFoundException, SQLException{
        
        Club club = new Club(id, nombre, anio_fundacion);
        
        this.conexionbd.modificarClubBd(club);
        
    }
    
    public void addEntrenadorClub(int id_club, int id_entrenador) throws ClassNotFoundException, SQLException{
        
        boolean contiene_entrenador = false;

        for(int i = 0; i < this.clubes.size(); i++){
            if(this.clubes.get(i).getId().equals(Integer.toString(id_club))){
                if(!this.clubes.get(i).getEntrenador().getId().equals(Integer.toString(0))){
                    contiene_entrenador = true;
                }
            }
        }
        
        if(contiene_entrenador == false){
        
            this.conexionbd.contratarEntrenadorClub(id_club, id_entrenador);
        }
        
    }
    
    public void despedirEntrenadorClub(int id_club) throws ClassNotFoundException, SQLException{

        this.conexionbd.despedirEntrenadorClub(id_club);

    }
    
    public void addJugadorClub(int id_club, int id_jugador) throws ClassNotFoundException, SQLException{

        this.conexionbd.contratarJugadorClub(id_club, id_jugador);
        
    }
    
    public void despedirJugadorClub(int id_jugador) throws ClassNotFoundException, SQLException{
        
        this.conexionbd.despedirJugadorClub(id_jugador);
    }
    
    public void addPatrocinadorClub(String id_club, String id_patrocinador) throws ClassNotFoundException, SQLException{
        
        this.conexionbd.contratarPatrocinadorClub(Integer.parseInt(id_club),Integer.parseInt(id_patrocinador));

    }
    
    public void despedirPatrocinadorClub(String id_club, String id_patrocinador) throws ClassNotFoundException, SQLException{
        
        this.conexionbd.despedirPatrocinadorClub(Integer.parseInt(id_club),Integer.parseInt(id_patrocinador));
        
    }
    
    //Metodos CRUD Liga
    
    public void cargarClubesLiga(int id_liga) throws SQLException, ClassNotFoundException{
        this.conexionbd.consultarClubesLigaBd(id_liga, this.clubes);
    }
    
    public void addLiga(String nombre, String pais, int temporada) throws ClassNotFoundException, SQLException{

        Liga liga = new Liga(nombre, pais, temporada);
        
        this.conexionbd.insertarLigaBd(liga);

    }
    
    public void removeLiga(String id) throws ClassNotFoundException, SQLException{
        
        this.conexionbd.desasignarLigaClubes(Integer.parseInt(id));
        this.conexionbd.eliminarLigaBd(id);
        
    }
    
    public void modificarLiga(String id, String nombre, String pais, int temporada) throws ClassNotFoundException, SQLException{
        
        Liga liga = new Liga(id, nombre, pais, temporada);
        
        this.conexionbd.modificarLigaBd(liga);
    }
    
    public void addClubLiga(String id_liga, String id_club) throws SQLException, ClassNotFoundException{

        this.conexionbd.aniadirClubLiga(Integer.parseInt(id_liga), Integer.parseInt(id_club));
        
    }
    
    public void eliminarClubLiga(int id_club) throws SQLException, ClassNotFoundException{
        
        this.conexionbd.eliminarClubLiga(id_club);
    }
   
    

    

    

    

    

    
    

    

    


}
