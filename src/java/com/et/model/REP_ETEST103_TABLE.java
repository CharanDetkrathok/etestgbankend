/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class REP_ETEST103_TABLE {

    Database db;

    public REP_ETEST103_TABLE(Database db) {
        this.db = db;
    }

    public REP_ETEST103 setAltmodel(Map<String, Object> row) {

        if (row != null) {
            REP_ETEST103 getRow = REP_ETEST103.builder()
                    .RECEIPT_DATE((String) row.get("RECEIPT_DATE"))
                    .TOTAL_STD((String) row.get("TOTAL_STD"))
                    .TOTAL_AMOUNT((String) row.get("TOTAL_AMOUNT"))
                    .STR_TOTAL_STD((String) row.get("STR_TOTAL_STD"))
                    .STR_TOTAL_AMOUNT((String) row.get("STR_TOTAL_AMOUNT"))
                    .build();

            return getRow;
        } else {
            return null;
        }
    }

    public List<REP_ETEST103> findRepETest103(String year, String semester) {
        List<REP_ETEST103> list = new ArrayList<REP_ETEST103>();
        String sql = " SELECT DISTINCT TO_CHAR(A.INSERT_DATE, 'MM/DD/YYYY') AS RECEIPT_DATE "
                + " FROM QR_PAYMENT_CONFIRM_TMB  A WHERE A.YEAR = '" + year + "' AND A.SEMESTER = '" + semester + "' "
                + " ORDER BY TO_CHAR(A.INSERT_DATE, 'MM/DD/YYYY') ASC";

        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));

        }
        return list;
    }
    //end find 

    public String RepETest103SumTotalAmount(String year, String semester, String repDate) {
        String total = "0";
        String sql = "SELECT CAST(SUM(A.AMOUNT)AS VARCHAR2(10)) AS TOTAL_AMOUNT "
                + "FROM QR_PAYMENT_CONFIRM_TMB A LEFT JOIN ET_RECEIPT B ON A.QRID = B.REF_KEY "
                + "WHERE TRUNC(A.INSERT_DATE) = TO_DATE(?, 'MM/DD/YYYY') AND A.YEAR = ? AND A.SEMESTER = ? AND B.RECEIPT_PAY_STATUS = '1'";
        Map<String, Object> row = db.querySingle(sql, repDate, year, semester);

        if (row != null) {
            total = (String) row.get("TOTAL_AMOUNT");
            return total;
        } else {
            return "null data";
        }

    }
    //end find 

    public String RepETest103SumTotalStudents(String year, String semester, String repDate) {
        String total = "0";
        String sql = "SELECT CAST(COUNT(A.STD_CODE)AS VARCHAR2(10)) AS TOTAL_STD "
                + "FROM QR_PAYMENT_CONFIRM_TMB A LEFT JOIN ET_RECEIPT B ON A.QRID = B.REF_KEY "
                + "WHERE TRUNC(A.INSERT_DATE) = TO_DATE(?, 'MM/DD/YYYY') AND A.YEAR = ? AND A.SEMESTER = ? AND B.RECEIPT_PAY_STATUS = '1'";
        Map<String, Object> row = db.querySingle(sql, repDate, year, semester);

        if (row != null) {
            total = (String) row.get("TOTAL_STD");
            return total;
        } else {
            return "null data";
        }

    }
    //end find 

}
