package io.github.oscarmaestre.compresores.compresortextoadaptativo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 * @author ana
 */
public class CalculadorFrecuencias {
    /** Dado un texto nos devuelve un stream con las x 
     * palabras más frecuentes en dicho text
     * @param texto Texto en el cual se quiere analizar 
     * qué palabras son las más frecuentes
     * @param xPalabrasMasFrecuentes Indicar un número x para indicar el límite.
     * Es decir, indicar 3 para sacar las 3 palabras más frecuentes, un 5
     * para extraer las 5 palabras más frecuentes, etc...
     * @return Un Stream ordenados de objetos Frecuencia palabra en el que podremos
     ver qué palabras son las más frecuentes y cuantas veces se repiten*/
    public static Stream<FrecuenciaPalabra> getMasFrecuentes(String texto, int xPalabrasMasFrecuentes){
        
        GestorFrecuenciasPalabras gestorFrecuenciasPalabras=new GestorFrecuenciasPalabras();
        
        String[] trozos = texto.split("[\\., ]");
        Stream<String> of = Stream.of(trozos);
        Stream<String> soloLargas = of.filter(palabra->palabra.length()>2);
        
        soloLargas.forEach(palabra->gestorFrecuenciasPalabras.contarAparicion(palabra));
        
        Stream<FrecuenciaPalabra> frecuencias = gestorFrecuenciasPalabras.getFrecuencias();
        Stream<FrecuenciaPalabra> ordenadas = frecuencias.sorted();
        Stream<FrecuenciaPalabra> limitadas = ordenadas.limit(xPalabrasMasFrecuentes);
        return limitadas;
    }
}
