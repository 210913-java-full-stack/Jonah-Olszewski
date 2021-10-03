
import exceptions.AccountDoesNotExistException;
import exceptions.IncorrectPasswordException;
import utils.ConnectionManager;
import utils.ViewManager;
import java.sql.Connection;
import java.sql.SQLException;

//This is where our program starts, with the main method and contains the menus and primary loop.
public class Driver {
    public static void main(String[] args) {
        //viewManager
        ViewManager viewManager = ViewManager.getViewManager();

        //connection
        Connection conn = ConnectionManager.getConnection();


        /**
         * This is our main loop, it keep running until something sets the viewManager "running" flag = false;
         * With every loop we invoke the viewManager singleton's goToNextView() method.
         */
        viewManager.navigate("MainMenu");
        while (viewManager.isRunning()) {
            try {
                viewManager.goToNextView();
            } catch (SQLException | AccountDoesNotExistException | IncorrectPasswordException e) {
                e.printStackTrace();
            }
        }

        viewManager.navigate("SubMenu");
        while (viewManager.isRunning()) {
            try {
                viewManager.goToNextView();
            } catch (SQLException | AccountDoesNotExistException | IncorrectPasswordException e) {
                e.printStackTrace();
            }
        }
    }
}