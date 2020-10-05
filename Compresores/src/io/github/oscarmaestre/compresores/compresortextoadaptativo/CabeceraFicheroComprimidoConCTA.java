package io.github.oscarmaestre.compresores.compresortextoadaptativo;

import java.io.Serializable;
import java.util.ArrayList;

public class CabeceraFicheroComprimidoConCTA implements Serializable{
    ArrayList<CodificacionCTA> codificaciones;

    public CabeceraFicheroComprimidoConCTA() {
        codificaciones=new ArrayList<>();
    }
    public void anadirCodificacion(String palabra, String palabraComprimida){
        CodificacionCTA cod=new CodificacionCTA(palabra, palabraComprimida);
        codificaciones.add(cod);
    }
}
