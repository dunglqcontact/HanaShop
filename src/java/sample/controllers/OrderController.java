/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.daos.OrderDAO;
import sample.dtos.OrderDTO;
import sample.dtos.OrderDetailDTO;
import sample.dtos.RoleDTO;
import sample.dtos.UserDTO;

/**
 *
 * @author Dung
 */
public class OrderController extends HttpServlet {

    private static final String ERROR = "errorPage.jsp";
    private static final String SUCCESS = "SearchProductController";
    private static final String LOGIN = "loginPage.jsp";

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
            RoleDTO currentRole = (RoleDTO) session.getAttribute("CURRENT_ROLE");
            if (currentRole != null) {
                if (currentRole.getRoleName().equals("Customer")) {
                    UserDTO currentUser = (UserDTO) session.getAttribute("CURRENT_USER");
                    OrderDAO orderDAO = new OrderDAO();
                    Date date = new Date();
                    orderDAO.createAnOrder(date, currentUser.getUserID());
                    OrderDTO currentOrder = orderDAO.getNewestOrder(currentUser.getUserID());
                    OrderDetailDTO orderDetail = (OrderDetailDTO) session.getAttribute("ORDER_DETAIL");
                    orderDAO.insertOrderDetails(currentOrder.getOrderID(), orderDetail);
                    float total = Float.parseFloat(request.getParameter("txtTotal"));
                    orderDAO.setOrderTotalPrice(currentOrder.getOrderID(), total);

                    orderDetail = null;
                    session.setAttribute("ORDER_DETAIL", orderDetail);
                    url = SUCCESS;
                } else {
                    url = LOGIN;
                }
            } else {
                url = LOGIN;
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
