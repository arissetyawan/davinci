<%-- 
    Document   : search
    Created on : Oct 6, 2018, 2:54:17 PM
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
<title>Product List</title>

<link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
<link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link href="bootstrap.css" rel="stylesheet">
</head>
	<body>
	<div class="wrap">
	<section>
            <div class="container">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>
                                Product ID
                            </th>
                            <th>
                                Name
                            </th>
                            <th>
                                Category ID
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
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${products}" var="products">
                            <tr>
                                <td>
                                    <c:out value="${products.product_id}"/>
                                </td>
                                <td>
                                    <c:out value="${products.name}"/>
                                </td>
                                <td>
                                    <c:out value="${products.category_id}"/>
                                </td>
                                <td>
                                    <c:out value="${products.price}"/>
                                </td>
                                <td>
                                    <c:out value="${products.stock}"/>
                                </td>
                                <td>
                                    <c:out value="${products.updated_at}"/>
                                </td>
                                    <td><a href="products?action=edit&product_id=<c:out value="${products.product_id}"/>">Update</a></td>
                                    <td><a href="products?action=delete&product_id=<c:out value="${products.product_id}"/>">Delete</a></td>
                                </tr>
                            </c:forEach>
                    </tbody>
                </table>
                <a href="products?action=new" role="button" class="btn btn-info btn-lg" data-toggle="modal">Add New Product</a>	
            </div>
	</section>	
</div>

<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>

</body>
</html>
