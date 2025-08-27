import java.util.*;

class Account {
    String name;
    int accNo;
    double balance;
    int pin;
    double interestRate = 5.0; // Default interest rate
    ArrayList<String> transactionHistory = new ArrayList<>();

    // Constructor
    public Account(String name, int accNo, double balance, int pin) {
        this.name = name;
        this.accNo = accNo;
        this.balance = balance;
        this.pin = pin;
        transactionHistory.add("Account created with balance: " + balance);
    }

    // Deposit
    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: " + amount);
        System.out.println("✅ Amount Deposited: " + amount);
    }

    // Withdraw
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrawn: " + amount);
            System.out.println("✅ Amount Withdrawn: " + amount);
        } else {
            System.out.println("❌ Insufficient Balance!");
        }
    }

    // Balance
    public void checkBalance() {
        System.out.println("💰 Current Balance: " + balance);
    }

    // Interest
    public void calculateInterest(double years) {
        double interest = (balance * interestRate * years) / 100;
        transactionHistory.add("Interest calculated for " + years + " year(s): " + interest);
        System.out.println("📊 Interest: " + interest);
        System.out.println("💰 Total After Interest: " + (balance + interest));
    }

    // Show Transaction History
    public void showTransactionHistory() {
        System.out.println("📜 Transaction History for " + name + ":");
        for (String t : transactionHistory) {
            System.out.println(" - " + t);
        }
    }

    // PIN Authentication
    public boolean authenticate(int inputPin) {
        return this.pin == inputPin;
    }
}

public class BankingSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Account> accounts = new ArrayList<>();

    // Logo
    public static void printLogo() {
        System.out.println("=====================================");
        System.out.println("   ★★★ FINTRACK BANKING SYSTEM ★★★");
        System.out.println("=====================================");
    }

    // Create Account
    public static void createAccount() {
        System.out.print("Enter Name: ");
        sc.nextLine(); // consume leftover \n
        String name = sc.nextLine();

        System.out.print("Enter Account Number: ");
        int accNo = sc.nextInt();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();

        System.out.print("Set a 4-digit PIN: ");
        int pin = sc.nextInt();

        Account acc = new Account(name, accNo, balance, pin);
        accounts.add(acc);

        System.out.println("✅ Account Created Successfully for " + name);
    }

    // Find Account
    public static Account findAccount(int accNo, int pin) {
        for (Account acc : accounts) {
            if (acc.accNo == accNo && acc.authenticate(pin)) {
                return acc;
            }
        }
        System.out.println("❌ Invalid Account Number or PIN!");
        return null;
    }

    // Main
    public static void main(String[] args) {
        printLogo();
        int choice;

        do {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Calculate Interest");
            System.out.println("6. Transaction History");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount();
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    int dAcc = sc.nextInt();
                    System.out.print("Enter PIN: ");
                    int dPin = sc.nextInt();
                    Account depAcc = findAccount(dAcc, dPin);
                    if (depAcc != null) {
                        System.out.print("Enter amount to deposit: ");
                        double dAmount = sc.nextDouble();
                        depAcc.deposit(dAmount);
                    }
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    int wAcc = sc.nextInt();
                    System.out.print("Enter PIN: ");
                    int wPin = sc.nextInt();
                    Account withAcc = findAccount(wAcc, wPin);
                    if (withAcc != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double wAmount = sc.nextDouble();
                        withAcc.withdraw(wAmount);
                    }
                    break;

                case 4:
                    System.out.print("Enter Account Number: ");
                    int bAcc = sc.nextInt();
                    System.out.print("Enter PIN: ");
                    int bPin = sc.nextInt();
                    Account balAcc = findAccount(bAcc, bPin);
                    if (balAcc != null) {
                        balAcc.checkBalance();
                    }
                    break;

                case 5:
                    System.out.print("Enter Account Number: ");
                    int iAcc = sc.nextInt();
                    System.out.print("Enter PIN: ");
                    int iPin = sc.nextInt();
                    Account intAcc = findAccount(iAcc, iPin);
                    if (intAcc != null) {
                        System.out.print("Enter time in years: ");
                        double years = sc.nextDouble();
                        intAcc.calculateInterest(years);
                    }
                    break;

                case 6:
                    System.out.print("Enter Account Number: ");
                    int tAcc = sc.nextInt();
                    System.out.print("Enter PIN: ");
                    int tPin = sc.nextInt();
                    Account transAcc = findAccount(tAcc, tPin);
                    if (transAcc != null) {
                        transAcc.showTransactionHistory();
                    }
                    break;

                case 7:
                    System.out.println("👋 Thank you for using FinTrack Banking System!");
                    break;

                default:
                    System.out.println("❌ Invalid choice!");
            }

        } while (choice != 7);
    }
}
