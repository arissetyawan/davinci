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
    <div class="text-md-center"
        <% if (session.getAttribute("current_user") !=null) { %>
            <h1 class="h3 mb-3 font-weight-normal text-md-center">Welcome, see you again !</h1>
        <% out.println("Debugged Logged User: " + session.getAttribute("current_user")); %>
        <% } else { %> 
             <h1 class="h3 mb-3 font-weight-normal text-md-center">Failed to login or your account maybe deleted!</h1>
          <% } %>
    </div>
    <br />      
    <br />      
    <br />      
    <br />      
    <br />      
    <br />      
    <br />
    
<%@include file= "/layouts/footer.html" %>
