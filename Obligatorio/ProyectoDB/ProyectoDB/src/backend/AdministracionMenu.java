/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import accesosBD.Configuracion;
import accesosBD.MenuRW;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Meki
 */
public class AdministracionMenu {

    public static void cargarMenu(JTable tabla) throws SQLException, ClassNotFoundException {
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(MenuRW.GET_MENU);
            ResultSet rs = stmt.executeQuery();

            insertarDatos(tabla, rs);
        }
    }

    private static void insertarDatos(JTable tabla, ResultSet rs) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);

        modelo.addColumn("ID");
        modelo.addColumn("DESCRIPCION");

        while (rs.next()) {
            Object[] fila = new Object[2];
            for (int i = 0; i < 2; i++) {
                fila[i] = rs.getObject(i + 1);
            }
            modelo.addRow(fila);
        }
    }

    public static void editarMenu(int id, String desc) throws SQLException, ClassNotFoundException {
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(MenuRW.EDITAR_MENU);
            stmt.setString(1, desc);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public static void insertarMenuMetodo(int idMetodo, int idMenu) throws SQLException {
        Connection con = DriverManager.getConnection(Configuracion.getURL(), Configuracion.getUsuario(), Configuracion.getPassword());
        String sql = MenuRW.INSERTAR_MENU_METODO;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, idMetodo);
        stmt.setInt(2, idMenu);

        stmt.executeUpdate();
    }

    public static void eliminarMenuMetodo(int idMetodo, int idMenu) throws SQLException, ClassNotFoundException {
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(MenuRW.ELIMINAR_MENU_METODO);
            stmt.setInt(1, idMetodo);
            stmt.setInt(2, idMenu);
            stmt.executeUpdate();
        }
    }

    public static void buscarMenu(int id, String descripcion, JTable tabla) throws SQLException, ClassNotFoundException {

        try (Connection con = Configuracion.getConnection()) {
            if (descripcion == null) {
                PreparedStatement stmt = con.prepareStatement(MenuRW.FILTRAR_MENU_ID);
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                insertarDatos(tabla, rs);
            } else if (id == -1) {

                PreparedStatement stmt = con.prepareStatement(MenuRW.FILTRAR_MENU_DESCRIPCION);
                stmt.setString(1, '%' + descripcion + '%');
                ResultSet rs = stmt.executeQuery();
                insertarDatos(tabla, rs);

            } else {
                PreparedStatement stmt = con.prepareStatement(MenuRW.FILTRAR_MENU);
                stmt.setInt(1, id);
                stmt.setString(2, descripcion);
                ResultSet rs = stmt.executeQuery();
                insertarDatos(tabla, rs);
            }
        }
    }

    public static void eliminarMenu(int id, JTable tabla) throws SQLException, ClassNotFoundException {
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(MenuRW.ELIMINAR_MENU);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public static void insertarMenu(String descripcion) throws SQLException {
        Connection con = DriverManager.getConnection(Configuracion.getURL(), Configuracion.getUsuario(), Configuracion.getPassword());
        String sql = MenuRW.INSERTAR_MENU;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, descripcion);

        stmt.executeUpdate();
    }

    public static ArrayList<String> llenar_combo(int idRol) throws SQLException, ClassNotFoundException {
        ArrayList<String> resultado = new ArrayList<>();
        Connection con = Configuracion.getConnection();
        String sql = "SELECT descripcion FROM menu JOIN rol_menu as roles on menu.id_menu = roles.id_menu where roles.id_rol =" + idRol;
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            resultado.add(rs.getString(1));
        }
        return resultado;
    }

    public static int getIdMenu(String nombre) throws SQLException, ClassNotFoundException {
        int idMenu = 0;
        try (Connection con = Configuracion.getConnection()) {
            String sql = "SELECT id_menu FROM menu where descripcion = '" + nombre + "'";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                idMenu = rs.getInt("id_menu");
            }
            return idMenu;
        }

    }

    public static ArrayList<String> traerMenusNoRelacionadosRol(int id_menu) throws SQLException, ClassNotFoundException {
        ArrayList<String> resultado = new ArrayList<>();
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(MenuRW.LISTAR_MENUS_NO_RELACIONADOS_ROL);
            stmt.setInt(1, id_menu);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String valor = rs.getString("id_menu") + "-" + rs.getString("descripcion");
                resultado.add(valor);

            }
            return resultado;
        }

    }

    public static ArrayList<String> traerMenuRelacionadosRol(int id_menu) throws SQLException, ClassNotFoundException {
        ArrayList<String> resultado = new ArrayList<>();
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(MenuRW.LISTAR_MENUS_RELACIONADOS_ROL);
            stmt.setInt(1, id_menu);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String valor = rs.getString("id_menu") + "-" + rs.getString("descripcion");
                resultado.add(valor);
            }
            return resultado;
        }

    }

}
