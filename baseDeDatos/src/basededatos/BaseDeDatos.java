/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 *
 * @author Meki
 */
public class BaseDeDatos {
    
     // Constantes
    public static final String CONN_SQL = "jdbc:postgresql://%s:%s/%s";
    public static final int PUERTO_DEFECTO = 5432;
    public static final int INICIO_COLUMNA = 1;
    public static final String FORMATO_FECHA_HORA = "yyyy-MM-dd HH:mm:ss";

    // Conexión activa actual a la base de datos
    private Connection conexion;

    // Nombre/IP del servidor
    private String servidor;

    // Puerto de la base de datos
    private int puerto;

    // Nombre de la base de datos conectada
    private String nombre_bd;

    // Nombre de usuario de la base de datos
    private String usuario;

    // Contraseña de la base de datos
    private String contrasenia;
    
    /**
     * Constructor por defecto de la clase
     */
    public BaseDeDatos() {
    }

    /**
     * Retorna servidor actual
     *
     * @return servidor actual
     */
    public String getServidor() {
        return this.servidor;
    }

    /**
     * Retorna puerto actual
     *
     * @return puerto actual
     */
    public int getPuerto() {
        return this.puerto;
    }

    /**
     * Retorna nombre base de datos actual
     *
     * @return nombre base de datos actual
     */
    public String getNombre_bd() {
        return this.nombre_bd;
    }

    /**
     * Retorna usuario actual
     *
     * @return usuario actual
     */
    public String getUsuario() {
        return this.usuario;
    }

    /**
     * Retorna contraseña actual
     *
     * @return contraseña actual
     */
    public String getContrasenia() {
        return this.contrasenia;
    }

     /**
     * Retorna una lista de las columnas del resultado de una query
     *
     * @param resultado resultado query
     * @return lista de columnas
     */
    public ArrayList<String> columnasQuery(ResultSet resultado) {
        int totalColumnas;
        ResultSetMetaData metadata;
        ArrayList<String> columnas = new ArrayList<>();

        try {
            metadata = resultado.getMetaData();
            totalColumnas = metadata.getColumnCount();

            for (int c = INICIO_COLUMNA; c <= totalColumnas; c++) {
                columnas.add(metadata.getColumnName(c));
            }

        } catch (SQLException ex) {
        }

        return columnas;
    }

    /**
     * Retorna una lista con datos del resultado de una query
     *
     * @param resultado
     * @return lista de datos
     */
    public ArrayList<ArrayList<String>> columnasDatos(ResultSet resultado) {
        int totalColumnas, totalDatos;
        ResultSetMetaData metadata;
        ArrayList<ArrayList<String>> datos = new ArrayList<>();
        ArrayList<String> dato;

        try {
            metadata = resultado.getMetaData();
            totalColumnas = metadata.getColumnCount();
            totalDatos = 0;

            // Recorro datos agregando a lista auxiliar
            while (resultado.next()) {
                totalDatos++;
                dato = new ArrayList<>();
                for (int c = INICIO_COLUMNA; c <= totalColumnas; c++) {
                    dato.add(resultado.getString(metadata.getColumnName(c)));
                }
                datos.add(dato);
            }

        } catch (SQLException ex) {
        }

        return datos;
    }

    /**
     * Ejecuta un query y retorna sus resultados
     *
     * @param sentencia sentencia a ejecutar
     * @return tipo set de resultados de PostgreSQL
     */
    public ResultSet query(String sentencia) throws SQLException {
        Statement declaracion;
        
        if (!this.estaConectado()) {
            return null;
        }
        
        try {
           
         declaracion = this.conexion.createStatement();
        return declaracion.executeQuery(sentencia);   
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return null;
        
    }

    /**
     * Desconecta el gestor
     */
    public void desconectar() {
        if (this.conexion != null) {
            try {
                this.conexion.close();
            } catch (SQLException e) {
            }
        }
    }

    /**
     * Conecta el gestor a una base de datos
     *
     * @param nombreServidor nombre/IP del servidor
     * @param nombreBaseDeDatos nombre de la base de datos
     * @param usuario usuario de la base de datos
     * @param contrasenia contraseña de la base de datos
     * @return true si conectó correctamente, de lo contrario retorna false
     */
    public boolean conectar(String nombreServidor, String nombreBaseDeDatos, String usuario, String contrasenia) {
        // Finalizo conexión existente si ya existe
        if (this.estaConectado()) {
            this.desconectar();
        }

        // Inicio y almaceno una nueva conexión
        try {
            this.conexion = DriverManager.getConnection(String.format(CONN_SQL, nombreServidor, PUERTO_DEFECTO, nombreBaseDeDatos), usuario, contrasenia);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retorna si el gestor está conectado y la conexión es válida
     *
     * @return true si está conectado y la conexión es válida
     */
    public boolean estaConectado() {
        if (this.conexion == null) {
            return false;
        }

        // Reviso si la conexión es válida
        try {
            return this.conexion.isValid(0);
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Retorna fecha y hora actual como texto
     *
     * @return fecha y hora actual
     */
    public String fechaHoraActual() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA_HORA);

        return sdf.format(cal.getTime());
    }

  
    
    
   
}
