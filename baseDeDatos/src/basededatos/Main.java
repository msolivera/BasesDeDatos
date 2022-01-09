/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Meki
 */
public class Main {
    
    
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        BaseDeDatos holaBase = new BaseDeDatos();
        
        holaBase.conectar("192.168.56.101", "basesDeDatos", "postgres", "1ncr31bl3");
        
         
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        
        System.out.println("HOLA BASE DE DATOS!! ");
        System.out.println(" ");
        System.out.println(" ");
        
        while(!salir){
           System.out.println("***MENU PRINCIPAL ***"); 
           System.out.println("1. Creación de una tabla ");
           System.out.println("2. Inserción de filas a la tabla");
           System.out.println("3. Listar filas de la tabla");
           System.out.println("4. Buscar los datos de la tabla");
           System.out.println("5. ************Salir************");
           
            
           System.out.println("Escribe una de las opciones:");
           opcion = sn.nextInt();
            
           switch(opcion){
               case 1:
                   String nombreTablaCrear;
                   System.out.println("Escriba el nombre de la tabla");
                   nombreTablaCrear = sn.next();
                   String atributo1;
                   System.out.println("Escriba el nombre del primer atributo");
                   atributo1 = sn.next();
                   String atributo2;
                   System.out.println("Escriba el nombre del segundo atributo");
                   atributo2 = sn.next();
                   
                   try {
                   String queryCrear = "CREATE TABLE public."+nombreTablaCrear+" ("+atributo1+" numeric,"+atributo2+" varchar(20)  ,PRIMARY KEY ("+atributo1+")) ; ALTER TABLE public."+nombreTablaCrear+" OWNER to postgres;";
                   ResultSet q1 = holaBase.query(queryCrear); 
                   } catch (Exception e) {
                                        e.printStackTrace();
                            }
                   
                   break;
               case 2:
                   String nombreTablaInsertar;
                   System.out.println("Escriba el nombre de la tabla");
                   nombreTablaInsertar = sn.next();
    
                   String variableI1;
                   System.out.println("Escriba el valor del primer atributo");
                   variableI1 = sn.next();
                   int resultado = Integer.parseInt(variableI1);
                    
                   String variableI2;
                   System.out.println("Escriba el valor del segundo atributo");
                   variableI2 = sn.next();
                   
                    try {
                   String queryInsertar = "INSERT INTO public."+nombreTablaInsertar+" VALUES ("+resultado+", '"+variableI2+"');";
                   ResultSet q2 = holaBase.query(queryInsertar); 
                   } catch (Exception e) {
                                        e.printStackTrace();
                            }
                   
                   break;
                case 3:
                   String nombreTablaFilas;
                   System.out.println("Escriba el nombre de la tabla");
                   nombreTablaFilas = sn.next();
                   
                   String queryFilas = "SELECT * FROM public."+nombreTablaFilas+";";
                   ResultSet q3 = holaBase.query(queryFilas);
                   
                       try {
                                while(q3.next()){
                                    
                                    System.out.println(q3.getInt(1)+" "+q3.getString(2));
                                }  
                                
                                } catch (Exception e) {
                                        e.printStackTrace();
                            }
                   break;
                case 4:
                   String nombreTablaBuscar;
                   String nombreAtributoBuscar;
                   String nombreAtributo;
                   String valorAtributoBuscar;
                   
                   System.out.println("Escriba el nombre de la tabla");
                   nombreTablaBuscar = sn.next();
                   
                   System.out.println("Escriba el Atributo por el cual buscar");
                   nombreAtributoBuscar = sn.next();
                   System.out.println("Escriba el otro atributo de la tabla");
                   nombreAtributo = sn.next();
                   
                   System.out.println("Escriba el valor a buscar");
                   valorAtributoBuscar = sn.next();
                   
                   String queryBuscar = "SELECT "+nombreAtributoBuscar+", "+nombreAtributo+" FROM public."+nombreTablaBuscar+" where "+nombreAtributoBuscar+"  = "+valorAtributoBuscar+";";
                   ResultSet q4 = holaBase.query(queryBuscar);
                   
                   try {
                                while(q4.next()){
                                    
                                    System.out.println(q4.getInt(1)+" "+q4.getString(2));
                                }  
                                
                                } catch (Exception e) {
                                        e.printStackTrace();
                            }
                   
                   break;
                case 5:
                   salir=true;
                   break;
                default:
                   System.out.println("Solo números entre 1 y 5");
           }
            
       }
        
        
    }
    
    
}
