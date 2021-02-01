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
                    <form method="post" action="/etestgbackend/EditCourse"  onsubmit="return validate();">
                        <input type="hidden" name="sem" value="${getCounterData.STUDY_SEMESTER}">
                        <input type="hidden" name="year" value="${getCounterData.STUDY_YEAR}">
                        <input type="hidden" name="coursenoTmp" value="${courseno}">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;">
                                    <label class="fontvwhead">
                                        <i class="fa fa-book"></i> แก้ไขวิชาที่เปิดสอบ
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

                            <!-- /# body -->
                            <div class="row">
                                <div class="col-1"></div>
                                <div class="col-3">
                                    <label for="course-no" style="font-weight: bold;">รหัสวิชา</label>
                                    <div class="input-group"> 
                                        <input type="text" name="courseno" class="form-control" id="course-no" value="${courseno}" 
                                              placeholder="กรอกรหัสวิชา 7 หลัก เช่น RAM1000" maxlength="7" onkeypress="return changeToUpperCase(event, this)"  required="true">
                                    </div>
                                    <div id="wrntxt" style="display: none; color: red;"> **กรอกรหัสวิชาให้ถูก</div>
                                </div>
                                <div class="col-3">
                                    <label for="cr" style="font-weight: bold;">จำนวนหน่วยกิต</label>
                                    <select name="credit" id="cr" class="form-control" required="true">
                                        <option value=""> เลือกหน่วยกิต </option>
                                        <option value="0"> ไม่มีหน่วยกิต</option>
                                        <option value="1"> 1 </option>
                                        <option value="2"> 2 </option>
                                        <option value="3"> 3 </option>
                                    </select>
                                    <div  id="crwrntxt"  style="display: none; color: red;"> **ระบุจำนวนหน่วยกิต</div>
                                </div>
                                <div class="col-5"></div>
                            </div> <p> 
                                <!-- /# end body -->
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
    <script>
        function goBack() {
            window.history.back();
        }

        function validate() {
            // check if input is bigger than 5
            var value = document.getElementById('course-no').value;
            var crvalue = document.getElementById('cr').value;
            if (value.length < 7 || value.length > 7) {
                alert('กรุณา กรอกรหัสวิชา 7 หลักเท่านั้น!!!');
                document.getElementById("wrntxt").innerHTML = '';
                return false; // keep form from submitting
            } else if (crvalue.length < 1) {
                alert('กรุณา กรอกรหัสวิชา 7 หลักเท่านั้น!!!');
                document.getElementById("crwrntxt").innerHTML = '';
                return false; // keep form from submitting
            }


            // else form is good let it submit, of course you will 
            // probably want to alert the user WHAT went wrong.

            return true;
        }

        function changeToUpperCase(event, obj) {
            charValue = (document.all) ? event.keyCode : event.which;
            if (charValue != "8" && charValue != "0" && charValue != "27") {
                obj.value += String.fromCharCode(charValue).toUpperCase();
                return false;
            } else {
                return true;
            }
        }

    </script>
    <jsp:include page="footer.jsp" />

