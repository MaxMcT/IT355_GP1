package bank;

import java.text.Normalizer;

public class UnicodeNormalizer {
    public static String normalize(String input){
        return Normalizer.normalize(input, Normalizer.Form.NFKC);
    }

    public static String validate(String input){

        String s =  Normalizer.normalize(input, Normalizer.Form.NFKC);
        s.
    }
}
