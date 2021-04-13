/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dung
 */
@MultipartConfig
public class MainController extends HttpServlet {

    private static final String ERROR = "errorPage.jsp";
    private static final String REGISTER_AS_CUSTOMER = "RegisterAsCustomerController";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    private static final String INSERT_NEW_PRODUCT_PARAM_TRANSFORM = "InsertNewProductParamTransformController";
    private static final String INSERT_NEW_PRODUCT = "InsertNewProductController";
    private static final String SEARCH_PRODUCT = "SearchProductController";
    private static final String CONFIRM_SEARCH_METHOD = "ConfirmSearchMethodController";
    private static final String DELETE_PRODUCT = "DeleteProductController";
    private static final String UPDATE_PRODUCT_PAGE = "UpdateProductPageController";
    private static final String UPDATE_PRODUCT = "UpdateProductController";
    private static final String ADD_TO_CART = "AddToCartController";
    private static final String REMOVE = "RemoveController";
    private static final String UPDATE = "UpdateCartController";
    private static final String ORDER = "OrderController";
    private static final String HISTORY = "HistoryController";
    private static final String VIEW_HISTORY_DETAIL = "ViewHistoryDetailController";
    private static final String SEARCH_HISTORY = "SearchHistoryController";
    private static final String CONFIRM_SEARCH_HISTORY_METHOD = "ConfirmSearchHistoryMethodController";

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
        try {
            String action = request.getParameter("btnAction");
            if (action.equals("Register")) {
                url = REGISTER_AS_CUSTOMER;
            }
            if (action.equals("Login")) {
                url = LOGIN;
            }
            if (action.equals("Logout")) {
                url = LOGOUT;
            }
            if (action.equals("InsertProductParamTransform")) {
                url = INSERT_NEW_PRODUCT_PARAM_TRANSFORM;
            }
            if (action.equals("Insert New Product")) {
                url = INSERT_NEW_PRODUCT;
            }
            if (action.equals("Search Product")) {
                url = SEARCH_PRODUCT;
            }
            if (action.equals("Confirm Search Method")) {
                url = CONFIRM_SEARCH_METHOD;
            }
            if (action.equals("Delete Product")) {
                url = DELETE_PRODUCT;
            }
            if (action.equals("Update Product Page")) {
                url = UPDATE_PRODUCT_PAGE;
            }
            if (action.equals("Update Product")) {
                url = UPDATE_PRODUCT;
            }
            if (action.equals("Add to Cart")) {
                url = ADD_TO_CART;
            }
            if (action.equals("Remove")) {
                url = REMOVE;
            }
            if (action.equals("Update")) {
                url = UPDATE;
            }
            if (action.equals("Order")) {
                url = ORDER;
            }
            if (action.equals("History")) {
                url = HISTORY;
            }
            if (action.equals("ViewHistoryDetail")) {
                url = VIEW_HISTORY_DETAIL;
            }
            if (action.equals("ViewHistoryDetail")) {
                url = VIEW_HISTORY_DETAIL;
            }
            if (action.equals("Search History")) {
                url = SEARCH_HISTORY;
            }
            if (action.equals("Confirm Search Order Method")) {
                url = CONFIRM_SEARCH_HISTORY_METHOD;
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
