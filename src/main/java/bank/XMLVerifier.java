package bank;


import org.xml.sax.*;
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

public class XMLVerifier  {

    private class XMLCheckResolver implements EntityResolver{
        @Override
        public InputSource resolveEntity(String publicId, String systemId) throws IOException {
            String entityPath = "src/main/resources/check.dtd";
            File entityFile = new File(entityPath);
            String fullEntityPath = entityFile.getCanonicalPath().replace("\\", "/");
            fullEntityPath = "file:///" + fullEntityPath;
            if(systemId.equals(fullEntityPath)){
                return new InputSource(entityPath);
        }else{
                return new InputSource();
            }
    }

    public boolean validateXML(String xml){
        InputSource xmlSource = new InputSource(new StringReader(xml));

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
        StreamSource ss = new StreamSource(new File("src/main/resources/check.xsd")); //edit schema

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
            xmlReader.setEntityResolver(new XMLCheckResolver());
            //parses the xml provided against the schema, entity resolver, and bases its actions off the default handler
            saxParser.parse(xmlSource, defaultHandler);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    }
}
