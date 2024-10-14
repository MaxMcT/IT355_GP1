package rule_examples;
//ERRO4-J Jamie Morrone
public class FinallyTest {

    /**
     * Returns value inside finally which causes issues
     * @return value
     */
    public static int FinallyBad()
    {
        int value = 0;
        try {
            value = 1/0;
        } 
        finally{
            System.out.println("Completed.");
            return value;
        }
    }

    /**
     * Returns value outside finally which thrwos exception as expected
     * @return value
     */
    public static int FinallyGood()
    {
        int value = 0;
        try {
            //Creates exception
            value = 1/0;
        } 
        finally{
            System.out.println("Completed.");
        }
        //Return value outside of finally block so that unhandled exception is not dumped
        return value;
    }
    public static void main(String[] args) {
        //Exception was dumped so it will not be caught in the try catch block
        System.out.println("Calling FinallyBad()");
        try {
            int testBad = FinallyBad();
            System.out.println("Returned value from FinallyBad: " + testBad);
        } catch (Exception e) {
            System.out.println("Exception in FinallyBad: " + e);
        }

        //Exception remains and will be caught in the try catch block
        System.out.println("\nCalling FinallyGood()");
        try {
            int testGood = FinallyGood();
            System.out.println("Returned value from FinallyGood: " + testGood);
        } catch (Exception e) {
            System.out.println("Exception in FinallyGood: " + e);
        }
    }
}
