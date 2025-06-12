package com.ijse.controller;

import com.ijse.dao.UserDAO;
import com.ijse.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // Get the DataSource from ServletContext
        ServletContext context = getServletContext();
        javax.sql.DataSource ds = (javax.sql.DataSource) context.getAttribute("ds");

        UserDAO userDAO = new UserDAO(ds);
        User user = userDAO.validateUser(username, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            if ("ADMIN".equalsIgnoreCase(user.getJobRole())) {
                resp.sendRedirect("admin/dashboard.jsp");
            } else {
                resp.sendRedirect("employee/dashboard.jsp");
            }
        } else {
            req.setAttribute("error", "Invalid username or password");
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
