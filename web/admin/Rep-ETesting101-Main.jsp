<%-- 
    Document   : Rep-ETesting101
    Created on : Oct 19, 2020, 2:18:56 PM
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
                <div class="card">              
                    <form method="post" action="/etestgbackend/RepETesting101?sumitt=1">
                        <input type="hidden" name="year" value="${getCounterData.STUDY_YEAR}">
                        <input type="hidden" name="sem" value="${getCounterData.STUDY_SEMESTER}">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;">
                                    <label class="fontvwhead">
                                        <i class="fa fa-file-excel-o"></i>                                         
                                        รายงานสรุปการรับเงินลงทะเบียนรายวัน (REP-Etest101) 
                                    </label>
                                    <br/>
                                    <hr>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-1"></div>
                                <div class="col-11" style="margin-left: 15px;">
                                    <label style="font-size: 1vw;">
                                        <b style="font-size: 20px;">
                                            <i class="fa fa-warning" style="color: red;"></i> ประจำภาคการศึกษา
                                            <c:choose>
                                                <c:when test = "${getCounterData.STUDY_SEMESTER == '3'}">
                                                    <b> Summer /${getCounterData.STUDY_YEAR}</b>
                                                </c:when>
                                                <c:otherwise>
                                                    <b> ${getCounterData.STUDY_SEMESTER}/${getCounterData.STUDY_YEAR}</b>
                                                </c:otherwise>                                                                                 
                                            </c:choose>  
                                                    &nbsp; 
                                        </b>
                                    </label>
                                    <br/><hr>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-1"></div>
                                <div class="col-3" style="margin-left: 15px;">
                                    <label for="examdate"><b>เลือก วัน/เดือน/ปี :</b></label>
                                    <select class="form-control" name="registerDate" required="true">
                                        <c:forEach items="${registerDate}" var = "registerDate" varStatus="count">
                                            <option  value="${registerDate.RECEIPT_DATE}"> ${registerDate.RECEIPT_DATE} </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-3" style="text-align: center; margin-top: 30px;">
                                    <button type="submit" class="btn btn-success" onclick="return confirm('คุณต้องการ ออกรายงาน REP-eTest101 ใช่หรือไม่?');"
                                            style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;">
                                        <i class="fa fa-file-excel-o"></i>&nbsp; REP-Etest101 
                                    </button>
                                    <button type="reset" class="btn btn-danger" style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;"><i class="fa fa-close"></i> ยกเลิก </button> 
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

