/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.daos.ProductDAO;
import sample.dtos.ProductDTO;
import sample.dtos.RoleDTO;

/**
 *
 * @author Dung
 */
public class SearchProductController extends HttpServlet {

    private static final String ERROR = "errorPage.jsp";
    private static final String SUCCESS_CLIENT = "homePage.jsp";
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
        try {
            HttpSession session = request.getSession();
            String indexString = request.getParameter("index");
            int index = Integer.parseInt(indexString);
            String methodSearch = (String) session.getAttribute("SEARCH_METHOD");
            ProductDAO productDAO = new ProductDAO();
            String statusString = (String) session.getAttribute("PRODUCT_STATUS");
            int status = Integer.parseInt(statusString);
            int pageSize = 6;
            int count = 1;
            
            RoleDTO currentRole = (RoleDTO) session.getAttribute("CURRENT_ROLE");
            if (methodSearch == null && currentRole == null) {
                url = SUCCESS_CLIENT;
            }
            if (methodSearch == null && currentRole.getRoleName().equals("Customer")) {
                url = SUCCESS_CLIENT;
            }
            if (methodSearch == null && currentRole.getRoleName().equals("Admin")) {
                url = SUCCESS_ADMIN;
            }

            if (methodSearch.equals("Name")) {
                String txtSearchProductName = request.getParameter("txtSearchProductName");
                if (txtSearchProductName == null) {
                    txtSearchProductName = (String) session.getAttribute("SEARCH_PRODUCT_NAME");
                } else {
                    session.setAttribute("SEARCH_PRODUCT_NAME", txtSearchProductName);
                }
                count = productDAO.countPagingProductByName(status, txtSearchProductName);
                ArrayList<ProductDTO> productList = productDAO.searchProductsByName(txtSearchProductName, status, index, pageSize);
                session.setAttribute("SEARCH_RESULT", productList);
                if (currentRole == null || currentRole.getRoleName().equals("Customer")) {
                    url = SUCCESS_CLIENT;
                } else {
                    url = SUCCESS_ADMIN;
                }
            }
            if (methodSearch.equals("Money")) {
                String txtSearchProductMinMoney = request.getParameter("txtSearchProductMinMoney");
                String txtSearchProductMaxMoney = request.getParameter("txtSearchProductMaxMoney");
                float minMoney;
                float maxMoney;
                if (txtSearchProductMinMoney == null || txtSearchProductMaxMoney == null) {
                    txtSearchProductMinMoney = (String) session.getAttribute("SEARCH_PRODUCT_MIN_PRICE");
                    txtSearchProductMaxMoney = (String) session.getAttribute("SEARCH_PRODUCT_MAX_PRICE");
                    minMoney = Float.parseFloat(txtSearchProductMinMoney);
                    maxMoney = Float.parseFloat(txtSearchProductMaxMoney);
                } else {
                    minMoney = Float.parseFloat(txtSearchProductMinMoney);
                    maxMoney = Float.parseFloat(txtSearchProductMaxMoney);
                    session.setAttribute("SEARCH_PRODUCT_MIN_PRICE", txtSearchProductMinMoney);
                    session.setAttribute("SEARCH_PRODUCT_MAX_PRICE", txtSearchProductMaxMoney);
                }
                count = productDAO.countPagingProductByPrice(status, minMoney, maxMoney);
                ArrayList<ProductDTO> productList = productDAO.searchProductsByPrice(minMoney, maxMoney, status, index, pageSize);
                session.setAttribute("SEARCH_RESULT", productList);
                if (currentRole == null || currentRole.getRoleName().equals("Customer")) {
                    url = SUCCESS_CLIENT;
                } else {
                    url = SUCCESS_ADMIN;
                }
            }
            if (methodSearch.equals("Category")) {
                String txtSearchCategory = request.getParameter("cmbCategory");
                int category;

                if (txtSearchCategory == null) {
                    txtSearchCategory = (String) session.getAttribute("SEARCH_PRODUCT_CATEGORY");
                    category = Integer.parseInt(txtSearchCategory);
                } else {
                    category = Integer.parseInt(txtSearchCategory);
                    session.setAttribute("SEARCH_PRODUCT_CATEGORY", txtSearchCategory);
                }
                count = productDAO.countPagingProductByCategory(status, category);
                ArrayList<ProductDTO> productList = productDAO.searchProductsByCategory(category, status, index, pageSize);
                session.setAttribute("SEARCH_RESULT", productList);
                if (currentRole == null || currentRole.getRoleName().equals("Customer")) {
                    url = SUCCESS_CLIENT;
                } else {
                    url = SUCCESS_ADMIN;
                }
            }
            int endPage = count / pageSize;
            if (count % pageSize != 0) {
                endPage++;
            }
            session.setAttribute("endPage", endPage);
        } catch (Exception e) {
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
