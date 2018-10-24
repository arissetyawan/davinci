<%-- 
    Document   : edit
    Created on : Oct 10, 2018, 12:32:29 PM
    Author     : Prieyuda Akadita S
--%>

<%@page language= "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head><title>People MVC JSP Bean Application</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js">
  
  </head>
  

    <body>
        <div style="padding: 48px; align-content: center" >
        <h1>Editing Transaction | <a href="orders?action=lists">View order</a></h1>
        <h4><c:out value='${message}' /></h4>
        <form action="/kamumau/transactions?action=update&id=<c:out value='${transaction.getId()}' />&id_order=<c:out value='${transaction.getId_order()}' />" method="post"> 
        <%@include file= "editform.html" %>
        </form>
        </div>
    </body>
</html>
