/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectodb.clases;

/**
 *
 * @author Micaela
 */
public class Menu {
 
    protected int idMenu;
    public String descripcion;

    public Menu(int idMenu, String descripcion) {
        this.idMenu = idMenu;
        this.descripcion = descripcion;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public String getDescripcion() {
        return descripcion;
    }
    
}
