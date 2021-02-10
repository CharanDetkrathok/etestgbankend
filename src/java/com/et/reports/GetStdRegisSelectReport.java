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
public class GetStdRegisSelectReport extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //stmt
        String examdate = request.getParameter("examdate");
        String sec = request.getParameter("sec");
        String sem = request.getParameter("sem");
        String year = request.getParameter("year");

        Database db = new Database();
        ET_COUNTER_ADMIN_TABLE getAdminTable = new ET_COUNTER_ADMIN_TABLE(db);
        ET_COUNTER_ADMIN getCounterData = getAdminTable.findCounterData();

        // ET_COURSE_OPEN_TABLE getCourseOPTable = new ET_COURSE_OPEN_TABLE(db);
        // ET_EXAM_DATE_TABLE getExamDateTable = new ET_EXAM_DATE_TABLE(db);
        ET_RECEIPT_TABLE getRepcieptTable = new ET_RECEIPT_TABLE(db);

        List<ET_RECEIPT> getRecieptDataList = null;
        // ET_RECEIPT getRecieptData = null;

        if (examdate.equals("0") && sec.equals("0")) {
//            getRecieptDataList = getRepcieptTable.findAllReceipt(sem, year);
            getRecieptDataList = getRepcieptTable.findReceiptAllDateExamAllSecoet(sem, year);
            if (getRecieptDataList == null) {
                response.sendRedirect("admin/faild.jsp");
            }
        } else if (examdate.equals("0") && !sec.equals("0")) {
            getRecieptDataList = getRepcieptTable.findAllReceiptSelectSection(sem, year, sec);
            if (getRecieptDataList == null) {
                response.sendRedirect("admin/faild.jsp");
            }
        } else {
            if (!examdate.equals("0") && sec.equals("0")) {
                getRecieptDataList = getRepcieptTable.findBySelectDate(sem, year, examdate);
                if (getRecieptDataList == null) {
                    response.sendRedirect("admin/faild.jsp");
                }
            } else {
                getRecieptDataList = getRepcieptTable.findBySelectDateAndSection(sem, year, sec, examdate);
                if (getRecieptDataList == null) {
                    response.sendRedirect("admin/faild.jsp");
                }
            }
        }

        int cnt = 0;
        if (getRecieptDataList != null) {
            for (int i = 0; i < getRecieptDataList.size(); i++) {
                cnt++;
            }
        }
        request.setAttribute("getRecieptDataList", getRecieptDataList);
        request.setAttribute("getCounterData", getCounterData);
        request.setAttribute("cnt", cnt);
        request.setAttribute("sec", sec);
        request.setAttribute("examdate", examdate);
        RequestDispatcher rs = request.getRequestDispatcher("admin/ShowStdRegisFromSelect.jsp");
        rs.forward(request, response);

        db.close();

        /*  PrintWriter out = response.getWriter();
        try {
             TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetStdRegisSelectReport</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetStdRegisSelectReport at " + request.getContextPath() + "</h1>");
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
