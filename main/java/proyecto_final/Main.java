package proyecto_final;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorAlumnos gestor = new GestorAlumnos();
        Scanner scanner = new Scanner(System.in);
        String opcion = "";

        do {
            System.out.println("=================================");
            System.out.println("|       GESTOR DE ALUMNOS       |");
            System.out.println("=================================");
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
                    String nombre = validarNombre(scanner);
                    int edad = validarEdad(scanner);
                    double nota = validarNota(scanner);

                    Alumno nuevoAlumno = new Alumno(nombre, edad, nota);
                    gestor.agregarAlumno(nuevoAlumno);
                    System.out.println("Alumno añadido correctamente.");
                    break;
                }
                case "2": {
                    List<Alumno> alumnos = gestor.getTodosLosAlumnos();
                    if (alumnos.isEmpty()) {
                        System.out.println("No hay alumnos registrados.");
                    } else {
                        for (Alumno a : alumnos) {
                            System.out.println(a);
                        }
                    }
                    break;
                }
                case "3": {
                    String nombre = validarNombre(scanner);
                    List<Alumno> encontrados = gestor.buscarPorNombre(nombre);

                    if (encontrados.isEmpty()) {
                        System.out.println("No se encontraron alumnos.");
                    } else {
                        for (Alumno a : encontrados) {
                            System.out.println(a);
                        }
                    }
                    break;
                }
                case "4": {
                    System.out.print("Ingresa el nombre del alumno a eliminar: ");
                    String nombre = validarNombre(scanner);
                    boolean eliminado = gestor.eliminarAlumno(nombre);
                    if (eliminado) {
                        System.out.println("Alumno eliminado con éxito.");
                    } else {
                        System.out.println("No se encontró al alumno.");
                    }
                    break;
                }
                case "5": {
                    try {
                        gestor.guardarDatos();
                        System.out.println("Datos exportados a XML con éxito.");
                    } catch (Exception e) {
                        System.out.println("Error al exportar: " + e.getMessage());
                    }
                    break;
                }
                case "6": {
                    try {
                        gestor.cargarDatos();
                        System.out.println("Datos importados con éxito.");
                    } catch (Exception e) {
                        System.out.println("Error al importar: " + e.getMessage());
                    }
                    break;
                }
                case "7": {
                    boolean exito = gestor.crearCopiaSeguridad();
                    if (exito) {
                        System.out.println("Copia de seguridad creada con éxito.");
                    } else {
                        System.out.println("Error al crear la copia de seguridad.");
                    }
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

    private static double validarNota(Scanner scanner) {
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
        return nota;
    }

    private static int validarEdad(Scanner scanner) {
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
        return edad;
    }

    private static String validarNombre(Scanner scanner) {
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
        return nombre;
    }
}
