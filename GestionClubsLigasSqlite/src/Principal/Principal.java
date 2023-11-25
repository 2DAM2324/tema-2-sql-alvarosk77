
package Principal;

import Interfaz.Ventana1;
import java.sql.Connection;
import Modelo.Conexion;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.NotSerializableException;
import java.sql.SQLException;
import org.xml.sax.SAXException;

/**
 *
 * @author alvar
 */
public class Principal {

    public static void main(String avgr[]) throws IOException, FileNotFoundException, ClassNotFoundException, NotSerializableException, SAXException, SQLException{
        
        
        Ventana1 prueba = new Ventana1();
        prueba.setVisible(true);
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Conexion.close();
        }));

    }
}
