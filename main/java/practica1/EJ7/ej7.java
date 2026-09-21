package practica1.EJ7;

import practica1.Alumno;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Scanner;

public class ej7 {

    static final String FICHERO = "src/main/java/practica1/datos/alumnos.dat";
    static final int TAM_REGISTRO = 16;

    public static void main(String[] args) {

        List<Alumno> lista = List.of(
                new Alumno(1, "Ana", "García", 20, 8.5),
                new Alumno(2, "Luis", "Pérez", 21, 7.2),
                new Alumno(3, "Marta", "López", 19, 9.1),
                new Alumno(4, "Carlos", "Ruiz", 22, 6.8)
        );

        try (RandomAccessFile fichero = new RandomAccessFile(FICHERO, "rw")) {
            for (Alumno al : lista) {
                fichero.writeInt(al.getId());
                fichero.writeInt(al.getEdad());
                fichero.writeDouble(al.getNota());
            }
            System.out.println("Fichero binario cargado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        try (RandomAccessFile fichero = new RandomAccessFile(FICHERO, "r");
             Scanner scanner = new Scanner(System.in)) {

            long totalRegistros = fichero.length() / TAM_REGISTRO;

            System.out.print("Introduce número de alumno (1 a " + totalRegistros + "): ");
            int numeroAlumno = scanner.nextInt();

            if (numeroAlumno >= 1 && numeroAlumno <= totalRegistros) {
                long posicionBytes = (long) (numeroAlumno - 1) * TAM_REGISTRO;
                fichero.seek(posicionBytes);

                int id = fichero.readInt();
                int edad = fichero.readInt();
                double nota = fichero.readDouble();

                Alumno alumnoConsultado = new Alumno(id, "", "", edad, nota);

                System.out.println("\nRegistro localizado en el byte: " + posicionBytes);
                System.out.println("ID: " + alumnoConsultado.getId());
                System.out.println("Edad: " + alumnoConsultado.getEdad());
                System.out.println("Nota: " + alumnoConsultado.getNota());
            } else {
                System.out.println("Número fuera de rango.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}