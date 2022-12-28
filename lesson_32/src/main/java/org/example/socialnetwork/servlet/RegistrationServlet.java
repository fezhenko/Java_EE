package org.example.socialnetwork.servlet;



import org.example.socialnetwork.model.User;
import org.example.socialnetwork.service.UserService;

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

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("UserService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        getServletContext().getRequestDispatcher("/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("username");
        String role = req.getParameter("role");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        if (!userService.validateUser(name, password)) {
            User user = new User(name, role, password);
            userService.saveUsers(user);
            session.setAttribute("isLoggedIn", true);
            session.setAttribute("username", name);
            resp.sendRedirect("users");
        } else {
            resp.sendRedirect("login");
        }
    }
}
