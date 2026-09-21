package practica1.EJ3;

import practica1.Alumno;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class ej4 {
    static final String RUTA = "src/main/java/practica1/datos/alumnos.txt";

    public static void main(String[] args) {
        Path ruta = Path.of(RUTA);
        Scanner scanner = new Scanner(System.in);

        int idValido = -1;
        boolean idCorrecto = false;

        while (!idCorrecto) {
            System.out.print("Introduce ID: ");
            String idTexto = scanner.nextLine();
            try {
                idValido = Integer.parseInt(idTexto);
                if (idValido > 0) {
                    idCorrecto = true;
                } else {
                    System.out.println("El ID debe ser mayor que 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: El ID debe ser un número entero.");
            }
        }

        boolean idRepetido = false;
        if (Files.exists(ruta)) {
            try (BufferedReader br = Files.newBufferedReader(ruta)) {
                String linea;
                while ((linea = br.readLine()) != null && !idRepetido) {
                    String[] datos = linea.split(";");
                    if (datos.length > 0 && datos[0].trim().equals(String.valueOf(idValido))) {
                        idRepetido = true;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                scanner.close();
                return;
            }
        }

        if (idRepetido) {
            System.out.println("Error: Ya existe un alumno con el ID " + idValido);
            scanner.close();
            return;
        }

        // Nombre
        String nombre = "";
        boolean nombreValido = false;
        while (!nombreValido) {
            System.out.print("Introduce nombre: ");
            nombre = scanner.nextLine().trim();
            if (nombre.matches(".*\\d.*") || nombre.isEmpty()) {
                System.out.println("Error: Introduce un nombre válido sin números.");
            } else {
                nombreValido = true;
            }
        }

        // Apellidos
        String apellidos = "";
        boolean apellidosValidos = false;
        while (!apellidosValidos) {
            System.out.print("Introduce apellidos: ");
            apellidos = scanner.nextLine().trim();
            if (apellidos.matches(".*\\d.*") || apellidos.isEmpty()) {
                System.out.println("Error: Introduce apellidos válidos sin números.");
            } else {
                apellidosValidos = true;
            }
        }

        // Edad
        int edad = 0;
        boolean edadValida = false;
        while (!edadValida) {
            System.out.print("Introduce edad: ");
            try {
                edad = Integer.parseInt(scanner.nextLine().trim());
                if (edad > 0) {
                    edadValida = true;
                } else {
                    System.out.println("La edad debe ser mayor que 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero para la edad.");
            }
        }

        // Nota
        double nota = 0.0;
        boolean notaValida = false;
        while (!notaValida) {
            System.out.print("Introduce nota (0.0 - 10.0): ");
            try {
                nota = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                if (nota >= 0.0 && nota <= 10.0) {
                    notaValida = true;
                } else {
                    System.out.println("La nota debe estar entre 0.0 y 10.0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número decimal válido.");
            }
        }

        Alumno nuevoAlumno = new Alumno(idValido, nombre, apellidos, edad, nota);

        try (BufferedWriter bw = Files.newBufferedWriter(ruta, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bw.write(nuevoAlumno.toCsv());
            bw.newLine();
            System.out.println("\nAlumno añadido correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}