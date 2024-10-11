package rule_examples;

import java.io.Serializable;

/**
 * SER05-J. Do not serialize instances of inner classes
 * This is an example of a safe serialization of an inner class
 * As the inner class is a static class it can be serialized safely
 */
public class SER05 implements Serializable{

    private String fName, lName;

    /**
     * A constructor that accepts a SerializabeBuilder class to instantiate fName and lName
     * @param builder A builder pattern class for SER05
     */
    private SER05(SerializableBuilder builder){
        this.fName = builder.fName;
        this.lName = builder.lName;
    }

    /**
     * This is an example of safe static inner class serialization
     * static classes can be safely serialized as it does not reference instance data from the outer class
     * It is an implementation of a builder pattern for a simple object storing first and last name
     */
    public static class SerializableBuilder implements Serializable {
        private String fName, lName;

        /**
         * sets the fName value
         * @param fName first name
         * @return this class as per the builder pattern
         */
        public SerializableBuilder setFName(String fName){
            this.fName=fName;
            return this;
        }

        /**
         * sets the lName value
         * @param lName last name
         * @return this class as per the builder pattern
         */
        public SerializableBuilder setLName(String lName){
            this.lName=lName;
            return this;
        }

        /**
         * Creates a new SER05 object using the values stored in this class builder
         * @return a new object of SER05
         */
        public SER05 build(){
            return new SER05(this);
        }
    }

    public static void main(String[] args) {
        SER05 test = new SerializableBuilder().setFName("Foo").setLName("Bar").build();

    }
}
