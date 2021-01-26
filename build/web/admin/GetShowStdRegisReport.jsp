<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="header.jsp" /> 
<style>
    .card-body {
        height: 100%; 
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
                    <form method="post" action="/etestgbackend/EditCounter" >
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;">
                                    <label class="fontvwhead">
                                        <i class="fa fa-puzzle-piece"></i> แสดงข้อมูลลงทะเบียนนักศึกษา 
                                    </label>
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
                            <!-- main  -->
                            <div class="row ml-1"> 
                                <div class="col-md-5">
                                    <div class="row"> 
                                        <div class="col-md-6">
                                            <b>รหัสนักศึกษา: </b> <br>${stdcode}<p>
                                        </div> 
                                    </div>
                                    <div class="row"> 
                                        <div class="col-md-6">
                                            <b>ชื่อ - สกุล: </b> <br>${name}<p>
                                        </div> 
                                    </div>   
                                    <div class="row"> 
                                        <div class="col-md-6">
                                            <b> ลงทะเบียนวันที่: </b> <br>${registdate}
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
                                    <table class="table table-hover" style="border: none; border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);">
                                        <thead class="text-center" style="background-color: #002752; color: #fff; border: none; font-weight: normal;">
                                            <tr style="color: white;">
                                                <th scope="col">ลำดับ</th>
                                                <th scope="col">วิชา</th>
                                                <th scope="col">หน่วยกิต</th>
                                                <th scope="col">คาบสอบ</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${getRecieptDataList}" var="getRecieptDataList" begin="0" end="${cnt}" varStatus="loop">                                                      
                                                <tr>
                                                    <td><c:out value="${loop.count}"/></td>
                                                    <td>${getRecieptDataList.COURSE_NO}</td>
                                                    <td>${getRecieptDataList.CREDIT}</td>
                                                    <td>${getRecieptDataList.EXAM_DATE}</td>
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
                                <div class="col-12" style="text-align: center;">
                                    <label for="bt" class="fontvw" >&nbsp;</label><br /> 
                                    <button type="button"  onclick="goBack()" class="btn btn-warning"
                                            style="height: 50px; width: 20%; border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #000; padding-top: 10px;">
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

