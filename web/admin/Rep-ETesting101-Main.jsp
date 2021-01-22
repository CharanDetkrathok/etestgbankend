<%-- 
    Document   : Rep-ETesting101
    Created on : Oct 19, 2020, 2:18:56 PM
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
                                    <label style="font-size: 1.5vw;">
                                            <i class="fa fa-warning" style="color: red;"></i> ประจำภาคการศึกษา
                                            <c:choose>
                                                <c:when test = "${getCounterData.STUDY_SEMESTER == '3'}">
                                                    <b> Summer /${getCounterData.STUDY_YEAR}</b>
                                                </c:when>
                                                <c:otherwise>
                                                    <b> ${getCounterData.STUDY_SEMESTER}/${getCounterData.STUDY_YEAR}</b>
                                                </c:otherwise>                                                                                 
                                            </c:choose> &nbsp; 
                                    </label>
                                    <br/>
                                </div>
                            </div>
                            <hr>
                            <br/>
                            <div class="row" style="display: flex; align-items: center; justify-content: center;">
                                <div class="col-3" style="">
                                    <label for="examdate">เลือก วัน/เดือน/ปี :</label>
                                    <select class="form-control" name="registerDate" required="true" style="height: 50px;">
                                        <c:forEach items="${registerDate}" var = "registerDate" varStatus="count">
                                            <option  value="${registerDate.RECEIPT_DATE}"> ${registerDate.RECEIPT_DATE} </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-3" style="text-align: center; margin-top: 35px;">
                                    <button type="submit" class="btn btn-success" onclick="return confirm('คุณต้องการ ออกรายงาน REP-eTest101 ใช่หรือไม่?');"
                                            style="height: 50px; width: 100%; border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;">
                                        <i class="fa fa-file-excel-o"></i>&nbsp; REP-Etest101 
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
    <jsp:include page="footer.jsp" />

