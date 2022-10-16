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
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

    private LoginService loginService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        loginService = (LoginService) config.getServletContext().getAttribute("loginService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        getServletContext().getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String name = req.getParameter("name");
    String password = req.getParameter("password");
    User user = new User(name, password);

        if(loginService.validateUser(user)){
            try(PrintWriter printWriter = resp.getWriter()){
                printWriter.write("user exist in DB, redirected...");
                getServletContext().getRequestDispatcher("/postLogin.jsp").forward(req,resp);
            }
            catch (IOException | ServletException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            loginService.saveUsers(user);
            try(PrintWriter printWriter = resp.getWriter()){
                printWriter.write("user is saved to DB...");
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
