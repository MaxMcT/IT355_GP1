//NUM54-J Jamie Morrone
import java.math.BigDecimal;
import java.math.MathContext;

public class DenormalizedTest {
    
    public static void main(String[] args) {
        //A very small value that is in the denormalized range
        double numBad = 1.0e-310; 
        
        for (int i = 0; i < 100; i++) {
            //Further decreases the value, potentially making it denormalized
            numBad /= 10;
        }
        
        //Using BigDecimal for more precise numbers and divison
        BigDecimal numGood = new BigDecimal("1.0e-310", MathContext.DECIMAL128);
        for (int i = 0; i < 100; i++) {
            //Precise divison
            numGood = numGood.divide(BigDecimal.TEN, MathContext.DECIMAL128); 
        }

        //Prints 0.0 (denormalized)
        System.out.println("Final value: " + numBad);
        //Prints correct output: 1E-410
        System.out.println("Final value: " + numGood.toString());
    }
}
