import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IDS01 {

    private static String normalizationExample() throws IllegalArgumentException {
        //encoded img tag that could be dangerous in a html context
        String injectedAttack = "\u003cimg src=\u0027dangerous image injection\u0027 oneror=alert(1)\u003e";
        //Normalizes the unicode characters so they can be matched with regex
        String normalizedString = Normalizer.normalize(injectedAttack, Normalizer.Form.NFKC);
        //catch potentially dangerous characters with regex
        Pattern pattern = Pattern.compile("[<'\">]"); // not a full list
        Matcher matcher = pattern.matcher(normalizedString);
        if(matcher.find()){
            throw new IllegalArgumentException();
        }
        String html = String.format("<ul><li>Apple</li> %s </li></ul>", injectedAttack);

        return null;

    }

    public static void main(String[] args) {
        try {
            System.out.println(normalizationExample());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
