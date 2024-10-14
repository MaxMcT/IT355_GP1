package rule_examples;

public class MSC51 { 
    // Author: Zach Contreras
    // This code demonstrates correct usage of conditional statements without semicolons

    public void checkCondition(int a, int b) {
        // Correctly checking a condition without an immediately following semicolon
        if (a == b) {
            System.out.println("a is equal to b");
        } else {
            System.out.println("a is not equal to b");
        }
    }
}
