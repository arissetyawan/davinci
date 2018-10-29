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
        
        function DeleteOrder(url){
            document.location.href = url;    
        }
        
        function tableCondition(values){ 
            if(values === "My orders"){
               document.location.href = "orders?action=list&choose=out";
            }else{
               document.location.href = "orders?action=list&choose=in";
            }
        }
        
        </script>
    </head>
    <body>
        <div align="center" style="margin: 128px;">
            <div class="form-group">
                <select class="form-control" id="choose" name="choose" onchange="tableCondition(this.value)">
                    <option selected="selected">My orders</option>
                    <option>Incoming orders</option>
            </select>
        </div>

            <a class="btn btn-primary" href="transactions?action=new">Add</a>
            <h4><c:out value='${message}' /></h4>
            <table class="table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>ID</th>
                    <th>Order</th>
                    <th>Seller name</th>
                    <th>Created on</th>
                    <th>Last updated</th>
                    <th>Status</th>
                    <th>Actions</th>    
                </tr>
            </thead>
            <tbody>
            <% int i = 1; %>
            <c:forEach var="order" items="${order}">
                <tr onclick="DoNav('transactions?action=list&order=<c:out value='${order.getNo()}' />');"
                              onmouseover="ChangeColor(this, true);"
                              onmouseout="ChangeColor(this, false);">
                    <td><%=i %></td>
                    <td><c:out value="${order.id}" /></td>
                    <td><c:out value="${order.no}" /></td>
                    <td><c:out value="${order.user_name}" /></td>
                    <td><c:out value="${order.created_at}"/></td>
                    <td><c:out value="${order.updated_at}"/></td>
                    <td><c:out value="${order.status}"/></td>
                    <td>
                        <a type="button" class="btn btn-danger" href="orders?action=delete&id=<c:out value='${order.getId()}' />&no=<c:out value='${order.getNo()}' />" 
                           onclick="return confirm('Are you sure?  The items on this bucket will be deleted too')">Delete</a>
                    </td>
                
            </tr>
            <% i++; %>
            </c:forEach>
            </tbody>
        </table>
    </div>  
        
    </body>
    
    
            <!-- Nav tabs -->
        <div>
<ul class="nav nav-tabs">
  <li class="nav-item">
    <a class="nav-link active" href="#">Active</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#">Link</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#">Link</a>
  </li>
  <li class="nav-item">
    <a class="nav-link disabled" href="#">Disabled</a>
  </li>
</ul>        
        </div>
</html>
