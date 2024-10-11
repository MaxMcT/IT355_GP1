//NUM00-J Jamie Morrone
public class IntegerOverflowTest {

    /**
     * Simple add function does not check for integer overflow
     * @param x
     * @param y
     * @return x+y
     */
    public int addBad(int x, int y) {
        return x + y; // This may overflow
    }

    /**
     * Check for integer overflow before adding
     * @param x
     * @param y
     * @return x+y
     */
    public int addGood(int x, int y) {
        if ((x > 0 && y > (Integer.MAX_VALUE - x)) || 
            (x < 0 && y < (Integer.MIN_VALUE - x))) {
            throw new ArithmeticException("Integer overflow");
        }
        return x + y;
    }

    public static void main(String[] args) {
        IntegerOverflowTest example = new IntegerOverflowTest();

        int x = Integer.MAX_VALUE;
        int y = 1;

        //Will cause an integer overflow
        try {
            System.out.println("Non-compliant add: " + example.addBad(x, y));
        } 
        catch (Exception e) {
            System.out.println("Error in non-compliant add: " + e.getMessage());
        }

        //Error is thrown so it will be caught
        try {
            System.out.println("Compliant add: " + example.addGood(x, y));
        } 
        catch (ArithmeticException e) {
            System.out.println("Error in compliant add: " + e.getMessage());
        }
    }
}
