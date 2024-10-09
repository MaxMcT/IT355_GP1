package rule_examples;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * IDS17-J: Prevent XML External Entity Attacks
 * This is done by providing a custom xmlResolver
 * The resolver checks any entities against a whitelist
 */
public class IDS17 {

    /**
     * This method shows a safe implementation of processing external entities in xml
     * @param inputStream stream containg the xml
     * @param defaultHandler handler to contain exception
     * @throws ParserConfigurationException The xml is configured incorrectly
     * @throws SAXException Can be caused by any of the SAX methods
     * @throws IOException Error reading the object in from an input stream or source
     */
    private static void safeHandlingOfExternalEntities(InputStream inputStream, DefaultHandler defaultHandler)
            throws ParserConfigurationException, SAXException, IOException{

        //create a SAX parser to read the xml
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();

        //get the xml reader in saxParsers and edit it
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setEntityResolver(new CustomXMLResolver1());
        xmlReader.setContentHandler(defaultHandler);

        //run the input through the parser
        InputSource inputSource = new InputSource(inputStream);
        xmlReader.parse(inputSource);
    }

    /**
     * This is a test case for IDS17
     * @param args None
     * @throws ParserConfigurationException The xml is configured incorrectly
     * @throws SAXException Can be caused by any of the SAX methods
     * @throws IOException Error reading the object in from an input stream or source
     */
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
        try{
            safeHandlingOfExternalEntities(Files.newInputStream(Paths.get("src/main/resources/gladiator.xml")), new DefaultHandler());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
