/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

/**
 *
 * @author chip_
 */
public interface Variables {
    
/**
 * TITULOS DE LAS VENTANAS EMERGENTES 
 */ 
    //Cadenas de titulo de las ventanas emergentes en procesos del padron catastral
    static final String MSG_TITLE_NORMAL_PADRON = "Analisis del Padron Catastral";
    static final String MSG_TITLE_ERROR_PADRON = "Error del Padron Catastral";
    
    //Cadenas de titulo de las ventanas emergentes en login con MySQL
    static final String MSG_TITLE_NORMAL_MYSQL = "Login MySQL";
    static final String MSG_TITLE_ERROR_MYSQL = "Error Login MySQL";
    
/**
 * NOMBRES IMPORTANTES
 */
    //Nombre del archivo de inicio de sesion en el servidor
    static final String NAME_FILE_LOGIN_MYSQL = "file-login-status.txt";
    //Nombre de la cadena que se asigna a las tablas de respaldos
    static final String NAME_STRING_END_SUPPORT = "_respaldo_apc";
    //Nombre de la direccion del look and feel
    static final String NAME_URL_LOOK_AND_FEEL_WINDOWS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    //Nombre del item inicial en los combos
    static final String NAME_FIRST_ITEM = "Selecciona uno...";
    
/**
 * VALORES
 */
    //Valor correspondiente a un segundo de tiempo
    public final static int ONE_SECOND = 1000;
}
