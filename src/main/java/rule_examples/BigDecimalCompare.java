package rule_examples;
//NUM11-J Jamie Morrone
import java.math.BigDecimal;

public class BigDecimalCompare{
    public static void main(String[] args) {
        //Create String representation of 0.0000001 -> 1.0E-7
        BigDecimal good1 = new BigDecimal(Double.toString(0.0000001));
        //Returns 0
        System.out.println(good1.compareTo(new BigDecimal("0.0000001")));
        System.out.println(good1);
}
    }
