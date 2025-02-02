package rule_examples;
// Evan Rogers
/**
 * NUM50-J. Convert integers to floating point for floating-point operations
 */
public class NUM50 {
   /**
    * Test case for NUM50-J, converts what would be an int to a float to ensure to not lose data through integer division
    * @param args
    */
    public static void main(String[] args) {    
    int x = 5;
    int y = 12;
    double result1 = x/2.0f; //Convert to float for a floating point operation
    double result2 = y/10.0f;

    System.out.println(result1);
    System.out.println(result2);
    }
}
