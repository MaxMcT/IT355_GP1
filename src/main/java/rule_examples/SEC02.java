package rule_examples;

/**
 * SEC02-J: Do not base security checks on untrusted sources
 * 
 * Author: Zach Contreras
 *
 * This class demonstrates the necessity of defensive copying for
 * security checks to prevent TOCTOU vulnerabilities.
 */
public class SEC02 {
    private String trustedInput;

    public void setInput(String input) {
        trustedInput = input; // setting untrusted input
    }

    public void performSecurityCheck() {
        String copyOfInput = new String(trustedInput); // defensive copy of untrusted input
        if (checkSecurity(copyOfInput)) {
            // Proceed with secured operation
        }
    }

    private boolean checkSecurity(String input) {
        // Perform security checks on the input
        return input.equals("SecureData");
    }
}
