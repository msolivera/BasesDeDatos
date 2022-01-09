/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectodb.clases;
import org.apache.commons.codec.digest.DigestUtils;
/**
 *
 * @author Micaela
 */
public class Usuario {
    
   public String nombreUsuario;
   protected String contrasenia;
   public String fechaCreacion;
   protected int rol;
   protected Persona persona;

    public Usuario(String nombreUsuario, String contrasenia, String fechaCreacion, int rol, Persona persona) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = encriptar(contrasenia);
        this.fechaCreacion = fechaCreacion;
        this.rol = rol;
        this.persona = persona;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public int getRol() {
        return rol;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }
   
    /**Metodo encargador de encriptar contraseña con SHA
     * @param contra
     * @return **/
    private String encriptar (String contra){
        String encriptado =DigestUtils.sha1Hex(contra); 
        return encriptado;
    }
   
    
}
