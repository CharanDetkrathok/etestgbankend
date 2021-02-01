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

public class ET_PROFILE_TABLE {

    Database db;

    public ET_PROFILE_TABLE(Database db) {
        this.db = db;
    }

    public ET_PROFILE setAltmodel(Map<String, Object> row) {

        if (row != null) {
            ET_PROFILE getRow = ET_PROFILE.builder()
                    .STD_CODE((String) row.get("STD_CODE"))
                    .NAME_THAI((String) row.get("NAME_THAI"))
                    .NAME_ENG((String) row.get("NAME_ENG"))
                    .BIRTH_DATE((String) row.get("BIRTH_DATE"))
                    .STD_STATUS_CURRENT((String) row.get("STD_STATUS_CURRENT"))
                    .CITIZEN_ID((String) row.get("CITIZEN_ID"))
                    .FACULTY_NO((String) row.get("FACULTY_NO"))
                    .FACULTY_NAME_THAI((String) row.get("FACULTY_NAME_THAI"))
                    .CURR_NAME_THAI((String) row.get("CURR_NAME_THAI"))
                    .MAJOR_NO((String) row.get("MAJOR_NO"))
                    .MAJOR_NAME_THAI((String) row.get("MAJOR_NAME_THAI"))
                    .MOBILE_TELEPHONE((String) row.get("MOBILE_TELEPHONE"))
                    .build();
            /* user.setXxtthh((Integer) row.get("USERID"));
             user.setUsersname((String) row.get("USERNAME"));
             user.setPassword((String) row.get("PASSWORD"));
             user.setPeRoid((Integer) row.get("PEROID"));
             user.setDateRep((String) row.get("DATE_REP"));*/
            return getRow;
        } else {
            return null;
        }
    }

    public List<ET_PROFILE> findAll() {
        List<ET_PROFILE> list = new ArrayList<ET_PROFILE>();
        String sql = "SELECT * FROM x ";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_PROFILE> findListRegisApproov(String year, String sem, String stdid) {
        List<ET_PROFILE> list = new ArrayList<ET_PROFILE>();
        String sql = "SELECT x";
        List<Map<String, Object>> result = db.queryList(sql, year, sem, stdid);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public ET_PROFILE findByStdProfile(String x) {
        String sql = " SELECT a.STD_CODE,a.NAME_THAI,a.NAME_ENG,a.BIRTH_DATE,a.STD_STATUS_CURRENT,"
                + " a.CITIZEN_ID,a.FACULTY_NO,a.FACULTY_NAME_THAI,a.CURR_NAME_THAI,a.MAJOR_NO, "
                + " a.MAJOR_NAME_THAI,b.MOBILE_TELEPHONE  FROM dbbach00.VM_student_web a, DBBACH00.VM_STUDENT_MOBILE  b "
                + " where A.STD_CODE = B.STD_CODE and "
                + " a.STD_CODE = ?";
        Map<String, Object> row = db.querySingle(sql, x);

        return setAltmodel(row);

    }

}
