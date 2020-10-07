/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import io.github.oscarmaestre.compresores.CompresorPalabrasGenericas;
import io.github.oscarmaestre.compresores.ICompresor;
import io.github.oscarmaestre.compresores.compresortextoadaptativo.CompresorTextoAdaptativo;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;


/**
 *
 * @author usuario
 */
public class TestCompresorPalabrasGenericas {
    
    public TestCompresorPalabrasGenericas() {
    }
    
    @Test
    public void testoPalabrasMasComunesEspaniol() {
        HashMap<String, String> diccionario=new HashMap<String,String>() {};
        String put = diccionario.put("desde", "!0");
        diccionario.put("para", "!1");
        diccionario.put("hasta", "!2");
        diccionario.put("hacia", "!3");
        diccionario.put("que", "!4");
        diccionario.put("los", "!5");
        diccionario.put("del", "!6");
        diccionario.put("las", "!7");
        diccionario.put("como", "!8");
        diccionario.put("pero", "!9");
        diccionario.put("una", "!0");
        
        String directorioFicheros="/home/usuario/repos/borrar2/";
        String fichero="quijote.txt";
        String ficheroComprimido=fichero+".lz";
        
        String rutaFicheroEntrada = directorioFicheros+fichero;
        String rutaFicheroSalida  = directorioFicheros + ficheroComprimido;
        CompresorPalabrasGenericas cg=new CompresorPalabrasGenericas(diccionario);
        System.out.println("Comprimiendo con compresor de palabras mas comunes del español");
        testCompresion(cg, rutaFicheroEntrada, rutaFicheroSalida);
    }
    
    @Test
    public void testComprimirConCompresorAdaptativo(){
        String directorioFicheros="/home/usuario/repos/borrar2/";
        String fichero=directorioFicheros+"quijote.txt";
        String ficheroComprimido=fichero+".lza";
        /* Vamos a intentar comprimir con distintos valores de palabras más frecuentes*/
        int[] cantidadPalabrasAComprimir={10, 25, 50, 100, 250, 1000};
        for (int i = 0; i < cantidadPalabrasAComprimir.length; i++) {
            int cantidad = cantidadPalabrasAComprimir[i];
            CompresorTextoAdaptativo cta=new CompresorTextoAdaptativo(cantidad);
            System.out.println("Comprimiendo con Compresor Adaptativo y tamanio "+cantidad);
            this.testCompresion(cta, fichero, ficheroComprimido);
        }
    }
    @Test
    public void testDescomprimirConCompresorAdaptativo() throws IOException, NoSuchAlgorithmException{
        String directorioFicheros="/home/usuario/repos/borrar2/";
        String fichero=directorioFicheros+"quijote.txt";
        String ficheroComprimido=fichero+".lza";
        
        String ficheroVueltoADescomprimir=fichero+".desc";
        CompresorTextoAdaptativo cta=new CompresorTextoAdaptativo();
        cta.descomprimir(ficheroComprimido, ficheroVueltoADescomprimir);
        boolean ficherosSonIguales = TestFicheros.ficherosSonIguales(fichero, ficheroVueltoADescomprimir);
        Assert.assertEquals(true, ficherosSonIguales);
    }
    

    public void testCompresion(ICompresor cg, String rutaFicheroEntrada, String rutaFicheroSalida) {
        try {
            cg.comprimir(rutaFicheroEntrada, rutaFicheroSalida);
            
            File antes=new File(rutaFicheroEntrada);
            long tamAntes = antes.length();
            
            File despues=new File(rutaFicheroSalida);
            long tamDespues = despues.length();
            
            float reduccion=100*(float) tamDespues/tamAntes;
            System.out.format("Tamanio antes: %10d  Tamanio despues:%10d  Reduccion:%8.2f%n", tamAntes, tamDespues, reduccion);          

        } catch (IOException ex) {
            System.out.println("Error!");
            ex.printStackTrace();
        }
    }
    
    @Test
    public void md5Funciona() throws IOException, NoSuchAlgorithmException{
        String directorioFicheros="/home/usuario/repos/borrar2/";
        String fichero=directorioFicheros+"quijote.txt";
        boolean sonIguales=TestFicheros.ficherosSonIguales(fichero, fichero);
        Assert.assertEquals(true, sonIguales);
    }
}

    
