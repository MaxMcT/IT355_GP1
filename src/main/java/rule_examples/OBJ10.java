package rule_examples;

/**
 * OBJ10-J. Do not use public static nonfinal fields
 * This rule ensures when using a public static field, make it a public static final field
 */
public class OBJ10 {
    
    /**
     * Square class to have a square with a default size
     */
    public static class Square {
        public static final int DEFAULT_SIZE = 10; // Correct implementation of using a public static final
        
        /**
         * Method to access default size
         * 
         * @return the default siaze
         */ 
        public static int getDefaultSize() {
            return DEFAULT_SIZE; 
        }
    }
    public static void main(String[] args) {
        Square sq = new Square();
        System.out.println(Square.getDefaultSize());
    }
    
}
