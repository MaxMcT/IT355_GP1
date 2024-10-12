package bank;

import java.io.FileReader;
import java.io.IOException;

public class readFile {

    
    public void read(String fileName){

        FileReader reader = null;
        try{

            reader = new FileReader (fileName);
            int character;


            while ((character = reader.read()) != -1){

                System.out.println((char) character);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        finally{
            if(reader !=null){
                try{
                reader.close();
                }
                catch(IOException e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }


    public void createFile(String fileName){

        
    }
    
}
