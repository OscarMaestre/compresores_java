package io.github.oscarmaestre.compresores;

import java.io.IOException;

public class CompresorLempelZiv extends CompresorGenerico{

    private int tamMemoria;
    StringBuilder memoria;
    public CompresorLempelZiv(int tamMemoria) {
        this.tamMemoria = tamMemoria;
    }
    

    /**
     * Get the value of tamMemoria
     *
     * @return the value of tamMemoria
     */
    public int getTamMemoria() {
        return tamMemoria;
    }

    /**
     * Set the value of tamMemoria
     *
     * @param tamMemoria new value of tamMemoria
     */
    public void setTamMemoria(int tamMemoria) {
        this.tamMemoria = tamMemoria;
    }

    @Override
    public void comprimir(String ficheroEntrada, String ficheroSalida) throws IOException{
        String textoEntrada=this.getTexto(ficheroEntrada);
        
        /* Leemos el texto carácter a carácter*/
        for (int i = 0; i < textoEntrada.length(); i++) {
            char caracter=textoEntrada.charAt(i);
            
            /* Intentamos anadir el caracter actual a la memoria*/
            this.anadirAMemoria(caracter);
        }
    }

    private void anadirAMemoria(char caracter) {
        boolean yaNoCabenMasSimbolosEnMemoria=memoria.length()>this.tamMemoria;
        if (yaNoCabenMasSimbolosEnMemoria){
            this.memoria.delete(0, tamMemoria);
        }
        this.memoria.append(caracter);
    }

}
