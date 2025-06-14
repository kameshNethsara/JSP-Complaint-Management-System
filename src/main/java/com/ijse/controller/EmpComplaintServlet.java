package com.ijse.controller;

import com.ijse.dao.ComplaintDAO;
import com.ijse.model.Complaint;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet("/emp-complaint")
public class EmpComplaintServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
            return;
        }

        // Get user ID from session
        String userId = (String) request.getSession().getAttribute("user_id");
        System.out.println("DEBUG - userId from session: " + userId);

        DataSource ds = (DataSource) getServletContext().getAttribute("ds");
        ComplaintDAO complaintDAO = new ComplaintDAO(ds);

        try {
            List<Complaint> complaints = complaintDAO.getComplaintsByUserId(userId);
            request.setAttribute("complaintList", complaints);
            request.getRequestDispatcher("/jsp/employee/dashboard.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load complaints");
            request.getRequestDispatcher("/jsp/employee/dashboard.jsp").forward(request, response);
        }

//        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/employee/dashboard.jsp");
//        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String complaintId = "CMP-" + UUID.randomUUID().toString().substring(0, 8);
        //String userId = request.getParameter("user_id");
        String userId = (String) request.getSession().getAttribute("user_id");

        if (userId == null) {
            // User not logged in or session expired
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp?error=Please login first");
            return;
        }

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Complaint complaint = new Complaint();
        complaint.setComplaintId(complaintId);
        complaint.setUserId(userId);
        complaint.setTitle(title);
        complaint.setDescription(description);

        // Get DataSource
        ServletContext context = getServletContext();
        DataSource ds = (DataSource) context.getAttribute("ds");
        ComplaintDAO complaintDAO = new ComplaintDAO(ds);

        try {
            boolean isSaved = complaintDAO.saveComplaint(complaint);
            if (isSaved) {
               // response.sendRedirect(request.getContextPath() + "/jsp/employee/dashboard.jsp?message=Complaint submitted successfully");

               // Redirect to SubmitComplaintServlet again, so doGet() is called and complaintList is loaded
                response.sendRedirect(request.getContextPath() + "/emp-complaint?message=Complaint submitted successfully");
            } else {
                //response.sendRedirect(request.getContextPath() + "/jsp/employee/dashboard.jsp?error=Failed to submit complaint");
                response.sendRedirect(request.getContextPath() + "/emp-complaint?error=Failed to submit complaint");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to submit complaint. Please try again.");
            request.getRequestDispatcher("/jsp/employee/dashboard.jsp").forward(request, response);
        }
    }
}
