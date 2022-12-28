<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='stylesheet' type='text/css' href='style.css'>
    <title>Registration</title>
</head>
<body>

<h2>Registration Form</h2>

<form action="registration" method="post">
    <div class="container">
        <label><b>Username</b></label>
        <label>
            <input type="text" placeholder="Enter Username" name="username" required>
        </label>

        <label><b>Role</b></label>
        <label>
            <input type="text" placeholder="Enter Role" name="role" required>
        </label>

        <label><b>Password</b></label>
        <label>
            <input type="password" placeholder="Enter Password" name="password" required>
        </label>

        <button type="submit">Register</button>
    </div>
</form>

</body>
</html>
