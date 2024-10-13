import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Bank {
    private static BankAccount account;
    private static File tempFile;
    private static Scanner scanner = new Scanner(System.in);

    /**
    * Class for creating accounts for the bank
    */
   static class BankAccount implements Serializable {
        private String accountNumber;
        private String accountName;
        private double balance;

        /**
         * Constructor for an account
         */
        public BankAccount(String accountNumber, String accountName, double initialBalance) {
            this.accountNumber = accountNumber;
            this.accountName = accountName;
            this.balance = initialBalance;
        }

        /**
         * Adds amount to account's balance
         * @param amount
         */
        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: " + amount);
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }

        /**
         * Removes amount for account's balance
         * @param amount
         */
        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
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
            return balance;
        }

        /**
         * Getter for account number
         * @return accountNumber
         */
        public String getAccountNumber() {
            return accountNumber;
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
            return "Account Number: " + accountNumber + "\n" +
                   "Account Name: " + accountName + "\n" +
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
    }

    /**
     * Creates an account from user input
     */
    public static void createAccount() {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter Account Name: ");
        String accountName = scanner.nextLine();
        System.out.print("Enter Initial Balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        account = new BankAccount(accountNumber, accountName, initialBalance);
        System.out.println("Account created successfully.");
    }

    /**
     * Deposits money into an account
     */
    public static void deposit() {
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
     */
    public static void withdraw() {
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
     */
    public static void checkBalance() {
        if (account == null) {
            System.out.println("No account available. Please create or load an account.");
            return;
        }
        System.out.println("Current Balance: " + account.getBalance());
    }

    /**
     * Displays menu for the bank
     */
    public static void displayMenu() {
        System.out.println("\nBank Menu:");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Check Balance");
        System.out.println("5. Save Account");
        System.out.println("6. Calculate Estimated Interest");
        System.out.println("7. Exit");
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
    for (int day = 0; day < 365; day++) {
        // balance += balance * dailyInterestRate
        tempBalance = tempBalance.add(tempBalance.multiply(dailyInterestRate));
    }

    //Print the final amount after applying daily interest for 1 year
    System.out.printf("Balance after 1 year with daily compounding at 2%% interest: %.2f%n", tempBalance.subtract(balance));
}



  public static void main(String[] args) {
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
                        deposit();
                        break;
                    case 3:
                        withdraw();
                        break;
                    case 4:
                        checkBalance();
                        break;
                    case 5:
                        saveAccount();
                        break;
                    case 6:
                        calcEstInterest();
                        break;
                    case 7:
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
