package rule_examples;

/**
 * SEC04-J: Protect sensitive operations with security manager checks
 * 
 * Author: Zach Contreras
 *
 * This class demonstrates how to enforce security checks on sensitive operations.
 */
public class SensitiveOperation {
    public void performOperation() {
        // Security check to ensure operation is allowed
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkPermission(new RuntimePermission("sensitiveOperation")); // check permissions
        }
        // Proceed with sensitive operation
        System.out.println("Sensitive operation executed.");
    }
}
