package rule_examples;

/**
 * Ensure that constructors do not call overridable methods
 * declare methods called in constructor final to ensure they are not overridden
 */
public class MET05 {

    public MET05(){
        exampleMethod(); //method is final to ensure this method is not overridable
    }

    public final void exampleMethod(){
        //do stuff here
    }

}
