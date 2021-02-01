/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.reports;

import com.et.model.*;
import java.io.IOException;
// import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ru-com7
 */
public class GetRegisReport extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Database db = new Database();
        ET_COUNTER_ADMIN_TABLE getAdminTable = new ET_COUNTER_ADMIN_TABLE(db);
        // ET_COURSE_OPEN_TABLE getCourseOPTable = new ET_COURSE_OPEN_TABLE(db);
       // ET_EXAM_DATE_TABLE getExamDateTable = new ET_EXAM_DATE_TABLE(db);
        // ET_REGIS_RU24_TABLE getEtRu24Table = new ET_REGIS_RU24_TABLE(db);
        // ET_RECEIPT_TABLE getRepTable = new ET_RECEIPT_TABLE(db);
        QR_PAYMENT_CONFIRM_TABLE_TMB getReptmbTable = new QR_PAYMENT_CONFIRM_TABLE_TMB(db);

        // ET_COURSE_OPEN getEtCourseOpData = null;
        //List<ET_EXAM_DATE> getExamDate = getExamDateTable.findExamDateReport();
        // ET_REGIS_RU24 getRu24 = null;
        // ET_RECEIPT getEtRepData = null;
        List<QR_PAYMENT_CONFIRM>  getTMBData = getReptmbTable.findPaymentDateReport();
        
        

        ET_COUNTER_ADMIN getCounterData = getAdminTable.findCounterData();

        if (getCounterData != null) {
            request.setAttribute("getCounterData", getCounterData);
            request.setAttribute("getTMBData", getTMBData);
            RequestDispatcher rs = request.getRequestDispatcher("admin/ShowStdRegisSelectReport.jsp");
            rs.forward(request, response);
        } else {
            response.sendRedirect("admin/faild.jsp");

        }

        db.close();

        /* PrintWriter out = response.getWriter();
        try {
            TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetRegisReport</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetRegisReport at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }*/
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
