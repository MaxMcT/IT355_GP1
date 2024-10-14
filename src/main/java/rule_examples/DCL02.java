package rule_examples;
// Evan Rogers
import java.util.*;

/**
 * DCL02-J Do not modify the collection's elements during an enhanced for statement
 * This can be done by declaring the enhandded for statement loop variables final and processing the list after
 */
public class DCL02 {
    @SuppressWarnings("removal")

    /**
     * Test Case for DCL02
     * @param args None
     */
    public static void main(String[] args) { 
        List<Integer> numList = Arrays.asList(new Integer[] {13, 14,15});
        boolean first_item = true;
        System.out.print("numList before: ");
        for(int i =0;i<numList.size();i++){
            System.out.print(numList.get(i) + " ");          
        }
        System.out.println();
        Integer item = new Integer(0);
        for(final Integer i: numList){ //declaring enhanced for loop variable as final
            item = i;
            if(first_item){
                first_item = false;
                item = new Integer(99);
            }
        }
        System.out.println(" New item: " + item); // Processed after the enhanced for loop

    }
    
}
