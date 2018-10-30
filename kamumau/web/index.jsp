<%-- 
    Document   : index
    Created on : Oct 5, 2018, 1:37:40 AM
    Author     : XXVII
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
    </head>
    <body>
        <jsp:forward page="/products?action=all" />
    </body>
</html>