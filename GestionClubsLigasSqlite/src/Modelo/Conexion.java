
package Modelo;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Types;


public class Conexion {
    private static Connection connection;

    public Conexion() {
        connection = null;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if(connection == null){

            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\alvar\\OneDrive\\Escritorio\\JAVA\\GestionClubsLigasSqlite\\gestionClubesLigas.db");
            System.out.println("Conexion hecha");
        }
        
        return connection;
    }

    public void consultarJugadores(ArrayList<Jugador>jugadores) throws SQLException, ClassNotFoundException{

        jugadores.clear();
        
        String sentenciaSql = "SELECT * FROM Jugadores";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);


        ResultSet resultado_consulta = sentencia.executeQuery();
        int i = 0;
        while (resultado_consulta.next()) {
                // Obtener los datos de cada fila
            Jugador jugador = new Jugador();
            jugador.setId(resultado_consulta.getInt("id_jugador"));
            jugador.setId_club(resultado_consulta.getInt("id_club"));
            jugador.setNombre(resultado_consulta.getString("nombre"));
            jugador.setApellido(resultado_consulta.getString("apellido"));
            jugador.setanio_nacimiento(resultado_consulta.getString("anio_nacimiento"));
            jugador.setNacionalidad(resultado_consulta.getString("nacionalidad"));
            jugador.setPosicion(resultado_consulta.getString("posicion"));
            jugador.setSalario(resultado_consulta.getDouble("salario"));

            jugadores.add(jugador);

            i++;
        }
        sentencia.close();
        
    }
    
    public void insertarJugadorBd(Jugador jugador) throws ClassNotFoundException, SQLException{

        String sentenciaSql = "INSERT INTO Jugadores (nombre, apellido, anio_nacimiento, nacionalidad, posicion, salario) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setString(1,jugador.getNombre());
        sentencia.setString(2, jugador.getApellido());
        sentencia.setString(3, jugador.getanio_nacimiento());
        sentencia.setString(4, jugador.getNacionalidad());
        sentencia.setString(5, jugador.getPosicion());
        sentencia.setDouble(6, jugador.getSalario());
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
    }
    
    public void modificarJugadorBd(Jugador jugador) throws ClassNotFoundException, SQLException{

        String sentenciaSql = "UPDATE Jugadores SET nombre = ?, apellido = ?, anio_nacimiento = ?, nacionalidad = ?, posicion = ?, salario = ? " + "WHERE id_jugador = ?";
        PreparedStatement sentencia = null;


        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setString(1,jugador.getNombre());
        sentencia.setString(2, jugador.getApellido());
        sentencia.setString(3, jugador.getanio_nacimiento());
        sentencia.setString(4, jugador.getNacionalidad());
        sentencia.setString(5, jugador.getPosicion());
        sentencia.setDouble(6, jugador.getSalario());
        sentencia.setInt(7, jugador.getId());
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
            
    }
    
    public void eliminarJugadorBd(String id) throws ClassNotFoundException, SQLException{

        String sentenciaSql = "DELETE FROM Jugadores WHERE id_jugador = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setInt(1,Integer.parseInt(id));
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
          
    }
    
    public void consultarEntrenadores(ArrayList<Entrenador>entrenadores) throws SQLException, ClassNotFoundException{
        
        entrenadores.clear();
        
        String sentenciaSql = "SELECT * FROM Entrenadores";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);


        ResultSet resultado_consulta = sentencia.executeQuery();
        int i = 0;
        while (resultado_consulta.next()) {
                // Obtener los datos de cada fila
            Entrenador entrenador = new Entrenador();
            entrenador.setId(Integer.toString(resultado_consulta.getInt("id_entrenador")));
            entrenador.setNombre(resultado_consulta.getString("nombre"));
            entrenador.setApellido(resultado_consulta.getString("apellido"));
            entrenador.setAnio_nacimiento(resultado_consulta.getString("anio_nacimiento"));
            entrenador.setNacionalidad(resultado_consulta.getString("nacionalidad"));
            entrenadores.add(entrenador);

            i++;
        }
        sentencia.close();
    }
    
    public void insertarEntrenadorBd(Entrenador entrenador) throws ClassNotFoundException, SQLException{

        String sentenciaSql = "INSERT INTO Entrenadores (nombre, apellido, anio_nacimiento, nacionalidad) VALUES (?, ?, ?, ?)";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setString(1,entrenador.getNombre());
        sentencia.setString(2, entrenador.getApellido());
        sentencia.setString(3, entrenador.getAnio_nacimiento());
        sentencia.setString(4, entrenador.getNacionalidad());
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
            
    }
    
    public void modificarEntrenadorBd(Entrenador entrenador) throws ClassNotFoundException, SQLException{

        String sentenciaSql = "UPDATE Entrenadores SET nombre = ?, apellido = ?, anio_nacimiento = ?, nacionalidad = ?" + "WHERE id_entrenador = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setString(1,entrenador.getNombre());
        sentencia.setString(2, entrenador.getApellido());
        sentencia.setString(3, entrenador.getAnio_nacimiento());
        sentencia.setString(4, entrenador.getNacionalidad());
        sentencia.setInt(5, Integer.parseInt(entrenador.getId()));
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
            
    }
    
    public void eliminarEntrenadorBd(String id) throws ClassNotFoundException, SQLException{

        String sentenciaSql = "DELETE FROM Entrenadores WHERE id_entrenador = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setInt(1,Integer.parseInt(id));
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
            
    }
    
    public void consultarPatrocinadores(ArrayList<Patrocinador>patrocinadores) throws SQLException, ClassNotFoundException{

        patrocinadores.clear();
        
        String sentenciaSql = "SELECT * FROM Patrocinadores";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);

        ResultSet resultado_consulta = sentencia.executeQuery();
        int i = 0;
        while (resultado_consulta.next()) {
                // Obtener los datos de cada fila
            Patrocinador patrocinador = new Patrocinador();
            patrocinador.setId_patrocinador(Integer.toString(resultado_consulta.getInt("id_patrocinador")));
            patrocinador.setNombre_empresa(resultado_consulta.getString("nombre_empresa"));
            patrocinador.setTipo_patrocinio(resultado_consulta.getString("tipo_patrocinio"));
            patrocinador.setDuracion_contrato(resultado_consulta.getInt("duracion_contrato"));
            patrocinadores.add(patrocinador);

            i++;
        }
        sentencia.close();

    }
    
    public void insertarPatrocinadorBd(Patrocinador patrocinador) throws ClassNotFoundException, SQLException{

        String sentenciaSql = "INSERT INTO Patrocinadores (nombre_empresa, tipo_patrocinio, duracion_contrato) VALUES (?, ?, ?)";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setString(1,patrocinador.getNombre_empresa());
        sentencia.setString(2, patrocinador.getTipo_patrocinio());
        sentencia.setInt(3, patrocinador.getDuracion_contrato());
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
            
    }
    
    public void modificarPatrocinadorBd(Patrocinador patrocinador) throws ClassNotFoundException, SQLException{

        String sentenciaSql = "UPDATE Patrocinadores SET nombre_empresa = ?, tipo_patrocinio = ?, duracion_contrato = ?" + "WHERE id_patrocinador = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setString(1,patrocinador.getNombre_empresa());
        sentencia.setString(2, patrocinador.getTipo_patrocinio());
        sentencia.setInt(3, patrocinador.getDuracion_contrato());
        sentencia.setInt(4, Integer.parseInt(patrocinador.getId_patrocinador()));
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
            
    }
    
    public void eliminarPatrocinadorBd(String id) throws ClassNotFoundException, SQLException{

        String sentenciaSql = "DELETE FROM Patrocinadores WHERE id_patrocinador = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setInt(1,Integer.parseInt(id));
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
            
    }
    
    public void consultarClubesPatrocinador(int id_patrocinador, ArrayList<Club>clubes_patrocinador) throws SQLException, ClassNotFoundException{

        clubes_patrocinador.clear();
        
        String sentenciaSql = "SELECT C.* FROM Clubes C JOIN Clubes_Patrocinadores CP ON C.id_club = CP.id_club WHERE CP.id_patrocinador = ?";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);

        connection.setAutoCommit(false);
        sentencia.setInt(1, id_patrocinador);
        ResultSet resultado_consulta = sentencia.executeQuery();
        int i = 0;
        while (resultado_consulta.next()) {
                // Obtener los datos de cada fila
            Club club = new Club();
            club.setId(Integer.toString(resultado_consulta.getInt("id_club")));
            club.setNombre(resultado_consulta.getString("nombre"));
            club.setAnio_fundacion(resultado_consulta.getInt("anio_fundacion"));
            club.getEntrenador().setId(Integer.toString(resultado_consulta.getInt("id_entrenador")));

            clubes_patrocinador.add(club);

            i++;
        }
        connection.commit();
        sentencia.close();

    }
    
    public void consultarClubes(ArrayList<Club>clubes) throws SQLException, ClassNotFoundException{

        clubes.clear();
        
        String sentenciaSql = "SELECT * FROM Clubes";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);

        ResultSet resultado_consulta = sentencia.executeQuery();
        int i = 0;
        while (resultado_consulta.next()) {
                // Obtener los datos de cada fila
            Club club = new Club();
            club.setId(Integer.toString(resultado_consulta.getInt("id_club")));
            club.setNombre(resultado_consulta.getString("nombre"));
            club.setAnio_fundacion(resultado_consulta.getInt("anio_fundacion"));
            club.getEntrenador().setId(Integer.toString(resultado_consulta.getInt("id_entrenador")));

            clubes.add(club);

            i++;
        }
        sentencia.close();

    }
    
    public void insertarClubBd(Club club) throws ClassNotFoundException, SQLException{

        String sentenciaSql = "INSERT INTO Clubes (nombre, anio_fundacion) VALUES (?, ?)";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setString(1,club.getNombre());
        sentencia.setInt(2, club.getAnio_fundacion());
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
    }
    
    public void modificarClubBd(Club club) throws ClassNotFoundException, SQLException{

        String sentenciaSql = "UPDATE Clubes SET nombre = ?, anio_fundacion = ?" + "WHERE id_club = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setString(1,club.getNombre());
        sentencia.setInt(2, club.getAnio_fundacion());
        sentencia.setInt(3, Integer.parseInt(club.getId()));
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
    }
    
    public void eliminarClubBd(String id) throws ClassNotFoundException, SQLException{

        String sentenciaSql = "DELETE FROM Clubes WHERE id_club = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setInt(1,Integer.parseInt(id));
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
            
    }
    
    public void desasignarJugadoresClub(int id_club) throws ClassNotFoundException, SQLException{
        
        String sentenciaSql = "UPDATE Jugadores SET id_club = ?" + "WHERE id_club = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setNull(1, Types.INTEGER);
        sentencia.setInt(2, id_club);
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
    }
    
    public void desasignarPatrocinadoresClub(int id_club) throws ClassNotFoundException, SQLException{
        
        String sentenciaSql = "DELETE FROM Clubes_Patrocinadores " + "WHERE id_club = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setInt(1, id_club);
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
    }
    
    public void consultarEntrenadoresLibresBd(ArrayList<Entrenador>entrenadores) throws SQLException, ClassNotFoundException{

        entrenadores.clear();
        
        String sentenciaSql = "SELECT * " +
        "FROM Entrenadores " +
        "LEFT JOIN Clubes ON Entrenadores.id_entrenador = Clubes.id_entrenador " +
        "WHERE Clubes.id_entrenador IS NULL";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);

        ResultSet resultado_consulta = sentencia.executeQuery();
        int i = 0;
        while (resultado_consulta.next()) {
                // Obtener los datos de cada fila
            Entrenador entrenador = new Entrenador();
            entrenador.setId(Integer.toString(resultado_consulta.getInt("id_entrenador")));
            entrenador.setNombre(resultado_consulta.getString("nombre"));
            entrenador.setApellido(resultado_consulta.getString("apellido"));
            entrenador.setAnio_nacimiento(resultado_consulta.getString("anio_nacimiento"));
            entrenador.setNacionalidad(resultado_consulta.getString("nacionalidad"));


            entrenadores.add(entrenador);

            i++;
        }
        sentencia.close();
    }
    
    public void contratarEntrenadorClub(int id_club, int id_entrenador) throws ClassNotFoundException, SQLException{
        
        String sentenciaSql = "UPDATE Clubes SET id_entrenador = ?" + "WHERE id_club = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setInt(1,id_entrenador);
        sentencia.setInt(2, id_club);
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
        
    }
    
    public void despedirEntrenadorClub(int id_club) throws ClassNotFoundException, SQLException{
        
        String sentenciaSql = "UPDATE Clubes SET id_entrenador = ?" + "WHERE id_club = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setNull(1, Types.INTEGER);
        sentencia.setInt(2, id_club);
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
        
    }
    
    public void consultarJugadoresLibresBd(ArrayList<Jugador>jugadores) throws SQLException, ClassNotFoundException{

        jugadores.clear();
        
        String sentenciaSql = "SELECT * " + "FROM Jugadores " + "WHERE id_club IS NULL";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);

        ResultSet resultado_consulta = sentencia.executeQuery();
        int i = 0;
        while (resultado_consulta.next()) {
                // Obtener los datos de cada fila
            Jugador jugador = new Jugador();
            jugador.setId(resultado_consulta.getInt("id_jugador"));
            jugador.setId_club(resultado_consulta.getInt("id_club"));
            jugador.setNombre(resultado_consulta.getString("nombre"));
            jugador.setApellido(resultado_consulta.getString("apellido"));
            jugador.setanio_nacimiento(resultado_consulta.getString("anio_nacimiento"));
            jugador.setNacionalidad(resultado_consulta.getString("nacionalidad"));
            jugador.setPosicion(resultado_consulta.getString("posicion"));
            jugador.setSalario(resultado_consulta.getDouble("salario"));

            jugadores.add(jugador);

            i++;
        }
        sentencia.close();
    }
    
    public void consultarJugadoresClubBd(int id_club, ArrayList<Jugador>jugadores_club) throws SQLException, ClassNotFoundException{

        jugadores_club.clear();
        
        String sentenciaSql = "SELECT * " + "FROM Jugadores " + "WHERE id_club = ?";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);

        connection.setAutoCommit(false);
        sentencia.setInt(1, id_club);
        ResultSet resultado_consulta = sentencia.executeQuery();
        int i = 0;
        while (resultado_consulta.next()) {
                // Obtener los datos de cada fila
            Jugador jugador = new Jugador();
            jugador.setId(resultado_consulta.getInt("id_jugador"));
            jugador.setId_club(resultado_consulta.getInt("id_club"));
            jugador.setNombre(resultado_consulta.getString("nombre"));
            jugador.setApellido(resultado_consulta.getString("apellido"));
            jugador.setanio_nacimiento(resultado_consulta.getString("anio_nacimiento"));
            jugador.setNacionalidad(resultado_consulta.getString("nacionalidad"));
            jugador.setPosicion(resultado_consulta.getString("posicion"));
            jugador.setSalario(resultado_consulta.getDouble("salario"));

            jugadores_club.add(jugador);

            i++;
        }
        connection.commit();
        sentencia.close();
    }
    
    public void contratarJugadorClub(int id_club, int id_jugador) throws ClassNotFoundException, SQLException{
        
        String sentenciaSql = "UPDATE Jugadores SET id_club = ?" + "WHERE id_jugador = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setInt(1,id_club);
        sentencia.setInt(2, id_jugador);
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
        
    }
    
    public void despedirJugadorClub(int id_jugador) throws ClassNotFoundException, SQLException{
        
        String sentenciaSql = "UPDATE Jugadores SET id_club = ?" + "WHERE id_jugador = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setNull(1, Types.INTEGER);
        sentencia.setInt(2, id_jugador);
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
    }
    
    public void consultarPatrocinadoresClubBd(int id_club, ArrayList<Patrocinador>patrocinadores_club) throws SQLException, ClassNotFoundException{

        patrocinadores_club.clear();
        
        String sentenciaSql = "SELECT DISTINCT P.* " + "FROM Patrocinadores P " + "JOIN Clubes_Patrocinadores CP ON P.id_patrocinador = CP.id_patrocinador " + "WHERE CP.id_club = ?";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);
        
        connection.setAutoCommit(false);
        sentencia.setInt(1, id_club);
        ResultSet resultado_consulta = sentencia.executeQuery();
        int i = 0;
        while (resultado_consulta.next()) {
                // Obtener los datos de cada fila
            Patrocinador patrocinador = new Patrocinador();
            patrocinador.setId_patrocinador(Integer.toString(resultado_consulta.getInt("id_patrocinador")));
            patrocinador.setNombre_empresa(resultado_consulta.getString("nombre_empresa"));
            patrocinador.setTipo_patrocinio(resultado_consulta.getString("tipo_patrocinio"));
            patrocinador.setDuracion_contrato(resultado_consulta.getInt("duracion_contrato"));
            patrocinadores_club.add(patrocinador);

            i++;
        }
        connection.commit();
        sentencia.close();
    }
    
    public void consultarPatrocinadoresLibresParaUnClubBd(int id_club, ArrayList<Patrocinador>patrocinadores_libres) throws SQLException, ClassNotFoundException{

        patrocinadores_libres.clear();
        
        String sentenciaSql = "SELECT P.* " + "FROM Patrocinadores P " + "WHERE P.id_patrocinador NOT IN (SELECT CP.id_patrocinador FROM Clubes_Patrocinadores CP " + "WHERE CP.id_club = ?)";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);

        connection.setAutoCommit(false);
        sentencia.setInt(1, id_club);
        ResultSet resultado_consulta = sentencia.executeQuery();
        int i = 0;
        while (resultado_consulta.next()) {
                // Obtener los datos de cada fila
            Patrocinador patrocinador = new Patrocinador();
            patrocinador.setId_patrocinador(Integer.toString(resultado_consulta.getInt("id_patrocinador")));
            patrocinador.setNombre_empresa(resultado_consulta.getString("nombre_empresa"));
            patrocinador.setTipo_patrocinio(resultado_consulta.getString("tipo_patrocinio"));
            patrocinador.setDuracion_contrato(resultado_consulta.getInt("duracion_contrato"));
            patrocinadores_libres.add(patrocinador);

            i++;
        }
        connection.commit();
        sentencia.close();
    }
    
    public void contratarPatrocinadorClub(int id_club, int id_jugador) throws ClassNotFoundException, SQLException{
        
        String sentenciaSql = "INSERT INTO Clubes_Patrocinadores (id_club, id_patrocinador) VALUES (?, ?)";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setInt(1,id_club);
        sentencia.setInt(2, id_jugador);
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
    }
    
    public void despedirPatrocinadorClub(int id_club, int id_patrocinador) throws ClassNotFoundException, SQLException{
        
        String sentenciaSql = "DELETE FROM Clubes_Patrocinadores WHERE id_club = ? AND id_patrocinador = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setInt(1,id_club);
        sentencia.setInt(2, id_patrocinador);
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
    }
    
    public void consultarLigas(ArrayList<Liga>ligas) throws SQLException, ClassNotFoundException{

        ligas.clear();
        
        String sentenciaSql = "SELECT * FROM Ligas";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);
        
        ResultSet resultado_consulta = sentencia.executeQuery();
        int i = 0;
        while (resultado_consulta.next()) {
                // Obtener los datos de cada fila
            Liga liga = new Liga();
            liga.setId(Integer.toString(resultado_consulta.getInt("id_liga")));
            liga.setNombre(resultado_consulta.getString("nombre"));
            liga.setPais(resultado_consulta.getString("pais"));
            liga.setTemporada(resultado_consulta.getInt("temporada"));

            ligas.add(liga);

            i++;
        }
        sentencia.close();
    }
    
    public void insertarLigaBd(Liga liga) throws ClassNotFoundException, SQLException{

        String sentenciaSql = "INSERT INTO Ligas (nombre, pais, temporada) VALUES (?, ?, ?)";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setString(1,liga.getNombre());
        sentencia.setString(2, liga.getPais());
        sentencia.setInt(3, liga.getTemporada());
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
    }
    
    public void modificarLigaBd(Liga liga) throws ClassNotFoundException, SQLException{

        String sentenciaSql = "UPDATE Ligas SET nombre = ?, pais = ?, temporada = ?" + "WHERE id_liga = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setString(1,liga.getNombre());
        sentencia.setString(2, liga.getPais());
        sentencia.setInt(3, liga.getTemporada());
        sentencia.setInt(4, Integer.parseInt(liga.getId()));
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
    }
    
    public void desasignarLigaClubes(int id_liga) throws ClassNotFoundException, SQLException{
        
        String sentenciaSql = "UPDATE Clubes SET id_liga = ?" + "WHERE id_liga = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setNull(1, Types.INTEGER);
        sentencia.setInt(2, id_liga);
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close(); 
    }
    
    public void eliminarLigaBd(String id) throws ClassNotFoundException, SQLException{

        String sentenciaSql = "DELETE FROM Ligas WHERE id_liga = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setInt(1,Integer.parseInt(id));
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
    }
    
    public void consultarClubesLibresBd(ArrayList<Club>clubes_libres) throws SQLException, ClassNotFoundException{

        clubes_libres.clear();
        
        String sentenciaSql = "SELECT * " + "FROM Clubes " + "WHERE id_liga IS NULL";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);


        ResultSet resultado_consulta = sentencia.executeQuery();
        int i = 0;
        while (resultado_consulta.next()) {
                // Obtener los datos de cada fila
            Club club = new Club();
            club.setId(Integer.toString(resultado_consulta.getInt("id_club")));
            club.setNombre(resultado_consulta.getString("nombre"));
            club.setAnio_fundacion(resultado_consulta.getInt("anio_fundacion"));
            club.getEntrenador().setId(Integer.toString(resultado_consulta.getInt("id_entrenador")));

            clubes_libres.add(club);

            i++;
        }

        sentencia.close();   
    }
    
    public void aniadirClubLiga(int id_liga, int id_club) throws SQLException, ClassNotFoundException{
        
        String sentenciaSql = "UPDATE Clubes SET id_liga = ?" + "WHERE id_club = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setInt(1, id_liga);
        sentencia.setInt(2, id_club);
        sentencia.executeUpdate();
            
        connection.commit();
        sentencia.close();
        
    }
    
    public void eliminarClubLiga(int id_club) throws SQLException, ClassNotFoundException{
        
        String sentenciaSql = "UPDATE Clubes SET id_liga = ?" + "WHERE id_club = ?";
        PreparedStatement sentencia = null;

        connection.setAutoCommit(false);
        sentencia = this.getConnection().prepareStatement(sentenciaSql);
        sentencia.setNull(1, Types.INTEGER);
        sentencia.setInt(2, id_club);
        sentencia.executeUpdate();

        connection.commit();
        sentencia.close();
    }
    
    public void consultarClubesLigaBd(int id_liga, ArrayList<Club>clubes_liga) throws SQLException, ClassNotFoundException{

        clubes_liga.clear();
        
        String sentenciaSql = "SELECT * FROM Clubes WHERE id_liga = ?";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);

        sentencia.setInt(1, id_liga);
        ResultSet resultado_consulta = sentencia.executeQuery();
        int i = 0;
        while (resultado_consulta.next()) {
                // Obtener los datos de cada fila
            Club club = new Club();
            club.setId(Integer.toString(resultado_consulta.getInt("id_club")));
            club.setNombre(resultado_consulta.getString("nombre"));
            club.setAnio_fundacion(resultado_consulta.getInt("anio_fundacion"));
            club.getEntrenador().setId(Integer.toString(resultado_consulta.getInt("id_entrenador")));

            clubes_liga.add(club);

            i++;
        }
        sentencia.close();

    }
    
    
    public static void close() throws SQLException {

        if (connection != null) {
            connection.close();
            System.out.println("Conexion cerrada");
        }
    }
}

