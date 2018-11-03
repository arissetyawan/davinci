<%-- 
    Document   : new
    Created on : Oct 31, 2018, 11:05:32 PM
    Author     : M_NAZIL
--%>

<%@include file= "/layouts/header.jsp" %>
<html lang="en">
<head>
		
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1, maximum-scale=1" name="viewport">

<title>feedBacks</title>
<link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css" />


<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>




</head>
            <br>
	    <div class="container">
		<div class="row">
		<div class="col-md-12">
		<div class="panel panel-primary">
		<div class="panel-heading">
		 <h3 class="panel-title"><c:out value="${users.fullname}"/><br></h3>
		</div>
	    <div class="panel-body">
                Address : <c:out value="${users.address}"/><br>
                Email   : <c:out value="${users.email}"/><br>
	    </div>
		</div>
	        </div>
		</div>
	    </div>
            <br>
	    <div class="container">
		<div class="row">
		<div class="col-md-12">
		<div class="panel panel-primary">
		<div class="panel-heading">
		 <h3 class="panel-title">Ensure your feedback is fair and please, be honest:)</h3>
		</div>
	    <div class="panel-body">
                <div class="container">
                  <form action="/kamumau/feedbacks?action=create" method="post"> 
                  
                        <input type="hidden" name="seller_id" value="1" />
                        <input type="hidden" name="buyer_id" value="1" />

                <div class="form-group">
                    <label for="nama">Order No :</label><br>
                    <input type="text" class="form-control col-sm-4" name="order_id" aria-describedby="order_id" value="1" placeholder="Order No" required oninvalid="this.setCustomValidity('order_id tidak boleh kosong')" oninput="setCustomValidity('')" readonly/>
			</div><br><br>
                <div class="form-group">
                    <label for="comment">
                        Comment : <br>
                        <textarea rows="5" type="text" cols="150" class="form-control col-sm-4" name="content" aria-describedby="content" placeholder="Insert New Comment" required oninvalid="this.setCustomValidity('Comment Is Not Null')" oninput="setCustomValidity('')" ></textarea>    
                    </label>
                </div>
                <div class="form-group row">
                <label for="sel1" class="col-sm-2 control-label">Rate : </label>
                <input type="hidden" id="rating" name="rating" value="" />
                </div>
                <div class="rating-stars">
                    <section class='rating-widget'>
                        <!-- Rating Stars Box -->
                          <ul id='stars'>
                            <li class='star' title='Poor' data-value='1'>
                              <i class='fa fa-star fa-fw'></i>
                            </li>
                            <li class='star' title='Fair' data-value='2'>
                              <i class='fa fa-star fa-fw'></i>
                            </li>
                            <li class='star' title='Good' data-value='3'>
                              <i class='fa fa-star fa-fw'></i>
                            </li>
                            <li class='star' title='Excellent' data-value='4'>
                              <i class='fa fa-star fa-fw'></i>
                            </li>
                            <li class='star' title='WOW!!!' data-value='5'>
                              <i class='fa fa-star fa-fw'></i>
                            </li>
                          </ul>
                    </section>
               
               </div>
  </div>
                <br>
                <input type="submit" value="Submit" class="btn btn-info" />
	    </div>
</form>    
		</div>
	        </div>
		</div>
                </div>
            </div>
</body>
</html>

 <link href="feedbacks/rating.css" rel="stylesheet" type="text/css">
                    
                   <script>
                $(document).ready(function(){
                  /* 1. Visualizing things on Hover - See next part for action on click */
                  $('#stars li').on('mouseover', function(){
                    var onStar = parseInt($(this).data('value'), 10); // The star currently mouse on
                    // Now highlight all the stars that's not after the current hovered star
                    $(this).parent().children('li.star').each(function(e){
                      if (e < onStar) {
                        $(this).addClass('hover');
                      }
                      else {
                        $(this).removeClass('hover');
                      }
                    });
                  }).on('mouseout', function(){
                    $(this).parent().children('li.star').each(function(e){
                      $(this).removeClass('hover');
                    });
                  });
                  /* 2. Action to perform on click */
                  $('#stars li').on('click', function(){
                    var onStar = parseInt($(this).data('value'), 10); // The star currently selected
                    var stars = $(this).parent().children('li.star');
                    for (i = 0; i < stars.length; i++) {
                      $(stars[i]).removeClass('selected');
                    }
                    for (i = 0; i < onStar; i++) {
                      $(stars[i]).addClass('selected');
                    }
                    // JUST RESPONSE (Not needed)
                    var ratingValue = parseInt($('#stars li.selected').last().data('value'), 10);
                    document.getElementById('rating').value = ratingValue;
                  });  
                });
               </script>

<%@include file= "/layouts/footer.html" %>
