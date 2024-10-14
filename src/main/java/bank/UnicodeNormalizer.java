package bank;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnicodeNormalizer {
    public static String normalize(String input){
        return Normalizer.normalize(input, Normalizer.Form.NFKC);
    }

    public static String validate(String input){
        String s =  Normalizer.normalize(input, Normalizer.Form.NFKC);
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+$"); // not a full list
        Matcher matcher = pattern.matcher(s);
        if(!matcher.find()){
            throw new IllegalArgumentException();
        }
        return s;
    }
}
