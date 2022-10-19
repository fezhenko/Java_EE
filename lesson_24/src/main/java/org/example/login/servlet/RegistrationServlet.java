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

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private LoginService loginService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        loginService = (LoginService) config.getServletContext().getAttribute("LoginService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username,password);

        HttpSession session = req.getSession();
        if(session.getAttribute("isLoggedIn")==null) {
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req,resp);
        }
        else if(session.getAttribute("isLoggedIn")!=null && loginService.validateUser(user)){
            if((Boolean)session.getAttribute("isLoggedIn")){
                resp.sendRedirect("postLogin");
            }
            else {
                resp.sendRedirect("login");
            }
        }
        else {
            getServletContext().getRequestDispatcher("/registration.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(name, password);
        loginService.saveUsers(user);
        resp.sendRedirect("postLogin");
    }
}
