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
                    <th>Product name</th>
                    <th>Qty</th>
                    <th>Total</th>
                    <c:choose>
                    <c:when test="${order.getStatus() == 'open'}">
                        <th>Actions</th>
                    </c:when>
                    </c:choose>
                </tr>
            </thead>
            <tbody>
            <% int i = 1; %>
            <c:forEach var="transaction" items="${transaction}">
                <c:choose>
                    <c:when test="${order.getStatus() == 'open'}">
                        <tr onclick="DoNav('transactions?action=edit&id=<c:out value='${transaction.getId()}'/>');"
                              onmouseover="ChangeColor(this, true);"
                              onmouseout="ChangeColor(this, false);">                
                    </c:when>
                    <c:otherwise>
                        <tr onmouseover="ChangeColor(this, true);" onmouseout="ChangeColor(this, false);">
                    </c:otherwise>
                </c:choose>
                    <td><%=i %></td>
                    <td><c:out value="${transaction.id_order}" /></td>
                    <td><c:out value="${transaction.name}" /></td>
                    <td><c:out value="${transaction.qty}"/></td>
                    <td><c:out value="${transaction.total}"/></td>
                    <c:choose>
                    <c:when test="${order.getStatus() == 'open'}">
                        <td><a class="btn btn-outline-danger btn-sm" href="transactions?action=delete&id=<c:out value='${transaction.getId()}' />" onclick="return confirm('Are you sure?')" >Delete this item</a></td>
                    </c:when>
                    </c:choose>

                    
                </tr>
            <%i++; %>
            </c:forEach>
            </tbody>
        </table>
    </div>  
    <div align="left"  style="padding: 1%">
        <h3><c:out value="Seller name ${user.fullname}" /></h3>
        <h3><c:out value="Bank ${user.bankname} - ${user.accountno}" /></h3>
    
    </div>


    <div align="right" style="margin: 5%">
    
    <c:choose>
    <c:when test="${order.getStatus() == 'completed'}">
        <a class="btn btn-primary" href="#" />Write feedback</a>
    </c:when>
    <c:otherwise>
        <c:choose>
        <c:when test="${order.getStatus() != 'cancelled'}">
            <a class="btn btn-primary" href="transactions?action=process&order=<c:out value='${order.getNo()}' />&status=<c:out value='${order.getStatus()}' />"><c:out value="${act}" /></a>
            <a class="btn btn-danger" href="#">Cancel</a>
        </c:when>
        <c:otherwise>
            <a disabled class="btn btn-outline-danger"><c:out value="${act}" /></a>
        </c:otherwise>
        </c:choose>

    </c:otherwise>
    </c:choose>

    </div>

    </body>
</html>
