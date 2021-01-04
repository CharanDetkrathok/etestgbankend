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
                <div class="card">              
                    <form method="post" action="/etestgbackend/GenerateSeat">
                        <input type="hidden" name="examdate" value="0">
                        <input type="hidden" name="sec" value="0">
                        <input type="hidden" name="year" value="${getCounterData.STUDY_YEAR}">
                        <input type="hidden" name="sem" value="${getCounterData.STUDY_SEMESTER}">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-12" style="margin-left: 15px;">
                                <label class="fontvwhead">
                                    <i class="fa fa-puzzle-piece"></i> จัดที่นั่งสอบ
                                </label>
                                <br/>
                                <hr>
                            </div>
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
                        <div class="row">
                            <div class="col-1"></div>
                        <div class="row" style="margin-top: 20px;"> 
                            <div class="col-12" style="text-align: center;">
                                <button type="submit" class="btn btn-primary" onclick="return confirm('คุณต้องการ สร้างที่นั่งใช่หรือไม่?');"><i class="fa fa-save"></i> สร้างที่นั่ง </button> 
                                <button type="button" class="btn btn-danger"><i class="fa fa-close"></i> Cancle </button> 
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