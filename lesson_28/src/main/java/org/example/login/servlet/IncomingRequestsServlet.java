package org.example.login.servlet;

import org.example.login.model.User;
import org.example.login.service.UserRequestService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/incoming-requests")
public class IncomingRequestsServlet extends HttpServlet {

    public UserRequestService userRequestService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userRequestService = (UserRequestService) config.getServletContext().getAttribute("UserRequestService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("txt/html");
        HttpSession session = req.getSession();
        String username = session.getAttribute("username").toString();
        final List<User> requestedUsers = userRequestService.findUsersWithNotApprovedRequest(username);
        req.setAttribute("requestedUsers", requestedUsers);
        getServletContext().getRequestDispatcher("/incoming-requests.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String username = session.getAttribute("username").toString();
        String requestedUser = req.getParameter("requestedUser");
        userRequestService.approveRequest(username, requestedUser);
        resp.sendRedirect("incoming-requests");
    }
}
