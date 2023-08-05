<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Header</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark" style="background: #1690A7">
    <a class="navbar-brand" href="?action=logs" style="margin-left: 10px;font-size: 24px">Admin application</a>
    <div class="d-flex flex-fill justify-content-center">
        <ul class="navbar-nav d-flex flex-wrap">
            <li class="nav-item">
                <a style="color: aliceblue" class="nav-link" href="?action=users">Users</a>
            </li>
            <li class="nav-item">
                <a style="color: aliceblue" class="nav-link" href="?action=logs">Logs</a>
            </li>
            <li class="nav-item">
                <a style="color: aliceblue" class="nav-link" href="?action=categories">Categories</a>
            </li>
        </ul>
    </div>
    <div class="ml-auto">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a style="color: aliceblue" class="nav-link" href="?action=sign-out">Sign out</a>
            </li>
        </ul>
    </div>
</nav>

<style>
    /* Promena boje teksta kada se stavi miš preko linka */
    .navbar-nav .nav-link:hover {
        color: #b9bed0 !important; /* Ovde postavite željenu boju za hover efekat */
    }
</style>

</body>
</html>
