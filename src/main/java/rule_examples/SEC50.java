package rule_examples;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class SEC50 { 
    // Author: Zach Contreras
    // This code demonstrates granting only necessary privileges in a secured operation

    public void secureOperation() {
        // Perform a privileged action with minimal necessary permissions
        AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
            // This code block can safely access secured resources
            System.out.println("Accessing sensitive resource...");
            return null; // Return type required for PrivilegedAction
        });
    }
}
