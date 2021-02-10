/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.course;

import com.et.model.*;
import java.io.IOException;
// import java.io.PrintWriter;
import java.math.BigDecimal;
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
public class AddCourse extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String sem = request.getParameter("sem");
        String year = request.getParameter("year");

        Database db = new Database();
        ET_COUNTER_ADMIN_TABLE getAdminTable = new ET_COUNTER_ADMIN_TABLE(db);
        ET_COUNTER_ADMIN getCounterData = getAdminTable.findCounterData();

        if (request.getParameter("submititem") != null) {

            String[] itemsCourse = request.getParameterValues("cn");
            String[] itemsCradit = request.getParameterValues("crd");

            ET_COURSE_OPEN_TABLE getCourseTable = new ET_COURSE_OPEN_TABLE(db);
            List<ET_COURSE_OPEN> getCourseData = null;
            ET_COURSE_OPEN AddCourseOP = new ET_COURSE_OPEN();
            boolean isInsertData = false;

            for (int index = 0; index < itemsCourse.length; index++) {

                String courseno = itemsCourse[index];
                String credit = itemsCradit[index];
                String statusCourse = "O";

                boolean chkAddCourse = false;

                AddCourseOP.setYEAR(getCounterData.getSTUDY_YEAR());
                AddCourseOP.setSEMESTER(getCounterData.getSTUDY_SEMESTER());
                AddCourseOP.setCOURSE_NO(courseno);
                AddCourseOP.setCREDIT(new BigDecimal(credit));
                AddCourseOP.setSTATUS_COURSE(statusCourse);
                chkAddCourse = getCourseTable.insertCourse(AddCourseOP);

                if (chkAddCourse) {
                    db.commit();
                    isInsertData = true;
                } else {
                    db.rollback();
                    response.sendRedirect("admin/faild.jsp");
                }

            }

            if (isInsertData == true) {
                RequestDispatcher rs = request.getRequestDispatcher("/ShowCourse");
                rs.forward(request, response);
            } else {
                response.sendRedirect("admin/faild.jsp");
            }
        } else {
            request.setAttribute("getCounterData", getCounterData);
            RequestDispatcher rs = request.getRequestDispatcher("admin/AddCourse.jsp");
            rs.forward(request, response);
        }
        db.close();

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    } // </editor-fold>
}
