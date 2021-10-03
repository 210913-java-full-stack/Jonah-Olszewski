package DAOs;

import exceptions.InvalidAccountTypeException;
import models.Account;
import models.Customer;
import utils.datastructure.MyArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO implements AccountCRUD{
    private Connection conn;

    public AccountDAO(Connection conn){
        this.conn = conn;
    }

//    @Override
//    public void save(Account bankAccount) throws SQLException {
//        String sqlSave = "SELECT * FROM accounts WHERE accountIdId = ?";
//        PreparedStatement pstmt = conn.prepareStatement(sqlSave);
//        pstmt.setInt(1, bankAccount.getAccountId());
//
//        ResultSet rs = pstmt.executeQuery();
//
//        if(rs.next()) {
//            //UPDATE - item already exists in table
//            String updateStatement = "UPDATE accounts SET accountId = ?, accountType = ?, balance = ?";
//            pstmt = conn.prepareStatement(updateStatement);
//            pstmt.setInt(1, bankAccount.getAccountId());
//            pstmt.setString(2, bankAccount.getAccountType());
//            pstmt.setDouble(3, bankAccount.getBalance());
//
//            pstmt.executeUpdate();
//        } else {
//            //INSERT - Item doesn't already exist in table
//            String insertStatement = "INSERT INTO accounts (accountId, accountType, balance) VALUES (?, ?, ?)";
//            pstmt = conn.prepareStatement(insertStatement);
//            pstmt.setInt(1, bankAccount.getAccountId());
//            pstmt.setString(2, bankAccount.getAccountType());
//            pstmt.setDouble(3, bankAccount.getBalance());
//
//            pstmt.executeUpdate();
//        }
//    }

    public int newestAccountId;

    @Override
    public int getAccountId() throws SQLException {
        //We are grabbing the newest account ID that was used by the auto increment in the table.
        String sql = "SELECT * FROM accounts_customers";
        PreparedStatement findAccNumStmt = conn.prepareStatement(sql);
        ResultSet rs = findAccNumStmt.executeQuery();

        while (rs.next()) {
            newestAccountId = rs.getInt("account_id");
        }

        return newestAccountId;
    }

    @Override
    public void newAccount(Account account) throws SQLException, InvalidAccountTypeException {
        getAccountId();

        //have to check and make sure the user doesn't exist already.
        String sql = "SELECT account_id FROM accounts WHERE account_id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, account.getAccountId());

        ResultSet rs = pstmt.executeQuery();

        //if results come back, the account already exists.
        if (rs.next()) {
            throw new InvalidAccountTypeException();
        } else {
            String insertStatement = "INSERT INTO accounts_customers (customer_id, account_id) VALUES (?,?)";
            PreparedStatement preparedInsertStmt = conn.prepareStatement(insertStatement);
            preparedInsertStmt.setInt(1, customer.getCustomerId());
            newestAccountId++;
            preparedInsertStmt.setInt(2, newestAccountId);
            preparedInsertStmt.executeUpdate();


            String acctInsertStmt = "INSERT INTO accounts (account_id, account_type, balance) VALUES (?,?,?)";
            PreparedStatement preparedAccountStmt = conn.prepareStatement(acctInsertStmt);
            preparedAccountStmt.setInt(1, newestAccountId);
            preparedAccountStmt.setString(2, accountType);
            preparedAccountStmt.setInt(3, 0);
            preparedAccountStmt.executeUpdate();
        }
    }


//    @Override
//    public int insertAccount(Account account) {
//        String sql = "INSERT INTO accounts (account_type, balance) VALUES (?,?)";
//        int insertId = 0;
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            ps.setDouble(1, account.getBalance());
//            ps.setString(2, account.getAccountType());
//            ps.execute();
//            ResultSet rs = ps.getGeneratedKeys();
//            if (rs.next()) {
//                insertId = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return insertId;
//    }
//
//    @Override
//    public void updateAccount(Account account) {
//        String sql = "UPDATE accounts SET account_type = ?, balance = ? WHERE account_id = ?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, account.getAccountType());
//            ps.setDouble(2, account.getBalance());
//            ps.setInt(3, account.getAccountId());
//            ps.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public Account getAccountById(int accountId) {
        String sql = "SELECT * FROM WHERE id = ?";
        Account account = new Account();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account.setAccountId(rs.getInt(1));
                account.setAccountType(rs.getString(2));
                account.setBalance(rs.getDouble(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public MyArrayList<Account> getAllAccounts() {
        MyArrayList<Account> accounts = new MyArrayList<>();
        String sql = "SELECT * FROM accounts";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accounts.add(new Account(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public MyArrayList<Account> getAccountsByCustomerId(int customerId) {
        MyArrayList<Account> accounts = new MyArrayList<>();
        String sql = "SELECT * FROM accounts WHERE customer_id";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                accounts.add(new Account(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

//    @Override
//    public MyArrayList<Account> getAllSavingsAccounts() {
//        MyArrayList<Account> accounts = new MyArrayList<>();
//        String sql = "SELECT * FROM accounts WHERE account_type = 2";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                accounts.add(new Account(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return accounts;
//    }

    @Override
    public void deleteAccountById(int id) {
        String sql = "DELETE from accounts WHERE account_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
