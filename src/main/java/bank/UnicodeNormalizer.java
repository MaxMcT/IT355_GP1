package bank;

import java.text.Normalizer;

public class UnicodeNormalizer {
    public String normalize(String input){
        return Normalizer.normalize(input, Normalizer.Form.NFKC);
    }

    //possibly add input validation
}
