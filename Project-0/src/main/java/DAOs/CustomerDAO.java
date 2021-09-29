package DAOs;

import models.Customer;
import utils.datastructure.MyArrayList;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CustomerDAO implements BankingAppCRUD<Customer> {
    private Connection conn;

    public CustomerDAO(Connection conn){
        this.conn = conn;
    }

    @Override
    public void save(Customer customer) throws SQLException {
        String sql = "SELECT * FROM customers WHERE customerId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, customer.getCustomerId());

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
            //UPDATE - item already exists in table
            String updateStatement = "UPDATE customers SET customerId = ?," +
                    "firstName = ?, lastName = ?, userName  = ?, passWord = ?, email = ?";
            pstmt = conn.prepareStatement(updateStatement);
            pstmt.setInt(1, customer.getCustomerId());
            pstmt.setString(2, customer.getFirstName());
            pstmt.setString(3, customer.getLastName());
            pstmt.setString(4, customer.getUserName());
            pstmt.setString(5, customer.getPassWord());
            pstmt.setString(6, customer.getEmail());

            pstmt.executeUpdate();
        } else {
            //INSERT - Item doesn't already exist in table
            String insertStatement = "INSERT INTO customers (customerId, fistName, lastName, " +
                    "userName, passWord, email) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertStatement);
            pstmt.setInt(1, customer.getCustomerId());
            pstmt.setString(2, customer.getFirstName());
            pstmt.setString(3, customer.getLastName());
            pstmt.setString(4, customer.getUserName());
            pstmt.setString(5, customer.getPassWord());
            pstmt.setString(6, customer.getEmail());

            pstmt.executeUpdate();
        }
    }

    @Override
    public Customer getByID(int id) throws SQLException {
        String sql = "SELECT * FROM customers WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            //Customer result = new Customer(rs.getInt("id"), rs.getString("message"), rs.getBoolean("complete"));  ASK ABOUT THIS
        } else {
            //return;
        }
        return null;
    }

    @Override
    public MyArrayList<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM customers";
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        List<Customer> resultList = new LinkedList<>();

        while (rs.next()) {
            //Customer newItem = new Customer(rs.getInt("id"), rs.getString("message"), rs.getBoolean("complete"));
            //resultList.add(newItem);
        }
        //return resultList;
        return null;
    }
}
