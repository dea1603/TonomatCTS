package Models;

public class SharedBankAccount {

    private static SharedBankAccount instance = null;
    private double balance = 0.0;


    private SharedBankAccount() {}

    public static SharedBankAccount getInstance() {
        if (instance == null) {
            instance = new SharedBankAccount();
        }
        return instance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("%.2f RON deposited to the shared account. New balance is: %.2f RON%n", amount, balance);
        } else {
            System.out.println("Cannot deposit a negative or zero amount.");
        }
    }

    public void displayBalance() {
        System.out.printf("Current Shared Account Balance: %.2f RON%n", balance);
    }

}
