package com.et.model;

import java.math.BigDecimal;
import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ET_RU_COURSE {
    
    private String COURSE_NO;
    private BigDecimal CREDIT;   

}
