/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.Feedback;
import model.User;




public class FeedbacksController extends ApplicationController {
    
    private final static String add_action = "new";
    private final static String list_action = "list";
    private String message= "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                String action = request.getParameter("action");
                if(action==null){
                    action= "list";
                }
                try {
                    switch (action) {
                    case "create":
                        createFeedback(request, response);
                        break;
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "delete":
                        deleteFeedback(request, response);
                        break;
                    default:
                        listFeedback(request, response);
                        break;
                    }
                } 
            catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }
    }
        private void listFeedback(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Feedback  feed= new Feedback();
        User user = new User();
        
        List<Feedback> feedbacks = feed.all();
        request.setAttribute("feedbacks", feedbacks);
        request.setAttribute("users", user.find(1));
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("feedbacks/list.jsp");
        dispatcher.forward(request, response);
    }
       
  
    private void createFeedback(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String content = request.getParameter("content");
        int rating = Integer.parseInt(request.getParameter("rating"));
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        int seller_id = Integer.parseInt(request.getParameter("seller_id"));
        int buyer_id = Integer.parseInt(request.getParameter("buyer_id"));
             
        Feedback feedback = new Feedback();
        feedback.setContent(content);
        feedback.setRating(rating);
        feedback.setOrder_id(order_id);
        feedback.setSeller_id(seller_id);
        feedback.setBuyer_id(buyer_id);
        if (feedback.create()){
            message= "new feedback added";                    
            request.setAttribute("message", message);
            response.sendRedirect("feedbacks?action="+list_action);
        }
        else{
            message= "new feedback failed to add";
            request.setAttribute("message", message);
            request.getRequestDispatcher("feedbacks?action="+add_action).include(request, response);
        }
    }
    
        private void showNewForm(HttpServletRequest request, HttpServletResponse response)
                
                throws ServletException, IOException {
             User user = new User();
        request.setAttribute("users", user.find(1));
        
            RequestDispatcher dispatcher = request.getRequestDispatcher("feedbacks/new.jsp");
            dispatcher.forward(request, response);
        }
        
    private void deleteFeedback(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Feedback feedbacks = new Feedback();
        feedbacks.setId(id);
        if(feedbacks.delete()){
            message= "feedback deleted";                    
        }
        else{
            message= "feedback was not deleted";                
        }    
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("feedbacks?action="+ list_action);
        dispatcher.forward(request, response);

//        response.sendRedirect("people?action="+list_action); 
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
