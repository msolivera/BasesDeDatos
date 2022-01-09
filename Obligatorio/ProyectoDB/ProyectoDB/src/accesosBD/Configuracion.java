/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesosBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author Usuario
 */
public class Configuracion {
    
    public static String usuario;
    public static int ci;
    
    //para franco
    
    //private static final String USUARIO_DB  = "root";
    //private static final String PASSWORD_DB  = "120894";
    //private static final String URL_DB  = "jdbc:mysql://localhost:3306/proyectofinal?characterEncoding=latin1&useConfigs=maxPerformance";
   
    
    //para agustin
    //private static final String USUARIO_DB  = "postgres";
    //private static final String PASSWORD_DB  = "passwd";
    //private static final String URL_DB = "jdbc:postgresql://192.168.132.128:5432/proyectoFinal";
    
//    CONFIG DE MICA
    private static final String USUARIO_DB  = "postgres";
    private static final String PASSWORD_DB  = "52304220";
    private static final String URL_DB  = "jdbc:postgresql://192.168.56.101:5432/proyectoSeguridad";
    
    public static String getUsuario(){
        return USUARIO_DB;
    }
    
    public static String getPassword(){
        return PASSWORD_DB;
    }
    
    public static String getURL(){
        return URL_DB;
    }
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
       //Class.forName("com.mysql.jdbc.Driver"); //esto solo para franco quitar el resto
      Class.forName("org.postgresql.Driver");//esto es para agustin y el resto de los mortales
       Connection con = (Connection)DriverManager.getConnection(Configuracion.getURL(), Configuracion.getUsuario(), Configuracion.getPassword()); 
       return con;
    }
}
