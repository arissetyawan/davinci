<%-- 
    Document   : new
    Created on : Oct 10, 2018, 1:09:28 PM
    Author     : Prieyuda Akadita S
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js">
    </head>
    <body>
      <div style="padding: 48px; align-content: center" >
        <h1>Adding Transaction</h1>
        <p>If you have open order, you item will be added on available shopping bucket</p>
        <form action="/kamumau/transactions?action=create" method="post"> 
        <%@include file= "form.html" %>
        </form>
      </div>

    </body>
</html>
