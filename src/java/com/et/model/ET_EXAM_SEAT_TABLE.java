package com.et.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ET_EXAM_SEAT_TABLE {

    Database db;

    public ET_EXAM_SEAT_TABLE(Database db) {
        this.db = db;
    }

    public ET_EXAM_SEAT setAltmodel(Map<String, Object> row) {

        if (row != null) {
            ET_EXAM_SEAT getRow = ET_EXAM_SEAT.builder()
                    .ID((String) row.get("ID"))
                    .PERIOD((String) row.get("PERIOD"))
                    .EXAM_DATE((String) row.get("EXAM_DATE"))
                    .EXAM_SEAT((String) row.get("EXAM_SEAT"))
                    .CREATE_DATE((String) row.get("CREATE_DATE"))
                    .INSERT_USER((String) row.get("INSERT_USER"))
                    .UPDATE_DATE((String) row.get("UPDATE_DATE"))
                    .YEAR((String) row.get("YEAR"))
                    .SEMESTER((String) row.get("SEMESTER"))
                    .BUILD((String) row.get("BUILD"))
                    .build();

            return getRow;
        } else {
            return null;
        }
    }

    public List<ET_EXAM_SEAT> findAll() {
        List<ET_EXAM_SEAT> list = new ArrayList<ET_EXAM_SEAT>();
        String sql = "SELECT ID,PERIOD,"
                + "TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE,EXAM_SEAT,"
                + "TO_CHAR(CREATE_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')CREATE_DATE,INSERT_USER,"
                + "TO_CHAR(UPDATE_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')UPDATE_DATE, "
                + "YEAR,SEMESTER,BUILD FROM  ET_EXAM_SEAT  ";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_EXAM_SEAT> findBylist(String x) {
        List<ET_EXAM_SEAT> list = new ArrayList<ET_EXAM_SEAT>();
        String sql = "SELECT ID,PERIOD"
                + "TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE,EXAM_SEAT,"
                + "TO_CHAR(CREATE_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')CREATE_DATE,INSERT_USER,"
                + "TO_CHAR(UPDATE_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')UPDATE_DATE, "
                + "YEAR,SEMESTER,BUILD FROM  ET_EXAM_SEAT  ";
        List<Map<String, Object>> result = db.queryList(sql, x);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_EXAM_SEAT> findBylistForChangeSeatExam(String year, String secmester, String examDate, String section) {
        List<ET_EXAM_SEAT> list = new ArrayList<ET_EXAM_SEAT>();
        String sql = "SELECT EXAM_SEAT FROM  ET_EXAM_SEAT  "
                   + "WHERE YEAR = ? AND SEMESTER = ? AND EXAM_DATE = TO_DATE( ?, 'mm/dd/yyyy' ) AND PERIOD = ?";
        List<Map<String, Object>> result = db.queryList(sql, year, secmester, examDate, section);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public ET_EXAM_SEAT findbyData() {
        String sql = "SELECT ID,PERIOD"
                + "TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE,EXAM_SEAT,"
                + "TO_CHAR(CREATE_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')CREATE_DATE,INSERT_USER,"
                + "TO_CHAR(UPDATE_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')UPDATE_DATE, "
                + "YEAR,SEMESTER,BUILD FROM  ET_EXAM_SEAT  ";
        Map<String, Object> row = db.querySingle(sql);

        return setAltmodel(row);

    }

    public boolean insert(ET_EXAM_SEAT obj) {
        // int colorNo = getColorNo();
        String sql = " INSERT INTO ET_EXAM_SEAT(ID,PERIOD,EXAM_DATE,EXAM_SEAT,CREATE_DATE,YEAR,SEMESTER) "
                + "VALUES(REQID_COURSE_OPEN.NEXTVAL,?,TO_DATE(?, 'mm/dd/yyyy hh24:mi:ss'),?,sysdate,?,?) ";

        String[] genCol = {"ID"};
        int chk = db.insertRc(genCol, sql, obj.getPERIOD(), obj.getEXAM_DATE(), obj.getEXAM_SEAT(), obj.getYEAR(), obj.getSEMESTER());

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

    public Boolean update(ET_COURSE_OPEN objval, String sumSeat) {
        String sql = "UPDATE ET_EXAM_SEAT SET EXAM_SEAT = ?";
        int chkUpdate = db.update(sql, sumSeat);
        try {
            return chkUpdate > 0;

        } catch (Exception e) {
            return false;
        }

    }

    public Boolean updateSumSeat(String sumSeat) {
        String sql = "UPDATE ET_EXAM_SEAT SET EXAM_SEAT = ?";
        int chkUpdate = db.update(sql, sumSeat);
        try {
            return chkUpdate > 0;

        } catch (Exception e) {
            return false;
        }

    }
    
    public Boolean updateNewSeatBySection(String newSeatExam, String year, String secmester, String examDate, String section) {
        String sql = " UPDATE ET_EXAM_SEAT SET UPDATE_DATE = sysdate, EXAM_SEAT = ?"
                   + " WHERE YEAR = ? AND SEMESTER = ? AND EXAM_DATE = TO_DATE( ?, 'mm/dd/yyyy' ) AND PERIOD = ?";
        int chkUpdate = db.update(sql, newSeatExam, year, secmester, examDate, section);
        try {
            return chkUpdate > 0;

        } catch (Exception e) {
            return false;
        }

    }
    
    public Boolean updateNewSeatAllSection(String newSeatExam, String year, String secmester, String examDate) {
        String sql = " UPDATE ET_EXAM_SEAT SET UPDATE_DATE = sysdate, EXAM_SEAT = ?"
                   + " WHERE YEAR = ? AND SEMESTER = ? AND EXAM_DATE = TO_DATE( ?, 'mm/dd/yyyy' )";
        int chkUpdate = db.update(sql, newSeatExam, year, secmester, examDate);
        try {
            return chkUpdate > 0;

        } catch (Exception e) {
            return false;
        }

    }

    public boolean checkDuplicate(String exam_date) {

        String sql = "SELECT YEAR FROM ET_EXAM_SEAT WHERE EXAM_DATE = TO_DATE(?, 'mm/dd/yyyy hh24:mi:ss')";
        Map<String, Object> row = db.querySingle(sql, exam_date);

        ET_EXAM_SEAT chk = setAltmodel(row);

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

    public Boolean delete(String year, String semester, String exam_date) {
        String sql = "DELETE FROM ET_EXAM_SEAT WHERE YEAR = ? AND SEMESTER = ? AND EXAM_DATE = TO_DATE( ?, 'mm/dd/yyyy' )";
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
    
        public Boolean deleteAll() {
        String sql = "DELETE FROM ET_EXAM_SEAT";
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

    public int count() {
        int maxusr = 0;
        String sql = "select to_char(count(to_number(COURSE_NO)))STUDY_SEMESTER from ET_EXAM_SEAT";
        Map<String, Object> row = db.querySingle(sql);

        if (row != null) {
            maxusr = Integer.parseInt((String) row.get("COURSE_NO"));
            return maxusr;
        } else {
            return 0;
        }
    }//end get max no

}
