<%@ page import="com.example.adminapp.models.User" %>
<%@ page import="com.example.adminapp.models.enums.Role" %>
<jsp:useBean id="userBean" type="com.example.adminapp.beans.UserBean" scope="session"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="styles/users.css">


    <title>Users</title>
    <script>
        $(document).ready(function () {
            $('#myTable').DataTable(); // myTable je ID vaše tabele
        });
    </script>


</head>
<body class="body">

<%@include file="header.jsp" %>
<div class="container-lg" style="padding-top: 20px">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
                        <h2>Users</h2>
                    </div>
                </div>
            </div>

            <div style="margin-bottom: 10px">
                <button type="button" class="mjau" onclick="location.href='?action=add-new-user'">
                    <span class="fa fa-plus"></span>
                    Add new user
                </button>
            </div>
            <table id="myTable" class="table table-striped">
                <thead>
                <tr>
                    <th style="width:5%;color:  #1690A7" scope="col">Id</th>
                    <th style="width: 15%; color:  #1690A7" scope="col">First name</th>
                    <th style="width: 15%; color:  #1690A7" scope="col">Last name</th>
                    <th style="width: 25%; color:  #1690A7" scope="col">Username</th>
                    <th style="width: 25%; color: #1690A7" scope="col">E-mail</th>
                    <th style="width: 15%;color:  #1690A7" scope="col">City</th>
                    <th style="color:  #1690A7;width: 25%;" scope="col">Role</th>

                    <th style="width: 10%;color:  #1690A7" scope="col">Status</th>
                    <th style="width: 15%;color:  #1690A7" scope="col">Actions</th>

                </tr>
                </thead>
                <tbody>
                <% for (User user : userBean.getAll()) {%>
                <tr>
                    <td style="word-wrap: break-word">
                        <%= user.getId() %>

                    </td>
                    <td style="word-wrap: break-word">
                        <%= user.getIme() %>
                    </td>
                    <td style="word-wrap: break-word">
                        <%=user.getPrezime() %>
                    </td>
                    <td style="word-wrap: break-word">
                        <%= user.getKorisnicko_ime() %>
                    </td>
                    <td style="word-wrap: break-word">
                        <%=user.getEmail() %>
                    </td style="word-wrap: break-word">
                    <td>
                        <%=user.getGrad()%>
                    </td>
                    <td style="word-wrap: break-word">
                        <% if (user.getRole() == Role.ADMIN) { %>
                        <p>Admin</p>
                        <% } else if (user.getRole() == Role.KORISNICKA_PODRSKA) { %>
                        <p>Customer support</p>
                        <% } else { %>
                        <p>User</p>
                        <% } %>
                    </td>

                    <td style="word-wrap: break-word">
                        <%= user.getStatus() %>
                    </td>

                    <td>
                        <div class="d-flex flex-row">

                            <button style="width: fit-content" type="button" class="btn" title="Edit"
                                    onclick="location.href='?action=update-user&id=<%=user.getId()%>'">
                                <span style="width: fit-content" class="fa fa-pencil text-dark"></span>
                            </button>


                            <button style="width: fit-content" type="button" class="btn" title="Delete"
                                    onclick="location.href='?action=delete-user&id=<%=user.getId()%>'">
                                <span style="width: fit-content" class="fa fa-trash text-danger"></span>
                            </button>

                        </div>
                    </td>

                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>