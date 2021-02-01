/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.control;

import com.et.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
public class EditCounter extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String fiscalyear = request.getParameter("fiscalyear");
        String year = request.getParameter("year");
        String sem = request.getParameter("sem");

        Database db = new Database();

        ET_COUNTER_ADMIN_TABLE getAdminTable = new ET_COUNTER_ADMIN_TABLE(db);
        ET_COUNTER_ADMIN AddeditCounter = new ET_COUNTER_ADMIN();
        boolean checkAddCounter = false;

        if (!fiscalyear.equals(null)) {
            AddeditCounter.setFISCAL_YEAR(fiscalyear);
            AddeditCounter.setSTUDY_YEAR(year);
            AddeditCounter.setSTUDY_SEMESTER(sem);
            checkAddCounter = getAdminTable.update(AddeditCounter);

            if (checkAddCounter) {
                db.commit();
                ET_COUNTER_ADMIN getCounterData = getAdminTable.findCounterData();
                request.setAttribute("getCounterData", getCounterData);
                RequestDispatcher rs = request.getRequestDispatcher("admin/ShowsCounterAdmin.jsp");
                rs.forward(request, response);
            } else {
                db.rollback();
                response.sendRedirect("admin/faild.jsp");
            }
        }

        db.close();

        /*
         * PrintWriter out = response.getWriter(); try { out.println("<!DOCTYPE html>");
         * out.println("<html>"); out.println("<head>");
         * out.println("<title>Servlet EditCounter</title>"); out.println("</head>");
         * out.println("<body>"); out.println("<h1>Servlet EditCounter at " +
         * request.getContextPath() + "</h1>"); out.println("</body>");
         * out.println("</html>"); } finally { out.close(); }
         */
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
