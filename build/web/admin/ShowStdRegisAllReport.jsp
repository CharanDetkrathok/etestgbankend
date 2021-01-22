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
                    <form method="post" action="/etestgbackend/GetRegisterSelectReport" >
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;"><label class="fontvwhead">
                                        <i class="fa fa-puzzle-piece"></i> รายงานลงทะเบียนทั้งหมด
                                    </label>
                                    <br />                                     
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
                                    <br />                                     
                                </div>
                            </div>
                            <hr>
                            <br>
                            <div class="row" style="display: flex;  align-items: center; justify-content: center;">
                                <div class="col-2" style="margin-left: 15px;">
                                    <label for="examdate">Select Date:</label>

                                    <select class="form-control"  name="examdate" id="examdate" required="true" style="height: 50px;">
                                        <option >---select date---</option>
                                        <option  value="0">----- ทั้งหมด -----</option>
                                        <c:forEach items = "${getExamDate}"  var = "getExamDate">
                                            <option value="${getExamDate.EXAM_DATE}">${getExamDate.EXAM_DATE}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-2">
                                    <label for="sec">Select Section:</label>
                                    <select class="form-control" name="sec" required="true" style="height: 50px;">
                                        <option  value="">---select section---</option>
                                        <option value="0">----- ทั้งหมด -----</option>
                                        <option value="1"> Section 1</option>
                                        <option value="2"> Section 2</option>
                                        <option value="3"> Section 3</option>
                                        <option value="4"> Section 4</option>
                                    </select>
                                </div> 
                                <div class="col-3">
                                    <label for="sec">:</label><br>
                                    <button type="submit" class="btn btn-success" style="height: 50px; width: 55%; border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;">
                                        <i class="fa fa-check"></i> ตกลง
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