<%-- 
    Document   : navbar_logged_in.jsp
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
        <a class="nav-link" href="products?action=search&product=">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="orders?action=list">My Orders</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="products?action=list">My Products</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="categories?action=list">My Categories</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="users?action=profile">My Profile</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="users?action=logout">Sign Out</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0" method="POST" action="products?action=search">
      <input class="form-control mr-sm-2" name="product" type="text" placeholder="Search Products..." aria-label="Search Products...">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
<h1><% out.println("Debugged Logged User: " + session.getAttribute("current_user")); %></h1>