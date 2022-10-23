package org.example.login.servlet;

import org.example.login.model.User;
import org.example.login.service.LoginService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private LoginService loginService;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        loginService = (LoginService) config.getServletContext().getAttribute("LoginService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final List<User> users = loginService.findUsers();
        req.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/users.jsp").forward(req,resp);
    }
}
