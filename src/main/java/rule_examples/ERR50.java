package rule_examples;

/**
 * ERR50-J. Use exceptions only for exceptional conditions
 */
public class ERR50 {
    
    public static void main(String[] args) {
        String result = "";
        String strings[] = {"Sam", "billy", "Joe"};
        for (int i = 0; i < strings.length; i++) { //Use strings.length to ensure the array doesn't go out of bounds, instead of using array out of bounds exception
        result = result.concat( strings[i]);
        }
        System.out.println(result);
    }
}
