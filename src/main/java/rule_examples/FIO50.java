package rule_examples;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * FIO50-J. Do not make assumptions about file creation
 */
public class FIO50 {

    public static void main(String[] args) {
        try {
            createFile("file1");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("File created");
    }

    /**
     * Creates a file, while checking for file related errors
     * takes in a string filename
     * @param filename
     * @throws FileNotFoundException
     */
public static void createFile(String filename) throws FileNotFoundException{
    try (OutputStream out = new BufferedOutputStream(
         Files.newOutputStream(Paths.get(filename), StandardOpenOption.CREATE_NEW))) {
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}
}
