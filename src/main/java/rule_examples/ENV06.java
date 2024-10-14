package rule_examples;

/**
 * ENV06-J: Production code must not contain debugging entry points
 * 
 * Author: Zach Contreras
 *
 * This class ensures that debugging methods are not present in production code.
 */
public class ENV06 {
    // Debug method for testing purposes, should not be in production
    // private static void debugMethod() {
    //     System.out.println("Debugging information");
    // }

    public void productionMethod() {
        // Main logic for production
        System.out.println("Production method executed.");
    }
}
