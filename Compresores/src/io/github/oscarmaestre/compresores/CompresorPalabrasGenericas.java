package io.github.oscarmaestre.compresores;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CompresorPalabrasGenericas extends CompresorGenerico{

    private Map<String, String> diccionario;

    public CompresorPalabrasGenericas(Map<String, String> diccionario) {
        this.diccionario = diccionario;
    }
    
    
    /* Este compresor reemplaza algunas palabras genericas por variantes más cortas
    que es poco probable que se repitan. Evidentemente, es muy dependiente del idioma
    */
    @Override
    public void comprimir(String ficheroEntrada, String ficheroSalida) throws IOException {
        String textoEntrada=this.getTexto(ficheroEntrada);
        String textoSalida=textoEntrada;
        for(Map.Entry<String, String> entrada: diccionario.entrySet()){
            String cadenaParaReemplazar=entrada.getKey();
            String cadenaNuevaEnElTexto = entrada.getValue();
            //System.out.println("Reemplazando "+ cadenaParaReemplazar + " con "+ cadenaNuevaEnElTexto);
            textoSalida = textoSalida.replace(cadenaParaReemplazar, cadenaNuevaEnElTexto);
        }
        
        volcarTextoEnFichero(ficheroSalida, textoSalida);
    } /*Fin del comprimir*/
} /*Fin de la clase*/
