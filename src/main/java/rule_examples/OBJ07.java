package rule_examples;

/**
 * Sensitive classes must not let themselves be copied
 * class is final and clones are disallowed to prevent copies from being made
 */
final public class OBJ07 {
    private String sensitiveData;
    private boolean sensitiveCondition;

    public final OBJ07 clone() throws CloneNotSupportedException { //prevent clones from being made
        throw new CloneNotSupportedException();
    }

    //sensitive methods here

}
