/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio11;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
/**
 *
 * @author cronida
 */


public class Main {
    public static void crearFicheroXML(String url, ArrayList<App> lista) {
        // Se crea el elemento raíz
        Element raiz = new Element("aplicaciones");

        // Creo el árbol de nodos a partir del elemento raíz
        Document arbolNodos = new Document(raiz);

        // Por cada mueble en la lista
        for (App app : lista) {
            // Nuevo elemento Mueble 
            Element appElement = new Element("aplicacion");

            // Establezco el id como atributo del elemento XML, y el resto de valores 
            // del objeto como elementos hijo del mismo
            appElement.setAttribute(new Attribute("codigo", Integer.toString(app.getCodigo())));

            // Construimos los elementos
            Element nombre = new Element("nombre");
            nombre.setText(app.getNombre());
            appElement.addContent(nombre);
            
            Element descripcion = new Element("descripcion");
            descripcion.setText(app.getDescripcion());
            appElement.addContent(descripcion);
            
            Element decargas = new Element("decargas");
            decargas.setText(Integer.toString(app.getnDescargas()));
            appElement.addContent(decargas);
            
            Element tamanio = new Element("tamanio");
            tamanio.setText(Double.toString(app.getKb()));
            appElement.addContent(tamanio);

            // Se añade el elemento Mueble con sus descendientes al árbol de nodos
            arbolNodos.getRootElement().addContent(appElement);
        }

        // La clase XMLOutputter sirve para escribir un fichero JDOM en el disco
        XMLOutputter xmlOutput = new XMLOutputter();
        // La siguente línea hace que se genere el documento en formato de árbol de etiquetas
        xmlOutput.setFormat(Format.getPrettyFormat());

        try {
            // Creación del fichero en el disco usando el objeto XMLOutputter y a partir del árbol de nodos
            xmlOutput.output(arbolNodos, new FileWriter(url));

            System.out.println("Fichero XML creado");

        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
    
    private static void crearFicheroTxt(String ruta, ArrayList<App> lista){
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(ruta))){			
            for (App i : lista) {
                flujo.write(i.toString());				
                flujo.newLine();
            }
            flujo.flush();
			
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    private static void crearFicheroTxt(String ruta, String contenido){
        try (BufferedWriter flujo = new BufferedWriter(new FileWriter(ruta))){			
            flujo.write(contenido);				            

            flujo.flush();
			
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    private static void leerFicheroTxt(String ruta, ArrayList<String> lista){
        String linea;
        
        try (Scanner datosFichero = new Scanner(new FileReader(ruta))){
            
            while (datosFichero.hasNextLine()) {
                linea = datosFichero.nextLine();
                lista.add(linea);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    private static void crearFicheroApp(ArrayList<String> lista){
        String[] tokens;
        String contenido;
        for(String i : lista){
            tokens = i.split(";");
            contenido = "ID:"+tokens[0]+"\nN. Descargas: "+tokens[1]+"\nDescripcion: "+tokens[3]+"\nTamanio Archivo: "+tokens[4];
            crearFicheroTxt("aplicaciones/"+tokens[2]+".txt",contenido);
        }
    }
    
    private static void copiarArchivo(String origen, String destino){
        Path orig = Paths.get(origen);
        Path dest = Paths.get(destino);
        try{
            Files.copy(orig, dest, StandardCopyOption.REPLACE_EXISTING);
        }catch(IOException e) {
            System.out.println(e.toString());
        }
    }
    
    private static void crearDirectorio(String ruta){
        Path file = Paths.get(ruta);
        try{
            if(!Files.exists(file)){
                Files.createDirectory(file);   
            }else{
                System.out.println("El directorio "+ruta+" ya existe");
            }            
        }catch(IOException e) {
            System.out.println(e.toString());
        }
    }
    
    
    public static void main(String[] args) {
        ArrayList<App> lista = new ArrayList<>();
        
        //Crea 50 aplicaciones usando el constructor por defecto, guárdalas en una lista y
        //muéstralas por pantalla.
        for (int i = 0; i < 50; i++) {
            lista.add(new App());
        }
        for (App i: lista) {
            System.out.println(i); 
        }
        
        //Guarda los datos de todas las App de la lista, en un fichero de texto llamado
        //aplicacionestxt.txt, dentro del directorio “./appstxt”.        
        crearDirectorio("appstxt");
        crearFicheroTxt("appstxt/aplicacionestxt.txt",lista);
        
        //Guarda los datos de todas las App de la lista, en un fichero XML llamado
        //aplicacionesxml.xml, dentro del directorio “./appsxml”.       
        crearDirectorio("appsxml");
        crearFicheroXML("appsxml/aplicacionesxml.xml",lista);
        
        //Crea una carpeta “./copias” y realiza una copia de los ficheros anteriores dentro de
        //ella.
        crearDirectorio("copias");
        copiarArchivo("appstxt/aplicacionestxt.txt","copias/aplicacionestxt.txt");
        copiarArchivo("appsxml/aplicacionesxml.xml","copias/aplicacionesxml.xml");
        
        //En una carpeta “./aplicaciones” crea un archivo de texto por cada aplicación que
        //haya en la lista. El archivo se llamará igual que la app y contendrá los datos de la
        //aplicación, separando los campos por el carácter (;).
        crearDirectorio("aplicaciones");
        ArrayList<String> lineasTxt = new ArrayList<>();
        leerFicheroTxt("appstxt/aplicacionestxt.txt", lineasTxt);
        crearFicheroApp(lineasTxt);
        
    }
   
}
