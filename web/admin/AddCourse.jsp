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

    .cn-add-item-list-mode, .crd-add-item-list-mode {
        border: none;
        pointer-events: none;        
    }

    .add-item-list-mode-edit {
        border: 1px solid;
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

    .edit-success {
        box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);
        background: rgba(40, 167, 69, 0.8); 
        color: #fff;
        border-radius: 0;         
    }

    .edit-success:hover {
        background: rgba(40, 167, 69, 1); 
    }

    .hidden-mode {
        display: none;
    }

    .on-hidden-mode {
        display: block;
    }

    .wrap-container {
        align-items: center;
        justify-content: center;
    }

    .item-wrap-container {
        align-items: center;
        justify-content: center;
        display: block;
    }

    .item-wrap-container {
        align-items: center;
        justify-content: center;
    }

    .item-wrap {
        align-items: center;
        justify-content: center;
        display: flex;
    }

    .course-label {
        margin-left: -202px;
    }

    .cradit-label {
        margin-left: 96px;
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
                        <div class="row wrap-container">
                            <div class="col-2 item-wrap-container"></div>
                            <div class="col-3 item-wrap-container">
                                <label for="course-no" style="font-weight: bold;">รหัสวิชา</label>
                                <div class="input-group"> 
                                    <input type="text" name="courseno" class="form-control" id="course-no" maxlength="7" placeholder="กรอกรหัสวิชา 7 หลัก เช่น RAM1000" onkeypress="return changeToUpperCase(event, this)"  required>
                                </div>
                                <div id="wrntxt" style="display: none; color: red;"> **กรอกรหัสวิชาให้ถูก</div>
                            </div>
                            <div class="col-3 item-wrap-container">
                                <label for="cr" style="font-weight: bold;">จำนวนหน่วยกิต</label> 
                                <select name="credit" id="cr" class="form-control" required >
                                    <option value=""> เลือกหน่วยกิต </option>
                                    <option value="0"> ไม่มีหน่วยกิต</option>
                                    <option value="1"> 1 </option>
                                    <option value="2"> 2 </option>
                                    <option value="3"> 3 </option>
                                </select>
                                <div  id="crwrntxt"  style="display: none; color: red;"> **ระบุจำนวนหน่วยกิต</div>
                            </div>
                            <div class="col-3 mt-2 item-wrap-container">           
                                <br>
                                <button  name="addItems" id="addItems" class="btn btn-success"  
                                         style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;"
                                         >
                                    <i class="fa fa-plus-circle"></i> เพิ่ม Item
                                </button>          &nbsp;&nbsp;&nbsp;                                   
                                <button type="button" class="btn btn-warning" onclick="goBack()"  
                                        style=" border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #000;">
                                    <i class="fa fa-backward"></i> กลับไปก่อนหน้า 
                                </button>                           
                            </div> 
                        </div> 

                        <div class="row">
                            <div class="col-12" style="margin-left: 15px;">
                                <br> 
                                <hr>
                            </div>
                        </div>
                        <!-- /# end body -->
                        <form method="post" action="/etestgbackend/AddCourse" class="hidden-mode"  onsubmit="return validate();">

                            <input type="hidden" name="sem" value="${getCounterData.STUDY_SEMESTER}">
                            <input type="hidden" name="year" value="${getCounterData.STUDY_YEAR}">

                            <div class="row item-wrap-container">
                                <div class="item-wrap">
                                    <label class="course-label">รหัสวิชา</label> <label class="cradit-label">จำนวนหน่วยกิต</label>
                                </div>  
                                <div class="col-12 item-wrap">                                    
                                    <ul id="incomplete-tasks">
                                    </ul>
                                </div>
                            </div>

                            <div class="row item-wrap-container"> 
                                <div class="col-12 item-wrap">                                    
                                    <button type="submit" class="btn btn-primary" onclick="return confirm('คุณต้องการ เพิ่มข้อมูลใช่หรือไม่?');"  
                                            style=" border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;">
                                        <i class="fa fa-check"></i> บันทึกวิชา 
                                    </button>  
                                </div>
                            </div>
                        </form>                            
                    </div>
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
        let onHiddenMode = document.querySelector('form.hidden-mode');

        function editValueInputText() {
            let listItem = this.parentNode;

            if (listItem.querySelector('button').classList.contains('edit')) {                              
                
                let cnEditText = listItem.querySelector('input[type=text].cn-add-item-list-mode');
                let crdEditText = listItem.querySelector('input[type=text].crd-add-item-list-mode');
                let editLabelBtn = listItem.querySelector('button.edit');
                
                editLabelBtn.innerHTML = 'ตกลง';
                editLabelBtn.classList.remove('edit');
                editLabelBtn.classList.toggle('edit-success');

                cnEditText.classList.remove('cn-add-item-list-mode');
                cnEditText.classList.toggle('cn-add-item-list-mode-edit');

                crdEditText.classList.remove('crd-add-item-list-mode');
                crdEditText.classList.toggle('crd-add-item-list-mode-edit');

            } else {

                let cnEditText = listItem.querySelector('input[type=text].cn-add-item-list-mode-edit');
                let crdEditText = listItem.querySelector('input[type=text].crd-add-item-list-mode-edit');

                if ((cnEditText.value !== '' && crdEditText.value !== '') && (cnEditText.value.length === 7) && (crdEditText.value.length === 1)) {

                    let editLabelBtn = listItem.querySelector('button.edit-success');



                    editLabelBtn.innerHTML = 'แก้ไข';
                    editLabelBtn.classList.remove('edit-success');
                    editLabelBtn.classList.toggle('edit');

                    cnEditText.classList.remove('cn-add-item-list-mode-edit');
                    cnEditText.classList.toggle('cn-add-item-list-mode');

                    crdEditText.classList.remove('crd-add-item-list-mode-edit');
                    crdEditText.classList.toggle('crd-add-item-list-mode');
                } else {

                    if (cnEditText.value === '' || cnEditText.value.length > 7 || cnEditText.value.length < 7) {
                        alert('รหัสวิชาต้องมี 7 ตัวอักษร');
                        cnEditText.style = 'background: rgba(200, 35, 51, 0.1); margin-right: 20px; border: 1px solid;';
                    }
                    console.log(cnEditText.value.length);
                    console.log(crdEditText.value.length);
                    if (crdEditText.value === '' || crdEditText.value.length < 1 || crdEditText.value.length > 1) {
                        alert('จำนวนหน่วยกิตต้อมี 1 ตัวอักษร');
                        crdEditText.style = 'background: rgba(200, 35, 51, 0.1); margin-right: 20px; border: 1px solid;';
                    }


                }
            }
        }

        function deleteValueInputText() {
            let listItem = this.parentNode;
            let ul = listItem.parentNode;

            ul.removeChild(listItem);

            let ulIncomplate = document.querySelector('ul#incomplete-tasks');

            console.log(ulIncomplate.childNodes[0].nextSibling);

            if (ulIncomplate.childNodes[0].nextSibling === null) {
                onHiddenMode.classList.remove('on-hidden-mode');
                onHiddenMode.classList.toggle('hidden-mode');
            }

        }

        addItemBtn.onclick = () => {

            if (courseNo.value !== '' && cradit.value !== '') {

                if (onHiddenMode) {
                    onHiddenMode.classList.remove('hidden-mode');
                    onHiddenMode.classList.toggle('on-hidden-mode');
                }

                let listItem = document.createElement("li");

                let cnInput = document.createElement("input");
                let crdInput = document.createElement("input");

                let editBtn = document.createElement("button");
                let deleteBtn = document.createElement("button");

                cnInput.type = 'text';
                cnInput.name = 'cn';
                cnInput.className = 'cn-add-item-list-mode';
                cnInput.value = courseNo.value;
                cnInput.style = 'margin-right: 20px;';

                crdInput.type = "text";
                crdInput.className = 'crd-add-item-list-mode';
                crdInput.name = 'crd';
                crdInput.value = cradit.value;
                crdInput.style = 'margin-right: 20px;';

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

                listItem.appendChild(document.createElement('hr'));

                incompleteTasksHolder.appendChild(listItem);

                let editInput = listItem.querySelector('button.edit');
                let deleteInput = listItem.querySelector('button.delete');

                editInput.addEventListener('click', editValueInputText);
                deleteInput.addEventListener('click', deleteValueInputText);

                courseNo.value = '';
                cradit.value = '';
            }
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

