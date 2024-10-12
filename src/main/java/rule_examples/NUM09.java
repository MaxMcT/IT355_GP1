package rule_examples;

/**
 * NUM09-J. Do not use floating-point variables as loop counters
 */
public class NUM09 {
    
    public static void main(String[] args) {
        for(int i=0;i<2;i++){
            System.out.println(i);
        }
    }
}
