<link href="/WEB-INF/stylesheets/jumbotron.css" rel="stylesheet">


<%@include file= "/layouts/header.jsp" %>


<br>
<br>

<div class="signup-form">
        
    <h1 class="modal-title float-left">Profile</h1>            

  <h4><c:out value='${message}' /></h4>
  
  <div class="jumbotron">
      <div class="frame">
  <form action="/kamumau/users?action=update" method="post"> 
  <%@include file= "form_1.html" %>
  
  </form>
      </div>
  </div>

  
  
  <hr>

</div> <!-- /container -->



    <%@include file= "/layouts/footer.html" %>
    
    <script>
        $('#password, #repassword').on('keyup', function () {
  if ($('#password').val() == $('#repassword').val()) {
    $('#message').html('Matching').css('color', 'green');
  } else 
    $('#message').html('Not Matching').css('color', 'red');
});
        
    </script>