<%-- 
    Document   : product_list
    Created on : Oct 4, 2018, 10:51:55 PM
    Author     : XXVII
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="en">
<head>
		
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">
<title>Products List</title>
<script type="text/javascript">
        function ChangeColor(tableRow, highLight) {
                if (highLight) {
                    tableRow.style.backgroundColor = '#B2EBF2';
                }else {
                    tableRow.style.backgroundColor = 'white';
                }
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
            <a class="nav-link" href="products?action=all">Home</a>
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
    <div>
        <div style="padding-left: 15px; margin-top: 4%">
        <h2>
            List of Products
        </h2>
        <p>List all products that in stock and products that have zero stock</p>
    </div>
    <hr>
    <ul class="nav nav-tabs" id="myTab" role="tablist" style="margin-top: 2%">
        <li class="nav-item">
            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">My Products</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" id="zero-tab" data-toggle="tab" href="#zero" role="tab" aria-controls="zero" aria-selected="false">Out of Stock</a>
        </li>
    </ul>
    </div>
    <div class="tab-content">
        <div class="tab-pane active" id="home" role="tabpanel" aria-labelledby="home-tab">
        <table class="table">
            <thead>
                        <tr>
                            <th>
                                No.
                            </th>
                            <th>
                                Name
                            </th>
                            <th>
                                Category
                            </th>
                            <th>
                                Price
                            </th>
                            <th>
                                Stock
                            </th>
                            <th>
                                Updated At
                            </th>
                            <th colspan="2">
                                Action
                            </th>
                        </tr>
                    </thead>
            <tbody>
            <% int no =1; %>
                        <c:forEach items="${products}" var="products">
                            
                            <tr onmouseover="ChangeColor(this, true);"
                              onmouseout="ChangeColor(this, false);">
                                <td>
                                    <% out.print(no); %>
                                </td>
                                <td>
                                    <c:out value="${products.name}"/>
                                </td>
                                <td>
                                    <c:out value="${products.category_id}"/>
                                </td>
                                <td>
                                    Rp <c:out value="${products.price}"/>
                                </td>
                                <td>
                                    <c:out value="${products.stock}"/> Pcs
                                </td>
                                <td>
                                    <c:out value="${products.updated_at}"/>
                                </td>
                                    <td>
                                        <a href="products?action=edit&id=<c:out value="${products.product_id}"/>" class="btn btn-outline-info btn-sm">Edit</a>
                                        <a onclick="return confirm('Are you sure ?')" href="products?action=delete&id=<c:out value="${products.product_id}"/>" class="btn btn-outline-danger btn-sm">Delete</a>
                                    </td>
                                </tr>
                                <% no++; %>
                            </c:forEach>     
            </tbody>
        </table>
    </div>
    <div class="tab-pane" id="zero" role="tabpanel" aria-labelledby="zero-tab">
        <table class="table">
            <thead>
                <tr>
                            <th>
                                No.
                            </th>
                            <th>
                                Name
                            </th>
                            <th>
                                Category
                            </th>
                            <th>
                                Price
                            </th>
                            <th>
                                Stock
                            </th>
                            <th>
                                Updated At
                            </th>
                            <th colspan="2">
                                Action
                            </th>
                </tr>
            </thead>
            <tbody>
            <% int n =1; %>
                        <c:forEach items="${productsZero}" var="pz">
                            
                            <tr onmouseover="ChangeColor(this, true);"
                              onmouseout="ChangeColor(this, false);">
                                <td>
                                    <% out.print(n); %>
                                </td>
                                <td>
                                    <c:out value="${pz.name}"/>
                                </td>
                                <td>
                                    <c:out value="${pz.category_id}"/>
                                </td>
                                <td>
                                    Rp <c:out value="${pz.price}"/>
                                </td>
                                <td>
                                    <c:out value="${pz.stock}"/> Pcs
                                </td>
                                <td>
                                    <c:out value="${pz.updated_at}"/>
                                </td>
                                    <td>
                                        <a href="products?action=edit&id=<c:out value="${pz.product_id}"/>" class="btn btn-outline-info btn-sm">Edit</a>
                                        <a onclick="return confirm('Are you sure ?')" href="products?action=delete&id=<c:out value="${pz.product_id}"/>" class="btn btn-outline-danger btn-sm">Delete</a>
                                    </td>
                                </tr>
                                <% n++; %>
                            </c:forEach>   
            </tbody>
        </table>
    </div>
</div>
<div>
    <a href="products?action=new" role="button" class="btn btn-primary btn-lg" style="margin-left: 3%">Add New Product</a>
</div>
<hr>
<%@include file= "/layouts/footer.html" %>