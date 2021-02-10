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

/**
 *
 * @author awong
 */
public class ET_RECEIPT_TABLE {

    Database db;

    public ET_RECEIPT_TABLE(Database db) {
        this.db = db;
    }

    public ET_RECEIPT setAltmodel(Map<String, Object> row) {

        if (row != null) {
            ET_RECEIPT getRow = ET_RECEIPT.builder()
                    .NAME_THAI((String) row.get("NAME_THAI"))
                    .FISCAL_YEAR((String) row.get("FISCAL_YEAR"))
                    .RECEIPT_NO((String) row.get("RECEIPT_NO"))
                    .STD_CODE((String) row.get("STD_CODE"))
                    .RECEIPT_YEAR((String) row.get("RECEIPT_YEAR"))
                    .RECEIPT_SEMESTER((String) row.get("RECEIPT_SEMESTER"))
                    .RECEIPT_DATE((String) row.get("CRRECEIPT_DATEEDIT"))
                    .RECEIPT_TIME((String) row.get("RECEIPT_TIME"))
                    .RECEIPT_PERIOD((String) row.get("RECEIPT_PERIOD"))
                    .TOTAL_AMOUNT((String) row.get("TOTAL_AMOUNT"))
                    .RECEIPT_TYPE((String) row.get("RECEIPT_TYPE"))
                    .RECEIPT_STATUS((String) row.get("RECEIPT_STATUS"))
                    .REGIONAL_NO((BigDecimal) row.get("REGIONAL_NO"))
                    .CASH_AMOUNT((BigDecimal) row.get("CASH_AMOUNT"))
                    .CHEQUE_AMOUNT((BigDecimal) row.get("CHEQUE_AMOUNT"))
                    .CREDIT_AMOUNT((BigDecimal) row.get("CREDIT_AMOUNT"))
                    .REGIS_METHOD((BigDecimal) row.get("REGIS_METHOD"))
                    .REGIS_CK((BigDecimal) row.get("REGIS_CK"))
                    .NEAR_GRADUATE((String) row.get("NEAR_GRADUATE"))
                    .PAYMENT_CODE((String) row.get("PAYMENT_CODE"))
                    .ACCOUNT_NUMBER((String) row.get("ACCOUNT_NUMBER"))
                    .BANK_FEE((BigDecimal) row.get("BANK_FEE"))
                    .RECEIPT_PAY_STATUS((String) row.get("RECEIPT_PAY_STATUS"))
                    .REGIS_GROUP_NO((BigDecimal) row.get("REGIS_GROUP_NO"))
                    .REGIS_DATE((String) row.get("REGIS_DATE"))
                    .TOTAL_AMOUNT_BANK((BigDecimal) row.get("TOTAL_AMOUNT_BANK"))
                    .BANK_VAT((BigDecimal) row.get("BANK_VAT"))
                    .BANK_TOTAL((BigDecimal) row.get("BANK_TOTAL"))
                    .NOMATCH_MONEY((BigDecimal) row.get("NOMATCH_MONEY"))
                    .BANK_DATE((String) row.get("BANK_DATE"))
                    .ADD_PAY((BigDecimal) row.get("ADD_PAY"))
                    .ADD_BANK_DATE((String) row.get("ADD_BANK_DATE"))
                    .EXAM_LOCATION_NO((BigDecimal) row.get("EXAM_LOCATION_NO"))
                    .WAIVED_NO((String) row.get("WAIVED_NO"))
                    .WAIVED_CR((BigDecimal) row.get("WAIVED_CR"))
                    .CASHIER_NO((String) row.get("CASHIER_NO"))
                    .USERID((String) row.get("USERID"))
                    .UPDATE_DATE((String) row.get("UPDATE_DATE"))
                    .USERNAME((String) row.get("USERNAME"))
                    .CREDITCARD_TYPE((BigDecimal) row.get("CREDITCARD_TYPE"))
                    .CREDITCARD_FEE((BigDecimal) row.get("CREDITCARD_FEE"))
                    .POSTAL_AMOUNT((BigDecimal) row.get("POSTAL_AMOUNT"))
                    .PAYMENT_TYPE((BigDecimal) row.get("PAYMENT_TYPE"))
                    .TIME_NO((BigDecimal) row.get("TIME_NO"))
                    .CHK_GRADUATE_STATUS((String) row.get("CHK_GRADUATE_STATUS"))
                    .SAVE_STATUS((String) row.get("SAVE_STATUS"))
                    .STATUS_B_UPDATE_DATE((String) row.get("STATUS_B_UPDATE_DATE"))
                    .FACULTY_NO((String) row.get("FACULTY_NO"))
                    .REF_KEY((String) row.get("REF_KEY"))
                    .REGIS_STATUS((String) row.get("REGIS_STATUS"))
                    .EXPIRE_DATE((String) row.get("EXPIRE_DATE"))
                    .COURSE_NO((String) row.get("COURSE_NO"))
                    .CREDIT((BigDecimal) row.get("CREDIT"))
                    .EXAM_DATE((String) row.get("EXAM_DATE"))
                    .AMOUNT((String) row.get("AMOUNT"))
                    .PERIOD((String) row.get("PERIOD"))
                    .CHECKDIGIT((String) row.get("CHECKDIGIT"))
                    .PAYMENT_DATE((String) row.get("PAYMENT_DATE"))
                    .SLIP_RUN_NO((String) row.get("SLIP_RUN_NO"))
                    .build();

            return getRow;
        } else {
            return null;
        }
    }

    public List<ET_RECEIPT> findAll() {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = "SELECT FISCAL_YEAR,COUNTER_NO,RECEIPT_NO,STD_CODE,RECEIPT_YEAR,RECEIPT_SEMESTER,TO_CHAR(RECEIPT_DATE, 'mm/dd/yyyy')RECEIPT_DATE,RECEIPT_TIME,RECEIPT_PERIOD,"
                + " TOTAL_AMOUNT,RECEIPT_TYPE,RECEIPT_STATUS,REGIONAL_NO,CASH_AMOUNT,CHEQUE_AMOUNT,CREDIT_AMOUNT,REGIS_METHOD,REGIS_CK,"
                + " NEAR_GRADUATE,PAYMENT_CODE,ACCOUNT_NUMBER,BANK_FEE,RECEIPT_PAY_STATUS,REGIS_GROUP_NO,TO_CHAR(REGIS_DATE, 'mm/dd/yyyy')REGIS_DATE,TOTAL_AMOUNT_BANK,"
                + " BANK_VAT,BANK_TOTAL,NOMATCH_MONEY,TO_CHAR(BANK_DATE, 'mm/dd/yyyy')BANK_DATE,ADD_PAY,TO_CHAR(ADD_BANK_DATE, 'mm/dd/yyyy')ADD_BANK_DATE,EXAM_LOCATION_NO,WAIVED_NO,WAIVED_CR,CASHIER_NO,USERID,"
                + " TO_CHAR(UPDATE_DATE, 'mm/dd/yyyy')UPDATE_DATE,USERNAME,CREDITCARD_TYPE,CREDITCARD_FEE,POSTAL_AMOUNT,PAYMENT_TYPE,TIME_NO,CHK_GRADUATE_STATUS,SAVE_STATUS,"
                + " TO_CHAR(STATUS_B_UPDATE_DATE, 'mm/dd/yyyy')STATUS_B_UPDATE_DATE,FACULTY_NO,REF_KEY,REGIS_STATUS,TO_CHAR(EXPIRE_DATE, 'mm/dd/yyyy')EXPIRE_DATE FROM ET_RECEIPT";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_RECEIPT> findAllToDisplay() {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = " SELECT A.FISCAL_YEAR,A.COUNTER_NO,RECEIPT_NO,A.STD_CODE,A.RECEIPT_YEAR,A.RECEIPT_SEMESTER,TO_CHAR(A.RECEIPT_DATE, 'mm/dd/yyyy')RECEIPT_DATE,A.RECEIPT_TIME,A.RECEIPT_PERIOD,"
                + " A.TOTAL_AMOUNT,A.RECEIPT_TYPE,A.RECEIPT_STATUS,A.REGIONAL_NO,A.CASH_AMOUNT,A.CHEQUE_AMOUNT,A.CREDIT_AMOUNT,A.REGIS_METHOD,A.REGIS_CK,"
                + " A.NEAR_GRADUATE,A.PAYMENT_CODE,A.ACCOUNT_NUMBER,A.BANK_FEE,A.RECEIPT_PAY_STATUS,A.REGIS_GROUP_NO,TO_CHAR(A.REGIS_DATE, 'mm/dd/yyyy')REGIS_DATE,A.TOTAL_AMOUNT_BANK,"
                + " A.BANK_VAT,A.BANK_TOTAL,A.NOMATCH_MONEY,TO_CHAR(A.BANK_DATE, 'mm/dd/yyyy')BANK_DATE,A.ADD_PAY,TO_CHAR(A.ADD_BANK_DATE, 'mm/dd/yyyy')ADD_BANK_DATE,A.EXAM_LOCATION_NO,A.WAIVED_NO,WAIVED_CR,A.CASHIER_NO,A.USERID,"
                + " TO_CHAR(A.UPDATE_DATE, 'mm/dd/yyyy')UPDATE_DATE,A.USERNAME,A.CREDITCARD_TYPE,A.CREDITCARD_FEE,A.POSTAL_AMOUNT,A.PAYMENT_TYPE,A.TIME_NO,A.CHK_GRADUATE_STATUS,A.SAVE_STATUS,"
                + " TO_CHAR(A.STATUS_B_UPDATE_DATE, 'mm/dd/yyyy')STATUS_B_UPDATE_DATE,A.FACULTY_NO,A.REF_KEY,A.REGIS_STATUS,TO_CHAR(A.EXPIRE_DATE, 'mm/dd/yyyy')EXPIRE_DATE, B.NAME_THAI"
                + " FROM ET_RECEIPT A"
                + " LEFT JOIN DBBACH00.VM_STUDENT_MOBILE B"
                + " ON A.STD_CODE = B.STD_CODE";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find

    public List<ET_RECEIPT> findAllDateAllSection(String year, String secmester) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = " SELECT DISTINCT A.FISCAL_YEAR,A.RECEIPT_YEAR,A.RECEIPT_SEMESTER,A.STD_CODE, A.TOTAL_AMOUNT,"
                + " A.RECEIPT_STATUS,A.FACULTY_NO, A.REF_KEY,A.RECEIPT_PAY_STATUS,"
                + " TO_CHAR(A.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE ,D.NAME_THAI"
                + " FROM ET_RECEIPT A,ET_REGIS_RU24 B , QR_PAYMENT_CONFIRM_TMB C, DBBACH00.VM_STUDENT_MOBILE D "
                + " WHERE  A.STD_CODE = B.STD_CODE AND A.RECEIPT_SEMESTER = B.SEMESTER AND A.RECEIPT_YEAR = B.YEAR AND A.REF_KEY = B.REF_KEY AND B.STD_CODE = D.STD_CODE  "
                + " AND  TO_NUMBER(A.TOTAL_AMOUNT) !=  TO_NUMBER(C.AMOUNT)"
                + " AND A.RECEIPT_SEMESTER = ? AND A.RECEIPT_YEAR = ? ORDER BY D.NAME_THAI ASC";
        List<Map<String, Object>> result = db.queryList(sql, secmester, year);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find

    public List<ET_RECEIPT> findAllSectionByDate(String year, String secmester, String examDate) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = " SELECT DISTINCT A.FISCAL_YEAR,A.RECEIPT_YEAR,A.RECEIPT_SEMESTER,A.STD_CODE, A.TOTAL_AMOUNT,"
                + " A.RECEIPT_STATUS,A.FACULTY_NO, A.REF_KEY,A.RECEIPT_PAY_STATUS,"
                + " TO_CHAR(A.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE ,D.NAME_THAI"
                + " FROM ET_RECEIPT A,ET_REGIS_RU24 B , QR_PAYMENT_CONFIRM_TMB C, DBBACH00.VM_STUDENT_MOBILE D "
                + " WHERE  A.STD_CODE = B.STD_CODE AND A.RECEIPT_SEMESTER = B.SEMESTER AND A.RECEIPT_YEAR = B.YEAR AND A.REF_KEY = B.REF_KEY AND B.STD_CODE = D.STD_CODE  "
                + " AND A.TOTAL_AMOUNT !=  C.AMOUNT AND B.EXAM_DATE = TO_DATE(?, 'mm/dd/yyyy')"
                + " AND A.RECEIPT_SEMESTER = ? AND A.RECEIPT_YEAR = ? ORDER BY D.NAME_THAI ASC";
        List<Map<String, Object>> result = db.queryList(sql, examDate, secmester, year);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find

    public List<ET_RECEIPT> findReceiptDetail(String year, String secmester, String stdCode, String RefKey) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = " SELECT  A.YEAR,A.SEMESTER,A.STD_CODE,A.COURSE_NO,A.CREDIT,"
                + " (TO_CHAR(A.EXAM_DATE, 'dd monthyyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') || ' ('  || A.SECTION_NO  || ')' )EXAM_DATE ,B.NAME_THAI,"
                + " TO_CHAR(C.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE"
                + " FROM ET_REGIS_RU24 A, DBBACH00.VM_STUDENT_MOBILE B, ET_RECEIPT C"
                + " WHERE  A.STD_CODE = B.STD_CODE AND A.STD_CODE = C.STD_CODE AND A.REF_KEY = C.REF_KEY AND A.YEAR = ? AND A.SEMESTER = ? AND A.STD_CODE = ? AND A.REF_KEY = ? ";
        List<Map<String, Object>> result = db.queryList(sql, year, secmester, stdCode, RefKey);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find

    public List<ET_RECEIPT> findAllDateBySection(String year, String secmester, String section) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = " SELECT DISTINCT A.FISCAL_YEAR,A.RECEIPT_YEAR,A.RECEIPT_SEMESTER,A.STD_CODE, A.TOTAL_AMOUNT,"
                + " A.RECEIPT_STATUS,A.FACULTY_NO, A.REF_KEY,A.RECEIPT_PAY_STATUS,"
                + " TO_CHAR(A.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE ,D.NAME_THAI"
                + " FROM ET_RECEIPT A,ET_REGIS_RU24 B , QR_PAYMENT_CONFIRM_TMB C, DBBACH00.VM_STUDENT_MOBILE D "
                + " WHERE  A.STD_CODE = B.STD_CODE AND A.RECEIPT_SEMESTER = B.SEMESTER AND A.RECEIPT_YEAR = B.YEAR AND A.REF_KEY = B.REF_KEY AND B.STD_CODE = D.STD_CODE  "
                + " AND A.TOTAL_AMOUNT !=  C.AMOUNT AND B.SECTION_NO = ?"
                + " AND A.RECEIPT_SEMESTER = ? AND A.RECEIPT_YEAR = ? ORDER BY D.NAME_THAI ASC";
        List<Map<String, Object>> result = db.queryList(sql, section, secmester, year);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find

    public List<ET_RECEIPT> findByDateBySection(String year, String secmester, String examDate, String section) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = " SELECT DISTINCT A.FISCAL_YEAR,A.RECEIPT_YEAR,A.RECEIPT_SEMESTER,A.STD_CODE, A.TOTAL_AMOUNT,"
                + " A.RECEIPT_STATUS,A.FACULTY_NO, A.REF_KEY,A.RECEIPT_PAY_STATUS,"
                + " TO_CHAR(A.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE ,D.NAME_THAI"
                + " FROM ET_RECEIPT A,ET_REGIS_RU24 B , QR_PAYMENT_CONFIRM_TMB C, DBBACH00.VM_STUDENT_MOBILE D "
                + " WHERE  A.STD_CODE = B.STD_CODE AND A.RECEIPT_SEMESTER = B.SEMESTER AND A.RECEIPT_YEAR = B.YEAR AND A.REF_KEY = B.REF_KEY AND B.STD_CODE = D.STD_CODE  "
                + " AND A.TOTAL_AMOUNT !=  C.AMOUNT AND B.SECTION_NO = ? AND B.EXAM_DATE = TO_DATE(?, 'mm/dd/yyyy')"
                + " AND A.RECEIPT_SEMESTER = ? AND A.RECEIPT_YEAR = ? ORDER BY D.NAME_THAI ASC";
        List<Map<String, Object>> result = db.queryList(sql, section, examDate, secmester, year);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find

    public List<ET_RECEIPT> findAllExamDate() {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = "YEAR, SEMESTER, STD_CODE, TIME_NO, COURSE_NO, CREDIT, SECTION_NO, "
                + "TO_CHAR(EXAM_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE,"
                + " PERIOD, BUILD_NO, ROW_EXAM, SEAT_EXAM, STATUS_REGIS, GRADE, SCORE_TOTAL, SCORE_MIDTERM, SCORE_FINAL,"
                + " CKREGIS, TO_CHAR(INSERT_DATE, 'dd/mm/yyyy HH24:MI:SS', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')INSERT_DATE, "
                + " INSERT_USER, REF_KEY, REGIS_STATUS  FROM ET_RECEIPT ";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_RECEIPT> findBylist(String x) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = "SELECT FISCAL_YEAR,COUNTER_NO,RECEIPT_NO,STD_CODE,RECEIPT_YEAR,RECEIPT_SEMESTER,RECEIPT_DATE,RECEIPT_TIME,RECEIPT_PERIOD,"
                + " TOTAL_AMOUNT,RECEIPT_TYPE,RECEIPT_STATUS,REGIONAL_NO,CASH_AMOUNT,CHEQUE_AMOUNT,CREDIT_AMOUNT,REGIS_METHOD,REGIS_CK,"
                + " NEAR_GRADUATE,PAYMENT_CODE,ACCOUNT_NUMBER,BANK_FEE,RECEIPT_PAY_STATUS,REGIS_GROUP_NO,REGIS_DATE,TOTAL_AMOUNT_BANK,"
                + " BANK_VAT,BANK_TOTAL,NOMATCH_MONEY,BANK_DATE,ADD_PAY,ADD_BANK_DATE,EXAM_LOCATION_NO,WAIVED_NO,WAIVED_CR,CASHIER_NO,USERID,"
                + " UPDATE_DATE,USERNAME,CREDITCARD_TYPE,CREDITCARD_FEE,POSTAL_AMOUNT,PAYMENT_TYPE,TIME_NO,CHK_GRADUATE_STATUS,SAVE_STATUS,"
                + " STATUS_B_UPDATE_DATE,FACULTY_NO,REF_KEY,REGIS_STATUS,EXPIRE_DATE FROM ET_RECEIPT ";
        List<Map<String, Object>> result = db.queryList(sql, x);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public ET_RECEIPT findRu24ByStudent(String x) {
        String sql = "SELECT FISCAL_YEAR,COUNTER_NO,RECEIPT_NO,STD_CODE,RECEIPT_YEAR,RECEIPT_SEMESTER,RECEIPT_DATE,RECEIPT_TIME,RECEIPT_PERIOD,"
                + " TOTAL_AMOUNT,RECEIPT_TYPE,RECEIPT_STATUS,REGIONAL_NO,CASH_AMOUNT,CHEQUE_AMOUNT,CREDIT_AMOUNT,REGIS_METHOD,REGIS_CK,"
                + " NEAR_GRADUATE,PAYMENT_CODE,ACCOUNT_NUMBER,BANK_FEE,RECEIPT_PAY_STATUS,REGIS_GROUP_NO,REGIS_DATE,TOTAL_AMOUNT_BANK,"
                + " BANK_VAT,BANK_TOTAL,NOMATCH_MONEY,BANK_DATE,ADD_PAY,ADD_BANK_DATE,EXAM_LOCATION_NO,WAIVED_NO,WAIVED_CR,CASHIER_NO,USERID,"
                + " UPDATE_DATE,USERNAME,CREDITCARD_TYPE,CREDITCARD_FEE,POSTAL_AMOUNT,PAYMENT_TYPE,TIME_NO,CHK_GRADUATE_STATUS,SAVE_STATUS,"
                + " STATUS_B_UPDATE_DATE,FACULTY_NO,REF_KEY,REGIS_STATUS,EXPIRE_DATE FROM ET_RECEIPT where STD_CODE = ?";
        Map<String, Object> row = db.querySingle(sql, x);

        return setAltmodel(row);

    }

    public ET_RECEIPT findRefKey(String receiptStdCode, String receiptYear, String receiptSemester, String receiptPayStatus, String refKey) {
        String sql = "SELECT FISCAL_YEAR,COUNTER_NO,RECEIPT_NO,STD_CODE,RECEIPT_YEAR,RECEIPT_SEMESTER,TO_CHAR(RECEIPT_DATE, 'mm/dd/yyyy')RECEIPT_DATE,RECEIPT_TIME,RECEIPT_PERIOD,"
                + " TOTAL_AMOUNT,RECEIPT_TYPE,RECEIPT_STATUS,REGIONAL_NO,CASH_AMOUNT,CHEQUE_AMOUNT,CREDIT_AMOUNT,REGIS_METHOD,REGIS_CK,"
                + " NEAR_GRADUATE,PAYMENT_CODE,ACCOUNT_NUMBER,BANK_FEE,RECEIPT_PAY_STATUS,REGIS_GROUP_NO,TO_CHAR(REGIS_DATE, 'mm/dd/yyyy')REGIS_DATE,TOTAL_AMOUNT_BANK,"
                + " BANK_VAT,BANK_TOTAL,NOMATCH_MONEY,TO_CHAR(BANK_DATE, 'mm/dd/yyyy')BANK_DATE,ADD_PAY,TO_CHAR(ADD_BANK_DATE, 'mm/dd/yyyy')ADD_BANK_DATE,EXAM_LOCATION_NO,WAIVED_NO,WAIVED_CR,CASHIER_NO,USERID,"
                + " TO_CHAR(UPDATE_DATE, 'mm/dd/yyyy')UPDATE_DATE,USERNAME,CREDITCARD_TYPE,CREDITCARD_FEE,POSTAL_AMOUNT,PAYMENT_TYPE,TIME_NO,CHK_GRADUATE_STATUS,SAVE_STATUS,"
                + " TO_CHAR(STATUS_B_UPDATE_DATE, 'mm/dd/yyyy')STATUS_B_UPDATE_DATE,FACULTY_NO,REF_KEY,REGIS_STATUS,TO_CHAR(EXPIRE_DATE, 'mm/dd/yyyy')EXPIRE_DATE FROM ET_RECEIPT"
                + " WHERE STD_CODE=? AND RECEIPT_YEAR=? AND RECEIPT_SEMESTER=? AND RECEIPT_PAY_STATUS=? AND REF_KEY = ?";
        Map<String, Object> row = db.querySingle(sql, receiptStdCode, receiptYear, receiptSemester, receiptPayStatus, refKey);

        return setAltmodel(row);

    }

    public boolean insert(ET_RECEIPT obj) {
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

    public Boolean updateReceiptPayStatus(ET_RECEIPT objval, String changeReceiptPayStatus) {
        String sql = "UPDATE ET_RECEIPT SET RECEIPT_PAY_STATUS = ?"
                + "WHERE STD_CODE=? AND RECEIPT_YEAR=? AND RECEIPT_SEMESTER=? AND RECEIPT_PAY_STATUS=? AND REF_KEY = ?";
        int chkUpdate = db.update(sql, changeReceiptPayStatus, objval.getSTD_CODE(), objval.getRECEIPT_YEAR(), objval.getRECEIPT_SEMESTER(),
                objval.getRECEIPT_PAY_STATUS(), objval.getREF_KEY());
        try {
            return chkUpdate > 0;

        } catch (Exception e) {
            return false;
        }

    }

    public Boolean delete(String year, String sem) {
        String sql = "delete from ET_RECEIPT where STUDY_YEAR = ? and STUDY_SEMESTER = ?";
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

    public int countStudent() {
        int maxusr = 0;
        String sql = "select to_char(count(to_number(STD_CODE)))STUDY_SEMESTER from ET_RECEIPT";
        Map<String, Object> row = db.querySingle(sql);

        if (row != null) {
            maxusr = Integer.parseInt((String) row.get("STUDY_SEMESTER"));
            return maxusr;
        } else {
            return 0;
        }
    }//end get max no

    // ------------------------REPORT ----------------------------------------------
    public List<ET_RECEIPT> findReceiptAllDateExamAllSecoet(String sem, String year) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = " SELECT DISTINCT A.FISCAL_YEAR,A.RECEIPT_YEAR,A.RECEIPT_SEMESTER,A.STD_CODE, A.TOTAL_AMOUNT,"
                + " A.RECEIPT_STATUS,A.FACULTY_NO, A.REF_KEY,A.RECEIPT_PAY_STATUS,"
                + " TO_CHAR(A.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE ,D.NAME_THAI"
                + " FROM ET_RECEIPT A,ET_REGIS_RU24 B , QR_PAYMENT_CONFIRM_TMB C, DBBACH00.VM_STUDENT_MOBILE D "
                + " WHERE  A.STD_CODE = B.STD_CODE AND A.RECEIPT_SEMESTER = B.SEMESTER AND A.RECEIPT_YEAR = B.YEAR AND A.REF_KEY = B.REF_KEY AND B.STD_CODE = D.STD_CODE  "
                + " AND  TO_NUMBER(A.TOTAL_AMOUNT) !=  TO_NUMBER(C.AMOUNT)"
                + " AND A.RECEIPT_SEMESTER = ? AND A.RECEIPT_YEAR = ? AND A.RECEIPT_PAY_STATUS = '1' ORDER BY D.NAME_THAI ASC";
        List<Map<String, Object>> result = db.queryList(sql, sem, year);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 
    public List<ET_RECEIPT> findAllReceipt(String sem, String year) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = "SELECT a.FISCAL_YEAR,a.RECEIPT_YEAR,a.RECEIPT_SEMESTER,a.STD_CODE, a.TOTAL_AMOUNT, a.RECEIPT_STATUS, a.FACULTY_NO, a.REF_KEY, "
                + " TO_CHAR(a.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE ,to_char(a.SLIP_NO,'fm000000')SLIP_RUN_NO, "
                + "  TO_CHAR(b.INSERT_DATE, 'dd/mm/yyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')RECEIPT_DATE ,D.NAME_THAI, "
                + "  A.RECEIPT_NO, TO_CHAR(B.INSERT_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')PAYMENT_DATE "
                + "  FROM ET_RECEIPT a,QR_PAYMENT_CONFIRM_TMB b,  DBBACH00.VM_STUDENT_MOBILE d "
                + "  where A.STD_CODE = B.STD_CODE and A.RECEIPT_SEMESTER = B.SEMESTER  "
                + " and A.RECEIPT_YEAR = b.year and A.REF_KEY = B.QRID AND B.STD_CODE = D.STD_CODE   "
                + " and A.RECEIPT_PAY_STATUS = '1' and A.RECEIPT_SEMESTER = ? and A.RECEIPT_YEAR = ? ";
        List<Map<String, Object>> result = db.queryList(sql, sem, year);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_RECEIPT> findAllReceiptSelectSection(String sem, String year, String sec) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = "SELECT  a.FISCAL_YEAR,a.RECEIPT_YEAR,a.RECEIPT_SEMESTER,a.STD_CODE, a.TOTAL_AMOUNT, a.RECEIPT_STATUS, a.FACULTY_NO, a.REF_KEY, "
                + " TO_CHAR(a.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE ,to_char(a.SLIP_NO,'fm000000')SLIP_RUN_NO  , "
                + " TO_CHAR(b.INSERT_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')RECEIPT_DATE"
                + " ,D.NAME_THAI,c.REF_KEY "
                + " FROM ET_RECEIPT a,QR_PAYMENT_CONFIRM_TMB b,ET_REGIS_RU24 c, DBBACH00.VM_STUDENT_MOBILE d"
                + " where  A.STD_CODE = B.STD_CODE and A.RECEIPT_SEMESTER = B.SEMESTER "
                + " and A.RECEIPT_YEAR = b.year and A.REF_KEY = B.QRID and B.STD_CODE = C.STD_CODE and B.SEMESTER = C.SEMESTER and B.YEAR = C.YEAR "
                + " and B.QRID = C.REF_KEY  and C.STD_CODE = D.STD_CODE  "
                + " and a.RECEIPT_PAY_STATUS = '1' and a.RECEIPT_SEMESTER = ? and a.RECEIPT_YEAR = ? and C.SECTION_NO = ?";
        List<Map<String, Object>> result = db.queryList(sql, sem, year, sec);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_RECEIPT> findBySelectDate(String sem, String year, String examdate) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = "SELECT  a.FISCAL_YEAR,a.RECEIPT_YEAR,a.RECEIPT_SEMESTER,a.STD_CODE, a.TOTAL_AMOUNT, a.RECEIPT_STATUS, a.FACULTY_NO, a.REF_KEY,  "
                + " TO_CHAR(a.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE ,to_char(a.SLIP_NO,'fm000000')SLIP_RUN_NO  , "
                + " TO_CHAR(b.INSERT_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')RECEIPT_DATE"
                + " ,D.NAME_THAI, A.RECEIPT_NO, TO_CHAR(B.INSERT_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')PAYMENT_DATE "
                + " FROM ET_RECEIPT a,QR_PAYMENT_CONFIRM_TMB b, DBBACH00.VM_STUDENT_MOBILE d"
                + " where A.STD_CODE = B.STD_CODE and A.RECEIPT_SEMESTER = B.SEMESTER "
                + " and A.RECEIPT_YEAR = b.year and A.REF_KEY = B.QRID  and B.STD_CODE = D.STD_CODE"
                + " and A.RECEIPT_PAY_STATUS = '1' and A.RECEIPT_SEMESTER = ? and A.RECEIPT_YEAR = ? "
                + " and  trunc(B.INSERT_DATE) = TO_DATE(?,'dd/mm/yyyy','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')";
        List<Map<String, Object>> result = db.queryList(sql, sem, year, examdate);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_RECEIPT> findBySelectDateAndSection(String sem, String year, String sec, String examdate) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = "SELECT  a.FISCAL_YEAR,a.RECEIPT_YEAR,a.RECEIPT_SEMESTER,a.STD_CODE, a.TOTAL_AMOUNT, a.RECEIPT_STATUS, a.FACULTY_NO, a.REF_KEY,  "
                + " TO_CHAR(a.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE  ,to_char(a.SLIP_NO,'fm000000')SLIP_RUN_NO , "
                + " TO_CHAR(b.INSERT_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')RECEIPT_DATE"
                + " ,D.NAME_THAI,c.REF_KEY , TO_CHAR(B.INSERT_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')PAYMENT_DATE "
                + " FROM ET_RECEIPT a,QR_PAYMENT_CONFIRM_TMB b,ET_REGIS_RU24 c, DBBACH00.VM_STUDENT_MOBILE d "
                + " where  A.STD_CODE = B.STD_CODE and A.RECEIPT_SEMESTER = B.SEMESTER "
                + " and A.RECEIPT_YEAR = b.year and B.STD_CODE = C.STD_CODE and B.SEMESTER = C.SEMESTER and B.YEAR = C.YEAR "
                + " and A.REF_KEY = B.QRID and B.QRID = c.REF_KEY  and C.STD_CODE = D.STD_CODE"
                + " and A.RECEIPT_PAY_STATUS = '1' and A.RECEIPT_SEMESTER = ? and A.RECEIPT_YEAR = ?  and C.SECTION_NO = ? "
                + " and trunc(B.INSERT_DATE) = TO_DATE(?,'dd/mm/yyyy','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')";
        List<Map<String, Object>> result = db.queryList(sql, sem, year, sec, examdate);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_RECEIPT> findReceiptSelectStdApproove(String sem, String year, String stdcode) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = "SELECT  a.FISCAL_YEAR,a.RECEIPT_YEAR,a.RECEIPT_SEMESTER,a.STD_CODE, a.TOTAL_AMOUNT, a.RECEIPT_STATUS, a.FACULTY_NO, a.REF_KEY, "
                + "TO_CHAR(a.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE , "
                + "TO_CHAR(b.INSERT_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')RECEIPT_DATE "
                + ",D.NAME_THAI,C.COURSE_NO,C.CREDIT,"
                + "(TO_CHAR(C.EXAM_DATE, 'dd monthyyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') || ' ('  || C.SECTION_NO  || ')' )EXAM_DATE "
                + " FROM ET_RECEIPT a,QR_PAYMENT_CONFIRM_TMB b,ET_REGIS_RU24 c, DBBACH00.VM_STUDENT_MOBILE d "
                + " where  A.STD_CODE = B.STD_CODE and A.RECEIPT_SEMESTER = B.SEMESTER "
                + " and A.RECEIPT_YEAR = b.year and A.REF_KEY = B.QRID and B.STD_CODE = C.STD_CODE and B.SEMESTER = C.SEMESTER and B.YEAR = C.YEAR "
                + " and B.QRID = C.REF_KEY  and C.STD_CODE = D.STD_CODE   "
                + " and a.RECEIPT_PAY_STATUS = '1' and a.RECEIPT_SEMESTER = ? and a.RECEIPT_YEAR = ? and C.STD_CODE = ?";
        List<Map<String, Object>> result = db.queryList(sql, sem, year, stdcode);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_RECEIPT> findReceiptSelectStdAll(String sem, String year, String stdcode, String RefKey) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = " SELECT  A.YEAR,A.SEMESTER,A.STD_CODE,A.COURSE_NO,A.CREDIT,"
                + " (TO_CHAR(A.EXAM_DATE, 'dd monthyyyy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') || ' ('  || A.SECTION_NO  || ')' )EXAM_DATE ,B.NAME_THAI,"
                + " TO_CHAR(C.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE"
                + " FROM ET_REGIS_RU24 A, DBBACH00.VM_STUDENT_MOBILE B, ET_RECEIPT C"
                + " WHERE  A.STD_CODE = B.STD_CODE AND A.STD_CODE = C.STD_CODE AND A.REF_KEY = C.REF_KEY AND A.YEAR = ? AND A.SEMESTER = ? AND A.STD_CODE = ? AND A.REF_KEY = ? ";
        List<Map<String, Object>> result = db.queryList(sql, year, sem, stdcode, RefKey);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    // regis all --------------------------------------------------
    public List<ET_RECEIPT> findRegisterAll(String sem, String year) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = "SELECT DISTINCT a.FISCAL_YEAR,a.RECEIPT_YEAR,a.RECEIPT_SEMESTER,a.STD_CODE, a.TOTAL_AMOUNT,"
                + " a.RECEIPT_STATUS,a.FACULTY_NO, a.REF_KEY,"
                + " TO_CHAR(a.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE ,D.NAME_THAI"
                + " FROM ET_RECEIPT a,ET_REGIS_RU24 b , DBBACH00.VM_STUDENT_MOBILE d "
                + " where  A.STD_CODE = B.STD_CODE and A.RECEIPT_SEMESTER = B.SEMESTER "
                + " and A.RECEIPT_YEAR = b.year and A.REF_KEY = B.REF_KEY and b.STD_CODE = D.STD_CODE "
                + " and A.RECEIPT_SEMESTER = ? and A.RECEIPT_YEAR = ?";
        List<Map<String, Object>> result = db.queryList(sql, sem, year);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_RECEIPT> findRegisterByDateAndSection(String sem, String year, String sec, String examdate) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = "SELECT  a.FISCAL_YEAR,a.RECEIPT_YEAR,a.RECEIPT_SEMESTER,a.STD_CODE, a.TOTAL_AMOUNT,"
                + " a.RECEIPT_STATUS,a.FACULTY_NO, a.REF_KEY,"
                + " TO_CHAR(a.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE ,D.NAME_THAI"
                + " FROM ET_RECEIPT a,ET_REGIS_RU24 b , DBBACH00.VM_STUDENT_MOBILE d "
                + " where  A.STD_CODE = B.STD_CODE and A.RECEIPT_SEMESTER = B.SEMESTER "
                + " and A.RECEIPT_YEAR = b.year and A.REF_KEY = B.REF_KEY and b.STD_CODE = D.STD_CODE "
                + " and A.RECEIPT_SEMESTER = ? and A.RECEIPT_YEAR = ?  and b.SECTION_NO = ? "
                + " and trunc(B.INSERT_DATE) = TO_DATE(?,'dd/mm/yyyy','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')";
        List<Map<String, Object>> result = db.queryList(sql, sem, year, sec, examdate);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }

    public List<ET_RECEIPT> findRegisterByDate(String sem, String year, String examdate) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = "SELECT  a.FISCAL_YEAR,a.RECEIPT_YEAR,a.RECEIPT_SEMESTER,a.STD_CODE, a.TOTAL_AMOUNT,"
                + " a.RECEIPT_STATUS,a.FACULTY_NO, a.REF_KEY,"
                + " TO_CHAR(a.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE ,D.NAME_THAI"
                + " FROM ET_RECEIPT a,ET_REGIS_RU24 b , DBBACH00.VM_STUDENT_MOBILE d "
                + " where  A.STD_CODE = B.STD_CODE and A.RECEIPT_SEMESTER = B.SEMESTER "
                + " and A.RECEIPT_YEAR = b.year and A.REF_KEY = B.REF_KEY and b.STD_CODE = D.STD_CODE "
                + " and A.RECEIPT_SEMESTER = ? and A.RECEIPT_YEAR = ? "
                + " and trunc(B.INSERT_DATE) = TO_DATE(?,'dd/mm/yyyy','NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')";
        List<Map<String, Object>> result = db.queryList(sql, sem, year, examdate);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    public List<ET_RECEIPT> findRegisterBySection(String sem, String year, String sec) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = "SELECT a.FISCAL_YEAR,a.RECEIPT_YEAR,a.RECEIPT_SEMESTER,a.STD_CODE, a.TOTAL_AMOUNT,"
                + " a.RECEIPT_STATUS,a.FACULTY_NO, a.REF_KEY,"
                + " TO_CHAR(a.REGIS_DATE, 'dd/mm/yyyy hh24:mi:ss', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')REGIS_DATE ,D.NAME_THAI"
                + " FROM ET_RECEIPT a,ET_REGIS_RU24 b , DBBACH00.VM_STUDENT_MOBILE d "
                + " where  A.STD_CODE = B.STD_CODE and A.RECEIPT_SEMESTER = B.SEMESTER "
                + " and A.RECEIPT_YEAR = b.year and A.REF_KEY = B.REF_KEY and b.STD_CODE = D.STD_CODE "
                + " and A.RECEIPT_SEMESTER = ? and A.RECEIPT_YEAR = ? and b.SECTION_NO = ? ";
        List<Map<String, Object>> result = db.queryList(sql, sem, year, sec);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }

    public List<ET_RECEIPT> findCheckdigit(String stdcode,String fiscalyear,String repNumcourse,String total,String sem, String year, String refkey) {
        List<ET_RECEIPT> list = new ArrayList<ET_RECEIPT>();
        String sql = "SELECT  to_char(DIPSTD01.GET_UIC(? ,?,'567',?,'1',?),'fm0000')CHECKDIGIT"
                + " from et_receipt  WHERE std_code = ? "
                + " and RECEIPT_SEMESTER = ? and RECEIPT_YEAR = ? and  REF_KEY = ?";
        List<Map<String, Object>> result = db.queryList(sql, stdcode,fiscalyear,repNumcourse,total,stdcode,sem,year,refkey);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }

    // ------------------------END REPORT ----------------------------------------------
}
