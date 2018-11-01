<%-- 
    Document   : edit
    Created on : Oct 10, 2018, 12:32:29 PM
    Author     : Prieyuda Akadita S
--%>

<%@include file="../layouts/header.jsp" %>
<%@page language= "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head>
      <title>Edit quantity</title>
  </head>
  

    <body>
        <div style="padding: 5%; align-content: center" >
        <h2>Edit quantity of <c:out value='${product.getName()}' /></h2>
        <h4><c:out value='${message}' /></h4>
        <form action="/kamumau/transactions?action=update&id=<c:out value='${transaction.getId()}' />&id_order=<c:out value='${transaction.getId_order()}' />" method="post"> 
        <%@include file= "editform.html" %>
        </form>
        </div>
    </body>
</html>
