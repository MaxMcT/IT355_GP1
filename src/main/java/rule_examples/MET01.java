package rule_examples;

/**
 * Never use assertions to validate method arguments
 *instead of using an assert statement, the values are explicitly checked for being positive in an if statement
 */
public class MET01 {

    public int doPositiveAddition(int x, int y){ //-1 if fail
        if (x >=0 && y >= 0){
            return x+y;
        }
        return -1;
    }
}
