package DAOs;

import exceptions.InvalidAccountTypeException;
import models.Account;
import utils.datastructure.MyArrayList;

import java.sql.SQLException;

public interface AccountCRUD {
    //create
    //save object to database method
    //public int insertAccount(Account account);


    void newAccount(Account account) throws SQLException, InvalidAccountTypeException;

    //read
    //query data from database, fill in empty model object
    public Account getAccountById(int accountId);
    public MyArrayList<Account> getAllAccounts();
    public MyArrayList<Account> getAccountsByCustomerId(int customerId);
    //public MyArrayList<Account> getAllSavingsAccounts();


    //public ToDoItem getItemByKeyword(String keyword); //SELECT * FROM items WHERE message LIKE "%KEYWORD%"
    //update
    //public void updateAccount(Account account);

    int getAccountId() throws SQLException;


    //delete
    //remove by ID
    public void deleteAccountById(int id);
}
