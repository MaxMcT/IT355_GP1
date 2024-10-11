package rule_examples;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SER12 extends ObjectInputStream {
    public Set whiteList;
    public SER12(InputStream in, Set whiteList) throws IOException {
        super(in);
        this.whiteList=whiteList;
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
        if(!whiteList.contains(desc.getName())){
            throw new InvalidClassException("Invalid Class: " + desc.getName());
        }
        return super.resolveClass(desc);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Object obj;

        Set whitelist =  new HashSet<String>(Arrays.asList(new String[]{"SER01"}));
        //have to read in value to byte buffer
        try (ByteArrayInputStream bias = new ByteArrayInputStream(new byte[300])){
            try(SER12 ser12 = new SER12(bias, whitelist)){
                obj = ser12.readObject();
            }
        }
        System.out.println(((SER01)obj).getS());
    }
}
