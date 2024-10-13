package bank;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TransactionReport implements Serializable {
    ArrayList<String> transactions;

    public ArrayList<String> loadTransactions(String name) throws SQLException, IOException, ClassNotFoundException {
        ByteArrayInputStream inputStream = (ByteArrayInputStream)SafeSQL.retrieveTransactions(name);
        Set<String> whitelist = new HashSet<String>(Arrays.asList(new String[]{"java.util.ArrayList", "bank.TransactionReport"}));

        if(inputStream == null)
            return null;

        SafeSerialization safeSerialization = new SafeSerialization(inputStream, whitelist);
        TransactionReport transactionReport = (TransactionReport) safeSerialization.readObject();
        transactions = transactionReport.transactions;
        return transactions;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public void addTransaction(String name, String transaction){
        if(transactions==null){
            transactions=new ArrayList<>();
        }
        transactions.add(transaction);
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
     * This method explicitly states to read in the object being serialized
     * @return an instance containing the updated data
     */
    protected Object readResolve(){
        return this;
    }

    /**
     * This method explicitly states to write in the object being serialized
     * @return the object being serialized
     */
    protected Object writeReplace(){
        return this;
    }

    private class SafeSerialization extends ObjectInputStream {
        public Set whitelist;

        /**
         * Instantiate SafeSerialization with an input stream and a whitelist
         * @param in an input stream
         * @param whitelist a list of classes allowed to deserialize
         * @throws IOException error in provided input stream
         */
        public SafeSerialization(InputStream in, Set whitelist) throws IOException {
            super(in);
            this.whitelist = whitelist;
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
            if(!whitelist.contains(desc.getName())){
                throw new InvalidClassException("Invalid Class: " + desc.getName());
            }
            return super.resolveClass(desc);
        }


    }

    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
//        TransactionReport transactionReport = new TransactionReport();
//        transactionReport.addTransaction("Billy", "Billy deposit 500");
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        try (ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream)){
//            outputStream.writeObject(transactionReport);
//            SafeSQL.updateTransactions("billy", new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
//            System.out.println("transaction example serialized to bytearray");
//
//        } catch (IOException | SQLException e){
//            e.printStackTrace();
//        }
        TransactionReport transactionReport = new TransactionReport();
        transactionReport.loadTransactions("billy");
        System.out.println(transactionReport.transactions.get(0));

    }
}
