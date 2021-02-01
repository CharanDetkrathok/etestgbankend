/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.course;

import com.et.model.*;
import java.io.IOException;
import java.io.PrintWriter;
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
@SuppressWarnings("unused")
public class ShowCourse extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // response.setContentType("text/html;charset=UTF-8");

        int cnt = 0;
        Database db = new Database();

        ET_COURSE_OPEN_TABLE getCourseTable = new ET_COURSE_OPEN_TABLE(db);
        ET_COUNTER_ADMIN_TABLE getAdminTable = new ET_COUNTER_ADMIN_TABLE(db);
        ET_COUNTER_ADMIN getCounterData = getAdminTable.findCounterData();
        List<ET_COURSE_OPEN> getCourseData = null;

        if (getCounterData != null) {
            getCourseData = getCourseTable.findBySemAndYear(getCounterData.getSTUDY_SEMESTER(),
                    getCounterData.getSTUDY_YEAR());
            if (getCourseData != null) {
                for (ET_COURSE_OPEN e : getCourseData) {
                    cnt++;
                }
                request.setAttribute("getCourseData", getCourseData);
                request.setAttribute("getCounterData", getCounterData);
                request.setAttribute("cnt", cnt);
                RequestDispatcher rs = request.getRequestDispatcher("admin/ShowCourse.jsp");
                rs.forward(request, response);
            } else {
                response.sendRedirect("admin/faild.jsp");
            }

        } else {
            response.sendRedirect("admin/faild.jsp");

        }

        db.close();

        /*
         * PrintWriter out = response.getWriter(); try { TODO output your page here. You
         * may use following sample code. out.println("<!DOCTYPE html>");
         * out.println("<html>"); out.println("<head>");
         * out.println("<title>Servlet ShowCourse</title>"); out.println("</head>");
         * out.println("<body>"); out.println("<h1>Servlet ShowCourse at " +
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
