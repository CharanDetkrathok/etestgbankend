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
public class ET_REP_SLIP {
    private String STD_CODE;
    private String YEAR;
    private String SEMESTER;
    private String QRID;
    private String INSERT_DATE;
    private String AMOUNT;
    private String COURSE_NO;
    private BigDecimal CREDIT;
    private BigDecimal SECTION_NO;
    private String EXAM_DATE;
    private String INSERT_TIME;
    private String TOTAL_AMOUNT;
    private String INSERT_DATE_TIME;
    private String DATE_GENERATED;
    private BigDecimal SLIP_NO;
    private String run_no;
    private String REF_KEY;
    private String CHECKDIGIT;
}
