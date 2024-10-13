package rule_examples;

/**
 * Provide mutable classes with copy functionality to safely allow passing instances to untrusted code
 */
public class OBJ04 {

    private final StringBuffer buffer;

    public OBJ04(OBJ04 copy){
        buffer = copy.getBuffer();
    }

    public StringBuffer getBuffer(){
        return new StringBuffer(buffer.toString()); //return a copy
    }
}
