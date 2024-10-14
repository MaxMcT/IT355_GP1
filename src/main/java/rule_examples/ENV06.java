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
    // Main method for demonstration purposes
    public static void main(String[] args) {
        ENV06 env06 = new ENV06();
        env06.productionMethod();
    }
}
