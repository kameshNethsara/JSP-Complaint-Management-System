package com.ijse.controller;

import com.ijse.dao.UserDAO;
import com.ijse.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String name = firstName + " " + lastName;
        String address = req.getParameter("address");
        String mobile = req.getParameter("mobile");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String department = req.getParameter("department");
        String jobRole = req.getParameter("jobRole");

        if (!password.equals(confirmPassword)) {
            resp.sendRedirect(req.getContextPath() + "/jsp/signup.jsp?signup=pass_mismatch");
            return;
        }

        // Generate unique user ID (e.g., using UUID or auto-increment in DB)
        String userId = "U" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        // Create User object
        User user = new User(
                userId,
                name,
                address,
                mobile,
                email,
                username,
                password,
                department,
                jobRole
        );

        // Get DataSource
        ServletContext context = getServletContext();
        DataSource ds = (DataSource) context.getAttribute("ds");

        // Save user
        UserDAO userDAO = new UserDAO(ds);

        if (userDAO.isUsernameTaken(username)) {
            resp.sendRedirect(req.getContextPath() + "/jsp/signup.jsp?signup=username_taken");
            return;
        }

        boolean success = userDAO.saveUser(user);

        if (success) {
            // Redirect to log in with success alert
            resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp?signup=success");
        } else {
            //signup.jsp?signup=fail
            resp.sendRedirect(req.getContextPath() + "/jsp/signup.jsp?signup=fail");
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        DataSource ds = (DataSource) context.getAttribute("ds");
        UserDAO userDAO = new UserDAO(ds);
        List<String> usernames = userDAO.getAllUsernames(); // use this method

//        System.out.println("Existing usernames (from DB): " + usernames);

        req.setAttribute("existingUsernames", usernames);
        req.getRequestDispatcher("/jsp/signup.jsp").forward(req, resp);
        // I can't understand to fix this path issue
//        HTTP Status 404 – Not Found
//        Type Status Report
//        Message JSP file [/jsp/signup.jsp] not found
//        Description The origin server did not find a current representation for the target resource or is not willing to disclose that one exists.
    }
}
