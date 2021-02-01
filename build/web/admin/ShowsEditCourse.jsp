<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="header.jsp" /> 
<style>
    .card .card-body {
        height: 100%;
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
                    <form method="post" action="/etestgbackend/SaveFile"  enctype="multipart/form-data">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;">
                                    <label class="fontvwhead">
                                        <i class="fa fa-puzzle-piece"></i> Upload files
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
                                                <b> ${getCounterData.STUDY_SEMESTER}/${getCounterData.STUDY_YEAR}</b>
                                            </c:otherwise>
                                        </c:choose>  
                                    </label>
                                    <br> 
                                    <hr>
                                </div>
                            </div>

                            <div class="row" style="margin-top: 25px;">                                 
                                <div class="col-2" style="text-align: right;">
                                    <label for="fiscalyear" class="fontvw" style="font-weight: bold;">File วิชาที่เปิด:</label> 
                                </div>   
                                <div class="col-5" >                                     
                                    <input type="file" class="form-control" name="file_course" required />
                                </div> 
                                <div class="col-5" style="text-align: center;">  </div>   
                            </div>
                            <div class="row" style="margin-top: 25px;">                                 
                                <div class="col-2" style="text-align: right;">
                                    <label for="fiscalyear" class="fontvw" style="font-weight: bold;">File จำนวนที่นั่ง:</label> 
                                </div>   
                                <div class="col-5" >                                     
                                    <input type="file" class="form-control"  name="file_seat" required />
                                </div> 
                                <div class="col-5" style="text-align: center;">  </div>   
                            </div>
                            <div class="row" style="margin-top: 25px;">                                 
                                <div class="col-2" style="text-align: right;">
                                    <label for="fiscalyear" class="fontvw" style="font-weight: bold;">File จำนวนคาบ:</label> 
                                </div>   
                                <div class="col-5" >                                     
                                    <input type="file" class="form-control"  name="file_rowseat" required />
                                </div> 
                                <div class="col-5" style="text-align: center;">  </div>   
                            </div>
                            <div class="row" style="margin-top: 25px;">                                 
                                <div class="col-2" style="text-align: right;">
                                    <label for="fiscalyear" class="fontvw" style="font-weight: bold;">File ลำดับที่นั่ง:</label> 
                                </div>   
                                <div class="col-5" >                                     
                                    <input type="file" class="form-control"  name="file_seatorder" required />
                                </div> 
                                <div class="col-5" style="text-align: center;">  </div>   
                            </div>

                            <div class="row"> 
                                <div class="col-1"></div>

                                <div class="col-11"><label for="bt" class="fontvw" >&nbsp;</label><br /> 
                                    <button type="submit" class="btn btn-primary" onclick="return confirm('คุณต้องการ เพิ่มข้อมูลใช่หรือไม่?');"  
                                            style=" border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;">
                                        <i class="fa fa-check"></i> บันทึกวิชา 
                                    </button>                                     
                                    <button type="button" class="btn btn-warning" onclick="goBack()"  
                                            style=" border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #000;">
                                        <i class="fa fa-backward"></i> กลับไปก่อนหน้า 
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