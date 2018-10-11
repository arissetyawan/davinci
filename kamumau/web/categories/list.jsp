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
        <input type="text" name="keyword" />
        <input type="submit" class="btn btn-primary btn-lg" />
    </form>
  </div>
</div>

<div class="container">

  <!-- Example row of columns -->
  <div class="row">
    <div class="col-md-4">
      <h2>Categories A</h2>
      <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
      <p><a class="btn btn-secondary" href="#" role="button">View products &raquo;</a></p>
    </div>
    <div class="col-md-4">
      <h2>Categories B</h2>
      <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
      <p><a class="btn btn-secondary" href="#" role="button">View products &raquo;</a></p>
    </div>
    <div class="col-md-4">
      <h2>Categories C</h2>
      <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
      <p><a class="btn btn-secondary" href="#" role="button">View products &raquo;</a></p>
    </div>
  </div>

  <hr>

</div> <!-- /container -->

</main>

<%@include file= "/layouts/footer.html" %>