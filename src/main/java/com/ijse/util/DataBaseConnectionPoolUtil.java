package com.ijse.util;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

@WebListener
public class DataBaseConnectionPoolUtil implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/cms");
        ds.setUsername("root");
        ds.setPassword("@317Kns20020317");

        ds.setInitialSize(10);
        ds.setMaxTotal(10);

        ServletContext sc = sce.getServletContext();
        sc.setAttribute("ds", ds); // Set to context
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            BasicDataSource ds = (BasicDataSource) sce.getServletContext().getAttribute("ds");
            ds.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
