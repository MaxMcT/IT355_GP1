//NUM07-J Jamie Morrone
public class NaNCompare {
    public static void main(String[] args) {
        //Create a value that is not a number
        double test = Math.sqrt(-1);
        //Compare using isNaN function
        System.out.println(Double.isNaN(test));
        //Compare incorrectly with equals operator
        System.out.println(test == Double.NaN);
    }
}