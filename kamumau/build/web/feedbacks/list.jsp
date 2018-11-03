<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<%@include file= "/layouts/header.jsp" %> 
    <div>
        <div style="padding-left: 15px; margin-top: 4%">
            <h6>Username : <c:out value="${users.fullname}"/> </h6>
            <h5>Address  : <c:out value="${users.address}"/><br></h5>
            <h4>Email    : <c:out value="${users.email}"/><br></h4>
        <h2>
            List of Feedback
        </h2>
        <p>List all Feedback</p>
    </div>
    <hr>
    </div>
    <hr>
    <div class="tab-content">
        <div class="tab-pane active" id="home" role="tabpanel" aria-labelledby="home-tab">
        <table class="table">
            <thead>
                        <tr>
                            <th>
                                No.
                            </th>
                            <th>
                                Content
                            </th>
                            <th>
                                Rating
                            </th>
                            <th>
                                Order ID
                            </th>
                            <th>
                                Seller ID
                            </th>
                            <th>
                                Buyer ID
                            </th>
                            <th colspan="1">
                                Action
                            </th>
                        </tr>
                    </thead>
            <tbody>
            <% int no =1; %>
                        <c:forEach items="${feedbacks}" var="Feedback">
                            
                            <tr onmouseover="ChangeColor(this, true);"
                              onmouseout="ChangeColor(this, false);">
                                <td>
                                    <% out.print(no); %>
                                </td>
                                <td>
                                    <c:out value="${Feedback.content}"/>
                                </td>
                                <td>
                                    <c:out value="${Feedback.rating}"/>
                                </td>
                                <td>
                                   <c:out value="${Feedback.order_id}"/>
                                </td>
                                <td>
                                    <c:out value="${Feedback.seller_id}"/>
                                </td>
                                <td>
                                    <c:out value="${Feedback.buyer_id}"/>
                                </td>
                                    <td>
                                        <a onclick="return confirm('Are you sure ?')" href="feedbacks?action=delete&id=<c:out value="${Feedback.id}"/>" class="btn btn-outline-danger btn-sm">Delete</a>
                                    </td>
                                </tr>
                                <% no++; %>
                            </c:forEach>     
            </tbody>
        </table>
    </div>
    </div>