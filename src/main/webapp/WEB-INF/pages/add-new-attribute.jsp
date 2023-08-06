<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="categoryBean" type="com.example.adminapp.beans.CategoryBean" scope="session"/>

<!doctype html>
<html lang="en">
<head>
    <title>Add attribute</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
    <link rel="stylesheet" href="styles/addUser.css">

    <script>
        function addAtributeIntoCategory(categoryId) {
            var url = '?action=add-new-attribute&id=&categoryId=';
            location.href = url;
        }
    </script>

</head>
<body>
<%@include file="header.jsp" %>
<br>
<div class="pom"><h1>Add attribute for '<%=categoryBean.getCategory().getNaziv()%>'</h1></div>
<div class="card border-primary mb-3 center wid" >
    <div class="card-body">
        <div class="card-body">
            <form method="POST" action="?action=add-new-attribute&id=<%=categoryBean.getCategory().getId()%>">
                <div class="form-group">
                    <label for="name">Name</label>
                    <input type="text" class="form-control" id="name" name="name"
                           required>
                </div>
                <div class="form-group">
                    <label for="type">Type</label>
                    <select class="form-control" id="type" name="type"
                            required>
                        <option value="0">
                            STRING
                        </option>
                        <option value="1">INT
                        </option>
                        <option value="2">
                            DOUBLE
                        </option>
                    </select>
                </div>
                <div class="pad">
                    <button id="submitBtn" type="submit" name="submit" class="btn btn-primary"
                            onclick="addAtributeIntoCategory(<%=categoryBean.getCategory().getId()%>)">

                    Add category</button>

                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>