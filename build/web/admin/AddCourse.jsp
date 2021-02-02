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
    li {
        list-style: none;
    }
    
    .add-item-list-mode {
        border: none;
        pointer-events: none;
    }
    
    .edit, .delete {
        margin: 5px;
        border-radius: 0; 
        box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);         
    }
    
    .edit {
        background: rgba(224, 168, 0, 0.8);
        color: #000;
    }
    
    .delete {
        background: rgba(200, 35, 51, 0.8);
        color: #fff;
    }
    
    .edit:hover {
        background: rgba(224, 168, 0, 1);
    }
    
    .delete:hover {
        background: rgba(200, 35, 51, 1);
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
                    <form method="post" action="/etestgbackend/AddCourse"  onsubmit="return validate();">
                        <input type="hidden" name="sem" value="${getCounterData.STUDY_SEMESTER}">
                        <input type="hidden" name="year" value="${getCounterData.STUDY_YEAR}">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;">
                                    <label class="fontvwhead">
                                        <i class="fa fa-book"></i> จัดการวิชาที่เปิดสอบ
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
                                        <input type="text" name="courseno" class="form-control" id="course-no" maxlength="7" placeholder="กรอกรหัสวิชา 7 หลัก เช่น RAM1000" onkeypress="return changeToUpperCase(event, this)"  required="true">
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
                                <div class="col-4">                                    
                                    <label style="font-weight: bold;">:</label>
                                    <br>
                                    <a type="button" name="addItems" id="addItems" class="btn btn-success"  
                                       style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;"
                                       >
                                        <i class="fa fa-plus-circle"></i> เพิ่ม Item
                                    </a>                                    
                                </div> 
                            </div> 

                            <div class="row">
                                <div class="col-12" style="margin-left: 15px;">
                                    <br> 
                                    <hr>
                                </div>
                            </div>
                            <!-- /# end body -->

                            <div class="row">

                                <div class="col-1"></div>
                                <ul id="incomplete-tasks">
                                    <div class="col-11">


                                    </div>
                                </ul>
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
    <script>
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
// ------------------------- เพิ่มข้อมูลรายวิชา เข้าไปใน Item lists -------------------
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
        let courseNo = document.querySelector('#course-no');
        let cradit = document.querySelector('#cr');
        let addItemBtn = document.querySelector('#addItems');
        let incompleteTasksHolder = document.querySelector('#incomplete-tasks');

        function editValueInputText() {
            let listItem = this.parentNode;
            console.log("edit");
        }

        function deleteValueInputText() {
            let listItem = this.parentNode;
            let ul = listItem.parentNode;

            ul.removeChild(listItem);
        }
        
        addItemBtn.onclick = () => {

            let listItem = document.createElement("li");

            let cnInput = document.createElement("input");
            let crdInput = document.createElement("input");

            let editBtn = document.createElement("button");
            let deleteBtn = document.createElement("button");

            cnInput.type = 'text';
            cnInput.name = 'cn';
            cnInput.className = 'add-item-list-mode';
            cnInput.value = courseNo.value;
            cnInput.placeholder = courseNo.value;

            crdInput.type = "text";
            crdInput.className = 'add-item-list-mode';
            crdInput.name = 'crd';
            crdInput.value = cradit.value;
            crdInput.placeholder = cradit.value;

            editBtn.innerHTML = 'แก้ไข';
            editBtn.type = 'button';
            editBtn.className = 'edit btn';

            deleteBtn.innerHTML = 'ลบ';
            deleteBtn.type = 'button';
            deleteBtn.className = 'delete btn';

            listItem.appendChild(cnInput);
            listItem.appendChild(crdInput);
            listItem.appendChild(editBtn);
            listItem.appendChild(deleteBtn);

            incompleteTasksHolder.appendChild(listItem);

            let editInput = listItem.querySelector('button.edit');
            let deleteInput = listItem.querySelector('button.delete');

            console.log('editInput=>' + editInput.parentNode.nodeName);
            console.log('deleteInput=>' + deleteInput.parentNode.nodeName);

            editInput.addEventListener('click', editValueInputText);
            deleteInput.addEventListener('click', deleteValueInputText);

            courseNo.value = '';
            cradit.value = '';
        };
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------
//------------------------------------------------------------------------------

        function goBack() {
            window.history.back();
        }

        function validate() {
            // check if input is bigger than 5
            var value = document.getElementById('course-no').value;
            var crvalue = document.getElementById('cr').value;
            if (value.length < 7 || value.length > 7) {
                alert('wtf');
                document.getElementById("wrntxt").innerHTML = '';
                return false; // keep form from submitting
            } else if (crvalue.length < 1) {
                alert('wtf2');
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

