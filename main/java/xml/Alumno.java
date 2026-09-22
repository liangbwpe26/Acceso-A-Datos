package xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Alumno {
    private int id;
    private String nombre;
    private int edad;
    private double nota;

    public Alumno(int id, String nombre, int edad, double nota) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "ID: " + id + "\n" +
                "Nombre: " + nombre + "\n" +
                "Edad: " + edad + "\n" +
                "Nota: " + nota + "\n" +
                "-----------------------------";
    }

    public static List<Alumno> getAlumnos() {
        List<Alumno> lista_alumnos = new ArrayList<Alumno>();
        try {
            File archivo = new File("alumnos.xml");

            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            Document documento =
                    builder.parse(archivo);

            NodeList alumnos =
                    documento.getElementsByTagName("alumno");

            for (int i = 0; i < alumnos.getLength(); i++) {
                Node nodo = alumnos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;
                    String idTexto = elemento.getElementsByTagName("id").item(0).getTextContent().trim();
                    String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent().trim();
                    String edadTexto = elemento.getElementsByTagName("edad").item(0).getTextContent().trim();
                    String notaTexto = elemento.getElementsByTagName("nota").item(0).getTextContent().trim();

                    int id = idTexto.isEmpty() ? 0 : Integer.parseInt(idTexto);
                    int edad = edadTexto.isEmpty() ? 0 : Integer.parseInt(edadTexto);
                    double nota = notaTexto.isEmpty() ? 0.0 : Double.parseDouble(notaTexto);

                    Alumno alumno = new Alumno(id, nombre, edad, nota);
                    lista_alumnos.add(alumno);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista_alumnos;
    }
}
