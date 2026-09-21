package practica1.EJ5;

import practica1.Alumno;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ej5 {
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

                String[] d = linea.split(";");
                if (d.length >= 4) {
                    int id = Integer.parseInt(d[0].trim());
                    String[] nombrePartes = d[1].trim().split(" ", 2);
                    String nombre = nombrePartes[0];
                    String apellidos = nombrePartes.length > 1 ? nombrePartes[1] : "";
                    int edad = Integer.parseInt(d[2].trim());
                    double nota = Double.parseDouble(d[3].trim());

                    alumnos.add(new Alumno(id, nombre, apellidos, edad, nota));
                }
            }

            if (alumnos.isEmpty()) {
                System.out.println("No hay alumnos registrados.");
                return;
            }

            int total = alumnos.size();
            double suma = 0.0;
            double max = alumnos.get(0).getNota();
            double min = alumnos.get(0).getNota();
            int aprobados = 0;
            int suspensos = 0;

            for (Alumno al : alumnos) {
                double n = al.getNota();
                suma += n;
                if (n > max) max = n;
                if (n < min) min = n;
                if (n >= 5.0) {
                    aprobados++;
                } else {
                    suspensos++;
                }
            }

            System.out.println("Número de alumnos: " + total);
            System.out.printf("Nota media: %.2f%n", (suma / total));
            System.out.println("Nota máxima: " + max);
            System.out.println("Nota mínima: " + min);
            System.out.println("Aprobados: " + aprobados);
            System.out.println("Suspensos: " + suspensos);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}