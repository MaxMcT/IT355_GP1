package rule_examples;


import java.util.Date;

/**
 * OBJ05 Do not return references to private mutable class members
 * copy is returned instead
 */
public class OBJ05 {

    private StringBuffer example = new StringBuffer("test"); //mutable member

    public StringBuffer getExample() {
        return new StringBuffer(example.toString()); //return a copy instead of a direct reference
    }
}
