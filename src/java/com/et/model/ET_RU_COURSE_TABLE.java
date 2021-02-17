package com.et.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.*;

@Data
public class ET_RU_COURSE_TABLE {

    Database db;

    public ET_RU_COURSE_TABLE(Database db) {
        this.db = db;
    }

    public ET_RU_COURSE setAltmodel(Map<String, Object> row) {

        if (row != null) {
            ET_RU_COURSE getRow = ET_RU_COURSE.builder()
                    .COURSE_NO((String) row.get("COURSE_NO"))
                    .CREDIT((BigDecimal) row.get("CREDIT"))
                    .build();
            return getRow;
        } else {
            return null;
        }
    }

    public List<ET_RU_COURSE> findAll(String year, String semester) {
        List<ET_RU_COURSE> list = new ArrayList<ET_RU_COURSE>();
        String sql = "SELECT DISTINCT A.COURSE_NO,A.CREDIT FROM VM_EXAM_SCHEDULE A WHERE A.REGIS_SEMESTER = '"+semester+"' AND A.REGIS_YEAR = '"+year+"' ORDER BY A.COURSE_NO";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
}
