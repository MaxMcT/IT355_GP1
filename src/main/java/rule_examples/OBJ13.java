package rule_examples;

/**
 * OBJ13-J: Ensure that references to mutable objects are not exposed
 * 
 * Author: Zach Contreras
 *
 * This class demonstrates the importance of not exposing mutable objects
 * directly to prevent unintended modifications.
 */
public class OBJ13 {
    private final String[] mutableArray;

    public MutableObjectExample() {
        mutableArray = new String[]{"Initial", "Values"};
    }

    // Method returns a defensive copy of the mutable array
    public String[] getMutableArray() {
        return mutableArray.clone(); // returns a clone to protect the original array
    }
}
