<%-- 
    Document   : Seat-Management-Edit
    Created on : Sep 3, 2020, 12:23:08 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

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
            <div class="col-lg-12">
                <div class="card" style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);">          

                    <div class="card-body">
                        <div class="row">
                            <div class="col-12" style="margin-left: 15px;">
                                <label class="fontvwhead">
                                    <i class="fa fa-puzzle-piece"></i> แก้ไขแถวที่นั่งสอบ และแก้ไขจำนวนที่นั่งสอบ/แถว
                                </label>
                                <br> 
                                <hr>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-12" style="margin-left: 15px;">
                                <label style="font-size: 1vw;">
                                    <i class="fa fa-warning" style="color: red;"></i> ปี/ภาคการศึกษาปัจจุบัน
                                    <c:choose>
                                        <c:when test = "${getCounterData.STUDY_SEMESTER == '3'}">
                                            <b> Summer /${getCounterData.STUDY_YEAR}</b>
                                        </c:when>
                                        <c:otherwise>
                                            <b> ${getCounterData.STUDY_SEMESTER}/${getCounterData.STUDY_YEAR}</b>
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
                                    <!--FORM แก้ไขข้อมูล-->
                                    <form action="/etestgbackend/SeatManagementUpdate" method="POST">
                                        <div class="row">
                                            <div class="col-12 form-group">
                                                <input type="text" class="form-control" name="sumSeat" value="${sumSeat}" hidden="true">
                                            </div>
                                        </div>
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
                                                <label for=""><b>ห้องที่ทำการจัดสอบ</b></label>
                                                <input value="${BuildRow.BUILD_NO}" type="text" style="text-transform:uppercase" class="form-control" name="build_no" placeholder="กรอก ห้องที่ทำการจัดสอบ เช่น SKB801" required="true">
                                            </div>
                                            <div class="col-6 form-group">
                                                <label for=""><b>แถวสอบ</b></label>
                                                <select class="form-control" name="row_exam" required="true">
                                                    <option selected="true" value="${BuildRow.ROW_EXAM}"> --- แถว ${BuildRow.ROW_EXAM} มี ${BuildRow.SEAT_EXAM} ที่นั่ง --- </option>
                                                    <c:forEach items="${getBuildRoww}" var = "BuildRow" varStatus="count">
                                                        <option selected="true" value="${getBuildRoww.ROW_EXAM}">${getBuildRoww.ROW_EXAM}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-6 form-group">
                                                <label for=""><b>กำหนดจำนวนที่นั่งสอบ/แถว</b> <font color="tomato">( เฉพาะตัวเลขเท่านั้น )</font></label>                                                
                                                <input value="${BuildRow.SEAT_EXAM}" type="number" min="1" class="form-control" name="seat_exam" 
                                                       placeholder="กรอก จำนวนที่นั่งสอบ ต่อแถว" onKeyPress="if (this.value.length == 3)return false;" required="true">
                                            </div>
                                            <div class="col-6 form-group" style="margin-top: 32px;">        
                                                <button type="submit" name="submit" class="btn btn-success col-3" 
                                                        onclick="return confirm('คุณต้องการ แก้ไขข้อมูลใช่หรือไม่?');"  
                                                        style=" border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;"
                                                        >
                                                    <i class="fa fa-check"></i> ตกลง
                                                </button>
                                                &nbsp;
                                                <button type="reset" class="btn btn-warning col-3"  
                                                        style=" border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #000;"
                                                        >
                                                    <i class="fa fa-close"></i> ยกเลิก
                                                </button>
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
