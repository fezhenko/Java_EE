<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Friend requests</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" />
</head>
<body>
    <div class="container">
        <div class="row" style="margin-top: 25px">
            <div class="card">
                <div class="card-body">
                    <div class="card-title">
                        <h3>Friend requests</h3>
                    </div>
                    <div class="card-text">
                        <form action="incoming-requests" method="post">
                        <table class="table">
                            <tr>
                                <th>User</th>
                                <th>Action</th>
                            </tr>
                            <tbody>
                            <c:forEach items="${requestedUsers}" var="requestedUser">
                                <tr>
                                    <td>
                                        <c:out value="${requestedUser.name}" />
                                    </td>
                                    <td>
                                        <div class="btn-group" role="group" aria-label="Basic example">
                                            <button type="approve" class="btn btn-secondary">Approve</button>
                                            <button type="decline" class="btn btn-secondary">Decline</button>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${param.error != null }">
            <div class="row" style="margin-top: 25px">
                <div class="alert alert-danger" role="alert">
                        ${param.error}
                </div>
            </div>
        </c:if>
    </div>
</body>
</html>