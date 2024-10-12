package rule_examples;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * SER12-J. Prevent deserialization of untrusted data
 * This is an example of using safely using a whitelist to determine deserialization
 */
public class SER12 extends ObjectInputStream {
    public Set whiteList;

    /**
     * Instantiate SER12 with an input stream and a whitelist
     * @param in an input stream
     * @param whiteList a list of classes allowed to deserialize
     * @throws IOException error in provided input stream
     */
    public SER12(InputStream in, Set whiteList) throws IOException {
        super(in);
        this.whiteList=whiteList;
    }

    /**
     * Override the resolveClass to use your whitelist
     * @param desc an instance of class {@code ObjectStreamClass}
     * @return A class object corresponding to desc
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        if(!whiteList.contains(desc.getName())){
            throw new InvalidClassException("Invalid Class: " + desc.getName());
        }
        return super.resolveClass(desc);
    }

    /**
     * This is a test case for IDS12
     * @param args None
     * @throws IOException error reading in the serialized object
     * @throws ClassNotFoundException serialized object does not deserialize to a recognized object
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Object obj;

        //create whitelist of allowed classes
        Set whitelist =  new HashSet<String>(Arrays.asList(new String[]{"rule_examples.SER01"}));

        //create channel to read in the ser file
        try (FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/SER01.ser"));
            FileChannel fileChannel = fileInputStream.getChannel()){

                //read file contents into a buffer
                ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size());
                fileChannel.read(byteBuffer);
                //the buffer is written backwards when read in so flip
                byteBuffer.flip();

            //safely serialize file
            try (ByteArrayInputStream bias = new ByteArrayInputStream(byteBuffer.array());
                SER12 ser12 = new SER12(bias, whitelist))
                {
                    obj = ser12.readObject();
                }
            }
        System.out.println(((SER01)obj).getS());
    }
}
