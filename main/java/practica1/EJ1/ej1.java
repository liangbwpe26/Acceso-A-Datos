package practica1.EJ1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ej1 {
    public static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce una ruta: ");
        String ruta = scanner.nextLine();

        // Comprobar si la ruta existe
        Path rutaPath = Path.of(ruta);
        if (!Files.exists(rutaPath)) {
            System.out.println("La ruta no existe");
        } else {
            // Comprobar si la ruta es un directorio
            if (Files.isDirectory(rutaPath)) {
                System.out.println("La ruta es un directorio");
                System.out.println("El directorio contiene: ");

                // Listar el contenido del directorio
                try (DirectoryStream<Path> contenido = Files.newDirectoryStream(rutaPath)) {
                    for (Path elemento : contenido) {
                        System.out.println(elemento);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("La ruta es un fichero");

                try {
                    // Comprobar si el fichero está vacío
                    if (Files.size(rutaPath) == 0) {
                        System.out.println("El fichero está vacío");
                    } else {
                        System.out.println("El fichero contiene: ");
                        Files.readAllLines(rutaPath).forEach(System.out::println);
                    }
                } catch (Exception e) {
                    System.out.println("Error al leer el fichero");
                }
            }
        }
    }
}
