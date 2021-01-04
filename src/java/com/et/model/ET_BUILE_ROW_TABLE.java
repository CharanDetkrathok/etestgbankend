/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ru-com7
 */
public class ET_BUILE_ROW_TABLE {

    Database db;

    public ET_BUILE_ROW_TABLE(Database db) {
        this.db = db;
    }

    public ET_BUILE_ROW setAltmodel(Map<String, Object> row) {

        if (row != null) {
            ET_BUILE_ROW getRow = ET_BUILE_ROW.builder()
                    .YEAR((String) row.get("YEAR"))
                    .SEMESTER((String) row.get("SEMESTER"))
                    .BUILD_NO((String) row.get("BUILD_NO"))
                    .ROW_EXAM((String) row.get("ROW_EXAM"))
                    .SEAT_EXAM((BigDecimal) row.get("SEAT_EXAM"))
                    .INSERT_DATE((String) row.get("INSERT_DATE"))
                    .INSERT_USER((String) row.get("INSERT_USER"))
                    .UPDATE_DATE((String) row.get("UPDATE_DATE"))
                    .UPDATE_USER((String) row.get("UPDATE_USER"))
                    .build();

            return getRow;
        } else {
            return null;
        }
    }

    public List<ET_BUILE_ROW> findAll() {
        List<ET_BUILE_ROW> list = new ArrayList<ET_BUILE_ROW>();
        String sql = "SELECT YEAR,SEMESTER,BUILD_NO,ROW_EXAM,SEAT_EXAM,"
                + "TO_CHAR(UPDATE_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')UPDATE_DATE,"
                + "TO_CHAR(INSERT_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE ,INSERT_USER,"
                + "UPDATE_USER "
                + "FROM  ET_BUILE_ROW  "
                + "ORDER BY ROW_EXAM ASC";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_BUILE_ROW> findBylist(String x) {
        List<ET_BUILE_ROW> list = new ArrayList<ET_BUILE_ROW>();
        String sql = "SELECT select FISCAL_YEAR,STUDY_YEAR,STUDY_SEMESTER,TYPE_COUNTER FROM ET_BUILE_ROW ";
        List<Map<String, Object>> result = db.queryList(sql, x);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 
    
    public ET_BUILE_ROW findBylist(String Year, String Semester, String Row_Exam) {
        String sql = "SELECT YEAR,SEMESTER,BUILD_NO,ROW_EXAM,SEAT_EXAM,"
                + "TO_CHAR(UPDATE_DATE, 'mm/dd/yyyy HH24:mm:ss')UPDATE_DATE,"
                + "TO_CHAR(INSERT_DATE, 'mm/dd/yyyy HH24:mm:ss')INSERT_DATE ,INSERT_USER,"
                + "UPDATE_USER "
                + "FROM  ET_BUILE_ROW  "
                + "WHERE YEAR = ? AND SEMESTER =? AND ROW_EXAM = ?"
                + "ORDER BY ROW_EXAM ASC";
                
        Map<String, Object> row = db.querySingle(sql, Year, Semester, Row_Exam);
        return setAltmodel(row);
    }
    //end find 

    public ET_BUILE_ROW findCounterData() {
        String sql = "SELECT FISCAL_YEAR,STUDY_YEAR,STUDY_SEMESTER,COUNTER_NO FROM ET_BUILE_ROW";
        Map<String, Object> row = db.querySingle(sql);

        return setAltmodel(row);

    }
    
    public ET_BUILE_ROW findSumSeatExam() {
        String sql = "SELECT SUM(SEAT_EXAM) AS SEAT_EXAM FROM ET_BUILE_ROW";
        Map<String, Object> row = db.querySingle(sql);

        return setAltmodel(row);

    }

    public boolean checkDuplicate(String row_exam) {

        String sql = "SELECT * FROM ET_BUILE_ROW WHERE ROW_EXAM = ?";
        Map<String, Object> row = db.querySingle(sql, row_exam);
        
        ET_BUILE_ROW chk = setAltmodel(row);
        
        try {
            if (chk != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }//end of check before insert

    public boolean insert(ET_BUILE_ROW obj) {
        // int colorNo = getColorNo();
        String sql = "insert into ET_BUILE_ROW(YEAR,SEMESTER,BUILD_NO,ROW_EXAM,SEAT_EXAM,INSERT_DATE) "
                + " values(?,?,?,?,?,SYSDATE)";

        String[] genCol = {"BUILD_NO"};
        int chk = db.insertRc(genCol, sql, obj.getYEAR(), obj.getSEMESTER(), obj.getBUILD_NO(), obj.getROW_EXAM(), obj.getSEAT_EXAM());

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

    public Boolean update(ET_BUILE_ROW obj) { 
        String sql = "update ET_BUILE_ROW set YEAR = ?,SEMESTER = ?, BUILD_NO = ?, ROW_EXAM = ?, SEAT_EXAM = ?, INSERT_DATE = TO_DATE(?,'mm/dd/yyyy HH24:MI:SS'), INSERT_USER = TO_DATE(?,'mm/dd/yyyy HH24:MI:SS'), UPDATE_DATE = SYSDATE, UPDATE_USER = TO_DATE(?,'mm/dd/yyyy HH24:MI:SS')"
                   + "WHERE YEAR = ? AND SEMESTER = ? AND ROW_EXAM = ?";
        
        int chkUpdate = db.update(sql, obj.getYEAR(), obj.getSEMESTER(), obj.getBUILD_NO(), obj.getROW_EXAM(), obj.getSEAT_EXAM(), obj.getINSERT_DATE(), obj.getINSERT_USER(), obj.getUPDATE_USER(), obj.getYEAR(), obj.getSEMESTER(), obj.getROW_EXAM());
        
        try {
            
            return chkUpdate > 0;

        } catch (Exception e) {
            
            return false;
        }

    }

    public Boolean delete(String year, String sem, String row_exam) {
        String sql = "DELETE FROM ET_BUILE_ROW WHERE YEAR = ? AND SEMESTER = ? AND ROW_EXAM = ?";
        int chkDelete = db.remove3Val(sql, year, sem, row_exam);
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

    public int countCounter() {
        int maxusr = 0;
        String sql = "select to_char(count(to_number(STUDY_SEMESTER)))STUDY_SEMESTER from XINT_COUNTER_ADMIN";
        Map<String, Object> row = db.querySingle(sql);

        if (row != null) {
            maxusr = Integer.parseInt((String) row.get("STUDY_SEMESTER"));
            return maxusr;
        } else {
            return 0;
        }
    }//end get max no
    
    public List<ET_BUILE_ROW> findRowAndSeat(String Year, String Semester) {
        List<ET_BUILE_ROW> list = new ArrayList<ET_BUILE_ROW>();
        String sql = "SELECT YEAR,SEMESTER,BUILD_NO,ROW_EXAM,SEAT_EXAM,"
                + "TO_CHAR(UPDATE_DATE, 'mm/dd/yyyy HH24:mm:ss')UPDATE_DATE,"
                + "TO_CHAR(INSERT_DATE, 'mm/dd/yyyy HH24:mm:ss')INSERT_DATE ,INSERT_USER,"
                + "UPDATE_USER "
                + "FROM  ET_BUILE_ROW  "
                + "WHERE YEAR = ? AND SEMESTER =?"
                + "ORDER BY ROW_EXAM ASC";
                
       List<Map<String, Object>> result = db.queryList(sql, Year, Semester);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 
}
