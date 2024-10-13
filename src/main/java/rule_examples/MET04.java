package rule_examples;

/**
 * Do not increase the accessibility of overridden or hidden class methods
 * declare method to be final to prevent a malicious subclass from raising accessibility from protected to public
 */
public class MET04 {

    protected final void exampleMethod(){ //method is declared final to make sure it is not overridden
        //do stuff here
    }

}
