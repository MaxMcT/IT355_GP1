package rule_examples;
// Evan Rogers
/**
 * NUM02-J. Ensure that division and remainder operations do not result in divide-by-zero errors  
 */
public class NUM02 {
    /**
     * Test Case for NUM02, ensuring to check if a number is zero to not divide by it
     * @param args None
     */
    public static void main(String[] args) {
    int num1=5, num2=0, result;
  
    if (num2 == 0) {
        System.out.println("Num2 is zero, not dividing");
    } else {
        result = num1 / num2;
        System.out.println(result);
    }
    }
}
