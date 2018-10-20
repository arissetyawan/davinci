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
import model.Order;

/*
Cara masuk orders:

kamumau/orders?action=list&choose=in

table name ORDERS

column
id INT AI
no Long
user_id INT
buyer_id INT
created_at DATE
updated_at DATE
status VARCHAR
*/
public class OrdersController extends HttpServlet{
    private final static String ADD_ACTION = "new";
    private final static String DELETE_ACTION = "delete";
    private final static String EDIT_ACTION = "edit";
    private final static String LIST_ACTION = "lists";
    private String message= "";
    private static final int USER_ID = 1; //untuk sementara sebagai user yg sedang login
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String action = request.getParameter("action");
                try {
                    switch (action) {
                    case "create":
                        createOrder(request, response);
                        break;
                    case "delete":
                        deleteOrder(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "update":
                        updateOrder(request, response);
                        break;
                    default:
                        //listOrder(request, response);
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
        if (o.update()){
            message= "order updated";     
            request.setAttribute("message", message);
            List<Order> order = o.all(USER_ID);
            request.setAttribute("order", order);
            request.getRequestDispatcher("/orders/list.jsp").include(request, response);
        }else{
            message= "order failed to updated";     
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("orders?action="+ EDIT_ACTION);
            dispatcher.forward(request, response);
        }
    }
    
    private void createOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        //Silakan kelompok product tembak method ini
        Order order = new Order();
        order.setUser_id(USER_ID); // << USER_ID adl user yang sedang login 
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
    
    
    private void listOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Order o= new Order();
        List<Order> order = o.all(USER_ID);
        request.setAttribute("order", order);
        RequestDispatcher dispatcher = request.getRequestDispatcher("orders/list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listOrderWithChoose(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Order o= new Order();
        String choose = request.getParameter("choose");
        List<Order> order = null;
        System.out.println(choose);
        if(choose.equals("in")){
            order = o.allIncoming(USER_ID);
            request.setAttribute("order", order);
            RequestDispatcher dispatcher = request.getRequestDispatcher("orders/list.jsp");
            dispatcher.forward(request, response);
        }else if(choose.equals("out")){
            order = o.allMyOrders(USER_ID);
            request.setAttribute("order", order);
            RequestDispatcher dispatcher = request.getRequestDispatcher("orders/listout.jsp");        
            dispatcher.forward(request, response);
        }
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
