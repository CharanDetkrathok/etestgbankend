/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.control;

import com.et.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
public class GenerateSeat extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // stmt
        String examdate = "0";// request.getParameter("examdate");
        String sec = "0";// request.getParameter("sec");
        String year = request.getParameter("year");
        String sem = request.getParameter("sem");

        // df
        ArrayList<String> rowSeat = new ArrayList<String>();
        ArrayList<String> ExamDate = new ArrayList<String>();
        ArrayList<String> sectionT = new ArrayList<String>();
        ArrayList<String> tmpnumRowSeat = new ArrayList<String>();
        ArrayList<String> tmpStdnumSeat = new ArrayList<String>();
        ArrayList<String> tmpStdCourse = new ArrayList<String>();
        ArrayList<String> tmpStdCradit = new ArrayList<String>();
        // ArrayList<String> tmpStatusCourse = new ArrayList<String>();
        ArrayList<String> tmpgetBuildRow = null;
        int stdcount = 1;
        int chkrow = 0;
        int chkSection = 4;

        // csll db
        Database db = new Database();
        ET_EXAM_SEAT_TABLE getExamSeatTable = new ET_EXAM_SEAT_TABLE(db);
        ET_COURSE_OPEN_TABLE getCourseOPTable = new ET_COURSE_OPEN_TABLE(db);
        ET_EXAM_DATE_TABLE getExamDateTable = new ET_EXAM_DATE_TABLE(db);
        ET_BUILE_ROW_TABLE getBuildRowTable = new ET_BUILE_ROW_TABLE(db);
        ET_REGIS_RU24_TABLE getRu24Table = new ET_REGIS_RU24_TABLE(db);
        ET_ROW_SEAT_ORDER_TABLE getRowSeatOrderTable = new ET_ROW_SEAT_ORDER_TABLE(db);
        List<ET_REGIS_RU24> getRu24Std = null;
        ET_ROW_SEAT_ORDER AddSeatOrder = new ET_ROW_SEAT_ORDER();
        ET_ROW_SEAT_ORDER DelSeatOrder = new ET_ROW_SEAT_ORDER();
        int chkInsert = 0;
        boolean chkAddSeatOrder = false;
        boolean chkDelSeatOrder = false;
        List<ET_BUILE_ROW> getBuildRow = getBuildRowTable.findAll();

        if (sec.equals("0")) {

            for (int j = 0; j < chkSection; j++) {

                chkrow = 0;
                stdcount = 1;
                String tmpSec = Integer.toString(j + 1);
                tmpnumRowSeat.clear();
                tmpStdnumSeat.clear();
                tmpStdCradit.clear();
                tmpStdCourse.clear();
                sectionT.clear();
                getRu24Std = null;
                chkInsert = getRowSeatOrderTable.countStudentToSetSeatRowOrder(year, sem, examdate, tmpSec);
                if (chkInsert > 0) {
                    chkDelSeatOrder = getRowSeatOrderTable.chkdeleteToSetSeatRowOrder(year, sem, examdate, tmpSec);
                    db.commit();
                }
                getRu24Std = getRu24Table.findBySelectDateAndSection(year, sem, examdate, tmpSec);

                for (ET_REGIS_RU24 et_regis_ru24 : getRu24Std) {
                    // tmpgetBuildRow = getBuildRow.get(chkrow);
                    if (stdcount > getBuildRow.get(chkrow).getSEAT_EXAM().intValue()) {
                        chkrow++;
                        stdcount = 1;
                    }
                    tmpnumRowSeat.add(getBuildRow.get(chkrow).getROW_EXAM() + (stdcount));
                    tmpStdnumSeat.add(et_regis_ru24.getSTD_CODE());
                    sectionT.add(et_regis_ru24.getSECTION_NO().toString());
                    tmpStdCradit.add(et_regis_ru24.getCREDIT().toString());
                    tmpStdCourse.add(et_regis_ru24.getCOURSE_NO());
                    ExamDate.add(et_regis_ru24.getEXAM_DATE());
                    stdcount++;
                }
                if (tmpnumRowSeat != null) {

                    // add db
                    for (int i = 0; i < tmpnumRowSeat.size(); i++) {
                        AddSeatOrder.setYEAR(year);
                        AddSeatOrder.setSEMESTER(sem);
                        AddSeatOrder.setROW_SEAT(tmpnumRowSeat.get(i));
                        AddSeatOrder.setSTD_CODE(tmpStdnumSeat.get(i));
                        AddSeatOrder.setEXAM_DATE(ExamDate.get(i));
                        AddSeatOrder.setSECTION_NO(sectionT.get(i));
                        AddSeatOrder.setCREDIT(new BigDecimal(tmpStdCradit.get(i)));
                        AddSeatOrder.setCOURSE_NO(tmpStdCourse.get(i));
                        AddSeatOrder.setSTATUS_COURSE("O");
                        chkAddSeatOrder = getRowSeatOrderTable.insert(AddSeatOrder);
                        if (chkAddSeatOrder) {
                            db.commit();
                        } else {
                            db.rollback();
                        }
                    }

                } else {
                    RequestDispatcher rs = request.getRequestDispatcher("admin/faild.jsp");
                    rs.forward(request, response);
                }
            }

        } else {
            chkInsert = getRowSeatOrderTable.countStudentToSetSeatRowOrder(year, sem, examdate, sec);
            if (chkInsert > 0) {
                chkDelSeatOrder = getRowSeatOrderTable.chkdeleteToSetSeatRowOrder(year, sem, examdate, sec);
                db.commit();
            }
            getRu24Std = getRu24Table.findBySelectDateAndSection(year, sem, examdate, sec);
            for (ET_REGIS_RU24 et_regis_ru24 : getRu24Std) {
                // tmpgetBuildRow = getBuildRow.get(chkrow);
                if (stdcount > getBuildRow.get(chkrow).getSEAT_EXAM().intValue()) {
                    chkrow++;
                    stdcount = 1;
                }
                tmpnumRowSeat.add(getBuildRow.get(chkrow).getROW_EXAM() + (stdcount));
                tmpStdnumSeat.add(et_regis_ru24.getSTD_CODE());
                sectionT.add(et_regis_ru24.getSECTION_NO().toString());
                tmpStdCradit.add(et_regis_ru24.getCREDIT().toString());
                tmpStdCourse.add(et_regis_ru24.getCOURSE_NO());
                ExamDate.add(et_regis_ru24.getEXAM_DATE());
                stdcount++;
            }
            if (tmpnumRowSeat != null) {

                // add db
                for (int i = 0; i < tmpnumRowSeat.size(); i++) {
                    AddSeatOrder.setYEAR(year);
                    AddSeatOrder.setSEMESTER(sem);
                    AddSeatOrder.setROW_SEAT(tmpnumRowSeat.get(i));
                    AddSeatOrder.setSTD_CODE(tmpStdnumSeat.get(i));
                    AddSeatOrder.setEXAM_DATE(ExamDate.get(i));
                    AddSeatOrder.setSECTION_NO(sectionT.get(i));
                    AddSeatOrder.setCREDIT(new BigDecimal(tmpStdCradit.get(i)));
                    AddSeatOrder.setCOURSE_NO(tmpStdCourse.get(i));
                    AddSeatOrder.setSTATUS_COURSE("O");
                    chkAddSeatOrder = getRowSeatOrderTable.insert(AddSeatOrder);
                    if (chkAddSeatOrder) {
                        db.commit();
                    } else {
                        db.rollback();
                    }
                }

            } else {
                RequestDispatcher rs = request.getRequestDispatcher("admin/faild.jsp");
                rs.forward(request, response);
            }
        }

        // request.setAttribute("tmpnumRowSeat", tmpnumRowSeat);
        // request.setAttribute("getRu24Std", getRu24Std);
        // request.setAttribute("getBuildRow", getBuildRow);
        // RequestDispatcher rs =
        // request.getRequestDispatcher("admin/ShowSeatDetail.jsp");
        // rs.forward(request, response);
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('ทำการสร้างข้อมูลเรียบร้อย');");
        out.println("location='ExportETRU25et';");
        out.println("</script>");

        db.close();
        /*
         * PrintWriter out = response.getWriter(); try { TODO output your page here. You
         * may use following sample code. out.println("<!DOCTYPE html>");
         * out.println("<html>"); out.println("<head>");
         * out.println("<title>Servlet GenerateSeat</title>"); out.println("</head>");
         * out.println("<body>"); out.println("<h1>Servlet GenerateSeat at " +
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
