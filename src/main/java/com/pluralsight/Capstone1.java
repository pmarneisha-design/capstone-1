package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Capstone1 {
    static Scanner scanner = new Scanner(System.in);

    // starts the program by showing the main menu
    public static void main(String[] args) {
        mainMenu();
    }
    // scanner for user input

    // shows users home screen options until they press X to exit
    public static void mainMenu() {
        // declares a variable to hold what the user types
        String userInput;
// prints the home menu so the user can choose what they want to do
        do {
            System.out.println("HOME SCREEN");
            System.out.println("D- Add Deposit");
            System.out.println("P- Payment");
            System.out.println("L- Ledger");
            System.out.println("X- Exit");
            System.out.println("Please enter character for action");
            // reads what the user types in
            userInput = scanner.nextLine();
            // decides what to do based on the users choice
            switch (userInput) {
                // if user clicks "D" call the addDeposit() method
                case "D":
                    addDeposit();
                    // break means to stop checking the other cases
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    ledgerMenu();
                    break;
                // if user types "X" break out of the loop and end program
                case "X":
                    break;
                // if user typed something else, show an error message
                default:
                    System.out.println("Invalid input. Try again.");
            }
            // the loop continues as long as the user hasn't typed "X"
        } while (userInput.equals("X"));
    }

    // starts new method that handles adding deposits
    private static void addDeposit() {
        // asks for date,get input, and converts to LocalDate type
        System.out.println("Enter transaction date: YYYY-MM-DD");
        String dateString = scanner.nextLine();
        // converts string into an amount
        LocalDate date = LocalDate.parse(dateString);
        // asks for time and converts to LocalTime type
        System.out.println("Enter transaction time: HH:MM ");
        String timeString = scanner.nextLine();
        LocalTime time = LocalTime.parse(timeString);
// ask what the transaction was for
        System.out.println("Enter transaction description");
        String descriptionString = scanner.nextLine();
// asks for the name of the vendor or source
        System.out.println("Enter vendor name");
        String vendorString = scanner.nextLine();
        // asks for the amount,reads it as a string, and converts it to a decimal
        System.out.println("Enter transaction amount");
        String amountString = scanner.nextLine();
        double amount = Double.parseDouble(amountString);


//date|time|description|vendor|amount
        String ledgerEntry = date + "\\|" + time + "\\|" + descriptionString + "\\|" + vendorString + "\\|" + amount;
        String fileName = "src/main/resources/transactions.csv";

// fileWriter writes stream of characters to file
        // open files
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(ledgerEntry + "\n");
            System.out.println("Transaction completed!");

        } catch (IOException error) {
            System.out.println("Error saving transaction");
        }
    }

    // date, time, description, vendor, amount
    private static void makePayment() {
        // prompt user for date
        System.out.println("Enter transaction date: YYYY-MM-DD");
        String dateString = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateString);
// prompt user for time
        System.out.println("Enter transaction time: HH:MM ");
        String timeString = scanner.nextLine();
        LocalTime time = LocalTime.parse(timeString);
// prompt user for description
        System.out.println("Enter transaction description");
        String descriptionString = scanner.nextLine();
// prompts user for vendor name
        System.out.println("Enter vendor name");
        String vendorString = scanner.nextLine();
        // prompts user to enter transaction amount
        System.out.println("Enter transaction amount");
        String amountString = scanner.nextLine();
        double amount = Double.parseDouble(amountString);
        //negates amount because this is a payment (debit)
        double debitAmount = -Math.abs(amount);

        String ledgerEntry = date + "\\|" + time + "\\|" + descriptionString + "\\|" + vendorString + "\\|" + amount;

        String fileName = "src/main/resources/transactions.csv";


        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(ledgerEntry + "\n");
            System.out.println("Payment added!");

        } catch (IOException error) {
            System.out.println("Invalid payment");
        }
    }

    private static void ledgerMenu() {
        String userInput;
        ArrayList<Transaction> transactionArrayList = readCsv();
        do {
            System.out.println("LEDGER MENU");
            System.out.println("A- All Entries");
            System.out.println("D- Deposits");
            System.out.println("P- Payments");
//            System.out.println("R- Reports");
            System.out.println("H- Homepage");
            userInput = scanner.nextLine();

            switch (userInput) {
                case "A":
                    allEntries(transactionArrayList);
                    break;
                case "D":
                    displayDeposits(transactionArrayList);
                    break;
                case "P":
                    displayPayments(transactionArrayList);
                    break;
//                case "R":
//                    allReports();
//                    break;
                case "H":
                    break;
            }
        } while (userInput.equals("H"));
    }


    public static ArrayList<Transaction> readCsv() {
        ArrayList<Transaction> transactionArrayList = null;
        try {
            //   create a FileReader object connected to the File
            transactionArrayList = new ArrayList<Transaction>();
            String fileName = "src/main/resources/transactions.csv";
            FileReader fileReader = new FileReader(fileName);
            // create a BufferedReader to manage input stream
            BufferedReader bufReader = new BufferedReader(fileReader);
            String input;
            // read until there is no more data
            while ((input = bufReader.readLine()) != null) {
                //2024-09-08\|08:16\|payment\|walmart\|13.0
                String[] dateEntry = input.split("\\|");
                LocalDate date = LocalDate.parse(dateEntry[0]);
                LocalTime time = LocalTime.parse(dateEntry[1]);
                String description = dateEntry[2];
                String vendor = dateEntry[3];
                double amount = Double.parseDouble(dateEntry[4]);
                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactionArrayList.add(transaction);


            }
            // close the stream and release the resources
            bufReader.close();

        } catch (IOException e) {
            // display stack trace if there was an error
            e.printStackTrace();
        }
        return transactionArrayList;
    }

    public static void allEntries(ArrayList<Transaction> transactionList) {
        for (int i = 0; i < transactionList.size(); i++) {
            System.out.println(transactionList.get(i).toString());
        }
    }

    public static void displayDeposits(ArrayList<Transaction> transactionList) {

        for (Transaction currentTransaction : transactionList) {
            if (currentTransaction.getAmount() > 0) {
                System.out.println(currentTransaction);
            }
        }
    }

    public static void displayPayments(ArrayList<Transaction> transactionList) {
        for (Transaction currentTransaction : transactionList) {
            if (currentTransaction.getAmount() < 0) {
                System.out.println(currentTransaction);
            }
        }
    }


//        } while (userInput.equals("X"));
//    }


//creating CLI financial transaction app to track all financial transactions
// application must have several screens
    //screens:
    // display home screen
    // home screen options: add deposit, make payment, ledger, exit
    // if user clicks add deposit then they should be prompted to add transactions: date, time, description, vendor, amount
    // create method- add deposit
    //if the user clicks make a payment (use negative # because subtracting from account)
    // prompt user for debit information
    // method makePayment
    //add formula to negate from account
    // use file writer to write new payment on transaction file
    //display the ledger screen: deposits, payments, reports
    // ledger menu: a)all: display all transactions screen(newest first)
    //

}
