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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
    <title>Product List</title>
    <script type="text/javascript">
        function ChangeColor(tableRow, highLight) {
            if (highLight) {
                tableRow.style.backgroundColor = '#B2EBF2';
            }else{
                tableRow.style.backgroundColor = 'white';
            }
            }
            function DoNav(theUrl){
            document.location.href = theUrl;
        }
    </script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"  crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"  crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"   crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
    </head>
    <body>
        <div>
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
          <a class="navbar-brand" href="#">KamuMau</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item active">
                <a class="nav-link" href="#">Home</a>
              </li>
              <li class="nav-item dropdown active">
                <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Categories</a>
                <div class="dropdown-menu" aria-labelledby="dropdown01">
                    <c:forEach items="${categories}" var="categories">
                             <a class="dropdown-item" href="#"><c:out value="${categories.name}"/></a>
                    </c:forEach>
                </div>
              </li>
              <li class="nav-item active">
                <a class="nav-link" href="#">Products</a>
              </li>
              <li class="nav-item active">
                <a class="nav-link" href="#">Sign In</a>
              </li>
              <li class="nav-item active">
                <a class="nav-link" href="#">Sign Up</a>
              </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
              <input class="form-control mr-sm-2" type="text" placeholder="Search Products..." aria-label="Search Products...">
              <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
          </div>
        </nav>
        </div>
        <div style="padding-left: 5%; margin-top: 5%">
        <h2>
            Edit Product
        </h2>
        <p>Edit product data</p>
        </div>
        <div class="container" style="margin-top: 1%">
            <form method="POST" action='products?action=update' name="frmAddProduct" role="form"> 
                <div class="form-group">
                    
                        <input class="form-control" type="hidden" id="product_id" name="product_id" value="<c:out value="${products.product_id}" />" />
                </div>
                <div class="form-group">
                    <label for="name">
                        Name:<input class="form-control" type="text" id="name" name="name" value="<c:out value="${products.name}" />" />
                    </label>
                </div>
                <div class="form-group">
                    <label for="category_id">
                        Category :
                        <select class="form-control" name="category_id">  
                        <c:forEach items="${categories}" var="categories">
                            <option value="<c:out value="${categories.category_id}"/>"><c:out value="${categories.name}"/></option>
                        </c:forEach>            
                        </select>    
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
                <input type="submit" value="Submit" class="btn btn-primary" />
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
