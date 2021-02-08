package com.et.control.mangement;

import com.et.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("unused")
public class SeatManagementInsert extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Database db = new Database();

        // ----- Query วัน/เดือน/ปี และภาคการศึกษา เพื่อไปแสดง ---------------------
        ET_COUNTER_ADMIN_TABLE getAdminTable = new ET_COUNTER_ADMIN_TABLE(db);
        ET_COUNTER_ADMIN getCounterData = getAdminTable.findCounterData();

        // ----- Query วัน/เดือน/ปี ที่ทำการเปิดสอบ เพื่อแสดงในเมนู -------------------
        ET_EXAM_DATE_TABLE getExamDateTable = new ET_EXAM_DATE_TABLE(db);
        List<ET_EXAM_DATE> getExamDate = getExamDateTable.findAllExamDate();

        // ----- Query ข้อมูล ปี/ภาค/ตึกสอบ/แถว/จำนวนที่นั่งต่อแถว ------------------
        ET_BUILE_ROW_TABLE getBuildRowTable = new ET_BUILE_ROW_TABLE(db);
        List<ET_BUILE_ROW> getBuildRow = getBuildRowTable.findAll();

        // ---- ผลรวมของจำนวนที่นั่งทั้งหมด ---------------------------------------
        int sumSeat = 0;
        for (int i = 0; i < getBuildRow.size(); i++) {
            sumSeat += getBuildRow.get(i).getSEAT_EXAM().intValue();
        }
        request.setAttribute("sumSeat", sumSeat);

        // ------- ข้อมูล ปี/ภาค/ตึกสอบ/แถว/จำนวนที่นั่งต่อแถว ------------------------
        request.setAttribute("BuildRow", getBuildRow);

        // ----- วัน/เดือน/ปี และภาคการศึกษา เพื่อไปแสดง ---------------------------
        request.setAttribute("getCounterData", getCounterData);

        // ----- วัน/เดือน/ปี ที่ทำการเปิดสอบ เพื่อแสดงในเมนู -------------------------
        request.setAttribute("getExamDate", getExamDate);

        // ------ เลือกเพิ่ม แถว และที่นั่งสอบ -----------------------------------------
        if (request.getParameter("Create") != null) {

            // --- สร้าง String สำหรับแสดงแถวที่นั่งสอบ -------------------------------
            String a[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                    "S", "T", "U", "V", "W", "X", "Y", "Z" };

            // --- สร้าง String สำหรับแสดงแถวที่นั่งสอบ ที่ยังเหลือ เช่นมีแถว A,B,C
            // แล้วเก็บเฉพาะ D-Z เพื่อนำไปแสดงให้ทำการเลือกเพื่อเพิ่มแถวใหม่
            ArrayList<String> rowExam = new ArrayList<String>();
            for (int i = 0; i < a.length; i++) {
                rowExam.add(a[i]);
            }

            // --- loop เอาแถวที่ถูกสร้างแล้วออกไป เช่นมีแถว A,B,C แล้วเก็บเฉพาะ D-Z
            // เพื่อนำไปแสดงให้ทำการเลือกเพื่อเพิ่มแถวใหม่
            for (int j = 0; j < a.length; j++) {
                for (int i = 0; i < getBuildRow.size(); i++) {

                    if (a[j].equals(getBuildRow.get(i).getROW_EXAM())) {

                        rowExam.remove(a[j]);

                    }

                }
            }

            request.setAttribute("rowExam", rowExam);
            RequestDispatcher rs = request.getRequestDispatcher("admin/Seat-Mangement-Create.jsp");
            rs.forward(request, response);

        } else if (request.getParameter("submit") != null) { // --- เพิ่ม --------

            // ------ เมื่อมีการ submit เพื่อเพิ่ม แถว และที่นั่งสอบ หลังกรอกข้อมูลแล้ว
            // --------
            String YEAR = request.getParameter("year");
            String SEMESTER = request.getParameter("semester");
            String BUILD_NO = request.getParameter("buildNoText").toUpperCase();
            String ROW_EXAM = request.getParameter("rowExamText");
            String SEAT_EXAM = request.getParameter("seatExamText");
            String SUM_SEAT_EXAM = request.getParameter("sumSeat");

            SimpleDateFormat formatter = new SimpleDateFormat("mm/dd/yyyy HH:mm:ss");
            java.util.Date date = new java.util.Date();

            String dateNow = formatter.format(date);

            ET_BUILE_ROW insertRowBuild = new ET_BUILE_ROW();
            insertRowBuild.setYEAR(YEAR);
            insertRowBuild.setSEMESTER(SEMESTER);
            insertRowBuild.setBUILD_NO(BUILD_NO);
            insertRowBuild.setROW_EXAM(ROW_EXAM);
            insertRowBuild.setSEAT_EXAM(BigDecimal.valueOf(Integer.valueOf(SEAT_EXAM)));
            insertRowBuild.setINSERT_DATE(dateNow);

            // --- ตรวจสอบว่ามีข้อมูลซ้ำหรือไม่ ถ้า checkDuplicateRowBuild เป็น false
            // ยังไม่มีข้อมูลเพิ่มได้
            boolean checkDuplicateRowBuild = getBuildRowTable.checkDuplicate(ROW_EXAM);
            PrintWriter out = response.getWriter();

            // --- SUM SEAT_EXAM เพิ่มทำการแก้ไขที่ตาราง ET_EXAM ทุกครั้ง
            int SumSeat = Integer.parseInt(SUM_SEAT_EXAM) + Integer.parseInt(SEAT_EXAM);

            // ----- Query วัน/เดือน/ปี และภาคการศึกษา จำนวนที่นั่ง เพื่อไปแสดง
            // ----------------
            ET_EXAM_SEAT_TABLE getExamSeatTable = new ET_EXAM_SEAT_TABLE(db);

            if (!checkDuplicateRowBuild) {

                boolean checkInsertRowBuild = getBuildRowTable.insert(insertRowBuild);

                if (checkInsertRowBuild) {

                    System.out.println("เพิ่มข้อมูลเรียบร้อย");
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('เพิ่มข้อมูลเรียบร้อย');");
                    out.println("location='SeatManagement';");
                    out.println("</script>");

                } else {

                    System.out.println("มีบางอย่างผิดพลาด ไม่สามารถเพิ่มข้อมูลได้");
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('มีบางอย่างผิดพลาด ไม่สามารถเพิ่มข้อมูลได้');");
                    out.println("location='SeatManagementInsert?Create=1';");
                    out.println("</script>");

                }

            } else {

                System.out.println("มีข้อมูลนี้อยู่แล้ว(ซ้ำ) ไม่สามารถเพิ่มข้อมูลได้");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('มีบางอย่างผิดพลาด ไม่สามารถเพิ่มข้อมูลได้');");
                out.println("location='SeatManagementInsert?Create=1';");
                out.println("</script>");

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
