/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.model;

// import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class ET_COUNTER_ADMIN_TABLE {
    
    Database db;

    public ET_COUNTER_ADMIN_TABLE(Database db) {
        this.db = db;
    }

       public ET_COUNTER_ADMIN setAltmodel(Map<String, Object> row) {

        if (row != null) {
            ET_COUNTER_ADMIN getRow = ET_COUNTER_ADMIN.builder()
                    .FISCAL_YEAR((String) row.get("FISCAL_YEAR"))
                    .STUDY_YEAR((String) row.get("STUDY_YEAR"))
                    .STUDY_SEMESTER((String) row.get("STUDY_SEMESTER"))
                    .COUNTER_NO((String) row.get("COUNTER_NO"))
                    .START_DATE((String) row.get("START_DATE"))
                    .END_DATE((String) row.get("END_DATE"))
                    .UPDATE_DATE((String) row.get("UPDATE_DATE"))
                    .UPDATE_DATE((String) row.get("TYPE_COUNTER"))
                    .build();

            return getRow;
        } else {
            return null;
        }
    }

    public List<ET_COUNTER_ADMIN> findAll() {
        List<ET_COUNTER_ADMIN> list = new ArrayList<ET_COUNTER_ADMIN>();
        String sql = "SELECT FISCAL_YEAR,STUDY_YEAR,STUDY_SEMESTER,COUNTER_NO,"
                + "TO_CHAR(START_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')START_DATE,"
                + "TO_CHAR(END_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')END_DATE ,"
                + "START_TIME, END_TIME,TYPE_COUNTER "
                + "FROM  ET_COUNTER_ADMIN  ";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_COUNTER_ADMIN> findBylist(String x) {
        List<ET_COUNTER_ADMIN> list = new ArrayList<ET_COUNTER_ADMIN>();
        String sql = "SELECT select FISCAL_YEAR,STUDY_YEAR,STUDY_SEMESTER,TYPE_COUNTER FROM ET_COUNTER_ADMIN ";
        List<Map<String, Object>> result = db.queryList(sql, x);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public ET_COUNTER_ADMIN findCounterData() {
        String sql = "SELECT FISCAL_YEAR,STUDY_YEAR,STUDY_SEMESTER,COUNTER_NO FROM ET_COUNTER_ADMIN";
        Map<String, Object> row = db.querySingle(sql);

        return setAltmodel(row);

    }

    public boolean insert(ET_COUNTER_ADMIN objval) {
        // int colorNo = getColorNo();
        String sql = "insert into XINT_COUNTER_ADMIN(STUDY_YEAR,STUDY_SEMESTER,START_DATE,END_DATE) "
                + "values(?,?,TO_DATE(?, 'yyyy-mm-dd', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI'),"
                + "TO_DATE(?, 'yyyy-mm-dd', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI'))";

        String[] genCol = {"STD_CODE"};
        int chk = db.insertRc(genCol, sql, objval.getSTUDY_YEAR(), objval.getSTUDY_SEMESTER(), objval.getSTART_DATE(), objval.getEND_DATE());

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

    public Boolean update(ET_COUNTER_ADMIN objval) {
        String sql = "update ET_COUNTER_ADMIN set FISCAL_YEAR = ?,STUDY_YEAR = ?, STUDY_SEMESTER = ? ,"
                + "UPDATE_DATE = SYSDATE";
        int chkUpdate = db.update(sql, objval.getFISCAL_YEAR(),objval.getSTUDY_YEAR(), objval.getSTUDY_SEMESTER());
        try {
            return chkUpdate > 0;

        } catch (Exception e) {
            return false;
        }

    }

    public Boolean delete(String year, String sem) {
        String sql = "delete from XINT_COUNTER_ADMIN where STUDY_YEAR = ? and STUDY_SEMESTER = ?";
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
