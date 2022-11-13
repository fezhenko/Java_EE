<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
    <div class="row" style="margin-top: 25px">
        <div class="card">
            <div class="card-body">
                <div class="card-title">
                    <h3>Registration Form</h3>
                </div>
                <div class="card-text">
                    <form action="registration" method="POST">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label" for="name">Name</label>
                            <div class="col-sm-7">
                                <input class="col-form-label form-control" id="name" name="name" placeholder="Name">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label" for="role">Role</label>
                            <div class="col-sm-7">
                                <input class="col-form-label form-control"
                                       id="role" name="role"
                                       placeholder="Role">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label" for="password">Password</label>
                            <div class="col-sm-7">
                                <input class="col-form-label form-control" type="password"
                                       id="password" name="password"
                                       placeholder="Password">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Registration</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
