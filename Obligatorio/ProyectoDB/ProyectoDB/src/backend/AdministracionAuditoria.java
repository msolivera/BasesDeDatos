/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import accesosBD.AplicacionRW;
import accesosBD.AuditoriaRW;
import accesosBD.Configuracion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class AdministracionAuditoria {

    public static void cargarTodos(JTable tabla) throws SQLException, ClassNotFoundException {
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(AuditoriaRW.GET_AUDITORIA);
            ResultSet rs = stmt.executeQuery();
            insertarDatos(tabla, rs);
        }
    }

    public static void filtrarAuditoria(JTable tabla, String fecha, String usuarioS,
            String descripcion, String usuarioA, String rolA) throws SQLException, ClassNotFoundException, ParseException {
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(AuditoriaRW.FILTRAR_AUDITORIA);
            if (fecha.equals("")) {
                fecha = "1000-01-01";
            }

            Date date = Date.valueOf(fecha);//converting string into sql date.
            stmt.setDate(1, date);
            stmt.setString(2, usuarioS);
            stmt.setString(3, descripcion);
            stmt.setString(4, usuarioA);
            if (rolA.equals("")) {
                stmt.setInt(5, 0);
            } else {
                stmt.setInt(5, Integer.valueOf(rolA));
            }

            ResultSet rs = stmt.executeQuery();
            insertarDatos(tabla, rs);
        }
    }

    public static void agregarAuditoria(String usuarioS,
            String descripcion, String usuarioA, Integer rolA) throws SQLException, ClassNotFoundException {
        try (Connection con = Configuracion.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(AuditoriaRW.INSERTAR_FILA);
            stmt.setString(1, usuarioS);
            stmt.setInt(2, Integer.valueOf(descripcion));
            stmt.setString(3, usuarioA);
            if (rolA == null) {
                stmt.setNull(4, 0);
            } else {
                stmt.setInt(4, rolA);
            }

            stmt.executeUpdate();
        }
    }

    private static void insertarDatos(JTable tabla, ResultSet rs) throws SQLException {
        DefaultTableModel modelo = new DefaultTableModel();
        tabla.setModel(modelo);

        modelo.addColumn("Fecha");
        modelo.addColumn("Usuario Solicitante");
        modelo.addColumn("Descripcion");
        modelo.addColumn("Usuario afectado");
        modelo.addColumn("Rol afectado");

        while (rs.next()) {
            Object[] fila = new Object[5];
            for (int i = 0; i <= 4; i++) {
                fila[i] = rs.getObject(i + 2);
            }
            modelo.addRow(fila);
        }
    }

}
