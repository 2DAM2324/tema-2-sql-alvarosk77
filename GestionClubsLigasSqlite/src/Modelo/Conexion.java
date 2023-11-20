
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
                //String columna2 = resultado_consulta.getString("nombre_columna_2");
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
        
        this.close();
    }
    
    public void insertarJugadorBd(Jugador jugador){
        //this.getConnection();
        
        String sentenciaSql = "INSERT INTO Jugadores (nombre, apellido, anio_nacimineto, nacionalidad, posicion, salario) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement sentencia = null;
        try {

            sentencia = this.getConnection().prepareStatement(sentenciaSql);
            sentencia.setString(1,jugador.getNombre());
            sentencia.setString(2, jugador.getApellido());
            sentencia.setString(3, jugador.getanio_nacimiento());
            sentencia.setString(4, jugador.getNacionalidad());
            sentencia.setString(5, jugador.getPosicion());
            sentencia.setDouble(6, jugador.getSalario());
            sentencia.executeUpdate();
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
        
        this.close();
    }
    
    public void close() {
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

