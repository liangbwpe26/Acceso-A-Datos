package practica1.EJ3;

import practica1.Alumno;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ej3 {
    static final String RUTA = "src/main/java/practica1/datos/alumnos.txt";

    public static void main(String[] args) {
        Path ruta = Path.of(RUTA);

        try {
            if (ruta.getParent() != null && !Files.exists(ruta.getParent())) {
                Files.createDirectories(ruta.getParent());
            }

            List<Alumno> lista = List.of(
                    new Alumno(1, "Ana", "García", 20, 8.5),
                    new Alumno(2, "Luis", "Pérez", 21, 7.2),
                    new Alumno(3, "Marta", "López", 19, 9.1),
                    new Alumno(4, "Carlos", "Ruiz", 22, 6.8)
            );

            try (BufferedWriter bw = Files.newBufferedWriter(ruta)) {
                for (Alumno al : lista) {
                    bw.write(al.toCsv());
                    bw.newLine();
                }
                System.out.println("Fichero generado correctamente usando objetos Alumno.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}