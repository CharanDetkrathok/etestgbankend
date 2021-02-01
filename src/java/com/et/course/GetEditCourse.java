/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.course;

import com.et.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ru-com7
 */
@SuppressWarnings("unused")
public class GetEditCourse extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String courseno = request.getParameter("courseno");
        String sem = request.getParameter("sem");
        String year = request.getParameter("year");

        Database db = new Database();
        ET_COURSE_OPEN_TABLE getCourseTable = new ET_COURSE_OPEN_TABLE(db);
        ET_COUNTER_ADMIN_TABLE getAdminTable = new ET_COUNTER_ADMIN_TABLE(db);
        ET_COUNTER_ADMIN getCounterData = getAdminTable.findCounterData();

        if (getCounterData != null && courseno != null) {
            request.setAttribute("courseno", courseno);
            request.setAttribute("getCounterData", getCounterData);
            RequestDispatcher rs = request.getRequestDispatcher("admin/EditCourse.jsp");
            rs.forward(request, response);
        } else {
            response.sendRedirect("admin/faild.jsp");
        }
        /*
         * PrintWriter out = response.getWriter(); try { TODO output your page here. You
         * may use following sample code. out.println("<!DOCTYPE html>");
         * out.println("<html>"); out.println("<head>");
         * out.println("<title>Servlet GetEditCourse</title>"); out.println("</head>");
         * out.println("<body>"); out.println("<h1>Servlet GetEditCourse at " +
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
