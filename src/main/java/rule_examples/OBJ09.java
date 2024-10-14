package rule_examples;

/**
 * OBJ09-J: Compare classes and not class names
 * 
 * Author: Zach Contreras
 *
 * This class illustrates safe comparison of class types using class objects.
 */
public class ClassComparisonExample {
    public void compareClasses() {
        Class<?> classA = String.class; // class reference
        Class<?> classB = String.class; // another class reference
        
        if (classA.equals(classB)) {
            // Classes are the same
            System.out.println("Classes are the same.");
        } else {
            // Classes are different
            System.out.println("Classes are different.");
        }
    }
}
