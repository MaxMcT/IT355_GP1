package bank;

import com.mysql.cj.jdbc.JdbcConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class SafeSQL {

    static final String db = "jdbc:mysql://safe-sql-demo.chqqa2u8esx7.us-east-2.rds.amazonaws.com/bank";
    static final String username = "IT355";
    static final String password = "IT355sqldemo";

    private static Connection dbConnect() throws SQLException {
        Connection dbConnection = getConnection(db, username, password);//provide connection link
        if(dbConnection == null){
            throw new SQLException();
        }
        return dbConnection;
    }
    static String getBalance(String name) throws SQLException, IllegalArgumentException {
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
        return rs.getString("balance");
    }

    static int deposit(BigDecimal amount, String name) throws SQLException, IllegalArgumentException {
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

    static int credit(BigDecimal amount, String name) throws SQLException, IllegalArgumentException {
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

    static int openAccount(String name) throws SQLException {
        Connection dbConnection = dbConnect();

        //Create a custom query where ? will be replaced by variable input
        String insert = "INSERT INTO accounts (name, balance) VALUES (?, 0);";
        //A prepared statement pre-compiles the query structure as sql and never compiles again
        PreparedStatement preparedStatement = dbConnection.prepareStatement(insert);
        //The variable input is inserted in place of the ? but is never compiled as SQL
        //This prevents the user input from being interpreted as part of the SQL statement
        preparedStatement.setString(1, name);

        return preparedStatement.executeUpdate();
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(deposit(new BigDecimal(500.001), "Billy"));
    }
}

