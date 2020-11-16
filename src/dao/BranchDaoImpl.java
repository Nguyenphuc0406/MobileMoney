/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Branch;

/**
 *
 * @author Phuc ND
 */
public class BranchDaoImpl extends JDBCConnection implements BranchDao{

    @Override
    public Branch getAllBranch(String branchCode) {
        String sql = "SELECT u.branch_id, u.branch_name, u.branch_code, u.branch_amount FROM branch u WHERE u.branch_code LIKE ? ";
        try {
            Connection conn = super.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, "%" + branchCode + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Branch branch = new Branch();
                branch.setId(resultSet.getInt("branch_id"));
                branch.setBranchName(resultSet.getString("branch_name"));
                branch.setBranchCode(resultSet.getString("branch_code"));
                branch.setBranchAmount(resultSet.getInt("branch_amount"));
                return branch;
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e);
        }
        return null;
    }

    @Override
    public void updateBranch(Branch branch) {
        String sql = "UPDATE branch SET branch_name = ?, branch_code = ?, branch_amount = ? WHERE branch_code = ?";
        try {
            Connection conn = super.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, branch.getBranchName());
            preparedStatement.setString(2, branch.getBranchCode());
            preparedStatement.setInt(3, branch.getBranchAmount());
            preparedStatement.setString(4, branch.getBranchCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception: " + e);
        }
    
    
    }
}
