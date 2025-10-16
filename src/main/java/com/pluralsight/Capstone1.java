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


    public static void main(String[] args) {
        mainMenu();
    }
    // scanner for user input


    public static void mainMenu() {
        String userInput;

        do {
            System.out.println("HOME SCREEN");
            System.out.println("D- Add Deposit");
            System.out.println("P- Payment");
            System.out.println("L- Ledger");
            System.out.println("X- Exit");
            System.out.println("Please enter character for action");
            userInput = scanner.nextLine();

            switch (userInput) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    ledgerMenu();
                    break;
                case "X":
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        } while (userInput.equals("X"));
    }

    // converts string into an amount and prompts user for deposit
    private static void addDeposit() {
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
//            System.out.println("D- Deposits");
//            System.out.println("P- Payments");
//            System.out.println("R- Reports");
            System.out.println("H- Homepage");
            userInput = scanner.nextLine();
            switch (userInput) {
                case "A":
                    allEntries(transactionArrayList);
                    break;
//                case "D":
//                    displayDeposits();
//                    break;
//                case "P":
//                    negativeEntries();
//                    break;
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

    public static void allEntries(ArrayList<Transaction> transactionList){
       for(var i = 0; i< transactionList.size(); i++){
           System.out.println(transactionList.get(i).toString());
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

    private static void exitApplication() {

    }
}
