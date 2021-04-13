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
import javax.servlet.http.Part;
import sample.daos.ProductDAO;
import sample.dtos.ProductDTOError;
import sample.dtos.RoleDTO;

/**
 *
 * @author Dung
 */
public class InsertNewProductController extends HttpServlet {

    private static final String ERROR = "insertNewProductPage.jsp";
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
                if (currentRole.getRoleName().equals("Admin")) {
                    boolean check = true;
                    ProductDTOError error = new ProductDTOError();
                    String productName = request.getParameter("txtProductName");
                    String description = request.getParameter("txtDescription");
                    String priceString = request.getParameter("txtPrice");
                    float price = 0;
                    try {
                        price = Float.parseFloat(priceString);
                        if (price <= 0) {
                            check = false;
                            error.setPriceError("Price must be larger than 0");
                        }
                    } catch (Exception e) {
                        error.setPriceError("Price must be a float number");
                        check = false;
                    }
                    Date dateobj = new Date();
                    int categoryID = Integer.parseInt(request.getParameter("cmbCategory"));
                    String quantityString = request.getParameter("txtQuantity");
                    int quantity = 0;
                    try {
                        quantity = Integer.parseInt(quantityString);
                        if (quantity <= 0) {
                            check = false;
                            error.setCategoryIDError("Quantity must be larger than 0");
                        }
                    } catch (Exception e) {
                        error.setQuantityError("Quantity must be a number");
                        check = false;
                    }
                    Part imagePart = request.getPart("file");
                    if (imagePart == null) {
                        check = false;
                        error.setImageDataError("Image must not empty");
                    }
                    boolean status = true;

                    if (productName.isEmpty()) {
                        error.setProductNameError("Product Name is not empty");
                        check = false;
                    }

                    if (description.isEmpty()) {
                        error.setDescriptionError("Description is not empty");
                        check = false;
                    }

                    if (priceString.isEmpty()) {
                        error.setPriceError("Price is not empty");
                        check = false;
                    }

                    if (quantityString.isEmpty()) {
                        error.setQuantityError("Quantity is not empty");
                        check = false;
                    }
                    if (check) {
                        ProductDAO productDAO = new ProductDAO();
                        productDAO.insertNewProduct(productName, description, price, dateobj, categoryID, quantity, imagePart, status);
                        url = SUCCESS;
                    } else {
                        request.setAttribute("INSERT_PRODUCT_ERROR", error);
                    }
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
