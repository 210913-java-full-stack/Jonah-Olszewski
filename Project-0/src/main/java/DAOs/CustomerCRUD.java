package DAOs;

import exceptions.UserAlreadyExistsException;
import models.Customer;
import utils.datastructure.MyArrayList;

import java.sql.SQLException;

public interface CustomerCRUD {

    //void updateCustomer(Customer customer);

    //int insertCustomer(Customer customer);

    int getCustomerId() throws SQLException;

    int getAccountId() throws SQLException;

    void newCustomer(Customer customer) throws SQLException, UserAlreadyExistsException;

    Customer getCustomerById(int id);

    MyArrayList<Customer> getAllCustomers();

    Customer getCustomerByUsername(String username);

    void deleteCustomerById(int id);
}
