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
import model.Person;

/**
 *
 * @author arissetyawan.email@gmail.com
 */
public class PeopleController extends HttpServlet {
    private final static String add_action = "new";
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
                if(action==null){
                    action= "list";
                }
                try {
                    switch (action) {
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "create":
                        createPerson(request, response);
                        break;
                    case "delete":
                        deletePerson(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "update":
                        updatePerson(request, response);
                        break;
                    default:
                        listPerson(request, response);
                        break;
                    }
                } 
            catch (SQLException ex) {
                throw new ServletException(ex);
            }
        }
    }
  
    private void listPerson(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Person p= new Person();
        List<Person> people = p.all();
        request.setAttribute("people", people);
        RequestDispatcher dispatcher = request.getRequestDispatcher("people/list.jsp");
        dispatcher.forward(request, response);
    }
    private void createPerson(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String profession = request.getParameter("profession");
 
        Person person = new Person();
        person.setName(name);
        person.setPhone(phone);
        person.setProfession(profession);
        if (person.create()){
            message= "new person added";                    
            request.setAttribute("message", message);
            response.sendRedirect("people?action="+list_action);
        }
        else{
            message= "new person failed to add";
            request.setAttribute("message", message);
            request.getRequestDispatcher("people?action="+add_action).include(request, response);
        }
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            RequestDispatcher dispatcher = request.getRequestDispatcher("people/new.jsp");
            dispatcher.forward(request, response);
        }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            Person person= new Person();
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("person", person.find(id));

            RequestDispatcher dispatcher = request.getRequestDispatcher("people/edit.jsp");
            dispatcher.forward(request, response);
        }
    
   private void updatePerson(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String profession = request.getParameter("profession");
 
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setPhone(phone);
        person.setProfession(profession);
        if (person.update()){
            message= "person updated";     
            request.setAttribute("message", message);
            List<Person> people = person.all();
            request.setAttribute("people", people);
            request.getRequestDispatcher("/people/list.jsp").include(request, response);
        }
        else{
            message= "person failed to updated";     
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("people?action="+ edit_action);
            dispatcher.forward(request, response);
        }
  
    }
 
    private void deletePerson(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
 
        Person person = new Person();
        person.setId(id);
        if(person.delete()){
            message= "person deleted";                    
        }
        else{
            message= "person was not deleted";                
        }    
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("people?action="+ list_action);
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
