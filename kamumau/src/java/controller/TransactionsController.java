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
import model.Order;
import model.Transaction;

public class TransactionsController extends HttpServlet{
    private final static String ADD_ACTION = "new";
    private final static String DELETE_ACTION = "delete";
    private final static String EDIT_ACTION = "edit";
    private final static String LIST_ACTION = "lists";
    private String message= "";
    private static final String TABLE_NAME = "transaction";
    private static final int USER_ID = 1; //untuk sementara sebagai user yg sedang login
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String action = request.getParameter("action");
                switch (action) {
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "create":
                        createTransaction(request, response);
                        break;
                    case "delete":
                        deleteTransaction(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "update":
                        updateTransaction(request, response);
                        break;
                    case "viewtransaction":
                        viewTransaction(request, response);
                        break;
                    default:
                        listTransaction(request, response);
                        break;
                }
        }
    }
    
    
    private void deleteTransaction(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Transaction tr = new Transaction();
        tr.setId(id);       
        message = tr.delete()? "order succesffully deleted":"order was not deleted";
        request.setAttribute("message", message);
        response.sendRedirect("orders?action="+LIST_ACTION+"$choose=in"); 
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("transactions/new.jsp");
            dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Transaction transaction = new Transaction();
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("transaction", transaction.find(id));
        RequestDispatcher dispatcher = request.getRequestDispatcher("transactions/edit.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listTransaction(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Transaction tr= new Transaction();
        int no = Integer.parseInt(request.getParameter("order"));
        List<Transaction> transaction = tr.all(no);
        request.setAttribute("transaction", transaction);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transactions/list.jsp");
        dispatcher.forward(request, response);
    }
    
    
    private void viewTransaction(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        Transaction tr= new Transaction();
        int no = Integer.parseInt(request.getParameter("order"));
        int buyer_id = Integer.parseInt(request.getParameter("buyer_id"));
        
        List<Transaction> transaction = tr.all(no);
        request.setAttribute("transaction", transaction);
        request.setAttribute("buyer_id", buyer_id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("transactions/viewtransaction.jsp");
        dispatcher.forward(request, response);
    }

    
   private void updateTransaction(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        int id_order = Integer.parseInt(request.getParameter("id_order"));
        int id_product = Integer.parseInt(request.getParameter("id_product"));
        int qty = Integer.parseInt(request.getParameter("qty"));
        int total = Integer.parseInt(request.getParameter("total"));
        Transaction transaction = new Transaction();
        transaction.setId(id);
        transaction.setId_order(id_order);
        transaction.setId_product(id_product);
        transaction.setQty(qty);
        transaction.setTotal(total);
        if (transaction.update()){
            message= "transaction updated";     
            request.setAttribute("message", message);
            Order o = new Order();
            List<Order> order = o.all(USER_ID);
            request.setAttribute("order", order);
            request.getRequestDispatcher("/orders/list.jsp").include(request, response);
        }else{
            message= "transaction failed to updated";     
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("orders?action="+LIST_ACTION);
            dispatcher.forward(request, response);
        }
    }
   
   
    private void createTransaction(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id_product = Integer.parseInt(request.getParameter("id_product"));
        int id_seller = Integer.parseInt(request.getParameter("id_seller"));
        int qty = Integer.parseInt(request.getParameter("qty"));
        int total = Integer.parseInt(request.getParameter("total"));
 
        Transaction transaction = new Transaction();
        transaction.setId_product(id_product);
        transaction.setQty(qty);
        transaction.setTotal(total);
        if(transaction.initOrCeate(transaction, USER_ID, id_seller)){
            message= "new added";                    
            request.setAttribute("message", message);
            response.sendRedirect("orders?action="+LIST_ACTION+"&choose=in");
        
        }else{
            message= "failed to add";
            request.setAttribute("message", message);
            request.getRequestDispatcher("orders?action="+LIST_ACTION+"&choose=in").include(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}