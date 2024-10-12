package rule_examples;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MET00-J. Validate method arguments
 */
public class MET00 {
    /**
     * Validates that the string only contains alphabetical characters
     * @param s provided string
     * @return s if alphabetic or an error message if not
     */
    public static String printAlphabeticalString(String s){
        if(s == null){
            return "No input was provided";
        }
        Pattern pattern = Pattern.compile("^[a-zA-Z]$");
        Matcher matcher = pattern.matcher(s);

        if(!matcher.find()){
            return "Invalid Input";
        }

        return s;
    }

    /**
     * This is a test case for MET00
     * @param args None
     */
    public static void main(String[] args) {
        System.out.println(printAlphabeticalString("1234"));
    }
}
