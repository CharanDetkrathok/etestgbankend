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
public class ET_EXAM_DATE_TABLE {

    Database db;

    public ET_EXAM_DATE_TABLE(Database db) {
        this.db = db;
    }

    public ET_EXAM_DATE setAltmodel(Map<String, Object> row) {

        if (row != null) {
            ET_EXAM_DATE getRow = ET_EXAM_DATE.builder()
                    .YEAR((String) row.get("YEAR"))
                    .SEMESTER((String) row.get("SEMESTER"))
                    .EXAM_DATE((String) row.get("EXAM_DATE"))
                    .PERIOD((String) row.get("PERIOD"))
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

    public List<ET_EXAM_DATE> findAll() {
        List<ET_EXAM_DATE> list = new ArrayList<ET_EXAM_DATE>();
        String sql = "SELECT YEAR,SEMESTER,"
                + "TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE,"
                + "TO_CHAR(INSERT_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE ,"
                + "TO_CHAR(UPDATE_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')UPDATE_DATE ,"
                + "PERIOD, INSERT_USER,UPDATE_USER "
                + "FROM  ET_EXAM_DATE  ";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    
    public List<ET_EXAM_DATE> findAll2() {
        List<ET_EXAM_DATE> list = new ArrayList<ET_EXAM_DATE>();
        String sql = "SELECT YEAR,SEMESTER,"
                + "TO_CHAR(EXAM_DATE, 'mm/dd/yyyy')EXAM_DATE,"
                + "TO_CHAR(INSERT_DATE, 'mm/dd/yyyy')INSERT_DATE ,"
                + "TO_CHAR(UPDATE_DATE, 'mm/dd/yyyy')UPDATE_DATE ,"
                + "PERIOD, INSERT_USER,UPDATE_USER "
                + "FROM  ET_EXAM_DATE  ";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_EXAM_DATE> findAllExamDate() {
        List<ET_EXAM_DATE> list = new ArrayList<ET_EXAM_DATE>();
        String sql = "SELECT  distinct(TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI'))EXAM_DATE FROM  ET_EXAM_DATE order by EXAM_DATE asc ";
       // String sql = "SELECT distinct(trunc(EXAM_DATE)),TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE FROM  ET_EXAM_DATE ";

        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_EXAM_DATE> findBylist(String x) {
        List<ET_EXAM_DATE> list = new ArrayList<ET_EXAM_DATE>();
        String sql = "SELECT select YEAR,SEMESTER FROM ET_EXAM_DATE ";
        List<Map<String, Object>> result = db.queryList(sql, x);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 
    
    public List<ET_EXAM_DATE> findBylistForChangeSeatExam(String year, String secmester, String examDate, String section) {
        List<ET_EXAM_DATE> list = new ArrayList<ET_EXAM_DATE>();
        String sql = "SELECT YEAR,SEMESTER,"
                + "TO_CHAR(EXAM_DATE, 'mm/dd/yyyy')EXAM_DATE,"
                + "TO_CHAR(INSERT_DATE, 'mm/dd/yyyy')INSERT_DATE ,"
                + "TO_CHAR(UPDATE_DATE, 'mm/dd/yyyy')UPDATE_DATE ,"
                + "PERIOD, INSERT_USER,UPDATE_USER "
                + "FROM  ET_EXAM_DATE  "
                + "WHERE YEAR = ? AND SEMESTER = ? AND EXAM_DATE = TO_DATE( ?, 'mm/dd/yyyy' ) AND PERIOD = ?";
        List<Map<String, Object>> result = db.queryList(sql, year, secmester, examDate, section);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 
    
    public List<ET_EXAM_DATE> findExamDateReport() {
        List<ET_EXAM_DATE> list = new ArrayList<ET_EXAM_DATE>();
        String sql = "SELECT  distinct(TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI'))EXAM_DATE "
                + "FROM  ET_EXAM_DATE order by to_date(EXAM_DATE,'DD-MM-YYYY') asc ";
       // String sql = "SELECT distinct(trunc(EXAM_DATE)),TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE FROM  ET_EXAM_DATE ";

        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public ET_EXAM_DATE findCounterData() {
        String sql = "SELECT * FROM ET_EXAM_DATE";
        Map<String, Object> row = db.querySingle(sql);

        return setAltmodel(row);

    } 
    
    public boolean insert(ET_EXAM_DATE obj) {
        // int colorNo = getColorNo();
        String sql = "insert into ET_EXAM_DATE(YEAR,SEMESTER,EXAM_DATE,PERIOD,INSERT_DATE) "
                + " values(?,?,TO_DATE(?, 'mm/dd/yyyy'),?,sysdate)";

        String[] genCol = {"EXAM_DATE"};
        int chk = db.insertRc(genCol, sql, obj.getYEAR(), obj.getSEMESTER(), obj.getEXAM_DATE(), obj.getPERIOD());

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

    public Boolean update(ET_EXAM_DATE objval) {
        String sql = "update ET_EXAM_DATE set FISCAL_YEAR = ?,STUDY_YEAR = ?, STUDY_SEMESTER = ? , UPDATE_DATE = SYSDATE";
        int chkUpdate = db.update(sql);
        try {
            return chkUpdate > 0;
        } catch (Exception e) {
            return false;
        }

    }
    
    public boolean checkDuplicate(String exam_date) {

        String sql = "SELECT YEAR FROM ET_EXAM_DATE WHERE EXAM_DATE = TO_DATE(?, 'mm/dd/yyyy hh24:mi:ss')";
        Map<String, Object> row = db.querySingle(sql, exam_date);
        
        ET_EXAM_DATE chk = setAltmodel(row);
        
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

    public Boolean delete(String year, String sem, String exam_date) {
        String sql = "DELETE FROM ET_EXAM_DATE WHERE YEAR = ? AND SEMESTER = ? AND EXAM_DATE = TO_DATE( ?, 'mm/dd/yyyy' )";
        int chkDelete = db.remove3Val(sql, year, sem, exam_date);
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
    
        
    public Boolean deleteAll() {
        String sql = "DELETE FROM ET_EXAM_DATE";
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
}
