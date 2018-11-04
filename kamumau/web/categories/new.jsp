<%-- 
    Document   : new
    Created on : Oct 15, 2018, 5:09:09 PM
    Author     : ASUS
--%>

<link href="/WEB-INF/stylesheets/jumbotron.css" rel="stylesheet">

<%@include file= "/layouts/header.jsp" %>

<main role="main">

<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron" style="padding-top: 20px;">
    <div class="container" >
      <center><h2>Add Category</h2>   </center>         
  <h4><c:out value='${message}' /></h4>

  <form action="/kamumau/categories?action=create" method="post"> 
  <%@include file= "form.jsp" %>
  </form>
  <hr>
</div>
</div> 
 <!-- /container --> 
</main>

    