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
                    <form method="post" action="/etestgbackend/EditCounter" >
                        <input type="hidden" name="sem" value="${getCounterData.STUDY_SEMESTER}">
                        <input type="hidden" name="year" value="${getCounterData.STUDY_YEAR}">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;"><label class="fontvwhead"><i class="fa fa-book"></i> จัดการวิชาที่เปิดสอบ</label><br /> <hr></div>
                            </div>
                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;">
                                    <label style="font-size: 1.2vw;">
                                        <i class="fa fa-warning" style="color: red;"></i> ปี/ภาคการศึกษาปัจจุบัน
                                        <c:choose>
                                            <c:when test = "${getCounterData.STUDY_SEMESTER == '3'}">
                                                <b> Summer /${getCounterData.STUDY_YEAR}</b>&nbsp;&nbsp;&nbsp;<font color="#0027FF"><b>จำนวนที่นั่ง ${sumSeat} ที่นั่ง</b></font>
                                            </c:when>
                                            <c:otherwise>
                                                <b> เทอม ${getCounterData.STUDY_SEMESTER}/${getCounterData.STUDY_YEAR}</b>
                                            </c:otherwise>
                                        </c:choose>  
                                    </label>
                                    <a class="btn btn-success"  
                                       style="float: right; width: 150px; margin: 0 26px 0 5px; border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;"
                                       href="/etestgbackend/AddCourse">
                                        <i class="fa fa-plus-square"></i> เพิ่มวิชาสอบ
                                    </a>
                                    <br>
                                    <hr>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-12">

                                    <table id="datatable" class="table table-hover" style="border: none; border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);">
                                        <thead class="text-center"  style="background-color: #002752; color: #fff; border: none; font-weight: normal;">
                                            <tr>
                                                <th>ลำดับ</th>
                                                <th>รหัสวิชา</th>
                                                <th>หน่วยกิต</th>
                                                <th>วันที่เพิ่ม</th>
                                                <th>วันที่แก้ไข</th> 
                                                <th>แก้ไขข้อมูล</th>
                                                <th>ลบข้อมูล</th>
                                            </tr>
                                        </thead>
                                        <tbody class="text-center"> 
                                            <c:forEach items="${getCourseData}" var="getCourseData" begin="0" end="${cnt}" varStatus="loop">
                                                <tr>
                                                    <td><c:out value="${loop.count}"/></td>
                                                    <td>${getCourseData.COURSE_NO}</td>
                                                    <td>${getCourseData.CREDIT}</td>
                                                    <td> 
                                                        <c:choose>
                                                            <c:when test = "${getCourseData.INSERT_DATE eq null}">
                                                                <b> - </b>
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${getCourseData.INSERT_DATE}
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td> 
                                                        <c:choose>
                                                            <c:when test = "${getCourseData.UPDATE_DATE eq null}">
                                                                <b> - </b>
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${getCourseData.UPDATE_DATE}
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <a type="button" class="btn btn-warning" 
                                                           style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);"
                                                           href="/etestgbackend/GetEditCourse?courseno=${getCourseData.COURSE_NO}&sem=${getCourseData.SEMESTER}&year=${getCourseData.YEAR}">
                                                            <i class="fa fa-pencil"></i> แก้ไข </a>
                                                    </td>
                                                    <td>
                                                        <a type="button" class="btn btn-danger" 
                                                           style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;"
                                                           onclick="return confirm('คุณต้องการ ลบข้อมูลใช่หรือไม่?');"
                                                           href="/etestgbackend/DeleteCourse?courseno=${getCourseData.COURSE_NO}&sem=${getCourseData.SEMESTER}&year=${getCourseData.YEAR}">
                                                            <i class="fa fa-trash"></i> ลบ </a>
                                                    </td>
                                                </tr> 
                                            </c:forEach>
                                        </tbody>
                                    </table> 
                                </div>

                            </div>
                            <p><hr></p>
                            <!-- /# end table -->
                            <div class="row">
                                <div class="col-12"></div>

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