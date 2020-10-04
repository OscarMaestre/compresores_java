package io.github.oscarmaestre.compresores.compresortextoadaptativo;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class GestorFrecuenciasPalabras {
    protected HashMap<String, Integer> frecuencias;
    public GestorFrecuenciasPalabras(){
        frecuencias=new HashMap<>();
    }
    public void contarAparicion(String palabra){
        Integer apariciones = frecuencias.get(palabra);
        if (apariciones==null){
            frecuencias.put(palabra, 1);
        } else {
            frecuencias.put(palabra, apariciones+1);
        }
    }

    public Stream<FrecuenciaPalabra> getFrecuencias() {
        
        ArrayList<FrecuenciaPalabra> listaFrecuencias=new ArrayList<>();
        for (Map.Entry<String, Integer> entry : frecuencias.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            FrecuenciaPalabra f=new FrecuenciaPalabra(key, value);
            listaFrecuencias.add(f);
        }
        return listaFrecuencias.stream();
    }
    
}
