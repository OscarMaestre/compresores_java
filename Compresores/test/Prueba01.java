/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import io.github.oscarmaestre.compresores.compresortextoadaptativo.FrecuenciaPalabra;
import io.github.oscarmaestre.compresores.compresortextoadaptativo.CalculadorFrecuencias;
import java.util.stream.Stream;

/**
 *
 * @author ana
 */
public class Prueba01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String texto1="hola hola hola a a a con con prueba";
        Stream<FrecuenciaPalabra> masFrecuentes = CalculadorFrecuencias.getMasFrecuentes(texto1, 3);
        masFrecuentes.forEach(f->System.out.println(
                f.getPalabra()+"-"+f.getFrecuencia()
        ));
    }
    
}
