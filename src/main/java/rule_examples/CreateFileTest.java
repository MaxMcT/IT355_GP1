package rule_examples;
// FIO14-J Jamie Morrone
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


class CreateFileTest{
    /**
     * Creates file but is not closed before program exits, leaving the file blank
     * @param filename
     * @throws IOException
     */
    private static void createFileBad(String filename) throws IOException {
        FileWriter out = new FileWriter(filename);
        out.write("hello");
        //Error that would cause premature termination of the program
        Runtime.getRuntime().exit(1);
        
        out.close();
    }
    /**
     * Creates file and closes it before the termination of the program
     * @param filename
     * @throws IOException
     */
    private static void createFileGood(String filename) throws IOException {
        FileWriter out = null;
        try {
            out = new FileWriter(filename);
            out.write("hello");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        //Close the file before terminating the program so that data isn't lost
        finally{
            out.close();
        }
        Runtime.getRuntime().exit(1);
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Enter 1 for bad file test and 2 for good file test: ");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        if(input == 1)
            createFileBad("test1.txt");
        if(input == 2)
            createFileGood("test2.txt");
    }
}
