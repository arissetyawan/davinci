<%-- 
    Document   : list
    Created on : Oct 10, 2018, 10:42:27 AM
    Author     : Prieyuda Akadita S
--%>

<%@include file="../layouts/header.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of things</title>
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
        <div align="center">
            <h4><c:out value='${message}' /></h4>
            <table class="table">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Order no</th>
                    <th>Product</th>
                    <th>Qty</th>
                    <th>Total</th>    
                </tr>
            </thead>
            <tbody>
            <% int i = 1; %>
            <c:forEach var="transaction" items="${transaction}">
                <tr onmouseover="ChangeColor(this, true);"
                    onmouseout="ChangeColor(this, false);">
                    <td><%=i %></td>
                    <td><c:out value="${transaction.id_order}" /></td>
                    <td><c:out value="${transaction.name}" /></td>
                    <td><c:out value="${transaction.qty}"/></td>
                    <td><c:out value="${transaction.total}"/></td>
                </tr>
            <%i++; %>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div align="left"  style="padding: 1%">
        <h3><c:out value="Buyer name ${user.fullname}" /></h3>
    </div>
    <div align="right" style="margin: 5%">
    
    <c:choose>
        <c:when test="${order.getStatus() != 'cancelled'}">
        <a class="btn btn-primary" href="transactions?action=processseller&order=<c:out value='${order.getNo()}' />&status=<c:out value='${order.getStatus()}' />"><c:out value="${act}" /></a>
        <a class="btn btn-danger" href="#">Cancel</a>
    </c:when>
        <c:otherwise>
            <a disabled class="btn btn-outline-danger"><c:out value="${act}" /></a>
        </c:otherwise>
    </c:choose>
    </div>
            
    
    </body>
</html>
