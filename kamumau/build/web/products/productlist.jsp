<%-- 
    Document   : product_list
    Created on : Oct 4, 2018, 10:51:55 PM
    Author     : XXVII
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<%@include file= "/layouts/header.jsp" %> 
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
                                <td style="color: red">
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