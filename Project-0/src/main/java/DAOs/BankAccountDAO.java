package DAOs;

import models.BankAccount;
import utils.datastructure.MyArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccountDAO implements BankingAppCRUD<BankAccount> {
    private Connection conn;

    public BankAccountDAO(Connection conn){
        this.conn = conn;
    }


    @Override
    public void save(BankAccount bankAccount) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE accountIdId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, bankAccount.getAccountId());

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
            //UPDATE - item already exists in table
            String updateStatement = "UPDATE accounts SET accountId = ?, accountType = ?, balance = ?";
            pstmt = conn.prepareStatement(updateStatement);
            pstmt.setInt(1, bankAccount.getAccountId());
            pstmt.setString(2, bankAccount.getAccountType());
            pstmt.setDouble(3, bankAccount.getBalance());

            pstmt.executeUpdate();
        } else {
            //INSERT - Item doesn't already exist in table
            String insertStatement = "INSERT INTO accounts (accountId, accountType, balance) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(insertStatement);
            pstmt.setInt(1, bankAccount.getAccountId());
            pstmt.setString(2, bankAccount.getAccountType());
            pstmt.setDouble(3, bankAccount.getBalance());

            pstmt.executeUpdate();
        }
    }

    @Override
    public BankAccount getByID(int id) throws SQLException {
        return null;
    }


    @Override
    public MyArrayList<BankAccount> getAll() throws SQLException {
        return null;
    }
}
