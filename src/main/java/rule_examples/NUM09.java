package rule_examples;

/**
 * NUM09-J. Do not use floating-point variables as loop counters
 */
public class NUM09 {
    /**
     * Test case for NUM09 that uses an int instead of a float for a loop counter
     * @param args None
     */
    public static void main(String[] args) {
        for(int i=0;i<2;i++){
            System.out.println(i);
        }
    }
}
