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
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("txt/html");
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");
        final List<User> requestedUsers = userRequestService.findUsersWithNotApprovedRequest(userId);
        req.setAttribute("requestedUsers", requestedUsers);
        getServletContext().getRequestDispatcher("/incoming-requests.jsp").forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");
        Long requestedUserId = Long.valueOf(req.getParameter("requestedUserId"));
        userRequestService.approveRequest(requestedUserId, userId);
        getServletContext().getRequestDispatcher("/incoming-requests.jsp").forward(req, res);
    }
}
