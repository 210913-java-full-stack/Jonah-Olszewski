package views;

import DAOs.AccountDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class AccountTypeMenu extends View {
    public AccountTypeMenu(Scanner scanner) {
        super("AccountTypeMenu", scanner);
    }

    @Override
    public void renderView() throws SQLException {
        AccountDAO aDAO = new AccountDAO(viewManager.getConn());

        Scanner sc = new Scanner(System.in);

        System.out.println("========== Account: ==========");
    while (true) {

        System.out.print("Enter account type 'Savings' or 'Checking': ");
        String accountType = sc.nextLine();
        // validation

        aDAO.newAccount(accountType);
            break;
        }
        System.out.println("\n=================================");
        viewManager.navigate("SubMenu");
    }

    // make one or multiple cheking or savings accounts
}
