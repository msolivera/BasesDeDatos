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
public class Persona {
    
    protected int cedula;
    public String nombre;
    public String apellido;
    protected String fechaNac;
    public String sexo;

    public Persona(int cedula, String nombre, String apellido, String fechaNac, String sexo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.sexo = sexo;
    }

    public int getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public String getSexo() {
        return sexo;
    }
    
    
    
    
}
