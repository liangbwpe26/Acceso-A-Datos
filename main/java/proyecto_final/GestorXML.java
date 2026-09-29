package proyecto_final;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class GestorXML {

    private final String RUTA_ARCHIVO = "alumnos.xml";

    public List<Alumno> importarAlumnos() throws Exception {
        List<Alumno> lista = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);

        if (!archivo.exists()) {
            return lista;
        }

        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document documento = builder.parse(archivo);
        NodeList alumnos = documento.getElementsByTagName("alumno");

        for (int i = 0; i < alumnos.getLength(); i++) {
            Node nodo = alumnos.item(i);
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element elemento = (Element) nodo;
                String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent().trim();
                int edad = Integer.parseInt(elemento.getElementsByTagName("edad").item(0).getTextContent().trim());
                double nota = Double.parseDouble(elemento.getElementsByTagName("nota").item(0).getTextContent().trim());

                lista.add(new Alumno(nombre, edad, nota));
            }
        }
        return lista;
    }

    public void exportarAlumnos(List<Alumno> alumnos) throws Exception {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = builder.newDocument();
        Element raiz = document.createElement("alumnos");
        document.appendChild(raiz);

        for (Alumno alumno : alumnos) {
            Element alumnoElement = document.createElement("alumno");

            Element nombreElement = document.createElement("nombre");
            nombreElement.setTextContent(alumno.getNombre());
            alumnoElement.appendChild(nombreElement);

            Element edadElement = document.createElement("edad");
            edadElement.setTextContent(String.valueOf(alumno.getEdad()));
            alumnoElement.appendChild(edadElement);

            Element notaElement = document.createElement("nota");
            notaElement.setTextContent(String.valueOf(alumno.getNota()));
            alumnoElement.appendChild(notaElement);

            raiz.appendChild(alumnoElement);
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(new DOMSource(document), new StreamResult(new File(RUTA_ARCHIVO)));
    }

    public void crearCopiaSeguridadXML() throws Exception {
        Path origen = Path.of("alumnos.xml");
        Path destino = Path.of("alumnos_backup.xml");

        Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
    }

    public void crearCopiaSeguridadTexto(List<Alumno> alumnos) throws Exception {
        Path fichero = Path.of("alumnos_backup.txt");

        try (BufferedWriter bw = Files.newBufferedWriter(fichero)) {
            for (Alumno alumno : alumnos) {
                bw.write(alumno.getId() + ";" + alumno.getNombre() + ";" +
                        alumno.getEdad() + ";" + alumno.getNota());
                bw.newLine();
            }
        }
    }
}