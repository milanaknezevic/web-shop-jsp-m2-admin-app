<%@ page import="com.example.adminapp.models.User" %>
<%@ page import="com.example.adminapp.beans.UserBean" %>
<%@ page import="com.example.adminapp.dao.UserDAO" %>
<%@ page import="com.example.adminapp.models.Log" %>
<%@ page import="com.example.adminapp.dao.LogDAO" %>

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


    <title>Logs</title>
    <script>
        $(document).ready(function() {
            $('#myTable').DataTable(); // myTable je ID vaše tabele
        });
    </script>



</head>
<body class="body">

<%@include file="header.jsp" %>
<div class="container-lg" style="padding-top: 20px">
    <div class="table-responsive">
        <div class="table-wrapper">
            <div  class="table-title">
                <div  class="row">
                    <div  class="col-sm-6">
                        <h2>Logs</h2>
                    </div>
                </div>
            </div>

            <table id="myTable" class="table table-striped">
                <thead>
                <tr>
                    <th style="color:  #1690A7" scope="col">Id</th>
                    <th style="color:  #1690A7;width: fit-content" scope="col">Message</th>
                    <th style="color:  #1690A7;width: fit-content" scope="col">Level</th>
                    <th style="color:  #1690A7;width: fit-content" scope="col">Log</th>
                    <th style="color:  #1690A7" scope="col">Date</th>


                </tr>
                </thead>
                <tbody>
                <% for (Log log : LogDAO.getAllLogs()) {%>
                <tr>
                    <td>
                        <%= log.getId() %>

                    </td>
                    <td style="word-wrap: break-word">
                        <%= log.getPoruka() %>
                    </td>
                    <td>
                        <%=log.getLevel() %>
                    </td>
                    <td style="word-wrap: break-word">
                        <%=log.getLog() %>
                    </td>
                    <td>
                        <%=log.getDatum() %>
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