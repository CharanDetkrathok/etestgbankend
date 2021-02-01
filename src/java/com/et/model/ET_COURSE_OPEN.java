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
public class ET_COURSE_OPEN {

    private String YEAR;
    private String SEMESTER;
    private String COURSE_NO;
    private BigDecimal CREDIT;
    private String STATUS_COURSE;
    private String COURSE_FEE;
    private String INSERT_DATE;
    private String INSERT_USER;
    private String UPDATE_DATE;
    private String UPDATE_USER;

}
