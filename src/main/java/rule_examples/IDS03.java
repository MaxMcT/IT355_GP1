package rule_examples;

/**
 * IDS03-J: Do not log unsanitized user input
 * 
 * Author: Zach Contreras
 *
 * This class demonstrates safe logging practices to avoid log injection vulnerabilities.
 */
public class IDS03 {
    public void logUserInput(String userInput) {
        String sanitizedInput = sanitizeInput(userInput); // sanitize before logging
        System.out.println("User input: " + sanitizedInput);
    }

    private String sanitizeInput(String input) {
        return input.replaceAll("[<>]", ""); // simple sanitization
    }
}
