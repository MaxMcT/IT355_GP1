import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import java.io.IOException;

public class CustomXMLResolver1 implements EntityResolver {

    @Override
    public InputSource resolveEntity(String publicId, String systemId) {
        String entityPath = "xml_schema1.sxd";
        System.out.println(publicId + ": " + systemId);
        if(systemId.equals(entityPath)){
            System.out.println("Custom Entity Resolve:\tPublicId: " + publicId + " systemId: " + systemId);
            return new InputSource(entityPath);
        }else{
            return new InputSource();
        }
    }
}
