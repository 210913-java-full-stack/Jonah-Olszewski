package utils;

import exceptions.AccountDoesNotExistException;
import exceptions.IncorrectPasswordException;
import utils.datastructure.MyArrayList;
import views.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * ViewManager class is designed as a singleton which handles rendering the different console I/O views we have.
 * It does this by maintaining a list of possible views, and when navigate is invoked with the name of a view it
 * searches the list for that view and queues it up to be rendered in the next goToNextView() call from main.
 * goToNextView calls the view's renderView() method.
 */
public class ViewManager {
        private static ViewManager viewManager;
        private static View nextView;
        private MyArrayList<View> viewList;
        private boolean running;
        private Connection conn;
        private Scanner scanner;

//        private Customer c;


        private ViewManager() {
            viewManager = this;
            running = true;
            conn = ConnectionManager.getConnection();
            scanner = new Scanner(System.in);
            viewList = new MyArrayList<>();


            //set up views
            viewList.add(new MainMenu(scanner));
            viewList.add(new RegistrationMenu(scanner));
            viewList.add(new LogInMenu(scanner));

            viewList.add(new SubMenu(scanner));
            viewList.add(new AccountTypeMenu(scanner));
            viewList.add(new DepositMenu(scanner));
            viewList.add(new WithdrawMenu(scanner));
            viewList.add(new DisplayAllAccountsMenu(scanner));
        }

        /**
         * This is the method to retrieve the singleton instance of our ViewManager
         */
        public static ViewManager getViewManager() {
            if(viewManager == null) {
                viewManager = new ViewManager();
            }
            return viewManager;
        }

        /**
         * This searches the list for a view by name and queues that view up to be rendered
         * next time the main loop calls goToNextView();
         *
         */
        public void navigate(String destination) {
            // in order to fix this you need to implement you own iterator in your MyArrayList
            for(View view : viewList) {
                if(view.getViewName().equals(destination)) {
                    nextView = view;
                }
            }
        }

//        public void navigate(String destination, Customer a) {
//            // in order to fix this you need to implement you own iterator in your MyArrayList
//            c = a;
//            for(View view : viewList) {
//                if(view.getViewName().equals(destination)) {
//                    nextView = view;
//                }
//            }
//        }

        /**
         * This is called from the main loop, and just calls the queued next view's render method.
         *
         */
        public void goToNextView() throws SQLException, AccountDoesNotExistException, IncorrectPasswordException {
            nextView.renderView();
        }

//        public void goToNextView(Customer a) throws SQLException {
//            nextView.renderView(a);
//        }

        public boolean isRunning() {
            return running;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        public Connection getConn() {
            return conn;
        }
}
