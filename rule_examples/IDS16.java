import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

/**
 * IDS16-J: Prevent XML Injection
 * This is done by providing a pre-made xml schema
 * if the xml does not match the schema an exception is thrown
 */
public class IDS16 {
    private static void generateValidXML(String innerVal,int age){
        //Set xml statement
        String xmlStr="<Person>\n<Name>" + innerVal + "</Name>\n<Age>" + age + "</Age></Person>";
        InputSource xmlSource = new InputSource(new StringReader(xmlStr));

        //Create a schema factory for xml
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        //define the way exceptions should be handled in the case of invalid xml
        DefaultHandler defaultHandler = new DefaultHandler() {
            @Override
            public void warning(SAXParseException e) throws SAXException {
                throw e;
            }

            @Override
            public void error(SAXParseException e) throws SAXException {
                throw e;
            }

            @Override
            public void fatalError(SAXParseException e) throws SAXException {
                throw e;
            }
        };

        //Create stream to gather schema data
        StreamSource ss = new StreamSource(new File("xml_schema1.xsd")); //edit schema

        try{
            //create the xml schema
            Schema schema = schemaFactory.newSchema(ss);
            //create a saxParser with the context of the schema
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setSchema(schema);
            SAXParser saxParser = saxParserFactory.newSAXParser();
            //get the xml reader form the parser
            XMLReader xmlReader = saxParser.getXMLReader();
            //prevents external entity attacks
            xmlReader.setEntityResolver(new CustomXMLResolver1());
            //parses the xml provided against the schema, entity resolver, and bases its actions off the default handler
            saxParser.parse(xmlSource, defaultHandler);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        System.out.println(xmlStr);
    }

    /**
     * This is a test case for IDS16
     * @param args
     */
    public static void main(String[] args) {

        generateValidXML("Bob",1);
    }
}

