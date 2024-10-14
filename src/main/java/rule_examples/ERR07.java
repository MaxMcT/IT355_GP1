package rule_examples;

/**
 * ERR07-J: Do not throw RuntimeException, Exception, or Throwable
 * 
 * Author: Zach Contreras
 *
 * This class demonstrates throwing specific exceptions for better error handling.
 */
public class SpecificExceptionExample {
    public void riskyOperation() throws CustomException {
        boolean errorOccurred = true; 
        if (errorOccurred) {
            throw new CustomException("An error occurred during the operation.");
        }
    }

    public static class CustomException extends Exception {
        public CustomException(String message) {
            super(message); // custom exception with message
        }
    }
}
