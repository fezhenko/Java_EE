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
                        <form action="friendRequests" method="post">
                            <table class="table">
                                <tr>
                                    <th>User ID</th>
                                    <th>User</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                <tbody>
                                <c:forEach items="${requestedUsers}" var="requestedUser">
                                    <input type="hidden" id="requestedUserId" name="requestedUserId" value="${requestedUser.userId}">
                                    <tr>
                                        <td>
                                            <c:out value="${requestedUser.userId}"/>
                                        </td>
                                        <td>
                                            <c:out value="${requestedUser.name}"/>
                                        </td>
                                        <td>
                                            <div class="form-group row">
                                                <button type="submit" class="btn btn-success">Approve</button>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group row">
                                                <button type="submit" class="btn btn-secondary">Decline</button>
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