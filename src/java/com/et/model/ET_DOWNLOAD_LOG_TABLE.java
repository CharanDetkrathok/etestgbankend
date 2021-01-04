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
 * @author ru-com7
 */
public class ET_DOWNLOAD_LOG_TABLE {
     Database db;

    public ET_DOWNLOAD_LOG_TABLE(Database db) {
        this.db = db;
    }

    public ET_DOWNLOAD_LOG setAltmodel(Map<String, Object> row) {

        if (row != null) {
            ET_DOWNLOAD_LOG getRow = ET_DOWNLOAD_LOG.builder()
                    .STD_CODE((String) row.get("STD_CODE"))
                    .YEAR((String) row.get("YEAR"))
                    .SEMESTER((String) row.get("SEMESTER"))
                    .INSERT_DATE((String) row.get("INSERT_DATE"))
                    .SLIPT_NO((BigDecimal) row.get("SLIPT_NO"))
                    .REF_KEY((String) row.get("REF_KEY"))
                    .DOWNLOAD_NO((BigDecimal) row.get("DOWNLOAD_NO")) 
                    .build();

            return getRow;
        } else {
            return null;
        }
    }

    public List<ET_DOWNLOAD_LOG> findAll() {
        List<ET_DOWNLOAD_LOG> list = new ArrayList<ET_DOWNLOAD_LOG>();
        String sql = "SELECT STD_CODE,YEAR,SEMESTER,"
                + "TO_CHAR(INSERT_DATE, 'dd/mm/yyyy H24:MI', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE"
                + ",SLIPT_NO,REF_KEY,DOWNLOAD_NO FROM ET_DOWNLOAD_LOG  ";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_DOWNLOAD_LOG> findBylist(String x) {
        List<ET_DOWNLOAD_LOG> list = new ArrayList<ET_DOWNLOAD_LOG>();
        String sql = "SELECT * FROM  ET_DOWNLOAD_LOG  ";
        List<Map<String, Object>> result = db.queryList(sql, x);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_DOWNLOAD_LOG> findBylistForChangeSeatExam(String year, String secmester, String examDate, String section) {
        List<ET_DOWNLOAD_LOG> list = new ArrayList<ET_DOWNLOAD_LOG>();
        String sql = "SELECT EXAM_SEAT FROM  ET_EXAM_SEAT  "
                   + "WHERE YEAR = ? AND SEMESTER = ? AND EXAM_DATE = TO_DATE( ?, 'mm/dd/yyyy' ) AND PERIOD = ?";
        List<Map<String, Object>> result = db.queryList(sql, year, secmester, examDate, section);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public ET_DOWNLOAD_LOG findbyData() {
        String sql = "SELECT * FROM  ET_DOWNLOAD_LOG  ";
        Map<String, Object> row = db.querySingle(sql);

        return setAltmodel(row);

    }

    public boolean insert(ET_DOWNLOAD_LOG obj) {
        // int colorNo = getColorNo();
        String sql = " INSERT INTO ET_DOWNLOAD_LOG(STD_CODE,YEAR,SEMESTER,INSERT_DATE,SLIPT_NO,REF_KEY,DOWNLOAD_NO) "
                + "VALUES(?,?,?,TO_DATE(SYSDATE, 'mm/dd/yyyy hh24:mi:ss'),?,?,?) ";

        String[] genCol = {"ID"};
        int chk = db.insertRc(genCol, sql, obj.getSTD_CODE(), obj.getYEAR(), obj.getSEMESTER(), obj.getSLIPT_NO(), 
                obj.getREF_KEY(), obj.getDOWNLOAD_NO());

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

    public Boolean update(ET_DOWNLOAD_LOG objval, String sumSeat) {
        String sql = "UPDATE ET_EXAM_SEAT SET ET_DOWNLOAD_LOG = ?";
        int chkUpdate = db.update(sql, sumSeat);
        try {
            return chkUpdate > 0;

        } catch (Exception e) {
            return false;
        }

    } 

    public Boolean delete(String year, String semester, String exam_date) {
        String sql = "DELETE FROM ET_DOWNLOAD_LOG WHERE YEAR = ? AND SEMESTER = ? AND insert_DATE = TO_DATE( ?, 'mm/dd/yyyy' )";
        int chkDelete = db.remove3Val(sql, year, semester, exam_date);
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

    public int countCheckDownloadNo(String stdcode,String year,String semester,BigDecimal sliptno) {
        int maxusr = 0;
        String sql = "select to_char(count(to_number(STD_CODE)))STD_CODE from ET_DOWNLOAD_LOG where ET_DOWNLOAD_NO = ? "
                + "and year = ? and semester = ? and slipt_no = ?";
        Map<String, Object> row = db.querySingle(sql, stdcode,year,semester,sliptno);

        if (row != null) {
            maxusr = Integer.parseInt((String) row.get("STD_CODE"));
            return maxusr;
        } else {
            return 0;
        }
    }//end get max no
}
