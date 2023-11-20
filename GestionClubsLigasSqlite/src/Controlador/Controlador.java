
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
        //this.deserializarEntidades();
        this.conexionbd = new Conexion();
        this.jugadores = new ArrayList<>();
        this.entrenadores = new ArrayList<>();
        this.patrocinadores = new ArrayList<>();
        this.clubes = new ArrayList<>();
        this.ligas = new ArrayList<>();
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
    
    public void serializarEntrenador(){

        ObjectOutputStream serializador = null;
        try {
            serializador = new ObjectOutputStream(new FileOutputStream("entrenadores.dat"));
            serializador.writeObject(this.entrenadores);
        } catch (IOException ioe) {

        } finally {
        if (serializador != null)
        try {
            serializador.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            }
        }

    }
    
    public void deserializarEntrenador(){
        
        ObjectInputStream deserializador = null;
        try {
            deserializador = new ObjectInputStream(new FileInputStream("entrenadores.dat"));
            this.entrenadores = (ArrayList<Entrenador>)deserializador.readObject();
        } catch (FileNotFoundException fnfe ) {
            fnfe.printStackTrace();
        } catch (ClassNotFoundException cnfe ) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
        if (deserializador != null)
            try {
                deserializador.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
    
    public void serializarJugador(){

        ObjectOutputStream serializador = null;
        try {
            serializador = new ObjectOutputStream(new FileOutputStream("jugadores.dat"));
            serializador.writeObject(this.jugadores);
        } catch (IOException ioe) {

        } finally {
        if (serializador != null)
        try {
            serializador.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            }
        }

    }
    
    public void serializarPatrocinador(){

        ObjectOutputStream serializador = null;
        try {
            serializador = new ObjectOutputStream(new FileOutputStream("patrocinadores.dat"));
            serializador.writeObject(this.patrocinadores);
        } catch (IOException ioe) {

        } finally {
        if (serializador != null)
        try {
            serializador.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            }
        }

    }
    
    public void deserializarPatrocinador(){
        
        ObjectInputStream deserializador = null;
        try {
            deserializador = new ObjectInputStream(new FileInputStream("patrocinadores.dat"));
            this.patrocinadores = (ArrayList<Patrocinador>)deserializador.readObject();
        } catch (FileNotFoundException fnfe ) {
            fnfe.printStackTrace();
        } catch (ClassNotFoundException cnfe ) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
        if (deserializador != null)
            try {
                deserializador.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
    
    public void serializarClub(){

        ObjectOutputStream serializador = null;
        try {
            serializador = new ObjectOutputStream(new FileOutputStream("clubes.dat"));
            serializador.writeObject(this.clubes);
        } catch (IOException ioe) {

        } finally {
        if (serializador != null)
        try {
            serializador.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            }
        }

    }
    
    public void deserializarClub(){
        
        ObjectInputStream deserializador = null;
        try {
            deserializador = new ObjectInputStream(new FileInputStream("clubes.dat"));
            this.clubes = (ArrayList<Club>)deserializador.readObject();
        } catch (FileNotFoundException fnfe ) {
            fnfe.printStackTrace();
        } catch (ClassNotFoundException cnfe ) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
        if (deserializador != null)
            try {
                deserializador.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
    
    public void serializarLiga(){

        ObjectOutputStream serializador = null;
        try {
            serializador = new ObjectOutputStream(new FileOutputStream("ligas.dat"));
            serializador.writeObject(this.ligas);
        } catch (IOException ioe) {

        } finally {
        if (serializador != null)
        try {
            serializador.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            }
        }

    }
    
    public void deserializarLiga(){
        
        ObjectInputStream deserializador = null;
        try {
            deserializador = new ObjectInputStream(new FileInputStream("ligas.dat"));
            this.ligas = (ArrayList<Liga>)deserializador.readObject();
        } catch (FileNotFoundException fnfe ) {
            fnfe.printStackTrace();
        } catch (ClassNotFoundException cnfe ) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
        if (deserializador != null)
            try {
                deserializador.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
    
    public void CargarTablasBaseDatos() throws SQLException{
        
        conexionbd.consultarJugadores(this.jugadores);

        conexionbd.consultarEntrenadores(this.entrenadores);
        
        conexionbd.consultarPatrocinadores(this.patrocinadores);

        conexionbd.close();
    }
    
    
    public void addJugador(int id, String nombre, String apellido, String anio_nacimiento, String nacionalidad, String posicion, double salario){

        boolean id_encontrada = false;

        //this.deserializarJugador();

        /*for (int i = 0; (i < this.jugadores.size()) && (this.jugadores.get(i).getId() != id); i++) {
            if(this.jugadores.get(i).getId().equals(id)){

                id_encontrada = true;
            }
        }*/

        Jugador jugador = new Jugador(id,nombre, apellido, anio_nacimiento, nacionalidad, posicion, salario);
                //this.jugadores.add(jugador);

                //this.serializarJugador();
        conexionbd.insertarJugadorBd(jugador);
                


    }
    
    public void removeJugador(String id){
        
        for(int i = 0; i < this.clubes.size(); i++){
            for(int j = 0; j < this.clubes.get(i).getJugadores().size(); j++){
                if(id.equals(this.clubes.get(i).getJugadores().get(j).getId())){
                    this.clubes.get(i).getJugadores().get(j).mostrarDatos();
                    this.clubes.get(i).getJugadores().remove(j);
                    
                    System.out.println("remove el canelita");
                }
            } 
        }
        this.serializarClub();
        
        for (int i = 0; i < this.jugadores.size(); i++) {
            
            if(id.equals(this.jugadores.get(i).getId())){
                
                this.jugadores.remove(i);
            }
        }
        
        this.serializarJugador();
        
    }
    
    public void modificarJugador(String id, String nombre, String apellido, double salario, String anio_nacimiento, String nacionalidad, String posicion){
        
        for (int i = 0; i < this.jugadores.size(); i++) {
            
            if(id.equals(this.jugadores.get(i).getId())){
                
                this.jugadores.get(i).setNombre(nombre);
                this.jugadores.get(i).setApellido(apellido);
                this.jugadores.get(i).setSalario(salario);
                this.jugadores.get(i).setanio_nacimiento(anio_nacimiento);
                this.jugadores.get(i).setNacionalidad(nacionalidad);
                this.jugadores.get(i).setPosicion(posicion);
            }
        }
        
        this.serializarJugador();
    }
    
    public void addEntrenador(String id, String nombre, String apellido, String anio_nacimiento, String nacionalidad){

        boolean id_encontrada = false;

        this.deserializarEntrenador();

        for (int i = 0; (i < this.entrenadores.size()) && (this.entrenadores.get(i).getId() != id); i++) {
            System.out.println("id bucle = " + this.entrenadores.get(i).getId());
            if(this.entrenadores.get(i).getId().equals(id)){

                id_encontrada = true;
            }
        }

        if(id_encontrada == false){

            Entrenador entrenador = new Entrenador(id, nombre, apellido, anio_nacimiento, nacionalidad);
            
            if(this.entrenadores != null){

                this.entrenadores.add(entrenador);
                
                this.serializarEntrenador();

            }
            else{

                System.out.println("ERROR");
            }

        }
        else{

            System.out.println("ID REPETIDA");
        }
        
    }
    
    public void removeEntrenador(String id){
        
         System.out.println("entra canelita remove");
        
         int pos = 0;
         
        for(int i = 0; i < this.clubes.size(); i++){
            
            if(this.clubes.get(i).getEntrenador() != null && id.equals(this.clubes.get(i).getEntrenador().getId())){
                this.clubes.get(i).getEntrenador().mostrarDatosEntrenador();
                this.clubes.get(i).setEntrenador(null);
                pos = i;
            }
            
        }
        
        if(this.clubes.get(pos).getEntrenador() == null){
            
            System.out.println("canelita esta null");
        }
        
        for (int i = 0; i < this.entrenadores.size(); i++) {
            
            if(id.equals(this.entrenadores.get(i).getId())){
                
                this.entrenadores.remove(i);
            }
        }
        
        this.serializarClub();
        
        this.serializarEntrenador();
        
         System.out.println("sale canelita remove");
    }
    
    public void modificarEntrenador(String id, String nombre, String apellido, String anio_nacimiento, String nacionalidad){
        
        
        for (int i = 0; i < this.entrenadores.size(); i++) {
            
            if(id.equals(this.entrenadores.get(i).getId())){
                
                this.entrenadores.get(i).setNombre(nombre);
                this.entrenadores.get(i).setApellido(apellido);
                this.entrenadores.get(i).setAnio_nacimiento(anio_nacimiento);
                this.entrenadores.get(i).setNacionalidad(nacionalidad);
            }
        }
        
        this.serializarEntrenador();
    }
    
    public void addPatrocinador(String id, String nombre_empresa, String tipo_patrocinador, int duracion_contrato){

        boolean id_encontrada = false;

        this.deserializarPatrocinador();

        for (int i = 0; (i < this.patrocinadores.size()) && (this.patrocinadores.get(i).getId_patrocinador()!= id); i++) {
            
            if(this.patrocinadores.get(i).getId_patrocinador().equals(id)){

                id_encontrada = true;
            }
        }

        if(id_encontrada == false){
            
            Patrocinador patrocinador = new Patrocinador(id, nombre_empresa, tipo_patrocinador, duracion_contrato);

            if(this.patrocinadores != null){

                this.patrocinadores.add(patrocinador);

                this.serializarPatrocinador();

            }
            else{

                System.out.println("ERROR");
            }

        }
        else{

            System.out.println("ID REPETIDA");
        }

    }
    
    public void removePatrocinador(String id){
        
        for(int i = 0; i < this.clubes.size(); i++){
            for(int j = 0; j < this.clubes.get(i).getPatrocinadores().size(); j++){
                if(id.equals(this.clubes.get(i).getPatrocinadores().get(j).getId_patrocinador())){
                    this.clubes.get(i).getPatrocinadores().get(j).mostrarDatos();
                    this.clubes.get(i).getPatrocinadores().remove(j);
                    
                    System.out.println("remove el canelita");
                }
            }
            
        }
        
        this.serializarClub();
        
        for (int i = 0; i < this.patrocinadores.size(); i++) {
            
            if(id.equals(this.patrocinadores.get(i).getId_patrocinador())){
                
                this.patrocinadores.remove(i);
            }
        }
        
        this.serializarPatrocinador();
    }
    
    public void modificarPatrocinador(String id, String nombre_empresa, String tipo_patrocinio, int duracion_contrato){
        
        
        for (int i = 0; i < this.patrocinadores.size(); i++) {
            
            if(id.equals(this.patrocinadores.get(i).getId_patrocinador())){
                
                this.patrocinadores.get(i).setNombre_empresa(nombre_empresa);
                this.patrocinadores.get(i).setTipo_patrocinio(tipo_patrocinio);
                this.patrocinadores.get(i).setDuracion_contrato(duracion_contrato);
            }
        }
        this.serializarPatrocinador();
    }
    
    public void addClub(String id, String nombre, int anio_fundacion){

        boolean id_encontrada = false;

        this.deserializarClub();

        for (int i = 0; (i < this.clubes.size()) && (this.clubes.get(i).getId()!= id); i++) {
            
            if(this.clubes.get(i).getId().equals(id)){

                id_encontrada = true;
            }
        }

        if(id_encontrada == false){
            
            Entrenador entrenador = new Entrenador();
            
            Club club = new Club(id, nombre, anio_fundacion,entrenador);

            if(this.clubes != null){

                this.clubes.add(club);

                this.serializarClub();

            }
            else{

                System.out.println("ERROR");
            }

        }
        else{

            System.out.println("ID REPETIDA");
        }

    }
    
    public void removeClub(String id){
        
        for (int i = 0; i < this.patrocinadores.size(); i++) {
            for(int j = 0; j < this.patrocinadores.get(i).getClubes().size(); j++){
                if(id.equals(this.patrocinadores.get(i).getClubes().get(j).getId())){
                    this.patrocinadores.get(i).getClubes().remove(j);
                }
            }
        }
        this.serializarPatrocinador();
        
        for (int i = 0; i < this.ligas.size(); i++) {
            for(int j = 0; j < this.ligas.get(i).getClubes().size(); j++){
                if(id.equals(this.ligas.get(i).getClubes().get(j).getId())){
                    this.ligas.get(i).getClubes().remove(j);
                }
            }
        }
        
        this.serializarLiga();
        
        for (int i = 0; i < this.clubes.size(); i++) {
            
            if(id.equals(this.clubes.get(i).getId())){
                
                this.clubes.remove(i);
            }
        }
        
        this.serializarClub();
    }
    
    public void modificarClub(String id, String nombre, int anio_fundacion){
        
        
        for (int i = 0; i < this.clubes.size(); i++) {
            
            if(id.equals(this.clubes.get(i).getId())){
                
                this.clubes.get(i).setNombre(nombre);
                this.clubes.get(i).setAnio_fundacion(anio_fundacion);
            }
        }
        
        this.serializarClub();
    }
    
    public void addLiga(String id, String nombre, String pais, int temporada){

        boolean id_encontrada = false;

        this.deserializarLiga();

        for (int i = 0; (i < this.ligas.size()) && (this.ligas.get(i).getId()!= id); i++) {
            
            if(this.ligas.get(i).getId().equals(id)){

                id_encontrada = true;
            }
        }

        if(id_encontrada == false){
            
            Liga liga = new Liga(id, nombre, pais, temporada);

            if(this.ligas != null){

                this.ligas.add(liga);

                this.serializarLiga();

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
        
        this.serializarLiga();
    }
    
    public void modificarLiga(String id, String nombre, String pais, int temporada){
        
        
        for (int i = 0; i < this.ligas.size(); i++) {
            
            if(id.equals(this.ligas.get(i).getId())){
                
                this.ligas.get(i).setNombre(nombre);
                this.ligas.get(i).setPais(pais);
                this.ligas.get(i).setTemporada(temporada);
            }
        }
        
        this.serializarLiga();
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
            this.serializarLiga();
        }
    }
    
    public void comprobarIdEntrenador(String id){
        for (int i = 0; i < this.entrenadores.size(); i++) {
            if(id.equals(this.entrenadores.get(i).getId())){
                id_entrenador_encontrada = true;
            }
        }
        
        numero_veces_id_entrenador_repetida = 0;
        for(int i = 0; i < this.clubes.size(); i++){
            if(id.equals(this.clubes.get(i).getEntrenador().getId())){
                numero_veces_id_entrenador_repetida++; 
            }
        }
    }
    
    public void addEntrenadorClub(String id_club, String id_entrenador){
        
        this.comprobarIdEntrenador(id_entrenador);
        
        if((id_entrenador_encontrada == true) && (numero_veces_id_entrenador_repetida < 1)){
            for(int i = 0; i < this.entrenadores.size(); i++){
                if(id_entrenador.equals(this.entrenadores.get(i).getId())){
                    for (int j = 0; j < this.clubes.size(); j++) {
                        if(id_club.equals(this.clubes.get(j).getId())){
                            this.clubes.get(j).setEntrenador(this.entrenadores.get(i));
                            this.clubes.get(j).getEntrenador().setId(this.entrenadores.get(i).getId());
                        }
                    }
                }
            }
            
            this.serializarClub();
        }
    }
    
    public void despedirEntrenadorClub(String id){
        
        for (int i = 0; i < this.clubes.size(); i++) {
            if(id.equals(this.clubes.get(i).getId())){
                this.clubes.get(i).setEntrenador(null);
            }
        }
        
        this.serializarClub();
    }
    
    public void comprobarIdJugador(String id){
        for (int i = 0; i < this.jugadores.size(); i++) {
            if(id.equals(this.jugadores.get(i).getId())){
                id_jugador_encontrada = true;
            }
        }
        
        numero_veces_id_jugador_repetida = 0;
        
        for(int i = 0; i < this.clubes.size(); i++){
            if(this.clubes.get(i).getJugadores() != null){
                for(int j = 0; j < this.clubes.get(i).getJugadores().size(); j++)
                    if(id.equals(this.clubes.get(i).getJugadores().get(j).getId())){
                        numero_veces_id_jugador_repetida++; 
                    }
            }
        }

    }
    
    
    public void addJugadorClub(String id_club, String id_jugador){
        
        this.comprobarIdJugador(id_jugador);
        
        if((id_jugador_encontrada == true) && (numero_veces_id_jugador_repetida < 1)){
            for(int i = 0; i < this.jugadores.size(); i++){
                if(id_jugador.equals(this.jugadores.get(i).getId())){
                    for (int j = 0; j < this.clubes.size(); j++) {
                        if(id_club.equals(this.clubes.get(j).getId())){
                            this.clubes.get(j).getJugadores().add(this.jugadores.get(i));
                        }
                    }
                }
            }
            
            this.serializarClub();
        }
    }
    
    public void despedirJugadorClub(String id_club, String id_jugador){
        
        for (int i = 0; i < this.clubes.size(); i++) {
            if(id_club.equals(this.clubes.get(i).getId())){
                for(int j = 0; j < this.clubes.get(i).getJugadores().size(); j++){
                    if(id_jugador.equals(this.clubes.get(i).getJugadores().get(j).getId())){
                        this.clubes.get(i).getJugadores().remove(j);
                    }
                }
            }
        }
        
        this.serializarClub();
    }
    
    public void comprobarIdPatrocinador(String id){
        for (int i = 0; i < this.patrocinadores.size(); i++) {
            if(id.equals(this.patrocinadores.get(i).getId_patrocinador())){
                id_patrocinador_encontrada = true;
            }
        }
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
        
        this.comprobarIdPatrocinador(id_patrocinador);
        
        this.comprobarIdPatrocinadorEnClub(id_club, id_patrocinador);
        
        if(id_patrocinador_encontrada == true && id_patrocinador_en_club_encontrada == false){
            for(int i = 0; i < this.patrocinadores.size(); i++){
                if(id_patrocinador.equals(this.patrocinadores.get(i).getId_patrocinador())){
                    for (int j = 0; j < this.clubes.size(); j++) {
                        if(id_club.equals(this.clubes.get(j).getId())){
                            this.clubes.get(j).getPatrocinadores().add(this.patrocinadores.get(i));
                            
                        }
                    }
                }
            }
            
            this.serializarClub();
            
            for(int i = 0; i < this.patrocinadores.size(); i++){
                if(id_patrocinador.equals(this.patrocinadores.get(i).getId_patrocinador())){
                    for(int j = 0; j < this.clubes.size(); j++){
                        if(id_club.equals(this.clubes.get(j).getId())){
                            this.patrocinadores.get(i).getClubes().add(this.clubes.get(j));
                        }
                    }
                }
            }
            this.serializarPatrocinador();
        }

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
        
        this.serializarClub();
        
        for(int i = 0; i < this.patrocinadores.size(); i++){
            if(id_patrocinador.equals(this.patrocinadores.get(i).getId_patrocinador())){
                for(int j = 0; j < this.patrocinadores.get(i).getClubes().size(); j++){
                    if(id_club.equals(this.patrocinadores.get(i).getClubes().get(j).getId())){
                            this.patrocinadores.get(i).getClubes().remove(j);
                    }
                }
            }
        }
        this.serializarPatrocinador();
    }
}
