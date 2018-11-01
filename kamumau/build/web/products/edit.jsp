<%-- 
    Document   : edit
    Created on : Oct 5, 2018, 3:03:47 AM
    Author     : XXVII
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<%@include file= "/layouts/header.jsp" %>
        <div style="padding-left: 5%; margin-top: 5%">
        <h2>
            Edit Product
        </h2>
        <p>Edit a product data</p>
        </div>
        <hr>
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
        <hr>
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
        <script>
            function goBack() {
                window.history.back();
            }
        </script>
   <%@include file= "/layouts/footer.html" %>
