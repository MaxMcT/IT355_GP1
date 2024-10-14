package rule_examples;

/**
 * Do not attempt to help the garbage collector by setting local reference variables to null
 */
public class OBJ54 {


    public void exampleFunction(){
        int exampleVar = 0;
        //doWhatever(int)...
        //exampleVar = null; //this is unnecessary, dont do this.
    }

}
