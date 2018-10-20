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
                    <th>ID</th>
                    <th>Order no</th>
                    <th>Product</th>
                    <th>Qty</th>
                    <th>Total</th>
                    <th>Actions</th>    
                </tr>
            </thead>
            <tbody>
            <% int i = 1; %>
            <c:forEach var="transaction" items="${transaction}">
                <tr onclick="DoNav('transactions?action=edit&id=<c:out value='${transaction.getId()}'/>');"
                              onmouseover="ChangeColor(this, true);"
                              onmouseout="ChangeColor(this, false);">
                    <td><%=i %></td>
                    <td><c:out value="${transaction.id}" /></td>
                    <td><c:out value="${transaction.id_order}" /></td>
                    <td><c:out value="${transaction.name}" /></td>
                    <td><c:out value="${transaction.qty}"/></td>
                    <td><c:out value="${transaction.total}"/></td>
                    <td>
                        <a type="button" class="btn btn-danger" href="transactions?action=delete&id=<c:out value='${transaction.getId()}' />" onclick="return confirm('Are you sure?')" >Delete</a>                     
                    </td>
                </tr>
            <%i++; %>
            </c:forEach>
            </tbody>
        </table>
    </div>  
    <div align="right" style="margin: 5%">
        <a class="btn btn-primary" href="#">Checkout</a>
        <a class="btn btn-danger" href="#">Cancel</a>
    </div>

    </body>
</html>
