package com.ijse.dao;

import com.ijse.model.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDAO {
    private final DataSource dataSource;

    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Validate users for login
    public User validateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setJobRole(rs.getString("job_role"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Save Users
    public boolean saveUser(User user) {
        String sql = "INSERT INTO users (user_id, name, address, mobile, email, username, password, department, job_role) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setString(1, user.getUserId());
            pst.setString(2, user.getName());
            pst.setString(3, user.getAddress());
            pst.setString(4, user.getMobile());
            pst.setString(5, user.getEmail());
            pst.setString(6, user.getUsername());
            pst.setString(7, user.getPassword());
            pst.setString(8, user.getDepartment());
            pst.setString(9, user.getJobRole());

            int result = pst.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging
            return false;
        }
    }
}
