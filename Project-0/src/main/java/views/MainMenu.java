package views;

import java.util.Scanner;

/**
 * This is the main menu, the first menu a user will interact with when the application starts
 */
public class MainMenu extends View {
    public MainMenu(Scanner scanner) {
        super("MainMenu", scanner);
    }

    /**
     * We print options prompting the user for input, accept that input and navigate to the appropriate next view.
     */
    @Override
    public void renderView() {
        //Write the I/O here, also the navigation based on processing input
        System.out.println("===MAIN MENU===\nWhat would you like to do?\n\n1) Register for an account.\n2) Log into existing account.\nQ) Quit");
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                viewManager.navigate("RegistrationMenu");
                break;
            case "2":
                viewManager.navigate("LogInMenu");
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
