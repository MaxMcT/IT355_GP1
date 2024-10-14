package rule_examples;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class SEC51 { 
    // Author: Zach Contreras
    // This code minimizes privileged code to reduce the attack surface

    public void changePassword(String newPassword) {
        // Move non-sensitive code outside of the privileged block
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            // Only perform sensitive operations that require elevated privileges
            System.out.println("Changing password to: " + newPassword);
            return null; // Return type required for PrivilegedAction
        });
        // Additional logic can be performed here safely without elevated privileges
    }
}
