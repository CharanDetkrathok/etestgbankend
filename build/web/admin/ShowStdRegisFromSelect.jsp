<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="header.jsp" /> 
<style>
    .card-body {
        height: 100%; 
        /*background: linear-gradient(90deg, rgba(253,187,45,0.5) 0%, rgba(13,145,147,0.5) 100%);*/
    }
    label, b, td, .col-11 {
        font-size: 1.1vw;
    }
    hr {
        border-top: 1px solid #999;        
    }
</style>
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
                <div class="card">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-12" style="margin-left: 15px;">
                                <label class="fontvwhead">
                                    <i class="fa fa-address-book-o"></i> รายงานการลงทะเบียน (นักศึกษาทำการชำระเงินแล้ว)
                                </label>
                                <br/>                                 
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-1"></div>
                            <div class="col-11" style="margin-left: 15px;">
                                <label style="font-size: 1.5vw;">
                                    <i class="fa fa-warning" style="color: red;"></i> ปี/ภาคการศึกษาปัจจุบัน 
                                    <c:choose>
                                        <c:when test = "${getCounterData.STUDY_SEMESTER == '3'}">
                                            <b> Summer /${getCounterData.STUDY_YEAR}</b>
                                        </c:when>
                                        <c:otherwise>
                                            <b> ${getCounterData.STUDY_SEMESTER}/${getCounterData.STUDY_YEAR}</b>
                                        </c:otherwise>
                                    </c:choose>  </label><br>
                                วันที่ <label style="font-size: 1vw;">
                                    <c:choose>
                                        <c:when test = "${examdate eq '0'}">
                                            ทั้งหมด
                                        </c:when>
                                        <c:otherwise>
                                            <b> ${examdate}</b>
                                        </c:otherwise>
                                    </c:choose>
                                </label>
                                คาบสอบ <label style="font-size: 1vw;">
                                    <c:choose>
                                        <c:when test = "${sec eq '0'}">
                                            ทั้งหมด
                                        </c:when>
                                        <c:otherwise>
                                            <b> ${sec}</b>
                                        </c:otherwise>
                                    </c:choose>
                                </label>
                                <br />
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-12">

                                <table id="datatable" class="table table-hover" style="border: none; border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);">
                                    <thead class="text-center" style="background-color: #002752; color: #fff; border: none; font-weight: normal;">
                                        <tr>
                                            <th>ลำดับ</th>
                                            <th>รหัสนักศึกษา</th>
                                            <th>ชื่อ - สกล</th>
                                            <th>วันที่ลงทะเบียน</th>
                                            <th>เงินลงทะเบียน</th>
                                            <th>วันที่ชำระเงิน</th>
                                            <th>เลขที่ใบเสร็จ</th>
                                            <th>รายละเอียด</th>
                                        </tr>
                                    </thead>
                                    <tbody class="text-center"> 
                                        <c:forEach items="${getRecieptDataList}" var="getRecieptDataList" begin="0" end="${cnt}" varStatus="loop">
                                            <tr>
                                                <td><c:out value="${loop.count}"/></td>
                                                <td>${getRecieptDataList.STD_CODE}</td>
                                                <td class="text-left">${getRecieptDataList.NAME_THAI}</td>
                                                <td>${getRecieptDataList.REGIS_DATE}</td>
                                                <td>${getRecieptDataList.TOTAL_AMOUNT}</td>
                                                <td>${getRecieptDataList.PAYMENT_DATE}</td>
                                                <td>${getRecieptDataList.SLIP_RUN_NO}</td>
                                                
                                                <td>
                                                    <a type="button" class="btn btn-info" 
                                                       href="/etestgbackend/GetSelectStudentRegis?srcVal=1&sem=${getCounterData.STUDY_SEMESTER}&year=${getCounterData.STUDY_YEAR}&stdcode=${getRecieptDataList.STD_CODE}&refkey=${getRecieptDataList.REF_KEY}"
                                                       style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;">
                                                        <i class="fa fa-eye"></i> รายละเอียด 
                                                    </a>
                                                </td>
                                            </tr>
                                            <c:set var = "sum" value="${sum+getRecieptDataList.TOTAL_AMOUNT}"/> 
                                        </c:forEach>
                                    </tbody>
                                </table>               
                                <fmt:parseNumber var = "newsum" type = "number" value = "${sum}" />
                                <b style="color: blue;"><c:out value = "ยอดเงินลงทะเบียน : ${newsum} บาท" /></b><br>   
                            </div>

                        </div>
                        <p><hr></p>
                        <!-- /# end table -->
                        <p>
                        <div class="p-3">
                            <div class="row" style="margin-top: 20px;"> 
                                <div class="col-12" style="text-align: center;">
                                    <a href="/etestgbackend/GetRegisReport" type="button" class="btn btn-warning"
                                       style="height: 50px; width: 20%; border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #000; padding-top: 13px;">
                                        <i class="fa fa-backward"></i> กลับสู่หน้ารายงานหลัก </a> 
                                </div>
                            </div>
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
    <!-- Footer -->

    <script>
        $(document).ready(function () {
            $('#tb').DataTable();
        });
    </script>
    <jsp:include page="footer.jsp" />