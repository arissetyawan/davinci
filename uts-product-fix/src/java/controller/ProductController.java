/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author XXVII
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductController"})
public class ProductController extends HttpServlet {
    private final static String add_action = "edit";
    private final static String delete_action = "delete";
    private final static String edit_action = "edit";
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

                System.out.println(action);
                try {
                switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "create":
                    createProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateProduct(request, response);
                    break;
                case "list":
                    listProduct(request, response);
                    break;
                case "search":
                    searchProduct(request, response);
                    break;    
                }
            } 
            catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }      
    }
    
    private void listProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        ProductDAO pDAO= new ProductDAO();
        List<Product> products = pDAO.getProducts();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("products/productlist.jsp");
        dispatcher.forward(request, response);
    }
    
    private void searchProduct(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException, SQLException {
            ProductDAO pDAO= new ProductDAO();
            int id = Integer.parseInt(request.getParameter("product_id"));
            request.setAttribute("products", pDAO.getProductByID(id));

            RequestDispatcher dispatcher = request.getRequestDispatcher("products/productlist.jsp");
            dispatcher.forward(request, response);
        }
    
    
    private void createProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String category_id = request.getParameter("category_id");
        float price = Float.parseFloat(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
        
        Product p = new Product();
        p.setCategory_id(category_id);
        p.setName(name);
        p.setPrice(price);
        p.setStock(stock);
        
        ProductDAO pDAO = new ProductDAO();
        if (pDAO.addProduct(p)){
            message= "new product added";                    
            request.setAttribute("message", message);
            response.sendRedirect("products?action="+list_action);
        }
        else{
            message= "new product failed to add";
            request.setAttribute("message", message);
            request.getRequestDispatcher("products?action="+list_action).include(request, response);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("products/new.jsp");
            dispatcher.forward(request, response);
        }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException, SQLException {
            ProductDAO pDAO= new ProductDAO();
            int id = Integer.parseInt(request.getParameter("product_id"));
            request.setAttribute("products", pDAO.getProductByID(id));

            RequestDispatcher dispatcher = request.getRequestDispatcher("products/edit.jsp");
            dispatcher.forward(request, response);
        }
    
   private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String category_id = request.getParameter("category_id");
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        int stock = Integer.parseInt(request.getParameter("stock"));
 
        Product p = new Product();
        p.setCategory_id(category_id);
        p.setName(name);
        p.setPrice(price);
        p.setStock(stock);
        
        ProductDAO pDAO = new ProductDAO();
        if (pDAO.updateProduct(p)){
            message= "product updated";     
            request.setAttribute("message", message);
            List<Product> products = pDAO.getProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("products/productlist.jsp").include(request, response);
        }
        else{
            message= "product failed to updated";     
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("products?action="+ edit_action);
            dispatcher.forward(request, response);
        }
  
    }
 
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("product_id"));
 
        Product p = new Product();
        ProductDAO pDAO = new ProductDAO();
        p.setProduct_id(id);
        if(pDAO.removeProduct(id)){
            message= "product deleted";                    
        }
        else{
            message= "product was not deleted";                
        }    
        request.setAttribute("message", message);
        response.sendRedirect("products?action="+list_action); 
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
