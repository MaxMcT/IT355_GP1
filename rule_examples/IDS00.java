import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static java.sql.DriverManager.getConnection;

/**
 * IDS00-J. Prevent SQL injection
 * This is an example of a safe way of allowing untrusted data into sql statements
 * This is accomplished through using some data validation and prepared Statements
 */
public class IDS00 {

    /**
     * Query the database for a rule by id
     * @param ruleID id of the rule example SER01-J
     * @return all columns in rules table corresponding to the provided ruleID
     * @throws SQLException In case where database connection fails
     * @throws IllegalArgumentException In case where input is invalid
     */
    static String queryRule(String ruleID) throws SQLException, IllegalArgumentException {
        Connection dbConnection = getConnection("jdbc:mysql://safe-sql-demo.chqqa2u8esx7.us-east-2.rds.amazonaws.com/java_rules", "IT355", "IT355sqldemo");//provide connection link
        if(dbConnection == null){
            throw new SQLException();
        }

        //all ids are 7 characters long
        if(ruleID.length()!=7)
            throw new IllegalArgumentException();

        //Create a custom query where ? will be replaced by variable input
        String ruleQuery = "SELECT * FROM rules WHERE rule_id=?;";
        //A prepared statement pre-compiles the query structure as sql and never compiles again
        PreparedStatement preparedStatement = dbConnection.prepareStatement(ruleQuery);
        //The variable input is inserted in place of the ? but is never compiled as SQL
        //This prevents the user input from being interpreted as part of the SQL statement
        preparedStatement.setString(1, ruleID);

        ResultSet rs =preparedStatement.executeQuery();
        System.out.println(rs.toString());
        if(!rs.next()){
            throw new IllegalArgumentException();
        }
        String out = "ruleID: " + ruleID + "| severity: " + rs.getString("severity");
        return out;
    }

    /**
     * fills the rule table with provided input from file
     * @param filename - name of csv file to pull rule data from
     * @throws FileNotFoundException - file not found
     * @throws SQLException - database connection fail
     */
    static void generateRuleTable(String filename) throws FileNotFoundException, SQLException {
        Scanner input = new Scanner(new File(filename));
        Connection dbConnection = getConnection("jdbc:mysql://safe-sql-demo.chqqa2u8esx7.us-east-2.rds.amazonaws.com/java_rules", "IT355", "IT355sqldemo");
        while(input.hasNextLine()){

            String rule = input.nextLine();
            System.out.println(rule);
            String ruleValues[] = rule.split(",");
            String ruleQuery = "INSERT INTO rules ( rule_id, severity, likelihood, remediation_cost, priority, level) VALUES (?,?,?,?,?,?);";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(ruleQuery);
            preparedStatement.setString(1, ruleValues[0]);
            preparedStatement.setString(2, ruleValues[1]);
            preparedStatement.setString(3, ruleValues[2]);
            preparedStatement.setString(4, ruleValues[3]);
            preparedStatement.setString(5, ruleValues[4]);
            preparedStatement.setString(6, ruleValues[5]);
            int rs =preparedStatement.executeUpdate();
            System.out.println(rs);
        }
    }

    /**
     * This is a test case for IDS00
     * @param args None
     */
    public static void main(String[] args) {
        try {
            queryRule("STR00-J");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
