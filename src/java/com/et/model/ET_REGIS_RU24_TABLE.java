/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author awong
 */
public class ET_REGIS_RU24_TABLE {

    Database db;

    public ET_REGIS_RU24_TABLE(Database db) {
        this.db = db;
    }

    public ET_REGIS_RU24 setAltmodel(Map<String, Object> row) {

        if (row != null) {
            ET_REGIS_RU24 getRow = ET_REGIS_RU24.builder()
                    .YEAR((String) row.get("YEAR"))
                    .SEMESTER((String) row.get("SEMESTER"))
                    .STD_CODE((String) row.get("STD_CODE"))
                    .TIME_NO((BigDecimal) row.get("TIME_NO"))
                    .COURSE_NO((String) row.get("COURSE_NO"))
                    .CREDIT((BigDecimal) row.get("CREDIT"))
                    .SECTION_NO((BigDecimal) row.get("SECTION_NO"))
                    .EXAM_DATE((String) row.get("EXAM_DATE"))
                    .PERIOD((String) row.get("PERIOD"))
                    .BUILD_NO((String) row.get("BUILD_NO"))
                    .ROW_EXAM((String) row.get("ROW_EXAM"))
                    .SEAT_EXAM((BigDecimal) row.get("SEAT_EXAM"))
                    .STATUS_REGIS((String) row.get("STATUS_REGIS"))
                    .GRADE((String) row.get("GRADE"))
                    .SCORE_TOTAL((BigDecimal) row.get("SCORE_TOTAL"))
                    .SCORE_MIDTERM((BigDecimal) row.get("SCORE_MIDTERM"))
                    .SCORE_FINAL((BigDecimal) row.get("SCORE_FINAL"))
                    .CKREGIS((String) row.get("CKREGIS"))
                    .INSERT_DATE((String) row.get("INSERT_DATE"))
                    .INSERT_USER((String) row.get("INSERT_USER"))
                    .REF_KEY((String) row.get("REF_KEY"))
                    .REGIS_STATUS((String) row.get("REGIS_STATUS"))
                    .build();

            return getRow;
        } else {
            return null;
        }
    }

    public List<ET_REGIS_RU24> findAll() {
        List<ET_REGIS_RU24> list = new ArrayList<ET_REGIS_RU24>();
        String sql = "SELECT YEAR, SEMESTER, STD_CODE, TIME_NO, COURSE_NO, CREDIT, SECTION_NO, "
                + "TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE,"
                + " PERIOD, BUILD_NO, ROW_EXAM, SEAT_EXAM, STATUS_REGIS, GRADE, SCORE_TOTAL, SCORE_MIDTERM, SCORE_FINAL,"
                + " CKREGIS, TO_CHAR(INSERT_DATE, 'dd/mm/yyyy HH24:MI:SS', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE, "
                + " INSERT_USER, REF_KEY, REGIS_STATUS  FROM ET_REGIS_RU24 ";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 
    
    
     public List<ET_REGIS_RU24> findBySelectDate(String year,String sem,String examdate) {
        List<ET_REGIS_RU24> list = new ArrayList<ET_REGIS_RU24>();
        String sql = "SELECT YEAR, SEMESTER, STD_CODE, TIME_NO, COURSE_NO, CREDIT, SECTION_NO, "
                + "TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE,"
                + " PERIOD, BUILD_NO, ROW_EXAM, SEAT_EXAM, STATUS_REGIS, GRADE, SCORE_TOTAL, SCORE_MIDTERM, SCORE_FINAL,"
                + " CKREGIS, TO_CHAR(INSERT_DATE, 'dd/mm/yyyy HH24:MI:SS', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE, "
                + " INSERT_USER, REF_KEY, REGIS_STATUS  FROM ET_REGIS_RU24 where YEAR = ? and SEMESTER = ? and "
                + " EXAM_DATE = to_date(?, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')";
        List<Map<String, Object>> result = db.queryList(sql,year,sem,examdate);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 
     
     public List<ET_REGIS_RU24> findBySelectDateAndSection(String year,String sem,String examdate,String sec) {
        List<ET_REGIS_RU24> list = new ArrayList<ET_REGIS_RU24>();
        String sql = " SELECT a.YEAR, a.SEMESTER, a.STD_CODE, a.TIME_NO, a.COURSE_NO, a.CREDIT, a.SECTION_NO, "
                + " TO_CHAR(a.EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE,"
                 + " a.PERIOD, a.BUILD_NO, a.ROW_EXAM, a.SEAT_EXAM, a.STATUS_REGIS, a.GRADE, a.SCORE_TOTAL, a.SCORE_MIDTERM, a.SCORE_FINAL,"
                 + " a.CKREGIS, TO_CHAR(a.INSERT_DATE, 'dd/mm/yyyy HH24:MI:SS', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE," 
                 + " a.INSERT_USER, a.REF_KEY, a.REGIS_STATUS  "
                 + " FROM ET_REGIS_RU24 a,ET_RECEIPT b where A.STD_CODE = B.STD_CODE and A.SEMESTER = B.RECEIPT_SEMESTER "
                 + " and A.YEAR= B.RECEIPT_YEAR and A.REF_KEY = B.REF_KEY and B.RECEIPT_PAY_STATUS = '1' AND "
                 + " YEAR = ? and SEMESTER = ? order by trunc(a.INSERT_DATE) asc";
        List<Map<String, Object>> result = db.queryList(sql,year,sem);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_REGIS_RU24> findBylist(String x) {
        List<ET_REGIS_RU24> list = new ArrayList<ET_REGIS_RU24>();
        String sql = "SELECT YEAR, SEMESTER, STD_CODE, TIME_NO, COURSE_NO, CREDIT, SECTION_NO, "
                + "TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE,"
                + " PERIOD, BUILD_NO, ROW_EXAM, SEAT_EXAM, STATUS_REGIS, GRADE, SCORE_TOTAL, SCORE_MIDTERM, SCORE_FINAL,"
                + " CKREGIS, TO_CHAR(INSERT_DATE, 'dd/mm/yyyy HH24:MI:SS', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE, "
                + " INSERT_USER, REF_KEY, REGIS_STATUS  FROM ET_REGIS_RU24 ";
        List<Map<String, Object>> result = db.queryList(sql, x);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public ET_REGIS_RU24 findRu24ByStudent(String x) {
        String sql = "YEAR, SEMESTER, STD_CODE, TIME_NO, COURSE_NO, CREDIT, SECTION_NO, "
                + "TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE,"
                + " PERIOD, BUILD_NO, ROW_EXAM, SEAT_EXAM, STATUS_REGIS, GRADE, SCORE_TOTAL, SCORE_MIDTERM, SCORE_FINAL,"
                + " CKREGIS, TO_CHAR(INSERT_DATE, 'dd/mm/yyyy HH24:MI:SS', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE, "
                + " INSERT_USER, REF_KEY, REGIS_STATUS  FROM ET_REGIS_RU24 where STD_CODE = ?";
        Map<String, Object> row = db.querySingle(sql, x);

        return setAltmodel(row);

    }

    public boolean insert(ET_REGIS_RU24 obj) {
        // int colorNo = getColorNo();
        String sql = "insert into ET_REGIS_RU24(YEAR,SEMESTER,EXAM_DATE,PERIOD,INSERT_DATE) "
                + " values(?,?,TO_DATE(?, 'mm/dd/yyyy hh24:mi:ss'),?,sysdate)";

        String[] genCol = {"EXAM_DATE"};
        int chk = db.insertRc(genCol, sql);

        try {
            if (chk > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }//end of insert

    public Boolean update(ET_REGIS_RU24 obj) {
        String sql = "update ET_REGIS_RU24 set ROW_EXAM = ?,SEAT_EXAM = ?, UPDATE_DATE = SYSDATE "
                + " where STD_CODE = ? and COURSE_NO = ? and YEAR = ? and SEMESTER = ? and EXAM_DATE = ?";
        int chkUpdate = db.update(sql, obj.getROW_EXAM(), obj.getSEAT_EXAM(), obj.getSTD_CODE(), 
                obj.getCOURSE_NO(), obj.getYEAR(), obj.getSEMESTER(), obj.getEXAM_DATE());
        try {
            return chkUpdate > 0;

        } catch (Exception e) {
            return false;
        }

    }

    public Boolean delete(String year, String sem) {
        String sql = "delete from ET_REGIS_RU24 where STUDY_YEAR = ? and STUDY_SEMESTER = ?";
        int chkDelete = db.remove2Val(sql, year, sem);
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

    public int countStudent() {
        int maxusr = 0;
        String sql = "select to_char(count(to_number(STD_CODE)))STUDY_SEMESTER from ET_REGIS_RU24";
        Map<String, Object> row = db.querySingle(sql);

        if (row != null) {
            maxusr = Integer.parseInt((String) row.get("STUDY_SEMESTER"));
            return maxusr;
        } else {
            return 0;
        }
    }//end get max no
    
}
