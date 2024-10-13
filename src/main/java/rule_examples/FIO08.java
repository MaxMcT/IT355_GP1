package rule_examples;

import java.io.FileReader;
import java.io.IOException;

/**
 * FIO08-J. Distinguish between characters or bytes read from a stream and -1
 * Thos example shows a safe way of dinstinguishing between a chracter and -1
 * This is done in the while statement condition, checking for -1 in the end of stream indicator, then converting to a char to be able to print the file contents
 */
public class FIO08 {

    /**
     * Test case of reading a file by characters and distinguishing -1
     * @param args None
     */
    public static void main(String[] args) {
        
        try (FileReader reader = new FileReader("src/main/resources/FIO08.txt")) {
            int character;

            while ((character = reader.read()) != -1) {
                System.out.print((char) character); // Processing the char and printing the output of the file
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}