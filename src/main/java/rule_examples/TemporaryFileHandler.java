package rule_examples;
//FIO03-J Jamie Morrone
import java.io.File;
import java.io.IOException;

public class TemporaryFileHandler {

    
    /**
     * Temporary file is created but not deleted before termination
     */
    public void createTempFileBad() {
        File tempFile = null;
        try {
            tempFile = File.createTempFile("temp", ".txt");
            System.out.println("Temporary file created: " + tempFile.getAbsolutePath());
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        //Temporary file is not deleted
    }

    /**
     * Temporary file is created and deleted before termination
     */
    public void createTempFileGood() {
        File tempFile = null;
        try {
            tempFile = File.createTempFile("temp", ".txt");
            System.out.println("Temporary file created: " + tempFile.getAbsolutePath());
        } 
        catch (IOException e) {
            System.out.println("Error creating file: " + e);
        } 
        finally {
            if (tempFile != null && tempFile.exists()) {
                //Temp file no longer in use and is deleted
                boolean deleted = tempFile.delete();
                if (deleted) {
                    System.out.println("Temporary file deleted: " + tempFile.getAbsolutePath());
                } 
                else {
                    System.err.println("Failed to delete temporary file: " + tempFile.getAbsolutePath());
                }
            }
        }
    }
    public static void main(String[] args) {
        TemporaryFileHandler handler = new TemporaryFileHandler();
        
        // Run the non-compliant example
        handler.createTempFileBad();

        // Run the compliant example
        handler.createTempFileGood();
    }
}
