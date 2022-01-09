/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesosBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class PersonaRW {

    public static final String INSERTAR_PERSONA = "INSERT INTO persona values (?,?,?,?,?,?)";

    public static final String GET_PERSONA = "SELECT * FROM persona where ci = ?";

    //public static String OBTENER_METODOS = "SELECT * FROM metodo";
    // public static String FILTRAR_METODOS = "SELECT * FROM metodo where id_metodo = ? or descripcion = ?";
    public static String ELIMINAR_PERSONA = "DELETE FROM persona where ci = ? AND ci not in (SELECT ci from usuario)";
    public static String EDITAR_PERSONA = "update persona set nombre = ? , apellido = ? , fecha_nac = ? , correo = ? , sexo = ? where ci = ?";

    public static String filtrarPersonas(String ci, String nombre, String apellido, String fecha, String correo, String sexo) {
        String where = "";
        String filtro = "";
        //si el id tiene algo, poner where id = ese_id
        if (!ci.equalsIgnoreCase("")) {
            filtro += " ci = " + ci + " or ";
        }
        if (!nombre.equalsIgnoreCase("")) {
            filtro += " nombre LIKE " + "'%" + nombre + "%' or ";
        }
        if (!apellido.equalsIgnoreCase("")) {
            filtro += " apellido LIKE " + "'%" + apellido + "%' or ";
        }
        if (!fecha.equalsIgnoreCase("")) {
            filtro += " fecha_nac LIKE " + "'%" + fecha + "%' or ";
        }
        if (!correo.equalsIgnoreCase("")) {
            filtro += " correo LIKE " + "'%" + correo + "%' or ";
        }
        if (!sexo.equalsIgnoreCase("")) {
            filtro += " sexo LIKE " + "'%" + sexo + "%' or ";
        }
        if (!filtro.equalsIgnoreCase("")) {
            where = "where";
            filtro = removeLast3Characters(filtro);

        }
        return "SELECT * FROM persona " + where + filtro;

    }

    public static String removeLast3Characters(String str) {
        String result = null;
            if ((str != null) && (str.length() > 0)) {
                result = str.substring(0, str.length() - 3);
            }
        return result;
    }

}
