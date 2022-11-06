package org.example.login.filter;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/incoming-requests")
public class IncomingRequestsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;

        final HttpSession session = req.getSession();
        if (session.getAttribute("isLoggedIn") != null) {
            final Boolean getIsLoggedInValue = (Boolean) req.getSession().getAttribute("isLoggedIn");
            if (getIsLoggedInValue) {
                filterChain.doFilter(servletRequest, servletResponse);
                servletRequest.getServletContext().getRequestDispatcher("/incoming-requests.jsp")
                        .forward(servletRequest, servletResponse);
            } else {
                res.sendRedirect("login");
            }
        }
    }
}
