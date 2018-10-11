<%-- 
    Document   : list
    Created on : Oct 1, 2018, 2:37:51 PM
    Author     : arissetyawan.email@gmail.com
--%>
<%@page language= "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>People MVC JSP Bean Application</title></head>
<body>
        <div align="center">

            <h1>List of People</h2></h1>
            <h4><c:out value='${message}' /></h4>
            <h2><a href="people?action=new">Add Person</a></h2>
                <table border="1" cellpadding="5">
                        <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Phone</th>
                                <th>Profession</th>
                                <th colspan='2'>Actions</th>
                        </tr>
                        <c:forEach var="person" items="${people}">
                                <tr>
                                        <td><c:out value="${person.id}" /></td>
                                        <td><c:out value="${person.name}" /></td>
                                        <td><c:out value="${person.phone}" /></td>
                                        <td><c:out value="${person.profession}" /></td>
                                        <td>
                                                <a href="people?action=edit&id=<c:out value='${person.getId()}' />">Edit</a>
                    </td>
                    <td>
                                                <a href="people?action=delete&id=<c:out value='${person.getId()}' />" onclick="return confirm('Are you sure?')" >Delete</a>                                          
                                        </td>
                                </tr>
                        </c:forEach>
                </table>
        </div>      
</body>
</html>