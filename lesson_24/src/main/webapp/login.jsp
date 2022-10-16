<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel='stylesheet' type='text/css' href='style.css'>
</head>
<body>

<h2>Login Form</h2>

  <form action="login" method="post">
    <div class="container">
      <label><b>Username</b></label>
      username: <input type="text" placeholder="Enter Username" name="username" required>

      <label><b>Password</b></label>
      password: <input type="password" placeholder="Enter Password" name="password" required>

      <button type="submit">Login</button>
    </div>
  </form>

</body>
</html>
