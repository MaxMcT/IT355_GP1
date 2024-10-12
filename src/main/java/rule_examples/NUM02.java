package rule_examples;

/**
 * NUM02-J. Ensure that division and remainder operations do not result in divide-by-zero errors  
 */
public class NUM02 {
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
