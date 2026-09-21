package practica1.EJ4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LeerFichero {
    static final String RUTA = "src/main/java/practica1/datos/alumnos.txt";

    public static void main(String[] args) {
        Path ruta = Path.of(RUTA);

        try (BufferedReader br = Files.newBufferedReader(ruta)) {

            String linea;

            // Leemos la primera línea (la cabecera) y no hacemos nada con ella para saltarla
            br.readLine();

            // Leemos el resto del documento línea por línea
            while ((linea = br.readLine()) != null) {

                // Dividimos la línea usando el punto y coma como separador
                String[] datos = linea.split(";");

                // Verificamos que la línea tenga exactamente 4 elementos para evitar errores
                if (datos.length == 4) {
                    System.out.println("ID: " + datos[0]);
                    System.out.println("Nombre: " + datos[1]);
                    System.out.println("Edad: " + datos[2]);
                    System.out.println("Nota: " + datos[3]);
                    System.out.println("---------------------------");
                }
            }

        } catch (IOException e) {
            System.out.println("Error de lectura");
            e.printStackTrace();
        }
    }
}
