package org.example.login.listener;

import org.example.login.repository.JdbcUserRepository;
import org.example.login.repository.UserRepository;
import org.example.login.service.LoginService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class DependencyInitializationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        final String dbDriver = "org.postgresql.Driver";
        final String username = sce.getServletContext().getInitParameter("db_user");
        final String password = sce.getServletContext().getInitParameter("db_password");
        final String dbUrl = sce.getServletContext().getInitParameter("db_url");

        try {
            Class.forName(dbDriver);
            final Connection con = DriverManager.getConnection(dbUrl, username, password);
            UserRepository repository = new JdbcUserRepository(con);
            LoginService loginService = new LoginService(repository);
            sce.getServletContext().setAttribute("LoginService", loginService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            final Connection connection = (Connection) sce.getServletContext().getAttribute("connection");
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}