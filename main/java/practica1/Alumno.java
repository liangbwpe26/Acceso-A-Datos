package practica1;

public class Alumno {
    private int id;
    private String nombre;
    private String apellidos;
    private int edad;
    private double nota;

    public Alumno() {
    }

    public Alumno(int id, String nombre, String apellidos, int edad, double nota) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String toCsv() {
        return id + ";" + nombre + " " + apellidos + ";" + edad + ";" + nota;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Nombre: " + nombre + " " + apellidos + "\n" +
                "Edad: " + edad + "\n" +
                "Nota: " + nota + "\n" +
                "----------------";
    }
}