package rule_examples;

import java.io.File;


/**
 * FIO02-J. Detect and handle file-related errors
 */
public class FIO02 {

    public static void main(String[] args) {
        File file = new File("src/main/resources/FIO02.txt");

        if (!file.exists()) {
            System.out.println("File does not exist: " + file.getAbsolutePath());
        }

        try {
            if (!file.delete()) {
                System.out.println("Failed to delete the file: " + file.getAbsolutePath());
            } else {
                System.out.println("File deleted successfully: " + file.getAbsolutePath());
            }
        } catch (SecurityException e) {
            System.out.println("Permission denied while deleting the file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error while deleting the file: " + e.getMessage());
        }
    }
}   