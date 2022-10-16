package org.example.login.servlet;

import org.example.login.model.User;
import org.example.login.service.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

    private LoginService loginService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        loginService = (LoginService) config.getServletContext().getAttribute("LoginService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        final HttpSession session = req.getSession();
        session.getAttribute("username");
        session.getAttribute("password");

        getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User(name, password);
        if(loginService.validateUser(user)){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/postLogin.jsp");
            requestDispatcher.forward(req, resp);
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/registration.jsp");
        requestDispatcher.forward(req, resp);
    }
}
