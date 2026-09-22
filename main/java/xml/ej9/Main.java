package xml.ej9;

import xml.Alumno;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Alumno> alumnos = Alumno.getAlumnos();

        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
    }
}