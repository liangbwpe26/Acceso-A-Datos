package proyecto_final;

import java.util.ArrayList;
import java.util.List;

public class GestorAlumnos {
    private List<Alumno> listaAlumnos;
    private GestorXML gestorXML;

    public GestorAlumnos() {
        this.listaAlumnos = new ArrayList<>();
        this.gestorXML = new GestorXML();
    }

    public void agregarAlumno(Alumno alumno) {
        listaAlumnos.add(alumno);
    }

    public List<Alumno> getTodosLosAlumnos() {
        return listaAlumnos;
    }

    public List<Alumno> buscarPorNombre(String nombre) {
        List<Alumno> encontrados = new ArrayList<>();
        for (Alumno alumno : listaAlumnos) {
            if (alumno.getNombre().equalsIgnoreCase(nombre)) {
                encontrados.add(alumno);
            }
        }
        return encontrados;
    }

    public boolean eliminarAlumno(String nombre) {
        for (int i = 0; i < listaAlumnos.size(); i++) {
            if (listaAlumnos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                listaAlumnos.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean crearCopiaSeguridad() {
        try {
            gestorXML.crearCopiaSeguridadXML();
            gestorXML.crearCopiaSeguridadTexto(listaAlumnos);
        } catch (Exception e) {
            System.out.println("Error al crear la copia de seguridad: " + e.getMessage());
            return false;
        }
        return true;
    }

    public void guardarDatos() throws Exception {
        gestorXML.exportarAlumnos(listaAlumnos);
    }

    public void cargarDatos() throws Exception {
        this.listaAlumnos = gestorXML.importarAlumnos();
    }
}