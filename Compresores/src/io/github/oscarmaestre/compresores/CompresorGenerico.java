package io.github.oscarmaestre.compresores;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class CompresorGenerico implements ICompresor{
    
    protected String getTexto(String ficheroEntrada) throws IOException{
        Stream<String> lineas = getLineasFichero(ficheroEntrada);
        String texto = lineas.collect(Collectors.joining(" "));
        return texto;
    }

    protected Stream<String> getLineasFichero(String ficheroEntrada) throws IOException {
        Path path = Paths.get(ficheroEntrada);
        Stream<String> lineas = Files.lines(path);
        return lineas;
    }
    protected Stream<Byte> getTextoComoStreamDeBytes(String ficheroEntrada) throws IOException{
        byte[] bytes = Files.readAllBytes(Paths.get(ficheroEntrada));
        
        Stream.Builder<Byte> builder = Stream.builder();
        for (int i = 0; i < bytes.length; i++) {
            builder.add(bytes[i]);
        }
        Stream<Byte> stream = builder.build();
        return stream;
    }
    @Override
    public abstract void comprimir(String ficheroEntrada, String ficheroSalida) throws IOException ;

    protected void volcarTextoEnFichero(String ficheroSalida, String textoSalida) throws IOException {
        FileWriter fw = new FileWriter(ficheroSalida);
        fw.write(textoSalida);
        fw.close();
    }
}
