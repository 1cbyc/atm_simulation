import java.util.List;
import java.util.ArrayList;

public class User {
    private String accountNumber;
    private String pin;
    private double balance;
    private List<String> transactionHistory = new ArrayList<>();

    public User(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String newPin) {
        this.pin = newPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void addTransaction(String note) {
        transactionHistory.add(note);
    }

    public void showTransactions() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("\n--- Transaction History ---");
        for (String t : transactionHistory) {
            System.out.println(t);
        }
    }
}