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

//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("user_id") == null) {
//            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
//            return;
//        }
//
//        String action = request.getParameter("action"); // insert, update, delete
//        String userId = (String) session.getAttribute("user_id");
//
//        String complaintId = request.getParameter("complaint_id");
//        String title = request.getParameter("title");
//        String description = request.getParameter("description");
//
//        Complaint complaint = new Complaint();
//        complaint.setComplaintId(complaintId);
//        complaint.setUserId(userId);
//        complaint.setTitle(title);
//        complaint.setDescription(description);
//
//        ServletContext context = getServletContext();
//        DataSource ds = (DataSource) context.getAttribute("ds");
//        ComplaintDAO complaintDAO = new ComplaintDAO(ds);
//
//        try {
//            boolean result = false;
//
//            if ("Submit".equalsIgnoreCase(action)) {
//                complaint.setComplaintId("CMP-" + UUID.randomUUID().toString().substring(0, 8));
//                result = complaintDAO.saveComplaint(complaint);
//            } else if ("update".equalsIgnoreCase(action)) {
//                result = complaintDAO.updateComplaint(complaint); // You must implement this method in DAO
//            } else if ("delete".equalsIgnoreCase(action)) {
//                result = complaintDAO.deleteComplaint(complaintId); // You must implement this method in DAO
//            }
//
//            if (result) {
//                response.sendRedirect(request.getContextPath() + "/emp-complaint?message=Success");
//            } else {
//                response.sendRedirect(request.getContextPath() + "/emp-complaint?error=Action failed");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            request.setAttribute("error", "Action failed. Try again.");
//            request.getRequestDispatcher("/jsp/employee/dashboard.jsp").forward(request, response);
//        }
//    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user_id") == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
            return;
        }

        String userId = (String) session.getAttribute("user_id");
        String action = request.getParameter("action");
        String complaintId = request.getParameter("complaint_id");

        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/emp-complaint?error=Invalid+action");
            return;
        }

        DataSource ds = (DataSource) getServletContext().getAttribute("ds");
        ComplaintDAO complaintDAO = new ComplaintDAO(ds);

        try {
            boolean result = false;

            if (action.equalsIgnoreCase("insert")) {
                Complaint newComplaint = new Complaint();
                newComplaint.setComplaintId("CMP-" + UUID.randomUUID().toString().substring(0, 8));
                newComplaint.setUserId(userId);
                newComplaint.setTitle(request.getParameter("title"));
                newComplaint.setDescription(request.getParameter("description"));
                result = complaintDAO.saveComplaint(newComplaint);

            } else if (action.equalsIgnoreCase("update")) {
                Complaint updateComplaint = new Complaint();
                updateComplaint.setComplaintId(complaintId);
                updateComplaint.setTitle(request.getParameter("title"));
                updateComplaint.setDescription(request.getParameter("description"));
                result = complaintDAO.updateComplaint(updateComplaint);

            } else if (action.equalsIgnoreCase("delete")) {
                result = complaintDAO.deleteComplaint(complaintId);

            } else {
                response.sendRedirect(request.getContextPath() + "/emp-complaint?error=Invalid+action+type");
                return;
            }

            if (result) {
                response.sendRedirect(request.getContextPath() + "/emp-complaint?message=Operation+successful");
            } else {
                response.sendRedirect(request.getContextPath() + "/emp-complaint?error=Operation+failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/emp-complaint?error=Server+error");
        }
    }

}
