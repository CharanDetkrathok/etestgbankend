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
public class ET_COURSE_OPEN_TABLE {

    Database db;

    public ET_COURSE_OPEN_TABLE(Database db) {
        this.db = db;
    }

    public ET_COURSE_OPEN setAltmodel(Map<String, Object> row) {

        if (row != null) {
            ET_COURSE_OPEN getRow = ET_COURSE_OPEN.builder()
                    .YEAR((String) row.get("YEAR"))
                    .SEMESTER((String) row.get("SEMESTER"))
                    .COURSE_NO((String) row.get("COURSE_NO"))
                    .CREDIT((BigDecimal) row.get("CREDIT"))
                    .STATUS_COURSE((String) row.get("STATUS_COURSE"))
                    .COURSE_FEE((String) row.get("COURSE_FEE"))
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

    public List<ET_COURSE_OPEN> findAll() {
        List<ET_COURSE_OPEN> list = new ArrayList<ET_COURSE_OPEN>();
        String sql = "SELECT YEAR,SEMESTER,COURSE_NO,CREDIT,STATUS_COURSE,COURSE_FEE,"
                + "TO_CHAR(INSERT_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE,INSERT_USER,"
                + "TO_CHAR(UPDATE_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')UPDATE_DATE ,"
                + "UPDATE_USER FROM  ET_COURSE_OPEN  ";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_COURSE_OPEN> findBySemAndYear(String sem, String year) {
        List<ET_COURSE_OPEN> list = new ArrayList<ET_COURSE_OPEN>();
        String sql = "SELECT YEAR,SEMESTER,COURSE_NO,CREDIT,STATUS_COURSE,COURSE_FEE,"
                + "TO_CHAR(INSERT_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE,INSERT_USER,"
                + "TO_CHAR(UPDATE_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')UPDATE_DATE ,"
                + "UPDATE_USER FROM  ET_COURSE_OPEN WHERE SEMESTER = ? AND YEAR = ?  ";
        List<Map<String, Object>> result = db.queryList(sql, sem, year);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_COURSE_OPEN> findBylist(String x) {
        List<ET_COURSE_OPEN> list = new ArrayList<ET_COURSE_OPEN>();
        String sql = "SELECT YEAR,SEMESTER,COURSE_NO,CREDIT,STATUS_COURSE,COURSE_FEE,"
                + "TO_CHAR(INSERT_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE,INSERT_USER,"
                + "TO_CHAR(UPDATE_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')UPDATE_DATE ,"
                + "UPDATE_USER FROM  ET_COURSE_OPEN where ";
        List<Map<String, Object>> result = db.queryList(sql, x);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public ET_COURSE_OPEN findbyData() {
        String sql = "SELECT YEAR,SEMESTER,COURSE_NO,CREDIT,STATUS_COURSE,COURSE_FEE,"
                + "TO_CHAR(INSERT_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE,INSERT_USER,"
                + "TO_CHAR(UPDATE_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')UPDATE_DATE ,"
                + "UPDATE_USER FROM  ET_COURSE_OPEN";
        Map<String, Object> row = db.querySingle(sql);

        return setAltmodel(row);

    }

    public boolean insert(ET_COURSE_OPEN obj) {
        // int colorNo = getColorNo();
        String sql = " INSERT INTO ET_COURSE_OPEN(YEAR,SEMESTER,COURSE_NO,STATUS_COURSE,INSERT_DATE)"
                + " VALUES(?,?,?,?,sysdate) ";

        String[] genCol = {"COURSE_NO"};
        int chk = db.insertRc(genCol, sql, obj.getYEAR(), obj.getSEMESTER(), obj.getCOURSE_NO(), obj.getSTATUS_COURSE());

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

    public boolean insertCourse(ET_COURSE_OPEN obj) {
        // int colorNo = getColorNo();
        String sql = " INSERT INTO ET_COURSE_OPEN(YEAR,SEMESTER,COURSE_NO,CREDIT,STATUS_COURSE,INSERT_DATE)"
                + " VALUES(?,?,?,?,?,sysdate) ";

        String[] genCol = {"COURSE_NO"};
        int chk = db.insertRc(genCol, sql, obj.getYEAR(), obj.getSEMESTER(), obj.getCOURSE_NO(), obj.getCREDIT(), obj.getSTATUS_COURSE());

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

    public Boolean update(ET_COURSE_OPEN objval) {
        String sql = " ";
        int chkUpdate = db.update(sql);
        try {
            return chkUpdate > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public Boolean editCourse(ET_COURSE_OPEN obj,String x) {
        String sql = "update  ET_COURSE_OPEN set COURSE_NO = ?, CREDIT = ? , UPDATE_DATE = SYSDATE "
                + "WHERE COURSE_NO = ? AND SEMESTER = ? AND YEAR = ?";
        int chkUpdate = db.update(sql, obj.getCOURSE_NO(), obj.getCREDIT(), x, obj.getSEMESTER(), obj.getYEAR());
        try {
            return chkUpdate > 0;

//                return true; 
        } catch (Exception e) {
            return false;
        }

    }

    public Boolean delete(String cno, String sem, String year) {
        String sql = "delete from ET_COURSE_OPEN where COURSE_NO = ? AND SEMESTER = ? AND YEAR = ?";
        int chkDelete = db.remove3Val(sql, cno, sem, year);
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

    public int count() {
        int maxusr = 0;
        String sql = "select to_char(count(to_number(COURSE_NO)))STUDY_SEMESTER from ET_COURSE_OPEN";
        Map<String, Object> row = db.querySingle(sql);

        if (row != null) {
            maxusr = Integer.parseInt((String) row.get("COURSE_NO"));
            return maxusr;
        } else {
            return 0;
        }
    }//end get max no
}
