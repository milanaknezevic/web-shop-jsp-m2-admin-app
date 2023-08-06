<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.adminapp.models.Attribute" %>
<%@ page import="java.util.Objects" %>

<jsp:useBean id="categoryBean" type="com.example.adminapp.beans.CategoryBean" scope="session"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Update user</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="header.jsp" %>
<br>
<h1 style="text-align: center">Update category</h1>
<main role="main" class="container bg-white d-flex justify-content-center">
    <div class="card border-primary" style="width: 25rem;margin-top: 0.1rem">

        <div class="card-body">
            <div class="card-body">
                <form method="POST" action="?action=update-category&id=<%=categoryBean.getCategory().getId()%>">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" value="<%=categoryBean.getCategory().getNaziv()%>"
                               id="name" name="name" placeholder="Enter name" required>
                    </div>
                    <div class="form-group">
                        <label style="font-size: 22px;font-weight: bold">Attributes</label>
                    </div>
                    <% if (categoryBean.getCategory().getAtributi().isEmpty()) { %>
                    <p>No attributes for this category.</p>
                    <% } else { %>
                    <% for (Attribute attribute : categoryBean.getCategory().getAtributi()) { %>
                    <div class="form-group" style="display: inline-block;">
                        <label for="attributeName_<%=attribute.getId()%>">Name</label>
                        <input type="text" class="form-control" value="<%=attribute.getNaziv()%>"
                               id="attributeName_<%=attribute.getId()%>" name="attributeName_<%=attribute.getId()%>"
                               placeholder="Enter Attribute name" required>
                    </div>
                    <div class="form-group" style="display: inline-block;">
                        <label for="type_<%=attribute.getId()%>">Type</label>
                        <select class="form-control" id="type_<%=attribute.getId()%>" name="type_<%=attribute.getId()%>"
                                required>
                            <option value="0" <%=Objects.equals(attribute.getTip(), "STRING") ? "selected" : "" %>>
                                STRING
                            </option>
                            <option value="1" <%=Objects.equals(attribute.getTip(), "INT") ? "selected" : "" %>>INT
                            </option>
                            <option value="2" <%=Objects.equals(attribute.getTip(), "DOUBLE") ? "selected" : "" %>>
                                DOUBLE
                            </option>
                        </select>
                    </div>
                    <% }
                    } %>

                    <button type="submit" name="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</main>
</body>
</html>