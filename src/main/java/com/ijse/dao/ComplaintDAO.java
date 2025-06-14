package com.ijse.dao;

import com.ijse.model.Complaint;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComplaintDAO {
    private final DataSource dataSource;

    public ComplaintDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public boolean saveComplaint(Complaint complaint) throws Exception {
        String sql = "INSERT INTO complaints (complaint_id, user_id, title, description, status, remarks) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, complaint.getComplaintId());
            stmt.setString(2, complaint.getUserId());
            stmt.setString(3, complaint.getTitle());
            stmt.setString(4, complaint.getDescription());
            stmt.setString(5, "PENDING"); // default status
            stmt.setString(6, null); // no remarks initially

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Complaint> getComplaintsByUserId(String userId) throws SQLException {
        List<Complaint> complaints = new ArrayList<>();
        String sql = "SELECT * FROM complaints WHERE user_id = ?";

        System.out.println("DEBUG - Fetching complaints for userId: " + userId);

        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setComplaintId(rs.getString("complaint_id"));
                complaint.setUserId(rs.getString("user_id"));
                complaint.setTitle(rs.getString("title"));
                complaint.setDescription(rs.getString("description"));
                complaint.setStatus(rs.getString("status"));
                complaint.setRemarks(rs.getString("remarks"));
                complaint.setCreatedAt(rs.getString("created_at"));
                complaints.add(complaint);
            }
        }

        return complaints;
    }
}
