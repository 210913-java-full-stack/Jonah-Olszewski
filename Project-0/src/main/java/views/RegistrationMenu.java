package views;

import DAOs.CustomerDAO;
import exceptions.UserAlreadyExistsException;
import models.Customer;

import java.sql.SQLException;
import java.util.Scanner;

import static utils.Validator.*;

/**
 * This view shows the ToDoItems in a list.
 */
public class RegistrationMenu extends View {

    public RegistrationMenu(Scanner scanner) {
        super("RegistrationMenu", scanner);
    }

    // registering for an account
    // Note: Need to add a limit tha forces you out of the while loop like a number of tries
    // maybe a quit option while you are interring your information
    @Override
    public void renderView() throws SQLException {
        CustomerDAO cDAO = new CustomerDAO(viewManager.getConn());

        Scanner sc = new Scanner(System.in);

        System.out.println("========== Registration: ==========");

        while(true) {
            // Asks for first name
            System.out.println("\nEnter your first name: ");
            String firstname = sc.nextLine();
            if (validateName(firstname)) {
                System.out.println("=='First name is valid'==");
            } else {
                System.out.println("=='First name is NOT valid'==");
                return;
            }

            // Asks for last name
            System.out.println("\nEnter your last name: ");
            String lastname = sc.nextLine();
            if (validateName(lastname)) {
                System.out.println("=='Last name is valid'==");
            } else {
                System.out.println("=='Last name is NOT valid'==");
                return;
            }

            // Ask for Username
            System.out.println("\nEnter username: ");
            String username = sc.nextLine();
            if (validateUsername(username)) {
                System.out.println("=='Username is valid'==");
            } else {
                System.out.println("=='Username is NOT valid'==");
                return;
            }

            // Ask for Password
            System.out.println("\nEnter password: ");
            String password = sc.nextLine();
            if (validatePassword(password)) {
                System.out.println("=='password is valid'==");
            } else {
                System.out.println("=='password is NOT valid'==");
                return;
            }

            // Ask for Email
            System.out.println("\nEnter Email: ");
            String email = sc.nextLine();
            if (validatePassword(password)) {
                System.out.println("=='Email is valid'==");
            } else {
                System.out.println("=='Email is NOT valid'==");
                return;
            }

            Customer newCustomer = new Customer(firstname, lastname, username, password, email);

            try {
                cDAO.newCustomer(newCustomer);
            } catch (SQLException | UserAlreadyExistsException e) { /*More Exceptions: UsernameAlreadyExist? | EmailAlreadyExist */
                System.out.println(e.getMessage());
            }
            break;
        }

        System.out.println("\n=================================");
        viewManager.navigate("MainMenu");
    }
}

//            /////////////////// we try the new validator class
//            try {
//                firstName = Validator.validateString("Enter first name: ", 3, 5, 25);
//            } catch (TooManyAttemptsException e) {
//                System.out.println("Too many attempts! returning to main menu...");
//                viewManager.navigate("MainMenu");
//            }
//            /////////////////////////////////////////////////////////
//            //once we are confident the first name is valid:

