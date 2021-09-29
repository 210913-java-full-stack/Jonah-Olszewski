package menus;

import models.BankAccount;
import utils.datastructure.MyArrayList;

import java.util.Scanner;


public class AccountMenu {
    public void accountMenu() {
        MyArrayList<BankAccount> bankAccounts = new MyArrayList<>();
        Scanner sc = new Scanner(System.in);

        double deposit;
        double withdraw;

        // sub menu once logged in loop
        boolean running = true;
        while (running) {
            System.out.println("\n===Account MENU===\nEnter selection:\n\n1) Make a Checkings Account.\n2) Make a Savings Account.\n3) Display all banking accounts.\nQ) Quit to Main Menu");

            String accountsMain = sc.nextLine();
            switch (accountsMain) {
                //allow user to make one or more accounts[checkings or savings] AS WELL AS display all of my accounts in a list which includes current balance
                //(could use something like the to-do-items list for selections for getting and selecting accounts?)
                //Note: All monetary amounts should be displayed in a proper currency format ($1,234.56). THIS WOULD BE: "$%,.2f\n" https://www.youtube.com/watch?v=hjsTu8300vg
                case "1":
                    System.out.println("You now have: //some counter for # of checkings account(s) 1, 5, etc// Chekings account");
                    // get deposits, withdraws
                    System.out.println("\n===Balance MENU===\nEnter selection:\n\n1) make a Deposit.\n2) Make a withdraw.\nQ) Quit to Account Menu");

                    String balanceMain1 = sc.next();
                    switch (balanceMain1) {
                        case "1":
                            System.out.println("Enter amount you would like to Deposit: ");
                            deposit = sc.nextDouble();
                            bankAccounts.add(new BankAccount(deposit));
                            break;
                        case "2":
                            System.out.println("Enter amount you would like to Withdraw: ");
                            withdraw = sc.nextDouble();
                            bankAccounts.add(new BankAccount(withdraw));
                            break;
                    }
                    break;
                case "2":
                    System.out.println("You now have: //some counter for # of Savings account(s) 1, 5, etc// Savings account");
                    // get deposits, withdraws and all accounts + current balance
                    System.out.println("\n===Balance MENU===\nEnter selection:\n\n1) make a Deposit.\n2) Make a withdraw.\n3) Display all banking accounts.\nQ) Quit to Account Menu");

                    String balanceMain2 = sc.next();
                    switch (balanceMain2) {
                        case "1":
                            System.out.println("Enter amount you would like to Deposit: ");
                            deposit = sc.nextDouble();
                            bankAccounts.add(new BankAccount(deposit));
                            break;
                        case "2":
                            System.out.println("Enter amount you would like to Withdraw: ");
                            withdraw = sc.nextDouble();
                            bankAccounts.add(new BankAccount(withdraw));
                            break;
                    }
                case "3":
                    // Displays all accounts WITH current balance \"$%,.2f\\n\"");
                    for (int i = 0; i < bankAccounts.size(); i++) {
                        // code goes here
                        BankAccount bc = bankAccounts.get(i);


                    }
                    break;
                case "Q":
                case "q":
                    running = false;
                    break;
            }
        }
    }
}