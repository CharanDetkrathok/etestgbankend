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
public class ET_DOWNLOAD_LOG {

    private String STD_CODE;
    private String YEAR;
    private String SEMESTER;
    private String INSERT_DATE;
    private BigDecimal SLIPT_NO;

    private String REF_KEY;
    private BigDecimal DOWNLOAD_NO;
}
