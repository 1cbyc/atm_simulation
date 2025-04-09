import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);

        System.out.println("===  Welcome to ATM Simulator by 1cbyc ===");

        System.out.print("Enter Account Number: ");
        String acc = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        if (atm.login(acc, pin)) {
            System.out.println("Login successful!");
            atm.showMenu();
        } else {
            System.out.println("Invalid credentials. Access denied.");
        }
    }
}