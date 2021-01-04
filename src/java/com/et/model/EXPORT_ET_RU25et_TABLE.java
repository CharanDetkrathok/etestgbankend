package com.et.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EXPORT_ET_RU25et_TABLE {

    Database db;

    public EXPORT_ET_RU25et_TABLE(Database db) {
        this.db = db;
    }

    public EXPORT_ET_RU25et setAltmodel(Map<String, Object> row) {

        if (row != null) {
            EXPORT_ET_RU25et getRow = EXPORT_ET_RU25et.builder()
                    .columnn1((String) row.get("COLUMM1"))
                    .columnn2((String) row.get("COLUMM2"))
                    .columnn3((String) row.get("COLUMM3"))
                    .build();

            return getRow;
        } else {
            return null;
        }
    }

    public List<EXPORT_ET_RU25et> findExportEtRu25etAll(String year, String semester) {
        List<EXPORT_ET_RU25et> list = new ArrayList<EXPORT_ET_RU25et>();
        String sql = " SELECT DISTINCT A.STD_CODE, (A.STD_CODE||(case when length( A.PRENAME_NO ) < 2 THEN to_char('0' || A.PRENAME_NO) else to_char(A.PRENAME_NO)end) ||' '||A.FIRST_NAME_THAI||' '||LAST_NAME_THAI)COLUMM1,"
                + " (A.FIRST_NAME_ENG || (case when A.MIDDLE_NAME_ENG is null THEN ' ' else  A.MIDDLE_NAME_ENG end ) || A.LAST_NAME_ENG)COLUMM2,"
                + " (A.FACULTY_NO || A.MAJOR_NO || A.STD_STATUS_CURRENT || A.REGIS_OK || TO_CHAR(a.BIRTH_DATE, 'ddmmyyyy','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')|| (case when A.PENAL_NO is null THEN '0' else  A.PENAL_NO end) || A.CAMPUS_NO || (case when A.ENROLLMENT_NO is null THEN to_char('00') else to_char(A.ENROLLMENT_NO) end)|| (case when A.SUBSIDY_NO is null THEN to_char('00')  when  length(A.SUBSIDY_NO) < 2 THEN to_char('0')||A.SUBSIDY_NO  else to_char(A.SUBSIDY_NO) end)|| (case when length(A.REGIONAL_NO) < 2 THEN to_char('0' || A.REGIONAL_NO) else  to_char(A.REGIONAL_NO)end)||( case when A.LIBRARY_LOCK is null THEN to_char( '0' ) else A.LIBRARY_LOCK end)||"
                + " (SELECT STD_CHECK_DIGIT(A.STD_CODE)RESULT FROM DUAL))COLUMM3"
                + " FROM  DBBACH00.VM_STUDENT_MOBILE A LEFT JOIN ET_RECEIPT B ON A.STD_CODE = B.STD_CODE LEFT JOIN QR_PAYMENT_CONFIRM_TMB D ON B.STD_CODE = D.STD_CODE WHERE B.RECEIPT_YEAR = '" + year + "' AND B.RECEIPT_SEMESTER = '" + semester + "' AND B.RECEIPT_PAY_STATUS = '1' AND B.REF_KEY = D.QRID ORDER BY A.STD_CODE ASC";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));

        }
        return list;
    }
    //end find 
}
