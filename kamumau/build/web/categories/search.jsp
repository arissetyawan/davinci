<%-- 
    Document   : list
    Created on : Oct 11, 2018, 8:27:04 PM
    Author     : x201
--%>
<link href="/WEB-INF/stylesheets/jumbotron.css" rel="stylesheet">

<%@include file= "/layouts/header.jsp" %>

<main role="main">

<!-- Main jumbotron for a primary marketing message or call to action -->
<div class="jumbotron">
  <div class="container">
    <h1 class="display-3">Looking for categories ?</h1>
    <p><form>
        <input type="text" name="keyword" <c:out value="${Category.name}" />/>
        <input type="submit" class="btn btn-primary btn-lg" href="categories?action=search"/>
    </form>
  </div>
</div>

<div class="container">

 
  <div align="center">

            <h1>List of Category</h1>
            <h4><c:out value='${message}' /></h4>
            <h2><a href="categories?action=new">Add Category</a></h2>
                <table border="1" cellpadding="5">
                        <tr>
                                <th>ID</th>
                                <th>Name Category</th>
                                <th>Category id</th>
                                <th>Description</th>
                                <th colspan='2'>Actions</th>
                        </tr>
                        <c:forEach var="Category" items="${categories}">
                                <tr>
                                        <td><c:out value="${Category.id}" /></td>
                                        <td><c:out value="${Category.name}" /></td>
                                        <td><c:out value="${Category.category_id}" /></td>
                                        <td><c:out value="${Category.description}" /></td>
                                        <td>
                                                <a href="categories?action=edit&id=<c:out value='${Category.getId()}' />">Edit</a>
                    </td>
                    <td>
                                                <a href="categories?action=delete&id=<c:out value='${Category.getId()}' />"  onclick="return confirm('Are you sure?')">Delete</a>                                          
                                        </td>
                                </tr>
                        </c:forEach>
                </table>
        </div> 

  <hr>

</div> <!-- /container -->

</main>

<%@include file= "/layouts/footer.html" %>