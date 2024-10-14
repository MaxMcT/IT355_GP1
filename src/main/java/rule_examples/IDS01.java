package rule_examples;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Recommendation -> IDS52-J. Prevent code injection
 * IDS01-J. Normalize strings before validating them
 * This is an example of normalizing a string before validation
 */
public class IDS01 {
    /**
     * Normalizes input and validate
     * @return the valid string if one exists
     * @throws IllegalArgumentException An illegal value was passed
     */
    private static String normalizationExample() throws IllegalArgumentException {
        //encoded img tag that could be dangerous in a html context
        String injectedAttack = "\u003cimg src=\u0027dangerous image injection\u0027 onerror=alert(1)\u003e";
        //Normalizes the unicode characters so they can be matched with regex
        String normalizedString = Normalizer.normalize(injectedAttack, Normalizer.Form.NFKC);
        //catch potentially dangerous characters with regex
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher matcher = pattern.matcher(normalizedString);
        if(!matcher.find()){
            throw new IllegalArgumentException();
        }
        String html = String.format("<ul><li>Apple</li> %s </li></ul>", injectedAttack);
        String encodedHtml = HTMLEncode(html);
        return encodedHtml;

    }

    private static String HTMLEncode(String input){
        StringBuilder stringBuilder = new StringBuilder();

        for(char c : input.toCharArray()){
            if(Character.isLetterOrDigit(c) || Character.isWhitespace(c)){
                stringBuilder.append(c);
            }else{
                stringBuilder.append("&#" + (int)c + ";");
            }
        }
        return stringBuilder.toString();
    }
    /**
     * This is a test case for IDS01
     * @param args None
     */
    public static void main(String[] args) {
        try {
            System.out.println(normalizationExample());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
