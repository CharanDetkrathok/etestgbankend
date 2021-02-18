<%-- 
    Document   : Date-Mangement-Create
    Created on : Sep 9, 2020, 10:35:33 AM
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="header.jsp" />
<style>
    .card-body {
        height: 100%; 
        /*background: linear-gradient(90deg, rgba(253,187,45,0.5) 0%, rgba(13,145,147,0.5) 100%);*/
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
    .de-add-item-list-mode, .re-add-item-list-mode, .se-add-item-list-mode {
        border: none;
        pointer-events: none;     
        margin-right: 20px;
        padding: 5px 10px;
        text-align: center;
        text-transform: uppercase;
    }

    .delete {
        border-radius: 0;
        background: rgba(200, 35, 51, 0.8);
        color: #fff;
    }
    
    .delete:hover {
        background: rgba(200, 35, 51, 1);
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

    .de-label, .re-label, .se-label {
        border: none;
        pointer-events: none;     
        margin-right: 20px;
        padding: 5px 10px;
        text-align: center;
    }

    .de-label {
        margin-left: -53px;
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
                                    <i class="fa fa-puzzle-piece"></i> เพิ่มวันสอบ
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
                                            <b> ${getCounterData.STUDY_SEMESTER}/${getCounterData.STUDY_YEAR}</b>&nbsp;&nbsp;&nbsp;<font color="#0027FF"><b>จำนวนที่นั่ง / คาบสอบ ( คาบละไม่เกิน ${sumSeat} ที่นั่ง)</b></font>
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
                                            <select class="form-control" name="semester" hidden="true">
                                                <c:choose>
                                                    <c:when test = "${getCounterData.STUDY_SEMESTER == '1'}">
                                                        <option selected="true" value="1">เทอม 1</option>
                                                    </c:when>
                                                    <c:when test = "${getCounterData.STUDY_SEMESTER == '2'}">
                                                        <option selected="true" value="2">เทอม 2</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option selected="true" value="3">ภาคฤดูร้อน</option>
                                                    </c:otherwise>
                                                </c:choose>         
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-6 form-group">
                                            <label for="">เลือกวันเปิดสอบ</label>
                                            <input type="date" class="form-control" name="date_exam" id="date_exam" required="true">
                                        </div>
                                        <div class="col-6 form-group">
                                            <label for="">คาบสอบ</label>
                                            <input type="text" class="form-control" name="row_exam" id="row_exam" value="1-4" placeholder="คาบที่ 1-4" disabled="true">
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-6 form-group">
                                            <label for="">จำนวนที่นั่งสอบ / คาบสอบ</label>
                                            <input type="number" class="form-control" name="seat_exam" id="seat_exam" min="1" value="${BuildRow.SEAT_EXAM}">
                                        </div>
                                        <div class="col-6 form-group" style="margin-top: 35px;">        
                                            <button type="button" name="addItems" id="addItems" class="btn btn-success col-3" style=" border-radius: 0; box-shadow: 0 0 5px 0 rgba(0, 0, 0, 0.5); color: #fff;">
                                                <i class="fa fa-plus-circle"></i> เพิ่ม Item
                                            </button>                                            
                                            &nbsp;
                                            &nbsp;
                                            <a type="button" href="/etestgbackend/DateManagement" class="btn btn-warning"
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
                                    <form action="/etestgbackend/DateManagementInsert" method="POST" class="hidden-mode">

                                        <input type="hidden" name="semester" value="${getCounterData.STUDY_SEMESTER}">
                                        <input type="hidden" name="year" value="${getCounterData.STUDY_YEAR}">

                                        <div class="row item-wrap-container">
                                            <div class="col-12 item-wrap-label">
                                                <label class="de-label">วันเปิดสอบ</label> &nbsp;
                                                <label class="re-label">คาบสอบ</label> &nbsp;
                                                <label class="se-label">จำนวนที่นั่งสอบ / คาบสอบ</label> &nbsp;                                               
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

        let dateExam = document.querySelector('#date_exam');
        let rowExam = document.querySelector('#row_exam');
        let seatExam = document.querySelector('#seat_exam');
        let addItemBtn = document.querySelector('#addItems');
        let incompleteTasksHolder = document.querySelector('#incomplete-tasks');
        let onHiddenMode = document.querySelector('form.hidden-mode');
        let duplicatedInputText = document.querySelector('label#duplicated-input-text');

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

                if (li.childNodes[0].value === dateExam.value) {

                    isHasItemList = true;
                    duplicatedInputText.innerHTML = 'วันสอบนี้ <b style="color: red; text-shadow: 1px 1px 2px #fff;">' + dateExam.value + '</b> มีอยู่ใน List แล้ว';
                    duplicatedInputText.style = 'padding: 10px;';
                }
            });

            if (!isHasItemList) {
                if (dateExam.value !== '' && rowExam.value !== '' && seatExam.value !== '') {

                    if (onHiddenMode) {
                        onHiddenMode.classList.remove('hidden-mode');
                        onHiddenMode.classList.add('on-hidden-mode');
                    }

                    let listItem = document.createElement("li");

                    let dateExamTag = document.createElement("input");
                    let rowExamTag = document.createElement("input");
                    let seatExamTag = document.createElement("input");

                    let labelTextbn = document.createElement('label');
                    let labelTextre = document.createElement('label');
                    let labelTextse = document.createElement('label');

                    let deleteBtn = document.createElement("button");

                    dateExamTag.type = 'text';
                    dateExamTag.name = 'dateExamText';
                    dateExamTag.id = 'dateExamText';
                    dateExamTag.className = 'de-add-item-list-mode';
                    dateExamTag.value = dateExam.value;

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

                    deleteBtn.innerHTML = 'ลบ';
                    deleteBtn.type = 'button';
                    deleteBtn.className = 'delete btn';

                    listItem.id = 'item-list';

                    listItem.appendChild(dateExamTag);
                    listItem.appendChild(rowExamTag);
                    listItem.appendChild(seatExamTag);

                    listItem.appendChild(deleteBtn);

                    listItem.appendChild(document.createElement('br'));

                    listItem.appendChild(labelTextbn);
                    listItem.appendChild(labelTextre);
                    listItem.appendChild(labelTextse);

                    listItem.appendChild(document.createElement('hr'));

                    incompleteTasksHolder.appendChild(listItem);

                    let deleteInput = listItem.querySelector('button.delete');

                    deleteInput.addEventListener('click', deleteValueInputText);
                } else {

                    if (dateExam.value === '' && (seatExam.value === '' || seatExam.value !== '')) {
                        dateExam.focus();
                    } else {
                        seatExam.focus();
                    }

                }
            }

        };
    </script>
    
    <!-- Footer -->
    <jsp:include page="footer.jsp" />