<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@page import="sun.java2d.cmm.ProfileDeferralMgr"%>
<%@ page import="com.et.model.ET_RU_COURSE"%>

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
        margin-right: 20px;
        padding: 5px 10px;
        text-align: center;
    }

    .cn-add-item-list-mode-edit, .crd-add-item-list-mode-edit { 
        margin-right: 20px; 
        padding: 5px 10px;
        text-align: center;
    }

    .cn-add-item-list-mode-edit-fail, .crd-add-item-list-mode-edit-fail { 
        background: rgba(200, 35, 51, 0.1); 
        margin-right: 20px; 
        padding: 5px 10px;
        border: 1px solid;
        text-align: center;
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

    .delete-list-animation {
        transform: translatex(20rem);
        transition: 900ms ease all;
        opacity: 0;
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

    .item-wrap {
        align-items: center;
        justify-content: center;
        display: flex;
    }

    .course-label {
        width: 200px;
        pointer-events: none;     
        margin-right: 41px;
        padding: 5px 10px;
        text-align: center;
    }

    .cradit-label {
        pointer-events: none;     
        margin-right: 174px;
        padding: 5px 10px;
        text-align: center;
    }

    .label-edit-text {
        align-items: center;
        justify-content: center;
        display: flex;
        color: rgba(200, 35, 51, 0.8);
    }

    .tooltip{
        position:absolute;
        margin:5px;
        width:200px;
        height:50px;
        border:1px solid black;
        display:none;
    }

    #must-input-text {
        background: rgba(200, 35, 51, 0.8); 
        text-align: center;
        align-items: center;
        justify-content: center;
        display: block;
        color: #fff;
    }

    #duplicated-input-text {
        background: rgba(224, 168, 0, 1);
        text-align: center;
        align-items: center;
        justify-content: center;
        display: block;   
        color: #000;
    }

    .subimt-btn {
        border-radius: 0; 
        box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); 
        color: #fff;
    }

    .hin-subimt-btn {
        border-radius: 0; 
        pointer-events: none;
        box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); 
        color: #999;
    }

    .hidden { 
        display: none;
    }

    .bg-display {
        background-color: rgb(251, 251, 251);
        border-bottom: 1px solid #999; 
        border-left: 1px solid #999;
        border-right: 1px solid #999;
        padding: 5px; 
    }

    .courseno-text-input {        
        border-radius: 0; 
        border: 1px solid #999;
        box-shadow: 0 0 1px 0 rgba(0, 0, 0, 0.5); 
    }

    #ru-course-tasks {
        width: 88%;
        position: absolute;
        z-index: 200;
    }

    #ru-course-tasks li:hover {
        background-color: #eee;
        cursor: pointer;
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
                            <div class="col-1 item-wrap-container"></div>
                            <div class="col-3 item-wrap-container">
                                <label for="course-no" style="font-weight: bold;">รหัสวิชา</label>
                                <div class="input-group"> 
                                    <input type="text" name="courseno" class="form-control courseno-text-input" id="course-no" 
                                           maxlength="7" placeholder="กรอกรหัสวิชา 7 ตัวอักษร" 
                                           onkeypress="return changeToUpperCase(event, this)"  
                                           required autocomplete="off">
                                </div>

                                <!-- Interpolation วิชา และหน่วยกิต ที่มหาลัยเปิดสอน เอาไว้ทำ Navigater search filter แสดง วิชาอัตโนมัต -->

                                <ul id="ru-course-tasks">
                                    <%
                                        List<ET_RU_COURSE> ruCourse = (List<ET_RU_COURSE>) request.getAttribute("ruCourse");
                                        for (ET_RU_COURSE ruC : ruCourse) {
                                    %>
                                    <li class="hidden bg-display" id="select-course-items">
                                        <span><%= ruC.getCOURSE_NO()%></span>
                                        <span>( <%= ruC.getCREDIT()%> )</span>
                                        <input hidden id="course-no-items-span" value="<%= ruC.getCOURSE_NO()%>">
                                        <input hidden id="credit-items-span" value="<%= ruC.getCREDIT()%>">
                                    </li>
                                    <%
                                        }
                                    %>
                                </ul>

                                <!-- จบ Interpolation วิชา และหน่วยกิต ที่มหาลัยเปิดสอน -->
                            </div>
                            <div class="col-3 item-wrap-container">
                                <label for="cr" style="font-weight: bold;">จำนวนหน่วยกิต</label> 
                                <select name="credit" id="cr" class="form-control courseno-text-input" required >
                                    <option value=""> เลือกหน่วยกิต </option>
                                    <option value="0"> 0 </option>
                                    <option value="1"> 1 </option>
                                    <option value="2"> 2 </option>
                                    <option value="3"> 3 </option>
                                </select>                                 
                            </div>
                            <div class="col-4 mt-2 item-wrap-container">           
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
                                <label id="duplicated-input-text"></label>
                                <label id="must-input-text"></label>                                
                                <hr>
                            </div>
                        </div>
                        <!-- /# end body -->
                        <form method="post" action="/etestgbackend/AddCourse?submititem=1" class="hidden-mode">

                            <input type="hidden" name="sem" value="${getCounterData.STUDY_SEMESTER}">
                            <input type="hidden" name="year" value="${getCounterData.STUDY_YEAR}">

                            <div class="row item-wrap-container">
                                <div class="col-12 item-wrap">
                                    <label class="course-label">รหัสวิชา</label> 
                                    <label class="cradit-label">จำนวนหน่วยกิต</label>
                                </div>  
                                <br>
                                <div class="col-12 item-wrap">                                    
                                    <ul id="incomplete-tasks">
                                    </ul>
                                </div>
                            </div>

                            <div class="row item-wrap-container"> 
                                <div class="col-12 item-wrap">                                    
                                    <button type="submit" name="submit" id="submit" class="btn btn-primary subimt-btn" onclick="return confirm('คุณต้องการ เพิ่มข้อมูลใช่หรือไม่?');">
                                        <i class="fa fa-save"></i> บันทึกวิชา 
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

//---------------------- วิชา และหน่วยกิต ที่มหาลัยเปิดสอน -----------------------------

        let ruCourseTasks = document.querySelector('#ru-course-tasks').getElementsByTagName('li');
        let courseNoOnInputText = document.querySelector('#course-no');

        courseNoOnInputText.addEventListener('keyup', (ev) => {
            
            let text = ev.target.value;            
            let pat = new RegExp(text, 'i');
            
            if (text.length > 1) {
                
                document.querySelector('#ru-course-tasks').classList.remove("hidden");
                
                for (let i = 0; i < ruCourseTasks.length; i++) {
                    
                    let item = ruCourseTasks[i];  
                    
                    if (pat.test(item.innerText)) {                        
                        item.classList.remove("hidden");                        
                    } else {                        
                        item.classList.add("hidden");
                    }
                }
            } else {
                for (let i = 0; i < ruCourseTasks.length; i++) {
                    
                    let item = ruCourseTasks[i];
                    item.classList.add("hidden");
                    
                }
            }
        });

        let ruCourseTasksClickForSelected = document.querySelector('#ru-course-tasks');
        let courseNoSelected = document.querySelector('#course-no');
        let craditSelected = document.querySelector('#cr');

        ruCourseTasksClickForSelected.addEventListener('click', event => {
            
            courseNoSelected.value = event.target.querySelector('input#course-no-items-span').value;
            craditSelected.value = event.target.querySelector('input#credit-items-span').value;
            
            for (let i = 0; i < ruCourseTasks.length; i++) {
                
                let item = ruCourseTasks[i];
                item.classList.add("hidden");
                
            }
        }, false);

//---------------------- (จบ) วิชา และหน่วยกิต ที่มหาลัยเปิดสอน -----------------------------



// ------------------------- เพิ่มข้อมูลรายวิชา เข้าไปใน Item lists -------------------

        let courseNo = document.querySelector('#course-no');
        let cradit = document.querySelector('#cr');
        let addItemBtn = document.querySelector('#addItems');
        let incompleteTasksHolder = document.querySelector('#incomplete-tasks');
        let onHiddenMode = document.querySelector('form.hidden-mode');
        let mustInputText = document.querySelector('label#must-input-text');
        let duplicatedInputText = document.querySelector('label#duplicated-input-text');

        function editValueInputText() {
            let listItem = this.parentNode;

            const lists = document.querySelectorAll('li#item-list');

            let submitBtn = document.querySelector('button#submit');
            let isBtnUsing = false;

            if (listItem.querySelector('button').classList.contains('edit')) {

                let cnEditText = listItem.querySelector('input[type=text].cn-add-item-list-mode');
                let crdEditText = listItem.querySelector('input[type=text].crd-add-item-list-mode');
                let editLabelBtn = listItem.querySelector('button.edit');

                let upperText = '';
                cnEditText.onkeypress = () => {
                    upperText = cnEditText.value.toUpperCase();
                    cnEditText.value = upperText;
                };

                editLabelBtn.innerHTML = 'ตกลง';
                editLabelBtn.classList.remove('edit');
                editLabelBtn.classList.toggle('edit-success');

                cnEditText.classList.remove('cn-add-item-list-mode');
                cnEditText.classList.toggle('cn-add-item-list-mode-edit');

                crdEditText.classList.remove('crd-add-item-list-mode');
                crdEditText.classList.toggle('crd-add-item-list-mode-edit');

                submitBtn.classList.remove('subimt-btn');
                submitBtn.classList.add('hin-subimt-btn');
                submitBtn.classList.remove('btn-primary');
                submitBtn.classList.add('btn-light');

            } else {

                let cnEditText = listItem.querySelector('input[type=text]#cn');
                let crdEditText = listItem.querySelector('input[type=text]#crd');
                let labelTextEditcrd = listItem.querySelector('label#labelTextcrd');
                let labelTextEditcn = listItem.querySelector('label#labelTextcn');

                if ((cnEditText.value !== '' && crdEditText.value !== '') && (cnEditText.value.length === 7) && (crdEditText.value.length === 1)) {

                    let editLabelBtn = listItem.querySelector('button.edit-success');

                    labelTextEditcrd.innerHTML = '';
                    labelTextEditcn.innerHTML = '';

                    editLabelBtn.innerHTML = 'แก้ไข';
                    editLabelBtn.classList.remove('edit-success');
                    editLabelBtn.classList.toggle('edit');

                    cnEditText.classList.remove('cn-add-item-list-mode-edit-fail');
                    cnEditText.classList.remove('cn-add-item-list-mode-edit');
                    cnEditText.classList.toggle('cn-add-item-list-mode');

                    crdEditText.classList.remove('crd-add-item-list-mode-edit-fail');
                    crdEditText.classList.remove('crd-add-item-list-mode-edit');
                    crdEditText.classList.toggle('crd-add-item-list-mode');
                } else {

                    if (cnEditText.value === '' || cnEditText.value.length > 7 || cnEditText.value.length < 7) {
                        cnEditText.classList.remove('cn-add-item-list-mode-edit');
                        cnEditText.classList.add('cn-add-item-list-mode-edit-fail');
                        labelTextEditcn.innerHTML = 'รหัสวิชาต้องมี 7 ตัวอักษรเท่านั้น!!!';
                    } else {
                        cnEditText.classList.remove('cn-add-item-list-mode-edit-fail');
                        cnEditText.classList.add('cn-add-item-list-mode-edit');
                        labelTextEditcn.innerHTML = '';
                    }

                    if (crdEditText.value === '' || crdEditText.value.length < 1 || crdEditText.value.length > 1) {
                        crdEditText.classList.remove('crd-add-item-list-mode-edit');
                        crdEditText.classList.add('crd-add-item-list-mode-edit-fail');
                        labelTextEditcrd.innerHTML = 'จำนวนหน่วยกิตต้องมี 1 ตัวอักษรเท่านั้น!!!';
                    } else {
                        crdEditText.classList.remove('crd-add-item-list-mode-edit-fail');
                        crdEditText.classList.add('crd-add-item-list-mode-edit');
                        labelTextEditcrd.innerHTML = '';
                    }

                }

                lists.forEach((li) => {
                    if (li.childNodes[2].className.match('edit-success')) {
                        isBtnUsing = true;
                    }
                });

                if (!isBtnUsing) {
                    submitBtn.classList.remove('hin-subimt-btn');
                    submitBtn.classList.add('subimt-btn');
                    submitBtn.classList.remove('btn-light');
                    submitBtn.classList.add('btn-primary');
                }
            }
        }

        function deleteValueInputText() {
            let listItem = this.parentNode;

            listItem.classList.add('delete-list-animation');

            window.setTimeout(() => {
                listItem.addEventListener('transitionend', () => {

                    const lists = document.querySelectorAll('li#item-list');
                    let submitBtn = document.querySelector('button#submit');

                    listItem.remove();
                    let ulIncomplate = document.querySelector('ul#incomplete-tasks');
                    if (ulIncomplate.childNodes[0].nextSibling === null) {

                        onHiddenMode.classList.remove('on-hidden-mode');
                        onHiddenMode.classList.add('hidden-mode');

                        submitBtn.classList.remove('hin-subimt-btn');
                        submitBtn.classList.add('subimt-btn');
                        submitBtn.classList.remove('btn-light');
                        submitBtn.classList.add('btn-primary');

                    }
                });

            }, 500);


        }

        addItemBtn.onclick = () => {

            let ruCourseTasksHidden = document.querySelector('#ru-course-tasks');
            ruCourseTasksHidden.classList.toggle("hidden");

            mustInputText.innerHTML = '';
            mustInputText.style = '';

            duplicatedInputText.innerHTML = '';
            duplicatedInputText.style = '';

            const lists = document.querySelectorAll('li#item-list');
            let isHasItemList = false;
            lists.forEach((li) => {

                if (li.firstChild.value === courseNo.value && li.children[1].value === cradit.value) {

                    isHasItemList = true;

                    duplicatedInputText.innerHTML = 'รหัสวิชา <b style="color: red; text-shadow: 1px 1px 2px #fff;">' + courseNo.value +'</b> มีอยู่ใน List แล้ว';
                    duplicatedInputText.style = 'padding: 10px;';

                    courseNo.value = '';
                    cradit.value = '';

                }
            });

            if (!isHasItemList) {

                if (courseNo.value !== '' && cradit.value !== '' && courseNo.value.length === 7 && cradit.value.length === 1)
                {

                    if (onHiddenMode) {
                        onHiddenMode.classList.remove('hidden-mode');
                        onHiddenMode.classList.add('on-hidden-mode');
                    }

                    let listItem = document.createElement("li");

                    let cnInput = document.createElement("input");
                    let crdInput = document.createElement("input");

                    let labelTextcn = document.createElement('label');
                    let labelTextcrd = document.createElement('label');

                    let editBtn = document.createElement("button");
                    let deleteBtn = document.createElement("button");

                    cnInput.type = 'text';
                    cnInput.name = 'cn';
                    cnInput.id = 'cn';
                    cnInput.className = 'cn-add-item-list-mode';
                    cnInput.value = courseNo.value;

                    crdInput.type = "text";
                    crdInput.className = 'crd-add-item-list-mode';
                    crdInput.name = 'crd';
                    crdInput.id = 'crd';
                    crdInput.value = cradit.value;

                    labelTextcn.id = 'labelTextcn';
                    labelTextcn.className = 'label-edit-text';

                    labelTextcrd.id = 'labelTextcrd';
                    labelTextcrd.className = 'label-edit-text';

                    editBtn.innerHTML = 'แก้ไข';
                    editBtn.type = 'button';
                    editBtn.className = 'edit btn';

                    deleteBtn.innerHTML = 'ลบ';
                    deleteBtn.type = 'button';
                    deleteBtn.className = 'delete btn';

                    listItem.id = 'item-list';

                    listItem.appendChild(cnInput);
                    listItem.appendChild(crdInput);

                    listItem.appendChild(editBtn);
                    listItem.appendChild(deleteBtn);

                    listItem.appendChild(document.createElement('br'));

                    listItem.appendChild(labelTextcn);
                    listItem.appendChild(labelTextcrd);

                    listItem.appendChild(document.createElement('hr'));

                    incompleteTasksHolder.appendChild(listItem);

                    let editInput = listItem.querySelector('button.edit');
                    let deleteInput = listItem.querySelector('button.delete');

                    editInput.addEventListener('click', editValueInputText);
                    deleteInput.addEventListener('click', deleteValueInputText);

                    courseNo.value = '';
                    cradit.value = '';
                } else {

                    if ((courseNo.value.length !== 7 & cradit.value.length !== 1) || cradit.value.length === 1) {

                        document.querySelector('#course-no').focus();

                    } else {

                        document.querySelector('#cr').focus();

                    }

                    mustInputText.innerHTML = 'รหัสวิชาต้องมี 7 ตัวอักษรเท่านั้น และ จำนวนหน่วยกิตต้องมี 1 ตัวอักษรเท่านั้น !!!';
                    mustInputText.style = 'padding: 10px;';

                }
            }
        };
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

                if (obj.value.length !== 7) {
                    obj.value += String.fromCharCode(charValue).toUpperCase();
                }

                return  false;

            } else {

                return true;

            }
        }

    </script>
    <jsp:include page="footer.jsp" />

