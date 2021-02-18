package com.et.control.genfiles;

import com.et.model.*;
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
public class ExportETRU25et extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");

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

            RequestDispatcher rs = request.getRequestDispatcher("admin/Export-ETRU25et-Main.jsp");
            rs.forward(request, response);

        } else if (CheckExportText.equals("FindExport")) {

            String YEAR = request.getParameter("year");
            String SEMESTER = request.getParameter("sem");
            String examdate = request.getParameter("examdate");
            String section = request.getParameter("section");

            EXPORT_ET_RU25et_TABLE getExportET_RU25et = new EXPORT_ET_RU25et_TABLE(db);
            List<EXPORT_ET_RU25et> ExportET_RU25et = null;

            ArrayList<EXPORT_ET_RU25et> lists = new ArrayList<EXPORT_ET_RU25et>();

            // --- ค้นข้อมูลทั้งหมด ทุก วัน/เดือน/ปี ที่สอบ และทุกคาบสอบ
            // --- ทุกวันทุกคาบสอบ
            ExportET_RU25et = getExportET_RU25et.findExportEtRu25etAll(YEAR, SEMESTER);

            // นับจำนวน นศ.
            int countStudents = 0;

            if (!ExportET_RU25et.isEmpty()) {

                for (int i = 0; i < ExportET_RU25et.size(); i++) {

                    // นับจำนวน นศ.
                    countStudents++;

                    EXPORT_ET_RU25et et_ru24et = new EXPORT_ET_RU25et();

                    et_ru24et.setColumnn1(String.format("%1$-" + 53 + "s", ExportET_RU25et.get(i).getColumnn1()));
                    et_ru24et.setColumnn2(String.format("%1$-" + 34 + "s", ExportET_RU25et.get(i).getColumnn2()));
                    et_ru24et.setColumnn3(ExportET_RU25et.get(i).getColumnn3());
                    lists.add(et_ru24et);
                } // end for

            } else {

                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('วันที่สอบ หรือ คาบสอบที่เลือกไม่มีนักศึกษาลงทะเบียน!!!');");
                out.println("location='ExportETRU25et';");
                out.println("</script>");

            }

            // นับจำนวน นศ.
            System.out.println("countStudents => " + countStudents);

            if (!lists.isEmpty()) {

                response.setContentType("text/plain");
                response.setHeader("Content-Disposition", "attachment; filename=\"RU25et.TXT\"");
                try {
                    OutputStream outputStream = response.getOutputStream();
                    String outputResult = "";
                    for (EXPORT_ET_RU25et list : lists) {

                        outputResult = list.getColumnn1() + list.getColumnn2() + list.getColumnn3() + "\n";
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
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ExportETRU25et.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ExportETRU25et.class.getName()).log(Level.SEVERE, null, ex);
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
