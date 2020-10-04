package io.github.oscarmaestre.compresores.compresortextoadaptativo;



public class FrecuenciaPalabra implements Comparable{
    private String palabra;
    private int frecuencia;

    public FrecuenciaPalabra(String palabra, int frecuencia) {
        this.palabra = palabra;
        this.frecuencia = frecuencia;
    }

    public String getPalabra() {
        return palabra;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    @Override
    public int compareTo(Object frecuencia2) {
        FrecuenciaPalabra frecuenciaPalabra2=(FrecuenciaPalabra) frecuencia2;
        if (frecuenciaPalabra2.getFrecuencia()==frecuencia){
            return 0;
        }
        if (frecuenciaPalabra2.getFrecuencia()<=frecuencia){
            return -1;
        }
        return 1;
        
    }

    
}
