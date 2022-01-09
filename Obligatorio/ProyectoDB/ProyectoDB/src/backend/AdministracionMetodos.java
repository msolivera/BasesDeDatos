/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import accesosBD.Configuracion;
import accesosBD.MetodoRW;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Agustin
 */
public class AdministracionMetodos {


    public static ArrayList<String> traerMetodosNoRelacionadosRol(int id_metodo) throws SQLException, ClassNotFoundException {

        ArrayList<String> resultado = new ArrayList<>();
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(MetodoRW.LISTAR_METODOS_NO_RELACIONADOS_ROL);
            stmt.setInt(1, id_metodo);
            ResultSet rs = stmt.executeQuery();
            
          while (rs.next()) {
                String valor = rs.getString("id_metodo") + "-" + rs.getString("descripcion");
                resultado.add(valor);

            }
            return resultado;
        }

    }

    public static ArrayList<String> traerMetodosNoRelacionadosMenu(int id_metodo) throws SQLException, ClassNotFoundException {
        ArrayList<String> resultado = new ArrayList<>();
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(MetodoRW.LISTAR_METODOS_NO_RELACIONADOS_MENU);
            stmt.setInt(1, id_metodo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String valor = rs.getString("id_metodo") + "-" + rs.getString("descripcion");
                resultado.add(valor);
            }
            return resultado;
        }

    }

    public static ArrayList<String> traerMetodosRelacionadosRol(int id_metodo) throws SQLException, ClassNotFoundException {
        ArrayList<String> resultado = new ArrayList<>();
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(MetodoRW.LISTAR_METODOS_RELACIONADOS_ROL);
            stmt.setInt(1, id_metodo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String valor = rs.getString("id_metodo") + "-" + rs.getString("descripcion");
                resultado.add(valor);
            }
            return resultado;
        }

    }

    public static ArrayList<String> traerMetodosRelacionadosMenu(int id_metodo) throws SQLException, ClassNotFoundException {
        ArrayList<String> resultado = new ArrayList<>();
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(MetodoRW.LISTAR_METODOS_RELACIONADOS_MENU);
            stmt.setInt(1, id_metodo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String valor = rs.getString("id_metodo") + "-" + rs.getString("descripcion");
                resultado.add(valor);
            }
            return resultado;
        }

    }

    public static void insertarMetodo(String nombre) throws SQLException, ClassNotFoundException {
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(MetodoRW.INSERTAR_METODO);
            stmt.setString(1, nombre);
            stmt.executeUpdate();
        }

    }

    public static void buscarMetodo(String id, String descripcion, JTable tabla) throws SQLException, ClassNotFoundException {
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(MetodoRW.filtrarMetodos(id, descripcion));
            stmt.setInt(1, Integer.parseInt(id));
            stmt.setString(2, descripcion);
            ResultSet rs = stmt.executeQuery();

            insertarDatos(tabla, rs);
        }
    }

    public static void cargarTablaMetodos(String id, String descripcion, JTable tabla) throws SQLException, ClassNotFoundException {
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(MetodoRW.filtrarMetodos(id, descripcion));
            ResultSet rs = stmt.executeQuery();
            insertarDatos(tabla, rs);
        }
    }


    public static void eliminarMetodo(String id, JTable tabla) {
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(MetodoRW.ELIMINAR_METODO);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AdministracionMetodos.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getMessage().contains("rol")) {
                JOptionPane.showMessageDialog(null, "La aplicacion no puede eliminarse aun porque existen roles asociados", "Error", 0);
            }
            if (ex.getMessage().contains("menu")) {
                JOptionPane.showMessageDialog(null, "La aplicacion no puede eliminarse aun porque existen menus asociados", "Error", 0);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministracionMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void modificarNombreMetodo(String nombre, String id) throws SQLException, ClassNotFoundException {
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(MetodoRW.MODIFICAR_DESCRIPCION);
            stmt.setString(1, nombre);
            stmt.setInt(2, Integer.parseInt(id));
            stmt.executeUpdate();
        }
    }


    private static void insertarDatos(JTable tabla, ResultSet rs) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);

        modelo.addColumn("id");
        modelo.addColumn("descripcion");

        while (rs.next()) {
            Object[] fila = new Object[2];
            for (int i = 0; i < 2; i++) {
                fila[i] = rs.getObject(i + 1);
            }
            modelo.addRow(fila);
        }
    }
    
        public static ArrayList<String> traerMetodoPorMenu(int id_Menu) throws SQLException, ClassNotFoundException {

        ArrayList<String> resultado = new ArrayList<>();
        try (Connection con = Configuracion.getConnection()) {
            String sql = "SELECT metodo.id_metodo, metodo.descripcion from metodo JOIN metodo_menu as memu on metodo.id_metodo = memu.id_metodo where memu.id_menu = " + String.valueOf(id_Menu); 
            PreparedStatement stmt = con.prepareStatement(sql);
            //stmt.setInt(1, id_Menu);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String valor = rs.getString("id_metodo") + "-" + rs.getString("descripcion");
                resultado.add(valor);

            }
            return resultado;
        }

    }
}
