package com.et.control.mangement;

import com.et.model.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReceiptManagementDetail extends HttpServlet {

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
        
        String Year = request.getParameter("receiptYear");
        String Semester = request.getParameter("receiptSemester");
        String StdCode = request.getParameter("receiptStdCode");
        String RefKey = request.getParameter("refKey");
        
        List<ET_RECEIPT> ReceiptData = getReceiptTable.findReceiptDetail(Year, Semester, StdCode, RefKey);
//        System.out.println(ReceiptData);
        String name = "";
        String date = "";
        String stdcCode = "";
        for (ET_RECEIPT et_receipt : ReceiptData) {
            name = et_receipt.getNAME_THAI();
            date = et_receipt.getREGIS_DATE();
            stdcCode = et_receipt.getSTD_CODE();
        }
        request.setAttribute("name", name);
        request.setAttribute("date", date);
        request.setAttribute("stdcCode", stdcCode);
        request.setAttribute("ReceiptData", ReceiptData);
        RequestDispatcher rs = request.getRequestDispatcher("admin/Receipt-Management-Detail.jsp");
        rs.forward(request, response);

        db.close();

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
