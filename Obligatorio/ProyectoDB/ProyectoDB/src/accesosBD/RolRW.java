/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesosBD;

/**
 *
 * @author Meki
 */
public class RolRW {

    public static String INSERTAR_ROL = "INSERT INTO rol (descripcion, id_aplicacion) values(?,?)";
    public static final String GET_ROL = "SELECT * FROM rol ";
    public static String FILTRAR_ROL_ID = "SELECT * FROM rol where id_rol = ?";
    public static String FILTRAR_ROL_DESCRIPCION = "SELECT * FROM rol where descripcion like ?";
    public static String FILTRAR_ROL = "SELECT * FROM rol where id_rol = ? or descripcion = ?";
    public static String ELIMINAR_ROL = "DELETE FROM rol where id_rol = ?";
    public static String EDITAR_ROL = "update rol set descripcion = ? where id_rol = ?";
    public static String INSERTAR_ROL_METODO = "INSERT INTO public.rol_metodo(id_rol, id_metodo)VALUES (?, ?)";
    public static String ELIMINAR_ROL_METODO = "DELETE FROM rol_metodo WHERE id_rol = ? AND id_metodo = ?";
    public static String GET_ID_ROL = "SELECT id_rol from rol WHERE descripcion = ?";
    public static String GET_ROL_POR_APP = "SELECT id_rol, descripcion from rol WHERE id_aplicacion = ?";
    public static String DESCRIPCION_DE_UN_ROL = "SELECT descripcion from rol WHERE id_rol = ?";
    public static String ELIMINAR_ROL_MENU = "DELETE FROM rol_menu WHERE id_rol = ? AND id_menu = ?";
    public static String INSERTAR_ROL_MENU = "INSERT INTO rol_menu(id_rol, id_menu)VALUES (?, ?)";
}
