package rule_examples;

/**
 * Do not invoke overridable methods in clone
 * methods in clone are final to ensure they are not overridden
 */
public class MET06 implements Cloneable{
    private int test;
    private String example;

    public Object clone() throws CloneNotSupportedException {
        final MET06 clone = (MET06) super.clone();
        clone.setValues(); //would cause issues if overridden, thus method is final to prevent that
        return clone;
    }

    final void setValues(){ //set to final so it cannot be overridden
        test = 1;
        example = "wow";
    }
}


