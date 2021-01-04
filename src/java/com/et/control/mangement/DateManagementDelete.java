package com.et.control.mangement;

import com.et.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DateManagementDelete extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");

        Database db = new Database();

        // ----- Query วัน/เดือน/ปี และภาคการศึกษา เพื่อไปแสดง ------------------------- 
        ET_COUNTER_ADMIN_TABLE getAdminTable = new ET_COUNTER_ADMIN_TABLE(db);

        // ----- Query วัน/เดือน/ปี และภาคการศึกษา จำนวนที่นั่ง เพื่อไปแสดง ----------------
        ET_EXAM_SEAT_TABLE getExamSeatTable = new ET_EXAM_SEAT_TABLE(db);

        // ----- Query วัน/เดือน/ปี ที่ทำการเปิดสอบ เพื่อแสดงในเมนู ----------------------- 
        ET_EXAM_DATE_TABLE getExamDateTable = new ET_EXAM_DATE_TABLE(db);

        if (request.getParameter("Delete") != null) {

            boolean isDeleteAllDate = getExamDateTable.deleteAll();
            boolean isDeleteAllSeat = getExamSeatTable.deleteAll();

            if (isDeleteAllDate && isDeleteAllSeat) {
                System.out.println("--ExamDate-- ลบได้");
                
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('ลบข้อมูลเรียบร้อย');");
                out.println("location='DateManagement';");
                out.println("</script>");
            } else {
                System.out.println("--ExamDate-- ลบไม่ได้");
                
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('มีบางอย่างผิดพลาด ไม่สามารถลบข้อมูลได้!!!');");
                out.println("location='DateManagement';");
                out.println("</script>");
            }
        }

        if (request.getParameter("Year") != null && request.getParameter("Semester") != null && request.getParameter("Exam_Date") != null) { //--- ลบแถวสอบ ---

            String Year = request.getParameter("Year");
            String Semester = request.getParameter("Semester");
            String Exam_Date = request.getParameter("Exam_Date");

            //--- เปลี่ยน พ.ศ. ให้เป็น ค.ศ. ก่อน 
            final String TEMP_OLD_FORMAT = "dd/MM/yyyy";
            final String TEMP_NEW_FORMAT = "yyyy-MM-dd";
            String TEMP_OldDateString = Exam_Date;
            String TEMP_EXAM_DATE;

            //--- เปลี่ยน พ.ศ. ให้เป็น ค.ศ. ก่อน (เปลี่ยนรูปแบบเพื่อแปลง)
            SimpleDateFormat temp_sdf = new SimpleDateFormat(TEMP_OLD_FORMAT, Locale.US);
            Date temp_d = temp_sdf.parse(TEMP_OldDateString);
            temp_sdf.applyPattern(TEMP_NEW_FORMAT);
            TEMP_EXAM_DATE = temp_sdf.format(temp_d);
            //--- เปลี่ยน พ.ศ. ให้เป็น ค.ศ. ก่อน (ลบ 2563-543 = 2020)
            LocalDate TEMP_EXAM_DATEe = LocalDate.parse(TEMP_EXAM_DATE).minus(543, ChronoUnit.YEARS);

            //--- เปลี่ยนรูปแบบเพื่อค้นหาข้อมูลที่ต้องการลบ
            final String OLD_FORMAT = "yyyy-MM-dd";
            final String NEW_FORMAT = "MM/dd/yyyy";
            String oldDateString = TEMP_EXAM_DATEe.toString();
            String NEW_EXAM_DATE;

            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            NEW_EXAM_DATE = sdf.format(d);

            boolean deleteResultExamDate = getExamDateTable.delete(Year, Semester, NEW_EXAM_DATE);
            if (deleteResultExamDate) {
                System.out.println("--ExamDate-- ลบได้");
            } else {
                System.out.println("--ExamDate-- ลบไม่ได้");
            }

            boolean deleteResultExamSeat = getExamSeatTable.delete(Year, Semester, NEW_EXAM_DATE);
            if (deleteResultExamSeat) {
                System.out.println("<<ExamSeat>> ลบได้");
            } else {
                System.out.println("<<ExamSeat>> ลบไม่ได้");
            }

            if (deleteResultExamDate && deleteResultExamSeat) {
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('ลบข้อมูลเรียบร้อย');");
                out.println("location='DateManagement';");
                out.println("</script>");
            } else {
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('มีบางอย่างผิดพลาด ไม่สามารถลบข้อมูลได้!!!');");
                out.println("location='DateManagement';");
                out.println("</script>");
            }

        } else {
            RequestDispatcher rs = request.getRequestDispatcher("DateManagement");
            rs.forward(request, response);
        }
        db.close();
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(DateManagementDelete.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DateManagementDelete.class.getName()).log(Level.SEVERE, null, ex);
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
