package rule_examples;

import java.util.ArrayList;
import java.util.List;

public class OBJ52 { 
    // Author: Zach Contreras
    // This code demonstrates garbage-collection-friendly practices by reusing objects

    public void manageMemory() {
        // Create a list to hold objects
        List<String> items = new ArrayList<>();

        // Use a loop to add elements
        for (int i = 0; i < 10; i++) {
            // Reuse the StringBuilder to minimize garbage creation
            StringBuilder sb = new StringBuilder();
            sb.append("Item ").append(i);
            items.add(sb.toString()); // Store the result in the list
        }

        // Use the items for processing
        processItems(items);
    }

    private void processItems(List<String> items) {
        // Process the list items
        for (String item : items) {
            System.out.println(item);
        }
    }
}
