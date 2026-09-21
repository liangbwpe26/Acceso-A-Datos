package practica1.EJ2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ej2 {
    public static void main() {
        // Ruta de la carpeta DAM
        Path rutaDam = Path.of("src/main/java/practica1/EJ2/DAM");

        try {
            // Verificar si la carpeta DAM ya existe
            if (!Files.exists(rutaDam)) {
                Files.createDirectory(rutaDam);
            }

            // Crear los directorios dentro de DAM
            String[] directorios = {
                    "documentos",
                    "imagenes",
                    "datos",
                    "copias",
            };

            // Iterar sobre los directorios y crearlos si no existen
            for (String directorio : directorios) {
                Path rutaDirectorio = rutaDam.resolve(directorio);
                if (!Files.exists(rutaDirectorio)) {
                    Files.createDirectory(rutaDirectorio);
                }
            }

            System.out.println("\nEstructura creada correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
