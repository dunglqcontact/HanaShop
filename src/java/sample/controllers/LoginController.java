/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.daos.RoleDAO;
import sample.daos.UserDAO;
import sample.dtos.RoleDTO;
import sample.dtos.UserDTO;
import sample.dtos.UserDTOError;

/**
 *
 * @author Dung
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "loginPage.jsp";
    private static final String SUCCESS_USER = "homePage.jsp";
    private static final String SUCCESS_ADMIN = "adminPage.jsp";

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
        String url = ERROR;
        HttpSession session = request.getSession();
        try {
            UserDTOError errorDTO = new UserDTOError();
            String userID = request.getParameter("txtUserID");
            String password = request.getParameter("txtPassword");

            boolean check = true;
            if (userID.isEmpty()) {
                errorDTO.setUserIDError("UserID is not empty");
                check = false;
            }

            if (password.isEmpty()) {
                errorDTO.setPasswordError("Password is not empty");
                check = false;
            }
            if (check) {
                UserDAO userDAO = new UserDAO();
                UserDTO crtUser = userDAO.login(userID, password);
                if (crtUser == null) {
                    errorDTO.setLoginFailed("Login Failed, try again");
                    request.setAttribute("LOGIN_ERROR", errorDTO);
                } else {
                    session.setAttribute("CURRENT_USER", crtUser);
                    RoleDAO roleDAO = new RoleDAO();
                    RoleDTO currRole = roleDAO.getUserRole(crtUser.getRoleID());
                    session.setAttribute("CURRENT_ROLE", currRole);
                    if (crtUser.getRoleID() == 1) {
                        url = SUCCESS_ADMIN;
                    }
                    if (crtUser.getRoleID() == 2) {
                        url = SUCCESS_USER;
                    }
                }
            } else {
                request.setAttribute("LOGIN_ERROR", errorDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
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
