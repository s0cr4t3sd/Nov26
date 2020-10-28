/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio13;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 *
 * @author cronida
 */
public class Main {
    private static void contenidoDirectorio(String ruta, ArrayList<String> lista){
        File f = new File(ruta);
        if(f.exists()){
            File[] ficheros = f.listFiles();
            for(File i : ficheros){
                lista.add(i.getName());
                System.out.println(i.getName());
            }
        }else{
            System.out.println("No existe el directorio "+ruta);
        }
    }
    
    private static App crearObjDeFichero(String ruta){
        String linea;
        String[] tokens, campo;
        App app;
        ArrayList<String> lista = new ArrayList();
        String[] nombre = ruta.split("\\.");
        int cod, des;
        double kb;
        
        try (Scanner datosFichero = new Scanner(new FileReader("aplicaciones/"+ruta))){
            
            linea = datosFichero.nextLine();
            tokens = linea.split(";");

            cod = Integer.parseInt(tokens[0]);
            des = Integer.parseInt(tokens[1]);
            kb = Double.parseDouble(tokens[3]);

            app = new App(cod, des, nombre[0], tokens[2], kb);
                        
           
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            app = null;
        } 
        return app;
    }
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        App app;
        ArrayList<String> listaFicheros = new ArrayList<>();
        ArrayList<App> lista = new ArrayList<>();
        String opcFichero = "";
        
        //Pregunta al usuario que app en modo texto desea leer de las posibles que haya en
        //“./aplicaciones”. Para ello muestra un listado con las que haya en la carpeta.        
        contenidoDirectorio("aplicaciones",listaFicheros);
              
        System.out.println("");
        boolean seguir = true;
        while(seguir){
            System.out.println("Dime que fichero quieres leer?");
            opcFichero = scn.nextLine();
            System.out.println("");
            seguir = !listaFicheros.contains(opcFichero);
        }
        
        //Una vez leída la app desde el fichero y creado el objeto app con los datos
        //correspondientes, muestra la app por pantalla.
        app = crearObjDeFichero(opcFichero);
        app.toString();
        
    }
}
