package views;

import DAOs.AccountDAO;
import models.Account;
import utils.datastructure.MyArrayList;

import java.sql.SQLException;
import java.util.Scanner;

/*
withdraw UPDATE accounts a
JOIN accounts_customers ac ON a.account_id = ac.account_id
JOIN customers c on ac.customer_id = c.customer_id
JOIN address ad on c.address_id = ad.address_id
SET a.balance = (balance - 199.99)
 */

public class WithdrawMenu extends View {
    // withdraw from cheking/saving account, specific cheking/saving accounts, or all cheking(s)/saving(s) accounts
    public WithdrawMenu(Scanner scanner) {
        super("viewName", scanner);
    }

    @Override
    public void renderView() throws SQLException {
        MyArrayList<Account> accountsList;
        AccountDAO aDAO = new AccountDAO(viewManager.getConn());
        accountsList = aDAO.getAllAccounts();

        Scanner sc = new Scanner(System.in);

        System.out.println("========== Withdraw: ==========");
        double withdraw = sc.nextDouble();

        // withdraw for one account


        // withdraw for multiple accounts


        // withdraw for all accounts


        viewManager.navigate("SubMenu");
    }
}
