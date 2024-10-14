package bank;

import java.io.FileReader;
import java.io.IOException;

public class Check {

    public static String readFile(String file){
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader("src/main/resources/FIO08.txt")) {
            int character;

            while ((character = reader.read()) != -1) {
                stringBuilder.append(character); // Processing the char and printing the output of the file
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return stringBuilder.toString();
    }
}
