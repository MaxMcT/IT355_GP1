package rule_examples;

/**
 * OBJ11-J: Be wary of letting constructors throw exceptions
 * 
 * Author: Zach Contreras
 *
 * This class demonstrates safe construction practices to prevent
 * partially initialized objects.
 */
public class OBJ11 {
    private final String value;

    public OBJ11(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null"); // ensures object is not partially initialized
        }
        this.value = value;
    }
}
