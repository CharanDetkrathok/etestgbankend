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
public class GetSelectStudentRegis extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //stmt
        String stdcode = request.getParameter("stdcode");
        // String refkey = request.getParameter("refkey");
        String sem = request.getParameter("sem");
        String year = request.getParameter("year");
        String srcVal = request.getParameter("srcVal");

        //-- values set
        int cnt = 0;
        String name = "";
        String registdate = "";

        Database db = new Database();
        ET_COUNTER_ADMIN_TABLE getAdminTable = new ET_COUNTER_ADMIN_TABLE(db);
        ET_COUNTER_ADMIN getCounterData = getAdminTable.findCounterData();

        // ET_REGIS_RU24_TABLE getRu24Table = new ET_REGIS_RU24_TABLE(db);
        ET_RECEIPT_TABLE getRecieptTable = new ET_RECEIPT_TABLE(db);

        List<ET_RECEIPT> getRecieptDataList = null;
        // ET_RECEIPT getRecieptData = null;

        if (stdcode != null && stdcode != null && stdcode != null ) {
            if (srcVal.equals("1")) {
                getRecieptDataList = getRecieptTable.findReceiptSelectStdApproove(sem, year, stdcode);

            } else {                
                 getRecieptDataList = getRecieptTable.findReceiptSelectStdAll(sem, year, stdcode);
            }

            if (getRecieptDataList != null) {

                for (ET_RECEIPT e : getRecieptDataList) {
                    name = e.getNAME_THAI();
                    registdate = e.getREGIS_DATE();
                    cnt++;
                }

                request.setAttribute("getCounterData", getCounterData);
                request.setAttribute("getRecieptDataList", getRecieptDataList);
                //request.setAttribute("cnt", cnt);
                request.setAttribute("stdcode", stdcode);
                request.setAttribute("year", sem);
                request.setAttribute("year", year);
                request.setAttribute("cnt", cnt);
                request.setAttribute("name", name);
                request.setAttribute("registdate", registdate);
                RequestDispatcher rs = request.getRequestDispatcher("admin/GetShowStdRegisReport.jsp");
                rs.forward(request, response);
            } else {
                response.sendRedirect("admin/faild.jsp");
            }
        } else {
            response.sendRedirect("admin/faild.jsp");
        }

        db.close();

        /*   PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetSelectStudentRegis</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetSelectStudentRegis at " + request.getContextPath() + "</h1>");
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
