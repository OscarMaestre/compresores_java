package io.github.oscarmaestre.compresores.compresortextoadaptativo;

import io.github.oscarmaestre.compresores.CompresorGenerico;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * Esta clase está pensada para comprimir un fichero DE TEXTO PLANO
 */
public class CompresorTextoAdaptativo extends CompresorGenerico{

    /* Se puede configurar la cantidad de palabras que el compresor va
    a comprimir, cuantas más palabras comprimamos más pequeño será
    el fichero resultante, pero también más se ralentizará el proceso
    */
    final int CANTIDAD_PALABRAS_A_COMPRIMIR;

    public CompresorTextoAdaptativo(int CANTIDAD_PALABRAS_A_COMPRIMIR) {
        this.CANTIDAD_PALABRAS_A_COMPRIMIR = CANTIDAD_PALABRAS_A_COMPRIMIR;
    }
    
    /* Cuando descomprimimos un fichero no se necesita indicar la 
    cantidad de palabras a comprimir */
    public CompresorTextoAdaptativo() {
        this.CANTIDAD_PALABRAS_A_COMPRIMIR=0;
    }
    
    /**
     * Dado un nombre de fichero, el método analiza el fichero buscando las
     * palabras más repetidas. Después las reemplaza por versiones más cortas
     * a fin de reducir el tamaño del fichero
     * @param ficheroEntrada Nombre del fichero a comprimir
     * @param ficheroSalida  Nombre del fichero en el que se va a guardar
     * el resultado comprimido
     * @throws IOException
     */
    @Override
    public void comprimir(String ficheroEntrada, String ficheroSalida) throws IOException {
        /* Cogemos todas las líneas del fichero...*/
        Stream<String> lineasFichero = this.getLineasFichero(ficheroEntrada);
        /* Y las únimos en un solo String*/
        String textoFichero = lineasFichero.collect(Collectors.joining(" "));
        
        /* Averiguamos las más frecuentes*/
        Stream<FrecuenciaPalabra> masFrecuentes = 
                CalculadorFrecuencias.getMasFrecuentes(
                    textoFichero, this.CANTIDAD_PALABRAS_A_COMPRIMIR);
        
        /* Convertimos el stream a un array para poder hacer
        algunas operaciones con más claridad */
        FrecuenciaPalabra[] arrayMasFrecuentes = 
                masFrecuentes.toArray(FrecuenciaPalabra[]::new);
        
                
        
        /*La lista de palabras comprimidas debe incluir todas las palabras
        antes y despues de ser comprimidas para luego poder descomprimir.
        Esta lista se guardará en una "cabecera de fichero" */
        CabeceraFicheroComprimidoConCTA cabecera;
        cabecera = new CabeceraFicheroComprimidoConCTA();
        
        /* Recorremos el vector de las más frecuentes...*/
        for (int i = 0; i < arrayMasFrecuentes.length; i++) {
            FrecuenciaPalabra frecuenciaPalabra = arrayMasFrecuentes[i];
            String palabraAComprimir=frecuenciaPalabra.getPalabra();
            
            /* E intentamos crear una secuencia especial de texto
            que pueda reemplazar a esa palabra, en definitiva COMPRIMIR */
            String palabraComprimida="!"+i;
            
            /* Pero ¡cuidado!, solo queremos comprimir si 
            la palabra "comprimida" realmente es más corta
            que la palabra original*/
            boolean palabraComprimidaResultaSerMasCorta=
                   palabraComprimida.length()<palabraAComprimir.length();
            /* Si realmente es más corta la versión comprimida...*/
            if (palabraComprimidaResultaSerMasCorta){
                /* Pues ¡comprimimos!*/
                textoFichero = 
                        textoFichero.replace(palabraAComprimir, palabraComprimida);
                /*Y guardamos la palabra comprimida y sin comprimir en la 
                cabecera */
                cabecera.anadirCodificacion(palabraAComprimir, palabraComprimida);
            } /*Fin del if*/
        } /*Fin del for*/
        
        /*Llegados a este punto, ya tenemos una cabecera y 
        un texto comprimido que es "más corto" que el original. 
        Lo volcamos en el fichero de salida */
        FileOutputStream ficheroOut=new FileOutputStream(ficheroSalida);
        ObjectOutputStream flujoObjetos;
        flujoObjetos=new ObjectOutputStream(ficheroOut);
        flujoObjetos.writeObject(cabecera);
        flujoObjetos.writeObject(textoFichero);
        flujoObjetos.close();
        
    } /*Fin del método*/

    @Override
    public void descomprimir(String ficheroEntrada, String ficheroSalida) throws IOException {
        /*Para descomprimir hay que leer de un fichero dos cosas*/
        FileInputStream ficheroIn=new FileInputStream(ficheroEntrada);
        ObjectInputStream flujoObjetos;
        flujoObjetos=new ObjectInputStream(ficheroIn);
        
        try {
            /*Cosa 1: debemos extraer la cabecera, donde se habrán almacenado
            las cadenas comprimidas junto a sus versiones sin comprimir */
            CabeceraFicheroComprimidoConCTA cabecera =
                    (CabeceraFicheroComprimidoConCTA) flujoObjetos.readObject();
            String textoEnFicheroEntrada = (String) flujoObjetos.readObject();
            String textoDescomprimido=
                    this.descomprimirTexto(cabecera, textoEnFicheroEntrada);
            this.volcarTextoEnFichero(ficheroSalida, textoDescomprimido);
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encontró una cabecera en el fichero:"+
                    ficheroEntrada);
        }
        
    }

    private String descomprimirTexto(CabeceraFicheroComprimidoConCTA cabecera, String textoEnFicheroEntrada) {
        ArrayList<CodificacionCTA> codificaciones = 
                cabecera.getCodificaciones();
        for (int i = 0; i < codificaciones.size(); i++) {
            CodificacionCTA codificacion = codificaciones.get(i);
            String palabra = codificacion.getPalabra();
            String palabraComprimida = codificacion.getPalabraComprimida();
            textoEnFicheroEntrada=
                    textoEnFicheroEntrada.replace(palabraComprimida, palabra);
        } /*Fin del for*/
        return textoEnFicheroEntrada;
    }

}
