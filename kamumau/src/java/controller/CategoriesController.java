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
import model.Category;



/**
 *
 * @author x201
 */
public class CategoriesController extends HttpServlet {
    
    private final static String add_action = "new";
    private final static String delete_action = "delete";
    private final static String edit_action = "edit";
    private final static String list_action = "list";
    private final static String search_action = "search";
    public String message="";

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
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "create":
                        createCategory(request, response);
                        break;
                    case "delete":
                        deleteCategory(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "update":
                        updateCategory(request, response);
                        break;
                    case "search":
                        searchCategory(request, response);
                        break;
                    default:
                        listCategories(request, response);
                        break;
                    }
                } 
            catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }
    }

    
    private void listCategories(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Category c= new Category();
        List<Category> categories = c.all();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("categories/list.jsp");
        dispatcher.forward(request, response);
        
    }
    
    private void searchCategory (HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Category c= new Category();
        String keyword = request.getParameter("keyword");
        System.out.println("ikhwanudin"+keyword);
        List<Category> categories = c.search(keyword);
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("categories/search.jsp");
        dispatcher.forward(request, response);
        
    }
    
       private void updateCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int category_id = Integer.parseInt(request.getParameter("category_id"));
        String description = request.getParameter("description");
 
        Category category = new Category();
        category.setId(id);
        category.setName(name);
        category.setParentId(category_id);
        category.setDescription(description);
        
        if (category.update()){
            message= "category updated";     
            request.setAttribute("message", message);
            List<Category> categories = category.all();
            request.setAttribute("category", categories);
            request.getRequestDispatcher("categories?action=list").include(request, response);
        }
        else{
            message= "category failed to updated";     
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("categories?action="+ edit_action);
            dispatcher.forward(request, response);
        }
  
    }
       
       private void showEditForm(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            Category category= new Category();
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("categories", category.find(id));

            RequestDispatcher dispatcher = request.getRequestDispatcher("categories/edit.jsp");
            dispatcher.forward(request, response);
        }
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("categories/new.jsp");
            dispatcher.forward(request, response);
        }
      private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Category category = new Category();
        category.setId(id);
        if(category.delete()){
            message= "category deleted";                    
        }
        else{
            message= "category was not deleted";                
        }    
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("categories?action="+ list_action);
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
    private void createCategory(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        int category_id= Integer.parseInt(request.getParameter("category_id"));
        String description = request.getParameter("description");
 
        Category category = new Category();
        category.setName(name);
        category.setParentId(category_id);
        category.setDescription(description);
        if (category.create()){
            message= "new category added";                    
            request.setAttribute("message", message);
            response.sendRedirect("categories?action="+list_action);
        }
        else{
            message= "new category failed to add";
            request.setAttribute("message", message);
            request.getRequestDispatcher("categories?action="+add_action).include(request, response);
        }
    }
}
