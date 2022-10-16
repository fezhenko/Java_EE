<%@ page import="org.example.login.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <body>
        <table>
            <tbody>
                    <% PrintWriter writer = response.getWriter();
                    writer.println("SUCCESS");
                    %>
<%--                    <%--%>
<%--                        PrintWriter writer = response.getWriter();--%>
<%--                        ((List<User>) request.getAttribute("users"))--%>
<%--                                .forEach(user -> writer.println("<tr><td>" + user.getName() + ""--%>
<%--                                        + user.getPassword() + "</td></tr>"));--%>
<%--                    %>--%>
            </tbody>
        </table>
    </body>
</html>