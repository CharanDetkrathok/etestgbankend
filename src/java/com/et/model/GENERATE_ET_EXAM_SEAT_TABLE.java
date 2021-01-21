package com.et.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GENERATE_ET_EXAM_SEAT_TABLE {

    Database db;

    public GENERATE_ET_EXAM_SEAT_TABLE(Database db) {
        this.db = db;
    }

    public GENERATE_ET_EXAM_SEAT setAltmodel(Map<String, Object> row) {

        if (row != null) {
            GENERATE_ET_EXAM_SEAT getRow = GENERATE_ET_EXAM_SEAT.builder()
                    .YEAR((String) row.get("YEAR"))
                    .SEMESTER((String) row.get("SEMESTER"))
                    .INSERT_DATE((String) row.get("INSERT_DATE"))
                    .ROW_SEAT((String) row.get("ROW_SEAT"))
                    .STD_CODE((String) row.get("STD_CODE"))
                    .COUNT_SEAT_THIS_ROW((BigDecimal) row.get("COUNT_SEAT_THIS_ROW"))
                    .EXAM_DATE((String) row.get("EXAM_DATE"))
                    .SECTION_NO((BigDecimal) row.get("SECTION_NO"))
                    .CREDIT((BigDecimal) row.get("CREDIT"))
                    .COURSE_NO((String) row.get("COURSE_NO"))
                    .STATUS_COURSE((String) row.get("STATUS_COURSE"))
                    .RECEIPT_PAY_STATUS((String) row.get("RECEIPT_PAY_STATUS"))
                    .SUM_SEAT_BY_ROW((String) row.get("SUM_SEAT_BY_ROW"))
                    .build();

            return getRow;
        } else {
            return null;
        }
    }

    public List<GENERATE_ET_EXAM_SEAT> findExamDate(String year, String semester) {
        List<GENERATE_ET_EXAM_SEAT> list = new ArrayList<GENERATE_ET_EXAM_SEAT>();
        String sql = " SELECT DISTINCT TO_CHAR(EXAM_DATE, 'MM/DD/YYYY')EXAM_DATE"
                + " FROM ET_EXAM_DATE WHERE YEAR = '" + year + "' AND SEMESTER = '" + semester + "' "
                + " GROUP BY EXAM_DATE "
                + " ORDER BY EXAM_DATE ";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find
    
    //------------------------------ ET_REGIS_RU24 ------------------------------
    public List<GENERATE_ET_EXAM_SEAT> findExamDateAndSectionForStudents(String year, String semester, String examDate, String section) {
        List<GENERATE_ET_EXAM_SEAT> list = new ArrayList<GENERATE_ET_EXAM_SEAT>();
        String sql = "SELECT DISTINCT TO_CHAR(B.RECEIPT_DATE, 'MM/DD/YYYY hh:mm:ss') INSERT_DATE, TO_CHAR(A.EXAM_DATE, 'MM/DD/YYYY')EXAM_DATE,B.COUNTER_NO, A.STD_CODE,A.COURSE_NO,A.CREDIT, A.SECTION_NO,B.RECEIPT_PAY_STATUS FROM ET_REGIS_RU24_BACKUP2 A,ET_RECEIPT_BACKUP2 B,QR_PAYMENT_CONFIRM_TMB_BACKUP2 C WHERE A.YEAR = '"+year+"' AND A.SEMESTER = '"+semester+"' AND A.EXAM_DATE = TO_DATE('"+examDate+"', 'MM/DD/YYYY') AND A.SECTION_NO = '"+section+"' AND B.RECEIPT_PAY_STATUS = '1' AND A.STD_CODE = B.STD_CODE AND A.REF_KEY = B.REF_KEY AND B.REF_KEY = C.QRID AND TO_NUMBER(B.TOTAL_AMOUNT) = TO_NUMBER(C.AMOUNT) AND NOT EXISTS (SELECT L.STD_CODE,L.COURSE_NO FROM ET_ROW_SEAT_ORDER L WHERE A.STD_CODE = L.STD_CODE AND A.COURSE_NO = L.COURSE_NO AND A.YEAR = L.YEAR AND A.SEMESTER = L.SEMESTER AND A.SECTION_NO = L.SECTION_NO) ORDER BY TO_CHAR(B.RECEIPT_DATE, 'MM/DD/YYYY hh:mm:ss')";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find

    //----------------  ET_ROW_SEAT_ORDER  --------------------
    public List<GENERATE_ET_EXAM_SEAT> findStudentsInRowSeat(String year, String semester, String examDate, String section) {
        List<GENERATE_ET_EXAM_SEAT> list = new ArrayList<GENERATE_ET_EXAM_SEAT>();
        String sql = "SELECT TO_CHAR(A.EXAM_DATE, 'MM/DD/YYYY')EXAM_DATE,A.ROW_SEAT,A.SECTION_NO, A.STD_CODE,A.COURSE_NO,A.CREDIT, A.SECTION_NO"
                + " FROM ET_ROW_SEAT_ORDER A"
                + " WHERE A.YEAR = '" + year + "' AND A.SEMESTER = '" + semester + "' AND A.EXAM_DATE = TO_DATE('" + examDate + "', 'MM/DD/YYYY') AND A.SECTION_NO = '" + section + "'";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find
    
    public int getCounterSeatThisRow(String year, String semester, String examDate, String section, String rowSeat) {
        
        List<GENERATE_ET_EXAM_SEAT> list = new ArrayList<GENERATE_ET_EXAM_SEAT>();
        String sql = "SELECT (CASE WHEN  COUNT( A.ROW_SEAT) IS NOT NULL THEN  COUNT( A.ROW_SEAT) END) AS COUNT_SEAT_THIS_ROW FROM ET_ROW_SEAT_ORDER A WHERE A.YEAR = '"+year+"' AND A.SEMESTER = '"+semester+"' AND A.EXAM_DATE = TO_DATE('"+examDate+"', 'MM/DD/YYYY') AND A.SECTION_NO = '"+section+"' AND A.ROW_SEAT LIKE '"+rowSeat+"'";

        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list.get(0).getCOUNT_SEAT_THIS_ROW().intValue();
    }
    //end find 

    public Boolean deleteBeforeInsertExamSeat(String year, String sem) {
        String sql = "DELETE FROM ET_ROW_SEAT_ORDER";
        int chkDelete = db.remove(sql);
        try {
            if (chkDelete > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }  //end of delete

    public boolean InsertAndGenerateEtExamSeat(String year, String semester, String rowSeat, String stdCode, String examDate, String section, String credit, String course, String statusCourse) {
        List<GENERATE_ET_EXAM_SEAT> list = new ArrayList<GENERATE_ET_EXAM_SEAT>();
        String sql = " INSERT INTO ET_ROW_SEAT_ORDER(YEAR,SEMESTER,INSERT_DATE,ROW_SEAT,STD_CODE,EXAM_DATE,SECTION_NO,CREDIT,COURSE_NO,STATUS_COURSE) "
                + " VALUES(?,?,SYSDATE,?,?,to_date(?,'dd/mm/yyyy'),?,?,?,?)";
        String[] genCol = {"STD_CODE"};
        int chk = db.insertRc(genCol, sql, year, semester, rowSeat, stdCode, examDate, section, credit, course, statusCourse);

        try {
            if (chk > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    //end find 

}
