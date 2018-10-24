<%-- 
    Document   : add_user
    Created on : Sep 27, 2018, 3:42:41 PM
    Author     : arissetyawan.email@gmail.com
--%>

<%@page language= "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <head><title>People MVC JSP Bean Application</title></head>

    <body>
        <h1>Editing Person | <a href="categories?action=list">List of Category</a></h1>
            <h4><c:out value='${message}' /></h4>
        <form action="/kamumau/categories?action=update&id=<c:out value='${categories.getId()}' />" method="post"> 
        <%@include file= "form.html" %>
        </form>
    </body>
</html>
