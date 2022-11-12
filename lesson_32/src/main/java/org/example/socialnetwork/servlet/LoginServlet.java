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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();
        if (userService.validateUser(name, password)) {
            final User getUser = userService.getUser(name, password);
            session.setAttribute("userId", getUser.getUserId());
            session.setAttribute("isLoggedIn", true);
            resp.sendRedirect("users");
        } else {
            session.setAttribute("isLoggedIn", false);
            resp.sendRedirect("registration");
        }
    }
}
