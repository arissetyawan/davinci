package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Order;
import model.User;

public class OrdersController extends ApplicationController{
    private final static String ADD_ACTION = "new";
    private final static String DELETE_ACTION = "delete";
    private final static String EDIT_ACTION = "edit";
    private final static String LIST_ACTION = "lists";
    private String message= "";
    //private static final int USER_ID = 2; //untuk sementara sebagai user yg sedang login
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String action = request.getParameter("action");
                try {
                    switch (action) {
                    case "create":
                        mustLoggedIn(request, response);
                        createOrder(request, response);
                        break;
                    case "delete":
                        mustLoggedIn(request, response);
                        deleteOrder(request, response);
                        break;
                    case "edit":
                        mustLoggedIn(request, response);
                        showEditForm(request, response);
                        break;
                    case "update":
                        mustLoggedIn(request, response);
                        updateOrder(request, response);
                        break;
                    case "cancel":
                        mustLoggedIn(request, response);
                        cancelOrder(request, response);
                        break;
                    default:
                        mustLoggedIn(request, response);
                        listOrderWithChoose(request, response);
                        break;
                }
                }catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }
    }
        
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = new Order();
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("order", order.find(id));
        RequestDispatcher dispatcher = request.getRequestDispatcher("orders/edit.jsp");
        dispatcher.forward(request, response);
    }
    
    private void updateOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");
        Order o = new Order();
        o.setStatus(status);
        HttpSession session = request.getSession();
        String me = session.getAttribute("current_user").toString();
        
        if (o.update()){
            message= "order updated";     
            request.setAttribute("message", message);
            List<Order> order = o.all(Integer.parseInt(me));
            request.setAttribute("order", order);
            request.getRequestDispatcher("/orders/list.jsp").include(request, response);
        }else{
            message= "order failed to updated";     
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("orders?action="+ EDIT_ACTION);
            dispatcher.forward(request, response);
        }
    }
    
    private void cancelOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int no = Integer.parseInt(request.getParameter("no"));
        String status = "cancelled";
        Order o = new Order();
        o = o.find(no);
        o.setStatus(status);
        if (o.update()){
            message= "order updated";     
            request.setAttribute("message", message);
            response.sendRedirect("orders?action="+LIST_ACTION);
        }else{
            message= "order failed to updated";     
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("orders?action="+ EDIT_ACTION);
            dispatcher.forward(request, response);
        }
    }
    
    
    private void createOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Order order = new Order();
        HttpSession session = request.getSession();
        int me = Integer.parseInt(session.getAttribute("current_user").toString());
        order.setUser_id(me);
        if (order.create()){
            message= "new order added";                    
            request.setAttribute("message", message);
            response.sendRedirect("orders?action="+LIST_ACTION);
        }else{
            message= "new order failed to add";
            request.setAttribute("message", message);
            request.getRequestDispatcher("orders?action="+LIST_ACTION).include(request, response);
        }
    }
    
        
    private void listOrderWithChoose(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Order o= new Order();
        HttpSession session = request.getSession();
        int me = Integer.parseInt(session.getAttribute("current_user").toString());

        List<Order> order = o.allIncoming(me);
        List<Order> mycart = o.allMyOrders(me);
        List<Order> myCompletedOrders = o.allCompletedOrders(me);
        List<Order> myOut = o.allOutcoming(me);
        request.setAttribute("mycart", mycart);
        request.setAttribute("order", order);
        request.setAttribute("completed", myCompletedOrders);
        request.setAttribute("outcoming", myOut);
        RequestDispatcher dispatcher = request.getRequestDispatcher("orders/list.jsp");
        dispatcher.forward(request, response);        
    }
    
    
    private void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int id_order = Integer.parseInt(request.getParameter("no"));
        Order order = new Order();
        order.setId(id);
        order.setNo(id_order);
        message = order.delete()? "order succesffully deleted":"order was not deleted";
        request.setAttribute("message", message);
        response.sendRedirect("orders?action="+LIST_ACTION); 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    
}
