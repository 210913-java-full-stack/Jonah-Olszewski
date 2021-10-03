package views;

import DAOs.AccountDAO;
import models.Account;
import utils.datastructure.MyArrayList;

import java.sql.SQLException;
import java.util.Scanner;

/*
I'd try using one of the SQL statements from Transfer $199.99 from Jason Smith to Amanda Smith
Deposit UPDATE accounts a
JOIN accounts_customers ac ON a.account_id = ac.account_id
JOIN customers c on ac.customer_id = c.customer_id
JOIN address ad on c.address_id = ad.address_id
SET a.balance = (balance + 199.99)
 */

public class DepositMenu extends View {
    // deposit into cheking/saving account, specific cheking/saving accounts, or all cheking(s)/saving(s) accounts
    public DepositMenu(Scanner scanner) {
        super("DepositMenu", scanner);
    }

    @Override
    public void renderView() throws SQLException {
        MyArrayList<Account> accountsList;
        AccountDAO aDAO = new AccountDAO(viewManager.getConn());
        accountsList = aDAO.getAllAccounts();

        Scanner sc = new Scanner(System.in);

        System.out.println("========== Deposit: ==========");
        double deposit = sc.nextDouble();

        // deposit for one account


        // deposit for multiple accounts


        // deposits for all accounts


        viewManager.navigate("SubMenu");
    }

}
