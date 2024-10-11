//FIO5-J Jamie Morrone
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class SecureByteBuffer {

    /**
     * Creating a buffer without exposing the backing array
     * @param data
     * @return data
     */
    public static ByteBuffer createBuffer(String data) {
        return ByteBuffer.wrap(data.getBytes(StandardCharsets.UTF_8)).asReadOnlyBuffer();
    }

    public static void main(String[] args) {
        String sensitiveData = "Sensitive Information";

        //Get a read-only ByteBuffer
        ByteBuffer buffer = createBuffer(sensitiveData);

        //Use the buffer safely without exposing the underlying array
        byte[] readOnlyArray = new byte[buffer.remaining()];
        buffer.get(readOnlyArray);

        System.out.println(new String(readOnlyArray, StandardCharsets.UTF_8));

        //Trying to get the backing array will throw an exception
        try {
            byte[] unsafeArray = buffer.array();
        } catch (UnsupportedOperationException e) {
            System.out.println("Attempt to access backing array was prevented.");
        }
    }
}
