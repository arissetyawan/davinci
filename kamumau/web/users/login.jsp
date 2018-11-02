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
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <div class="container">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
        
                <form class="form-signin" action="users?action=do-login" method="POST">
                    
                  
                            <div class="form-group row">
                                <label for="email" class="col-md-4 col-form-label text-md-right">E-Mail </label>
                                <div class="col-md-8">
                                    <input type="text" id="inputEmail" class="form-control" name="email" placeholder="Email" required autofocus>
                                </div>
                            </div>
                  
                            <div class="form-group row">
                                <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                <div class="col-md-8">
                                    <input type="password" id="inputPassword" class="form-control" placeholder="Password" name="password" required>
                                </div>
                            </div>
                    
                    
                    <div class="form-group row">
                        <a class="col-md-4 col-form-label text-md-right btn-link" style="padding-top: 20px" id="Sign UP" href="users?action=new">Sign UP</a>
                        <div class="col-md-6">
                             <button class="btn btn-lg btn-primary btn-block  float-right" style="display:grid;grid-template-columns:1fr 1fr 1fr;grid-template-rows:50px;" type="submit" id="btn-submit">Sign in</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>

<%@include file= "/layouts/footer.html" %>
