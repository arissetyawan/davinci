<%-- 
    Document   : navbar_not_logged_in.jsp
    Created on : Oct 11, 2018, 7:35:36 PM
    Author     : x201
--%>

<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
  <a class="navbar-brand" href="#">KamuMau</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="products?action=all">Home</a>
      </li>
       <li class="nav-item dropdown active">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Categories</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <c:forEach items="${categories}" var="categories">
              <a class="dropdown-item" href="products?action=search-by-category&id=<c:out value="${categories.category_id}"/>"><c:out value="${categories.name}"/></a>
          </c:forEach> 
        </div>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="products?action=list">Products</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#">Sign In</a>
      </li>
      
      
    </ul>
    <form class="form-inline my-2 my-lg-0" method="POST" action="products?action=search">
      <input class="form-control mr-sm-2" name="product" type="text" placeholder="Search Products..." aria-label="Search Products...">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>