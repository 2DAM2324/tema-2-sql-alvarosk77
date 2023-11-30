
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
    
    public static Connection getConnection() {
        if(connection == null){
            try {

                Class.forName("org.sqlite.JDBC");
            
                connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\alvar\\OneDrive\\Escritorio\\JAVA\\GestionClubsLigasSqlite\\gestionClubesLigas.db");
                System.out.println("Conexion hecha");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            
        }
        
        return connection;
    }

    public void consultarJugadores(ArrayList<Jugador>jugadores) throws SQLException{

        jugadores.clear();
        
        String sentenciaSql = "SELECT * FROM Jugadores";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);
        try {

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
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
        
    }
    
    public void insertarJugadorBd(Jugador jugador){

        String sentenciaSql = "INSERT INTO Jugadores (nombre, apellido, anio_nacimiento, nacionalidad, posicion, salario) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia = null;
        try {
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
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
    }
    
    public void modificarJugadorBd(Jugador jugador){

        String sentenciaSql = "UPDATE Jugadores SET nombre = ?, apellido = ?, anio_nacimiento = ?, nacionalidad = ?, posicion = ?, salario = ? " + "WHERE id_jugador = ?";
        PreparedStatement sentencia = null;

        try {
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
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
            
    }
    
    public void eliminarJugadorBd(String id){

        String sentenciaSql = "DELETE FROM Jugadores WHERE id_jugador = ?";
        PreparedStatement sentencia = null;
        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setInt(1,Integer.parseInt(id));
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
            
    }
    
    public void consultarEntrenadores(ArrayList<Entrenador>entrenadores) throws SQLException{
        
        entrenadores.clear();
        
        String sentenciaSql = "SELECT * FROM Entrenadores";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);
        try {

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
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }

    }
    
    public void insertarEntrenadorBd(Entrenador entrenador){

        String sentenciaSql = "INSERT INTO Entrenadores (nombre, apellido, anio_nacimiento, nacionalidad) VALUES (?, ?, ?, ?)";
        PreparedStatement sentencia = null;
        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setString(1,entrenador.getNombre());
            sentencia.setString(2, entrenador.getApellido());
            sentencia.setString(3, entrenador.getAnio_nacimiento());
            sentencia.setString(4, entrenador.getNacionalidad());
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
            
    }
    
    public void modificarEntrenadorBd(Entrenador entrenador){

        String sentenciaSql = "UPDATE Entrenadores SET nombre = ?, apellido = ?, anio_nacimiento = ?, nacionalidad = ?" + "WHERE id_entrenador = ?";
        PreparedStatement sentencia = null;

        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setString(1,entrenador.getNombre());
            sentencia.setString(2, entrenador.getApellido());
            sentencia.setString(3, entrenador.getAnio_nacimiento());
            sentencia.setString(4, entrenador.getNacionalidad());
            sentencia.setInt(5, Integer.parseInt(entrenador.getId()));
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
            
    }
    
    public void eliminarEntrenadorBd(String id){

        String sentenciaSql = "DELETE FROM Entrenadores WHERE id_entrenador = ?";
        PreparedStatement sentencia = null;
        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setInt(1,Integer.parseInt(id));
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
            
    }
    
    public void consultarPatrocinadores(ArrayList<Patrocinador>patrocinadores) throws SQLException{

        patrocinadores.clear();
        
        String sentenciaSql = "SELECT * FROM Patrocinadores";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);
        try {

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
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }

    }
    
    public void insertarPatrocinadorBd(Patrocinador patrocinador){

        String sentenciaSql = "INSERT INTO Patrocinadores (nombre_empresa, tipo_patrocinio, duracion_contrato) VALUES (?, ?, ?)";
        PreparedStatement sentencia = null;
        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setString(1,patrocinador.getNombre_empresa());
            sentencia.setString(2, patrocinador.getTipo_patrocinio());
            sentencia.setInt(3, patrocinador.getDuracion_contrato());
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
            
    }
    
    public void modificarPatrocinadorBd(Patrocinador patrocinador){

        String sentenciaSql = "UPDATE Patrocinadores SET nombre_empresa = ?, tipo_patrocinio = ?, duracion_contrato = ?" + "WHERE id_patrocinador = ?";
        PreparedStatement sentencia = null;

        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setString(1,patrocinador.getNombre_empresa());
            sentencia.setString(2, patrocinador.getTipo_patrocinio());
            sentencia.setInt(3, patrocinador.getDuracion_contrato());
            sentencia.setInt(4, Integer.parseInt(patrocinador.getId_patrocinador()));
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
            
    }
    
    public void eliminarPatrocinadorBd(String id){

        String sentenciaSql = "DELETE FROM Patrocinadores WHERE id_patrocinador = ?";
        PreparedStatement sentencia = null;
        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setInt(1,Integer.parseInt(id));
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
            
    }
    
    public void consultarClubes(ArrayList<Club>clubes) throws SQLException{

        clubes.clear();
        
        String sentenciaSql = "SELECT * FROM Clubes";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);
        try {

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
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }

    }
    
    public void insertarClubBd(Club club){

        String sentenciaSql = "INSERT INTO Clubes (nombre, anio_fundacion) VALUES (?, ?)";
        PreparedStatement sentencia = null;
        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setString(1,club.getNombre());
            sentencia.setInt(2, club.getAnio_fundacion());
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
            
    }
    
    public void modificarClubBd(Club club){

        String sentenciaSql = "UPDATE Clubes SET nombre = ?, anio_fundacion = ?" + "WHERE id_club = ?";
        PreparedStatement sentencia = null;

        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setString(1,club.getNombre());
            sentencia.setInt(2, club.getAnio_fundacion());
            sentencia.setInt(3, Integer.parseInt(club.getId()));
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
            
    }
    
    public void eliminarClubBd(String id){

        String sentenciaSql = "DELETE FROM Clubes WHERE id_club = ?";
        PreparedStatement sentencia = null;
        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setInt(1,Integer.parseInt(id));
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
            
    }
    
    public void desasignarJugadoresClub(int id_club){
        
        String sentenciaSql = "UPDATE Jugadores SET id_club = ?" + "WHERE id_club = ?";
        PreparedStatement sentencia = null;

        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setNull(1, Types.INTEGER);
            sentencia.setInt(2, id_club);
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
    }
    
    public void consultarEntrenadoresLibresBd(ArrayList<Entrenador>entrenadores) throws SQLException{

        entrenadores.clear();
        
        String sentenciaSql = "SELECT * " +
        "FROM Entrenadores " +
        "LEFT JOIN Clubes ON Entrenadores.id_entrenador = Clubes.id_entrenador " +
        "WHERE Clubes.id_entrenador IS NULL";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);
        try {

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
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }   
    }
    
    public void contratarEntrenadorClub(int id_club, int id_entrenador){
        
        String sentenciaSql = "UPDATE Clubes SET id_entrenador = ?" + "WHERE id_club = ?";
        PreparedStatement sentencia = null;

        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setInt(1,id_entrenador);
            sentencia.setInt(2, id_club);
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
        
    }
    
    public void despedirEntrenadorClub(int id_club){
        
        String sentenciaSql = "UPDATE Clubes SET id_entrenador = ?" + "WHERE id_club = ?";
        PreparedStatement sentencia = null;

        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setNull(1, Types.INTEGER);
            sentencia.setInt(2, id_club);
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
        
    }
    
    public void consultarJugadoresLibresBd(ArrayList<Jugador>jugadores) throws SQLException{

        jugadores.clear();
        
        String sentenciaSql = "SELECT * " + "FROM Jugadores " + "WHERE id_club IS NULL";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);
        try {

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
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }   
    }
    
    public void consultarJugadoresClubBd(int id_club, ArrayList<Jugador>jugadores_club) throws SQLException{

        jugadores_club.clear();
        
        String sentenciaSql = "SELECT * " + "FROM Jugadores " + "WHERE id_club = ?";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);
        try {
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
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }   
    }
    
    public void contratarJugadorClub(int id_club, int id_jugador){
        
        String sentenciaSql = "UPDATE Jugadores SET id_club = ?" + "WHERE id_jugador = ?";
        PreparedStatement sentencia = null;

        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setInt(1,id_club);
            sentencia.setInt(2, id_jugador);
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
        
    }
    
    public void despedirJugadorClub(int id_jugador){
        
        String sentenciaSql = "UPDATE Jugadores SET id_club = ?" + "WHERE id_jugador = ?";
        PreparedStatement sentencia = null;

        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setNull(1, Types.INTEGER);
            sentencia.setInt(2, id_jugador);
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
        
    }
    
    public void consultarPatrocinadoresClubBd(int id_club, ArrayList<Patrocinador>patrocinadores_club) throws SQLException{

        patrocinadores_club.clear();
        
        String sentenciaSql = "SELECT DISTINCT P.* " + "FROM Patrocinadores P " + "JOIN Clubes_Patrocinadores CP ON P.id_patrocinador = CP.id_patrocinador " + "WHERE CP.id_club = ?";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);
        try {
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
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }   
    }
    
    public void consultarPatrocinadoresLibresParaUnClubBd(int id_club, ArrayList<Patrocinador>patrocinadores_libres) throws SQLException{

        patrocinadores_libres.clear();
        
        String sentenciaSql = "SELECT P.* " + "FROM Patrocinadores P " + "WHERE P.id_patrocinador NOT IN (SELECT CP.id_patrocinador FROM Clubes_Patrocinadores CP " + "WHERE CP.id_club = ?)";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);
        try {
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
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }   
    }
    
    public void contratarPatrocinadorClub(int id_club, int id_jugador){
        
        String sentenciaSql = "INSERT INTO Clubes_Patrocinadores (id_club, id_patrocinador) VALUES (?, ?)";
        PreparedStatement sentencia = null;

        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setInt(1,id_club);
            sentencia.setInt(2, id_jugador);
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
        
    }
    
    public void despedirPatrocinadorClub(int id_club, int id_patrocinador){
        
        String sentenciaSql = "DELETE FROM Clubes_Patrocinadores WHERE id_club = ? AND id_patrocinador = ?";
        PreparedStatement sentencia = null;

        try {
            connection.setAutoCommit(false);
            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setInt(1,id_club);
            sentencia.setInt(2, id_patrocinador);
            sentencia.executeUpdate();
            
            connection.commit();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }
        
    }
    
    public void consultarLigas(ArrayList<Liga>ligas) throws SQLException{

        String sentenciaSql = "SELECT * FROM Ligas";
        PreparedStatement sentencia = this.getConnection().prepareStatement(sentenciaSql);
        try {

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
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            if (sentencia != null){
                try {
                    sentencia.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
               }
            }
        }

    }
    
    public static void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexion cerrada");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

