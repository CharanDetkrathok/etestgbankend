/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.model;

import java.math.BigDecimal;
import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ET_RECEIPT {

    private String NAME_THAI;
    private String FISCAL_YEAR;
    private BigDecimal COUNTER_NO;
    private String RECEIPT_NO;
    private String STD_CODE;
    private String RECEIPT_YEAR;
    private String RECEIPT_SEMESTER;
    private String RECEIPT_DATE;
    private String RECEIPT_TIME;
    private String RECEIPT_PERIOD;
    private String TOTAL_AMOUNT;
    private String RECEIPT_TYPE;
    private String RECEIPT_STATUS;
    private BigDecimal REGIONAL_NO;
    private BigDecimal CASH_AMOUNT;
    private BigDecimal CHEQUE_AMOUNT;
    private BigDecimal CREDIT_AMOUNT;
    private BigDecimal REGIS_METHOD;
    private BigDecimal REGIS_CK;
    private String NEAR_GRADUATE;
    private String PAYMENT_CODE;
    private String ACCOUNT_NUMBER;
    private BigDecimal BANK_FEE;
    private String RECEIPT_PAY_STATUS;
    private BigDecimal REGIS_GROUP_NO;
    private String REGIS_DATE;
    private BigDecimal TOTAL_AMOUNT_BANK;
    private BigDecimal BANK_VAT;
    private BigDecimal BANK_TOTAL;
    private BigDecimal NOMATCH_MONEY;
    private String BANK_DATE;
    private BigDecimal ADD_PAY;
    private String ADD_BANK_DATE;
    private BigDecimal EXAM_LOCATION_NO;
    private String WAIVED_NO;
    private BigDecimal WAIVED_CR;
    private String CASHIER_NO;
    private String USERID;
    private String UPDATE_DATE;
    private String USERNAME;
    private BigDecimal CREDITCARD_TYPE;
    private BigDecimal CREDITCARD_FEE;
    private BigDecimal POSTAL_AMOUNT;
    private BigDecimal PAYMENT_TYPE;
    private BigDecimal TIME_NO;
    private String CHK_GRADUATE_STATUS;
    private String SAVE_STATUS;
    private String STATUS_B_UPDATE_DATE;
    private String FACULTY_NO;
    private String REF_KEY;
    private String REGIS_STATUS;
    private String EXPIRE_DATE;
    private String COURSE_NO;
    private BigDecimal CREDIT;
    private String EXAM_DATE;
    private String AMOUNT;
    private String PERIOD;
    private String CHECKDIGIT;
    private String PAYMENT_DATE;
    private String SLIP_RUN_NO;
}
