/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio11;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.stream.*;


/**
 *
 * @author cronida
 */
public class App {
    private int codigo, nDescargas;
    private String nombre, descripcion;
    private double kb;
    private static int contInst = 1;

    public App() {     
        Random r = new Random();        
        
        this.codigo = contInst;
        this.nombre = generarNombre();
        this.kb = 100 + (r.nextDouble() * (1024 - 100));
        this.nDescargas = r.nextInt(50000);
        contInst++;
    }

    public App(String nombre, String descripcion) {
        Random r = new Random();
        
        this.codigo = contInst;
        this.nDescargas = r.nextInt()+5000;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.kb = 100 + (r.nextDouble() * (1024 - 100));
        contInst++;
    }
    
   

    public int getnDescargas() {
        return nDescargas;
    }

    public void setnDescargas(int nDescargas) {
        this.nDescargas = nDescargas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getKb() {
        return kb;
    }

    public void setKb(double kb) {
        this.kb = kb;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        DecimalFormat df2 = new DecimalFormat("#.##");
        return codigo+";"+ nDescargas+";"+nombre+";"+descripcion+";"+df2.format(kb);
    }      
    
    private String generarNombre(){
        char letra;
        Random rdn = new Random();
        
        letra = (char) (rdn.nextInt(25)+97);
        return "app"+getCodigo()+letra;
    }
    
    
}
