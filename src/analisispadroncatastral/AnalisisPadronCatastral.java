/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisispadroncatastral;

import Controlador.Base;
import Controlador.Variables;
import Pantallas.Inicio;
import Pantallas.LoginMySQL;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author ALEJANDRO.DELGADILLO
 */
public class AnalisisPadronCatastral {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Cambiar el diseño a el predeterminado de Windows
        try {
            UIManager.setLookAndFeel(Variables.NAME_URL_LOOK_AND_FEEL_WINDOWS);
	} catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null, 
                    "Error con el tema\n" + e.toString(), 
                    Variables.MSG_TITLE_ERROR_PADRON, 
                    JOptionPane.ERROR_MESSAGE
            );
	}

        
        //Creamos el archivo del estatus de logeo
        File fichero = new File(Variables.NAME_FILE_LOGIN_MYSQL);
        //Damos permisos al archivo
        fichero.setExecutable(true);
        fichero.setReadable(true);
        fichero.setWritable(true);
        //Obtenemos la ruta predeterminada
        String ruta = fichero.getAbsolutePath();
        //Definimos el objeto para las credenciales almacenadas en el archivo
        ArrayList<String> credenciales;
        //Definimos el objeto para las bases de datos
        ArrayList<String> listaBaseDatos;
        //Creamos el objeto para obtener las bases de datos
        Base base = new Base();
        
        try {
            //Creamos los objetos necesarios para la lectura del archivo
            FileReader fileReader = new FileReader(ruta);
            BufferedReader entrada = new BufferedReader(fileReader);
            //Creamos la lista para almacenar las credenciales
            credenciales = new ArrayList<String>();
            
            //Mientrar el archivo tenga informacion
            while(entrada.ready()){
                //Obtener la primer linea del archivo
                String cadena = entrada.readLine();
                //Obtener lugar en el que hay un espacio
                int espacio = cadena.indexOf(" ");
                //Obtener la cadena posterior al espacio, dado que es el usuario o el password
                String util = cadena.substring(espacio + 1);
                //Agregar la cadena a la lista de credenciales
                credenciales.add(util);
            }
            //Obtener el usuario, que es la primer cadena del archivo
            String usuario = credenciales.get(0);
            //Obtener el password, que es la segunda cadena del archivo
            String password = credenciales.get(1);
            //Obtener la lista de bases de datos
            listaBaseDatos = base.getBases(usuario, password);
            
            //En caso de que la lista este vacia, debido a un error de autenticacion
            if(listaBaseDatos == null){
                //LLamar a la ventana de logeo
                LoginMySQL loginmysql = new LoginMySQL();
                loginmysql.show();
            } else {
                //LLamar a la ventana de inicio de la aplicacion
                Inicio inicio = new Inicio(listaBaseDatos, usuario, password);
                inicio.show();
            }
            
            
        //En caso de que no exista el archivo con las credenciales de logeo
        } catch (FileNotFoundException ex) {
            //LLamar a la ventana de logeo
            LoginMySQL loginmysql = new LoginMySQL();
            loginmysql.show();
            
        //En caso de algun error en la lectura del archivo
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    null, 
                    "No se puede leer el archivo\n" + ex.toString(), 
                    Variables.MSG_TITLE_ERROR_PADRON, 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
