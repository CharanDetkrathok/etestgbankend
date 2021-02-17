<%-- 
    Document   : Rep-ETesting101-Data
    Created on : Oct 21, 2020, 8:40:51 AM
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

        <div class="row" id="">
            <div class="col-lg-12">
                <div class="card">              
                    <table style="margin: 20px;" class="" id= "">

                        <thead>
                        <br id="hid"><br id="hid"><br id="hid"><br id="hid">
                        <tr>
                            <th>
                                <span class="incrementClass"></span>
                            </th>
                        </tr>
                        <tr class="text-center">
                            <th colspan="6">
                                <h6 class="text-center mb-2">สถาบันบริการวิชาการทางอิเล็กทรอนิกส์ มหาวิทยาลัยรามคำแหง</h6>
                            </th>
                        </tr>
                        <tr class="text-center">
                            <th colspan="6">
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
                            <th colspan="6">
                                <h6 class="text-center">เครื่องที่ 567 ประจำวันที่ ${registerDate} คาบที่ 1 (จำแนกตามเลขที่ใบเสร็จ)</h6>
                            </th>
                        </tr>
                        <tr style="border-bottom: 1px solid black;">
                            <th colspan="6">
                                REP-eTest101
                            </th>
                        </tr>
                        <tr class="text-center" style="border-bottom: 1px solid black;">
                            <th style="padding-top:5px; padding-bottom:5px;">ลำดับ</th>
                            <th style="padding-top:5px; padding-bottom:5px;">เครื่องที่/เลขที่ใบเสร็จ</th>
                            <th style="padding-top:5px; padding-bottom:5px;">รหัสนักศึกษา</th>
                            <th style="padding-top:5px; padding-bottom:5px;">ค่าลงทะเบียนสอบ</th>
                            <th style="padding-top:5px; padding-bottom:5px;">รวม</th>                            
                        </tr>   
                        </thead>                       
                        <tbody>
                            <%
                                List<REP_ETEST101> etest101 = (List<REP_ETEST101>) request.getAttribute("repETest101");
                                int recordNo = 1;
                                int Total_Amount = 0;
                                String totalAmount = "";
                                String Amount = "";

                                for (REP_ETEST101 e : etest101) {

                                    Amount = e.getAMOUNT();
                                    // เพิ่ม , หลัก พัน เหมื่อน แสน ล้าน       
                                    switch (Amount.length()) {
                                        case 7:
                                            Amount = Amount.substring(0, 4) + "," + Amount.substring(4);
                                            Amount = Amount.substring(0, 1) + "," + Amount.substring(1);
                                            break;
                                        case 6:
                                            Amount = Amount.substring(0, 3) + "," + Amount.substring(3);
                                            break;
                                        case 5:
                                            Amount = Amount.substring(0, 2) + "," + Amount.substring(2);
                                            break;
                                        case 4:
                                            Amount = Amount.substring(0, 1) + "," + Amount.substring(1);
                                            break;
                                    }
                            %>
                            <tr class="text-center">
                                <td><%= recordNo++%> </td>
                                <td><%= e.getSLIP_NO()%></td>
                                <td><%= e.getSTD_CODE()%></td>
                                <td><%= Amount%></td>
                                <td><%= Amount%></td>  

                            </tr>

                            <%
                                    Total_Amount = Integer.parseInt(e.getTOTAL_AMOUNT());
                                }

                                totalAmount = String.valueOf(Total_Amount);

                                // เพิ่ม , หลัก พัน เหมื่อน แสน ล้าน       
                                switch (totalAmount.length()) {
                                    case 7:
                                        totalAmount = totalAmount.substring(0, 4) + "," + totalAmount.substring(4);
                                        totalAmount = totalAmount.substring(0, 1) + "," + totalAmount.substring(1);
                                        break;
                                    case 6:
                                        totalAmount = totalAmount.substring(0, 3) + "," + totalAmount.substring(3);
                                        break;
                                    case 5:
                                        totalAmount = totalAmount.substring(0, 2) + "," + totalAmount.substring(2);
                                        break;
                                    case 4:
                                        totalAmount = totalAmount.substring(0, 1) + "," + totalAmount.substring(1);
                                        break;
                                }
                            %>   

                        </tbody>
                        <tr class="text-center"  style="border-top: 1px solid black; border-bottom: 1px solid black;">
                            <td style="padding-top:5px; padding-bottom:5px;"></td>
                            <td style="padding-top:5px; padding-bottom:5px;"><b>Counter totals</b></td>
                            <td style="padding-top:5px; padding-bottom:5px;"></td>
                            <td style="padding-top:5px; padding-bottom:5px;"><b><%= totalAmount%></b></td>
                            <td style="padding-top:5px; padding-bottom:5px;"><b><%= totalAmount%></b></td>                            
                        </tr>
                        <tr class="text-center"  style="border-bottom: 1px solid black;">
                            <td style="padding-top:5px; padding-bottom:5px;"></td>
                            <td style="padding-top:5px; padding-bottom:5px;"><b>Period totals</b></td>
                            <td style="padding-top:5px; padding-bottom:5px;"></td>
                            <td style="padding-top:5px; padding-bottom:5px;"><b><%= totalAmount%></b></td>
                            <td style="padding-top:5px; padding-bottom:5px;"><b><%= totalAmount%></b></td>                            
                        </tr>
                        <tfoot>

                        </tfoot>
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

