package rule_examples;

import java.io.*;

/**
 * SER01-J. Do not deviate from the proper signatures of serialization methods
 * This is an example of a class implementing serialization method with the correct signatures
 */
public class SER01 implements Serializable {

    //Singleton pattern
    private static SER01 SINGLETON = null;
    public String s;

    /**
     * Private constructor for SER01 SINGLETON
     */
    private SER01(){
        s="Foo";
    }

    /**
     * public facing method to act as SER01 SINGLETON constructor
     * @return an existing SERO1 object or a new one if one does not exist
     */
    public static SER01 SER01(){
        if(SINGLETON == null){
            SINGLETON = new SER01();
        }
        return SINGLETON;
    }

    /**
     * get the string value s
     * @return s
     */
    public String getS(){
        return s;
    }

    /**
     * Converts Objects to a serialized form
     * @param stream The stream to write the serialized data to
     * @throws IOException There was an error writing to the stream
     */
    private void writeObject(final ObjectOutputStream stream) throws IOException {
            stream.defaultWriteObject();
    }

    /**
     * Converting serialized form to objects
     * @param stream The stream to read the serialized data from
     * @throws IOException There was an error reading data form the stream
     * @throws ClassNotFoundException No matching class could be found for the provided serialized data
     */
    private void readObject(final ObjectInputStream stream) throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
    }

    /**
     * This method decides to keep the existing instance or replace it with the deserialized object
     * @return a instance containing the updated data
     */
    protected Object readResolve(){
        if(SINGLETON != null){
            SINGLETON.s = this.s;
            return SINGLETON;
        }

        return this;
    }

    /**
     * guarantees the singleton instance will be serialized
     * @return the singleton instance
     */
    protected Object writeReplace(){
        return SINGLETON;
    }

    public static void main(String[] args) {
        SER01 ser01 = SER01();

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/ser01.ser"))){
            outputStream.writeObject(ser01);
            System.out.println("Ser01 example serialized to ser01.ser");
        } catch (IOException e){
            e.printStackTrace();
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("src/main/resources/ser01.ser"))){
            SER01 test = (SER01)inputStream.readObject();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
