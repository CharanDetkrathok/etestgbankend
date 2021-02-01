package com.et.control.mangement;

import com.et.model.Database;
import com.et.model.ET_BUILE_ROW;
import com.et.model.ET_BUILE_ROW_TABLE;
import com.et.model.ET_COUNTER_ADMIN;
import com.et.model.ET_COUNTER_ADMIN_TABLE;
import com.et.model.ET_EXAM_DATE;
import com.et.model.ET_EXAM_DATE_TABLE;
import com.et.model.ET_EXAM_SEAT;
import com.et.model.ET_EXAM_SEAT_TABLE;
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
// import static org.eclipse.jdt.internal.compiler.codegen.ConstantPool.Out;

@SuppressWarnings("unused")
public class DateManagementUpdate extends HttpServlet {

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

        // ----- Query วัน/เดือน/ปี และภาคการศึกษา จำนวนที่นั่ง เพื่อไปแสดง
        // ----------------
        ET_EXAM_SEAT_TABLE getExamSeatTable = new ET_EXAM_SEAT_TABLE(db);

        // ----- Query วัน/เดือน/ปี ที่ทำการเปิดสอบ เพื่อแสดงในเมนู
        // -----------------------
        ET_EXAM_DATE_TABLE getExamDateTable = new ET_EXAM_DATE_TABLE(db);
        List<ET_EXAM_DATE> getExamDate = getExamDateTable.findAll();

        // ----- Query ข้อมูล ปี/ภาค/ตึกสอบ/แถว/จำนวนที่นั่งต่อแถว -------------------
        ET_BUILE_ROW_TABLE getBuildRowTable = new ET_BUILE_ROW_TABLE(db);
        ET_BUILE_ROW BuildRow = getBuildRowTable.findSumSeatExam();

        // ------- ข้อมูล ปี/ภาค/ตึกสอบ/แถว/จำนวนที่นั่งต่อแถว ------------------------
        request.setAttribute("BuildRow", BuildRow);

        // ----- วัน/เดือน/ปี และภาคการศึกษา เพื่อไปแสดง -------------------------------
        request.setAttribute("getCounterData", getCounterData);

        // ----- วัน/เดือน/ปี ที่ทำการเปิดสอบ เพื่อแสดงในเมนู
        // -----------------------------
        request.setAttribute("ExamDate", getExamDate);

        // ------ เลือกเพิ่ม แถว และที่นั่งสอบ -----------------------------------------
        if (request.getParameter("Year") != null && request.getParameter("Semester") != null
                && request.getParameter("Exam_Date") != null && request.getParameter("Section") != null) { // ---
                                                                                                           // ลบแถวสอบ
                                                                                                           // ---

            String Year = request.getParameter("Year");
            String Semester = request.getParameter("Semester");
            String Exam_Date = request.getParameter("Exam_Date");
            String Section = request.getParameter("Section");
            String NewExamdate = request.getParameter("Exam_Date");
            Exam_Date = changeDate(Exam_Date);

            List<ET_EXAM_SEAT> getToDisplayExamSeat = getExamSeatTable.findBylistForChangeSeatExam(Year, Semester,
                    Exam_Date, Section);
            String SeatExam = getToDisplayExamSeat.get(0).getEXAM_SEAT();
            request.setAttribute("Exam_Date", NewExamdate);
            request.setAttribute("Section", Section);
            request.setAttribute("SeatExam", SeatExam);

            RequestDispatcher rs = request.getRequestDispatcher("admin/Date-Mangement-Edit.jsp");
            rs.forward(request, response);
        } else if (request.getParameter("submit") != null) {

            String YEAR = request.getParameter("year");
            String SEMESTER = request.getParameter("semester");
            String EXAM_DATE = request.getParameter("date_exam");
            String NEW_SEAT_EXAM = request.getParameter("seat_exam");
            String SECTION = request.getParameter("section");

            EXAM_DATE = changeDate(EXAM_DATE);

            boolean checkInsertExamSeat = false;

            if (SECTION.equals("0")) {

                checkInsertExamSeat = getExamSeatTable.updateNewSeatAllSection(NEW_SEAT_EXAM, YEAR, SEMESTER,
                        EXAM_DATE);
                if (checkInsertExamSeat) {
                    System.out.println("แก้ไข <<ET_EXAM_SEAT>> เรียบร้อย");
                } else {
                    System.out.println("มีบางอย่างผิดพลาด ไม่สามารถแก้ไขข้อมูล <<ET_EXAM_SEAT>> ได้");
                }

                if (checkInsertExamSeat) {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('แก้ไข \" ที่นั่งสอบใหม่ \" เรียบร้อย');");
                    out.println("location='DateManagement';");
                    out.println("</script>");
                } else {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('มีบางอย่างผิดพลาด ไม่สามารถแก้ไขข้อมูลได้!!!');");
                    out.println("location='DateManagementInsert?Create=1';");
                    out.println("</script>");
                }

            } else {

                checkInsertExamSeat = getExamSeatTable.updateNewSeatBySection(NEW_SEAT_EXAM, YEAR, SEMESTER, EXAM_DATE,
                        SECTION);
                if (checkInsertExamSeat) {
                    System.out.println("แก้ไข <<ET_EXAM_SEAT>> เรียบร้อย");
                } else {
                    System.out.println("มีบางอย่างผิดพลาด ไม่สามารถแก้ไขข้อมูล <<ET_EXAM_SEAT>> ได้");
                }

                if (checkInsertExamSeat) {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('แก้ไข \" ที่นั่งสอบใหม่ \" เรียบร้อย');");
                    out.println("location='DateManagement';");
                    out.println("</script>");
                } else {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('มีบางอย่างผิดพลาด ไม่สามารถแก้ไขข้อมูลได้!!!');");
                    out.println("location='DateManagementInsert?Create=1';");
                    out.println("</script>");
                }

            }

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
            Logger.getLogger(DateManagementUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DateManagementUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
