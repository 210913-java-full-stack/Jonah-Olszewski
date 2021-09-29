package DAOs;

import utils.datastructure.MyArrayList;

import java.sql.SQLException;

public interface BankingAppCRUD<T> {
    //create
    //save object to database method
    public void save(T t) throws SQLException;

    //read
    //query data from database, fill in empty model object
    public T getByID(int id) throws SQLException;
    public MyArrayList<T> getAll() throws SQLException;


    //public ToDoItem getItemByKeyword(String keyword); //SELECT * FROM items WHERE message LIKE "%KEYWORD%"
    //update
    // we will use the save() method for updates


    //delete
    //remove by ID
//    public void deleteCustomerByID(int customerId) throws SQLException;
//    public void deleteBankAccountByID(int accountId) throws SQLException;
}
