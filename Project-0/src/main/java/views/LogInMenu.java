package views;

import DAOs.CustomerDAO;
import exceptions.AccountDoesNotExistException;
import exceptions.IncorrectPasswordException;

import java.sql.SQLException;
import java.util.Scanner;

public class LogInMenu extends View {

    public LogInMenu(Scanner scanner) {
        super("LogInMenu", scanner);
    }

    // logging into an account

    @Override
    public void renderView() throws SQLException {
        CustomerDAO cDAO = new CustomerDAO(viewManager.getConn());

        Scanner sc = new Scanner(System.in);

        System.out.println("\n========== Log in: ==========");

        while (true) {
            System.out.println("\nEnter your Username: ");
            String username = sc.nextLine();

            System.out.println("\nEnter your Password: ");
            String password = sc.nextLine();

            // validation
            try {
                if(cDAO.loginVerification(username, password) != null){
                    System.out.println("Welcome " + username);
                    break;
                } else {
                    System.out.println("Username and password do not match");
                    return;
                }
            } catch (AccountDoesNotExistException | IncorrectPasswordException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        System.out.println("\n=================================");
        viewManager.navigate("SubMenu");
    }
}

