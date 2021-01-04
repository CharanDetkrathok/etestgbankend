package com.et.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EXPORT_ET_STDC_TABLE {

    Database db;

    public EXPORT_ET_STDC_TABLE(Database db) {
        this.db = db;
    }

    public EXPORT_ET_STDC setAltmodel(Map<String, Object> row) {

        if (row != null) {
            EXPORT_ET_STDC getRow = EXPORT_ET_STDC.builder()
                    .YEAR((String) row.get("YEAR"))
                    .SEMESTER((String) row.get("SEMESTER"))
                    .STD_CODE((String) row.get("STD_CODE"))
                    .COURSE_NO((String) row.get("COURSE_NO"))
                    .CREDIT((BigDecimal) row.get("CREDIT"))
                    .SECTION_NO((String) row.get("SECTION_NO"))
                    .EXAM_DATE((String) row.get("EXAM_DATE"))
                    .ROW_SEAT((String) row.get("ROW_SEAT"))
                    .build();

            return getRow;
        } else {
            return null;
        }
    }

    public List<EXPORT_ET_STDC> findExportEtSTDCAll(String year, String secmester) {
        List<EXPORT_ET_STDC> list = new ArrayList<EXPORT_ET_STDC>();
        String sql = " SELECT DISTINCT A.YEAR, A.SEMESTER,  A.STD_CODE,  A.COURSE_NO, A.CREDIT, A.SECTION_NO, TO_CHAR(A.EXAM_DATE, 'ddMMyy')EXAM_DATE, A.ROW_SEAT"
                + " FROM ET_ROW_SEAT_ORDER A,ET_RECEIPT B,QR_PAYMENT_CONFIRM_TMB C WHERE A.YEAR = '" + year + "' AND A.SEMESTER = '" + secmester + "' AND B.RECEIPT_PAY_STATUS = '1' "
                + " AND A.STD_CODE = B.STD_CODE AND B.REF_KEY = C.QRID AND TO_NUMBER(B.TOTAL_AMOUNT) = TO_NUMBER(C.AMOUNT) ORDER BY EXAM_DATE,A.STD_CODE ASC";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

}
