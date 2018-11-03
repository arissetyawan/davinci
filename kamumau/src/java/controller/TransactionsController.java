package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Order;
import model.Product;
import model.Transaction;
import model.User;

public class TransactionsController extends ApplicationController{
    private final static String ADD_ACTION = "new";
    private final static String DELETE_ACTION = "delete";
    private final static String EDIT_ACTION = "edit";
    private final static String LIST_ACTION = "lists";
    private String message= "";
    private static final String TABLE_NAME = "transaction";
    //private static final int USER_ID = 2; //untuk sementara sebagai user yg sedang login
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String action = request.getParameter("action");
                switch (action) {
                    case "new":
                        mustLoggedIn(request, response);
                        showNewForm(request, response);
                        break;
                    case "create":
                        mustLoggedIn(request, response);
                        createTransaction(request, response);
                        break;
                    case "delete":
                        mustLoggedIn(request, response);
                        deleteTransaction(request, response);
                        break;
                    case "edit":
                        mustLoggedIn(request, response);
                        showEditForm(request, response);
                        break;
                    case "update":
                        mustLoggedIn(request, response);
                        updateTransaction(request, response);
                        break;
                    case "viewtransaction":
                        mustLoggedIn(request, response);
                        viewTransaction(request, response);
                        break;
                    case "process":
                        mustLoggedIn(request, response);
                        processOrder(request, response);
                        break;
                    case "processseller":
                        mustLoggedIn(request, response);
                        processSeller(request, response);
                        break;
                    default:
                        mustLoggedIn(request, response);
                        listTransaction(request, response);
                        break;
                }
        }
    }
    
    
    private void deleteTransaction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            Transaction tr = new Transaction();    
            Order o = new Order();
            int id = Integer.parseInt(request.getParameter("id"));
            tr = tr.find(id);
            message = tr.delete()? "order succesffully deleted":"order was not deleted";
            List<Transaction> transaction = tr.all(tr.getId_order());
            if(transaction.isEmpty()){
                //Delete the order too, because the cart is empty
                o = o.find(tr.getId_order());
                o.delete();
            }
            request.setAttribute("message", message);
            response.sendRedirect("orders?action="+LIST_ACTION); 
        }catch(IOException | NumberFormatException e){
            System.err.println("deleteTransaction() : "+e.getMessage());
        }
        
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("transactions/new.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Transaction transaction = new Transaction();
            Product product = new Product();
            int id = Integer.parseInt(request.getParameter("id"));
            transaction = transaction.find(id);
            product = product.getProductByID(transaction.getId_product());
            request.setAttribute("transaction", transaction);
            request.setAttribute("product", product);
            RequestDispatcher dispatcher = request.getRequestDispatcher("transactions/edit.jsp");
            dispatcher.forward(request, response);

        }catch(SQLException e){
            System.err.println("showEditForm() : "+e.getMessage());
        }
        
    }
    
    private void listTransaction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            Transaction tr= new Transaction();
            Order order = new Order();
            int no = Integer.parseInt(request.getParameter("order"));
            Order o = order.find(no);
            String action = "";
            User user = new User();
            user = user.find(o.getUser_id());
        
            switch(o.getStatus()){
                case "open":
                    action = "Checkout";
                    break;
                case "new":
                    action = "I have paid the order";
                    break;
                case "paid":
                    action = "Waiting for confirmation";
                    break;
                case "delivered":
                    action = "I have received the order";
                    break;
                case "completed":
                    action = "Order completed";
                    break;
                case "cancelled":
                    action = "This order was cancelled";
                    break;

                default:
                    action = o.getStatus();
                    break;
            }
            List<Transaction> transaction = tr.all(no);
            request.setAttribute("transaction", transaction);
            request.setAttribute("order", o);
            request.setAttribute("act", action);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("transactions/list.jsp");
            dispatcher.forward(request, response);
        }catch(Exception e){
            System.err.println("ListTransaction() : "+e.getMessage());
        }
    }
    
    
    private void viewTransaction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Transaction tr= new Transaction();
        Order order = new Order();
        int no = Integer.parseInt(request.getParameter("order"));
        Order o = order.find(no);
        List<Transaction> transaction = tr.all(no);
        String action = "";
        User user = new User();
        user = user.find(o.getBuyer_id());
        
        switch(o.getStatus()){
            case "new":
                action = "Waiting for buyer to paid";
                break;
            case "paid":
                action = "Deliver order";
                break;
            case "delivered":
                action = "Waiting for buyer confirmation";
                break;
            case "waiting":
                action = "Confirm payment";
                break;
            case "completed":
                action = "Order completed";
                break;
            case "cancelled":
                action = "This order was cancelled";
                break;
            default:
                action = o.getStatus();
                break;
        }

        request.setAttribute("transaction", transaction);
        request.setAttribute("act", action);
        request.setAttribute("order", o);
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transactions/viewtransaction.jsp");
        dispatcher.forward(request, response);
    }

    
   private void updateTransaction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       try{
        Product product = new Product(); 
        int id = Integer.parseInt(request.getParameter("id"));
        int id_order = Integer.parseInt(request.getParameter("id_order"));
        int id_product = Integer.parseInt(request.getParameter("id_product"));
        int qty = Integer.parseInt(request.getParameter("qty"));
        product = product.getProductByID(id_product);
        int newPrice = (int) (qty * product.getPrice());
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setId_order(id_order);
        transaction.setId_product(id_product);
        transaction.setQty(qty);
        transaction.setTotal(newPrice);
        if (transaction.update()){
            message= "transaction updated";     
            request.setAttribute("message", message);
            response.sendRedirect("orders?action="+LIST_ACTION);
        }else{
            message= "transaction failed to updated";     
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("orders?action="+LIST_ACTION);
            dispatcher.forward(request, response);
        }
       
       }catch(Exception e){
           System.err.println("updateTransaction() : "+e.getMessage());
       }    
    }
   
    
   
    private void createTransaction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id_product = Integer.parseInt(request.getParameter("id_product"));
        int id_seller = Integer.parseInt(request.getParameter("id_seller"));
        int qty = Integer.parseInt(request.getParameter("qty"));
        HttpSession session = request.getSession();
        String me = session.getAttribute("current_user").toString();
        User user = new User();
        user = user.find(Integer.parseInt(me));
        
        Transaction transaction = new Transaction();
        transaction.setId_product(id_product);
        transaction.setQty(qty);
        if(transaction.initOrCeate(transaction, user.getId(), id_seller)){
            message= "new added";                    
            request.setAttribute("message", message);
            response.sendRedirect("orders?action="+LIST_ACTION);
        }else{
            message= "failed to add";
            request.setAttribute("message", message);
            request.getRequestDispatcher("orders?action="+LIST_ACTION).include(request, response);
        }
    }

    
    
    public void processOrder(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try{
           int order_id = Integer.parseInt(request.getParameter("order"));
           String statusBefore = request.getParameter("status");
           Order order = new Order();
           order = order.find(order_id);
           switch(statusBefore){
               case "open":
                   order.setStatus("new");
                   break;
               case "new":
                   order.setStatus("waiting");
                   break;
               case "paid":
                   order.setStatus("delivered");
                   break;
               case "delivered":
                   order.setStatus("completed");
                   break;
               default:
                   order.setStatus(request.getParameter("status"));
                   break;
           }
           order.update();
           response.sendRedirect("orders?action="+LIST_ACTION);
       }catch(IOException | NumberFormatException e){
           System.err.println("processOrder() : "+e.getMessage());
       }
    }
    
    public void processSeller(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        try{
           int order_id = Integer.parseInt(request.getParameter("order"));
           String statusBefore = request.getParameter("status");
           Order order = new Order();
           order = order.find(order_id);
           switch(statusBefore){
               case "waiting":
                   order.setStatus("paid");
                   break;    
               case "paid":
                   order.setStatus("delivered");
                   break;
               default:
                   break;
           }
           order.update();
           response.sendRedirect("orders?action="+LIST_ACTION);
       }catch(IOException | NumberFormatException e){
           System.err.println("processSeller() : "+e.getMessage());
       }
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
