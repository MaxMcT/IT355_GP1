package rule_examples;
// Evan Rogers
/**
 * OBJ08-J. Do not expose private members of an outer class from within a nested class
 * This code shows how the test class should have a compilation error after attempting to access a private nested class
 */
 class Outer { 
    private String secret = "data";
    private class Inner{
      private void getData(){
        System.out.println(secret);
      }
    }
  }
  /**
   * Test class to demonstrate private members of an outer class not being exposed
   */
  public class OBJ08 {
    public static void main(String[] args) {
    Outer sensitive =  new Outer();
    //Outer.Inner data = sensitive.new Inner(); //Fails to compile, class attempts to access a private nested class
    }
  }