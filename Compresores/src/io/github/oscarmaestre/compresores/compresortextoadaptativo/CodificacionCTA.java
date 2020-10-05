package io.github.oscarmaestre.compresores.compresortextoadaptativo;

import java.io.Serializable;

public class CodificacionCTA implements Serializable{
    
    private final String palabra ;
    
    private final String palabraComprimida;

    public CodificacionCTA(String palabra, String palabraComprimida) {
        this.palabra = palabra;
        this.palabraComprimida = palabraComprimida;
    }

    
    public String getPalabra() {
        return palabra;
    }

    public String getPalabraComprimida() {
        return palabraComprimida;
    }

}
