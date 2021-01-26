package com.et.control.mangement;

import com.et.model.Database;
import com.et.model.ET_COUNTER_ADMIN;
import com.et.model.ET_COUNTER_ADMIN_TABLE;
import com.et.model.REP_ETEST101;
import com.et.model.REP_ETEST101_TABLE;
import com.et.model.REP_ETEST103;
import com.et.model.REP_ETEST103_TABLE;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

public class RepETesting103 extends HttpServlet {

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

            String YEAR = request.getParameter("year");
            String SEMESTER = request.getParameter("sem");

            // เรียกข้อมูลวันที่มีการจ่ายเงิน (จากการลงทะเบียน)
            REP_ETEST103_TABLE getRepETest103 = new REP_ETEST103_TABLE(db);
            List<REP_ETEST103> repETest = getRepETest103.findRepETest103(YEAR, SEMESTER);

            // ทำการสร้าง array เพื่อรอเก็บวันที่ที่แปลงเป็น พ.ศ.
            ArrayList<REP_ETEST103> registerDate = new ArrayList<REP_ETEST103>();

            if (!repETest.isEmpty()) {
                for (int i = 0; i < repETest.size(); i++) {

                    REP_ETEST103 tempDate = new REP_ETEST103();

                    // ส่งไปเปลี่ยนเป็นเป็น พ.ศ. changeDateThaiFormate();
                    tempDate.setRECEIPT_DATE(repETest.get(i).getRECEIPT_DATE());

                    registerDate.add(tempDate);

                }

                // เตรียม Lists ไว้เก็บข้อมูลที่จะทำการจัดเรียงใหม่ สำหรับออกรายงาน
                ArrayList<REP_ETEST103> repETest103 = new ArrayList<REP_ETEST103>();

                for (int i = 0; i < registerDate.size(); i++) {

                    REP_ETEST103 tempDisplay = new REP_ETEST103();
                    String totalAmount = "";
                    String totalSTD = "";

                    totalAmount = getRepETest103.RepETest103SumTotalAmount(YEAR, SEMESTER, registerDate.get(i).getRECEIPT_DATE());
                    totalSTD = getRepETest103.RepETest103SumTotalStudents(YEAR, SEMESTER, registerDate.get(i).getRECEIPT_DATE());

                    tempDisplay.setRECEIPT_DATE(changeDateThaiFormate(repETest.get(i).getRECEIPT_DATE()));
                    tempDisplay.setTOTAL_AMOUNT(totalAmount);
                    tempDisplay.setTOTAL_STD(totalSTD);

                    // เพิ่มข้อมูลที่จัดเรียงใหม่ลง Lists
                    repETest103.add(tempDisplay);
                }

                int tempSumStd = 0;
                int tempSumAmount = 0;

                for (int i = 0; i < repETest103.size(); i++) {

                    if (repETest103.get(i).getTOTAL_AMOUNT() == null) {
                        repETest103.get(i).setSTR_TOTAL_AMOUNT("0");
                    } else {
                        // (จำนวนเงิน ยอดรวม "รายวัน") เพิ่ม , หลัก พัน เหมื่อน แสน ล้าน 
                        repETest103.get(i).setSTR_TOTAL_AMOUNT(additionalSemicolon(repETest103.get(i).getTOTAL_AMOUNT()));
                        tempSumAmount += Integer.valueOf(repETest103.get(i).getTOTAL_AMOUNT());
                    }

                    if (repETest103.get(i).getTOTAL_STD() == null) {
                        repETest103.get(i).setSTR_TOTAL_STD("0");
                    } else {
                        // (จำนวนนักศึกษา ยอดรวม "รายวัน") เพิ่ม , หลัก พัน เหมื่อน แสน ล้าน 
                        repETest103.get(i).setSTR_TOTAL_STD(additionalSemicolon(repETest103.get(i).getTOTAL_STD()));
                        tempSumStd += Integer.valueOf(repETest103.get(i).getTOTAL_STD());
                    }                    
                   
                }

                String sumStd = "0";
                String sumAmount = "0";

                if (tempSumStd == 0) {
                    sumStd = "0";
                } else {
                    sumStd = additionalSemicolon(String.valueOf(tempSumStd));
                }
                
                if (tempSumAmount == 0) {
                    sumAmount = "0";
                } else {
                    sumAmount = additionalSemicolon(String.valueOf(tempSumAmount));
                }

                String dateee = changeDateThaiFormate(String.valueOf(registerDate.get(0).getRECEIPT_DATE())) + " - " + changeDateThaiFormate(String.valueOf(registerDate.get(registerDate.size() - 1).getRECEIPT_DATE()));

                request.setAttribute("timePeriod", dateee);
                request.setAttribute("sumStd", sumStd);
                request.setAttribute("sumAmount", sumAmount);
                request.setAttribute("YEAR", YEAR);
                request.setAttribute("SEMESTER", SEMESTER);
                request.setAttribute("repETest103", repETest103);

                RequestDispatcher rs = request.getRequestDispatcher("admin/Rep-ETesting103-Data.jsp");
                rs.forward(request, response);
            } else {
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('วันที่สอบที่เลือกไม่มีนักศึกษาลงทะเบียน!!!');");
                out.println("location='/etestgbackend/RepETesting103';");
                out.println("</script>");
            }
        } else {
            String YEAR = getCounterData.getSTUDY_YEAR();
            String SEMESTER = getCounterData.getSTUDY_SEMESTER();

            // เรียกข้อมูลวันที่มีการจ่ายเงิน (จากการลงทะเบียน)
            REP_ETEST101_TABLE getRepETest101 = new REP_ETEST101_TABLE(db);
            List<REP_ETEST101> repETest = getRepETest101.findRepETest101(YEAR, SEMESTER);

            // ทำการสร้าง array เพื่อรอเก็บวันที่ที่แปลงเป็น พ.ศ.
            ArrayList<REP_ETEST101> registerDate = new ArrayList<REP_ETEST101>();

            for (int i = 0; i < repETest.size(); i++) {

                REP_ETEST101 tempDate = new REP_ETEST101();

                // ส่งไปเปลี่ยนเป็นเป็น พ.ศ. changeDateThaiFormate();
                tempDate.setRECEIPT_DATE(changeDateThaiFormate(repETest.get(i).getRECEIPT_DATE()));

                registerDate.add(tempDate);

            }

            request.setAttribute("registerDate", registerDate);

            RequestDispatcher rs = request.getRequestDispatcher("admin/Rep-ETesting103-Main.jsp");
            rs.forward(request, response);
        }

        db.close();

    }

    public String additionalSemicolon(String tempStr) {

        String tempTotalAmount = tempStr;

        // เพิ่ม , หลัก พัน เหมื่อน แสน ล้าน       
        switch (tempTotalAmount.length()) {
            case 7:
                tempTotalAmount = tempTotalAmount.substring(0, 4) + "," + tempTotalAmount.substring(4);
                tempTotalAmount = tempTotalAmount.substring(0, 1) + "," + tempTotalAmount.substring(1);
                break;
            case 6:
                tempTotalAmount = tempTotalAmount.substring(0, 3) + "," + tempTotalAmount.substring(3);
                break;
            case 5:
                tempTotalAmount = tempTotalAmount.substring(0, 2) + "," + tempTotalAmount.substring(2);
                break;
            case 4:
                tempTotalAmount = tempTotalAmount.substring(0, 1) + "," + tempTotalAmount.substring(1);
                break;
        }

        return tempTotalAmount;
    }

    public String changeDateThaiFormate(String Exam_Date) throws ParseException {

        //--- เปลี่ยน พ.ศ. ให้เป็น ค.ศ. ก่อน 
        final String TEMP_OLD_FORMAT = "MM/dd/yyyy";
        final String TEMP_NEW_FORMAT = "yyyy-MM-dd";
        String TEMP_OldDateString = Exam_Date;
        String TEMP_EXAM_DATE;

        //--- เปลี่ยน พ.ศ. ให้เป็น ค.ศ. ก่อน (เปลี่ยนรูปแบบเพื่อแปลง)
        SimpleDateFormat temp_sdf = new SimpleDateFormat(TEMP_OLD_FORMAT, Locale.US);
        Date temp_d = temp_sdf.parse(TEMP_OldDateString);
        temp_sdf.applyPattern(TEMP_NEW_FORMAT);
        TEMP_EXAM_DATE = temp_sdf.format(temp_d);

        //--- เปลี่ยน พ.ศ. ให้เป็น ค.ศ. ก่อน (ลบ 2563-543 = 2020)
        LocalDate TEMP_EXAM_DATEe = LocalDate.parse(TEMP_EXAM_DATE).plus(543, ChronoUnit.YEARS);

        //--- เปลี่ยนรูปแบบเพื่อค้นหาข้อมูลที่ต้องการแก้ไข
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
            Logger.getLogger(RepETesting103.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RepETesting103.class.getName()).log(Level.SEVERE, null, ex);
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
