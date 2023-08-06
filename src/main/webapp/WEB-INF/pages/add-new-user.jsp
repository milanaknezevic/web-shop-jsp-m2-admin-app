<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>


<!doctype html>
<html lang="en">
<head>
    <title>Add user</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
    <link rel="stylesheet" href="styles/addUser.css">


</head>
<body>
<%@include file="header.jsp" %>
<br>
<div class="pom"><h1>Add user</h1></div>
<div class="card border-primary mb-3 center wid" >
    <div class="card-body">
        <div class="card-body">
            <form method="POST" action="?action=add-new-user">
                <div class="form-group">
                    <label for="firstName">First name</label>
                    <input type="text" class="form-control" id="firstName" name="firstName"
                           required>
                </div>
                <div class="form-group">
                    <label for="lastName">Last name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" required>
                </div>
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password"
                           required>
                </div>
                <div class="form-group">
                    <label for="email">E-mail</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="city">City</label>
                    <input type="text" class="form-control" id="city" name="city" required>
                </div>
                <div class="form-group">
                    <label for="rola">Role</label>
                    <select class="form-control" id="rola" name="rola" required>
                        <option value="1">Customer suport</option>
                        <option value="2">Custom user</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="avatar">Avatar</label>
                    <input type="file" class="form-control" id="avatar" name="avatar" data-validation="mime size"
                           data-validation-allowing="jpg, png, gif" data-validation-max-size="10M">
                </div>
                <div class="pad">
                    <button id="submitBtn" type="submit" name="submit" class="btn btn-primary">Add user</button>

                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>