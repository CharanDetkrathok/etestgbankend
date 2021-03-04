package com.et.control.genfiles;

import com.et.model.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
public class ExportETSTDC extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {

        Database db = new Database();

        ET_COUNTER_ADMIN_TABLE getAdminTable = new ET_COUNTER_ADMIN_TABLE(db);
        ET_COUNTER_ADMIN getCounterData = getAdminTable.findCounterData();

        ET_EXAM_DATE_TABLE getExamDateTable = new ET_EXAM_DATE_TABLE(db);
        List<ET_EXAM_DATE> getExamDate = getExamDateTable.findAllExamDate();

        request.setAttribute("getCounterData", getCounterData);
        request.setAttribute("getExamDate", getExamDate);

        // Parame Export Text File
        String CheckExportText = request.getParameter("Export");

        if (getCounterData != null && CheckExportText == null) {

            response.setContentType("text/html;charset=UTF-8");

            RequestDispatcher rs = request.getRequestDispatcher("admin/Export-ETSTDC-Main.jsp");
            rs.forward(request, response);

        } else if (CheckExportText.equals("FindExport")) {

            String YEAR = request.getParameter("year");
            String SEMESTER = request.getParameter("sem");
            String examdate = request.getParameter("examdate");
            String section = request.getParameter("section");

            EXPORT_ET_STDC_TABLE getExportET_STDC = new EXPORT_ET_STDC_TABLE(db);
            List<EXPORT_ET_STDC> ExportET_STDC = null;

            ArrayList<ET_STDC> lists = new ArrayList<ET_STDC>();

            ExportET_STDC = getExportET_STDC.findExportEtSTDCAll(YEAR, SEMESTER); // --- ค้นข้อมูลทั้งหมด ทุก
            // วัน/เดือน/ปี ที่สอบ และทุกคาบสอบ
//            System.err.println(ExportET_STDC);
            // นับจำนวน นศ.
            int countStudents = 0;
            if (!ExportET_STDC.isEmpty()) {

                for (int i = 0; i < ExportET_STDC.size(); i++) {
                    ET_STDC et_stdc = new ET_STDC();

                    String EXAM_DATE = ExportET_STDC.get(i).getEXAM_DATE();

                    String ROW_SEAT;
                    if (ExportET_STDC.get(i).getROW_SEAT().length() == 2) {
                        ROW_SEAT = ExportET_STDC.get(i).getROW_SEAT().substring(0, 1) + '0'
                                + ExportET_STDC.get(i).getROW_SEAT().substring(1);
                    } else {
                        ROW_SEAT = ExportET_STDC.get(i).getROW_SEAT();
                    }

                    // นับจำนวน นศ.
                    countStudents++;

                    et_stdc.setStdc_year(ExportET_STDC.get(i).getYEAR());
                    et_stdc.setStdc_std_semester(ExportET_STDC.get(i).getSEMESTER());
                    et_stdc.setStdc_std_code(ExportET_STDC.get(i).getSTD_CODE());
                    et_stdc.setStdc_std_course_code(ExportET_STDC.get(i).getCOURSE_NO() + "   ");
                    et_stdc.setStdc_credit("0" + ExportET_STDC.get(i).getCREDIT() + " ");
                    et_stdc.setStdc_section("02 ");
                    et_stdc.setApp_date_etest_dd(EXAM_DATE);
                    et_stdc.setApp_period_etest(ExportET_STDC.get(i).getSECTION_NO() + " ");
                    et_stdc.setApp_bld("SKB802");
                    et_stdc.setApp_row(ROW_SEAT);
                    et_stdc.setEtest_status(" e-  ");
                    et_stdc.setStdc_score_tot("000 ");
                    et_stdc.setStdc_score_M("000 ");
                    et_stdc.setStdc_score_F("000 ");
                    et_stdc.setStdc_score_chsum("0000");

                    lists.add(et_stdc);
                } // end for

            } else {

                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('วันที่สอบ หรือ คาบสอบที่เลือกไม่มีนักศึกษาลงทะเบียน!!!');");
                out.println("location='ExportETSTDC';");
                out.println("</script>");

            }
            // นับจำนวน นศ.
//            System.out.println("countStudents => " + countStudents);

            if (!lists.isEmpty()) {
//                System.out.println("get 1 => " + response.getCharacterEncoding());
                response.reset();
                response.setCharacterEncoding("US-ASCII");
//                System.out.println("get 2 => " + response.getCharacterEncoding());
                response.setContentType("text/plain");
                response.setHeader("Content-Disposition", "attachment; filename=\"ET_STDC.TXT\"");
                try {
                    OutputStream outputStream = response.getOutputStream();
                    String outputResult = "";
                    for (ET_STDC list : lists) {

                        outputResult = list.getStdc_year() + list.getStdc_std_semester() + list.getStdc_std_code()
                                + list.getStdc_std_course_code() + list.getStdc_credit() + list.getStdc_section()
                                + list.getApp_date_etest_dd() + list.getApp_period_etest() + list.getApp_bld()
                                + list.getApp_row() + list.getEtest_status() + list.getStdc_score_tot()
                                + list.getStdc_score_M() + list.getStdc_score_F() + list.getStdc_score_chsum() + "\n";
                        outputStream.write(outputResult.getBytes());
                    }

                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

                System.out.println("Lists = null");

            }

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ExportETSTDC.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ExportETSTDC.class.getName()).log(Level.SEVERE, null, ex);
        }
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
