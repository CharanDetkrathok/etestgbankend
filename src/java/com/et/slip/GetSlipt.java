/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.slip;

import java.util.Arrays;

import com.et.model.*;
import com.sun.javafx.fxml.expression.BinaryExpression;
import static com.sun.javafx.fxml.expression.Expression.add;
import java.awt.AWTEventMulticaster;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ru-com7
 */
public class GetSlipt extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String stdcode = "6301503105";
        String refkey = "DRKEE210209235900775";
        String sem = "2";
        String year = "2563";

        // String tmpstdcode = "NjMwMTAwMzI1NA==";
        /*
        String stdcode = decodeparam(request.getParameter("stdcode"));       
       // byte[] decodedtmpstdcode = Base64.getDecoder().decode(tmpstdcode);
       // String stdcode = new String(decodedtmpstdcode);

        String refkey =  decodeparam(request.getParameter("refkey"));
        //byte[] decodedtmprefkey = Base64.getDecoder().decode(tmprefkey);
        //String refkey = new String(decodedtmprefkey);

        String sem =  decodeparam(request.getParameter("sem"));
        //byte[] decodedtmpsem = Base64.getDecoder().decode(tmpsem);
        //String sem = new String(decodedtmpsem);

        String year =  decodeparam(request.getParameter("year"));
        //byte[] decodedtmpyear = Base64.getDecoder().decode(tmpyear);
        //String year = new String(decodedtmpyear);*/
        //tmp stmt
        String getfiscal = "";
        String gsubStrfiscal = "";
        String tmpSem = "";
        String tmpYear = "";
        String subYear = "";
        String subrefkey = "";
        int sumCredit = 0;
        int cntCourse = 0;
        String chkDigit = "";
        String total = "";
        String slipNo = "";
        String strTotalThai = "";
        String tmpTotal = "";
        String[] tempSplitTotal = null;
        int noColAmount = 12;
        boolean checkupdaterepno = false;
        String tmp = "";
        String repNumcourse = "";

        ArrayList<String> arrAmount = new ArrayList<String>();
        // ArrayList<String> arrRepSlip = new ArrayList<String>();

        //call db
        Database db = new Database();
        ET_COUNTER_ADMIN_TABLE getEtCounterTable = new ET_COUNTER_ADMIN_TABLE(db);
        ET_PROFILE_TABLE getProfileTable = new ET_PROFILE_TABLE(db);
        ET_REP_SLIP_TABLE getSlipTable = new ET_REP_SLIP_TABLE(db);

        ET_PROFILE getProfile = null;
        List<ET_REP_SLIP> getRepSlip = null;
        ET_REP_SLIP getHeaderRepSlip = null;
        ET_REP_SLIP getSlipRunNo = null;
        ET_REP_SLIP checkSlipNo = null;
        ET_REP_SLIP getSlipNo = null;
        ET_REP_SLIP getCheckdigit = null;
        ET_REP_SLIP getSlipNoByStd = null;
        String sliprunno = "000000";
        boolean checkVal = false;
        int getslipNo = 0;

        // check slipt
        checkSlipNo = getSlipTable.findSlipNoByRef(stdcode, sem, year, refkey);
        if (checkSlipNo.getRun_no() != null) {
            getSlipNoByStd = getSlipTable.findSlipNo(stdcode, sem, year, refkey);
            sliprunno = getSlipNoByStd.getRun_no().toString();
            checkVal = true;
        } else {
            getslipNo = getSlipTable.getMaxSlip();
            if (getslipNo > 0) {
                getSlipRunNo = getSlipTable.genSlipRunNo();
                sliprunno = getSlipRunNo.getRun_no().toString();
                if (getSlipRunNo != null) {
                    sliprunno = sliprunno;
                    ET_REP_SLIP AddSlipNo = new ET_REP_SLIP();
                    AddSlipNo.setSLIP_NO(new BigDecimal(sliprunno));
                    AddSlipNo.setSTD_CODE((stdcode));
                    AddSlipNo.setSEMESTER((sem));
                    AddSlipNo.setYEAR((year));
                    AddSlipNo.setREF_KEY((refkey));
                     checkupdaterepno = getSlipTable.updateSlipt(AddSlipNo);
                    if (checkupdaterepno) {
                        db.commit();
                        checkVal = true;
                    } else {
                        response.sendRedirect("err.jsp");
                    }
                } else {
                    response.sendRedirect("err.jsp");
                }
            } else {
                response.sendRedirect("err.jsp");
            }
        }

        //---------------------------------------------------------
        if (checkVal) {
            // sliprunno = checkSlipNo.getRun_no();
            List<ET_COUNTER_ADMIN> getEtCounter = getEtCounterTable.findAll();
            if (getEtCounter != null) {
                for (ET_COUNTER_ADMIN i : getEtCounter) {
                    tmpSem = i.getSTUDY_SEMESTER();
                    tmpYear = i.getSTUDY_YEAR();
                    getfiscal = i.getFISCAL_YEAR();
                }
                
                if (tmpSem.equals(sem) && tmpYear.equals(year) && !stdcode.equals("") && !refkey.equals("")) {
                    //getMockSlip = getSlipTable.mockSlip();
                    getProfile = getProfileTable.findByStdProfile(stdcode);
                    getHeaderRepSlip = getSlipTable.findByHeaderSlip(stdcode, sem, year, refkey);
                    getRepSlip = getSlipTable.findListRegisSlip(stdcode, sem, year, refkey);
                    int tempYear;
                    tempYear = Integer.parseInt(getfiscal.substring(2));
                    subYear = String.valueOf(tempYear);
                    subrefkey = refkey.substring(15);

                    //      HashMap<String, String> tmparr = new HashMap<String, String>();
                    if (getRepSlip != null && getHeaderRepSlip != null) {
                        for (ET_REP_SLIP j : getRepSlip) {
                            arrAmount.add("200");
                            sumCredit = sumCredit + Integer.parseInt(j.getCREDIT().toString());
                            total = j.getTOTAL_AMOUNT();
                            tmpTotal = j.getAMOUNT();
                            cntCourse++;
                            //arrRepSlip.addAll();
                        }

                        int chkColAmount = 0;
                        chkColAmount = (noColAmount - cntCourse);
                        int chkRowSlip = 0;
                        if (cntCourse > 0) {
                            for (int i = 0; i <= chkColAmount; i++) {
                                arrAmount.add("&nbsp;");
                            }
                            // chkRowSlip = chkColAmount -1;
                            /* for (int i = 0; i < chkColAmount; i++) {
                            getRepSlip.add(getMockSlip);
                        }*/
                        }

                        if (tmpTotal != null) {
                            strTotalThai = ThaiBaht(tmpTotal);
                            slipNo = refkey.substring(15);
                            tempSplitTotal = total.split("[.]");
                            tmp = tempSplitTotal[0];
                            repNumcourse = Integer.toString(cntCourse);
                        } else {
                            response.sendRedirect("err.jsp");
                        }

                        getCheckdigit = getSlipTable.findCheckdigit(stdcode, getfiscal, repNumcourse, tmpTotal, sem, year, refkey);
                        if (getCheckdigit != null) {
                            chkDigit = getCheckdigit.getCHECKDIGIT();
                        }

                        request.setAttribute("getProfile", getProfile);
                        request.setAttribute("chkDigit", chkDigit);
                        request.setAttribute("getRepSlip", getRepSlip);
                        request.setAttribute("getfiscal", getfiscal);
                        request.setAttribute("sliprunno", sliprunno);
                        request.setAttribute("refkey", refkey);
                        request.setAttribute("getHeaderRepSlip", getHeaderRepSlip);
                        request.setAttribute("slipNo", slipNo);
                        request.setAttribute("sem", sem);
                        request.setAttribute("year", year);
                        request.setAttribute("subYear", subYear);
                        request.setAttribute("subrefkey", subrefkey);
                        request.setAttribute("arrAmount", arrAmount);
                        request.setAttribute("tmpTotal", tmp);
                        request.setAttribute("sumCredit", sumCredit);
                        request.setAttribute("cntCourse", cntCourse);
                        request.setAttribute("sumCredit", sumCredit);
                        request.setAttribute("strTotalThai", strTotalThai);
                        request.setAttribute("noColAmount", noColAmount);
                        request.setAttribute("chkColAmount", chkColAmount);
                        RequestDispatcher rs = request.getRequestDispatcher("repSlipt.jsp");
                        rs.forward(request, response);
                    } else {
                        response.sendRedirect("err.jsp");
                    }
                } else {
                    //RequestDispatcher rs = request.getRequestDispatcher("err.jsp");
                    //rs.forward(request, response);
                    response.sendRedirect("err.jsp");
                }
            } else {
                response.sendRedirect("err.jsp");
            }
        } else {
            response.sendRedirect("err.jsp");
        }

        db.close();
        // String getToken = generateNewToken(); 
        /* if (stdcode != "") {
            RequestDispatcher rs = request.getRequestDispatcher("repSlipt.jsp");
                            rs.forward(request, response);
            
        } else {
        }
         */
 /* PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetSlipt</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>  " + strTotalThai + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }*/
    }

    private static String ThaiBaht(String total) {
        String bathTxt, n;
        String bathTH = "";

        // bathTxt = "1201.01";
        String[] num = {"ศูนย์", "หนึ่ง", "สอง", "สาม", "สี่", "ห้า", "หก", "เจ็ด", "แปด", "เก้า", "สิบ"};
        String[] rank = {"", "สิบ", "ร้อย", "พัน", "หมื่น", "แสน", "ล้าน"};
        String[] temp = total.split("[.]");
        String intVal = temp[0];
        String deciVal = temp[1];
        if (Double.parseDouble(total) == 0) {
            bathTH = "ศูนย์บาทถ้วน";
        } else {
            for (int i = 0; i < intVal.length(); i++) {
                n = intVal.substring(i, i + 1);
                if (Integer.parseInt(n) != 0) {
                    if ((i == (intVal.length() - 1)) && (n.indexOf("1") > -1) && intVal.charAt(intVal.length() - 2) != '0') {
                        bathTH += "เอ็ด";
                    } else if ((i == (intVal.length() - 2)) && (n.indexOf("2") > -1)) {
                        bathTH += "ยี่";
                    } else if ((i == (intVal.length() - 2)) && (n.indexOf("1") > -1)) {
                        bathTH += "";
                    } else {
                        bathTH += num[Integer.parseInt(n)];
                    }
                    bathTH += rank[(intVal.length() - i) - 1];
                } else if (i == 0) {
                    bathTH += num[Integer.parseInt(n)];
                }
            }
        }
        bathTH += "บาทถ้วน";
        return bathTH;
        /*  for (int i = 0; i < deciVal.length(); i++) {
                if (deciVal.indexOf("00") > -1) {
                    bathTH += "ถ้วน";
                    break;
                } else {
                    n = deciVal.substring(i, i + 1);
                    if (n.indexOf("0") == -1) {
                        if ((i == deciVal.length() - 1) && n.indexOf("1") > -1 && deciVal.charAt(0) != '0') {
                            bathTH += "เอ็ด";
                        } else if ((i == (deciVal.length() - 2)) && (n.indexOf("2") > -1)) {
                            bathTH += "ยี่";
                        } else if ((i == (deciVal.length() - 2)) && (n.indexOf("1") > -1)) {
                            bathTH += "";
                        } else {
                            bathTH += num[Integer.parseInt(n)];
                        }
                        bathTH += rank[(deciVal.length() - i) - 1];

                    }
                }
                if (i == deciVal.length() - 1) {
                    bathTH += "สตางค์";
                }
            }

        }*/

    }

    public static Object[] add(Object[] arr, Object... elements) {
        Object[] tempArr = new Object[arr.length + elements.length];
        System.arraycopy(arr, 0, tempArr, 0, arr.length);

        for (int i = 0; i < elements.length; i++) {
            tempArr[arr.length + i] = elements[i];
        }
        return tempArr;

    }

    public static String decodeparam(String param) {
        byte[] decodedstr = Base64.getDecoder().decode(param);
        String str = new String(decodedstr);
        return str;

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
