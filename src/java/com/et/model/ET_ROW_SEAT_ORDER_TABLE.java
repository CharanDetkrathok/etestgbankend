/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ru-com7
 */
public class ET_ROW_SEAT_ORDER_TABLE {

    Database db;

    public ET_ROW_SEAT_ORDER_TABLE(Database db) {
        this.db = db;
    }

    public ET_ROW_SEAT_ORDER setAltmodel(Map<String, Object> row) {

        if (row != null) {
            ET_ROW_SEAT_ORDER getRow = ET_ROW_SEAT_ORDER.builder()
                    .YEAR((String) row.get("YEAR"))
                    .SEMESTER((String) row.get("SEMESTER"))
                    .INSERT_DATE((String) row.get("INSERT_DATE"))
                    .ROW_SEAT((String) row.get("ROW_SEAT"))
                    .STD_CODE((String) row.get("STD_CODE"))
                    .EXAM_DATE((String) row.get("EXAM_DATE"))
                    .SECTION_NO((String) row.get("SECTION_NO"))
                    .CREDIT((BigDecimal) row.get("CREDIT"))
                    .COURSE_NO((String) row.get("COURSE_NO"))
                    .STATUS_COURSE((String) row.get("STATUS_COURSE"))
                    .build();

            return getRow;
        } else {
            return null;
        }
    }

    public List<ET_ROW_SEAT_ORDER> findAll() {
        List<ET_ROW_SEAT_ORDER> list = new ArrayList<ET_ROW_SEAT_ORDER>();
        String sql = "SELECT YEAR, SEMESTER,"
                + "TO_CHAR(INSERT_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE,"
                + " ROW_SEAT,STD_CODE, "
                + "TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE,SECTION_NO, "
                + "CREDIT,COURSE_NO,STATUS_COURSE "
                + " FROM ET_ROW_SEAT_ORDER ";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public ET_ROW_SEAT_ORDER findx(String x) {
        String sql = "SELECT YEAR, SEMESTER,"
                + "TO_CHAR(INSERT_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE,"
                + " ROW_SEAT,STD_CODE FROM ET_ROW_SEAT_ORDER ";
        Map<String, Object> row = db.querySingle(sql, x);

        return setAltmodel(row);

    }
    
     public List<ET_ROW_SEAT_ORDER> findBySemYearDateForExportTextFile(String sem, String year,String datex) {
        List<ET_ROW_SEAT_ORDER> list = new ArrayList<ET_ROW_SEAT_ORDER>();
        String sql = "SELECT YEAR, SEMESTER,"
                + "TO_CHAR(INSERT_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE,"
                + " ROW_SEAT,STD_CODE, "
                + "TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE,SECTION_NO, "
                + " CREDIT,COURSE_NO,STATUS_COURSE "
                + " FROM ET_ROW_SEAT_ORDER where EXAM_DATE = to_date('"+ datex +"','dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') and "
                + " YEAR = '"+ year +"' and SEMESTER = '"+ sem +"' ";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    
    public boolean insert(ET_ROW_SEAT_ORDER obj) {
        // int colorNo = getColorNo();
        String sql = "insert into ET_ROW_SEAT_ORDER(YEAR,SEMESTER,INSERT_DATE,ROW_SEAT,STD_CODE,EXAM_DATE,SECTION_NO,CREDIT,COURSE_NO,STATUS_COURSE) "
                + " values(?,?,SYSDATE,?,?,to_date(?,'dd/mm/yyyy'),?,?,?,?)";

        String[] genCol = {"STD_CODE"};
        int chk = db.insertRc(genCol, sql, obj.getYEAR(), obj.getSEMESTER(), obj.getROW_SEAT(), obj.getSTD_CODE(),
                obj.getEXAM_DATE(), obj.getSECTION_NO(),obj.getCREDIT() , obj.getCOURSE_NO(), obj.getSTATUS_COURSE());

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

    public Boolean update(ET_ROW_SEAT_ORDER obj) {
        String sql = "update ET_ROW_SEAT_ORDER set ROW_EXAM = ?,SEAT_EXAM = ?, UPDATE_DATE = SYSDATE "
                + " where STD_CODE = ? and COURSE_NO = ? and YEAR = ? and SEMESTER = ? and EXAM_DATE = ?";
        int chkUpdate = db.update(sql);
        try {
            return chkUpdate > 0;

        } catch (Exception e) {
            return false;
        }

    }

    public Boolean delete(String year, String sem) {
        String sql = "delete from ET_ROW_SEAT_ORDER ";
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

    public Boolean chkdelete(String year, String sem, String exdate, String sec) {
        String sql = "delete from ET_ROW_SEAT_ORDER where  YEAR = ? and SEMESTER = ? "
                + "and EXAM_DATE = to_date(?, 'dd/mm/yyyy') and SECTION_NO = ?";
        int chkDelete = db.remove4Val(sql, year, sem, exdate, sec);
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
    
    public Boolean chkdeleteToSetSeatRowOrder(String year, String sem, String exdate, String sec) {
        String sql = "delete from ET_ROW_SEAT_ORDER where  YEAR = ? and SEMESTER = ? ";
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

    public Boolean chkdeleteAll(String year, String sem, String exdate) {
        String sql = "delete from ET_ROW_SEAT_ORDER where  YEAR = ? and SEMESTER = ? "
                + "and EXAM_DATE = to_date(?, 'dd/mm/yyyy') ";
        int chkDelete = db.remove3Val(sql, year, sem, exdate);
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

    public int countStudent(String year, String sem, String exdate, String sec) {
        int maxusr = 0;
        String sql = "select to_char(count(to_number(STD_CODE)))STD_CODE from ET_ROW_SEAT_ORDER where  YEAR = ? and SEMESTER = ? "
                + "and EXAM_DATE = to_date(?, 'dd/mm/yyyy')  and SECTION_NO = ?";
        Map<String, Object> row = db.querySingle(sql, year, sem, exdate, sec);

        if (row != null) {
            maxusr = Integer.parseInt((String) row.get("STD_CODE"));
            return maxusr;
        } else {
            return 0;
        }
    }//end get max no
    
    public int countStudentToSetSeatRowOrder(String year, String sem, String exdate, String sec) {
        int maxusr = 0;
        String sql = "select to_char(count(to_number(STD_CODE)))STD_CODE from ET_ROW_SEAT_ORDER where  YEAR = ? and SEMESTER = ? ";
        Map<String, Object> row = db.querySingle(sql, year, sem);

        if (row != null) {
            maxusr = Integer.parseInt((String) row.get("STD_CODE"));
            return maxusr;
        } else {
            return 0;
        }
    }//end get max no
}
