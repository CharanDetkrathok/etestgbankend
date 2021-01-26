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
                    <form method="post" action="/etestgbackend/EditCounter">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;">
                                    <label class="fontvwhead">
                                        <i class="fa fa-puzzle-piece"></i> จัดการปี/ภาคการศึกษา
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
                                        </c:choose>  
                                    </label>
                                    <br/>                                     
                                </div>
                            </div>
                            <hr>
                            <br/>
                            <div class="row" style="display: flex; align-items: center; justify-content: center;">
                                <div class="col-2">
                                    <label for="fiscalyear" class="fontvw" style="margin-left: 10px; font-size: 1.2vw;">ปี ปัจจุบัน : </label> 
                                    <input type="text" class="form-control" name="fiscalyear" id="fiscalyear" required="true" style="height: 50px; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); border: none; border-radius: 0px;" value="${getCounterData.FISCAL_YEAR}">
                                </div>
                                <div class="col-2">
                                    <label for="year" class="fontvw" style="margin-left: 10px; font-size: 1.2vw;">ปีการศึกษา : </label> 
                                    <input type="text" class="form-control" name="year" id="year" required="true" style="height: 50px; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); border: none; border-radius: 0px;" value="${getCounterData.STUDY_YEAR}">
                                </div>
                                <div class="col-2">
                                    <label for="sem" class="fontvw" style="margin-left: 10px; font-size: 1.2vw;">ภาคการศึกษา : </label> 
                                    <input type="text" class="form-control" name="sem" id="sem" required="true" style="height: 50px; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); border: none; border-radius: 0px;" value="${getCounterData.STUDY_SEMESTER}">
                                </div>
                                <div class="col-2">
                                    <label for="bt" class="fontvw" >&nbsp;</label>
                                    <br/> 
                                    <button type="submit" class="btn btn-success" style="height: 50px; width: 100%; font-size: 1.2vw;  border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);">
                                        <i class="fa fa-save"></i> บันทึก
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