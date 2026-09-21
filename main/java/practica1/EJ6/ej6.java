package practica1.EJ6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class ej6 {
    static final String RUTA_ORIGEN = "src/main/java/practica1/datos";
    static final String DIRECTORIO_COPIAS = "src/main/java/practica1/datos";

    public static void main(String[] args) {
        Path ruta = Path.of(RUTA_ORIGEN, "alumnos.txt");
        Path ruta_copias = Path.of(DIRECTORIO_COPIAS, "copias");

        if (!Files.exists(ruta)) {
            System.out.println("Error: El archivo " + RUTA_ORIGEN + " no existe.");
            return;
        }
        try {
            if (!Files.exists(ruta_copias)) {
                Files.createDirectories(ruta_copias);
                System.out.println("Directorio de copias creado.");
            }

            LocalDate fechaActual = LocalDate.now();
            String nombreCopia = "alumnos_" + fechaActual + ".txt";
            Path destino = ruta_copias.resolve(nombreCopia);

            Files.copy(ruta, destino, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Copia de seguridad realizada correctamente en:");
            System.out.println(destino);

        } catch (IOException e) {
            System.out.println("Error al realizar la copia de seguridad.");
            e.printStackTrace();
        }
    }
}
