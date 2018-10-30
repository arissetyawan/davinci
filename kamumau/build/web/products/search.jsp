<%-- 
    Document   : search
    Created on : Oct 27, 2018, 10:42:09 AM
    Author     : ninelota
--%>

<%-- 
    Document   : list
    Created on : Oct 11, 2018, 8:27:04 PM
    Author     : x201
--%>



<%@include file= "/layouts/header.jsp" %>

<main role="main">

<!-- Main jumbotron for a primary marketing message or call to action -->

<div style="margin-top: 5%;" class="container">
    <h1 class="">Search Result  :</h1>
</div>
<c:forEach items="${products}" var="product">
<div style="margin-top:3%; " class="container">    
    <div class="card-columns">       
        <!-- Example row of columns -->
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <form method="POST" action="transactions?action=new">
            <h4 align="center" class="card-title"><c:out value="${product.name}"/></h4>
            <h5 class="card-title"><c:out value="${product.category_id}"/></h5>
            <p class="card-text">Rp <c:out value="${product.price}"/></p>
            <input type="submit" value="Buy" class="btn btn-primary" />
            </form>
        </div>
    </div>
    </div>
  <hr>
</div> <!-- /container -->
 </c:forEach> 
</main>

<%@include file= "/layouts/footer.html" %>