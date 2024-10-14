package rule_examples;

/**
 * Minimize the accessibility of classes and their members
 * classes and their members should have the minimal amount of access needed for them to function
 */
final class OBJ51 {
    private final int option1;
    private final int option2;

    OBJ51(int option1, int option2){
        this.option1 = option1;
        this.option2 = option2;
    }

    public void printOptions(){
        System.out.println("option 1: " + option1);
        System.out.println("option 2: " + option2);
    }
}
