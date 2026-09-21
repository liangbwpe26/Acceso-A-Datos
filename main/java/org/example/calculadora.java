package org.example;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class calculadora {
    public static void main(String[] args) {
        String opcion = "";
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("CALCULADORA");
            for (String s : Arrays.asList("1- Suma", "2- Resta", "3- Multiplicación", "4- División", "5- Resto", "0- Salir")) {
                System.out.println(s);
            }
            System.out.print("Introduce una opción: ");
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1": {
                    boolean flag = false;

                    do {
                        try {
                            System.out.println("Introduce el primer número: ");
                            double num1 = scanner.nextDouble();
                            System.out.println("Introduce el segundo número: ");
                            double num2 = scanner.nextDouble();

                            double suma_total = num1 + num2;
                            System.out.println("La suma de tus números es: " + suma_total);

                            flag = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Debes introducir un número válido.");
                            scanner.nextLine();
                        }
                    } while (!flag);

                    break;
                }
                case "2": {
                    boolean flag = false;

                    do {
                        try {
                            System.out.println("Introduce el primer número: ");
                            double num1 = scanner.nextDouble();
                            System.out.println("Introduce el segundo número: ");
                            double num2 = scanner.nextDouble();

                            double resta_total = num1 - num2;
                            System.out.println("La suma de tus números es: " + resta_total);

                            flag = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Debes introducir un número válido.");
                            scanner.nextLine();
                        }
                    } while (!flag);


                    break;
                }
                case "3": {

                }
                case "4": {

                }
                case "5": {

                }
                case "0": {

                }

                default: {
                    System.out.println("Opción no válida");
                }
            }
            scanner.nextLine();
        } while (!opcion.equals("0"));
    }
}
