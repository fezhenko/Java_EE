package org.example.login.filter;



import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@WebFilter("/*")
public class LoginFilter implements Filter {

    private Set<String> availableUrls;
    @Override
    public void init(FilterConfig filterConfig) {
        availableUrls = new HashSet<>();
        availableUrls.add("/login");
        availableUrls.add("/registration");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        String servletPath = req.getServletPath();
        if (availableUrls.contains(servletPath)) {
            chain.doFilter(request, response);
            return;
        }
        final HttpSession session = req.getSession();
        if (session.getAttribute("isLoggedIn") == null) {
            res.sendRedirect("login");
            return;
        }
        Boolean getIsLoggedInValue = (Boolean) req.getSession().getAttribute("isLoggedIn");
        if (!getIsLoggedInValue) {
            res.sendRedirect("login");
            return;
        }
        chain.doFilter(request, response);
    }
}
