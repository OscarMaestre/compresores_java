package io.github.oscarmaestre.compresores.compresortextoadaptativo;

public class FicheroTextoComprimidoConCTA {
    CabeceraFicheroComprimidoConCTA cabecera;
    String                          textoComprimido;

    public FicheroTextoComprimidoConCTA(CabeceraFicheroComprimidoConCTA cabecera, String textoComprimido) {
        this.cabecera = cabecera;
        this.textoComprimido = textoComprimido;
    }

    public CabeceraFicheroComprimidoConCTA getCabecera() {
        return cabecera;
    }

    public String getTextoComprimido() {
        return textoComprimido;
    }
    
}
