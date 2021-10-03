package views;

import java.util.Scanner;

public class SubMenu extends View {
    public SubMenu(Scanner scanner) {
        super("SubMenu", scanner);
    }

    /**
     * We print options prompting the user for input, accept that input and navigate to the appropriate next view.
     */
    @Override
    public void renderView() {
        System.out.println("=== SUB MENU ===\nWhat would you like to do?\n\n1) Make an account: " +
                "\n2) Make a Deposit.\n3) Make a withdraw." +
                "\n4) Show all accounts.\nQ) Quit");
        String newInput = scanner.nextLine();
        switch (newInput) {
            case "1":
                viewManager.navigate("AccountTypeMenu");
                break;
            case "2":
                viewManager.navigate("DepositMenu");
                break;
            case "3":
                viewManager.navigate("WithdrawMenu");
                break;
            case "4":
                viewManager.navigate("DisplayAllAccountsMenu");
                break;
            case "Q":
            case "q":
                viewManager.setRunning(false);
                break;
            default:
                System.out.println("Invalid input! Please type one of the numbers from the list.");
        }
    }
}
