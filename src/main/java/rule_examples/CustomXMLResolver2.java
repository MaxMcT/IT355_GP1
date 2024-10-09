package rule_examples;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;

public class CustomXMLResolver2 implements EntityResolver {

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
        String entityPath = "placeholder";
        if(systemId.equals(entityPath)){
            System.out.println("Custom Entity Resolve:\tPublicId: " + publicId + " systemId: " + systemId);
            return new InputSource(entityPath);
        }else{
            return new InputSource();
        }
    }
}