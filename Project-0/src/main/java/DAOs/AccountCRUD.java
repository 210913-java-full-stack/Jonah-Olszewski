package DAOs;

import models.BankAccount;
import models.BankAccountType;
import utils.datastructure.MyArrayList;

import java.sql.SQLException;

public interface BankingAccountCRUD<T> {
    //create
    //save object to database method
    public void save(T t) throws SQLException;

    public int insertBankAccount(BankAccount bankAccount);



    //public int insertBankAccountLink(BankAccountLink bankAccountLink);

    //read
    //query data from database, fill in empty model object
    public T getByID(int id);
    public MyArrayList<T> getAll();

    public MyArrayList<BankAccount> getAllBankAccounts();
    public MyArrayList<BankAccount> getBankAccountsByCustomerId(int customerId);
    public MyArrayList<BankAccount> getAllSavingsBankAccounts();

    public MyArrayList<BankAccountType> getAllTypes();

    //public ToDoItem getItemByKeyword(String keyword); //SELECT * FROM items WHERE message LIKE "%KEYWORD%"
    //update
    // we will use the save() method for updates


    //delete
    //remove by ID
//    public void deleteCustomerByID(int customerId) throws SQLException;
//    public void deleteBankAccountByID(int accountId) throws SQLException;
}
