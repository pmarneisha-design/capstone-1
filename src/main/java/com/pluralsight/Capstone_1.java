package com.pluralsight;
import java.util.Scanner;

public class Capstone_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        mainMenu(scanner);
        scanner.close();
    }
    // scanner for user input


    public static void mainMenu(Scanner scanner) {
        String userInput;

        do {
            System.out.println("HOME SCREEN");
            System.out.println("D- Add Deposit");
            System.out.println("P- Payment");
            System.out.println("L- Ledger");
            System.out.println("X- Exit");

            userInput = getInput(scanner, "Please enter character for action");

            switch (userInput) {
                case "D" -> addDeposit();
                case "P" -> makePayment();
                case "L" -> ledgerMenu(scanner);
                case "X" -> exitApplication();
                default -> System.out.println("Invalid input. Try again.");
            }
        } while (userInput.equals("X"));
    }

    // ADD DEPOSIT
    private static void addDeposit() {
        System.out.println("Enter deposit amount");
    }

    private static void makePayment() {
    }

    private static void ledgerMenu(Scanner scanner) {
        do {
            System.out.println("LEDGER MENU");
            System.out.println("A- Display All Entries");
            System.out.println("D- Deposits");
            System.out.println("P- Payments");
            System.out.println("R- Reporta");

            userInput = getInput(scanner, "Please enter character for action");

            switch (userInput) {
                case "D" -> addDeposit();
                case "P" -> makePayment();
                case "L" -> ledgerMenu(scanner);
                case "X" -> exitApplication();
                default -> System.out.println("Invalid input. Try again.");
            }
        } while (userInput.equals("X"));
    }


    private static void exitApplication() {

    }

    private static String getInput(Scanner scanner, String message) {
    }
}
