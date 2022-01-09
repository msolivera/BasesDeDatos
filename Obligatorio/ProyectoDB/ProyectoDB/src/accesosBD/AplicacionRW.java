/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesosBD;

/**
 *
 * @author Usuario
 */
public class AplicacionRW {
    
    public static String INSERTAR_APLICACION = "INSERT into aplicacion (nombre) values (?)";
    public static String OBTENER_APLICACIONES = "SELECT * FROM aplicacion";
    public static String FILTRAR_APLICACIONES = "SELECT * FROM aplicacion where id_aplicacion = ? or nombre = ?";
    public static String ELIMINAR_APLICACION = "DELETE FROM aplicacion where id_aplicacion = ?";
    public static String MODIFICAR_NOMBRE = "update aplicacion set nombre = ? where id_aplicacion = ?";
    public static String OBTENER_MENUS_ASOCIADOS = "SELECT * FROM menus_en_aplicacion WHERE id_aplicacion = ?";
    public static String ELIMINAR_MENU_ASOCIADO = "DELETE FROM   menu_aplicacion  where id_aplicacion = ? and id_menu = ?";
    public static String OBTENER_MENUS_NO_ASOCIADOS = " select * FROM   menu m where id_menu not in (select id_menu from menu_aplicacion where id_aplicacion = ?)";
    public static String AGREGAR_MENU = "INSERT into menu_aplicacion values (?,?)";
    public static String NOMBRE_APLICACIONES = "SELECT nombre FROM aplicacion";
    public static String ID_APLICACIONES = "SELECT id_aplicacion FROM aplicacion where nombre= ?";
    public static String ID_APLICACIONES_ROL = "SELECT nombre FROM aplicacion where id_aplicacion IN (SELECT id_aplicacion FROM rol WHERE id_rol = ?)";
    
        public static String filtrarAplicaciones(String id, String nombre) {
        String where = "";
        //si el id tiene algo, poner where id = ese_id
        if (!id.equalsIgnoreCase("")) {
            where = "where id_aplicacion = " + id;
            if (!nombre.equalsIgnoreCase("")) {
                where = where + " or nombre LIKE " + "'%" + nombre + "%'";
            }
        } else {
            if (!nombre.equalsIgnoreCase("")) {
                where = "where nombre LIKE " + "'%" + nombre + "%'";
            }
        }
        return "SELECT * FROM aplicacion " + where;
    }
    public static String ROLES_EN_APLICACION = "SELECT * FROM rol_en_aplicacion where id_aplicacion = ?";
    public static String ROLES_SIN_APLICACION = "SELECT * FROM rol where id_aplicacion is null";
    public static String QUITAR_ROL = "update rol set id_aplicacion = null where id_rol = ?";
    public static String AGREGAR_ROL = "update rol set id_aplicacion = ? where id_rol = ?";

}
