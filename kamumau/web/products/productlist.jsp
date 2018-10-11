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
<title>Product List</title>


<link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css" />
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>

</head>
	<body>
        <br>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
              <div class="navbar-header">
                <a class="navbar-brand" href="#">ApalahSHOP</a>
              </div>
              <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#">Page 1</a></li>
                <li><a href="#">Page 2</a></li>
                <li><a href="#">Page 3</a></li>
              </ul>
            </div>
        </nav>
        
	<div class="wrap">
	<section>
            <div class="container">
                <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>
                                No
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
                    <tfoot>
                        <tr>
                            <th align="center">
                                No
                            </th>
                            <th align="center">
                                Name
                            </th>
                            <th align="center">
                                Category
                            </th>
                            <th align="center">
                                Price
                            </th>
                            <th align="center">
                                Stock
                            </th>
                            <th align="center">
                                Updated At
                            </th>
                            <th colspan="2" align="center">
                                Action
                            </th>
                        </tr>
                    </tfoot>
                    
                       <% int no =1; %>
                        <c:forEach items="${products}" var="products">
                            
                            <tr>
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
                                    <c:out value="${products.price}"/>
                                </td>
                                <td>
                                    <c:out value="${products.stock}"/>
                                </td>
                                <td>
                                    <c:out value="${products.updated_at}"/>
                                </td>
                                    <td align="center"><a href="products?action=edit&id=<c:out value="${products.product_id}"/>">Edit</a></td>
                                    <td align="center"><a href="products?action=delete&id=<c:out value="${products.product_id}"/>">Delete</a></td>
                                </tr>
                                <% no++; %>
                            </c:forEach>
                                
                </table>
                <div>
                <a href="products?action=new" role="button" class="btn btn-primary btn-lg" data-toggle="modal">Add New Product</a>
                </div>
            </div>
	</section>	
</div>

<script>
    $(document).ready(function() {
        $('#example').DataTable();
    });
</script>
</body>
</html>
