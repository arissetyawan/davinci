<%-- 
    Document   : add_user
    Created on : Sep 27, 2018, 3:42:41 PM
    Author     : arissetyawan.email@gmail.com
--%>

    <%@page language= "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF8" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <html lang="en" class="jumbotron">
        <head>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <meta name="description" content="">
            <meta name="author" content="aris">
            <link rel="icon" href="favicon.ico">
            <title>KamuMau - sell or buy as you want</title>
                <!-- Bootstrap core CSS -->
            <link href="/WEB-INF/stylesheets/jumbotron.css" rel="stylesheet">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" crossorigin="anonymous">
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" crossorigin="anonymous"></script>
            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"  crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"  crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"   crossorigin="anonymous"></script>
        </head>
        <body class="jumbotron" style="padding-top: 5px;">
        <center><h3>Editing Person | <a href="categories?action=list">List of Category</a></h3></center>
                <h4><c:out value='${message}' /></h4>
            <form action="/kamumau/categories?action=update&id=<c:out value='${categories.getId()}' />" method="post"> 
            <%@include file= "form.html" %>
            </form>
        </body>
    </html>
