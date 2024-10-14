package bank;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This class can be used to create a report containing all the transactions made with an account
 * This is achieved through storing a serialized version of this class with the associated class in the database
 */
public class TransactionReport implements Serializable {
    private ArrayList<String> transactions;

    /**
     * retrieves the transaction report associated with this account
     * @param name name of the account holder
     * @return The transactions associated with this account if there is an existing report null if not
     * @throws SQLException There was a problem connecting to the database
     * @throws IOException There was a problem reading in the serialized object from the input stream
     * @throws ClassNotFoundException There was not a corresponding class found matching the deserialized object
     */
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

    /**
     * retrieve Transactions list
     * @return ArrayList of transactions
     */
    public ArrayList<String> getTransactions() {
        return transactions;
    }

    /**
     * Add a transaction to the Report
     * @param transaction String representing the transaction
     */
    public void addTransaction(String transaction){
        if(transactions==null){
            transactions=new ArrayList<>();
        }
        transactions.add(transaction);
    }

    /**
     * upload the new Transaction Report ot the database
     * @param name name of the associated account holder
     */
    public void updateTransactions(String name){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream)){
            outputStream.writeObject(this);
            SafeSQL.updateTransactions(name, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            System.out.println("transaction example serialized to bytearray");
        } catch (IOException | SQLException e){
            e.printStackTrace();
        }
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

    /**
     * This class extends ObjectInputStream to provide a filtering of deserialized classes
     */
    private class SafeSerialization extends ObjectInputStream {
        private Set whitelist;

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
         * @throws IOException There was a problem reading from desc
         * @throws ClassNotFoundException The class in the Object Stream did not match an existing class
         */
        @Override
        protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
            if(!whitelist.contains(desc.getName())){
                throw new InvalidClassException("Invalid Class: " + desc.getName());
            }
            return super.resolveClass(desc);
        }


    }
}
