package com.et.control.mangement;

import com.et.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DateManagementInsert extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");

        Database db = new Database();

        // ----- Query วัน/เดือน/ปี และภาคการศึกษา เพื่อไปแสดง ------------------------- 
        ET_COUNTER_ADMIN_TABLE getAdminTable = new ET_COUNTER_ADMIN_TABLE(db);
        ET_COUNTER_ADMIN getCounterData = getAdminTable.findCounterData();

        // ----- Query วัน/เดือน/ปี และภาคการศึกษา จำนวนที่นั่ง เพื่อไปแสดง ----------------
        ET_EXAM_SEAT_TABLE getExamSeatTable = new ET_EXAM_SEAT_TABLE(db);

        // ----- Query วัน/เดือน/ปี ที่ทำการเปิดสอบ เพื่อแสดงในเมนู ----------------------- 
        ET_EXAM_DATE_TABLE getExamDateTable = new ET_EXAM_DATE_TABLE(db);
        List<ET_EXAM_DATE> getExamDate = getExamDateTable.findAll();

        // ----- Query ข้อมูล ปี/ภาค/ตึกสอบ/แถว/จำนวนที่นั่งต่อแถว -------------------
        ET_BUILE_ROW_TABLE getBuildRowTable = new ET_BUILE_ROW_TABLE(db);
        ET_BUILE_ROW BuildRow = getBuildRowTable.findSumSeatExam();

        //------- ข้อมูล ปี/ภาค/ตึกสอบ/แถว/จำนวนที่นั่งต่อแถว ------------------------
        request.setAttribute("BuildRow", BuildRow);

        // ----- วัน/เดือน/ปี และภาคการศึกษา เพื่อไปแสดง ------------------------------- 
        request.setAttribute("getCounterData", getCounterData);

        // ----- วัน/เดือน/ปี ที่ทำการเปิดสอบ เพื่อแสดงในเมนู -----------------------------
        request.setAttribute("ExamDate", getExamDate);

        // ------ เลือกเพิ่ม แถว และที่นั่งสอบ -----------------------------------------
        if (request.getParameter("Create") != null) {

            RequestDispatcher rs = request.getRequestDispatcher("admin/Date-Mangement-Create.jsp");
            rs.forward(request, response);

        } else if (request.getParameter("submit") != null) {

            String YEAR = request.getParameter("year");
            String SEMESTER = request.getParameter("semester");
            String EXAM_DATE = request.getParameter("date_exam");
            String SEAT_EXAM = request.getParameter("seat_exam");

            String TEMP_EXAM_DATE = EXAM_DATE.replace("-", "/");

            final String OLD_FORMAT = "yyyy/MM/dd";
            final String NEW_FORMAT = "MM/dd/yyyy";
            String oldDateString = TEMP_EXAM_DATE;
            String NEW_EXAM_DATE;

            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            NEW_EXAM_DATE = sdf.format(d);

            ET_EXAM_DATE insertExamDate = new ET_EXAM_DATE();
            insertExamDate.setYEAR(YEAR);
            insertExamDate.setSEMESTER(SEMESTER);
            insertExamDate.setEXAM_DATE(NEW_EXAM_DATE);

            ET_EXAM_SEAT insertExamSeat = new ET_EXAM_SEAT();
            insertExamSeat.setYEAR(YEAR);
            insertExamSeat.setSEMESTER(SEMESTER);
            insertExamSeat.setEXAM_DATE(NEW_EXAM_DATE);
            insertExamSeat.setEXAM_SEAT(SEAT_EXAM);

            boolean checkDuplicateExamDate = getExamDateTable.checkDuplicate(NEW_EXAM_DATE);
            boolean checkDuplicateExamSeat = getExamSeatTable.checkDuplicate(NEW_EXAM_DATE);

            boolean checkInsertExamSeat = false;
            boolean checkInsertExamDate = false;

            if (!checkDuplicateExamDate || !checkDuplicateExamSeat) {

                for (int i = 0; i < 4; i++) {

                    insertExamDate.setPERIOD(String.valueOf(i + 1));
                    checkInsertExamDate = getExamDateTable.insert(insertExamDate);
                    if (checkInsertExamDate) {
                        System.out.println("เพิ่ม --ET_EXAM_DATE-- เรียบร้อย");
                    } else {
                        System.out.println("มีบางอย่างผิดพลาด ไม่สามารถเพิ่มข้อมูล --ET_EXAM_DATE-- ได้");
                    }

                    insertExamSeat.setPERIOD(String.valueOf(i + 1));
                    checkInsertExamSeat = getExamSeatTable.insert(insertExamSeat);
                    if (checkInsertExamSeat) {
                        System.out.println("เพิ่ม <<ET_EXAM_SEAT>> เรียบร้อย");
                    } else {
                        System.out.println("มีบางอย่างผิดพลาด ไม่สามารถเพิ่มข้อมูล <<ET_EXAM_SEAT>> ได้");
                    }

                }// end for

                if (checkInsertExamSeat && checkInsertExamDate) {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('เพิ่มข้อมูลเรียบร้อย');");
                    out.println("location='DateManagement';");
                    out.println("</script>");
                } else {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('มีบางอย่างผิดพลาด ไม่สามารถเพิ่มข้อมูลได้!!!');");
                    out.println("location='DateManagementInsert?Create=1';");
                    out.println("</script>");
                }

            } else {
                System.out.println("มีข้อมูลแล้ว");
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('ไม่สามารถเพิ่มข้อมูลได้ เพราะมีข้อมูลอยู่แล้ว!!!');");
                out.println("location='DateManagementInsert?Create=1';");
                out.println("</script>");
            }

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
            Logger.getLogger(DateManagementInsert.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DateManagementInsert.class.getName()).log(Level.SEVERE, null, ex);
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
