<%@ page import="com.example.adminapp.models.User" %>
<%@ page import="com.example.adminapp.beans.UserBean" %>
<%@ page import="com.example.adminapp.models.enums.Role" %>
<%@ page import="com.example.adminapp.models.enums.Status" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="userBean" type="com.example.adminapp.beans.UserBean" scope="session"/>
<!doctype html>
<html lang="en">
<head>
    <title>Update user</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>


    <style>
        .center {
            margin: auto;
            width: 50%;
        }
    </style>
</head>
<body>
<%@include file="header.jsp" %>
<br>
<br>
<div style="text-align: center"><h1>Update user</h1></div>
<div class="card border-primary mb-3 center" style="max-width: 50rem;">
    <div class="card-body">
        <div class="card-body">
            <form class="form-add-user" method="POST" action="?action=update-user&id=<%=userBean.getUser().getId()%>">
                <div class="form-group">
                    <label for="firstName">First name</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First name"
                           required value="<%=userBean.getUser().getIme()%>">
                </div>
                <div class="form-group">
                    <label for="lastName">Last name</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last name"
                           required
                           value="<%=userBean.getUser().getPrezime()%>">
                </div>
                <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Username"
                           required
                           value="<%=userBean.getUser().getKorisnicko_ime()%>">
                </div>

                <div class="form-group">
                    <label for="email">E-mail</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="E-mail" required
                           value="<%=userBean.getUser().getEmail()%>">
                </div>

                <div class="form-group">
                    <label for="city">City</label>
                    <input type="text" class="form-control" id="city" name="city" placeholder="City" required
                           value="<%=userBean.getUser().getGrad()%>">
                </div>
                <div class="form-group">
                    <label for="city">Role</label>
                    <select class="form-control" id="rola" name="rola" required>
                        <option value="0" <%= userBean.getUser().getRole() == Role.ADMIN ? "selected" : "" %>>Admin</option>
                        <option value="1" <%= userBean.getUser().getRole() == Role.KORISNICKA_PODRSKA ? "selected" : "" %>>Customer suport</option>
                        <option value="2" <%= userBean.getUser().getRole() == Role.OBICNI_KORISNIK ? "selected" : "" %>>Custom user</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="city">Status</label>
                    <select class="form-control" id="status" name="status" required>
                        <option value="0" <%= userBean.getUser().getStatus() == Status.REQUESTED ? "selected" : "" %>>Requested</option>
                        <option value="1" <%= userBean.getUser().getStatus() == Status.ACTIVE ? "selected" : "" %>>Active</option>
                        <option value="2" <%= userBean.getUser().getStatus() == Status.BLOCKED ? "selected" : "" %>>Blocked</option>
                    </select>
                </div>
                <div class="form-group" style="padding-bottom: 5px">
                    <label for="avatar">Avatar</label>
                    <input type="file" class="form-control" id="avatar" name="avatar" data-validation="mime size"
                           data-validation-allowing="jpg, png, gif" data-validation-max-size="10M"
                           value="<%=userBean.getUser().getAvatar()%>">
                </div>
                <button type="submit" name="submit" class="btn btn-primary">Update user</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>