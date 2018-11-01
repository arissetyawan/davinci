<%-- 
    Document   : list
    Created on : Oct 9, 2018, 6:29:44 PM
    Author     : Prieyuda Akadita S
--%>
<%@include file="../layouts/header.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of order</title>
        <script type="text/javascript">

        function ChangeColor(tableRow, highLight) {
                if (highLight) {
                    tableRow.style.backgroundColor = '#B2EBF2';
                }else {
                    tableRow.style.backgroundColor = 'white';
                }
            }
        function DoNav(theUrl){
            document.location.href = theUrl;
        }
                
        </script>
        
        
    </head>
    <body>
            
        <div style="margin-top: 5%">
            <div style="padding-left: 15px">
                <h2>
                    Manage your orders here
                </h2>
                <p>You can click to see the details of order(s)</p>
            </div>
            
        <ul class="nav nav-tabs" id="myTab" role="tablist">
          <li class="nav-item">
            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">My cart</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Incoming orders</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="messages-tab" data-toggle="tab" href="#messages" role="tab" aria-controls="messages" aria-selected="false">Completed orders</a>
          </li>
            <li class="nav-item">
            <a class="nav-link" id="messages-tab" data-toggle="tab" href="#myorders" role="tab" aria-controls="myorders" aria-selected="false">My orders</a>
          </li>
        </ul>
</div>
    <div class="tab-content">
        <div class="tab-pane active" id="home" role="tabpanel" aria-labelledby="home-tab">
        <table class="table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Order</th>
                    <th>Seller name</th>
                    <th>Created on</th>
                    <th>Last updated</th>
                    <th>Actions</th>    
                </tr>
            </thead>
            <tbody>
            <% int i = 1; %>
            <c:forEach var="mycart" items="${mycart}">
                <tr onclick="DoNav('transactions?action=list&order=<c:out value='${mycart.getNo()}'/>');"
                              onmouseover="ChangeColor(this, true);"
                              onmouseout="ChangeColor(this, false);">
                    <td><%=i %></td>
                    <td><c:out value="${mycart.no}" /></td>
                    <td><c:out value="${mycart.user_name}" /></td>
                    <td><c:out value="${mycart.created_at}"/></td>
                    <td><c:out value="${mycart.updated_at}"/></td>
                    <td>
                        <a class="btn btn-outline-danger btn-sm" href="orders?action=delete&id=<c:out value='${mycart.getId()}' />&no=<c:out value='${mycart.getNo()}' />" 
                           onclick="return confirm('Are you sure?  The items on this bucket will be deleted too')">Delete</a>
                    </td>
                
            </tr>
            <% i++; %>
            </c:forEach>
            </tbody>
        </table>
            <div align="center">
                <p>My cart contained all of your items but which is not yet paid</p>
            </div>
    </div>
    <div class="tab-pane" id="profile" role="tabpanel" aria-labelledby="profile-tab">
        <table class="table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Order</th>
                    <th>Buyer name</th>
                    <th>Created on</th>
                    <th>Last updated</th>
                    <th>Status</th>
                    <th>Actions</th>    
                </tr>
            </thead>
            <tbody>
            <% int ix = 1; %>
            <c:forEach var="order" items="${order}">
                <tr onclick="DoNav('transactions?action=viewtransaction&order=<c:out value='${order.getNo()}'/>');"
                              onmouseover="ChangeColor(this, true);"
                              onmouseout="ChangeColor(this, false);">
                    <td><%=ix %></td>
                    <td><c:out value="${order.no}" /></td>
                    <td><c:out value="${order.buyer_name}" /></td>
                    <td><c:out value="${order.created_at}"/></td>
                    <td><c:out value="${order.updated_at}"/></td>
                    <td><c:out value="${order.status}"/></td>
                    <c:choose>
                    <c:when test="${order.getStatus() != 'cancelled'}">
                                            <td>
                        <a class="btn btn-outline-danger btn-danger btn-sm" href="orders?action=cancel&no=<c:out value='${order.getNo()}' />" 
                           onclick="return confirm('Are you sure to cancel this order?')">Cancel this order</a>
                    </td>

                    </c:when>
                    </c:choose>
                
            </tr>
            <% ix++; %>
            </c:forEach>
            </tbody>
        </table>
            <div align="center">
                <p>Incoming orders contains all users who buy your products</p>
            </div>
    </div>
    <div class="tab-pane" id="messages" role="tabpanel" aria-labelledby="messages-tab">
            <table class="table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Order</th>
                    <th>Seller name</th>
                    <th>Created on</th>
                    <th>Last updated</th>
                    <th>Feedback</th>
                </tr>
            </thead>
            <tbody>
            <% int index = 1; %>
            <c:forEach var="completed" items="${completed}">
                <tr onclick="DoNav('transactions?action=list&order=<c:out value='${completed.getNo()}' 
       />&status=<c:out value='${completed.getStatus()}' />');"
                              onmouseover="ChangeColor(this, true);"
                              onmouseout="ChangeColor(this, false);">
                    <td><%=index %></td>
                    <td><c:out value="${completed.no}" /></td>
                    <td><c:out value="${completed.user_name}" /></td>
                    <td><c:out value="${completed.created_at}"/></td>
                    <td><c:out value="${completed.updated_at}"/></td>
                    <td>
                        <a class="btn btn-outline-success btn-secondary btn-sm" href="orders?action=delete&id=<c:out value='${completed.getId()}' />&no=<c:out value='${completed.getNo()}' />" 
                           onclick="return confirm('Are you sure?  The items on this bucket will be deleted too')">Feedback</a>
                    </td>                
            </tr>
            <% index++; %>
            </c:forEach>
            </tbody>
        </table>
            <div align="center">
                <p>Only completed orders will be showed here</p>
            </div>
    </div>
    
    <div class="tab-pane" id="myorders" role="tabpanel" aria-labelledby="myorders-tab">
    <table class="table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Order</th>
                    <th>Seller name</th>
                    <th>Created on</th>
                    <th>Last updated</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
            <% int ind = 1; %>
            <c:forEach var="outcoming" items="${outcoming}">
                <tr onclick="DoNav('transactions?action=list&order=<c:out value='${outcoming.getNo()}' />&status=<c:out value='${outcoming.getStatus()}' />');"
                              onmouseover="ChangeColor(this, true);"
                              onmouseout="ChangeColor(this, false);">
                    <td><%=ind %></td>
                    <td><c:out value="${outcoming.no}" /></td>
                    <td><c:out value="${outcoming.user_name}" /></td>
                    <td><c:out value="${outcoming.created_at}"/></td>
                    <td><c:out value="${outcoming.updated_at}"/></td>
                    <td><c:out value="${outcoming.status}"/></td>
                    <td>
                        <a class="btn btn-outline-danger btn-danger btn-sm" href="orders?action=cancel&no=<c:out value='${outcoming.getNo()}' />" 
                           onclick="return confirm('Are you sure?  Your payment will be void')">Cancel</a>

                    </td>                
            </tr>
            <% ind++; %>
            </c:forEach>
            </tbody>
        </table>
            <div align="center">
                <p>In process order will be showed here. You can track them</p>
            </div>

    </div>

</div>

<script>
  
  
  $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
    e.target; // newly activated tab
    e.relatedTarget; // previous active tab
    });
    
    $(function () {
    $('#myTab li:last-child a').tab('show')
  });
</script>


    </body>
        
   
</html>
