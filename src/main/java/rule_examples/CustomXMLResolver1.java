package rule_examples;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.IOException;

/**
 * This class is used to ensure any xml entity being loaded is of a allowed type
 */
public class CustomXMLResolver1 implements EntityResolver {
    /**
     * Checks to see if the provided external entity is valid
     * @param publicId The public identifier of the external entity
     *        being referenced, or null if none was supplied.
     * @param systemId The system identifier of the external entity
     *        being referenced.
     * @return A input source containing the external entity if its valid or an empty InputSource if not
     * @throws IOException There was an error reading in the file
     */
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
