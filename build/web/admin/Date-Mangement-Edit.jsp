<%-- 
    Document   : Date-Mangement-Edit
    Created on : Sep 25, 2020, 2:03:41 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="header.jsp" />
<style>
    .card-body {
        height: 30vw; 
        /*background: linear-gradient(90deg, rgba(253,187,45,0.5) 0%, rgba(13,145,147,0.5) 100%);*/
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

                    <div class="card-body">
                        <div class="row">
                            <div class="col-12" style="margin-left: 15px;">
                                <label class="fontvwhead">
                                    <i class="fa fa-puzzle-piece"></i> แก้ไข จำนวนที่นั่ง ตามวันสอบ
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
                                            <b> ${getCounterData.STUDY_SEMESTER}/${getCounterData.STUDY_YEAR}</b>&nbsp;&nbsp;&nbsp;<font color="#0027FF"><b>จำนวนที่นั่ง / คาบสอบ ( คาบละไม่เกิน ${sumSeat} ที่นั่ง)</b></font>
                                        </c:otherwise>
                                    </c:choose>  
                                </label>
                                <br> 
                                <hr>
                            </div>
                        </div>

                        <div class="row">
                            <div class="container-fluid" style="padding: 10px 60px;">
                                <div class="col-12">
                                    <!--FORM เพิ่มข้อมูล-->
                                    <form action="/etestgbackend/DateManagementUpdate" method="POST">
                                        <div class="row">
                                            <div class="col-6 form-group">
                                                <input type="number" class="form-control" name="year" value="${getCounterData.STUDY_YEAR}" hidden="true">
                                            </div>
                                            <div class="col-6 form-group">       
                                                <select class="form-control" name="semester" hidden="true">
                                                    <c:choose>
                                                        <c:when test = "${getCounterData.STUDY_SEMESTER == '1'}">
                                                            <option selected="true" value="1">เทอม 1</option>
                                                        </c:when>
                                                        <c:when test = "${getCounterData.STUDY_SEMESTER == '2'}">
                                                            <option selected="true" value="2">เทอม 2</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option selected="true" value="3">ภาคฤดูร้อน</option>
                                                        </c:otherwise>
                                                    </c:choose>         
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-6 form-group">
                                                <label for="">วัน/เดือน/ปี สอบ</label>
                                                <input type="text" value="${Exam_Date}" name="date_exam" hidden="true">
                                                <input type="text" class="form-control" value="${Exam_Date}" disabled="true">
                                            </div>
                                            <div class="col-6 form-group">
                                                <label for="">เลือกคาบสอบ</label>
                                                <select class="form-control" name="section" required="true">
                                                    <option value="0"> แก้ไขทุกคาบสอบ </option>
                                                    <option selected="true" value="${Section}">คาบ ${Section}</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-6 form-group">
                                                <label for="">กำหนดจำนวนที่นั่งสอบ</label>
                                                <input type="number" class="form-control" name="seat_exam" min="0" value="${SeatExam}" placeholder="รวม ${SeatExam} ที่นั่ง" required="true">
                                            </div>
                                            <div class="col-6 form-group" style="margin-top: 35px;">        
                                                <button type="submit" name="submit" class="btn btn-success col-3" 
                                                        onclick="return confirm('คุณต้องการ เพิ่มข้อมูลใช่หรือไม่?');"  
                                                        style=" border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;"
                                                        >
                                                    <i class="fa fa-check"></i> ตกลง
                                                </button>
                                                &nbsp;
                                                <button type="reset" class="btn btn-danger col-3"  
                                                        style=" border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;"
                                                        >
                                                    <i class="fa fa-close"></i> คืนค่า
                                                </button>
                                                &nbsp;
                                                <a type="button" href="/etestgbackend/DateManagement" class="btn btn-warning"
                                                   style="width: 30%; border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #000;">
                                                    <i class="fa fa-backward"></i> &nbsp; กลับ 
                                                </a>
                                            </div>
                                        </div>
                                    </form>
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
    <jsp:include page="footer.jsp" />
