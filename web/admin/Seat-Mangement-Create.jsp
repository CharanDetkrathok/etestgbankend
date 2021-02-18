<%-- 
    Document   : Seat-Mangement-Create
    Created on : Sep 1, 2020, 3:47:29 PM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

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
    .bn-add-item-list-mode, .re-add-item-list-mode, .se-add-item-list-mode {
        border: none;
        pointer-events: none;     
        margin-right: 20px;
        padding: 5px 10px;
        text-align: center;
        text-transform: uppercase;
    }

    .bn-add-item-list-mode-edit, .re-add-item-list-mode-edit, .se-add-item-list-mode-edit { 
        margin-right: 20px; 
        padding: 5px 10px;
        text-align: center;
        text-transform: uppercase;
    }

    .bn-add-item-list-mode-edit-fail, .re-add-item-list-mode-edit-fail, .se-add-item-list-mode-edit-fail { 
        background: rgba(200, 35, 51, 0.1); 
        margin-right: 20px; 
        padding: 5px 10px;
        border: 1px solid;
        text-align: center;
        text-transform: uppercase;
    }

    .add-item-list-mode-edit {
        border: 1px solid;
    }

    .label-edit-text {
        align-items: center;
        justify-content: center;
        display: flex;
        color: rgba(200, 35, 51, 0.8);
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

    .bn-label, .re-label, .se-label {
        border: none;
        pointer-events: none;     
        margin-right: 20px;
        padding: 5px 10px;
        text-align: center;
    }

    .bn-label {
        margin-left: -125px;
    }

    .re-label {
        margin-left: 80px;
    }

    .se-label {
        margin-left: 70px;
    }

    .item-wrap-label {
        align-items: center;
        justify-content: center;
        display: flex;
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
                <div class="card" style="border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5);">          

                    <div class="card-body">
                        <div class="row">
                            <div class="col-12" style="margin-left: 15px;">
                                <label class="fontvwhead">
                                    <i class="fa fa-puzzle-piece"></i> เพิ่มแถวที่นั่งสอบ และเพิ่มจำนวนที่นั่งสอบ/แถว
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

                        <div class="row">
                            <div class="container-fluid" style="padding: 10px 60px;">
                                <div class="col-12">

                                    <div class="row">
                                        <div class="col-6 form-group">
                                            <label for="">ห้องที่ทำการจัดสอบ</label>
                                            <input type="text" style="text-transform:uppercase" class="form-control" name="build_no" id="build_no" placeholder="กรอก ห้องที่ทำการจัดสอบ เช่น SKB801" required="true">
                                        </div>
                                        <div class="col-6 form-group">
                                            <label for="">เพิ่มแถวสอบ</label>
                                            <c:set var="secCount" value="0" scope="page" />
                                            <select class="form-control" name="row_exam" id="row_exam" required="true">                                                     
                                                <c:forEach items="${BuildRow}" var = "BuildRow">
                                                    <c:set var="secCount" value="${secCount = secCount + BuildRow.SEAT_EXAM}" scope="page"/>
                                                    <option disabled="true" style="background-color: #ffc107; color: #000;"> --- แถว ${BuildRow.ROW_EXAM} มี ${BuildRow.SEAT_EXAM} ที่นั่ง --- </option>
                                                </c:forEach>  
                                                <option disabled="true" style="background-color: #fff; color: tomato;"> --- รวม ${secCount} ที่นั่ง --- </option>
                                                <c:forEach items="${rowExam}" var = "rowExam" >
                                                    <option value="${rowExam}" style="background-color: #00c292; color: #fff;">${rowExam}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-6 form-group">
                                            <label for="">กำหนดจำนวนที่นั่งสอบ/แถว <font color="tomato">( เฉพาะตัวเลขเท่านั้น )</font></label>                                                
                                            <input type="number" min="1" class="form-control" name="seat_exam" id="seat_exam" placeholder="กรอก จำนวนที่นั่งสอบ ต่อแถว" onKeyPress="if (this.value.length === 3)
                                                        return false;" required="true">
                                        </div>
                                        <div class="col-6 form-group" style="margin-top: 35px;"> 
                                            <button type="button" name="addItems" id="addItems" class="btn btn-success col-3" style=" border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;">
                                                <i class="fa fa-plus-circle"></i> เพิ่ม Item
                                            </button>                                            
                                            &nbsp;                                            
                                            <a type="button" href="/etestgbackend/SeatManagement" class="btn btn-warning"
                                               style="width: 30%; border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #000;">
                                                <i class="fa fa-backward"></i> &nbsp; กลับ 
                                            </a>
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

                                    <!--FORM เพิ่มข้อมูล-->
                                    <form action="/etestgbackend/SeatManagementInsert" method="POST" class="hidden-mode">

                                        <input type="hidden" name="semester" value="${getCounterData.STUDY_SEMESTER}">
                                        <input type="hidden" name="year" value="${getCounterData.STUDY_YEAR}">

                                        <div class="row item-wrap-container">
                                            <div class="col-12 item-wrap-label">
                                                <label class="bn-label">ห้องสอบ</label> &nbsp;
                                                <label class="re-label">แถวสอบ</label> &nbsp;
                                                <label class="se-label">จำนวนที่นั่งสอบ</label> &nbsp;                                               
                                            </div>  
                                            <br>
                                            <div class="col-12 item-wrap">                                    
                                                <ul id="incomplete-tasks">
                                                </ul>
                                            </div>
                                        </div>

                                        <div class="row item-wrap-container"> 
                                            <div class="col-12 item-wrap">
                                                <button type="submit" name="submit" id="submit" class="btn btn-primary col-3 subimt-btn" 
                                                        onclick="return confirm('คุณต้องการ เพิ่มข้อมูลใช่หรือไม่?');">
                                                    <i class="fa fa-save"></i> บันทึก
                                                </button>
                                            </div>                                           
                                        </div>

                                    </form>
                                </div>
                            </div>
                        </div>
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

    <script>

        let buildNo = document.querySelector('#build_no');
        let rowExam = document.querySelector('#row_exam');
        let seatExam = document.querySelector('#seat_exam');
        let addItemBtn = document.querySelector('#addItems');
        let incompleteTasksHolder = document.querySelector('#incomplete-tasks');
        let onHiddenMode = document.querySelector('form.hidden-mode');
        let duplicatedInputText = document.querySelector('label#duplicated-input-text');

        function editValueInputText() {

            let listItem = this.parentNode;
            const lists = document.querySelectorAll('li#item-list');

            let submitBtn = document.querySelector('button#submit');
            let isBtnUsing = false;

            if (listItem.querySelector('button').classList.contains('edit')) {

                let bnEditText = listItem.querySelector('input[type=text].bn-add-item-list-mode');
                let reEditText = listItem.querySelector('input[type=text].re-add-item-list-mode');
                let seEditText = listItem.querySelector('input[type=text].se-add-item-list-mode');
                let editLabelBtn = listItem.querySelector('button.edit');

                editLabelBtn.innerHTML = 'ตกลง';
                editLabelBtn.classList.remove('edit');
                editLabelBtn.classList.toggle('edit-success');

                bnEditText.classList.remove('bn-add-item-list-mode');
                bnEditText.classList.toggle('bn-add-item-list-mode-edit');

                reEditText.classList.remove('re-add-item-list-mode');
                reEditText.classList.toggle('re-add-item-list-mode-edit');

                seEditText.classList.remove('se-add-item-list-mode');
                seEditText.classList.toggle('se-add-item-list-mode-edit');

                submitBtn.classList.remove('subimt-btn');
                submitBtn.classList.add('hin-subimt-btn');
                submitBtn.classList.remove('btn-primary');
                submitBtn.classList.add('btn-light');

            } else {

                let bnEditText = listItem.querySelector('input[type=text]#buildNoText');
                let reEditText = listItem.querySelector('input[type=text]#rowExamText');
                let seEditText = listItem.querySelector('input[type=text]#seatExamText');

                let labelTextEditbn = listItem.querySelector('label#labelTextbn');
                let labelTextEditre = listItem.querySelector('label#labelTextre');
                let labelTextEditse = listItem.querySelector('label#labelTextse');

                if (bnEditText.value !== '' && reEditText.value !== '' && seEditText.value !== '' && bnEditText.value.length === 6 && reEditText.value.length === 1 && seEditText.value.length >= 1) {

                    let letters = /^[A-Za-z]+$/;

                    if (reEditText.value.match(letters)) {

                        let editLabelBtn = listItem.querySelector('button.edit-success');

                        labelTextEditse.innerHTML = '';
                        labelTextEditbn.innerHTML = '';
                        labelTextEditre.innerHTML = '';

                        editLabelBtn.innerHTML = 'แก้ไข';
                        editLabelBtn.classList.remove('edit-success');
                        editLabelBtn.classList.toggle('edit');

                        bnEditText.classList.remove('bn-add-item-list-mode-edit-fail');
                        bnEditText.classList.remove('bn-add-item-list-mode-edit');
                        bnEditText.classList.toggle('bn-add-item-list-mode');

                        reEditText.classList.remove('re-add-item-list-mode-edit-fail');
                        reEditText.classList.remove('re-add-item-list-mode-edit');
                        reEditText.classList.toggle('re-add-item-list-mode');

                        seEditText.classList.remove('se-add-item-list-mode-edit-fail');
                        seEditText.classList.remove('se-add-item-list-mode-edit');
                        seEditText.classList.toggle('se-add-item-list-mode');

                    } else {

                        reEditText.classList.remove('re-add-item-list-mode-edit');
                        reEditText.classList.add('re-add-item-list-mode-edit-fail');
                        labelTextEditre.innerHTML = 'แถวสอบต้องเป็น \"ตัวอักษร\"  เท่านั้น!!!';
                    }

                } else {

                    if (bnEditText.value === '' || bnEditText.value.length > 6 || bnEditText.value.length < 6) {
                        bnEditText.classList.remove('bn-add-item-list-mode-edit');
                        bnEditText.classList.add('bn-add-item-list-mode-edit-fail');
                        labelTextEditbn.innerHTML = 'ห้องสอบต้องมี 6 ตัวอักษรเท่านั้น!!!';
                    } else {
                        bnEditText.classList.remove('bn-add-item-list-mode-edit-fail');
                        bnEditText.classList.add('bn-add-item-list-mode-edit');
                        labelTextEditbn.innerHTML = '';
                    }

                    if (reEditText.value === '' || reEditText.value.length > 1 || reEditText.value.length < 1) {
                        reEditText.classList.remove('re-add-item-list-mode-edit');
                        reEditText.classList.add('re-add-item-list-mode-edit-fail');
                        labelTextEditre.innerHTML = 'แถวสอบต้องมี 1 ตัวอักษรเท่านั้น!!!';
                    } else {
                        reEditText.classList.remove('re-add-item-list-mode-edit-fail');
                        reEditText.classList.add('re-add-item-list-mode-edit');
                        labelTextEditre.innerHTML = '';
                    }

                    if (seEditText.value === '' || seEditText.value.length < 1) {
                        seEditText.classList.remove('se-add-item-list-mode-edit');
                        seEditText.classList.add('se-add-item-list-mode-edit-fail');
                        labelTextEditse.innerHTML = 'จำนวนที่นั่งสอบต้องมีมากกว่า 1 ที่นั่งเท่านั้น!!!';
                    } else {
                        seEditText.classList.remove('se-add-item-list-mode-edit-fail');
                        seEditText.classList.add('se-add-item-list-mode-edit');
                        labelTextEditse.innerHTML = '';
                    }
                }

                lists.forEach((li) => {
                    if (li.childNodes[3].className.match('edit-success')) {
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

                        duplicatedInputText.innerHTML = '';
                        duplicatedInputText.style = '';

                        submitBtn.classList.remove('hin-subimt-btn');
                        submitBtn.classList.add('subimt-btn');
                        submitBtn.classList.remove('btn-light');
                        submitBtn.classList.add('btn-primary');
                    }
                });
            }, 500);
        }

        addItemBtn.onclick = () => {

            duplicatedInputText.innerHTML = '';
            duplicatedInputText.style = '';

            const lists = document.querySelectorAll('li#item-list');
            let isHasItemList = false;

            lists.forEach((li) => {

                if (li.childNodes[1].value === rowExam.value) {

                    isHasItemList = true;
                    duplicatedInputText.innerHTML = 'แถวสอบ <b style="color: red; text-shadow: 1px 1px 2px #fff;">' + rowExam.value + '</b> มีอยู่ใน List แล้ว';
                    duplicatedInputText.style = 'padding: 10px;';
                }
            });

            if (!isHasItemList) {
                if (buildNo.value !== '' && rowExam.value !== '' && seatExam.value !== '') {

                    if (onHiddenMode) {
                        onHiddenMode.classList.remove('hidden-mode');
                        onHiddenMode.classList.add('on-hidden-mode');
                    }

                    let listItem = document.createElement("li");

                    let buildNoTag = document.createElement("input");
                    let rowExamTag = document.createElement("input");
                    let seatExamTag = document.createElement("input");

                    let labelTextbn = document.createElement('label');
                    let labelTextre = document.createElement('label');
                    let labelTextse = document.createElement('label');

                    let editBtn = document.createElement("button");
                    let deleteBtn = document.createElement("button");

                    buildNoTag.type = 'text';
                    buildNoTag.name = 'buildNoText';
                    buildNoTag.id = 'buildNoText';
                    buildNoTag.className = 'bn-add-item-list-mode';
                    buildNoTag.value = buildNo.value;

                    rowExamTag.type = "text";
                    rowExamTag.className = 're-add-item-list-mode';
                    rowExamTag.name = 'rowExamText';
                    rowExamTag.id = 'rowExamText';
                    rowExamTag.value = rowExam.value;

                    seatExamTag.type = "text";
                    seatExamTag.className = 'se-add-item-list-mode';
                    seatExamTag.name = 'seatExamText';
                    seatExamTag.id = 'seatExamText';
                    seatExamTag.value = seatExam.value;

                    labelTextbn.id = 'labelTextbn';
                    labelTextbn.className = 'label-edit-text';

                    labelTextre.id = 'labelTextre';
                    labelTextre.className = 'label-edit-text';

                    labelTextse.id = 'labelTextse';
                    labelTextse.className = 'label-edit-text';

                    editBtn.innerHTML = 'แก้ไข';
                    editBtn.type = 'button';
                    editBtn.className = 'edit btn';

                    deleteBtn.innerHTML = 'ลบ';
                    deleteBtn.type = 'button';
                    deleteBtn.className = 'delete btn';

                    listItem.id = 'item-list';

                    listItem.appendChild(buildNoTag);
                    listItem.appendChild(rowExamTag);
                    listItem.appendChild(seatExamTag);

                    listItem.appendChild(editBtn);
                    listItem.appendChild(deleteBtn);

                    listItem.appendChild(document.createElement('br'));

                    listItem.appendChild(labelTextbn);
                    listItem.appendChild(labelTextre);
                    listItem.appendChild(labelTextse);

                    listItem.appendChild(document.createElement('hr'));

                    incompleteTasksHolder.appendChild(listItem);

                    let editInput = listItem.querySelector('button.edit');
                    let deleteInput = listItem.querySelector('button.delete');

                    editInput.addEventListener('click', editValueInputText);
                    deleteInput.addEventListener('click', deleteValueInputText);
                } else {

                    if (buildNo.value === '' && (seatExam.value === '' || seatExam.value !== '')) {
                        buildNo.focus();
                    } else {
                        seatExam.focus();
                    }

                }
            }

        };
    </script>

    <!-- Footer -->
    <jsp:include page="footer.jsp" />