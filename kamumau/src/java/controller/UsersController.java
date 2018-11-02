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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author x201
 */
@WebServlet(name = "UsersController", urlPatterns = {"/users"})
public class UsersController extends ApplicationController {
    private final static String add_action ="new";
    private final static String login_action = "login";
    private final static String logout_action = "logout";
    private final static String list_action = "list";
    private final static String Update_action ="update";
    private String message="";

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
        String action = request.getParameter("action");
        if(action==null){
            action= "login";
        }
        try{
        switch (action) {
            case "new":
                ShowNewForm(request, response);
                break;
            case "create":
                createUser(request, response);
                break;
            case "logout":
                doLogout(request, response);
                break;
            case "do-login":
                doLogin(request, response);
                break;
            case "welcome":
                mustLoggedIn(request, response);
                showWelcomePage(request, response);
                break;
            case "profile":
                mustLoggedIn(request, response);
                showProfileForm(request, response);
                break;
                
            case "update":
                mustLoggedIn(request, response);
                updateProfile(request, response);
            default:
                showLoginForm(request, response);
                break;
            }
        }
        catch (SQLException ex){
            throw new ServletException(ex);
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException, ServletException  {
                String email= request.getParameter("email");
                String passwd= request.getParameter("password");
                User user= new User();     
                user.setEmail(email);
                System.out.println("Isi email : " + email + " passwordnya : "+ passwd);
                
                user.setPassword(passwd);
                logout(request, response);
                if(user.doLogin()){
                    setLoggedIn(user, request, response);
                    showWelcomePage(request, response);
                    System.out.println("Login berhasil.");
                    
                }else{
                    showLoginFail(request, response);
                    System.out.println("Login gagal.");
                }
        }
    private void doLogout(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException, ServletException  {
                logout(request, response);
                showLoginForm(request, response);
        }
    private void showLoginForm(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException, ServletException  {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/users/login.jsp");
            dispatcher.forward(request, response);
        }
    private void showWelcomePage(HttpServletRequest request, HttpServletResponse response)    throws ServletException, IOException, ServletException  {
            RequestDispatcher dispatcher = request.getRequestDispatcher("users/welcome.jsp");
            dispatcher.forward(request, response);
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

    private void ShowNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("users/signup.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showProfileForm(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        HttpSession session = request.getSession();
        String me = session.getAttribute("current_user").toString();
        User user = new User();
        user = user.find(Integer.parseInt(me));
        request.setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("users/profile.jsp");
        dispatcher.forward(request, response);
    }

    private void createUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException,ServletException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String retpassword = request.getParameter("retpassword");
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String bankname = request.getParameter("bankname");
        String accountno = request.getParameter("accountno");
        String created_at = request.getParameter("created_at");
        
        User user= new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRetpassword(retpassword);
        user.setFullname(fullname);
        user.setAddress(address);
        user.setBankname(bankname);
        user.setAccountno(accountno);
        
        if(user.create()){
            message="new user added";
            request.setAttribute("message", message);
            response.sendRedirect("users?action="+add_action);
        }else{
            message = "new user failed to add";
            request.setAttribute("message", message);
            request.getRequestDispatcher("users?action="+add_action).include(request, response);
        }
    }

    private void showLoginFail(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException, ServletException  {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/users/welcome.jsp");
            dispatcher.forward(request, response);
    }
    
        private void updateProfile(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException,ServletException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String retpassword = request.getParameter("retpassword");
        String fullname = request.getParameter("fullname");
        String address = request.getParameter("address");
        String bankname = request.getParameter("bankname");
        String accountno = request.getParameter("accountno");
        String created_at = request.getParameter("created_at");
        
        HttpSession session = request.getSession();
        String me = session.getAttribute("current_user").toString();
        
        User user= new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRetpassword(retpassword);
        user.setFullname(fullname);
        user.setAddress(address);
        user.setBankname(bankname);
        user.setAccountno(accountno);
        user.setId(Integer.parseInt(me));
        if(user.update()){
            message="update";
            request.setAttribute("message", message);
            response.sendRedirect("users?action="+Update_action);
        }else{
            message = " user failed to update";
            request.setAttribute("message", message);
            request.getRequestDispatcher("users?action="+Update_action).include(request, response);
        }
    }

    
}
    

