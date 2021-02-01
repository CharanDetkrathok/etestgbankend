package com.et.control.mangement;

import com.et.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
public class ReceiptManagementUpdate extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Database db = new Database();

        // ----- Query วัน/เดือน/ปี และภาคการศึกษา เพื่อไปแสดง -------------------------
        ET_COUNTER_ADMIN_TABLE getAdminTable = new ET_COUNTER_ADMIN_TABLE(db);
        ET_COUNTER_ADMIN getCounterData = getAdminTable.findCounterData();

        // ----- Query สถานะการจ่ายเงิน เพื่อไปแสดง ----------------------------------
        ET_RECEIPT_TABLE getReceiptTable = new ET_RECEIPT_TABLE(db);

        // ----- วัน/เดือน/ปี และภาคการศึกษา เพื่อไปแสดง -------------------------------
        request.setAttribute("getCounterData", getCounterData);

        String receiptStdCode = request.getParameter("receiptStdCode");
        String receiptYear = request.getParameter("receiptYear");
        String receiptSemester = request.getParameter("receiptSemester");
        String receiptPayStatus = request.getParameter("receiptPayStatus");
        String refKey = request.getParameter("refKey");

        // ----- สถานะการจ่ายเงิน เพื่อไปแสดง ----------------------------------------
        ET_RECEIPT ReceiptData = getReceiptTable.findRefKey(receiptStdCode, receiptYear, receiptSemester,
                receiptPayStatus, refKey);
        request.setAttribute("ReceiptData", ReceiptData);

        boolean checkUpdate;

        if (receiptPayStatus.equals("1")) {

            checkUpdate = getReceiptTable.updateReceiptPayStatus(ReceiptData, "0");

        } else {
            checkUpdate = getReceiptTable.updateReceiptPayStatus(ReceiptData, "1");
        }

        request.setAttribute("year", request.getParameter("year"));
        request.setAttribute("sem", request.getParameter("sem"));
        request.setAttribute("examdate", request.getParameter("examdate"));
        request.setAttribute("section", request.getParameter("section"));
        request.setAttribute("ReceiptData", ReceiptData);

        RequestDispatcher rs = request.getRequestDispatcher("ReceiptManagementData");
        rs.forward(request, response);
        db.close();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
