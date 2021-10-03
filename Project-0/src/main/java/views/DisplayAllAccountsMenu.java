package views;

import DAOs.AccountDAO;
import models.Account;
import utils.datastructure.MyArrayList;

import java.sql.SQLException;
import java.util.Scanner;

public class DisplayAllAccountsMenu extends View {
    public DisplayAllAccountsMenu(Scanner scanner) {
        super("DisplayAllAccountsMenu", scanner);
    }

    @Override
    public void renderView() throws SQLException {
        //retrieve list
        MyArrayList<Account> accountsList;
        AccountDAO aDAO = new AccountDAO(viewManager.getConn());
        accountsList = aDAO.getAllAccounts();

        for(int i = 0; i < accountsList.size(); i++) {
            System.out.println("$%,.2f\n" + accountsList.get(i).getBalance()); // once here you need to check if this works or not
        }

        System.out.println("=================================");
        viewManager.navigate("SubMenu");
    }
    // displaying all accounts WITH current balance in "$%,.2f\n" currency form.
}
