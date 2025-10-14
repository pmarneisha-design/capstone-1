package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class AccountingLedgerApp {
    static ArrayList<Transaction> transactions = transactions = new ArrayList<>();

    // scanner for user input
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

    }

    public static void mainMenu (String[] args) {
        char userInput;

        do {
            System.out.println("HOME SCREEN");
            System.out.println("Add Deposit");
            System.out.println("Make Payment");
            System.out.println("Ledger");
            System.out.println("Exit");
            System.out.println("Enter your input: ");

          switch (userInput) {
              case 'D' -> {
                  addDeposit();
              }
            case 'P' -> {
                  makePayment();
            }

              case 'L' -> {
                  ledgerMenu();
              }
          }

        }
    }

    private static void makePayment() {
    }

    private static void addDeposit() {
    }
}
