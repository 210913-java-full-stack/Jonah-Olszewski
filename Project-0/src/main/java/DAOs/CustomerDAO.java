package DAOs;

import exceptions.AccountDoesNotExistException;
import exceptions.IncorrectPasswordException;
import exceptions.UserAlreadyExistsException;
import models.Customer;
import utils.datastructure.MyArrayList;

import java.sql.*;

public class CustomerDAO implements CustomerCRUD{
    private Connection conn;

    public CustomerDAO(Connection conn){
        this.conn = conn;
    }

//    @Override
//    public void updateCustomer(Customer customer) {
//        String sql = "UPDATE customer SET first_name = ?,  last_name = ?, username = ?, password = ? email = ? WHERE user_id = ?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, customer.getFirstname());
//            ps.setString(2, customer.getLastname());
//            ps.setString(3, customer.getUsername());
//            ps.setString(4, customer.getPassword());
//            ps.setString(5, customer.getEmail());
//            ps.setInt(6, customer.getCustomerId());
//            ps.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public int insertCustomer(Customer customer) {
//        String sql = "INSERT INTO customers (first_name, last_name, " +
//                "username, password, email) VALUES (?, ?, ?, ?, ?)";
//        int insertId = 0;
//        try {
//            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            pstmt.setString(1, customer.getFirstname());
//            pstmt.setString(2, customer.getLastname());
//            pstmt.setString(3, customer.getUsername());
//            pstmt.setString(4, customer.getPassword());
//            pstmt.setString(5, customer.getEmail());
//            pstmt.execute();
//            ResultSet rs = pstmt.getGeneratedKeys();
//            if (rs.next()) {
//                insertId = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return insertId;
//    }

    public int newestCustomerId, newestAccountId;

    @Override
    public int getCustomerId() throws SQLException {
        //We are grabbing the newest account ID that was used by the auto increment in the table.
        String sql = "SELECT * FROM accounts_customers";
        PreparedStatement findCusNumStmt = conn.prepareStatement(sql);
        ResultSet rs = findCusNumStmt.executeQuery();

        while (rs.next()) {
            newestCustomerId = rs.getInt("customer_id");
        }

        return newestCustomerId;
    }

    @Override
    public int getAccountId() throws SQLException {
        //We are grabbing the newest account ID that was used by the auto increment in the table.
        String sql = "SELECT * FROM accounts_customers";
        PreparedStatement findAccNumStmt = conn.prepareStatement(sql);
        ResultSet rs = findAccNumStmt.executeQuery();

        while (rs.next()) {
            newestAccountId = rs.getInt("account_id");
        }

        return  newestAccountId;
    }

    @Override
    public void newCustomer(Customer customer) throws SQLException, UserAlreadyExistsException {
        getCustomerId();
        getAccountId();

        //have to check and make sure the user doesn't exist already.
        String sql = "SELECT customer_id FROM customers WHERE customer_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, customer.getCustomerId());

        ResultSet rs = pstmt.executeQuery();

        //if results come back, the account already exists.
        if(rs.next())
        {
            throw new UserAlreadyExistsException();
        }
        else
        {
            String insertStatement = "INSERT INTO accounts_customers (customer_id, account_id) VALUES (?,?)";
            PreparedStatement preparedInsertStmt = conn.prepareStatement(insertStatement);
            newestCustomerId++;
            preparedInsertStmt.setInt(1,newestCustomerId);
            newestAccountId++;
            preparedInsertStmt.setInt(2,newestAccountId);
            preparedInsertStmt.executeUpdate();

            String newCustomer = "INSERT INTO customers (customer_id, first_name, last_name, username, password, email) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedInsertStatement= conn.prepareStatement(newCustomer);
            preparedInsertStatement.setInt(1, newestCustomerId);
            preparedInsertStatement.setString(2,customer.getFirstname());
            preparedInsertStatement.setString(3,customer.getLastname());
            preparedInsertStatement.setString(4,customer.getUsername());
            preparedInsertStatement.setString(5,customer.getPassword());
            preparedInsertStatement.setString(6,customer.getEmail());
            preparedInsertStatement.executeUpdate();

            String acctInsertStmt = "INSERT INTO accounts (account_id, account_type, balance) VALUES (?,?,?)";
            PreparedStatement preparedAccountStmt = conn.prepareStatement(acctInsertStmt);
            preparedAccountStmt.setInt(1,newestAccountId);
            preparedAccountStmt.setString(2,"Checking");
            preparedAccountStmt.setInt(3,0);
            preparedAccountStmt.executeUpdate();
        }
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = new Customer();
        String sqlGetID = "SELECT * FROM customers WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlGetID);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                customer.setCustomerId(rs.getInt(1));
                customer.setUsername(rs.getString(2));
                customer.setPassword(rs.getString(3));
                customer.setFirstname(rs.getString(4));
                customer.setLastname(rs.getString(5));
                customer.setEmail(rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public Customer loginVerification(String username, String password) throws SQLException, AccountDoesNotExistException, IncorrectPasswordException {
        Customer returnCustomer;
        //check username exists
        String getStatement = "SELECT * FROM customers WHERE username = ?";
        PreparedStatement firstPreparedStmt = conn.prepareStatement(getStatement);
        firstPreparedStmt.setString(1, username);

        ResultSet checkCustomerResult = firstPreparedStmt.executeQuery();

        if(!checkCustomerResult.next())
        {
            throw new AccountDoesNotExistException();
        }

        //check username and password combination
        getStatement = "SELECT * FROM customers WHERE (username = ?) AND (password = ?)";
        PreparedStatement preparedGetStmt = conn.prepareStatement(getStatement);
        preparedGetStmt.setString(1, username);
        preparedGetStmt.setString(2, password);

        ResultSet getCustomerResult = preparedGetStmt.executeQuery();

        //if this username and password combination exist, then log them in. Otherwise they put in the wrong password, throw the exception.
        if(getCustomerResult.next())
        {
            returnCustomer = new Customer(getCustomerResult.getString("first_name"), getCustomerResult.getString("last_name"),
                    getCustomerResult.getString("username"), getCustomerResult.getString("password"), getCustomerResult.getString("email"));
            return returnCustomer;
        }
        else
        {
            throw new IncorrectPasswordException();
        }
    }




//        //Customer customer = new Customer();
//        String sqlLogIn = "SELECT * FROM customers WHERE userName = ? AND passWord = ?";
//
//        try{
//            PreparedStatement pstmt = conn.prepareStatement(sqlLogIn);
//            pstmt.setString(1, username);
//            pstmt.setString(2, password);
//
//            ResultSet rs = pstmt.executeQuery();
//
//            if(rs.next()) {
//                customer.setUsername(rs.getString("userName"));
//                customer.setPassword(rs.getString("passWord"));
//                customer.setCustomerId(rs.getInt(1));
//                customer.setFirstname(rs.getString(2));
//                customer.setLastname(rs.getString(3));
//                customer.setEmail(rs.getString(4));
//            }
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//
//        return customer;
//    }

    @Override
    public MyArrayList<Customer> getAllCustomers() {
        MyArrayList<Customer> customer = new MyArrayList<>();
        String sqlGetAll = "SELECT * FROM customers";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlGetAll);

            while (rs.next()) {
                customer.add(new Customer(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return customer;
    }

//    @Override
//    public MyArrayList<Customer> getCustomerByAccount(int accountId) {
//        return null;
//    }

    @Override
    public Customer getCustomerByUsername(String username) {
        Customer customer = new Customer();
        String sql = "SELECT * FROM customer WHERE username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer.setCustomerId(rs.getInt(1));
                customer.setFirstname(rs.getString(2));
                customer.setLastname(rs.getString(3));
                customer.setUsername(rs.getString(4));
                customer.setPassword(rs.getString(5));
                customer.setEmail(rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }


    @Override
    public void deleteCustomerById(int id) {
        String sql = "DELETE from customer WHERE customer_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
