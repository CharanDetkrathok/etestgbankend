package com.et.control.mangement;

import com.et.model.*;
import com.et.model.ET_COUNTER_ADMIN;
import com.et.model.ET_COUNTER_ADMIN_TABLE;
import com.et.model.ET_EXAM_DATE;
import com.et.model.ET_EXAM_DATE_TABLE;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DateManagement extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Database db = new Database();

        // ----- Query วัน/เดือน/ปี และภาคการศึกษา เพื่อไปแสดง ------------------------- 
        ET_COUNTER_ADMIN_TABLE getAdminTable = new ET_COUNTER_ADMIN_TABLE(db);
        ET_COUNTER_ADMIN getCounterData = getAdminTable.findCounterData();
        
        // ----- Query วัน/เดือน/ปี และภาคการศึกษา จำนวนที่นั่ง เพื่อไปแสดง ----------------
        ET_EXAM_SEAT_TABLE getExamSeatTable = new ET_EXAM_SEAT_TABLE(db);
        List<ET_EXAM_SEAT> ExamSeat = getExamSeatTable.findAll();

        // ----- Query วัน/เดือน/ปี ที่ทำการเปิดสอบ เพื่อแสดงในเมนู ----------------------- 
        ET_EXAM_DATE_TABLE getExamDateTable = new ET_EXAM_DATE_TABLE(db);
        List<ET_EXAM_DATE> ExamDate = getExamDateTable.findAll();
        
        // ----- Query ข้อมูล ปี/ภาค/ตึกสอบ/แถว/จำนวนที่นั่งต่อแถว -------------------
        ET_BUILE_ROW_TABLE getBuildRowTable = new ET_BUILE_ROW_TABLE(db);
        List<ET_BUILE_ROW> getBuildRow = getBuildRowTable.findAll();
        
        // ---- ผลรวมของจำนวนที่นั่งทั้งหมด -------------------------------------------
        int sumSeat = 0;
        for (int i = 0; i < getBuildRow.size(); i++) {
            sumSeat += getBuildRow.get(i).getSEAT_EXAM().intValue();
        }
        request.setAttribute("sumSeat", sumSeat);
        
        // ----- วัน/เดือน/ปี และภาคการศึกษา เพื่อไปแสดง ------------------------------- 
        request.setAttribute("getCounterData", getCounterData);
        
        // ----- วัน/เดือน/ปี ที่ทำการเปิดสอบ เพื่อแสดงในเมนู -----------------------------
        request.setAttribute("ExamSeat", ExamSeat);
        
        // ----- วัน/เดือน/ปี ที่ทำการเปิดสอบ เพื่อแสดงในเมนู -----------------------------
        request.setAttribute("ExamDate", ExamDate);
        
        RequestDispatcher rs = request.getRequestDispatcher("admin/Date-Management-Main.jsp");
        rs.forward(request, response);
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
        processRequest(request, response);
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
