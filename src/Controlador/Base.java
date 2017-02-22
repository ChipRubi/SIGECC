/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEJANDRO.DELGADILLO
 */
public class Base {
    //Almacenara las bases de datos del servidor local
    private ArrayList<String> listaBasesDatos;
    //Almacenara los nombres de los campos
    private ArrayList<String> listaNombresColumnas;
    
    //Metodo que genera una lista con las bases de datos del servidor
    public ArrayList<String> getBases(String usuario, String password){
        //Se crea una nueva lista
        listaBasesDatos = new ArrayList<String>();
        //Se crea una nueva conexion
        Conexion conexion = new Conexion();
        //Se asigna el usuario al objeto de la conexion
        conexion.setUsuario(usuario);
        //Se asigna el password al objeto de la conexion
        conexion.setPassword(password);
        //La consulta para obtener las bases de datos
        String sql = "show databases";
        
        //En caso de realizar la conexion usando el usuario y el password
        try {
            //Se obtiene la conexion a la base y se ejecuta la consulta
            Connection con = conexion.getConexion();
            Statement comando = con.createStatement();
            ResultSet registro = comando.executeQuery(sql);
            
            //Mientras la consulta tenga registros
            while(registro.next()){
                //Obtener cada una de las bases de datos
                String r = registro.getString("Database");
                //Añadirla a la lista de bases de datos
                listaBasesDatos.add(r);
            }
            
        //En caso de ocurrir algun error en la consulta y obtencion de datos
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    null, 
                    "Error al obtener las bases\n" + ex.toString(), 
                    Variables.MSG_TITLE_ERROR_PADRON, 
                    JOptionPane.ERROR_MESSAGE
            );
            listaBasesDatos = null;
            
        //En caso de que algun parametro de la conexion sea nula
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(
                    null, 
                    "El usuario o la contraseña son incorrectos", 
                    Variables.MSG_TITLE_ERROR_MYSQL, 
                    JOptionPane.ERROR_MESSAGE
            );
            listaBasesDatos = null;
        }
        
        //En cualquier caso regresa la lista de bases de datos
        return listaBasesDatos;
    }
    
    //Metodo que devuelve los nombres de los campos de una tabla
    public ArrayList<String> getColumnsNames(String usuario, String password, String base, String tabla){
        //Crear la lista para los nombres de las columnas de la tabla
        listaNombresColumnas = new ArrayList<String>();
        String sql = ""
                + "select * "
                + "from " + tabla;
        
        Conexion conexion = new Conexion();
        conexion.setUsuario(usuario);
        conexion.setPassword(password);
        conexion.setBase(base);
        
        try {
            Connection con = conexion.getConexion();
            Statement comando = con.createStatement();
            
            ResultSet registro = comando.executeQuery(sql);
            ResultSetMetaData metaRegistro = registro.getMetaData();
            
            int numeroColumnas = metaRegistro.getColumnCount();
            for (int i = 1; i <= numeroColumnas; i++) {
                listaNombresColumnas.add(metaRegistro.getColumnName(i));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, 
                    "Error al obtener nombres de columnas\n" + e.toString(), 
                    Variables.MSG_TITLE_ERROR_PADRON, 
                    JOptionPane.ERROR_MESSAGE
            );
            listaNombresColumnas = null;
        }
        
        return listaNombresColumnas;
    }
}
