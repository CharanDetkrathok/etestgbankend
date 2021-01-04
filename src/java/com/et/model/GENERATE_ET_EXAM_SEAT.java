package com.et.model;

import java.math.BigDecimal;
import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GENERATE_ET_EXAM_SEAT {

    private String YEAR;
    private String SEMESTER;
    private String INSERT_DATE;
    private String ROW_SEAT;
    private String STD_CODE;
    private String EXAM_DATE;
    private BigDecimal SECTION_NO;
    private BigDecimal CREDIT;
    private String COURSE_NO;
    private String STATUS_COURSE;
    private String RECEIPT_PAY_STATUS;
    private String CHECH_DUPLICATE_REF_KEY;
    private String SUM_SEAT_BY_ROW;
}
