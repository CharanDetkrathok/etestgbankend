package com.et.control.mangement;

import com.et.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
public class ReceiptManagementData extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");

        Database db = new Database();

        // ----- Query วัน/เดือน/ปี และภาคการศึกษา เพื่อไปแสดง -------------------------
        ET_COUNTER_ADMIN_TABLE getAdminTable = new ET_COUNTER_ADMIN_TABLE(db);
        ET_COUNTER_ADMIN getCounterData = getAdminTable.findCounterData();

        // ----- Query สถานะการจ่ายเงิน เพื่อไปแสดง ----------------------------------
        ET_RECEIPT_TABLE getReceiptTable = new ET_RECEIPT_TABLE(db);

        // ----- วัน/เดือน/ปี และภาคการศึกษา เพื่อไปแสดง -------------------------------
        request.setAttribute("getCounterData", getCounterData);

        String YEAR = request.getParameter("year");
        String SEMESTER = request.getParameter("sem");
        String ExamDate = request.getParameter("examdate");
        String SECTION = request.getParameter("section");

        // ----- สถานะการจ่ายเงิน เพื่อไปแสดง ----------------------------------------
        List<ET_RECEIPT> ReceiptData = getReceiptTable.findAllDateAllSection(YEAR, SEMESTER);// --- ค้นข้อมูลทั้งหมด ทุก

        if (!ReceiptData.isEmpty()) {

            request.setAttribute("year", YEAR);
            request.setAttribute("sem", SEMESTER);
            request.setAttribute("examdate", ExamDate);
            request.setAttribute("section", SECTION);
            request.setAttribute("ReceiptData", ReceiptData);
            RequestDispatcher rs = request.getRequestDispatcher("admin/Receipt-Management-Data.jsp");
            rs.forward(request, response);

        } else {

            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println(
                    "alert('วันสอบและคาบสอบ ที่ทำการเลือกนักศึกษาชำระเงินเรียบร้อยแล้ว หรือไม่มีนักศึกษาลงทะเบียนสอบ!!!');");
            out.println("location='ReceiptManagement';");
            out.println("</script>");

        }

        db.close();

    }

    public String changeDate(String Exam_Date) throws ParseException {

        // --- เปลี่ยน พ.ศ. ให้เป็น ค.ศ. ก่อน
        final String TEMP_OLD_FORMAT = "dd/MM/yyyy";
        final String TEMP_NEW_FORMAT = "yyyy-MM-dd";
        String TEMP_OldDateString = Exam_Date;
        String TEMP_EXAM_DATE;

        // --- เปลี่ยน พ.ศ. ให้เป็น ค.ศ. ก่อน (เปลี่ยนรูปแบบเพื่อแปลง)
        SimpleDateFormat temp_sdf = new SimpleDateFormat(TEMP_OLD_FORMAT, Locale.US);
        Date temp_d = temp_sdf.parse(TEMP_OldDateString);
        temp_sdf.applyPattern(TEMP_NEW_FORMAT);
        TEMP_EXAM_DATE = temp_sdf.format(temp_d);
        // --- เปลี่ยน พ.ศ. ให้เป็น ค.ศ. ก่อน (ลบ 2563-543 = 2020)
        LocalDate TEMP_EXAM_DATEe = LocalDate.parse(TEMP_EXAM_DATE).minus(543, ChronoUnit.YEARS);

        // --- เปลี่ยนรูปแบบเพื่อค้นหาข้อมูลที่ต้องการแก้ไข
        final String OLD_FORMAT = "yyyy-MM-dd";
        final String NEW_FORMAT = "MM/dd/yyyy";
        String oldDateString = TEMP_EXAM_DATEe.toString();
        String NEW_EXAM_DATE;

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(oldDateString);
        sdf.applyPattern(NEW_FORMAT);
        NEW_EXAM_DATE = sdf.format(d);

        return NEW_EXAM_DATE;
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
            Logger.getLogger(ReceiptManagementData.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ReceiptManagementData.class.getName()).log(Level.SEVERE, null, ex);
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
