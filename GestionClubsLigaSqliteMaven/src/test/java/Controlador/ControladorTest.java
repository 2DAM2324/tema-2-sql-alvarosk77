/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Controlador;

import Modelo.Club;
import Modelo.Conexion;
import Modelo.ConexionMock;
import Modelo.Entrenador;
import Modelo.Jugador;
import Modelo.Liga;
import Modelo.Patrocinador;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author alvar
 */
public class ControladorTest {
    
    public ControladorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addJugador method, of class Controlador.
     */
    @Test
    public void testAddJugador() throws Exception {
        System.out.println("addJugador");
        
        ConexionMock conexionMock = new ConexionMock();
        Controlador controlador = new Controlador();
        controlador.configurarConexion(conexionMock);

        // Crear un jugador para la prueba
        Jugador jugador = new Jugador();
        jugador.setNombre("Lionel");
        jugador.setApellido("Messi");
        jugador.setanio_nacimiento("1987");
        jugador.setNacionalidad("Argentina");
        jugador.setPosicion("Delantero");
        jugador.setSalario(1000000.0);
        jugador.setId_club(1);

        // Ejecución de la prueba
        controlador.addJugador(jugador.getNombre(), jugador.getApellido(), jugador.getanio_nacimiento(), jugador.getNacionalidad(), jugador.getPosicion(), jugador.getSalario());
        conexionMock.close();
        // Verificación de los resultados
        assertEquals(1, conexionMock.getJugadoresBd().size());
        
    }
    
    /**
     * Test of modificarJugador method, of class Controlador.
     */
    /*@Test
    public void testModificarJugador() throws Exception {
        System.out.println("modificarJugador");
        
        ConexionMock conexionMock = new ConexionMock();
        Controlador controlador = new Controlador();
        controlador.configurarConexion(conexionMock);
        
                // Crear un jugador para la prueba
        Jugador jugadorExistente = new Jugador();
        jugadorExistente.setId(1); // Supongamos que el jugador con ID 1 ya existe
        jugadorExistente.setNombre("Lionel");
        jugadorExistente.setApellido("Messi");
        jugadorExistente.setanio_nacimiento("1987");
        jugadorExistente.setNacionalidad("Argentina");
        jugadorExistente.setPosicion("Delantero");
        jugadorExistente.setSalario(1000000.0);
        jugadorExistente.setId_club(1);

        // Añadir el jugador existente a la lógica simulada de la conexión
        conexionMock.getJugadoresBd().add(jugadorExistente);

        // Crear un nuevo objeto con los datos modificados
        Jugador jugadorModificado = new Jugador();
        jugadorModificado.setId(1); // Mismo ID que el jugador existente
        jugadorModificado.setNombre("Lionel");
        jugadorModificado.setApellido("Messi");
        jugadorModificado.setanio_nacimiento("1987");
        jugadorModificado.setNacionalidad("Argentina");
        jugadorModificado.setPosicion("Delantero");
        jugadorModificado.setSalario(1500000.0); // Salario modificado
        jugadorModificado.setId_club(1);

        // Ejecución de la prueba
        controlador.modificarJugador(jugadorModificado.getId(), jugadorModificado.getNombre(), jugadorModificado.getApellido() , jugadorModificado.getanio_nacimiento(), jugadorModificado.get);

        // Verificación de los resultados
        assertEquals(1, conexionMock.getJugadoresSimulados().size());
        assertEquals(jugadorModificado, conexionMock.getJugadoresSimulados().get(0));
    }*/
    

    
    
    /**
     * Test of removeJugador method, of class Controlador.
     */
    @Test
    public void testRemoveJugador() throws Exception {
        System.out.println("removeJugador");

        ConexionMock conexionMock = new ConexionMock();
        Controlador controlador = new Controlador();
        controlador.configurarConexion(conexionMock);
        
        Jugador jugador = new Jugador();
        jugador.setId(1); // Supongamos que el jugador con ID 1 ya existe
        jugador.setNombre("Lionel");
        jugador.setApellido("Messi");
        jugador.setanio_nacimiento("1987");
        jugador.setNacionalidad("Argentina");
        jugador.setPosicion("Delantero");
        jugador.setSalario(1000000.0);
        jugador.setId_club(1);
        
        conexionMock.getJugadoresBd().add(jugador);
        
        controlador.removeJugador(Integer.toString(jugador.getId()));
        conexionMock.close();
        
        assertEquals(0, conexionMock.getJugadoresBd().size());
        
    }
    
        /**
     * Test of addEntrenador method, of class Controlador.
     */
    @Test
    public void testAddEntrenador() throws Exception {
        System.out.println("addEntrenador");
        ConexionMock conexionMock = new ConexionMock();
        Controlador controlador = new Controlador();
        controlador.configurarConexion(conexionMock);

        // Crear un entrenador para la prueba
        Entrenador entrenador = new Entrenador();
        entrenador.setId("0");
        entrenador.setNombre("Pep");
        entrenador.setApellido("Guardiola");
        entrenador.setAnio_nacimiento("1971");
        entrenador.setNacionalidad("España");

        // Ejecución de la prueba
        controlador.addEntrenador(entrenador.getNombre(), entrenador.getApellido(), entrenador.getAnio_nacimiento(), entrenador.getNacionalidad());
        conexionMock.close();

        // Verificación de los resultados
        assertEquals(1, conexionMock.getEntrenadoresBd().size());
    }
    
    /**
     * Test of removeEntrenador method, of class Controlador.
     */
    @Test
    public void testRemoveEntrenador() throws Exception {
        System.out.println("removeEntrenador");
        ConexionMock conexionMock = new ConexionMock();
        Controlador controlador = new Controlador();
        controlador.configurarConexion(conexionMock);

        // Crear un entrenador para la prueba
        Entrenador entrenador = new Entrenador();
        entrenador.setId("0");
        entrenador.setNombre("Pep");
        entrenador.setApellido("Guardiola");
        entrenador.setAnio_nacimiento("1971");
        entrenador.setNacionalidad("España");

        conexionMock.getEntrenadoresBd().add(entrenador);
        
        // Ejecución de la prueba
        controlador.removeEntrenador(entrenador.getId());
        conexionMock.close();

        // Verificación de los resultados
        assertEquals(0, conexionMock.getEntrenadoresBd().size());
    }
}
