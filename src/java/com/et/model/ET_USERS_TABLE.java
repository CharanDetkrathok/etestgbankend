/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.model;

// import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;

@Data
public class ET_USERS_TABLE {
    
    Database db;

    public ET_USERS_TABLE(Database db) {
        this.db = db;
    }

    public ET_USERS setAltmodel(Map<String, Object> row) {

        if (row != null) {
            ET_USERS getRow = ET_USERS.builder()
                    .USER_NAME((String) row.get("USERNAME"))
                    .USER_PASSWORD((String) row.get("PASSWORD"))
                    .INSERT_DATE((String) row.get("INSERT_DATE"))
                    .UPDATE_DATE((String) row.get("UPDATE_DATE"))
                    .PERIOD((String) row.get("PERIOD"))
                    .build();

            return getRow;
        } else {
            return null;
        }
    }

    public List<ET_USERS> findAll() {
        List<ET_USERS> list = new ArrayList<ET_USERS>();
        String sql = "*";
        List<Map<String, Object>> result = db.queryList(sql);

        for (Map<String, Object> row : result) {

            list.add(setAltmodel(row));
        }
        return list;
    }
    //end find 

    /*public List<ET_USERS> findByCourselist(String x) {
     List<ET_USERS> list = new ArrayList<ET_USERS>();
     String sql = "select *\n"
     + "from (\n"
     + "select a.std_code,c.course_no,a.NAME_THAI,a.FACULTY_NAME_THAI,a.CURR_NAME_THAI,MAJOR_NAME_THAI ,"
     + "to_char(d.EXAM_DATE, 'DD/MM/YYYY', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI')EXAM_DATE,d.EXAM_PERIOD,"
     + "(d.EXAM_DATE || ' ' ||d.EXAM_PERIOD) as EXAM_DATE_PERIOD,"
     + "count(*) over (partition by a.std_code,d.exam_date,d.exam_period) cnt"
     + " from dbbach00.VM_STUDENT_REGIS a ,UGB_adc_EXAM d,regis000.VM_regis_ru24 c "
     + " where d.COURSE_SEMESTER = c.SEMESTER and d.COURSE_YEAR = C.YEAR and d.COURSE_NO = C.COURSE_NO and A.STD_CODE = C.STD_CODE "
     + "and COURSE_YEAR = '2562' and COURSE_SEMESTER ='3'and c.STD_CODE like '?'"
     + ") "
     + " where cnt > 1";
     List<Map<String, Object>> result = db.queryList(sql, x + "%");

     for (Map<String, Object> row : result) {

     list.add(setAltmodel(row));
     }
     return list;
     }*/
    //end find 
    public ET_USERS findByUserName(String x) {
        String sql = "SELECT USERNAME,PASSWORD,PERIOD FROM ET_USERS WHERE USERNAME = ?";
        Map<String, Object> row = db.querySingle(sql, x);

        return setAltmodel(row);

    }

    public boolean insert(ET_USERS obj) {
        // int colorNo = getColorNo();
        String sql = "insert into ET_USERS(USERNAME,PASSWORD,CREATE_DATE,PERIOD) values(?,?,sysdate,?)";

        String[] genCol = {"USER_NAME"};
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

    public Boolean update(ET_USERS objval) {
        String sql = "update ET_USERS set USER_NAME = ?  "
                + "where USER_NAME = ?";
        int chkUpdate = db.update(sql);
        try {
            return chkUpdate > 0;

        } catch (Exception e) {
            return false;
        }

    }

    //end of update
    public Boolean delete(String x) {
        String sql = "delete from ET_USERS where USER_NAME = ?";
        int chkDelete = db.remove(sql, x);
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

    public int getCount() {
        int maxusr = 0;
        String sql = "select to_char(count(USER_NAME))USERID from ET_USERS where PERIOD ='1'";
        Map<String, Object> row = db.querySingle(sql);

        if (row != null) {
            maxusr = Integer.parseInt((String) row.get("USERID")) ;
            return maxusr;
        } else {
            return 0;
        }

    }
}
