package com.et.model;

import java.math.BigDecimal;
import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class REP_ETEST101 {

    private String RECEIPT_DATE;
    private String SLIP_NO;
    private String STD_CODE;
    private String AMOUNT;
    private String TOTAL_AMOUNT;
    private String INSERT_DATE;
    private String REF_KEY;
}
