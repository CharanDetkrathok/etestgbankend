/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.model;

// import java.math.BigDecimal;
import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ET_PROFILE {
    
    private String STD_CODE;
    private String NAME_THAI;
    private String NAME_ENG;
    private String BIRTH_DATE;
    private String STD_STATUS_CURRENT;
    private String CITIZEN_ID;
    private String FACULTY_NO;
    private String FACULTY_NAME_THAI;
    private String CURR_NAME_THAI;
    private String MAJOR_NO;
    private String MAJOR_NAME_THAI;
    private String MOBILE_TELEPHONE; 
    
}
