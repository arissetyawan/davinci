<%-- 
    Document   : edit
    Created on : Oct 5, 2018, 3:03:47 AM
    Author     : XXVII
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Product</title>
        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="Resource/bootstrap.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <form method="POST" action='products?action=update' name="frmAddProduct" role="form"> 
                <div class="form-group">
                    <label for="name">
                        Name:<input class="form-control" type="text" id="name" name="name" value="<c:out value="${products.name}" />" />
                    </label>
                </div>
                <div class="form-group">
                    <label for="category_id">
                        Category ID: <input class="form-control" type="number" id="category_id" name="category_id" value=<c:out value="${products.category_id}" /> />
                    </label>
                </div>
                <div class="form-group">
                    <label for="price">
                        Price: <input class="form-control" type="text" name="price" value="<c:out value="${products.price}" />" /> 
                    </label>
                </div>
                <div class="form-group">
                    <label for="stock">
                        Stock: <input class="form-control" type="text" name="stock" value="<c:out value="${products.stock}" />" />
                    </label>
                </div>
                <input type="submit" value="Submit" class="btn btn-info" />
                <input type="button" value="Back" onclick="goBack()" class="btn btn-warning" />
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script>
            function goBack() {
                window.history.back();
            }
        </script>
    </body>
</html>
