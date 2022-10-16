<%@ page import="org.example.login.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Users</title>
</head>
<body>
<table>
    <tr>
        <th>Users</th>
    </tr>
    <tbody>
    <%
        PrintWriter writer = response.getWriter();
        ((List<User>) request.getAttribute("users"))
                .stream()
                .forEach(user -> writer.println("<tr><td>" + user.getName() + "</td></tr>"));
    %>
    <% for (User user : (List<User>) request.getAttribute("users")) {%>
    <tr>
        <td>
            <%=user.getName()%>
        </td>
    </tr>
    <%} %>
    </tbody>
</table>
</body>
</html>