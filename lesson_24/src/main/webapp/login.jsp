<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel='stylesheet' type='text/css' href='style.css'>
</head>
<body>

<h2>Login Form</h2>

  <form action="<%= request.getContextPath()%>/login" method="post">
    <div class="container">
      <label for="uname"><b>Username</b></label>
      Name: <input type="text" placeholder="Enter Username" name="username" required>

      <label for="psw"><b>Password</b></label>
      Password: <input type="password" placeholder="Enter Password" name="password" required>

      <button type="submit">Login</button>
    </div>
  </form>

</body>
</html>
