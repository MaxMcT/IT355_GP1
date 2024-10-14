package rule_examples;

import java.io.IOException;
import java.io.InputStream;

/**
 * IDS07-J. Sanitize untrusted data passed to the Runtime.exec() method
 * This is an example of a safe use of Runtime.exec()
 * As the untrusted data is passed through a strict whitelist we can trust the data
 */
public class IDS07 {
    /**
     * returns checks if the untrusted input is valid
     * @param userInput An untrusted input to be passed to a terminal
     * @param windows if true then its being run on a Windows machine if not then its running on a posix machine
     * @return true if the input passes our whitelist
     */
    private static boolean validCmd(String userInput, boolean windows) {
        if(windows) {
            return userInput=="dir";
        }else{//posix
            return userInput=="ls";
        }
    }

    /**
     * Passes untrusted input to a terminal in a safe manner
     * @param userInput a untrusted input
     * @param windows true = windows machine | false = posix
     * @throws IllegalArgumentException The input provided did not pass our validation
     * @throws IOException There was an error reading data from a stream or passing data to runtime.exec
     * @throws InterruptedException There was an error in waitFor while waiting for the process results
     */
    private static void safeExec(String userInput, boolean windows) throws IllegalArgumentException, IOException, InterruptedException {
        //check input against a whitelist before sending to runtime.exec
        if(!validCmd(userInput, windows))
            throw new IllegalArgumentException();
        Runtime runtime = Runtime.getRuntime();
        //pass in sanitized data.
        //would be better to avoid runtime.exec completely
        Process process = runtime.exec("cmd.exe /C " + userInput);
        int result = process.waitFor();
        InputStream in;
        if(result != 0) {
            System.out.println("Process error code: " + result);
            in = process.getErrorStream();
        }else{
            in = process.getInputStream();
        }

        int character;
        while((character = in.read()) != -1){
            System.out.print((char)character);
        }
    }

    /**
     * This is a test case for IDS07
     * @param args None
     */
    public static void main(String[] args) {
        try {
            safeExec("dir", true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
