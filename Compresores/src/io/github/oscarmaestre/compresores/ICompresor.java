/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.oscarmaestre.compresores;

import java.io.IOException;

/**
 *
 * @author usuario
 */
public interface ICompresor {
    public void comprimir(String ficheroEntrada, String ficheroSalida) throws IOException;
    public void descomprimir(String ficheroEntrada, String ficheroSalida) throws IOException;
}
