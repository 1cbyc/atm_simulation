import java.io.*;
import java.util.*;

//private Scanner scanner = new Scanner(System.in);
// i put this here so that the entire ATM class can use the scanner

public class ATM {
    private Map<String, User> users = new HashMap<>();
    private User currentUser;
    private final String filePath = "data/users.txt";
    private Scanner scanner;

    public ATM() {
        scanner = new Scanner(System.in);
        loadUsers();
    }

    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String acc = parts[0];
                String pin = parts[1];
                double bal = Double.parseDouble(parts[2]);
                users.put(acc, new User(acc, pin, bal));
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/users.txt"))) {
            for (User user : users.values()) {
                writer.write(user.getAccountNumber() + "," + user.getPin() + "," + user.getBalance());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
    // tested theory again about the saveUsers() option with the private void saveusers option

    // trying to add an option to change pin in the show menu aspect of things now
    private void changePin() {
        System.out.print("Enter current PIN: ");
        int currentPin = scanner.nextInt();
//        String currentPin = scanner.nextLine();
        scanner.nextLine();

        if (currentUser.getPin().equals(currentPin)) {
            System.out.println("Incorrect PIN. Try again.");
            return;
        }

        System.out.print("Enter new PIN: ");
        String newPin = scanner.nextLine();

        System.out.print("Confirm new PIN: ");
        String confirmPin = scanner.nextLine();

        if (!newPin.equals(confirmPin)) {
            System.out.println("PINs do not match. Try again.");
            return;
        }

        currentUser.setPin(newPin);
        saveUsers();
        System.out.println("PIN successfully changed.");
    }

    public boolean login(String accountNumber, String pin) {
        if (users.containsKey(accountNumber)) {
            User user = users.get(accountNumber);
            if (user.getPin().equals(pin)) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public void showMenu() {
//        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== ATM MENU ===");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Transaction History");
            System.out.println("5. Change PIN");
            System.out.println("6. Exit");
            System.out.println("Choose option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Balance: ₦" + currentUser.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double dep = scanner.nextDouble();
                    currentUser.deposit(dep);
                    saveUsers(); // testing a theory
                    System.out.println("Deposit: ₦" + dep);
                    currentUser.addTransaction("Deposited ₦" + dep );
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double wit = scanner.nextDouble();
                    if (currentUser.withdraw(wit)) {
                        System.out.println("Withdrawn: ₦" + wit);
                        saveUsers(); // testing another theory
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                    currentUser.addTransaction("Withdrew ₦" + wit );
                    break;
                case 4:
                    currentUser.showTransactions();
                    break;
                case 5:
                    changePin();
                    break;
                case 6:
                    saveUsers();
                    System.out.println("Thank you for banking with us.");
            }

        } while (choice != 6);
    }
}