package rule_examples;
// Evan Rogers
/**
 * ERR50-J. Use exceptions only for exceptional conditions
 */
public class ERR50 {
    /**
     * Test case for ERR50, uses strings.length to account for array out of bounds exception instead 
     * of catching the arrayoutofbounds exception
     * @param args None
     */
    public static void main(String[] args) {
        String result = "";
        String strings[] = {"Sam", "billy", "Joe"};
        for (int i = 0; i < strings.length; i++) { //Use strings.length to ensure the array doesn't go out of bounds, instead of using array out of bounds exception
        result = result.concat( strings[i]);
        }
        System.out.println(result);
    }
}
