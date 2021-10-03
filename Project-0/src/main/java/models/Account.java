package models;

import java.util.Scanner;

public class Account {
    private int accountId;
    private double balance;
    private String accountType;
    //double amount;
    //private double deposit;
    //private double withdraw;

    Scanner input = new Scanner(System.in);

    public Account() {
        super();
        //balance = 0;
    }

//    public Account(double balance){
//        this.accountId = accountId;
//        this.balance = balance;
//    }

    public Account(int accountId, String accountType, double balance) {
        this.accountId = accountId;
        this.balance = balance;
        this.accountType = accountType;
        //this.amount = amount;
    }

//    public void displayAllAccounts(MyArrayList<BankAccount> bankAccounts){
//        // code for displaying all accounts
//    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        // code for getting current balance...
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


//    public double getDeposit() {
//        return deposit;
//    }
//
//    public void setDeposit(double deposit) {
//        this.deposit = deposit;
//    }
//
//    public double getWithdraw() {
//        return withdraw;
//    }
//
//    public void setWithdraw(double withdraw) {
//        this.withdraw = withdraw;
//    }
//
//    public double deposit(double amount) {
//        // code for Depositing
//        amount = input.nextDouble();
//        if(amount > 0) {
//            balance = balance + amount;
//            return balance;
//        }
//        else {
//            return 0;
//        }
//    }
//
//    public double withdraw(double amount) {
//        // code for Withdrawing
//        amount = input.nextDouble();
//        if(amount > 0) {
//            balance = balance - amount;
//            return balance;
//        }
//        else {
//            return 0;
//        }
//    }

    @Override
    public String toString() {
        return "Account [accountId=" + accountId + ", accountType=" + accountType + ", balance=" + balance
                + "]";
    }
}
