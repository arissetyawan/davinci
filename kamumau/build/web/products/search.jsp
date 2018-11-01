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
<hr>
<div style="margin-top:3%; " class="container">    
    <div class="card-columns">       
        <!-- Example row of columns -->
        <c:forEach items="${products}" var="product">
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <form action="transactions?action=create" method="POST">
            <input class="form-control" type="hidden" id="id_product" name="id_product" value="<c:out value="${product.product_id}" />" />
            <input class="form-control" type="hidden" id="id_seller" name="id_seller" value="<c:out value="${product.owner}" />" />
            <input class="form-control" type="hidden" id="qty" name="qty" value="1" />
            <h4 align="center" class="card-title"><c:out value="${product.name}"/></h4>
            <h5 class="card-title"><c:out value="${product.category_id}"/></h5>
            <p class="card-text">Rp <c:out value="${product.price}"/></p>
            <input type="submit" value="Buy" style="float: right; margin-bottom: 10%" class="btn btn-primary" />
            </form>
        </div>
    </div>
            </c:forEach> 
    </div>
  <hr>
</div> <!-- /container -->
 
</main>

<%@include file= "/layouts/footer.html" %>