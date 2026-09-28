package proyecto_final;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Alumno> lista_alumnos = new ArrayList<>();
        String opcion = "";
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("===============================");
            System.out.println("GESTOR DE ALUMNOS");
            System.out.println("===============================");
            System.out.println("1. Añadir alumno");
            System.out.println("2. Mostrar alumnos");
            System.out.println("3. Buscar alumno");
            System.out.println("4. Eliminar alumno");
            System.out.println("5. Exportar a XML");
            System.out.println("6. Importar desde XML");
            System.out.println("7. Crear copia de seguridad");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1": {
                    String nombre = "";
                    boolean nombreValido = false;
                    do {
                        System.out.print("Introduce el nombre del alumno: ");
                        nombre = scanner.nextLine().trim();

                        if (!nombre.isEmpty() && nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                            nombreValido = true;
                        } else {
                            System.out.println("Error: El nombre solo puede contener letras y no debe estar vacío.");
                        }
                    } while (!nombreValido);

                    int edad = 0;
                    boolean edadValida = false;
                    do {
                        System.out.print("Introduce la edad del alumno (8-80): ");
                        try {
                            edad = Integer.parseInt(scanner.nextLine().trim());
                            if (edad >= 8 && edad <= 80) {
                                edadValida = true;
                            } else {
                                System.out.println("Error: La edad debe ser entre 8 y 80 años.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Debes introducir un número entero válido.");
                        }
                    } while (!edadValida);

                    double nota = 0.0;
                    boolean notaValida = false;
                    do {
                        System.out.print("Introduce la nota del alumno (0-10): ");
                        try {
                            nota = Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
                            if (nota >= 0.0 && nota <= 10.0) {
                                notaValida = true;
                            } else {
                                System.out.println("Error: La nota debe estar entre 0 y 10.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Debes introducir un número válido.");
                        }
                    } while (!notaValida);

                    try {
                        Alumno alumno = new Alumno(nombre, edad, nota);
                        lista_alumnos.add(alumno);
                        System.out.println("Alumno añadido correctamente.");

                    } catch (NumberFormatException e) {
                        System.out.println("Error: La edad debe ser un número entero y la nota un número válido.");
                    } catch (Exception e) {
                        System.out.println("Ocurrió un error inesperado al registrar el alumno.");
                    }
                    break;
                }
                case "2": {
                    if (lista_alumnos.isEmpty()) {
                        System.out.println("No hay alumnos registrados.");
                    } else {
                        System.out.println("--- Lista de alumnos ---");
                        for (Alumno alumno : lista_alumnos) {
                            System.out.println(alumno);
                        }
                    }
                    break;
                }
                case "3": {
                    break;
                }
                case "4": {
                    break;
                }
                case "5": {
                    break;
                }
                case "6": {
                    break;
                }
                case "7": {
                    break;
                }
                case "8": {
                    System.out.println("Saliendo del programa...");
                    break;
                }
                default: {
                    System.out.println("Opción no válida");
                    break;
                }
            }
        } while (!opcion.equals("8"));
    }
}
