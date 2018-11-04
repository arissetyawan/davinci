<%-- 
    Document   : list
    Created on : Oct 11, 2018, 8:27:04 PM
    Author     : x201
--%>
    <link href="/WEB-INF/stylesheets/jumbotron.css" rel="stylesheet">
    <%@include file= "/layouts/header.jsp" %>

    <main role="main">
    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron" style="padding-top: 25px;">
            <div class="container">
                <h3 class="display-6"><center>Looking for category ?</center></h3>
                <p>
                <form action="categories?action=search" method="post">
                    <center>
                    <input type="text" name="keyword" />
                    <input type="submit" class="btn btn-primary btn-lg" value="Search"/>
                    </center>
                </form>
            </div>
            <div class="container">
                <div align="center">
                           <h2><b>List of Category</b></h2>
                    <h4><c:out value='${message}' /></h4>
                    <h3><a href="categories?action=new">Add Category</a></h3>
                    <p><hr/><p/>
                    <div class="container" style="padding-top: 20px;">
                        <!-- Example row of columns -->
                        <div class="row">
                            <c:forEach var="Category" items="${categories}">
                                <div class="col-md-4" >
                                    <h3><c:out value="${Category.name}" /></h3>
                                    <p><c:out value="${Category.description}" /></p>
                                    <p><a class="btn btn-secondary" style="background-color: green;" href="categories?action=edit&id=<c:out value='${Category.getId()}' />"role="button" >Edit</a>
                                       <a class="btn btn-secondary" style="background-color: red;" href="categories?action=delete&id=<c:out value='${Category.getId()}' />" role="button" onclick="return confirm('Are you sure?')">Delete</a> 
                                       <a class="btn btn-primary"  href="#" role="button">View products &raquo;</a></p>
                                    <p></p>
                                </div>
                            </c:forEach>
                        </div>
                        <hr>
                    </div>                      
                </div> 
            </div>
        </div>
    </main>
    