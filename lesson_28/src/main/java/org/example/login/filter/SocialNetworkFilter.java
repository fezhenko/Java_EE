package org.example.login.filter;


import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


@WebFilter("/*")
public class SocialNetworkFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final Set<String> filteredUrls = new HashSet<>();
        filteredUrls.add("/users");
        filteredUrls.add("/incoming-requests");
        String servletPath = req.getServletPath();

        if (filteredUrls.contains(servletPath)) {
            final HttpSession session = req.getSession();
            if (session.getAttribute("isLoggedIn") != null) {
                final Boolean getIsLoggedInValue = (Boolean) req.getSession().getAttribute("isLoggedIn");
                if (getIsLoggedInValue) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect("login");
                }
            } else {
                res.sendRedirect("registration");
            }
            return;
        }
        chain.doFilter(request, response);
    }
}
