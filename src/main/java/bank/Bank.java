package bank;

import bank.SafeSQL;
import bank.TransactionReport;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Bank {
    private static BankAccount account;
    private static File tempFile;
    private static Scanner scanner = new Scanner(System.in);

    /**
    * Class for creating accounts for the bank
    */
   static class BankAccount implements Serializable {
        private String accountName;
        private double balance;
        private TransactionReport transactionReport;


        /**
         * Constructor for an account
         */
        public BankAccount(String accountName, double initialBalance) {
            this.accountName = accountName;
            this.balance = initialBalance;
        }

        /**
         * Adds amount to account's balance
         * @param amount
         */
        public void deposit(double amount) throws SQLException {
            if (amount > 0) {
                balance += amount;
                SafeSQL.deposit(new BigDecimal(amount), accountName);
                transactionReport.addTransaction(accountName +": Deposited " + amount);
                System.out.println("Deposited: " + amount);
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }

        /**
         * Removes amount for account's balance
         * @param amount
         */
        public void withdraw(double amount) throws SQLException {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                SafeSQL.credit(new BigDecimal(amount), accountName);
                transactionReport.addTransaction(accountName +": Credited " + amount);
                System.out.println("Withdrawn: " + amount);
            } else {
                System.out.println("Invalid withdrawal amount.");
            }
        }

        /**
         * Getter for balance
         * @return balance
         */
        public double getBalance() {
            if(!Double.isNaN(balance)) {
            return balance;
        }
            else {
                return -1;
            }
        }

        /**
         * Gettter for account name
         * @return name
         */
        public String getAccountName() {
            return accountName;
        }

        /**
         * Prints account number, name, and balance
         */
        public String toString() {
            return "Account Name: " + accountName + "\n" +
                   "Balance: " + balance;
        }
    }
    
    /**
     * Creates file and closes it before the termination of the program
     * @param filename
     * @throws IOException
     */
    private static void createFile(String filename) throws IOException {
        FileWriter out = null;
        try {
            out = new FileWriter(filename);
            //Do something with file
        } 
        catch (IOException e) {
            System.out.println("File failed to create: " + e);
        }
        finally {
            if(out != null) {
                out.close();
            }
        }
    }

    /**
     * Saves account to temp file
     */
    public static void saveAccount() {
        if (account == null) {
            System.out.println("No account to save.");
            return;
        }
        try {
            tempFile = File.createTempFile("bank_account", ".tmp");
            System.out.println("Temporary file created: " + tempFile);
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempFile))){  
                oos.writeObject(account);
                System.out.println("Account saved to file: " + tempFile);
            }
        } catch (IOException e) {
            System.out.println("Error saving account: " + e.getMessage());
        }
        account.transactionReport.updateTransactions(account.accountName);
    }

    /**
     * Creates an account from user input
     * @throws SQLException
     */
    public static void createAccount() throws SQLException {
        System.out.print("Enter Account Name: ");
        String accountName = UnicodeNormalizer.validate(scanner.nextLine());
        System.out.print("Enter Initial Balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        account = new BankAccount( accountName, initialBalance);
        System.out.println("Account created successfully.");
        SafeSQL.openAccount(accountName, new BigDecimal(initialBalance));
    }

    /**
     * Deposits money into an account
     * @throws SQLException
     */
    public static void deposit() throws SQLException {
        if (account == null) {
            System.out.println("No account available. Please create or load an account.");
            return;
        }
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        account.deposit(amount);
    }

    /**
     * Withdraws money from an account
     * @throws SQLException
     */
    public static void withdraw() throws SQLException {
        if (account == null) {
            System.out.println("No account available. Please create or load an account.");
            return;
        }
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        account.withdraw(amount);
    }

    /**
     * Checks balance of account
     * @throws SQLException
     */
    public static void checkBalance() {
        if (account == null) {
            System.out.println("No account available. Please create or load an account.");
            return;
        }
        System.out.println("Current Balance: " + account.getBalance());
    }

    /**
     * reads a check in from a file
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws SQLException
     */
    private static void readCheck() throws IOException, ParserConfigurationException, SAXException, SQLException {
        System.out.print("Enter file location of Check: ");
        String filename = UnicodeNormalizer.normalize(scanner.nextLine());
        File file = new File(filename);
        if(file.exists()) {
            Check check = new Check();
            check.readCheck(filename);
            if (check.getTo().equals(account.accountName)) {
                double amount = Double.valueOf(check.getAmount());
                account.deposit(amount);
                SafeSQL.credit(new BigDecimal(amount), check.getFrom());
            }else{
                System.out.println("This check was addressed to another account");
            }
        }else{
            System.out.println("File does not exist");
        }

    }

    /**
     * Loads an account from the database
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static void loadAccount() throws SQLException, IOException, ClassNotFoundException {
        System.out.print("Enter Account Name: ");
        String name = UnicodeNormalizer.validate(scanner.nextLine());
        account = new BankAccount("name", 0);
        account.accountName = name;
        try {
            account.balance = SafeSQL.getBalance(name);
            account.transactionReport = new TransactionReport();
            account.transactionReport.loadTransactions(account.accountName);
        }catch (SQLException e){
            System.out.println("Invalid account");
        }
    }

    /**
     * this method lists past transactions
     */
    private static void listTransactions(){
        if(account.transactionReport!=null){
        for(String transaction: account.transactionReport.getTransactions()){
            System.out.println(transaction);
        }}else{
            System.out.println("No transactions");
        }
    }

    /**
     * Displays menu for the bank
     */
    public static void displayMenu() {
        System.out.println("\nBank Menu:");
        System.out.println("1. Create Account");
        System.out.println("2. Load Account");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Check Balance");
        System.out.println("6. Save Account");
        System.out.println("7. Calculate Estimated Interest");
        System.out.println("8. Cash Check");
        System.out.println("9. list transactions");
        System.out.println("10. Exit");
        System.out.print("Choose an option: ");
    }



/**
 * Calculate cumulative interest over 1 year at a 2% annual interest rate 
 */
public static void calcEstInterest() {
    if (account == null) {
        System.out.println("No account available. Please create or load an account.");
        return;
    }

    BigDecimal balance = BigDecimal.valueOf(account.getBalance());
    if (balance.compareTo(new BigDecimal("0")) == 0){
        System.out.println("Balance is 0, no interest can be earned.");
        return;
    }

    BigDecimal tempBalance = BigDecimal.valueOf(account.getBalance());
    BigDecimal annualInterestRate = new BigDecimal("0.02");
    BigDecimal dailyInterestRate = annualInterestRate.divide(BigDecimal.valueOf(365), 10, RoundingMode.HALF_EVEN);

    //Calculate cumulative interest for 1 year, updating daily
    // for (int day = 0; day < 365; day++) {
    //     // balance += balance * dailyInterestRate
    //     tempBalance = tempBalance.add(tempBalance.multiply(dailyInterestRate));
    // }

    for (int day : IntStream.range(0, 365).toArray()) {
        tempBalance = tempBalance.add(tempBalance.multiply(dailyInterestRate));
    }

    //Print the final amount after applying daily interest for 1 year
    System.out.printf("Balance after 1 year with daily compounding at 2%% interest: %.2f%n", tempBalance.subtract(balance));
}


  public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException, ParserConfigurationException, SAXException {
            boolean running = true;
            while (running) {
                displayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        loadAccount();
                        break;
                    case 3:
                        deposit();
                        break;
                    case 4:
                        withdraw();
                        break;
                    case 5:
                        checkBalance();
                        break;
                    case 6:
                        saveAccount();
                        break;
                    case 7:
                        calcEstInterest();
                        break;
                    case 8:
                        readCheck();
                        break;
                    case 9:
                        listTransactions();
                        break;
                    case 10:
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        if (tempFile != null && tempFile.exists()) {
            //Temp file no longer in use and is deleted
            boolean deleted = tempFile.delete();
            if (deleted) {
                System.out.println("Temporary file deleted: " + tempFile.getAbsolutePath());
            } 
            else {
                System.err.println("Failed to delete temporary file: " + tempFile.getAbsolutePath());
            }
        }
  }
}
