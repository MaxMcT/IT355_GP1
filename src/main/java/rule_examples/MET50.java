package rule_examples;

/**
 * Avoid ambiguous or confusing uses of overloading
 * use factory methods with distinct names rather than overloaded constructors
 */
public class MET50 {

    public static MET50 constructA(int i, String b){
        //constructA code
        return null;
    }

    public static MET50 constructB(String b, int i){
        //constructB code
        return null;
    }

    public static MET50 constructC(double j, int i, String b){
        //constructB code
        return null;
    }

}
