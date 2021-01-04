<%-- 
    Document   : Receipt-Management-Detail
    Created on : Sep 29, 2020, 10:01:51 AM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="header.jsp" /> 

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
                    <form method="post" action="/etestgbackend/EditCounter" >
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;"><label class="fontvwhead">
                                        <i class="fa fa-puzzle-piece"></i> แสดงข้อมูลลงทะเบียนนักศึกษา </label></div>
                            </div>
                            <div class="row">
                                <div class="col-1"></div>
                                <div class="col-11" style="margin-left: 15px;">
                                    <label style="font-size: 1vw;">
                                        <i class="fa fa-warning" style="color: red;"></i> ปี/ภาคการศึกษาปัจจุบัน 
                                        <c:choose>
                                            <c:when test = "${getCounterData.STUDY_SEMESTER == '3'}">
                                                <b> Summer /${getCounterData.STUDY_YEAR}</b>
                                            </c:when>
                                            <c:otherwise>
                                                <b> ${getCounterData.STUDY_SEMESTER}/${getCounterData.STUDY_YEAR}</b>
                                            </c:otherwise>
                                        </c:choose>  </label><br /> <hr></div>
                            </div>

                            <!-- main  -->
                            <div class="row"> 
                                <div class="col-md-5">
                                    <div class="row"> 
                                        <div class="col-md-6">
                                            <b>รหัสนักศึกษา: </b><br> ${stdcCode} <br><p>
                                        </div> 
                                    </div>
                                    <div class="row"> 
                                        <div class="col-md-6">
                                            <b>ชื่อ - สกุล: </b><br> ${name} <br><p>
                                        </div> 
                                    </div>   
                                    <div class="row"> 
                                        <div class="col-md-6">
                                            <b> ลงทะเบียนวันที่: </b><br> ${date} 
                                        </div>
                                        <div class="col-md-6">  </div>
                                    </div>  
                                </div>
                                <div class="col-md-7">
                                    <div class="row"> 
                                        <div class="col-md-12">
                                            <h3> <i class="fa fa-retweet"></i> ข้อมูลวิชาลงทะเบียน <hr></h3> 
                                            <h3>
                                        </div> 
                                    </div>
                                    <table class="table table-striped-sm table-responsive-sm">
                                        <thead>
                                            <tr class="bg-primary" style="font-weight: bold;color: white;">
                                                <th scope="col">#No</th>
                                                <th scope="col">วิชา</th>
                                                <th scope="col">หน่วยกิต</th>
                                                <th scope="col">คาบสอบ</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${ReceiptData}" var="ReceiptData"  varStatus="loop">                                                      
                                                <tr>
                                                    <td><c:out value="${loop.count}"/></td>
                                                    <td>${ReceiptData.COURSE_NO}</td>
                                                    <td>${ReceiptData.CREDIT}</td>
                                                    <td>${ReceiptData.EXAM_DATE}</td>
                                                </tr> 
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                    <hr>
                                </div>

                            </div>

                            <div class="row p-2"> 
                                <div class="col-12" style="text-align: center;"> <hr></div>
                            </div>
                            <!-- end main  -->
                            <div class="row"> 
                                <div class="col-12" style="text-align: center; padding-bottom: 50px;">
                                    <label for="bt" class="fontvw" >&nbsp;</label><br /> 
                                    <button type="button"  onclick="goBack()" class="btn btn-warning">
                                        <i class="fa fa-backward"></i> กลับไปก่อนหน้า </button> <label id="demo" ></label>               
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
    <!-- Footer -->

    <script>
        function goBack() {
            window.history.back();
        }
    </script>
    <jsp:include page="footer.jsp" />


