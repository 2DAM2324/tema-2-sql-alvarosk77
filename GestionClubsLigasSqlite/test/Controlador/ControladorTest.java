/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Controlador;

import Modelo.Club;
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

        // Verificación de los resultados
        assertEquals(1, conexionMock.getJugadoresBd().size());
        assertEquals(jugador, conexionMock.getJugadoresBd().get(0));
    }
    
}
