package bank;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

/**
 * This class should be used for any sql database interactions.
 * The methods contained in this class use prepared statements to prevent SQL injection
 */
public class SafeSQL {

    static final String db = "jdbc:mysql://safe-sql-demo.chqqa2u8esx7.us-east-2.rds.amazonaws.com/bank";
    static final String username = "IT355";
    static final String password = "IT355sqldemo";

    /**
     * get a connection to the database
     * @return a database connection
     * @throws SQLException failed to connect to the database
     */
    private static Connection dbConnect() throws SQLException {
        Connection dbConnection = getConnection(db, username, password);//provide connection link
        if(dbConnection == null){
            throw new SQLException();
        }
        return dbConnection;
    }

    /**
     * returns the balance of the provided account
     * @param name the name of the account holder
     * @return account balance
     * @throws SQLException query failed
     * @throws IllegalArgumentException invalid arguments were provided to the query
     */
    public static double getBalance(String name) throws SQLException, IllegalArgumentException {
        Connection dbConnection = dbConnect();

        //Create a custom query where ? will be replaced by variable input
        String query = "SELECT balance FROM accounts WHERE name=?;";
        //A prepared statement pre-compiles the query structure as sql and never compiles again
        PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
        //The variable input is inserted in place of the ? but is never compiled as SQL
        //This prevents the user input from being interpreted as part of the SQL statement
        preparedStatement.setString(1, name);

        ResultSet rs =preparedStatement.executeQuery();
        System.out.println(rs.toString());
        if(!rs.next()){
            throw new IllegalArgumentException();
        }
        return Double.valueOf(rs.getString("balance"));
    }

    /**
     * This method deposits money into the account
     * @param amount amount to deposit - must be positive
     * @param name name of the account holder
     * @return 1 = success anything else = failure
     * @throws SQLException failed to update the account
     * @throws IllegalArgumentException provided arguments were invalid
     */
    public static int deposit(BigDecimal amount, String name) throws SQLException, IllegalArgumentException {
        if(amount.intValue() <= 0)
            return -1;
        Connection dbConnection = dbConnect();

        //Create a custom query where ? will be replaced by variable input
        String update = "UPDATE accounts SET balance = balance + ? WHERE name=?;";
        //A prepared statement pre-compiles the query structure as sql and never compiles again
        PreparedStatement preparedStatement = dbConnection.prepareStatement(update);
        //The variable input is inserted in place of the ? but is never compiled as SQL
        //This prevents the user input from being interpreted as part of the SQL statement
        preparedStatement.setString(1, amount.toString());
        preparedStatement.setString(2, name);

        return preparedStatement.executeUpdate();
    }

    /**
     * This method credits money from the account
     * @param amount the amount of money to credit
     * @param name the name of the account holder
     * @return 1 = success anything else = failure
     * @throws SQLException failed to update the account
     * @throws IllegalArgumentException provided arguments were invalid
     */
    public static int credit(BigDecimal amount, String name) throws SQLException, IllegalArgumentException {
        if(amount.intValue() <= 0)
            return -1;
        Connection dbConnection = dbConnect();

        //Create a custom query where ? will be replaced by variable input
        String ruleQuery = "UPDATE accounts SET balance = balance - ? WHERE name=?;";
        //A prepared statement pre-compiles the query structure as sql and never compiles again
        PreparedStatement preparedStatement = dbConnection.prepareStatement(ruleQuery);
        //The variable input is inserted in place of the ? but is never compiled as SQL
        //This prevents the user input from being interpreted as part of the SQL statement
        preparedStatement.setString(1, amount.toString());
        preparedStatement.setString(2, name);

        return preparedStatement.executeUpdate();
    }


    /**
     * This method creates a account with 0 dollars
     * @param name name attached to account
     * @return 1 = success anything else = failure
     * @throws SQLException failed to create the account
     */

    public static int openAccount(String name, BigDecimal amount) throws SQLException {
        Connection dbConnection = dbConnect();

        //Create a custom query where ? will be replaced by variable input
        String insert = "INSERT INTO accounts (name, balance) VALUES (?, ?);";
        //A prepared statement pre-compiles the query structure as sql and never compiles again
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insert);
        //The variable input is inserted in place of the ? but is never compiled as SQL
        //This prevents the user input from being interpreted as part of the SQL statement
        preparedStatement.setString(1, name);
        preparedStatement.setBigDecimal(2, amount);

        return preparedStatement.executeUpdate();
    }

    /**
     * get the associated transcaction serialized object from the database
     * @param name name associated with the account
     * @return An input stream containing the serialized object
     * @throws SQLException There was an error connecting to the database
     * @throws IllegalArgumentException The provided input was invalid
     */
    public static InputStream retrieveTransactions(String name) throws SQLException, IllegalArgumentException {
        Connection dbConnection = dbConnect();

        //Create a custom query where ? will be replaced by variable input
        String insert = "SELECT transactions from accounts where name=?;";
        //A prepared statement pre-compiles the query structure as sql and never compiles again
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insert);
        //The variable input is inserted in place of the ? but is never compiled as SQL
        //This prevents the user input from being interpreted as part of the SQL statement
        preparedStatement.setString(1, name);

        ResultSet rs =preparedStatement.executeQuery();
        System.out.println(rs.toString());
        if(!rs.next()){
            throw new IllegalArgumentException();
        }
        return rs.getBinaryStream("transactions");
    }

    public static int updateTransactions(String name, InputStream transactionBinary) throws SQLException {
        Connection dbConnection = dbConnect();

        //Create a custom query where ? will be replaced by variable input
        String insert = "UPDATE accounts SET transactions=? WHERE name=?;";
        //A prepared statement pre-compiles the query structure as sql and never compiles again
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insert);
        //The variable input is inserted in place of the ? but is never compiled as SQL
        //This prevents the user input from being interpreted as part of the SQL statement
        preparedStatement.setBinaryStream(1, transactionBinary);
        preparedStatement.setString(2, name);

        return preparedStatement.executeUpdate();
    }
    
    public static void main(String[] args) throws SQLException {
        System.out.println(deposit(new BigDecimal(500.001), "Billy"));
    }
}

