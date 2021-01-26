<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="header.jsp" /> 
<style>
    .card-body {
        height: 30vw; 
        /*background: linear-gradient(90deg, rgba(253,187,45,0.5) 0%, rgba(13,145,147,0.5) 100%);*/
    }
    label, b, table, .col-md-6 {
        font-size: 1.2vw;
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
                    <form method="post" action="/etestgbackend/GetStdRegisSelectReport" >
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;">
                                    <label class="fontvwhead">
                                        <i class="fa fa-puzzle-piece"></i> รายงานการลงทะเบียน
                                    </label>
                                    <br/>                                     
                                </div>
                            </div>
                            <hr>
                            <input type="hidden" name="sem" value="${getCounterData.STUDY_SEMESTER}">
                            <input type="hidden" name="year" value="${getCounterData.STUDY_YEAR}">
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
                                        </c:choose>  
                                    </label>
                                    <br/>                                    
                                </div>
                            </div>
                            <hr>
                            <br>
                            <div class="row" style="display: flex;  align-items: center; justify-content: center;">
                                <input type="hidden" class="form-control"  name="examdate" id="examdate" value="0">
                                <input type="hidden" class="form-control" name="sec" id="sec" value="0">                                
                                <div class="col-12 text-center" style="text-align: center;">
                                    <label for="sec">รายงานการลงทะเบียน ของนักศึกษา ที่ทำการ <b style="font-style: italic;">ชำระเงินเรียบร้อยแล้ว</b></label><br>
                                    <br>
                                    <h3 style="color: #000;">คลิกที่ปุ่มเพื่อดูรายละเอียด</h3>
                                    <br>
                                    <button type="submit" class="btn btn-success" style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff; padding: 15px 35px; font-size: 18px;">
                                        <i class="fa fa-money"></i> รายงานชำระเงิน
                                    </button>  
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
    <jsp:include page="footer.jsp" />