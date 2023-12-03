/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Controlador.Controlador;
import Modelo.Club;
import Modelo.Entrenador;
import Modelo.Jugador;
import Modelo.Liga;
import Modelo.Patrocinador;
import java.awt.Color;
import java.awt.Font;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
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
import org.w3c.dom.Text;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Patricia Burgos
 */
public class Ventana1 extends javax.swing.JFrame {

    String id_jugador_seleccionado;
    String id_patrocinador_seleccionado;
    String id_entrenador_seleccionado;
    String id_club_seleccionado;
    String id_jugador_club_seleccionado;
    String id_patrocinador_club_seleccionado;
    String id_liga_seleccionada;
    String id_club_liga_seleccionada;
    
    
    /**
     * Creates new form Ventana1
     */
    public Ventana1() throws IOException, FileNotFoundException, ClassNotFoundException, NotSerializableException, SAXException, SQLException {
        initComponents();
        
        this.controller = new Controlador();
        
        this.inicializarTabJugadores();
        this.inicializarTabEntrenador();
        this.inicializarTabPatrocinador();
        this.inicializarTabClub();
        this.inicializarTabLiga();
    }
    
    public void inicializarTabJugadores(){
        this.jugadores = controller.getJugadores();
        
        table_model_jugador = (DefaultTableModel) jTable_jugador.getModel();
        
        jTable_jugador.setModel(table_model_jugador);
       
        for(Jugador jugador : jugadores) {
            Vector<Object> row = new Vector<Object>();

            table_model_jugador.addRow(new Object[]{jugador.getId(),jugador.getNombre(),jugador.getApellido(),jugador.getSalario(),jugador.getanio_nacimiento(),jugador.getNacionalidad(),jugador.getPosicion()});
        }
        
        jTable_jugador.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = jTable_jugador.getSelectedRow();
                System.out.println(selectedRow);
                if (selectedRow != -1) {
                    id_jugador_seleccionado = table_model_jugador.getValueAt(selectedRow,0).toString();
                    
                    jTextField_nombre_jugador.setText(table_model_jugador.getValueAt(selectedRow,1).toString());
                    jTextField_apellido_jugador.setText(table_model_jugador.getValueAt(selectedRow,2).toString());
                    jTextField_salario_jugador.setText(table_model_jugador.getValueAt(selectedRow,3).toString());
       
                }     
            }
        }

        });
        
    }
    
    public void inicializarTabEntrenador(){
        
        this.entrenadores = controller.getEntrenadores();
        
        table_model_entrenador = (DefaultTableModel) jTable_entrenadores.getModel();
        
        jTable_entrenadores.setModel(table_model_entrenador);
       
        for(Entrenador e : this.entrenadores) {
            Vector<Object> row = new Vector<Object>();

            table_model_entrenador.addRow(new Object[]{e.getId(),e.getNombre(),e.getApellido(),e.getAnio_nacimiento(),e.getNacionalidad()});
        }
        
        jTable_entrenadores.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = jTable_entrenadores.getSelectedRow();
                System.out.println(selectedRow);
                if (selectedRow != -1) {
                    id_entrenador_seleccionado = table_model_entrenador.getValueAt(selectedRow,0).toString();
                    
                    jTextField_nombre_entrenador.setText(table_model_entrenador.getValueAt(selectedRow,1).toString());
                    jTextField_apellido_entrenador.setText(table_model_entrenador.getValueAt(selectedRow,2).toString());
       
                }     
            }
        }

        });
    }
    
    public void mostrarTablaClubesDeUnPatrocinador(){
        
        try {
            for(int i = table_model_clubes_patrocinador.getRowCount()-1 ; i >= 0; i--){
                
                table_model_clubes_patrocinador.removeRow(i);
                
            }
            
            this.controller.cargarClubesPatrocinador(Integer.parseInt(id_patrocinador_seleccionado));
                
            this.clubes_patrocinador = this.controller.getClubes();
                
            
            table_model_clubes_patrocinador = (DefaultTableModel) jTableClubesPatrocinador.getModel();
            
            jTableClubesPatrocinador.setModel(table_model_clubes_patrocinador);
            
            for(Club c : this.clubes_patrocinador) {
                Vector<Object> row = new Vector<Object>();
                
                table_model_clubes_patrocinador.addRow(new Object[]{c.getId(),c.getNombre()});
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
        }
        
    }
    
    public void inicializarTabPatrocinador(){
        
        this.patrocinadores = controller.getPatrocinadores();
        
        table_model_patrocinador = (DefaultTableModel) jTable_patrocinador.getModel();
        
        jTable_patrocinador.setModel(table_model_patrocinador);
       
        for(Patrocinador p : this.patrocinadores) {
            Vector<Object> row = new Vector<Object>();

            table_model_patrocinador.addRow(new Object[]{p.getId_patrocinador(),p.getNombre_empresa(),p.getTipo_patrocinio(),p.getDuracion_contrato()});
        }
        
        jTable_patrocinador.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = jTable_patrocinador.getSelectedRow();
                System.out.println(selectedRow);
                if (selectedRow != -1) {
                    id_patrocinador_seleccionado = table_model_patrocinador.getValueAt(selectedRow,0).toString();
                    jTextField_nombre_empresa_patrocinador.setText(table_model_patrocinador.getValueAt(selectedRow,1).toString());
                    jTextField_duracion_contrato_patrocinador.setText(table_model_patrocinador.getValueAt(selectedRow,3).toString());
                    mostrarTablaClubesDeUnPatrocinador();
                }     
            }
        }

        });
    }
    
    public void mostrarTablaPatrocinadoresDeUnClub(){
        
        try {
            for(int i = table_model_patrocinadores_club.getRowCount()-1 ; i >= 0; i--){
                
                table_model_patrocinadores_club.removeRow(i);
                
            }
            
            this.controller.cargarPatrocinadoresClub(Integer.parseInt(id_club_seleccionado));
            
            this.patrocinadores_club = this.controller.getPatrocinadores();
            
            table_model_patrocinadores_club = (DefaultTableModel) jTablePatrocinadoresClub.getModel();
            
            jTablePatrocinadoresClub.setModel(table_model_patrocinadores_club);
            
            if(this.patrocinadores_club != null){
                
                for(Patrocinador p : this.patrocinadores_club) {     
                    Vector<Object> row = new Vector<Object>();
                    
                    table_model_patrocinadores_club.addRow(new Object[]{p.getId_patrocinador(),p.getNombre_empresa()});
                    
                    //p.mostrarDatos();
                }
                
                jTablePatrocinadoresClub.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            int selectedRow = jTablePatrocinadoresClub.getSelectedRow();
                            System.out.println(selectedRow);
                            if (selectedRow != -1) {
                                id_patrocinador_club_seleccionado = table_model_patrocinadores_club.getValueAt(selectedRow,0).toString();
                            }
                        }
                    }
                    
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
        }
    }
    
    public void inicializarTabClub(){
        
        
        JScrollPane scrollPa = new JScrollPane(jPanel_club);
        
        
        jTabbedPane.add(scrollPa);
        
        scrollPa.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        scrollPa.getVerticalScrollBar().setUnitIncrement(15);
        
        jTabbedPane.addTab("Club", null, scrollPa);
        
        
        this.clubes = controller.getClubes();
        
        table_model_club = (DefaultTableModel) jTable_clubes.getModel();
        
        jTable_clubes.setModel(table_model_club);
       
        for(Club c : this.clubes) {
            Vector<Object> row = new Vector<Object>();

            table_model_club.addRow(new Object[]{c.getId(),c.getNombre(),c.getAnio_fundacion(),c.getEntrenador().getId()});
        }
        
        this.actualizarJcomboxIdEntrenadores();
                
        this.actualizarJcomboxIdJugadores();
        
       
 
        jTable_clubes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = jTable_clubes.getSelectedRow();
                System.out.println(selectedRow);
                if (selectedRow != -1) {
                    id_club_seleccionado = table_model_club.getValueAt(selectedRow,0).toString();
                    jTextField_nombre_club.setText(table_model_club.getValueAt(selectedRow,1).toString());
                    
                    mostrarTablaJugadoresDeUnClub();
                    actualizarJcomboxIdPatrocinadores();
                    mostrarTablaPatrocinadoresDeUnClub();
                }     
            }
        }

        });
    }
    
    public void actualizarJcomboxIdJugadores(){
        
        try {
            jComboBox_idJugadores.removeAllItems();
            
            controller.cargarJugadoresLibresBd();
            
            this.jugadores = controller.getJugadores();
            
            for(Jugador j : this.jugadores){
                jComboBox_idJugadores.addItem(Integer.toString(j.getId()));
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
        }
    }
    
    public void actualizarJcomboxIdEntrenadores(){
        
        try {
            jComboBox_idEntrenadores.removeAllItems();
            
            controller.cargarEntrenadoresLibresBd();
            
            this.entrenadores = controller.getEntrenadores();
            
            for(Entrenador e : this.entrenadores){ 
                jComboBox_idEntrenadores.addItem(e.getId());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
        }
    }
    
    public void actualizarJcomboxIdPatrocinadores(){
        
        jComboBoxPatrocinadoresLibresClub.removeAllItems();
        
        if(id_club_seleccionado != null){
        
            try {
                controller.cargarPatrocinadoresLibresParaUnClub(Integer.parseInt(id_club_seleccionado));
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            }
        
        }
        
        this.patrocinadores = controller.getPatrocinadores();
        
        for(Patrocinador p : this.patrocinadores){
            jComboBoxPatrocinadoresLibresClub.addItem(p.getId_patrocinador()); 
        }
    }
    
    public void mostrarTablaJugadoresDeUnClub(){
        
        try {
            for(int i = table_model_jugadores_club.getRowCount()-1 ; i >= 0; i--){
                
                table_model_jugadores_club.removeRow(i);
                
            }
            
            controller.cargarJugadoresClub(Integer.parseInt(id_club_seleccionado));
            
            this.jugadores_club = controller.getJugadores();
            
            table_model_jugadores_club = (DefaultTableModel) jTableJugadoresClub.getModel();
            
            jTableJugadoresClub.setModel(table_model_jugadores_club);
            
            if(this.jugadores_club != null){
                     
                for(Jugador j : this.jugadores_club) {
                    Vector<Object> row = new Vector<Object>();
                    
                    table_model_jugadores_club.addRow(new Object[]{j.getId(),j.getNombre()});
                }
                
                
                jTableJugadoresClub.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            int selectedRow = jTableJugadoresClub.getSelectedRow();
                            System.out.println(selectedRow);
                            if (selectedRow != -1) {
                                id_jugador_club_seleccionado = table_model_jugadores_club.getValueAt(selectedRow,0).toString();
                            }
                        }
                    }
                    
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
        }
        
    }
    
    public void actualizarJcomboxIdClubes(){
        
        try {
            jComboBoxClubesLibres.removeAllItems();
            
            controller.cargarClubesLibres();
            
            this.clubes = controller.getClubes();
            
            for(Club c : this.clubes){
                jComboBoxClubesLibres.addItem(c.getId());
                
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
        }
    }
    
    public void mostrarTablaClubesDeUnaLiga(){
        
        try {
            for(int i = table_model_clubes_liga.getRowCount()-1 ; i >= 0; i--){
                
                table_model_clubes_liga.removeRow(i);
                
            }
            
            this.controller.cargarClubesLiga(Integer.parseInt(id_liga_seleccionada));
            
            this.clubes_liga = this.controller.getClubes();
            
            table_model_clubes_liga = (DefaultTableModel) jTableClubesLiga.getModel();
            
            jTableClubesLiga.setModel(table_model_clubes_liga);
            
            for(Club c : this.clubes_liga) {
                Vector<Object> row = new Vector<Object>();
                
                table_model_clubes_liga.addRow(new Object[]{c.getId(),c.getNombre()});
                
            }
            
            jTableClubesLiga.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        int selectedRow = jTableClubesLiga.getSelectedRow();
                        if (selectedRow != -1) {
                            id_club_liga_seleccionada = table_model_clubes_liga.getValueAt(selectedRow,0).toString();
                        }
                    }
                }
                    
            });
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
        }
    }
    
    public void inicializarTabLiga(){
        
        this.ligas = controller.getLigas();
        
        table_model_liga = (DefaultTableModel) jTable_liga.getModel();
        
        jTable_liga.setModel(table_model_liga);
       
        for(Liga l : this.ligas) {
            Vector<Object> row = new Vector<Object>();

            table_model_liga.addRow(new Object[]{l.getId(),l.getNombre(),l.getPais(),l.getTemporada()});
        }
        
        this.actualizarJcomboxIdClubes();
        
        jTable_liga.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = jTable_liga.getSelectedRow();
                System.out.println(selectedRow);
                if (selectedRow != -1) {
                    id_liga_seleccionada = table_model_liga.getValueAt(selectedRow,0).toString();
                    jTextField_nombre_liga.setText(table_model_liga.getValueAt(selectedRow,1).toString());
                    mostrarTablaClubesDeUnaLiga();
                }     
            }
        }

        });
    }
    
    public void clearFieldsJugadores(){
        jTextField_nombre_jugador.setText("");
        jTextField_apellido_jugador.setText("");
        jTextField_salario_jugador.setText("");
        
        
    }
    
    public void clearFieldsEntrenadores(){
        jTextField_nombre_entrenador.setText("");
        jTextField_apellido_entrenador.setText("");
    }
    
    public void clearFieldsPatrocinadores(){
        jTextField_nombre_empresa_patrocinador.setText("");
        jTextField_duracion_contrato_patrocinador.setText("");
    }
    
    public void clearFieldsClubes(){
        jTextField_nombre_club.setText("");
    }
    
    public void clearFieldsLigas(){
        jTextField_nombre_liga.setText("");
    }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();
        jPanel_liga = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_liga = new javax.swing.JTable();
        jLabel_telefono = new javax.swing.JLabel();
        jLabel_direccion = new javax.swing.JLabel();
        jTextField_nombre_liga = new javax.swing.JTextField();
        jButton_aniadir_liga = new javax.swing.JButton();
        jButton_modificar_liga = new javax.swing.JButton();
        jButton_borrar_liga = new javax.swing.JButton();
        jComboBox_pais_liga = new javax.swing.JComboBox<>();
        jLabel_ciudades_bibliotecas = new javax.swing.JLabel();
        jComboBox_temporada_liga = new javax.swing.JComboBox<>();
        jLabel_ciudades_bibliotecas1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableClubesLiga = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxClubesLibres = new javax.swing.JComboBox<>();
        jButtonAniadirClubLiga = new javax.swing.JButton();
        jButtonEliminarClubLiga = new javax.swing.JButton();
        jPanel_patrocinador = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable_patrocinador = new javax.swing.JTable();
        jLabel_telefono2 = new javax.swing.JLabel();
        jLabel_direccion2 = new javax.swing.JLabel();
        jTextField_nombre_empresa_patrocinador = new javax.swing.JTextField();
        jButton_aniadir_patrocinador = new javax.swing.JButton();
        jButton_modificar_biblioteca2 = new javax.swing.JButton();
        jButton_borrar_patrocinador = new javax.swing.JButton();
        jComboBox_tipo_patrocinio = new javax.swing.JComboBox<>();
        jLabel_ciudades_bibliotecas4 = new javax.swing.JLabel();
        jLabel_ciudades_bibliotecas5 = new javax.swing.JLabel();
        jTextField_duracion_contrato_patrocinador = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableClubesPatrocinador = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jPanel_entrenador = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable_entrenadores = new javax.swing.JTable();
        jLabel_telefono1 = new javax.swing.JLabel();
        jLabel_direccion1 = new javax.swing.JLabel();
        jTextField_nombre_entrenador = new javax.swing.JTextField();
        jButton_aniadir_entrenador = new javax.swing.JButton();
        jButton_modificar_entrenador = new javax.swing.JButton();
        jButton_borrar_entrenador = new javax.swing.JButton();
        jComboBox_anio_nacimiento_entrenador = new javax.swing.JComboBox<>();
        jLabel_ciudades_bibliotecas2 = new javax.swing.JLabel();
        jComboBox_nacionalidad_entrenador = new javax.swing.JComboBox<>();
        jLabel_ciudades_bibliotecas3 = new javax.swing.JLabel();
        jLabel_telefono3 = new javax.swing.JLabel();
        jTextField_apellido_entrenador = new javax.swing.JTextField();
        jPanel_club = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_clubes = new javax.swing.JTable();
        jLabel_autor = new javax.swing.JLabel();
        jLabel_biblioteca_libro = new javax.swing.JLabel();
        jComboBox_anio_funcdacion_club = new javax.swing.JComboBox<>();
        jTextField_nombre_club = new javax.swing.JTextField();
        jButton_borrar_club = new javax.swing.JButton();
        jButton_modificar_club = new javax.swing.JButton();
        jButton_aniadir_libro = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton_aniadir_jugador_club = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableJugadoresClub = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton_aniadir_patrocinador_club = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTablePatrocinadoresClub = new javax.swing.JTable();
        jButtonDespedirEntrenador = new javax.swing.JButton();
        jButtonDespedirJugador = new javax.swing.JButton();
        jButtonDespedirPatrocinador = new javax.swing.JButton();
        jComboBox_idJugadores = new javax.swing.JComboBox<>();
        jComboBox_idEntrenadores = new javax.swing.JComboBox<>();
        jComboBoxPatrocinadoresLibresClub = new javax.swing.JComboBox<>();
        jPanel_jugador = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_jugador = new javax.swing.JTable();
        jLabel_nombre_jugador = new javax.swing.JLabel();
        jLabel_salario_jugador = new javax.swing.JLabel();
        jLabel_fecha_nacimiento_jugador = new javax.swing.JLabel();
        jComboBox_fecha_nacimiento_jugador = new javax.swing.JComboBox<>();
        jTextField_salario_jugador = new javax.swing.JTextField();
        jButton_borrar_jugador = new javax.swing.JButton();
        jButton_modificar_jugador = new javax.swing.JButton();
        jButton_aniadir_jugador = new javax.swing.JButton();
        jTextField_nombre_jugador = new javax.swing.JTextField();
        jLabel_nombre_persona2 = new javax.swing.JLabel();
        jTextField_apellido_jugador = new javax.swing.JTextField();
        jComboBox_nacionalidad_jugador = new javax.swing.JComboBox<>();
        jLabel_nacionalidad_jugador = new javax.swing.JLabel();
        jLabel_posicion_jugador = new javax.swing.JLabel();
        jComboBox_posicion_jugador = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable_liga.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Pais", "Temporada"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable_liga);
        if (jTable_liga.getColumnModel().getColumnCount() > 0) {
            jTable_liga.getColumnModel().getColumn(0).setResizable(false);
            jTable_liga.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel_telefono.setText("Nombre:");

        jButton_aniadir_liga.setText("Añadir");
        jButton_aniadir_liga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_ligaActionPerformed(evt);
            }
        });

        jButton_modificar_liga.setText("Modificar");
        jButton_modificar_liga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_ligaActionPerformed(evt);
            }
        });

        jButton_borrar_liga.setText("Borrar");
        jButton_borrar_liga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_ligaActionPerformed(evt);
            }
        });

        jComboBox_pais_liga.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estadounidense", "Canadiense", "Mexicano", "Francés", "Alemán", "Español", "Italiano", "Británico", "Brasileño", "Argentino", "Japonés", "Chino", "Australiano", "Ruso", "Indio", "Sudafricano", "Sueco", "Noruego", "Griego", "Turco" }));

        jLabel_ciudades_bibliotecas.setText("Pais:");

        jComboBox_temporada_liga.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972" }));

        jLabel_ciudades_bibliotecas1.setText("Temporada:");

        jLabel8.setText("Clubes Liga");

        jTableClubesLiga.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane9.setViewportView(jTableClubesLiga);

        jLabel9.setText("Clubes Libres");

        jButtonAniadirClubLiga.setText("Añadir Club");
        jButtonAniadirClubLiga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAniadirClubLigaActionPerformed(evt);
            }
        });

        jButtonEliminarClubLiga.setText("Eliminar Club");
        jButtonEliminarClubLiga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarClubLigaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_ligaLayout = new javax.swing.GroupLayout(jPanel_liga);
        jPanel_liga.setLayout(jPanel_ligaLayout);
        jPanel_ligaLayout.setHorizontalGroup(
            jPanel_ligaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ligaLayout.createSequentialGroup()
                .addGroup(jPanel_ligaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ligaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_ligaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_liga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_liga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_liga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_ligaLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_ligaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_telefono)
                            .addComponent(jLabel_direccion)
                            .addComponent(jLabel_ciudades_bibliotecas)
                            .addComponent(jLabel_ciudades_bibliotecas1)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGroup(jPanel_ligaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel_ligaLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel_ligaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox_pais_liga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField_nombre_liga, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox_temporada_liga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ligaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxClubesLibres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonAniadirClubLiga)))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminarClubLiga))
                    .addGroup(jPanel_ligaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel_ligaLayout.setVerticalGroup(
            jPanel_ligaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ligaLayout.createSequentialGroup()
                .addGroup(jPanel_ligaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_ligaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_liga)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_liga, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_liga))
                    .addGroup(jPanel_ligaLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(71, 71, 71)
                .addGroup(jPanel_ligaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_telefono)
                    .addComponent(jTextField_nombre_liga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel_ligaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_ciudades_bibliotecas)
                    .addComponent(jComboBox_pais_liga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel_ligaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel_direccion)
                    .addGroup(jPanel_ligaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_ciudades_bibliotecas1)
                        .addComponent(jComboBox_temporada_liga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel_ligaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBoxClubesLibres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAniadirClubLiga)
                    .addComponent(jButtonEliminarClubLiga))
                .addGap(52, 52, 52)
                .addComponent(jLabel8)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(428, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Liga", jPanel_liga);

        jTable_patrocinador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre Empresa", "Tipo Patrocinio", "Duracion Contrato"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable_patrocinador);
        if (jTable_patrocinador.getColumnModel().getColumnCount() > 0) {
            jTable_patrocinador.getColumnModel().getColumn(0).setResizable(false);
            jTable_patrocinador.getColumnModel().getColumn(1).setResizable(false);
            jTable_patrocinador.getColumnModel().getColumn(2).setResizable(false);
            jTable_patrocinador.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel_telefono2.setText("Nombre Empresa:");

        jButton_aniadir_patrocinador.setText("Añadir");
        jButton_aniadir_patrocinador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_patrocinadorActionPerformed(evt);
            }
        });

        jButton_modificar_biblioteca2.setText("Modificar");
        jButton_modificar_biblioteca2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_biblioteca2ActionPerformed(evt);
            }
        });

        jButton_borrar_patrocinador.setText("Borrar");
        jButton_borrar_patrocinador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_patrocinadorActionPerformed(evt);
            }
        });

        jComboBox_tipo_patrocinio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Temporal", "Parcial" }));

        jLabel_ciudades_bibliotecas4.setText("Tipo de Patrocinio:");

        jLabel_ciudades_bibliotecas5.setText("Duracion Contrato:");

        jLabel1.setText("Semanas");

        jTableClubesPatrocinador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane8.setViewportView(jTableClubesPatrocinador);

        jLabel7.setText("Clubes Asociados a los patrocinadores");

        javax.swing.GroupLayout jPanel_patrocinadorLayout = new javax.swing.GroupLayout(jPanel_patrocinador);
        jPanel_patrocinador.setLayout(jPanel_patrocinadorLayout);
        jPanel_patrocinadorLayout.setHorizontalGroup(
            jPanel_patrocinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_patrocinadorLayout.createSequentialGroup()
                .addGroup(jPanel_patrocinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_patrocinadorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_patrocinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_biblioteca2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_patrocinador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_patrocinador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_patrocinadorLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_patrocinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_patrocinadorLayout.createSequentialGroup()
                                .addComponent(jLabel_ciudades_bibliotecas4)
                                .addGap(24, 24, 24)
                                .addComponent(jComboBox_tipo_patrocinio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel_patrocinadorLayout.createSequentialGroup()
                                .addGroup(jPanel_patrocinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel_patrocinadorLayout.createSequentialGroup()
                                        .addGroup(jPanel_patrocinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel_telefono2)
                                            .addComponent(jLabel_ciudades_bibliotecas5))
                                        .addGap(24, 24, 24)
                                        .addComponent(jTextField_nombre_empresa_patrocinador, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel_patrocinadorLayout.createSequentialGroup()
                                        .addGap(119, 119, 119)
                                        .addComponent(jTextField_duracion_contrato_patrocinador, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1))
                            .addComponent(jLabel_direccion2)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel_patrocinadorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel_patrocinadorLayout.setVerticalGroup(
            jPanel_patrocinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_patrocinadorLayout.createSequentialGroup()
                .addGroup(jPanel_patrocinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_patrocinadorLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_patrocinador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_biblioteca2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_patrocinador))
                    .addGroup(jPanel_patrocinadorLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel_patrocinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_telefono2)
                    .addComponent(jTextField_nombre_empresa_patrocinador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel_patrocinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_ciudades_bibliotecas4)
                    .addComponent(jComboBox_tipo_patrocinio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel_patrocinadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_ciudades_bibliotecas5)
                    .addComponent(jTextField_duracion_contrato_patrocinador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(50, 50, 50)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel_direccion2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(495, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Patrocinador", jPanel_patrocinador);

        jTable_entrenadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Apellido", "Año Nacimineto", "Nacionalidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable_entrenadores);
        if (jTable_entrenadores.getColumnModel().getColumnCount() > 0) {
            jTable_entrenadores.getColumnModel().getColumn(0).setResizable(false);
            jTable_entrenadores.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel_telefono1.setText("Nombre:");

        jTextField_nombre_entrenador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_nombre_entrenadorActionPerformed(evt);
            }
        });

        jButton_aniadir_entrenador.setText("Añadir");
        jButton_aniadir_entrenador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_entrenadorActionPerformed(evt);
            }
        });

        jButton_modificar_entrenador.setText("Modificar");
        jButton_modificar_entrenador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_entrenadorActionPerformed(evt);
            }
        });

        jButton_borrar_entrenador.setText("Borrar");
        jButton_borrar_entrenador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_entrenadorActionPerformed(evt);
            }
        });

        jComboBox_anio_nacimiento_entrenador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", " " }));

        jLabel_ciudades_bibliotecas2.setText("Año Nacimiento:");

        jComboBox_nacionalidad_entrenador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "brasil", "alemania", "Argentina", "italia", "francia", "España", "Países Bajos", "Uruguay", "inglaterra", "Portugal", "México", "Suecia", "Bélgica", "croacia", "Colombia", "Chile", "Nigeria", "Ghana", "Costa de Marfil", "Camerún", "rusia", "dinamarca", "Senegal", "Polonia", "Japon", "Corea del Sur", "Estados Unidos", "Turquía", "Grecia", "Paraguay", "Australia", "Suiza", "Rumania", "Hungría", "Austria", "Escocia", "irlanda", "Egipto", "marruecos", "Argelia", "Túnez", "Ghana", "Costa Rica", "Ecuador", "Serbia", "Montenegro", "Bosnia y Herzegovina", "Ucrania", "Eslovaquia", "Eslovenia" }));

        jLabel_ciudades_bibliotecas3.setText("Nacionalidad:");

        jLabel_telefono3.setText("Apellido:");

        javax.swing.GroupLayout jPanel_entrenadorLayout = new javax.swing.GroupLayout(jPanel_entrenador);
        jPanel_entrenador.setLayout(jPanel_entrenadorLayout);
        jPanel_entrenadorLayout.setHorizontalGroup(
            jPanel_entrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_entrenadorLayout.createSequentialGroup()
                .addGroup(jPanel_entrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_entrenadorLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel_entrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel_ciudades_bibliotecas3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_ciudades_bibliotecas2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel_entrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel_direccion1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel_entrenadorLayout.createSequentialGroup()
                                    .addGap(118, 118, 118)
                                    .addGroup(jPanel_entrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTextField_nombre_entrenador, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel_entrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox_anio_nacimiento_entrenador, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField_apellido_entrenador, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox_nacionalidad_entrenador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel_telefono3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_telefono1, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel_entrenadorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_entrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_entrenador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_entrenador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_entrenador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel_entrenadorLayout.setVerticalGroup(
            jPanel_entrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_entrenadorLayout.createSequentialGroup()
                .addGroup(jPanel_entrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_entrenadorLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_entrenador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_entrenador, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_entrenador))
                    .addGroup(jPanel_entrenadorLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(jPanel_entrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_telefono1)
                    .addComponent(jTextField_nombre_entrenador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel_entrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_telefono3)
                    .addComponent(jTextField_apellido_entrenador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel_entrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel_entrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_ciudades_bibliotecas3)
                        .addComponent(jComboBox_nacionalidad_entrenador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel_entrenadorLayout.createSequentialGroup()
                        .addGroup(jPanel_entrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_ciudades_bibliotecas2)
                            .addComponent(jComboBox_anio_nacimiento_entrenador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)))
                .addComponent(jLabel_direccion1)
                .addContainerGap(664, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Entrenador", jPanel_entrenador);

        jTable_clubes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Año Fundacion", "Id_entrenador"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable_clubes);
        if (jTable_clubes.getColumnModel().getColumnCount() > 0) {
            jTable_clubes.getColumnModel().getColumn(0).setResizable(false);
            jTable_clubes.getColumnModel().getColumn(1).setResizable(false);
            jTable_clubes.getColumnModel().getColumn(2).setResizable(false);
            jTable_clubes.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel_autor.setText("Nombre:");

        jLabel_biblioteca_libro.setText("Año Fundacion:");

        jComboBox_anio_funcdacion_club.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920" }));

        jButton_borrar_club.setText("Borrar");
        jButton_borrar_club.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_clubActionPerformed(evt);
            }
        });

        jButton_modificar_club.setText("Modificar");
        jButton_modificar_club.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_clubActionPerformed(evt);
            }
        });

        jButton_aniadir_libro.setText("Añadir");
        jButton_aniadir_libro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_libroActionPerformed(evt);
            }
        });

        jLabel2.setText("Entrenadores Libres");

        jButton1.setText("Contratar Entrenador");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Jugadores Libres");

        jButton_aniadir_jugador_club.setText("Contratar Jugador");
        jButton_aniadir_jugador_club.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_jugador_clubActionPerformed(evt);
            }
        });

        jTableJugadoresClub.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre"
            }
        ));
        jScrollPane1.setViewportView(jTableJugadoresClub);

        jLabel4.setText("Jugadores Club Seleccionado");

        jLabel5.setText("Patrocinadores libres para el club seleccionado:");

        jButton_aniadir_patrocinador_club.setText("Contratar Patrocinador");
        jButton_aniadir_patrocinador_club.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_patrocinador_clubActionPerformed(evt);
            }
        });

        jLabel6.setText("Patrocinadores Club Seleccionado");

        jTablePatrocinadoresClub.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre"
            }
        ));
        jScrollPane7.setViewportView(jTablePatrocinadoresClub);

        jButtonDespedirEntrenador.setText("Despedir Entrenador");
        jButtonDespedirEntrenador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDespedirEntrenadorActionPerformed(evt);
            }
        });

        jButtonDespedirJugador.setText("Despedir Jugador");
        jButtonDespedirJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDespedirJugadorActionPerformed(evt);
            }
        });

        jButtonDespedirPatrocinador.setText("Despedir Patrocinador");
        jButtonDespedirPatrocinador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDespedirPatrocinadorActionPerformed(evt);
            }
        });

        jComboBox_idJugadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_idJugadoresActionPerformed(evt);
            }
        });

        jComboBox_idEntrenadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_idEntrenadoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_clubLayout = new javax.swing.GroupLayout(jPanel_club);
        jPanel_club.setLayout(jPanel_clubLayout);
        jPanel_clubLayout.setHorizontalGroup(
            jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_clubLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_clubLayout.createSequentialGroup()
                        .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_clubLayout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton_modificar_club, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton_aniadir_libro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton_borrar_club, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(24, 67, Short.MAX_VALUE))
                    .addGroup(jPanel_clubLayout.createSequentialGroup()
                        .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_clubLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel_clubLayout.createSequentialGroup()
                                        .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addGroup(jPanel_clubLayout.createSequentialGroup()
                                                .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel_autor)
                                                    .addComponent(jLabel_biblioteca_libro)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel3))
                                                .addGap(29, 29, 29)
                                                .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jComboBox_anio_funcdacion_club, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextField_nombre_club, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jComboBox_idEntrenadores, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jComboBox_idJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jComboBoxPatrocinadoresLibresClub, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(54, 54, 54)
                                        .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButtonDespedirEntrenador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton_aniadir_jugador_club, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButtonDespedirJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel_clubLayout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jButtonDespedirPatrocinador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jButton_aniadir_patrocinador_club, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                    .addComponent(jLabel6))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel_clubLayout.setVerticalGroup(
            jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_clubLayout.createSequentialGroup()
                .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_clubLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_libro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_club, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_club))
                    .addGroup(jPanel_clubLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32)
                .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_autor)
                    .addComponent(jTextField_nombre_club, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_anio_funcdacion_club, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_biblioteca_libro))
                .addGap(26, 26, 26)
                .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(jComboBox_idEntrenadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonDespedirEntrenador)
                .addGap(18, 18, 18)
                .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jButton_aniadir_jugador_club)
                    .addComponent(jComboBox_idJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(jButtonDespedirJugador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jButton_aniadir_patrocinador_club))
                .addGap(18, 18, 18)
                .addGroup(jPanel_clubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDespedirPatrocinador)
                    .addComponent(jComboBoxPatrocinadoresLibresClub, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane.addTab("Club", jPanel_club);

        jTable_jugador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Apellido", "Salario", "Fecha Nacimiento", "Nacionalidad", "Posicion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable_jugador);
        if (jTable_jugador.getColumnModel().getColumnCount() > 0) {
            jTable_jugador.getColumnModel().getColumn(0).setResizable(false);
            jTable_jugador.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel_nombre_jugador.setText("Nombre:");

        jLabel_salario_jugador.setText("Salario:");

        jLabel_fecha_nacimiento_jugador.setText("Fecha Nacimiento:");

        jComboBox_fecha_nacimiento_jugador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", " " }));

        jButton_borrar_jugador.setText("Borrar");
        jButton_borrar_jugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_borrar_jugadorActionPerformed(evt);
            }
        });

        jButton_modificar_jugador.setText("Modificar");
        jButton_modificar_jugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_modificar_jugadorActionPerformed(evt);
            }
        });

        jButton_aniadir_jugador.setText("Añadir");
        jButton_aniadir_jugador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_aniadir_jugadorMouseClicked(evt);
            }
        });
        jButton_aniadir_jugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_aniadir_jugadorActionPerformed(evt);
            }
        });

        jLabel_nombre_persona2.setText("Apellido:");

        jTextField_apellido_jugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_apellido_jugadorActionPerformed(evt);
            }
        });

        jComboBox_nacionalidad_jugador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "brasil", "alemania", "Argentina", "italia", "francia", "España", "Países Bajos", "Uruguay", "inglaterra", "Portugal", "México", "Suecia", "Bélgica", "croacia", "Colombia", "Chile", "Nigeria", "Ghana", "Costa de Marfil", "Camerún", "rusia", "dinamarca", "Senegal", "Polonia", "Japon", "Corea del Sur", "Estados Unidos", "Turquía", "Grecia", "Paraguay", "Australia", "Suiza", "Rumania", "Hungría", "Austria", "Escocia", "irlanda", "Egipto", "marruecos", "Argelia", "Túnez", "Ghana", "Costa Rica", "Ecuador", "Serbia", "Montenegro", "Bosnia y Herzegovina", "Ucrania", "Eslovaquia", "Eslovenia" }));

        jLabel_nacionalidad_jugador.setText("Nacionalidad:");

        jLabel_posicion_jugador.setText("Posicion:");

        jComboBox_posicion_jugador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "POR", "DFC", "LD", "LI", "DC", "MC", "MI", "ED", "EI", "MCD" }));

        javax.swing.GroupLayout jPanel_jugadorLayout = new javax.swing.GroupLayout(jPanel_jugador);
        jPanel_jugador.setLayout(jPanel_jugadorLayout);
        jPanel_jugadorLayout.setHorizontalGroup(
            jPanel_jugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_jugadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_jugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_jugadorLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_jugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton_modificar_jugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_aniadir_jugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton_borrar_jugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel_jugadorLayout.createSequentialGroup()
                        .addGroup(jPanel_jugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_nombre_jugador)
                            .addComponent(jLabel_salario_jugador)
                            .addComponent(jLabel_fecha_nacimiento_jugador)
                            .addComponent(jLabel_nombre_persona2)
                            .addComponent(jLabel_nacionalidad_jugador)
                            .addComponent(jLabel_posicion_jugador))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel_jugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox_fecha_nacimiento_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_jugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField_salario_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_nombre_jugador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField_apellido_jugador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBox_nacionalidad_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox_posicion_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel_jugadorLayout.setVerticalGroup(
            jPanel_jugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_jugadorLayout.createSequentialGroup()
                .addGroup(jPanel_jugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_jugadorLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton_aniadir_jugador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_modificar_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_borrar_jugador))
                    .addGroup(jPanel_jugadorLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel_jugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_nombre_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_nombre_jugador))
                .addGap(18, 18, 18)
                .addGroup(jPanel_jugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_apellido_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_nombre_persona2))
                .addGap(26, 26, 26)
                .addGroup(jPanel_jugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_salario_jugador)
                    .addComponent(jTextField_salario_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel_jugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_fecha_nacimiento_jugador)
                    .addComponent(jComboBox_fecha_nacimiento_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel_jugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_nacionalidad_jugador)
                    .addComponent(jComboBox_nacionalidad_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel_jugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_posicion_jugador)
                    .addComponent(jComboBox_posicion_jugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(561, Short.MAX_VALUE))
        );

        jTabbedPane.addTab("Jugador", jPanel_jugador);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane)
        );

        jTabbedPane.getAccessibleContext().setAccessibleName("Ciudad");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_nombre_entrenadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_nombre_entrenadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nombre_entrenadorActionPerformed

    private void jButton_aniadir_entrenadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aniadir_entrenadorActionPerformed
        try {
            // TODO add your handling code here:
            controller.addEntrenador(jTextField_nombre_entrenador.getText(), jTextField_apellido_entrenador.getText(), jComboBox_anio_nacimiento_entrenador.getSelectedItem().toString(),jComboBox_nacionalidad_entrenador.getSelectedItem().toString());
            this.actualizarVistaClubes();
            this.actualizarVistaEntrenadores();
            this.clearFieldsEntrenadores();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
        }
    }//GEN-LAST:event_jButton_aniadir_entrenadorActionPerformed

    private void jButton_borrar_entrenadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrar_entrenadorActionPerformed
        // TODO add your handling code here
        if(id_entrenador_seleccionado != null){
            try {
                controller.removeEntrenador(id_entrenador_seleccionado);
                this.actualizarVistaClubes();
                this.actualizarVistaEntrenadores();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            System.out.println("error");
        }
        this.clearFieldsEntrenadores();
    }//GEN-LAST:event_jButton_borrar_entrenadorActionPerformed

    private void jButton_modificar_entrenadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificar_entrenadorActionPerformed
        // TODO add your handling code here:
        if(id_entrenador_seleccionado != null){
            try {
                controller.modificarEntrenador(id_entrenador_seleccionado,jTextField_nombre_entrenador.getText(), jTextField_apellido_entrenador.getText(), jComboBox_anio_nacimiento_entrenador.getSelectedItem().toString(), jComboBox_nacionalidad_entrenador.getSelectedItem().toString());
                this.actualizarVistaEntrenadores();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            System.out.println("error");
        }
        this.clearFieldsEntrenadores();
    }//GEN-LAST:event_jButton_modificar_entrenadorActionPerformed

    private void jButton_aniadir_patrocinadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aniadir_patrocinadorActionPerformed
        // TODO add your handling code here:
        if(Integer.parseInt(jTextField_duracion_contrato_patrocinador.getText()) > 0){
            try {
                controller.addPatrocinador(jTextField_nombre_empresa_patrocinador.getText(), jComboBox_tipo_patrocinio.getSelectedItem().toString(), Integer.parseInt(jTextField_duracion_contrato_patrocinador.getText()));
                this.actualizarVistaPatrocinadores();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
            
        }
        
        this.clearFieldsPatrocinadores();
    }//GEN-LAST:event_jButton_aniadir_patrocinadorActionPerformed

    private void jButton_aniadir_ligaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aniadir_ligaActionPerformed
        try {
            // TODO add your handling code here:
            this.controller.addLiga(jTextField_nombre_liga.getText(), jComboBox_pais_liga.getSelectedItem().toString(), Integer.parseInt(jComboBox_temporada_liga.getSelectedItem().toString()));
            this.actualizarVistaLigas();
            
            this.clearFieldsLigas();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
        }
    }//GEN-LAST:event_jButton_aniadir_ligaActionPerformed

    private void jButton_borrar_patrocinadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrar_patrocinadorActionPerformed
        // TODO add your handling code here:
        
        if(id_patrocinador_seleccionado != null){
            try {
                controller.removePatrocinador(id_patrocinador_seleccionado);
                this.actualizarVistaPatrocinadores();
                //this.mostrarTablaPatrocinadoresDeUnClub();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            
            System.out.println("error");
        }
        
        this.clearFieldsPatrocinadores();
    }//GEN-LAST:event_jButton_borrar_patrocinadorActionPerformed

    private void jButton_modificar_biblioteca2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificar_biblioteca2ActionPerformed
        // TODO add your handling code here:
        if(id_patrocinador_seleccionado != null){
            try {
                controller.modificarPatrocinador(id_patrocinador_seleccionado, jTextField_nombre_empresa_patrocinador.getText(), jComboBox_tipo_patrocinio.getSelectedItem().toString(), Integer.parseInt(jTextField_duracion_contrato_patrocinador.getText()));
                this.actualizarVistaPatrocinadores();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        
        }
        else{
            
            System.out.println("error");
            
        }
        
        this.clearFieldsPatrocinadores();
    }//GEN-LAST:event_jButton_modificar_biblioteca2ActionPerformed

    private void jButton_aniadir_libroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aniadir_libroActionPerformed
        try {
            // TODO add your handling code here:
            controller.addClub(jTextField_nombre_club.getText(), Integer.parseInt(jComboBox_anio_funcdacion_club.getSelectedItem().toString()));
            this.actualizarVistaClubes();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
        }
        
    }//GEN-LAST:event_jButton_aniadir_libroActionPerformed

    private void jButton_borrar_clubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrar_clubActionPerformed
        // TODO add your handling code here:
        if(id_club_seleccionado != null){
            try {
                controller.removeClub(id_club_seleccionado);
                this.actualizarVistaClubes();
                this.mostrarTablaJugadoresDeUnClub();
                this.mostrarTablaPatrocinadoresDeUnClub();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            System.out.println("error");
        }
        this.clearFieldsClubes();
    }//GEN-LAST:event_jButton_borrar_clubActionPerformed

    private void jButton_modificar_clubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificar_clubActionPerformed
        // TODO add your handling code here:
        if(id_club_seleccionado != null){
            try {
                controller.modificarClub(id_club_seleccionado, jTextField_nombre_club.getText(), Integer.parseInt(jComboBox_anio_funcdacion_club.getSelectedItem().toString()));
                this.actualizarVistaClubes();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            System.out.println("error");
        }
        this.clearFieldsClubes();
    }//GEN-LAST:event_jButton_modificar_clubActionPerformed

    private void jButton_borrar_ligaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrar_ligaActionPerformed
        // TODO add your handling code here:
        if(id_liga_seleccionada != null){
            try {
                this.controller.removeLiga(id_liga_seleccionada);
                this.actualizarVistaLigas();
                this.mostrarTablaClubesDeUnaLiga();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            System.out.println("error");
        }
        this.clearFieldsLigas();
    }//GEN-LAST:event_jButton_borrar_ligaActionPerformed

    private void jButton_modificar_ligaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificar_ligaActionPerformed
        // TODO add your handling code here:
        if(id_liga_seleccionada != null){
            try {
                this.controller.modificarLiga(id_liga_seleccionada, jTextField_nombre_liga.getText(), jComboBox_pais_liga.getSelectedItem().toString(), Integer.parseInt(jComboBox_temporada_liga.getSelectedItem().toString()));
                this.actualizarVistaLigas();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            System.out.println("error");
        }
        this.clearFieldsLigas();
    }//GEN-LAST:event_jButton_modificar_ligaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(id_club_seleccionado != null){
            try {
                this.controller.addEntrenadorClub(Integer.parseInt(id_club_seleccionado), Integer.parseInt(jComboBox_idEntrenadores.getSelectedItem().toString()));
                this.actualizarVistaClubes();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
            
        }
        else{
            
            System.out.println("error");
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton_aniadir_jugador_clubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aniadir_jugador_clubActionPerformed
        // TODO add your handling code here:
        if(id_club_seleccionado != null){
            try {
                controller.addJugadorClub(Integer.parseInt( id_club_seleccionado), Integer.parseInt(jComboBox_idJugadores.getSelectedItem().toString()));
                this.actualizarVistaClubes();
                this.mostrarTablaJugadoresDeUnClub();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            System.out.println("error");
        }

    }//GEN-LAST:event_jButton_aniadir_jugador_clubActionPerformed

    private void jButton_aniadir_patrocinador_clubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aniadir_patrocinador_clubActionPerformed
        // TODO add your handling code here:
        if(id_club_seleccionado != null){
            try {
                controller.addPatrocinadorClub(id_club_seleccionado, jComboBoxPatrocinadoresLibresClub.getSelectedItem().toString());
                this.actualizarJcomboxIdPatrocinadores();
                this.mostrarTablaPatrocinadoresDeUnClub();
                this.mostrarTablaClubesDeUnPatrocinador();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            System.out.println("error");
        }
    }//GEN-LAST:event_jButton_aniadir_patrocinador_clubActionPerformed

    private void jTextField_apellido_jugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_apellido_jugadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_apellido_jugadorActionPerformed

    private void jButton_aniadir_jugadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_aniadir_jugadorMouseClicked
        // TODO add your handling code here:
        
        if(Double.parseDouble(jTextField_salario_jugador.getText()) > 0.0){
            try {
                controller.addJugador(jTextField_nombre_jugador.getText(),jTextField_apellido_jugador.getText() ,jComboBox_fecha_nacimiento_jugador.getSelectedItem().toString(), jComboBox_nacionalidad_jugador.getSelectedItem().toString(), jComboBox_posicion_jugador.getSelectedItem().toString(),Double.parseDouble(jTextField_salario_jugador.getText()));
                this.actualizarVistaJugadores();
                this.actualizarVistaClubes();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            this.clearFieldsJugadores();
        }        
        
    }//GEN-LAST:event_jButton_aniadir_jugadorMouseClicked

    private void jButton_modificar_jugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_modificar_jugadorActionPerformed
        // TODO add your handling code here:

        
        if((id_jugador_seleccionado != null)){
            try {
                controller.modificarJugador( Integer.parseInt(id_jugador_seleccionado),jTextField_nombre_jugador.getText(), jTextField_apellido_jugador.getText(), Double.parseDouble(jTextField_salario_jugador.getText()), jComboBox_fecha_nacimiento_jugador.getSelectedItem().toString(), jComboBox_nacionalidad_jugador.getSelectedItem().toString(), jComboBox_posicion_jugador.getSelectedItem().toString());
                this.actualizarVistaJugadores();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            this.clearFieldsJugadores();
        }

    }//GEN-LAST:event_jButton_modificar_jugadorActionPerformed

    private void jButton_borrar_jugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_borrar_jugadorActionPerformed
        // TODO add your handling code here:
        if(id_jugador_seleccionado != null){
            try {
                controller.removeJugador(id_jugador_seleccionado);
                
                this.actualizarVistaJugadores();
                this.actualizarVistaClubes();
                
                //this.mostrarTablaJugadoresDeUnClub();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            System.out.println("error");
        }
        this.clearFieldsJugadores();
    }//GEN-LAST:event_jButton_borrar_jugadorActionPerformed

    private void jButtonDespedirEntrenadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDespedirEntrenadorActionPerformed
        // TODO add your handling code here:
        if(id_club_seleccionado != null){
            try {
                controller.despedirEntrenadorClub(Integer.parseInt(id_club_seleccionado));
                
                this.actualizarVistaClubes();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            System.err.println("error");
        }
        this.clearFieldsClubes();
    }//GEN-LAST:event_jButtonDespedirEntrenadorActionPerformed

    private void jButtonDespedirJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDespedirJugadorActionPerformed
        // TODO add your handling code here:
        if(id_club_seleccionado != null && id_jugador_club_seleccionado != null){
            try {
                controller.despedirJugadorClub(Integer.parseInt(id_jugador_club_seleccionado));
                this.actualizarVistaClubes();
                this.mostrarTablaJugadoresDeUnClub();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            System.err.println("error");
        }
        
    }//GEN-LAST:event_jButtonDespedirJugadorActionPerformed

    private void jButtonDespedirPatrocinadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDespedirPatrocinadorActionPerformed
        // TODO add your handling code here
        if(id_club_seleccionado != null && id_patrocinador_club_seleccionado != null){
            try {
                controller.despedirPatrocinadorClub(id_club_seleccionado, id_patrocinador_club_seleccionado);
                this.actualizarJcomboxIdPatrocinadores();
                this.mostrarTablaPatrocinadoresDeUnClub();
                this.mostrarTablaClubesDeUnPatrocinador();
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            System.err.println("error");
        }
    }//GEN-LAST:event_jButtonDespedirPatrocinadorActionPerformed

    private void jButton_aniadir_jugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_aniadir_jugadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_aniadir_jugadorActionPerformed

    private void jComboBox_idJugadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_idJugadoresActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox_idJugadoresActionPerformed

    private void jComboBox_idEntrenadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_idEntrenadoresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_idEntrenadoresActionPerformed

    private void jButtonAniadirClubLigaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAniadirClubLigaActionPerformed
        // TODO add your handling code here:
        if(id_liga_seleccionada != null){
            try {
                this.controller.addClubLiga(id_liga_seleccionada, jComboBoxClubesLibres.getSelectedItem().toString());
                this.actualizarVistaLigas();
                this.mostrarTablaClubesDeUnaLiga();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            System.out.println("error");
        }
    }//GEN-LAST:event_jButtonAniadirClubLigaActionPerformed

    private void jButtonEliminarClubLigaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarClubLigaActionPerformed
        // TODO add your handling code here:
        if(id_liga_seleccionada != null && id_club_liga_seleccionada != null){
            try {
                //controller.despedirJugadorClub(Integer.parseInt(id_jugador_club_seleccionado));
                //this.actualizarVistaClubes();
                //this.mostrarTablaJugadoresDeUnClub();
                this.controller.eliminarClubLiga(Integer.parseInt(id_club_liga_seleccionada));
                this.actualizarVistaLigas();
                this.mostrarTablaClubesDeUnaLiga();
            } catch (SQLException ex) {
                Logger.getLogger(Ventana1.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
            }
        }
        else{
            System.err.println("error");
        }
    }//GEN-LAST:event_jButtonEliminarClubLigaActionPerformed
    
    
    
    public void actualizarVistaJugadores(){
        
        try {
            for(int i = table_model_jugador.getRowCount()-1 ; i >= 0; i--){
                
                table_model_jugador.removeRow(i);
                
            }
            
            controller.CargarTablasBaseDatos();
            
            this.jugadores = controller.getJugadores();
            
            for(Jugador jugador : this.jugadores) {
                Vector<Object> row = new Vector<Object>();
                
                table_model_jugador.addRow(new Object[]{jugador.getId(),jugador.getNombre(),jugador.getApellido(),jugador.getSalario(),jugador.getanio_nacimiento(),jugador.getNacionalidad(),jugador.getPosicion()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
        }
        
    }
    
    public void actualizarVistaEntrenadores(){
        
        try {
            for(int i = table_model_entrenador.getRowCount()-1 ; i >= 0; i--){
                
                table_model_entrenador.removeRow(i);
                
            }
            
            this.controller.CargarTablasBaseDatos();
            
            this.entrenadores = controller.getEntrenadores();
            
            
            for(Entrenador e : this.entrenadores) {
                Vector<Object> row = new Vector<Object>();
                
                table_model_entrenador.addRow(new Object[]{e.getId(),e.getNombre(),e.getApellido(),e.getAnio_nacimiento(),e.getNacionalidad()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
        }
        
    }
    
    public void actualizarVistaPatrocinadores(){
        
        try {
            for(int i = table_model_patrocinador.getRowCount()-1 ; i >= 0; i--){
                
                table_model_patrocinador.removeRow(i);
                
            }
            
            this.controller.CargarTablasBaseDatos();
            
            this.patrocinadores = controller.getPatrocinadores();
            
            
            for(Patrocinador p : this.patrocinadores) {
                Vector<Object> row = new Vector<Object>();
                
                table_model_patrocinador.addRow(new Object[]{p.getId_patrocinador(),p.getNombre_empresa(),p.getTipo_patrocinio(),p.getDuracion_contrato()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
        }
        
    }
    
    public void actualizarVistaClubes(){
        
        try {
            
            for(int i = table_model_club.getRowCount()-1 ; i >= 0; i--){
                
                table_model_club.removeRow(i);
                
            }
            
            this.controller.CargarTablasBaseDatos();
            
            this.clubes = controller.getClubes();
            
            this.actualizarJcomboxIdEntrenadores();
            
            this.actualizarJcomboxIdJugadores();
            
            this.actualizarJcomboxIdPatrocinadores();
            
            
            for(Club c : this.clubes) {
                
                Vector<Object> row = new Vector<Object>();
                
                table_model_club.addRow(new Object[]{c.getId(),c.getNombre(),c.getAnio_fundacion(),c.getEntrenador().getId()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
        }
        
    }
    
    public void actualizarVistaLigas(){
        
        try {
            for(int i = table_model_liga.getRowCount()-1 ; i >= 0; i--){
                
                table_model_liga.removeRow(i);
                
            }
            
            this.controller.CargarTablasBaseDatos();
            
            this.ligas = controller.getLigas();
            
            this.actualizarJcomboxIdClubes();
            
            for(Liga l : this.ligas) {
                Vector<Object> row = new Vector<Object>();
                
                table_model_liga.addRow(new Object[]{l.getId(),l.getNombre(),l.getPais(),l.getTemporada()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error de excepcion sql: No se encontro la clase");
        }
        
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAniadirClubLiga;
    private javax.swing.JButton jButtonDespedirEntrenador;
    private javax.swing.JButton jButtonDespedirJugador;
    private javax.swing.JButton jButtonDespedirPatrocinador;
    private javax.swing.JButton jButtonEliminarClubLiga;
    private javax.swing.JButton jButton_aniadir_entrenador;
    public javax.swing.JButton jButton_aniadir_jugador;
    private javax.swing.JButton jButton_aniadir_jugador_club;
    private javax.swing.JButton jButton_aniadir_libro;
    private javax.swing.JButton jButton_aniadir_liga;
    private javax.swing.JButton jButton_aniadir_patrocinador;
    private javax.swing.JButton jButton_aniadir_patrocinador_club;
    private javax.swing.JButton jButton_borrar_club;
    private javax.swing.JButton jButton_borrar_entrenador;
    public javax.swing.JButton jButton_borrar_jugador;
    private javax.swing.JButton jButton_borrar_liga;
    private javax.swing.JButton jButton_borrar_patrocinador;
    private javax.swing.JButton jButton_modificar_biblioteca2;
    private javax.swing.JButton jButton_modificar_club;
    private javax.swing.JButton jButton_modificar_entrenador;
    public javax.swing.JButton jButton_modificar_jugador;
    private javax.swing.JButton jButton_modificar_liga;
    private javax.swing.JComboBox<String> jComboBoxClubesLibres;
    private javax.swing.JComboBox<String> jComboBoxPatrocinadoresLibresClub;
    public javax.swing.JComboBox<String> jComboBox_anio_funcdacion_club;
    public javax.swing.JComboBox<String> jComboBox_anio_nacimiento_entrenador;
    private javax.swing.JComboBox<String> jComboBox_fecha_nacimiento_jugador;
    private javax.swing.JComboBox<String> jComboBox_idEntrenadores;
    private javax.swing.JComboBox<String> jComboBox_idJugadores;
    public javax.swing.JComboBox<String> jComboBox_nacionalidad_entrenador;
    private javax.swing.JComboBox<String> jComboBox_nacionalidad_jugador;
    public javax.swing.JComboBox<String> jComboBox_pais_liga;
    private javax.swing.JComboBox<String> jComboBox_posicion_jugador;
    public javax.swing.JComboBox<String> jComboBox_temporada_liga;
    public javax.swing.JComboBox<String> jComboBox_tipo_patrocinio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_autor;
    private javax.swing.JLabel jLabel_biblioteca_libro;
    private javax.swing.JLabel jLabel_ciudades_bibliotecas;
    private javax.swing.JLabel jLabel_ciudades_bibliotecas1;
    private javax.swing.JLabel jLabel_ciudades_bibliotecas2;
    private javax.swing.JLabel jLabel_ciudades_bibliotecas3;
    private javax.swing.JLabel jLabel_ciudades_bibliotecas4;
    private javax.swing.JLabel jLabel_ciudades_bibliotecas5;
    private javax.swing.JLabel jLabel_direccion;
    private javax.swing.JLabel jLabel_direccion1;
    private javax.swing.JLabel jLabel_direccion2;
    private javax.swing.JLabel jLabel_fecha_nacimiento_jugador;
    private javax.swing.JLabel jLabel_nacionalidad_jugador;
    private javax.swing.JLabel jLabel_nombre_jugador;
    private javax.swing.JLabel jLabel_nombre_persona2;
    private javax.swing.JLabel jLabel_posicion_jugador;
    private javax.swing.JLabel jLabel_salario_jugador;
    private javax.swing.JLabel jLabel_telefono;
    private javax.swing.JLabel jLabel_telefono1;
    private javax.swing.JLabel jLabel_telefono2;
    private javax.swing.JLabel jLabel_telefono3;
    private javax.swing.JPanel jPanel_club;
    private javax.swing.JPanel jPanel_entrenador;
    private javax.swing.JPanel jPanel_jugador;
    private javax.swing.JPanel jPanel_liga;
    private javax.swing.JPanel jPanel_patrocinador;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTableClubesLiga;
    private javax.swing.JTable jTableClubesPatrocinador;
    private javax.swing.JTable jTableJugadoresClub;
    private javax.swing.JTable jTablePatrocinadoresClub;
    private javax.swing.JTable jTable_clubes;
    private javax.swing.JTable jTable_entrenadores;
    public javax.swing.JTable jTable_jugador;
    private javax.swing.JTable jTable_liga;
    private javax.swing.JTable jTable_patrocinador;
    public javax.swing.JTextField jTextField_apellido_entrenador;
    private javax.swing.JTextField jTextField_apellido_jugador;
    public javax.swing.JTextField jTextField_duracion_contrato_patrocinador;
    private javax.swing.JTextField jTextField_nombre_club;
    public javax.swing.JTextField jTextField_nombre_empresa_patrocinador;
    public javax.swing.JTextField jTextField_nombre_entrenador;
    private javax.swing.JTextField jTextField_nombre_jugador;
    public javax.swing.JTextField jTextField_nombre_liga;
    private javax.swing.JTextField jTextField_salario_jugador;
    // End of variables declaration//GEN-END:variables
    private Controlador controller;
 
    
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private DefaultTableModel table_model_jugador = new DefaultTableModel();
    
    private ArrayList<Entrenador> entrenadores = new ArrayList<>();
    private DefaultTableModel table_model_entrenador = new DefaultTableModel();
    
    private ArrayList<Patrocinador> patrocinadores = new ArrayList<>();
    private DefaultTableModel table_model_patrocinador = new DefaultTableModel();
    
    private ArrayList<Club> clubes = new ArrayList<>();
    private DefaultTableModel table_model_club = new DefaultTableModel();
    
    private ArrayList<Jugador> jugadores_club = new ArrayList<>();
    private DefaultTableModel table_model_jugadores_club = new DefaultTableModel();
    
    private ArrayList<Patrocinador> patrocinadores_club = new ArrayList<>();
    private DefaultTableModel table_model_patrocinadores_club = new DefaultTableModel();
    
    private ArrayList<Club> clubes_patrocinador = new ArrayList<>();
    private DefaultTableModel table_model_clubes_patrocinador = new DefaultTableModel();
    
    private ArrayList<Liga> ligas = new ArrayList<>();
    private DefaultTableModel table_model_liga = new DefaultTableModel();
    
    private ArrayList<Club> clubes_liga = new ArrayList<>();
    private DefaultTableModel table_model_clubes_liga = new DefaultTableModel();
    

}
