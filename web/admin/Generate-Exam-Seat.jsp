<%-- 
    Document   : Generate-Exam-Seat
    Created on : Oct 3, 2020, 9:46:18 AM
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
                <div class="card">              
                    <form method="post" action="/etestgbackend/GenerateExamSeat?sumitt=1">
                        <input type="hidden" name="year" value="${getCounterData.STUDY_YEAR}">
                        <input type="hidden" name="sem" value="${getCounterData.STUDY_SEMESTER}">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;">
                                    <label class="fontvwhead">
                                        <i class="fa fa-puzzle-piece"></i> จัดที่นั่งสอบ
                                    </label>
                                    <br/>                                    
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-1"></div>
                                <div class="col-11" style="margin-left: 15px;">
                                    <label style="font-size: 1.5vw;">
                                        <i class="fa fa-warning" style="color: red;"></i> 
                                        ปี/ภาคการศึกษาปัจจุบัน 
                                        <c:choose>
                                            <c:when test = "${getCounterData.STUDY_SEMESTER == '3'}">
                                                <b> Summer /${getCounterData.STUDY_YEAR}</b>
                                            </c:when>
                                            <c:otherwise>
                                                <b> ${getCounterData.STUDY_SEMESTER}/${getCounterData.STUDY_YEAR}</b>
                                            </c:otherwise>
                                        </c:choose>  
                                    </label>
                                    <br/>
                                </div>
                            </div>
                            <hr>
                            <br/>
                            <div class="row" style="display: flex; align-items: center; justify-content: center;">
                                <div class="col-3" style="">
                                    <label for="etExamSeatExamDate">เลือก วัน/เดือน/ปี :</label>
                                    <select class="form-control" name="etExamSeatExamDate" required="true" style="height: 50px; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); border: none; border-radius: 0;">
                                        <c:forEach items="${etExamSeatExamDate}" var = "etExamSeatExamDate" varStatus="count">
                                            <option  value="${etExamSeatExamDate.EXAM_DATE}"> ${etExamSeatExamDate.EXAM_DATE} </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-4" style="text-align: left; margin: 32px 0 0 0; padding: 0 0 0 0;">
                                    <button type="submit" class="btn btn-success"
                                            style="height: 50px; width: 40%; border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;">
                                        <i class="fa fa-file-excel-o"></i>&nbsp; สร้างที่นั่งสอบ 
                                    </button>                                    
                                </div>                                
                            </div>
<!--                            <div class="row text-center" style="display: block;  align-items: center; justify-content: center; margin-top: 20px;"> 
                                <div class="col-12 text-center" style="text-align: center;">
                                    <h3 style="color: #000;">คลิกที่ปุ่มเพื่อ จัดแถวและที่นั่งสำหรับสอบ</h3><br>
                                    <button type="submit" class="btn btn-success" onclick="return confirm('คุณต้องการ สร้างที่นั่งใช่หรือไม่?');"
                                            style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff; padding: 15px 25px; font-size: 18px;">
                                        <i class="fa fa-address-card-o"></i> สร้างที่นั่งสอบ 
                                    </button> 
                                </div>
                            </div>-->
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
