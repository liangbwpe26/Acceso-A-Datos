package practica1.EJ3;

import practica1.Alumno;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeerFichero {
    static final String RUTA = "src/main/java/practica1/datos/alumnos.txt";

    public static void main(String[] args) {
        Path ruta = Path.of(RUTA);
        List<Alumno> alumnos = new ArrayList<>();

        if (!Files.exists(ruta)) {
            System.out.println("El fichero no existe.");
            return;
        }

        try (BufferedReader br = Files.newBufferedReader(ruta)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty() || linea.startsWith("ID")) {
                    continue;
                }

                String[] datos = linea.split(";");
                if (datos.length >= 4) {
                    int id = Integer.parseInt(datos[0].trim());
                    String nombreCompleto = datos[1].trim();
                    int edad = Integer.parseInt(datos[2].trim());
                    double nota = Double.parseDouble(datos[3].trim());

                    // Separar nombre y primer apellido si vienen juntos
                    String[] partesNombre = nombreCompleto.split(" ", 2);
                    String nombre = partesNombre[0];
                    String apellidos = partesNombre.length > 1 ? partesNombre[1] : "";

                    Alumno alumno = new Alumno(id, nombre, apellidos, edad, nota);
                    alumnos.add(alumno);
                }
            }

            for (Alumno al : alumnos) {
                System.out.println(al);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}