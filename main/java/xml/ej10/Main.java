package xml.ej10;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import xml.Alumno;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
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
                    new StreamResult(new File("alumnos2.xml"));
            transformer.transform(source, result);
            System.out.println("XML creado");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
