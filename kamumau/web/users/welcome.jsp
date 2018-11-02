<%-- 
    Document   : list
    Created on : Oct 11, 2018, 8:27:04 PM
    Author     : x201
--%>
<link href="/WEB-INF/stylesheets/login.css" rel="stylesheet">
<link href="/WEB-INF/stylesheets/jumbotron.css" rel="stylesheet">

<%@include file= "/layouts/header.jsp" %>
    <br />      
    <br />      
    <br />      
    <br />      
    <br />      
    <br />      
    <br />      
    <br />      
    <br />      
    <br />      
    <br />      
    <br />      
    <br />      
    <h1 class="h3 mb-3 font-weight-normal">Welcome, see you again !</h1>
    <% out.println("Debugged Logged User: " + session.getAttribute("current_user")); %>
    
<%@include file= "/layouts/footer.html" %>
