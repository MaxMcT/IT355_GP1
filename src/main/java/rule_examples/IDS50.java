package rule_examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IDS55-J. Understand how escape characters are interpreted when strings are loaded - recommendation
 */
public class IDS50 {
    /**
     * Verify that the filename only uses basic characters
     * @param filename proposed file name
     * @return true if valid false if not
     */
    public static boolean verifyFileName(String filename){
        Pattern pattern = Pattern.compile("[^A-Za-z0-9._]");
        Matcher matcher = pattern.matcher(filename);
        return !matcher.find();
    }

    /**
     * This is a test case for IDS12
     * @param args None
     */
    public static void main(String[] args) {
        System.out.println(verifyFileName("test.txt"));
    }


}
