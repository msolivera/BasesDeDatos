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
public class MenuRW {

    public static final String GET_MENU = "SELECT * FROM menu ";
    public static String EDITAR_MENU = "update menu set descripcion = ? where id_menu = ?";
    public static String INSERTAR_MENU_METODO = "INSERT INTO metodo_menu(id_metodo, id_menu)VALUES (?, ?)";
    public static String ELIMINAR_MENU_METODO = "DELETE FROM metodo_menu WHERE id_metodo = ? AND id_menu = ?";
    public static String FILTRAR_MENU_ID = "SELECT * FROM menu where id_menu = ?";
    public static String FILTRAR_MENU_ROL = "SELECT descripcion FROM menu JOIN rol_menu as roles on menu.id_menu = roles.id_menu where roles.id_rol = ?";
    public static String FILTRAR_MENU_DESCRIPCION = "SELECT * FROM menu where descripcion like ?";
    public static String GET_MENU_ID = "SELECT id_menu FROM menu where descripcion like ' ? '";
    public static String FILTRAR_MENU = "SELECT * FROM menu where id_menu = ? or descripcion = ?";
    public static String ELIMINAR_MENU = "DELETE FROM menu where id_menu = ?";
    public static String INSERTAR_MENU = "INSERT INTO menu (descripcion) values(?)";
    public static String LISTAR_MENUS_NO_RELACIONADOS_ROL = "SELECT * FROM menu WHERE id_menu NOT IN (SELECT id_menu FROM rol_menu WHERE id_rol = ?)";
    public static String LISTAR_MENUS_RELACIONADOS_ROL = "SELECT * FROM menu WHERE id_menu IN (SELECT id_menu FROM rol_menu WHERE id_rol = ?)";

}
