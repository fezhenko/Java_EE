package org.example.login.servlet;

import org.example.login.service.UserRequestService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OutcomingRequestsServlet extends HttpServlet {

    public UserRequestService userRequestService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userRequestService = (UserRequestService) config.getServletContext().getAttribute("UserRequestService");
    }
}
