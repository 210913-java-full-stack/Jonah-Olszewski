package DAOs;

import models.ToDoItem;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ToDoItemDAO implements ToDoCrud{
    private Connection conn;

    public ToDoItemDAO(Connection conn) {
        this.conn = conn;
    }


    @Override
    public void save(ToDoItem row) throws SQLException {
        String sql = "SELECT * FROM to_do_items WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, row.getId());

        ResultSet rs = pstmt.executeQuery();


        if(rs.next()) {
            //UPDATE - item already exists in table
            String updateStatement = "UPDATE to_do_items SET message = ?, complete = ? WHERE id = ?";
            PreparedStatement preparedUpdateStatement = conn.prepareStatement(updateStatement);
            preparedUpdateStatement.setString(1, row.getMessage());
            preparedUpdateStatement.setBoolean(2, row.isComplete());
            preparedUpdateStatement.setInt(3, row.getId());

            preparedUpdateStatement.executeUpdate();

        } else {
            //INSERT - Item doesn't already exist in table
            String insertStatement = "INSERT INTO to_do_items (message, complete) VALUES (?, ?)";
            PreparedStatement preparedInsertStatement = conn.prepareStatement(insertStatement);
            preparedInsertStatement.setString(1, row.getMessage());
            preparedInsertStatement.setBoolean(2, row.isComplete());

            preparedInsertStatement.executeUpdate();

        }



    }

    @Override
    public void getItemByID(int id) throws SQLException {
        String sql = "SELECT * FROM to-do-items WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            ToDoItem result = new ToDoItem(rs.getInt("id"), rs.getString("message"), rs.getBoolean("complete"));
        } else {
            return null;
        }
    }

    @Override
    public List<ToDoItem> getAllItems() throws SQLException{
        String sql = "SELECT * FROM to-do-items";
        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(sql);

        List<ToDoItem> resultList = new LinkedList<>();

        while (rs.next()) {
            ToDoItem newItem = new ToDoItem(rs.getInt("id"), rs.getString("message"), rs.getBoolean("complete"));
            resultList.add(newItem);
        }
        return resultList;
    }

    @Override
    public void deleteByID(int id) throws SQLException{
        String sql = "DELETE FROM to-do-items WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,id);

        pstmt.executeUpdate();
    }
}
