package com.et.model;

// import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class REP_ETEST101_TABLE {

    Database db;

    public REP_ETEST101_TABLE(Database db) {
        this.db = db;
    }

    public REP_ETEST101 setAltmodel(Map<String, Object> row) {

        if (row != null) {
            REP_ETEST101 getRow = REP_ETEST101.builder()
                    .RECEIPT_DATE((String) row.get("RECEIPT_DATE"))
                    .SLIP_NO((String) row.get("SLIP_NO"))
                    .STD_CODE((String) row.get("STD_CODE"))
                    .AMOUNT((String) row.get("AMOUNT"))
                    .TOTAL_AMOUNT((String) row.get("TOTAL_AMOUNT"))
                    .INSERT_DATE((String) row.get("INSERT_DATE"))
                    .REF_KEY((String) row.get("REF_KEY"))
                    .build();

            return getRow;
        } else {
            return null;
        }
    }

    public List<REP_ETEST101> findRepETest101(String year, String semester) {
        List<REP_ETEST101> list = new ArrayList<REP_ETEST101>();
        String sql = " SELECT DISTINCT TO_CHAR(A.INSERT_DATE, 'MM/DD/YYYY') AS RECEIPT_DATE "
                + " FROM QR_PAYMENT_CONFIRM_TMB A WHERE A.YEAR = '" + year + "' AND A.SEMESTER = '" + semester + "' "
                + " ORDER BY TO_CHAR(A.INSERT_DATE, 'MM/DD/YYYY') ASC";

        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));

        }
        return list;
    }
    //end find 

    public List<REP_ETEST101> RepETest101(String year, String semester, String repDate) {
        List<REP_ETEST101> list = new ArrayList<REP_ETEST101>();
        String sql = "SELECT TO_CHAR(A.INSERT_DATE, 'MM/DD/YYYY') AS INSERT_DATE,CAST(B.SLIP_NO AS VARCHAR2(10))AS SLIP_NO,A.STD_CODE,B.TOTAL_AMOUNT AS AMOUNT,B.REF_KEY "
                + "FROM QR_PAYMENT_CONFIRM_TMB  A LEFT JOIN ET_RECEIPT B ON A.QRID = B.REF_KEY "
                + "WHERE TRUNC(A.INSERT_DATE) = TO_DATE('"+repDate+"', 'MM/DD/YYYY') AND A.YEAR = '"+year+"' AND A.SEMESTER = '"+semester+"' AND B.RECEIPT_PAY_STATUS = '1' ORDER BY B.SLIP_NO ASC";

        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));

        }
        return list;
    }
    //end find 
    
    
}
