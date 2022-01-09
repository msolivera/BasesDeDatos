/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesosBD;

/**
 *
 * @author Agustin
 */
public class MetodoRW {
    public static String INSERTAR_METODO = "INSERT into metodo (descripcion) values (?)";
    public static String OBTENER_METODOS = "SELECT * FROM metodo";
    public static String FILTRAR_METODOS = "SELECT * FROM metodo where id_metodo = ? or descripcion = ?";
    public static String ELIMINAR_METODO = "DELETE FROM metodo where id_metodo = ?";
    public static String MODIFICAR_DESCRIPCION = "update metodo set descripcion = ? where id_metodo = ?";
    public static String LISTAR_METODOS_NO_RELACIONADOS_ROL = "SELECT * FROM metodo WHERE id_metodo NOT IN (SELECT id_metodo FROM rol_metodo WHERE id_rol = ?)";
    public static String LISTAR_METODOS_NO_RELACIONADOS_MENU = "SELECT * FROM metodo WHERE id_metodo NOT IN (SELECT id_metodo FROM metodo_menu WHERE id_menu = ?)";
    public static String LISTAR_METODOS_RELACIONADOS_ROL = "SELECT * FROM metodo WHERE id_metodo IN (SELECT id_metodo FROM rol_metodo WHERE id_rol = ?)";
    public static String LISTAR_METODOS_RELACIONADOS_MENU = "SELECT * FROM metodo WHERE id_metodo IN (SELECT id_metodo FROM metodo_menu WHERE id_menu = ?)";
    public static String GET_METODO_POR_MENU = "SELECT metodo.id_metodo, metodo.descripcion from metodo JOIN metodo_menu as memu on metodo.id_metodo = memu.id_metodo where memu.id_menu = ?";

    public static String filtrarMetodos(String id, String descripcion) {
        String where = "";
        //si el id tiene algo, poner where id = ese_id
        if (!id.equalsIgnoreCase("")) {
            where = "where id_metodo = " + id;
            if (!descripcion.equalsIgnoreCase("")) {
                where = where + " or descripcion LIKE " + "'%" + descripcion + "%'";
            }
        } else {
            if (!descripcion.equalsIgnoreCase("")) {
                where = "where descripcion LIKE " + "'%" + descripcion + "%'";
            }
        }
        return "SELECT * FROM metodo " + where;
    }

}
