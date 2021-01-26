<%-- 
    Document   : Receipt-Management-Data
    Created on : Sep 29, 2020, 9:47:43 AM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="header.jsp" />
<style>
    .card .card-body {
        height: 100%;
    }
    label {
        font-size: 1.2vw;
    }
    hr {
        border-top: 1px solid #999;        
    }
</style>

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
            <div class="col-lg-12">
                <div class="card" style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);">      

                    <form method="post" action="#">
                        <input type="hidden" name="year" value="${getCounterData.STUDY_YEAR}">
                        <input type="hidden" name="sem" value="${getCounterData.STUDY_SEMESTER}">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;">
                                    <label class="fontvwhead">
                                        <i class="fa fa-puzzle-piece"></i> ปรับสถานะการชำระเงินของนักศึกษา 
                                    </label>
                                    <br>
                                    <hr>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;">
                                    <label style="font-size: 1.5vw;">
                                        <i class="fa fa-warning" style="color: red;"></i> ปี/ภาคการศึกษาปัจจุบัน
                                        <c:choose>
                                            <c:when test = "${getCounterData.STUDY_SEMESTER == '3'}">
                                                <b> Summer /${getCounterData.STUDY_YEAR}</b>
                                            </c:when>
                                            <c:otherwise>
                                                <b> เทอม ${getCounterData.STUDY_SEMESTER}/${getCounterData.STUDY_YEAR}</b>
                                            </c:otherwise>
                                        </c:choose>  
                                    </label>
                                    <br>
                                    <hr>
                                </div>
                            </div>
                            <div class="row">
                                <div class="container-fluid" style="padding: 30px 10px;">
                                    <div class="col-12 table-responsive-sm">

                                        <table id="datatable" class="table table-hover" style="border: none; border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);">
                                            <thead class="text-center" style="background-color: #002752; color: #fff; border: none; font-weight: normal;">
                                                <tr>
                                                    <th scope="col">ลำดับ</th>
                                                    <th scope="col">ชื่อ-นามสุล</th>
                                                    <th scope="col">รหัสนักศึกษา</th>
                                                    <th scope="col">ภาคการศึกษา</th>
                                                    <th scope="col">ค่าลงทะเบียน</th>   
                                                    <th scope="col">รายละเอียด</th>
                                                    <th scope="col">ปรับสถานะการชำระเงิน</th>
                                                </tr>
                                            </thead>
                                            <tbody class="text-center">

                                                <c:forEach items="${ReceiptData}" var = "ReceiptData" varStatus="count">                                                 
                                                    <tr>
                                                        <td scope="row">${count.count}</td>
                                                        <td class="text-left">${ReceiptData.NAME_THAI}</td>
                                                        <td>${ReceiptData.STD_CODE}</td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test = "${ReceiptData.RECEIPT_SEMESTER == '3'}">
                                                                    Summer
                                                                </c:when>
                                                                <c:otherwise>
                                                                    เทอม ${ReceiptData.RECEIPT_SEMESTER}
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>${ReceiptData.TOTAL_AMOUNT}</td>
                                                        <td>
                                                            <a type="button" class="btn btn-info" 
                                                               style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);"
                                                               href="/etestgbackend/ReceiptManagementDetail?receiptStdCode=${ReceiptData.STD_CODE}&receiptYear=${ReceiptData.RECEIPT_YEAR}&receiptSemester=${ReceiptData.RECEIPT_SEMESTER}&refKey=${ReceiptData.REF_KEY}">
                                                                <i class="fa fa-eye"></i> รายละเอียด 
                                                            </a>
                                                        </td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test = "${ReceiptData.RECEIPT_PAY_STATUS == 1}">
                                                                    <a type="button" class="btn btn-success" 
                                                                       style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);"
                                                                       onclick="return confirm('คุณต้องการ ปรับสถานะการชำระเงินเป็น ***ยังไม่ชำระเงิน!*** ใช่หรือไม่?');"
                                                                       href="/etestgbackend/ReceiptManagementUpdate?receiptStdCode=${ReceiptData.STD_CODE}&receiptYear=${ReceiptData.RECEIPT_YEAR}&receiptSemester=${ReceiptData.RECEIPT_SEMESTER}&receiptPayStatus=${ReceiptData.RECEIPT_PAY_STATUS}&refKey=${ReceiptData.REF_KEY}&year=${year}&sem=${sem}&examdate=${examdate}&section=${section}">
                                                                        <i class="fa fa-money"></i> ชำระเงินแล้ว &nbsp;
                                                                    </a>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <a type="button" class="btn btn-danger" 
                                                                       style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);"
                                                                       onclick="return confirm('คุณต้องการ ปรับสถานะการชำระเงินเป็น ***ชำระเงินเรียบร้อยแล้ว*** ใช่หรือไม่?');"
                                                                       href="/etestgbackend/ReceiptManagementUpdate?receiptStdCode=${ReceiptData.STD_CODE}&receiptYear=${ReceiptData.RECEIPT_YEAR}&receiptSemester=${ReceiptData.RECEIPT_SEMESTER}&receiptPayStatus=${ReceiptData.RECEIPT_PAY_STATUS}&refKey=${ReceiptData.REF_KEY}&year=${year}&sem=${sem}&examdate=${examdate}&section=${section}">
                                                                        <i class="fa fa-money"></i> ยังไม่ชำระเงิน
                                                                    </a>
                                                                </c:otherwise>
                                                            </c:choose> 

                                                        </td>
                                                    </tr>                                                        
                                                    <%--<c:set var = "sum" value="${sum+ReceiptData.TOTAL_AMOUNT}"/>--%> 
                                                </c:forEach>

                                            </tbody>
                                        </table>   
                                        <%--<fmt:parseNumber var = "newsum" type = "number" value = "${sum}" />--%>
                                        <!--<b style="color: blue;"><c:out value = "ยอดเงินลงทะเบียน : ${newsum} บาท" /></b><br>-->
                                    </div>
                                </div>
                            </div>
                        </div>

                    </form>
                    <!-- end main  -->
                    <div class="row"> 
                        <div class="col-12" style="text-align: center; padding-bottom: 50px;">
                            <label for="bt" class="fontvw" >&nbsp;</label><br /> 
                            <button type="button"  onclick="location = 'ReceiptManagement';" class="btn btn-warning"
                                    style="height: 50px; width: 20%; border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #000; padding-top: 10px;">
                                <i class="fa fa-backward"></i> กลับไปก่อนหน้า </button> <label id="demo" ></label>               
                        </div>
                    </div>
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

