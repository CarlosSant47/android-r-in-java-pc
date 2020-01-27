package modelo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Context {
    private NodeList listStrings;
    private NodeList listStringArrays;
    private NodeList listIntegers;

    public Context()
    {
        try {
            this.loadStringResources();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public String getString(String resource)
    {
        String result = "";
        for (int x = 0; x < listStrings.getLength(); x++)
        {
            Node node = listStrings.item(x);
            if(node.getAttributes().getNamedItem("name").getNodeValue().equals(resource))
            {
                result = node.getFirstChild().getNodeValue();
                node = null;
                break;
            }
            node = null;
        }
        if(result.matches(""))
            System.out.println("String no encontrado");
        return result;
    }

    public String[] getStringArray(String resource)
    {
        String result[] = {};
        for (int x = 0; x < listStringArrays.getLength(); x++)
        {
            Node node = listStringArrays.item(x);
            if(node.getAttributes().getNamedItem("name").getNodeValue().equals(resource))
            {
                if(node.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    int sizeElements = element.getElementsByTagName("item").getLength();
                    String elements[] = new String[sizeElements];
                    for (int index = 0; index < sizeElements; index++)
                    {
                        elements[index] = element.getElementsByTagName("item").item(index).getTextContent();
                    }
                    result = elements;
                    element = null;
                    node = null;
                    break;
                }
            }
        }
        if(result.length == 0)
        {
            System.out.println("No se encontre el String array");
        }

        return result;
    }

    public int getInteger(String resource)
    {
        int result = 0;
        for (int x = 0; x < listStrings.getLength(); x++)
        {
            Node node = listStrings.item(x);
            if(node.getAttributes().getNamedItem("name").getNodeValue().equals(resource))
            {
                result = Integer.parseInt(node.getFirstChild().getNodeValue());
                node = null;
                break;
            }
            node = null;
        }
        return result;
    }

    private void loadStringResources() throws ParserConfigurationException, IOException, SAXException {
        String langResource = this.detectLangResource();
        URL url = getClass().getResource(langResource);
        File file = new File(url.getPath());
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();
        this.listStrings = doc.getElementsByTagName("string");

        this.listStringArrays = doc.getElementsByTagName("string-array");
        this.listIntegers = doc.getElementsByTagName("integer");
        //laod all string, arrays or integer resources to ram



    }
    private String detectLangResource()
    {
        String lang = System.getProperty("user.language");
        String folderString = "/res/strings/strings_" + lang + ".xml";
        if(getClass().getResource(folderString) == null) {
            System.out.println("Unsupported language");
            return "/res/strings/strings_en.xml";
        }
        else
            return folderString;
    }
}
