//OBJ03-J Jamie Morrone
import java.util.ArrayList;
import java.util.List;

public class HeapPollutionTest {

    /**
     * Heap pollution from using Object obj parameter instead of T
     * @param list
     * @param obj
     */
    private static void addUnsafe(List list, Object obj) {
        list.add(obj);
    }

    /**
     * Using wrong data type for list and unsafe add method
     */
    public static void polluteHeap() {
        List<String> strings = new ArrayList<>();
        //Adding ints to a list of strings will cause errors
        addUnsafe(strings, 42); 
        
        for (String s : strings) {
            System.out.println(s);
        }
    }

    /**
     * Safe method with generics that prevents heap pollution
     * @param <T>
     * @param list
     * @param obj
     */
    private static <T> void addSafe(List<T> list, T obj) {
        list.add(obj);
    }

    /**
     * Calling the addSafe method using T and adding the correct data type to the list
     */
    public static void cleanHeap() {
        List<String> strings = new ArrayList<>();
        //Adding strings to list of strings
        addSafe(strings, "Hello");
        
        for (String s : strings) {
            System.out.println(s);
        }
    }
    public static void main(String[] args) {
        System.out.println("Non-Compliant Example:");
        try {
            polluteHeap();
        } 
        catch (ClassCastException e) {
            System.out.println("Caught ClassCastException: " + e.getMessage());
        }
        
        System.out.println("\nCompliant Example:");
        cleanHeap();
    }
}