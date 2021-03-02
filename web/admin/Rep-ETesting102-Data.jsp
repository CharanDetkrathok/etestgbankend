<%-- 
    Document   : Rep-ETesting102
    Created on : Oct 19, 2020, 2:19:11 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@page import="sun.java2d.cmm.ProfileDeferralMgr"%>
<%@ page import="com.et.model.REP_ETEST101"%>
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
            font-family: 'Sarabun', sans-serif;
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
        footer, footer-inner bg-white, .site-footer{
            display: none;
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
                            font-family: 'Sarabun', sans-serif !important;
                            ">
                        <i class="fa fa-backward"></i> กลับไปก่อนหน้า </button>
                </div>
            </div>
            <div class="col-lg-3">
                <div class="card" id="hid">
                    <button type="button" class="btn btn-info" 
                            style=" 
                            border-radius: 0; box-shadow: 0 0 2px 0 rgba(0, 0, 0, 0.5); color: #000;
                            font-family: 'Sarabun', sans-serif !important;
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
                    <table style="margin: 20px;" cellpadding="1" cellspacing="1" id="DataPanel" class="gridtable"> 
                        <thead>
                        <br id="hid"><br id="hid"><br id="hid"><br id="hid">
                        <tr class="text-center">
                            <th colspan="5">
                                <h6 class="text-center mb-2">สถาบันบริการวิชาการทางอิเล็กทรอนิกส์ มหาวิทยาลัยรามคำแหง</h6>
                            </th>
                        </tr>
                        <tr class="text-center">
                            <th colspan="5">
                                <h6 class="text-center mb-2">
                                    รายงานสรุปการรับเงินลงทะเบียน e-Testing ประจำภาค 
                                    <c:choose>
                                        <c:when test = "${SEMESTER == '3'}">
                                            Summer /${YEAR}
                                        </c:when>
                                        <c:otherwise>
                                            ${SEMESTER}/${YEAR}
                                        </c:otherwise>
                                    </c:choose> 
                                </h6>
                            </th>
                        </tr>
                        <tr class="text-center">
                            <th colspan="5">
                                <h6 class="text-center">จำแนกตามคาบและเครื่องรับเงิน ประจำวันที่ ${registerDate} </h6>
                            </th>
                        </tr>
                        <tr style="border-bottom: 1px solid black;">
                            <th colspan="5">
                                REP-eTest102
                            </th>
                        </tr>
                        <tr class="text-center" style="border-bottom: 1px solid black;">
                            <th style="padding-top:5px; padding-bottom:5px;">เครื่องรับเงิน</th>
                            <th style="padding-top:5px; padding-bottom:5px;">เจ้าหน้าที่รับเงิน</th>
                            <th style="padding-top:5px; padding-bottom:5px;">ค่าลงทะเบียน</th>
                            <th style="padding-top:5px; padding-bottom:5px;">(จำนวนคน)</th>
                            <th style="padding-top:5px; padding-bottom:5px;">รวม</th>                            
                        </tr>     
                        </thead>
                        <%
                            List<REP_ETEST101> etest101 = (List<REP_ETEST101>) request.getAttribute("repETest101");
                            int recordNo = 1;
                            
                            for (REP_ETEST101 e : etest101) {
                        %>
                        <tbody>                    
                            <%
                                    recordNo++;
                                }
                                
                                // ลบจำนวนที่ increament จำนวน loop ลง 1 เพราะรอบสุดท้ายมันจะต้องเกินก่อนออก Loop
                                recordNo = recordNo - 1;                             
                            %>
                            <tr class="text-center">
                                <td>567</td>
                                <td>eTesting</td>
                                <td>${sumTotalAmount}</td>
                                <td>( <%= recordNo%> )</td>
                                <td>${sumTotalAmount}</td>  
                            </tr>
                            <tr class="text-center"  style="border-top: 1px solid black; border-bottom: 1px solid black;">
                                <td style="padding-top:5px; padding-bottom:5px;"><b>Counter totals</b></td>
                                <td style="padding-top:5px; padding-bottom:5px;"></td>
                                <td style="padding-top:5px; padding-bottom:5px;"><b>${sumTotalAmount}</b></td>
                                <td style="padding-top:5px; padding-bottom:5px;">( <%= recordNo%> )</td>
                                <td style="padding-top:5px; padding-bottom:5px;"><b>${sumTotalAmount}</b></td>                            
                            </tr>
                            <tr class="text-center"  style="border-bottom: 1px solid black;">
                                <td style="padding-top:5px; padding-bottom:5px;"><b>Amount Totals All</b></td>
                                <td style="padding-top:5px; padding-bottom:5px;"></td>
                                <td style="padding-top:5px; padding-bottom:5px;"><b>${sumTotalAmount}</b></td>
                                <td style="padding-top:5px; padding-bottom:5px;">( <%= recordNo%> )</td>
                                <td style="padding-top:5px; padding-bottom:5px;"><b>${sumTotalAmount}</b></td>                            
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

