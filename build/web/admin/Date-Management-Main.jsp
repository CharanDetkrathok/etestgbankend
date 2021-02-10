<%-- 
    Document   : Date-Management-Main
    Created on : Sep 9, 2020, 9:13:39 AM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="header.jsp" />
<style>
    .card .card-body {
        height: 100%;
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
                                        <i class="fa fa-puzzle-piece"></i> กำหนดวันสอบ
                                    </label>
                                    <br>
                                    <hr>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;">
                                    <label style="font-size: 1.2vw;">
                                        <i class="fa fa-warning" style="color: red;"></i> ปี/ภาคการศึกษาปัจจุบัน
                                        <c:choose>
                                            <c:when test = "${getCounterData.STUDY_SEMESTER == '3'}">
                                                <b> Summer /${getCounterData.STUDY_YEAR}</b>&nbsp;&nbsp;&nbsp;<font color="#0027FF"><b>จำนวนที่นั่ง / คาบสอบ ${sumSeat} ที่นั่ง</b></font>
                                            </c:when>
                                            <c:otherwise>
                                                <b> เทอม ${getCounterData.STUDY_SEMESTER}/${getCounterData.STUDY_YEAR}</b>&nbsp;&nbsp;&nbsp;<font color="#0027FF"><b>จำนวนที่นั่ง / คาบสอบ ( คาบละไม่เกิน ${sumSeat} ที่นั่ง)</b></font>
                                            </c:otherwise>
                                        </c:choose>  
                                    </label>
                                    <a class="btn btn-success"  
                                       style="float: right; width: 100px; margin: 0 26px 0 5px; border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;"
                                       href="/etestgbackend/DateManagementInsert?Create=1">
                                        <i class="fa fa-plus-square"></i> เพิ่ม
                                    </a>
                                    <br>
                                    <hr>
                                </div>
                            </div>
                            <div class="row">
                                <div class="container-fluid" style="padding: 30px 10px;">
                                    <div class="col-12 table-responsive-sm">

                                        <table id="datatable" class="table table-hover" style="border: none; border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);">
                                            <thead class="text-center"  style="background-color: #002752; color: #fff; border: none; font-weight: normal;">
                                                <tr>
                                                    <th scope="col">ลำดับ</th>
                                                    <th scope="col">วันที่สอบ</th>   
                                                    <th scope="col">คาบสอบ</th>
                                                    <th scope="col">จำนวนที่นั่งสอบ</th>                                                    
                                                    <th scope="col">เพิ่มข้อมูลเมื่อ</th>
                                                    <th scope="col">แก้ไขข้อมูลเมื่อ</th>
                                                    <th scope="col">แก้ไขข้อมูล</th>
                                                    <th scope="col">ลบข้อมูล</th>
                                                </tr>
                                            </thead>
                                            <tbody class="text-center">

                                                <c:forEach items="${ExamSeat}" var = "ExamSeat" varStatus="count">                                                 
                                                    <tr>
                                                        <td scope="row">${count.count}</td>
                                                        <td>${ExamSeat.EXAM_DATE}</td>
                                                        <td>${ExamSeat.PERIOD}</td>
                                                        <td>${ExamSeat.EXAM_SEAT}</td>
                                                        <td>${ExamSeat.CREATE_DATE}</td> 
                                                        <td>
                                                            <c:choose>
                                                                <c:when test = "${ExamSeat.UPDATE_DATE == null}">
                                                                    -
                                                                </c:when>
                                                                <c:otherwise>
                                                                    ${ExamSeat.UPDATE_DATE}
                                                                </c:otherwise>
                                                            </c:choose>       
                                                        </td>
                                                        <td>                                                            
                                                            <a type="button" class="btn btn-warning" 
                                                               style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);"
                                                               href="/etestgbackend/DateManagementUpdate?Year=${ExamSeat.YEAR}&Semester=${ExamSeat.SEMESTER}&Exam_Date=${ExamSeat.EXAM_DATE}&Section=${ExamSeat.PERIOD}">
                                                                <i class="fa fa-pencil"></i> แก้ไข 
                                                            </a>                                                            
                                                        </td>
                                                        <td>                                                            
                                                            <a type="button" class="btn btn-danger" 
                                                               style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;"
                                                               onclick="return confirm('คุณต้องการ ลบข้อมูลใช่หรือไม่?');"
                                                               href="/etestgbackend/DateManagementDelete?Year=${ExamSeat.YEAR}&Semester=${ExamSeat.SEMESTER}&Exam_Date=${ExamSeat.EXAM_DATE}">
                                                                <i class="fa fa-trash"></i> ลบ 
                                                            </a>                                                            
                                                        </td>
                                                    </tr> 
                                                </c:forEach>

                                            </tbody>
                                        </table>                             
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>

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
