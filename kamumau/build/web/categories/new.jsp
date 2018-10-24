<%-- 
    Document   : new
    Created on : Oct 15, 2018, 5:09:09 PM
    Author     : ASUS
--%>

<link href="/WEB-INF/stylesheets/jumbotron.css" rel="stylesheet">

<%@include file= "/layouts/header.jsp" %>

<main role="main">

<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
  <div class="container">

  <h1>Adding Category</h1>            

  <h4><c:out value='${message}' /></h4>

  <form action="/kamumau/categories?action=create" method="post"> 
  <%@include file= "form.html" %>
  </form>

 
  

  <hr>

</div>
</div> 

 <!-- /container --> 

</main>

    