import DAOs.BankAccountDAO;
import menus.AccountMenu;
import models.Customer;
import utils.ConnectionManager;
import utils.datastructure.MyArrayList;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 */
public class Driver {
    public static void main(String[] args) {
        MyArrayList<Customer> customers = new MyArrayList<>();
        Scanner sc = new Scanner(System.in);

        try(Connection conn = ConnectionManager.getConnection()){
            //Customer customer = new Customer(4, "Felix", "Dubwa","User1", "Pass1", "TestEmail@revature.net"); //WHY IS THIS NECESSARY
            BankAccountDAO dao = new BankAccountDAO(conn);
            //dao.save(customer); GETTING AN ERROR

        } catch(SQLException | IOException e) {
                e.printStackTrace();
        }

        // main menu loop
        System.out.println("===WELCOME TO THE REVETURE BANK!===");
        boolean running = true;
        while (running) {
            System.out.println("\n===MAIN MENU===\nEnter selection:\n\n1) Register for an account.\n2) Log into account.\nQ) Quit");

            String main = sc.nextLine();

            switch (main) {
                case "1":
                    System.out.println("========== Registration: ==========");

                    //code for getting firstName, lastName, userName, password [could add more like email, address, phone-number, other...]
                    System.out.print("Enter you first name: ");//Test first name:John
                    String firstName = sc.nextLine();
                    System.out.print("Enter you last name: ");//Test last name:John
                    String lastName = sc.nextLine();
                    System.out.print("Enter username: ");//Test username:user
                    String userName = sc.nextLine();
                    System.out.print("Enter password: ");//Test password:pass
                    String passWord = sc.nextLine();
                    System.out.println("Enter email: ");
                    String email = sc.nextLine();
                    //store the inputs in an array
                    int customerId = + 1;
                    customers.add(new Customer(customerId, firstName, lastName, userName, passWord, email));

                    System.out.println("\n===congratulations! You just Registered for an Account:" + firstName + ", " + lastName + "===");
                    System.out.println("=================================");
                    break;
                case "2":
                    System.out.println("========== Log in: ==========");
                    //code for getting username and password
                    System.out.print("Enter you're Username name: ");//Test first name:John
                    userName = sc.nextLine();
                    System.out.print("Enter you're Password name: ");//Test last name:John
                    passWord = sc.nextLine();

                    // after inputs check an array for weather or not it exists and log in
                    if (!(userName.equals("") && "passWord".equals(userName))) {
                        System.out.println("Authentication Failed");
                        System.out.println("Back to main menu");
                    } else {
                        System.out.println("Authentication Successful");
                        // another case statement: once logged in ask user if they want to make a bank account [either checking or savings for now], or displaying all accounts
                        // call AccountMenu

                        new AccountMenu().accountMenu();
                    }
                    System.out.println("=================================");
                    break;
                case "Q":
                case "q":
                    running = false;
                    break;
                default:
                    System.out.println("You entered an invalid selection! Please input a selection from the list.");
                    break;
            }
        }
    }
}

