/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEJANDRO.DELGADILLO
 */
public class Conexion {
    
    //Almacenara la conexion, inicialmente es nulo
    private Connection conexion = null;
    //Procesara la sentencia SQL y obtendra los resultados de tipo ResultSet
    private Statement comando = null;
    
    //Direccion del servidor local
    private String servidor = "jdbc:mysql://localhost/";
    //Almacenra el nombre de la base de datos
    private String base = "";
    //Almacentara el usuario del servidor
    private String usuario = "";
    //Almacenara el password del servidor
    private String password = "";

    //Metodos Getters y Setters de las variables importantes para otras clases
    
    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    //Metodo que permite obtener la conexion a la base de datos
    public Connection getConexion(){
        //En caso de realizar la conexion con exito
        try {
            //URL del servidor al que se pretende conectar la aplicacion
            String url = servidor + base;
            //Se conecta con el driver de MySQL para Java
            Class.forName("com.mysql.jdbc.Driver");
            //Obtiene la conexion con los parametros especificados
            conexion = DriverManager.getConnection(url, usuario, password);
            
        //En caso de ocurrir algun error al obtener la conexion
        } catch (SQLException e) {
            conexion = null;
            
        //En caso de no encontrar el driver de MySQL
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(
                    null, 
                    "No se encuentra el driver de MySQL\n" + e.toString(), 
                    Variables.MSG_TITLE_ERROR_PADRON, 
                    JOptionPane.ERROR_MESSAGE
            );
            conexion = null;
        }
        
        //En cualquier caso regresa la variable con la conexion
        return conexion;
    }
}
