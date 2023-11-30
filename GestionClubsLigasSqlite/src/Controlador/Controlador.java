
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
    
    private boolean id_jugador_encontrada = false;
    private int numero_veces_id_jugador_repetida = 0;
    
    private boolean id_entrenador_encontrada = false;
    private int numero_veces_id_entrenador_repetida = 0;
    
    private boolean id_patrocinador_encontrada = false;
    private boolean id_patrocinador_en_club_encontrada = false;
    
    private boolean id_club_encontrado = false;
    private int numero_veces_id_club_repetida = 0;
    
    public Controlador() throws SQLException {

        this.jugadores = new ArrayList<>();
        this.entrenadores = new ArrayList<>();
        this.patrocinadores = new ArrayList<>();
        this.clubes = new ArrayList<>();
        this.ligas = new ArrayList<>();
        this.conexionbd = new Conexion();
        this.conexionbd.getConnection();
        this.CargarTablasBaseDatos();
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
    
    public Patrocinador getPatrocinadorById(String id_patrocinador) {
        
        int posicion = 0;
        
        for (int i = 0; i < this.patrocinadores.size(); i++) {
           
            if(id_patrocinador.equals(this.patrocinadores.get(i).getId_patrocinador())){
                posicion = i;
            }
        }
        
        return this.patrocinadores.get(posicion);
    }

    public void setPatrocinadores(ArrayList<Patrocinador> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }

    public ArrayList<Club> getClubes() {
        return clubes;
    }
    
    public Club getClubById(String id_club) {
        
        int posicion = 0;
        
        for (int i = 0; i < this.clubes.size(); i++) {
           
            if(id_club.equals(this.clubes.get(i).getId())){
                posicion = i;
            }
        }
        
        return this.clubes.get(posicion);
    }

    public void setClubes(ArrayList<Club> clubes) {
        this.clubes = clubes;
    }

    public ArrayList<Liga> getLigas() {
        return ligas;
    }

    public Liga getLigaById(String id_liga) {
        
        int posicion = 0;
        
        for (int i = 0; i < this.ligas.size(); i++) {
           
            if(id_liga.equals(this.ligas.get(i).getId())){
                posicion = i;
            }
        }
        
        return this.ligas.get(posicion);
    }
    
    public void setLigas(ArrayList<Liga> ligas) {
        this.ligas = ligas;
    }

    
    public void CargarTablasBaseDatos() throws SQLException{
        
        conexionbd.consultarJugadores(this.jugadores);

        conexionbd.consultarEntrenadores(this.entrenadores);
        
        conexionbd.consultarPatrocinadores(this.patrocinadores);
        
        conexionbd.consultarClubes(this.clubes);
        
        conexionbd.consultarLigas(this.ligas);
    }
    
    public void cargarJugadoresBd(){
        try {
            conexionbd.consultarJugadores(this.jugadores);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void cargarEntrenadoresBd(){
        try {
            conexionbd.consultarEntrenadores(this.entrenadores);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void cargarClubesBd(){
        try {
            conexionbd.consultarClubes(this.clubes);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void cargarEntrenadoresLibresBd(){
        try {
            conexionbd.consultarEntrenadoresLibresBd(this.entrenadores);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void cargarJugadoresLibresBd(){
        try {
            conexionbd.consultarJugadoresLibresBd(this.jugadores);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void cargarPatrocinadoresLibresParaUnClub(int id_club){
        try {
            conexionbd.consultarPatrocinadoresLibresParaUnClubBd(id_club, this.patrocinadores);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void addJugador(String nombre, String apellido, String anio_nacimiento, String nacionalidad, String posicion, double salario){

        Jugador jugador = new Jugador(nombre, apellido, anio_nacimiento, nacionalidad, posicion, salario);

        conexionbd.insertarJugadorBd(jugador);       
    }
    
    public void removeJugador(String id){

        conexionbd.eliminarJugadorBd(id);
        
    }
    
    public void modificarJugador(int id, String nombre, String apellido, double salario, String anio_nacimiento, String nacionalidad, String posicion){

        Jugador jugador = new Jugador(nombre, apellido, anio_nacimiento, nacionalidad, posicion, salario);
        
        this.conexionbd.modificarJugadorBd(jugador);
    }
    
    public void addEntrenador(String nombre, String apellido, String anio_nacimiento, String nacionalidad){

        Entrenador entrenador = new Entrenador(nombre, apellido, anio_nacimiento, nacionalidad);
        
        this.conexionbd.insertarEntrenadorBd(entrenador);
        
    }
    
    public void removeEntrenador(String id){

        this.conexionbd.eliminarEntrenadorBd(id);

    }
    
    public void modificarEntrenador(String id, String nombre, String apellido, String anio_nacimiento, String nacionalidad){
        
        Entrenador entrenador = new Entrenador(id, nombre, apellido, anio_nacimiento, nacionalidad);
        
        this.conexionbd.modificarEntrenadorBd(entrenador);
    }
    
    public void addPatrocinador(String nombre_empresa, String tipo_patrocinador, int duracion_contrato){

        Patrocinador patrocinador = new Patrocinador(nombre_empresa, tipo_patrocinador, duracion_contrato);
        
        this.conexionbd.insertarPatrocinadorBd(patrocinador);

    }
    
    public void removePatrocinador(String id){
        
        this.conexionbd.eliminarPatrocinadorBd(id);
        
    }
    
    public void modificarPatrocinador(String id, String nombre_empresa, String tipo_patrocinio, int duracion_contrato){
        
        Patrocinador patrocinador = new Patrocinador(id, nombre_empresa, tipo_patrocinio, duracion_contrato);
        
        this.conexionbd.modificarPatrocinadorBd(patrocinador);
    }
    
    public void addClub(String nombre, int anio_fundacion){

        Club club = new Club(nombre, anio_fundacion);
        
        this.conexionbd.insertarClubBd(club);


    }
    
    public void removeClub(String id){
        this.conexionbd.desasignarJugadoresClub(Integer.parseInt(id));
        this.conexionbd.eliminarClubBd(id);

    }
    
    public void modificarClub(String id, String nombre, int anio_fundacion){
        
        Club club = new Club(id, nombre, anio_fundacion);
        
        this.conexionbd.modificarClubBd(club);
        
    }
    
    
    public void addLiga(String id, String nombre, String pais, int temporada){

        boolean id_encontrada = false;

        //this.deserializarLiga();

        for (int i = 0; (i < this.ligas.size()) && (this.ligas.get(i).getId()!= id); i++) {
            
            if(this.ligas.get(i).getId().equals(id)){

                id_encontrada = true;
            }
        }

        if(id_encontrada == false){
            
            Liga liga = new Liga(id, nombre, pais, temporada);

            if(this.ligas != null){

                this.ligas.add(liga);

                //this.serializarLiga();

            }
            else{

                System.out.println("ERROR");
            }

        }
        else{

            System.out.println("ID REPETIDA");
        }

    }
    
    public void removeLiga(String id){
        
        for (int i = 0; i < this.ligas.size(); i++) {
            
            if(id.equals(this.ligas.get(i).getId())){
                
                this.ligas.remove(i);
            }
        }
        
        //this.serializarLiga();
    }
    
    public void modificarLiga(String id, String nombre, String pais, int temporada){
        
        
        for (int i = 0; i < this.ligas.size(); i++) {
            
            if(id.equals(this.ligas.get(i).getId())){
                
                this.ligas.get(i).setNombre(nombre);
                this.ligas.get(i).setPais(pais);
                this.ligas.get(i).setTemporada(temporada);
            }
        }
        
        //this.serializarLiga();
    }
    
    public void comprobarIdClub(String id){
        for (int i = 0; i < this.clubes.size(); i++) {
            if(id.equals(this.clubes.get(i).getId())){
                id_club_encontrado = true;
            }
        }
        
        
        numero_veces_id_club_repetida = 0;
        for(int i = 0; i < this.ligas.size(); i++){
            for(int j = 0; j < this.ligas.get(i).getClubes().size(); j++){
                if(id.equals(this.ligas.get(i).getClubes().get(j).getId())){
                    numero_veces_id_club_repetida++; 
                }
            }

        }
    }
    
    public void addClubLiga(String id_liga, String id_club){

        this.comprobarIdClub(id_club);
        
        if(id_club_encontrado == true && numero_veces_id_club_repetida < 1){
            for(int i = 0; i < this.ligas.size(); i++){
                if(id_liga.equals(this.ligas.get(i).getId())){
                    for (int j = 0; j < this.clubes.size(); j++) {
                        if(id_club.equals(this.clubes.get(j).getId())){
                            this.ligas.get(i).getClubes().add(this.clubes.get(j));
                        }
                    }
                }
            }
            //this.serializarLiga();
        }
    }
   
    
    public void addEntrenadorClub(int id_club, int id_entrenador){
        
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
    
    public void despedirEntrenadorClub(int id_club){

        this.conexionbd.despedirEntrenadorClub(id_club);

    }
    
    public void cargarJugadoresClub(int id_club) throws SQLException{
        this.conexionbd.consultarJugadoresClubBd(id_club, this.jugadores);
    }
    
    public void addJugadorClub(int id_club, int id_jugador){

        this.conexionbd.contratarJugadorClub(id_club, id_jugador);
        
    }
    
    public void despedirJugadorClub(int id_jugador){
        
        this.conexionbd.despedirJugadorClub(id_jugador);
    }
    
    public void cargarPatrocinadoresClub(int id_club) throws SQLException{
        this.conexionbd.consultarPatrocinadoresClubBd(id_club, this.patrocinadores);
    }
    
    public void comprobarIdPatrocinadorEnClub(String id_club, String id_patrocinador){
        
        for (int i = 0; i < this.clubes.size(); i++) {
            if(id_club.equals(this.clubes.get(i).getId())){
                if(this.clubes.get(i).getPatrocinadores() != null){
                    for(int j = 0; j < this.clubes.get(i).getPatrocinadores().size(); j++){
                        if(id_patrocinador.equals(this.clubes.get(i).getPatrocinadores().get(j).getId_patrocinador())){
                            id_patrocinador_en_club_encontrada = true;
                        }
                    }
                }
                
            }
        }
    }
    
    public void addPatrocinadorClub(String id_club, String id_patrocinador){
        
        this.conexionbd.contratarPatrocinadorClub(Integer.parseInt(id_club),Integer.parseInt(id_patrocinador));

    }
    
    public void despedirPatrocinadorClub(String id_club, String id_patrocinador){
        
        for (int i = 0; i < this.clubes.size(); i++) {
            if(id_club.equals(this.clubes.get(i).getId())){
                for(int j = 0; j < this.clubes.get(i).getPatrocinadores().size(); j++){
                    if(id_patrocinador.equals(this.clubes.get(i).getPatrocinadores().get(j).getId_patrocinador())){
                        this.clubes.get(i).getPatrocinadores().remove(j); 
                    }
                }
            }
        }
        
        //this.serializarClub();
        
        for(int i = 0; i < this.patrocinadores.size(); i++){
            if(id_patrocinador.equals(this.patrocinadores.get(i).getId_patrocinador())){
                for(int j = 0; j < this.patrocinadores.get(i).getClubes().size(); j++){
                    if(id_club.equals(this.patrocinadores.get(i).getClubes().get(j).getId())){
                            this.patrocinadores.get(i).getClubes().remove(j);
                    }
                }
            }
        }
        //this.serializarPatrocinador();
    }

}
