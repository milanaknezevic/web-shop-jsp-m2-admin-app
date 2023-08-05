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

    <script>
        $(document).ready(function () {
            //Clone the hidden element and shows it
            $('.add-one').click(function () {
                $('.dynamic-element').first().clone().appendTo('.dynamic-stuff').show();
                attach_delete();
            });

            //Attach functionality to delete buttons
            function attach_delete() {
                $('.delete').off();
                $('.delete').click(function () {
                    console.log("click");
                    $(this).closest('.form-group').remove();
                });
            }
        });
    </script>

    <style>
    .add-one {
            color: green;
            text-align: center;
            font-weigth: bolder;
            cursor: pointer;
            margin-top: 10px;
        }

        .delete {
            color: white;
            background-color: rgb(231, 76, 60);
            text-align: center;
            margin-top: 6px;
            font-weight: 700;
            border-radius: 5px;
            min-width: 20px;
            cursor: pointer;
        }


        .dynamic-element {
            margin-bottom: 0px;
        }

         .center {
             margin: auto;
             width: 50%;
         }

    </style>
</head>
<body>
<%@include file="header.jsp" %>
<br>
<!-- HIDDEN DYNAMIC ELEMENT TO CLONE -->
<!-- you can replace it with any other elements -->
<div class="form-group dynamic-element" style="display:none">
    <div class="row d-flex align-items-center">
        <!-- Replace these fields -->
        <!-- Replace these fields -->
        <div class="col-md-5">
            <label for="atribute_name">Attribute name</label>
            <input type="text" class="form-control" id="atribute_name" name="attribute_name[]" required>
        </div>
        <div class="col-md-5">
            <label for="atribute_type">Attribute type</label>
            <input type="text" class="form-control" id="atribute_type" name="attribute_type[]" required>
        </div>
        <!-- End of fields -->

        <div class="col-md-2">
            <p style="margin-top: 40px;width: 30px;height: 30px" class="delete">x</p>
        </div>
        <!-- End of fields -->
    </div>
</div>


<!-- END OF HIDDEN ELEMENT -->


<div style="text-align: center"><h1>Add category</h1></div>
<div class="card border-primary mb-3 center" style="max-width: 50rem;">
    <div class="card-body">
        <div class="card-body">
            <form method="POST" action="?action=add-new-category">
                <fieldset>
                    <!-- Form Name -->

                    <div class="form-group">
                        <label for="name">Category name</label>
                        <input type="text" class="form-control" id="name" name="name"
                               required>
                    </div>

                    <div class="dynamic-stuff">
                        <!-- Dynamic element will be cloned here -->
                        <!-- You can call clone function once if you want it to show it a first element-->
                    </div>

                    <!-- Button -->
                    <div class="form-group">
                        <div class="row">
                            <div class="col-md-12">
                                <p class="add-one">+ Add attributes</p>
                            </div>
                            <div class="col-md-5"></div>
                            <div style="padding: 5px">
                                <button id="submit" type="submit" name="submit" class="btn btn-primary">Add category</button>

                            </div>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>
</body>
</html>