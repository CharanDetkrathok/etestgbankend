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

/**
 *
 * @author awong
 */
public class QR_PAYMENT_CONFIRM_TABLE_TMB {
    Database db;

    public QR_PAYMENT_CONFIRM_TABLE_TMB(Database db) {
        this.db = db;
    }
    
    public QR_PAYMENT_CONFIRM setAltmodel(Map<String, Object> row) {

        if (row != null) {
            QR_PAYMENT_CONFIRM getRow = QR_PAYMENT_CONFIRM.builder()
                    .BANKREF((String) row.get("BANKREF"))
                    .BILLERNO((String) row.get("BILLERNO"))
                    .REF1((String) row.get("REF1"))
                    .REF2((String) row.get("REF2"))
                    .QRID((String) row.get("QRID"))
                    .PAYERNAME((String) row.get("PAYERNAME"))
                    .PAYERBANK((String) row.get("PAYERBANK"))
                    .FILLER((String) row.get("FILLER"))
                    .AMOUNT((String) row.get("AMOUNT"))
                    .RESULTCODE((String) row.get("RESULTCODE"))
                    .RESULTDESC((String) row.get("RESULTDESC"))
                    .TRANSDATE((String) row.get("TRANSDATE"))
                    .INSERT_DATE((String) row.get("INSERT_DATE"))
                    .UAT_STATUS((String) row.get("UAT_STATUS"))
                    .STD_CODE((String) row.get("STD_CODE"))
                    .SYSTEM_ID((String) row.get("SYSTEM_ID"))
                    .YEAR((String) row.get("YEAR"))
                    .SEMESTER((String) row.get("SEMESTER"))
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
    
    public List<QR_PAYMENT_CONFIRM> findPaymentDateReport() {
        List<QR_PAYMENT_CONFIRM> list = new ArrayList<QR_PAYMENT_CONFIRM>();
        String sql = "SELECT  distinct TO_CHAR(INSERT_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE "
                + " FROM  QR_PAYMENT_CONFIRM_TMB order by INSERT_DATE asc ";
       // String sql = "SELECT distinct(trunc(EXAM_DATE)),TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE FROM  ET_EXAM_DATE ";

        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 
    
       public QR_PAYMENT_CONFIRM findx() {
        String sql = "SELECT * FROM ET_EXAM_DATE";
        Map<String, Object> row = db.querySingle(sql);

        return setAltmodel(row);

    } 
}
