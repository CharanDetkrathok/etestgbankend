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

import lombok.*;

@Data
public class ET_REP_SLIP_TABLE {

    Database db;

    public ET_REP_SLIP_TABLE(Database db) {
        this.db = db;
    }

    public ET_REP_SLIP setAltmodel(Map<String, Object> row) {

        if (row != null) {
            ET_REP_SLIP getRow = ET_REP_SLIP.builder()
                    .STD_CODE((String) row.get("STD_CODE"))
                    .YEAR((String) row.get("YEAR"))
                    .SEMESTER((String) row.get("SEMESTER"))
                    .QRID((String) row.get("QRID"))
                    .INSERT_DATE((String) row.get("INSERT_DATE"))
                    .AMOUNT((String) row.get("AMOUNT"))
                    .COURSE_NO((String) row.get("COURSE_NO"))
                    .CREDIT((BigDecimal) row.get("CREDIT"))
                    .SECTION_NO((BigDecimal) row.get("SECTION_NO"))
                    .EXAM_DATE((String) row.get("EXAM_DATE"))
                    .INSERT_TIME((String) row.get("INSERT_TIME"))
                    .TOTAL_AMOUNT((String) row.get("TOTAL_AMOUNT"))
                    .INSERT_DATE_TIME((String) row.get("INSERT_DATE_TIME"))
                    .DATE_GENERATED((String) row.get("DATE_GENERATED"))
                    .SLIP_NO((BigDecimal) row.get("SLIP_NO"))
                    .run_no((String) row.get("RUN_NO"))
                    .REF_KEY((String) row.get("REF_KEY"))
                    .CHECKDIGIT((String) row.get("CHECKDIGIT"))
                    // .SLIP((String) row.get("CHECKDIGIT"))
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

    public List<ET_REP_SLIP> findAll() {
        List<ET_REP_SLIP> list = new ArrayList<ET_REP_SLIP>();
        String sql = "SELECT * FROM x ";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_REP_SLIP> findListRegisSlip(String stdid, String sem, String year, String refkey) {
        List<ET_REP_SLIP> list = new ArrayList<ET_REP_SLIP>();
        String sql = "select c.STD_CODE,c.YEAR,c.SEMESTER,c.QRID,"
                + "to_char(c.INSERT_DATE,'dd month yyyy hh24:mi','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE,"
                + "(to_char(c.INSERT_DATE,'hh24:mi','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') || ' น.' )INSERT_TIME,"
                + "c.AMOUNT,a.COURSE_NO,a.CREDIT,a.SECTION_NO,"
                + "to_char(a.EXAM_DATE,'dd/mm/yy','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE,TOTAL_AMOUNT "
                + " from ET_REGIS_RU24 a,ET_RECEIPT b,QR_PAYMENT_CONFIRM_TMB c "
                + " where A.STD_CODE = B.STD_CODE and b.STD_CODE = c.STD_CODE and A.SEMESTER = B.RECEIPT_SEMESTER and A.YEAR = B.RECEIPT_YEAR "
                + " and A.REF_KEY = B.REF_KEY and B.REF_KEY = C.QRID "
                + " and B.RECEIPT_SEMESTER = C.SEMESTER and B.RECEIPT_YEAR = c.year and "
                + " c.STD_CODE = ? and c.SEMESTER = ? and c.year = ? "
                + " and C.QRID = ? and b.RECEIPT_PAY_STATUS = '1' order by a.EXAM_DATE asc";
        List<Map<String, Object>> result = db.queryList(sql, stdid, sem, year, refkey);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public ET_REP_SLIP findByHeaderSlip(String stdid, String sem, String year, String refkey) {
        String sql = " select STD_CODE,YEAR,semester,qrid,"
                + "to_char(INSERT_DATE,'dd month yyyy','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE,"
                + "to_char(INSERT_DATE,'dd/mm/yyyy hh24:mi:ss','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE_TIME,"
                + "to_char(sysdate,'dd/mm/yyyy hh24:mi:ss','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')DATE_GENERATED,"
                + " (to_char(INSERT_DATE,'hh24:mi','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') || ' น.' )INSERT_TIME,AMOUNT "
                + " from QR_PAYMENT_CONFIRM_TMB "
                + " WHERE STD_CODE = ? and SEMESTER = ? and year = ?  and QRID = ? ";
        Map<String, Object> row = db.querySingle(sql, stdid, sem, year, refkey);

        return setAltmodel(row);

    }

    public ET_REP_SLIP mockSlip() {
        String sql = " select   ('&' || 'nbsp;')STD_CODE,  YEAR, ('&' || 'nbsp;')semester, "
                + "('&' || 'nbsp;')qrid, ('&' || 'nbsp;')INSERT_DATE,  ('&' || 'nbsp;')INSERT_DATE_TIME, ('&' || 'nbsp;')DATE_GENERATED,\n"
                + "('&' || 'nbsp;')INSERT_TIME, ('&' || 'nbsp;')AMOUNT \n"
                + "  from QR_PAYMENT_CONFIRM_TMB  where STD_CODE='111' ";
        Map<String, Object> row = db.querySingle(sql);

        return setAltmodel(row);

    }

    public ET_REP_SLIP genSlipRunNo(String stdid, String sem, String year, String refkey) {
        String sql = " SELECT  to_char(request_slipno.nextval,'fm00000')run_no  "
                + " from et_receipt "
                + " WHERE STD_CODE = ? and RECEIPT_SEMESTER = ? and RECEIPT_YEAR = ?  and REF_KEY = ? ";
        Map<String, Object> row = db.querySingle(sql, stdid, sem, year, refkey);

        return setAltmodel(row);

    }

    public ET_REP_SLIP findSlipRunNo(String stdid, String sem, String year, String refkey) {
        String sql = "SELECT to_char(slip_no,'fm00000')run_no  "
                + " from et_receipt "
                + " WHERE STD_CODE = ? and RECEIPT_SEMESTER = ? and RECEIPT_YEAR = ?  and REF_KEY = ? ";
        Map<String, Object> row = db.querySingle(sql, stdid, sem, year, refkey);

        return setAltmodel(row);
    }

    /*  public ET_REP_SLIP findCheckdigit(String stdid, String repno, String amount, String sem, String year, String refkey,String fisyear) {
       // String sql = " SELECT to_char(DIPSTD01.GET_UIC('" + stdid +"' ,'" + fisyear +"','567','" + repno +"','1','" + new BigDecimal(amount)+"'),'fm0000')CHECKDIGIT  "
                String sql = " SELECT to_char(DIPSTD01.GET_UIC('6306043677','2564','567','3','1','600.00'),'fm0000')CHECKDIGIT "
                + " from et_receipt "
                + " WHERE STD_CODE = ? and RECEIPT_SEMESTER = ? and RECEIPT_YEAR = ?  and REF_KEY = ? ";
        Map<String, Object> row = db.querySingle(sql, stdid, sem, year, refkey, fisyear);

        return setAltmodel(row);

    }  */
    public boolean insert(ET_REP_SLIP obj) {
        // int colorNo = getColorNo();
        String sql = "insert into ET_RECEIPT(YEAR,SEMESTER,EXAM_DATE,PERIOD,INSERT_DATE) "
                + " values(?,?,TO_DATE(?, 'mm/dd/yyyy hh24:mi:ss'),?,sysdate)";

        String[] genCol = {"EXAM_DATE"};
        int chk = db.insertRc(genCol, sql);

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

    public Boolean update(ET_REP_SLIP obj) {
        String sql = "update ET_RECEIPT set SLIP_NO = ? "
                + " where STD_CODE = ? and REF_KEY = ? and RECEIPT_SEMESTER = ? and RECEIPT_YEAR = ? ";
        int chkUpdate = db.update(sql, obj.getSLIP_NO(), obj.getSTD_CODE(), obj.getREF_KEY(),
                obj.getSEMESTER(), obj.getYEAR());
        try {
            return chkUpdate > 0;

        } catch (Exception e) {
            return false;
        }

    }

    public ET_REP_SLIP findCheckdigit(String stdcode, String fiscalyear, String repNumcourse, String total, String sem, String year, String refkey) {
        String sql = "SELECT  to_char(DIPSTD01.GET_UIC(? ,?,'567',?,'1',?),'fm0000')CHECKDIGIT"
                + " from et_receipt  WHERE std_code = ? "
                + " and RECEIPT_SEMESTER = ? and RECEIPT_YEAR = ? and  REF_KEY = ?";
        Map<String, Object> row = db.querySingle(sql, stdcode, fiscalyear, repNumcourse, total, stdcode, sem, year, refkey);

        return setAltmodel(row);
    }

    public ET_REP_SLIP findSlipNoByRef(String stdid, String sem, String year, String refkey) {
        String sql = "select to_char(max(to_number(SLIP_NO)),'fm000000')run_no from ET_RECEIPT where STD_CODE = ? and RECEIPT_SEMESTER = ?"
                + " and RECEIPT_year = ? and REF_KEY = ?";
        Map<String, Object> row = db.querySingle(sql, stdid, sem, year, refkey);

        return setAltmodel(row);

    }

    public ET_REP_SLIP findSlipNo(String stdid, String sem, String year, String refkey) {
        String sql = "select to_char(SLIP_NO,'fm000000')run_no from ET_RECEIPT where STD_CODE = ? and RECEIPT_SEMESTER = ?"
                + "  and RECEIPT_year = ? and REF_KEY  = ?";
        Map<String, Object> row = db.querySingle(sql, stdid, sem, year, refkey);

        return setAltmodel(row);

    }

    public int getMaxSlip() {
        int maxusr = 0;
        String sql = "select to_char(max(to_number(SLIP_NO)))SLIP_NO from et_receipt";
        Map<String, Object> row = db.querySingle(sql);

        if (row != null) {
            maxusr = Integer.parseInt((String) row.get("SLIP_NO"));
            return maxusr;
        } else {
            return 0;
        }

    }

    public ET_REP_SLIP genSlipRunNo() {
        // String sql = "select to_char(max(to_number(SLIP_NO) + 1),'fm00000')run_no from ET_RECEIPT";
        String sql = "select to_char((max(to_number(SLIP_NO)))+ 1,'fm000000')run_no from ET_RECEIPT";
        Map<String, Object> row = db.querySingle(sql);

        return setAltmodel(row);

    }

    public Boolean updateSlipt(ET_REP_SLIP obj) {
        String sql = "update ET_RECEIPT set SLIP_NO = ? "
                + " where STD_CODE = ? and REF_KEY = ? and RECEIPT_SEMESTER = ? and RECEIPT_YEAR = ? ";
        int chkUpdate = db.update(sql, obj.getSLIP_NO(), obj.getSTD_CODE(), obj.getREF_KEY(),
                obj.getSEMESTER(), obj.getYEAR());
        try {
            return chkUpdate > 0;

        } catch (Exception e) {
            return false;
        }

    }

}
