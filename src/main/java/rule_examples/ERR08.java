package rule_examples;
// Evan Rogers
/**
 * ERR08-J. Do not catch NullPointerException or any of its ancestors
 * This rule is an example of a way to handle not catching a null pointer exception
 * This is done by handling if it is null before using it
 */
public class ERR08 {

    @SuppressWarnings("unused")
    /**
     * Test Case showing how to deal with a null instead of catching a null pointer exception
     * @param args None
     */
    public static void main(String[] args) {
        String name = null;
        if(name!=null){   // Checks to see if name is null before using it
            System.out.println(name);
        }
        else{
            System.out.println("String is null");
        }
    }
    
}
