public class Bank {

    /**
     * Creates file and closes it before the termination of the program
     * @param filename
     * @throws IOException
     */
    private static void createFile(String filename) throws IOException {
        FileWriter out = null;
        try {
            out = new FileWriter(filename);
            //Do something with file
        } 
        catch (IOException e) {
            System.out.println("File failed to create: " + e);
        }
        finally {
            if(out != null) {
                out.close();
            }
        }
    )
        
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

  }
}
