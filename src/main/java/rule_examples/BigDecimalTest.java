//NUM10-J Jamie Morrone
import java.math.BigDecimal;

public class BigDecimalTest{
    public static void main(String[] args) {
        //Incorrect decimal constructor, will not print as expected
        BigDecimal bad = new BigDecimal(0.4);
        //Use valueOf to pass float values with precision 
        BigDecimal good = BigDecimal.valueOf(0.4);
        System.out.println("Incorrect use" + bad);
        System.out.println("Correct use: " + good);
    }
}
