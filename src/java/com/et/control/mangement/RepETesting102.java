package com.et.control.mangement;

import com.et.model.Database;
import com.et.model.ET_COUNTER_ADMIN;
import com.et.model.ET_COUNTER_ADMIN_TABLE;
import com.et.model.REP_ETEST101;
import com.et.model.REP_ETEST101_TABLE;
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RefETesting102", urlPatterns = {"/RefETesting102"})
public class RepETesting102 extends HttpServlet {

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
            REP_ETEST101_TABLE getRepETest101 = new REP_ETEST101_TABLE(db);
            List<REP_ETEST101> repETest = getRepETest101.findRepETest101(YEAR, SEMESTER);

            // เตรียม Lists ไว้เก็บข้อมูลที่จะทำการจัดเรียงใหม่ สำหรับออกรายงาน
            ArrayList<REP_ETEST101> repETest101 = new ArrayList<REP_ETEST101>();

            if (!repETest.isEmpty()) {

                for (int i = 0; i < repETest.size(); i++) {

                    REP_ETEST101 tempRepETest101 = new REP_ETEST101();
                    tempRepETest101.setRECEIPT_DATE(changeDateThaiFormate(repETest.get(i).getRECEIPT_DATE()));

                    repETest101.add(tempRepETest101);

                }

                request.setAttribute("YEAR", YEAR);
                request.setAttribute("SEMESTER", SEMESTER);
                request.setAttribute("registerDate", repETest101);

                RequestDispatcher rs = request.getRequestDispatcher("admin/Rep-ETesting102-Selector.jsp");
                rs.forward(request, response);

            } else {
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('ปี,ภาค ที่เลือกไม่มีนักศึกษาลงทะเบียน!!!');");
                out.println("location='/etestgbackend/RepETesting102';");
                out.println("</script>");
            }

        } else if (request.getParameter("sumittt") != null) {

            String YEAR = request.getParameter("year");
            String SEMESTER = request.getParameter("sem");
            String registerDate = changeDateUnitedStateFormate(request.getParameter("registerDate"));

            // เรียกข้อมูลวันที่มีการจ่ายเงิน (จากการลงทะเบียน)
            REP_ETEST101_TABLE getRepETest101 = new REP_ETEST101_TABLE(db);
            List<REP_ETEST101> repETest = getRepETest101.RepETest101(YEAR, SEMESTER, registerDate);

            // เตรียม Lists ไว้เก็บข้อมูลที่จะทำการจัดเรียงใหม่ สำหรับออกรายงาน
            ArrayList<REP_ETEST101> repETest101 = new ArrayList<REP_ETEST101>();

            // รวมเงินของวันที่ลงทะเบียน
            int totalAmount = 0;

            // เพิ่ม 0 ไปข้างหน้าให้เต็ม 6 หลัก
            String tempSlip_No = null;

            if (!repETest.isEmpty()) {

                for (int i = 0; i < repETest.size(); i++) {

                    REP_ETEST101 tempRepETest101 = new REP_ETEST101();
                    tempRepETest101.setINSERT_DATE(changeDateThaiFormate(repETest.get(i).getINSERT_DATE()));
                    tempRepETest101.setSTD_CODE(repETest.get(i).getSTD_CODE());
                    tempRepETest101.setAMOUNT(repETest.get(i).getAMOUNT());
                    tempRepETest101.setREF_KEY(repETest.get(i).getREF_KEY());

                    // เพิ่ม 0 ไปข้างหน้าให้เต็ม 6 หลัก       
                    switch (repETest.get(i).getSLIP_NO().length()) {
                        case 1:
                            tempSlip_No = "00000" + repETest.get(i).getSLIP_NO();
                            break;
                        case 2:
                            tempSlip_No = "0000" + repETest.get(i).getSLIP_NO();
                            break;
                        case 3:
                            tempSlip_No = "000" + repETest.get(i).getSLIP_NO();
                            break;
                        case 4:
                            tempSlip_No = "00" + repETest.get(i).getSLIP_NO();
                            break;
                        case 5:
                            tempSlip_No = "0" + repETest.get(i).getSLIP_NO();
                            break;
                        case 6:
                            tempSlip_No = repETest.get(i).getSLIP_NO();
                            break;
                    }

                    tempRepETest101.setSLIP_NO("567/" + tempSlip_No);

                    // รวมเงินแต่ละคน
                    totalAmount += Integer.parseInt(tempRepETest101.getAMOUNT());
                    tempRepETest101.setTOTAL_AMOUNT(String.valueOf(totalAmount));
                    // เพิ่มข้อมูลที่จัดเรียงใหม่ลง Lists
                    repETest101.add(tempRepETest101);

                }

                request.setAttribute("YEAR", YEAR);
                request.setAttribute("SEMESTER", SEMESTER);

                request.setAttribute("registerDate", changeDateThaiFormate2(registerDate));
                request.setAttribute("repETest101", repETest101);

                RequestDispatcher rs = request.getRequestDispatcher("admin/Rep-ETesting102-Data.jsp");
                rs.forward(request, response);

            } else {
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('วันที่สอบที่เลือกไม่มีนักศึกษาลงทะเบียน!!!');");
                out.println("location='/etestgbackend/RepETesting102';");
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

            RequestDispatcher rs = request.getRequestDispatcher("admin/Rep-ETesting102-Main.jsp");
            rs.forward(request, response);

        }

        db.close();

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

    public String changeDateThaiFormate2(String Exam_Date) throws ParseException {

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

    public String changeDateUnitedStateFormate(String Exam_Date) throws ParseException {

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

        //--- เปลี่ยนรูปแบบเพื่อค้นหาข้อมูลที่ต้องการแก้ไข
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
            Logger.getLogger(RepETesting102.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RepETesting102.class.getName()).log(Level.SEVERE, null, ex);
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
