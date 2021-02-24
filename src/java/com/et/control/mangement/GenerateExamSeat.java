package com.et.control.mangement;

import com.et.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
public class GenerateExamSeat extends HttpServlet {

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

        // ----- วัน/เดือน/ปี และภาคการศึกษา เพื่อไปแสดง -------------------------------
        request.setAttribute("getCounterData", getCounterData);

        if (request.getParameter("sumitt") != null) {

            String year = request.getParameter("year");
            String semester = request.getParameter("sem");

            GENERATE_ET_EXAM_SEAT_TABLE getGenerateEtExamSeat = new GENERATE_ET_EXAM_SEAT_TABLE(db);
            List<GENERATE_ET_EXAM_SEAT> etExamSeat = getGenerateEtExamSeat.findExamDate(year, semester);

            List<GENERATE_ET_EXAM_SEAT> studentData = null;

            // ----- Query ข้อมูล ปี/ภาค/ตึกสอบ/แถว/จำนวนที่นั่งต่อแถว ------------------
            ET_BUILE_ROW_TABLE getBuildRowTable = new ET_BUILE_ROW_TABLE(db);

            // เก็บแถว A-Z ลง Lists และจำนวนที่สั่ง เอาไว้ไปหยอด นักศึกษาลงเก้าอี้ ใน Method
            // setExamSeat()
            List<ET_BUILE_ROW> getBuildRow = getBuildRowTable.findRowAndSeat(year, semester);

            // นับจำนวน นศ. ลงทะเบียน
            int countStudents = 0;

            int ttt = 0;

            // boolean checkDeleteBeforeInsertExamSeat =
            // getGenerateEtExamSeat.deleteBeforeInsertExamSeat(year, semester);
            // หยอดนักศึกษาลงเก้าอีและแถวสอบ ตามวันที่เปิดสอบ
            for (GENERATE_ET_EXAM_SEAT generate_et_exam_seat : etExamSeat) {

                // หยอด นศ. ตามคาบสอบ มีทั้งหมด 4 คาบ
                for (int sectionTemp = 1; sectionTemp < 5; sectionTemp++) {

                    // ค้นหาข้อมูล นศ. ตามวันและคาบสอบ ค้นที่ละคาบของวันสอบ
                    // ที่ลงทะเบียนและจ่ายเงินแล้ว
                    studentData = getGenerateEtExamSeat.findExamDateAndSectionForStudents(year, semester,
                            generate_et_exam_seat.getEXAM_DATE(), String.valueOf(sectionTemp));

                    //จำนวนที่นั่งสอบของคาบสอบนั้นๆ
                    String seatThisExamdateAndPeriod = getGenerateEtExamSeat.getSeatThisExamdateAndPeriod(year, semester, generate_et_exam_seat.getEXAM_DATE(), String.valueOf(sectionTemp));
                    
                    System.out.println("วันสอบ => " + generate_et_exam_seat.getEXAM_DATE() + " " + sectionTemp);
                    
                    // ผลการค้นข้อมูล ถ้ามี นศ. สอบตรงกับคาบและวันสอบ จริงทำการจัดที่นั่งสอบให้ นศ.
                    if (!studentData.isEmpty()) {

                        // กำหนดที่นั่ง เริ่มต้น ที่โต๊ะหรือเก้าอี้สอบตัวแรก
                        int countSeatRow = 1;

                        // Loop จนกว่าจะจัดที่นั่งให้ นศ. จนครบทั้งหมดใน วันและคาบสอบนั้นๆ
                        for (int i = 0; i < studentData.size(); i++) {
                            System.out.println("std => " + studentData.get(i).getSTD_CODE());
                            // นับ นศ.
                            countStudents++;

                            // ส่ง นศ. ไป insert ที่นั่งสอบใน Method setExamSeat ลงฐานข้อมูล
                            setExamSeat(year, semester, countSeatRow, studentData.get(i), getBuildRow, getGenerateEtExamSeat, Integer.parseInt(seatThisExamdateAndPeriod));

                        }

//                        System.out.println("มี นศ. ลงทะเบียน จำนวน นศ. ได้ที่นั่งแล้ว = " + countStudents);
                    } else {
//                        System.out.println("ไม่มี นศ. ลงทะเบียน " + etExamSeat.get(ttt).getEXAM_DATE() + " คาบที่ " + sectionTemp);
                    }
                }

                ttt++;
            }
            System.out.println("countStudents => " + countStudents);
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('ทำการสร้างข้อมูลเรียบร้อย');");
            out.println("location='GenerateExamSeat';");
            out.println("</script>");
        } else {

            RequestDispatcher rs = request.getRequestDispatcher("admin/Generate-Exam-Seat.jsp");
            rs.forward(request, response);

        }
        db.close();

    }

    public void setExamSeat(String year, String semester, int countSeatRow, GENERATE_ET_EXAM_SEAT students,
            List<ET_BUILE_ROW> buileRow, GENERATE_ET_EXAM_SEAT_TABLE getGenerateEtExamSeatInsert, int seatThisExamdateAndPeriod)
            throws ParseException {

        int count = countSeatRow;
        String Year = year;
        String Semester = semester;
        String RowSeat = "";
        String StdCode = students.getSTD_CODE();
        String ExamDate = changeDate(students.getEXAM_DATE());
        String Section = students.getSECTION_NO().toString();
        String Credit = students.getCREDIT().toString();
        String Course = students.getCOURSE_NO();
        String StatusCourse = "O";

        boolean insetSeat = false;
        for (int i = 0; i < buileRow.size(); i++) {

            int countSeatThisRow = getGenerateEtExamSeatInsert.getCounterSeatThisRow(Year, Semester,
                    changeFormatDate(ExamDate), Section, buileRow.get(i).getROW_EXAM() + "%");

            if (countSeatThisRow >= seatThisExamdateAndPeriod) {

            } else {
                countSeatThisRow += 1;
                RowSeat = buileRow.get(i).getROW_EXAM() + "" + String.valueOf(countSeatThisRow);
                insetSeat = getGenerateEtExamSeatInsert.InsertAndGenerateEtExamSeat(Year, Semester, RowSeat, StdCode,
                        ExamDate, Section, Credit, Course, StatusCourse);
            }

            if (insetSeat) {
                break;
            }

        }

    }

    public String changeFormatDate(String Exam_Date) throws ParseException {

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

        LocalDate TEMP_EXAM_DATEe = LocalDate.parse(TEMP_EXAM_DATE);

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

    public String changeDate(String Exam_Date) throws ParseException {

        // --- เปลี่ยน พ.ศ. ให้เป็น ค.ศ. ก่อน
        final String TEMP_OLD_FORMAT = "MM/dd/yyyy";
        final String TEMP_NEW_FORMAT = "yyyy-MM-dd";
        String TEMP_OldDateString = Exam_Date;
        String TEMP_EXAM_DATE;

        // --- เปลี่ยน พ.ศ. ให้เป็น ค.ศ. ก่อน (เปลี่ยนรูปแบบเพื่อแปลง)
        SimpleDateFormat temp_sdf = new SimpleDateFormat(TEMP_OLD_FORMAT, Locale.US);
        Date temp_d = temp_sdf.parse(TEMP_OldDateString);
        temp_sdf.applyPattern(TEMP_NEW_FORMAT);
        TEMP_EXAM_DATE = temp_sdf.format(temp_d);
        // --- เปลี่ยน พ.ศ. ให้เป็น ค.ศ. ก่อน (ลบ 2563-543 = 2020)
        LocalDate TEMP_EXAM_DATEe = LocalDate.parse(TEMP_EXAM_DATE).plus(543, ChronoUnit.YEARS);

        // --- เปลี่ยนรูปแบบเพื่อค้นหาข้อมูลที่ต้องการแก้ไข
        final String OLD_FORMAT = "yyyy-MM-dd";
        final String NEW_FORMAT = "dd/MM/yyyy";
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
            Logger.getLogger(GenerateExamSeat.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GenerateExamSeat.class.getName()).log(Level.SEVERE, null, ex);
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
