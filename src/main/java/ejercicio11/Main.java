/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio11;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

            
            /**
             * Las líneas anteriores se pueden resumir en las siguientes:
             * muebleElement.addContent(new
             * Element("ANCHO").setText(mueble.getAncho().toString()));
             * muebleElement.addContent(new
             * Element("ALTO").setText(mueble.getAlto().toString()));
             * muebleElement.addContent(new
             * Element("PROFUNDO").setText(mueble.getProfundo().toString()));
             */
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

            System.out.println("Fichero creado");

        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
    
    
    public static void main(String[] args) {
        ArrayList<App> lista = new ArrayList<>();
        
        for (int i = 0; i < 50; i++) {
            lista.add(new App());
        }
        
        for (App i: lista) {
            System.out.println(i); 
        }
        crearFicheroXML("app.xml",lista);
    }
   
}
