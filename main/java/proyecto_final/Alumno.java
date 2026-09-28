package proyecto_final;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Alumno {
    private static int contadorId = 1;

    private int id;
    private String nombre;
    private int edad;
    private double nota;

    public Alumno(String nombre, int edad, double nota) {
        this.id = contadorId++;
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
                    String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent().trim();
                    String edadTexto = elemento.getElementsByTagName("edad").item(0).getTextContent().trim();
                    String notaTexto = elemento.getElementsByTagName("nota").item(0).getTextContent().trim();

                    int edad = edadTexto.isEmpty() ? 0 : Integer.parseInt(edadTexto);
                    double nota = notaTexto.isEmpty() ? 0.0 : Double.parseDouble(notaTexto);

                    Alumno alumno = new Alumno(nombre, edad, nota);
                    lista_alumnos.add(alumno);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista_alumnos;
    }

    public static void exportar_alumnos() {
        List<Alumno> alumnos = Alumno.getAlumnos();

        try {
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =
                    factory.newDocumentBuilder();

            Document document = builder.newDocument();

            Element raiz =
                    document.createElement("alumnos");

            document.appendChild(raiz);

            for (Alumno alumno : alumnos) {
                Element alumnoElement =
                        document.createElement("alumno");

                raiz.appendChild(alumnoElement);

                Element idElement =
                        document.createElement("id");
                idElement.setTextContent(String.valueOf(alumno.getId()));
                alumnoElement.appendChild(idElement);

                Element nombreElement =
                        document.createElement("nombre");
                nombreElement.setTextContent(alumno.getNombre());
                alumnoElement.appendChild(nombreElement);

                Element edadElement =
                        document.createElement("edad");
                edadElement.setTextContent(String.valueOf(alumno.getEdad()));
                alumnoElement.appendChild(edadElement);

                Element notaElement =
                        document.createElement("nota");
                notaElement.setTextContent(String.valueOf(alumno.getNota()));
                alumnoElement.appendChild(notaElement);
            }

            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer =
                    transformerFactory.newTransformer();
            DOMSource source =
                    new DOMSource(document);
            StreamResult result =
                    new StreamResult(new File("alumnos.xml"));
            transformer.transform(source, result);
            System.out.println("XML creado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void crear_copia_seguridad_alumnos() {

    }
}
