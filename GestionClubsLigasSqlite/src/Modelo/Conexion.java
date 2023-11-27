
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


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
    
    public void consultarClubes(ArrayList<Club>clubes) throws SQLException{

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

