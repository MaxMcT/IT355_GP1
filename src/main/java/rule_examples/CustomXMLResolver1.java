package rule_examples;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.IOException;

public class CustomXMLResolver1 implements EntityResolver {

    @Override
    public InputSource resolveEntity(String publicId, String systemId) throws IOException {
        String entityPath = "src/main/resources/movie.dtd";
        File entityFile = new File(entityPath);
        String fullEntityPath = entityFile.getCanonicalPath().replace("\\", "/");
        fullEntityPath = "file:///" +fullEntityPath;
        if(systemId.equals(fullEntityPath)){
            return new InputSource(entityPath);
        }else{
            return new InputSource();
        }
    }
}
