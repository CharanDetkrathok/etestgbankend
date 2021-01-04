<%-- 
    Document   : Rep-ETesting103
    Created on : Oct 19, 2020, 2:19:21 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@page import="sun.java2d.cmm.ProfileDeferralMgr"%>
<%@ page import="com.et.model.REP_ETEST103"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>
    @media print {

        table.gridtable     { page-break-after: auto; }
        table.gridtable   tr    { page-break-inside:avoid;page-break-after: auto; }
        table.gridtable   td    { page-break-inside:avoid; page-break-after:auto }
        table.gridtable   thead { display:table-header-group }
        table.gridtable   tfoot { display:table-footer-group }

        html, body{
            margin-top: -60px;
            margin-left: -250px;  
            height:100%;
            width:100%;
            font-size: 20px;
            font-family: 'Times New Roman';
            page-break-before: always;
        }        
        #hid {
            display: none;
        }
        #left-panel {
            display: none;
        }        
        .top-right {
            display: none;
        }
        .header {
            display: none;
        }
        table {
            table-layout: auto;
            width: 950px; 
            font-size: 20px;
        }       
    }

    @page {
        size: A4;
    }



</style>

<jsp:include page="header.jsp" />

<!-- /#header -->
<!-- Content -->
<div class="content">
    <!-- Animated -->
    <div class="animated fadeIn">
        <!-- Widgets  -->
        <div class="row"> 

        </div>
        <!-- /Widgets -->
        <!--  Traffic  -->
        <div class="row">
            <div class="col-lg-6"></div>
            <div class="col-lg-3">
                <div class="card" id="hid">
                    <button type="button"  onclick="history.back()" class="btn btn-warning"
                            style=" 
                            border-radius: 0; box-shadow: 0 0 2px 0 rgba(0, 0, 0, 0.5); color: #000;
                            font-family: Verdana,Arial,sans-serif !important;
                            ">
                        <i class="fa fa-backward"></i> กลับไปก่อนหน้า </button>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="card" id="hid">
                    <button type="button" class="btn btn-info" 
                            style=" 
                            border-radius: 0; box-shadow: 0 0 2px 0 rgba(0, 0, 0, 0.5); color: #000;
                            font-family: Verdana,Arial,sans-serif !important;
                            "
                            onClick="window.print();"
                            value="Print">
                        &nbsp;<i class="fa fa-print"></i>&nbsp; <font style="color: #fff">พิมพ์รายงานการรับเงิน</font> 
                    </button>
                </div>
            </div>            
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="card">              
                    <table cellpadding="1" cellspacing="1" id="DataPanel" class="gridtable"> 
                        <thead>
                        <br id="hid"><br id="hid"><br id="hid"><br id="hid">
                        <tr class="text-center">
                            <th colspan="6">
                                <h6 class="text-center mb-2">REP-eTest103 สถาบันบริการวิชาการทางอิเล็กทรอนิกส์ มหาวิทยาลัยรามคำแหง</h6>
                            </th>
                        </tr>
                        <tr class="text-center">
                            <th colspan="6">
                                <h6 class="text-center mb-2">
                                    รายงานสรุปการรับเงินลงทะเบียน e-Testing ประจำภาค 
                                    <c:choose>
                                        <c:when test = "${getCounterData.STUDY_SEMESTER == '3'}">
                                            Summer /${getCounterData.STUDY_YEAR}
                                        </c:when>
                                        <c:otherwise>
                                            ${getCounterData.STUDY_SEMESTER}/${getCounterData.STUDY_YEAR}
                                        </c:otherwise>
                                    </c:choose> 
                                </h6>
                            </th>
                        </tr>
                        <tr class="text-center" style="border-bottom: 1px solid black;">
                            <th colspan="6" style="padding-bottom:20px;">
                                <h6 class="text-center"> ตั้งแต่วันที่ ${timePeriod} </h6>
                            </th>
                        </tr>
                        <tr class="text-center" style="border-bottom: 1px solid black;">
                            <th style="padding-top:5px; padding-bottom:5px;">วันที่</th>
                            <th style="padding-top:5px; padding-bottom:5px;">คาบ</th>
                            <th style="padding-top:5px; padding-bottom:5px;">เครื่องรับเงิน</th>
                            <th style="padding-top:5px; padding-bottom:5px;">ค่าลงทะเบียน</th>
                            <th style="padding-top:5px; padding-bottom:5px;">( จำนวนคน )</th>
                            <th style="padding-top:5px; padding-bottom:5px;">จำนวนเงิน</th>                            
                        </tr>     
                        </thead>
                        <%
                            List<REP_ETEST103> etest103 = (List<REP_ETEST103>) request.getAttribute("repETest103");
//
//                            int Total_Amount_All = 0;
//                            String totalAmountAll = "";
//
//                            int Total_Std = 0;
//                            String totalStdAll = "";

                            for (REP_ETEST103 e : etest103) {

                        %>
                        <tbody>   
                            <tr class="text-center">
                                <td><%= e.getRECEIPT_DATE()%> </td>
                                <td>1</td>
                                <td>567</td>
                                <td><%= e.getSTR_TOTAL_AMOUNT()%></td>                                
                                <td>( <%= e.getSTR_TOTAL_STD()%> )</td>  
                                <td><%= e.getSTR_TOTAL_AMOUNT()%></td>
                            </tr>
                            <%
                                }
                            %>
                            <tr class="text-center"  style="border-bottom: 1px solid black; border-top: 1px solid black;">
                                <td style="padding-top:5px; padding-bottom:5px;" colspan="3"><b>Amount Totals All</b></td>
                                <td style="padding-top:5px; padding-bottom:5px;"><b>${sumAmount}</b></td>
                                <td style="padding-top:5px; padding-bottom:5px;"><b>( ${sumStd} )</b></td>
                                <td style="padding-top:5px; padding-bottom:5px;"><b>${sumAmount}</b></td>                            
                            </tr>
                        <tbody>
                    </table>
                </div>
            </div><!-- /# column -->
        </div>
        <!--  /Traffic -->
        <div class="clearfix"></div>

        <!-- .animated -->
    </div>
    <!-- /.content -->
    <div class="clearfix"></div>
    <!-- Footer -->
    <jsp:include page="footer.jsp" />