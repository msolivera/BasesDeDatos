/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import accesosBD.Configuracion;
import accesosBD.PersonaRW;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class AdministracionPersonas {

    public static boolean personaExiste(String ci) throws SQLException, ClassNotFoundException {
        Connection con = Configuracion.getConnection();
        String sql = PersonaRW.GET_PERSONA;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, Integer.parseInt(ci));
        ResultSet rs = stmt.executeQuery();
        return rs.next();
    }

    public static void insertarPersona(String nombre, String apellido, String documento,
            String email, String fechaNacimiento, String sexo) throws SQLException, ClassNotFoundException {
        Connection con = Configuracion.getConnection();
        String sql = PersonaRW.INSERTAR_PERSONA;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, Integer.valueOf(documento));
        stmt.setString(2, nombre);
        stmt.setString(3, apellido);
        stmt.setString(4, fechaNacimiento);
        stmt.setString(5, email);
        stmt.setString(6, sexo);
        stmt.executeUpdate();
    }

    public static void editarPersona(String nombre, String apellido,
            String fechaNacimiento, String email, String sexo, String ci) throws SQLException, ClassNotFoundException {
        Connection con = Configuracion.getConnection();
        String sql = PersonaRW.EDITAR_PERSONA;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nombre);
        stmt.setString(2, apellido);
        stmt.setString(3, fechaNacimiento);
        stmt.setString(4, email);
        stmt.setString(5, sexo);
        stmt.setInt(6, Integer.parseInt(ci));
        stmt.executeUpdate();
    }

    public static void eliminarPersona(String ci) throws SQLException, ClassNotFoundException {
        Connection con = Configuracion.getConnection();
        String sql = PersonaRW.ELIMINAR_PERSONA;
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, Integer.valueOf(ci));
        stmt.executeUpdate();
        
    }

    public static void cargarTablaPersonas(String ci, String nombre, String apellido, String fecha, String correo, String sexo, JTable tabla) throws SQLException, ClassNotFoundException {
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(PersonaRW.filtrarPersonas(ci, nombre, apellido, fecha, correo, sexo));
            ResultSet rs = stmt.executeQuery();
            insertarDatos(tabla, rs);
        }
    }

    private static void insertarDatos(JTable tabla, ResultSet rs) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);

        modelo.addColumn("ci");
        modelo.addColumn("nombre");
        modelo.addColumn("apellido");
        modelo.addColumn("fecha Nacimiento");
        modelo.addColumn("correo");
        modelo.addColumn("sexo");

        while (rs.next()) {
            Object[] fila = new Object[6];
            for (int i = 0; i < 6; i++) {
                fila[i] = rs.getObject(i + 1);
            }
            modelo.addRow(fila);
        }
    }

    public static boolean esCIValida(String ci) {

        if (ci.length() != 7 && ci.length() != 8) {
            return false;
        } else {
            try {
                Integer.parseInt(ci);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        int digVerificador = Integer.parseInt((ci.charAt(ci.length() - 1)) + "");
        int[] factores;
        if (ci.length() == 7) { // CI viejas
            factores = new int[]{9, 8, 7, 6, 3, 4};
        } else {
            factores = new int[]{2, 9, 8, 7, 6, 3, 4};
        }
        int suma = 0;
        for (int i = 0; i < ci.length()-1; i++) {
            int digito = Integer.parseInt(ci.charAt(i) + "");
            suma += digito * factores[i];
        }
        int resto = suma % 10;
        int checkdigit = 10 - resto;

        if (checkdigit == 10) {
            return (digVerificador == 0);
        } else {
            return (checkdigit == digVerificador);
        }
    }
}
