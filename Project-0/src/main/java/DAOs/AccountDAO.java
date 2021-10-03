package DAOs;

import models.BankAccount;
import utils.datastructure.MyArrayList;

import java.sql.*;

public class BankAccountDAO implements BankingAccountCRUD<BankAccount> {
    private Connection conn;

    public BankAccountDAO(Connection conn){
        this.conn = conn;
    }


    @Override
    public void save(BankAccount bankAccount) throws SQLException {
        String sqlSave = "SELECT * FROM accounts WHERE accountIdId = ?";
        PreparedStatement pstmt = conn.prepareStatement(sqlSave);
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
    public BankAccount getByID(int id) {
        String sqlGetID = "SELECT * FROM accounts WHERE id = ?";
        BankAccount bankAccount = new BankAccount();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sqlGetID);
            pstmt.setInt(1,id);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlGetID);

            if(rs.next()){
                bankAccount.setAccountId(rs.getInt(1));
                bankAccount.setAccountType(rs.getString(2)); //will update eventually
                bankAccount.setBalance(rs.getDouble(3));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return bankAccount;
    }



    @Override
    public MyArrayList<BankAccount> getAll() {
        MyArrayList<BankAccount> bankAccount = new MyArrayList<>();
        String sqlGetAll = "SELECT * FROM accounts";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlGetAll);

            //List<Customer> resultList = new LinkedList<>();

            while (rs.next()) {
                bankAccount.add(new BankAccount(rs.getInt(1),rs.getString(2),rs.getInt(3)));  //...
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return bankAccount;
    }
}
