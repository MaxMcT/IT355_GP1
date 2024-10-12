package rule_examples;

/**
 * ERR08-J. Do not catch NullPointerException or any of its ancestors
 * This rule is an example of a way to handle not catching a null pointer exception
 * This is done by handling if it is null before using it
 */
public class ERR08 {

    @SuppressWarnings("unused")
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
