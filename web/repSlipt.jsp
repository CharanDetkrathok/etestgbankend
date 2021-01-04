<%@page import="java.util.Base64"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>E-Testing slip ${getHeaderRepSlip.STD_CODE}${getHeaderRepSlip.DATE_GENERATED}</title>
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

        <link
            href="https://fonts.googleapis.com/css2?family=Sarabun:wght@200;700&display=swap"
            rel="stylesheet"
            />

        <style>
            * {
                box-sizing: border-box;
                font-family: "Sarabun", sans-serif;
                color: black;
                -webkit-print-color-adjust: exact !important; /* Chrome, Safari */
                color-adjust: exact !important; /*Firefox*/
                margin: 0 auto;
            }

            .bill-border {
                width: 1000px;
                height: 1000px;
                margin: 0 auto;
                border: 5px solid #46a8c9;
                background-image: url("images/icons/Emblem_of_Ramkhamhaeng_University2.png");
                background-repeat: no-repeat;
                background-position: 50% 100%;
                background-size: 500px;
            }

            /* หัวใบเสร็จ */
            .row-container-wrap-head {
                width: 1000px;
                margin: -10px auto 5px;
            }

            /* หัวใบเสร็จด้วนซ้าย */
            .bill-head-left {
                margin: 2px 0 0 8px;
                font-size: 16px;
            }

            .bill-head-left-text {
                margin-top: 6px;
                margin-left: 8px;
                font-size: 16px;
            }
            /* ------------------------------------------------------------- */

            /* หัวใบเสร็จตรงกลาง */

            /*ตราครุฑ*/
            .img-bill-header-center {
                width: 70px;
                margin: 15px 0 0 0;
            }

            /*ตราพ่อขุนฯ*/
            .img-bill-header-left {
                width: 50px;
                margin: -45px 0 -15px -265px;
            }

            .bill-head-1-center {
                margin: -20px 15px 0 15px;
                font-size: 20px;
                font-weight: bold;
            }

            .bill-head-2-center {
                font-size: 18px;
                margin: 5px -160px 0 -160px;
                font-weight: bold;
            }

            .bill-head-3-center {
                font-size: 16px;
                margin-top: 5px;
                font-weight: bold;
            }
            /* ------------------------------------------------------------- */

            /* หัวใบเสร็จด้วนขวา */
            .bill-head-right {
                margin: 10px 20px 0 0;
                font-size: 16px;
            }

            .bill-head-right-text {
                margin: 5px 20px;
                font-size: 16px;
            }
            /* ------------------------------------------------------------- */

            /* ข้อมูลในใบเสร็จ  ส่วนของตารางข้อมูลด้านขวา ขอจบภาคในภาคนี้ รวม(บาท)*/
            .row-container-wrap-data-1 {
                font-size: 16px;
                width: 300px;   
                margin-top: 10px;     
                margin-left: 8px;
                display: inline-block;
                border-collapse: collapse;
            }

            .row-container-wrap-data-1-1 {
                font-size: 16px;
                width: 100px;
                margin: 0 0 0 -5px;
                display: inline-block;
                border-collapse: collapse;
            }

            .table-thead-1-data-1 {
                width: 300px;
                font-weight: 600;
                margin: 0 0 0 0;
                border: 1px solid black;
                background-color: #d7e9ef;
            }
            .square-text {
                display: inline-block;
                margin: -100px 0 0 0;
            }

            .square-text-1 {
                display: inline-block;     
                margin: 0 0 0 0;   
                padding: 9px 0 10px 0;
            }

            .bg-color {
                padding: 4px 4px 4px 4px;
            }

            .bg-color-border {
                color: black;
                text-align: center;
                font-weight: 600;
                font-family: "Times New Roman", Times, serif;
                padding: 6px 2px 6px 2px;
                border: 1px solid black;
            }

            .bg-color-text {
                font-weight: 600;
                color: black;
                padding: 4px 4px 4px 4px;
            }

            .table-thead-1-data-2 {
                width: 100px;
                padding: 11px 0 10px 0;
                font-weight: 600;
                border: 1px solid black;
                background-color: #d7e9ef;
            }
            /* ------------------------------------------------------------- */

            /* ส่วนของตารางข้อมูลด้านซ้าย กระบวนวิชา หน่วยกิต วันที่สอบ คาบ ห้องสอบ */
            .row-container-wrap-data-2 {
                font-size: 16px;
                width: 560px;
                margin: 0 0 0 13px;
                display: inline-block;
                border-collapse: collapse;
            }

            .table-thead-2-data-1 {
                padding: 11px 0 10px 0;
                height: 10px;
                font-weight: 600;
                border: 1px solid black;
                background-color: #d7e9ef;
            }

            .bg-color-border-data {
                color: black;
                text-align: center;
                font-weight: 600;
                font-family: "Times New Roman", Times, serif;
                padding: 6px 2px 6px 2px;
                font-style: normal;
                border: 1px solid black;
            }
            /* ------------------------------------------------------------- */

            .square-bottom {
                font-size: 16px;
                font-weight: bold;
                text-align: center;
                width: 120px;
                height: 30px;
                padding-top: 2px;
                margin: 0 10px;
                border: 1px solid black;
                display: inline-block;
            }
            /* background-position: center 450px; */



            @media print,screen {
                .Header {
                    display: none;
                }
            }
            @media print {
                #printPageButton {
                    display: none;
                }
            }
            @page 
            {
                size:  auto;    
                margin: 200mm;
            }
        </style>
    </head>

    <body>
        <div class="bill-border">
            <!-- หัวใบเสร็จ -->
            <table class="row-container-wrap-head">
                <tr>
                    <td align="left" style="width: 333px;">
                    </td>

                    <td align="center" style="width: 333px;">
                        <img
                            class="img-bill-header-center"
                            src="images/icons/Thai_government_Garuda_emblem.png"
                            alt="ตราครุฑ"
                            /><br />
                        <img
                            class="img-bill-header-left"
                            src="images/icons/Emblem_of_Ramkhamhaeng_University1.png"
                            alt="ตราพ่อขุนรามคหแหงมหาราช"
                            />
                        <p class="bill-head-1-center">มหาวิทยาลัยรามคำแหง</p>
                        <p class="bill-head-2-center">ใบเสร็จรับเงิน การลงทะเบียนสอบทางอิเล็กทรอนิกส์</p>
                        <p class="bill-head-3-center">เลขประจำตัวผู้เสียภาษี 099 4000 193483</p>
                    </td>

                    <td align="right" style="width: 333px;">            
                    </td>
                </tr>
            </table>

            <!--------------------- หัวใบเสร็จ ซ้าย และขวา ------------------------->
            <!-- หัวใบเสร็จ ซ้าย และขวา-->
            <table class="row-container-wrap-head">
                <tr>
                    <td align="left" style="width: 650px">
                        <p class="bill-head-left">
                            <b>ภาค/ปีการศึกษา</b>
                            <span style="font-size: 22px" id="semester" >&nbsp;&nbsp; 
                                <c:choose>
                                    <c:when test = "${sem eq '3'}">
                                        ฤดูร้อน/${year}
                                    </c:when>
                                    <c:otherwise>
                                        ${sem}/${year}
                                    </c:otherwise>
                                </c:choose> </span>
                        </p>
                        <p class="bill-head-left-text">
                            <b>วันที่</b>
                            <span style="font-size: 22px" id="register-date">
                                &nbsp;&nbsp;${getHeaderRepSlip.INSERT_DATE} (1)</span
                            >
                        </p>
                        <p class="bill-head-left-text">
                            <b>ได้รับเงินจาก</b>
                            <span style="font-size: 22px" id="receive-money-from"
                                  >&nbsp; ${getProfile.NAME_THAI}</span
                            >
                        </p>
                        <p class="bill-head-left-text" style="font-size: 22px">
                            เวลา :
                            <span id="register-time">${getHeaderRepSlip.INSERT_TIME}</span>
                        </p>
                    </td>
                    <!-- หัวใบเสร็จขาว      -->
                    <td align="right" style="width: 350px">
                        <p class="bill-head-right">
                            <b style="margin: 5px 79px 0 0;">เครื่องที่/ปีงบประมาณ</b>
                            <span style="margin: 0 0 0 0; font-size: 22px" id="register-machine-fiscal-year" > 567/${subYear}</span>
                        </p>
                        <p class="bill-head-right-text">
                            <b>เลขที่</b>
                            <span style="margin: 2px 0 0 75px; font-size: 22px" id="register-number">
                                ${sliprunno}</span
                            >
                        </p>
                        <p class="bill-head-right-text">
                            <b>รหัสประจำตัวนักศึกษา</b>
                            <span style="margin: 2px 0 0 25px; font-size: 22px" id="student-id">
                                ${getHeaderRepSlip.STD_CODE}</span >
                        </p>
                        <p class="bill-head-right-text">
                            <b>คณะ/สาขา</b>
                            <span style="margin: 2px 0 0 83px; font-size: 22px" id="student-id"> [${getProfile.MAJOR_NO}${getProfile.FACULTY_NO}]</span>
                        </p>
                    </td>
                </tr>
            </table>

            <!-- ------------------------------- จบส่วนหัวใบเสร็จ --------------------------- -->

            <!-- ข้อมูลใบเสร็จ ด้านซ้าย -->
            <table class="row-container-wrap-data-1">
                <thead>
                    <tr>
                        <th class="table-thead-1-data-1">
                            <p class="square-text-1">รายการ</p>
                        </th>
                    </tr>
                </thead>
                <tbody style="border: 1px solid black;">
                    <tr style="height: 403px;">
                        <td class="bg-color">
                            <p style="margin-top: -155px; font-size: 15px;">
                                &nbsp;&nbsp;ค่าธรรมเนียมลงทะเบียนสอบทางอิเล็กทรอนิกส์&nbsp;
                            </p>
                            <p style="font-weight: 600; color: black; margin: 130px 0 0 0;">
                                &nbsp;&nbsp;&nbsp;คาบ&nbsp;1 = 09:00-11:30
                            </p>
                            <p style="font-weight: 600; color: black; margin: 0 0 0 0;">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2 = 12:00-14:30
                            </p>
                            <p style="font-weight: 600; color: black; margin: 0 0 0 0;">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3 = 15:00-17:30
                            </p>
                            <p style="font-weight: 600; color: black; margin: 0 0 0 0;">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4 = 18:00-20:30
                            </p>
                        </td>
                    </tr>
                </tbody>

                <td style="float: right; font-weight: bold; margin: 2px 5px 6px 0;">
                    รวมเงิน
                </td>
            </table>
            <!-- ---------------------------------------------------------- -->

            <!-- ตารางที่สอง ตรงกลางรวมเงิน -->
            <table class="row-container-wrap-data-1-1">
                <thead>
                    <tr>
                        <th class="table-thead-1-data-2">
                            <p>รวม (บาท)</p>
                        </th>
                    </tr>
                </thead>
                <tbody style="border: 1px solid black; font-family: 'Times New Roman', Times, serif;">
                    <tr>
                        <td class="bg-color-border" id="total-price">${tmpTotal}</td>    
                    </tr>
                    <tr>
                        <td class="bg-color-border" id="total-price">&nbsp;</td> 
                    </tr>
                    <tr>
                        <td class="bg-color-border" id="total-price">&nbsp;</td> 
                    </tr>
                    <tr>
                        <td class="bg-color-border" id="total-price">&nbsp;</td> 
                    </tr>
                    <tr>
                        <td class="bg-color-border" id="total-price">&nbsp;</td> 
                    </tr>
                    <tr>
                        <td class="bg-color-border" id="total-price">&nbsp;</td> 
                    </tr>
                    <tr>
                        <td class="bg-color-border" id="total-price">&nbsp;</td> 
                    </tr>
                    <tr>
                        <td class="bg-color-border" id="total-price">&nbsp;</td> 
                    </tr>
                    <tr>
                        <td class="bg-color-border" id="total-price">&nbsp;</td> 
                    </tr>
                    <tr>
                        <td class="bg-color-border" id="total-price">&nbsp;</td> 
                    </tr>
                    <tr>
                        <td class="bg-color-border" id="total-price">&nbsp;</td> 
                    </tr>
                    <tr>
                        <td class="bg-color-border" id="total-price">&nbsp;</td> 
                    </tr>
                    <tr>
                        <td class="bg-color-border" id="total-price">&nbsp;</td> 
                    </tr>

                </tbody>

                <!-- ไว้ด้านนอก <tbody> เพื่อแสดงการ "รับเงิน" -->
                <tr>
                    <td class="bg-color-border" id="total-price">${tmpTotal}</td>
                </tr>
            </table>
            <!-- ---------------------------------------------------------- -->

            <!-- ตารางที่สาม ทางขวารายละเอียดลงทะเบียน -->
            <table class="row-container-wrap-data-2">
                <thead>
                    <tr>
                        <th class="table-thead-2-data-1" style="width: 125px;">
                            <p class="square-text">กระบวนวิชา</p>
                        </th>
                        <th class="table-thead-2-data-1" style="width: 125px;">หน่วยกิต</th>
                        <th class="table-thead-2-data-1" style="width: 125px;">
                            วันที่สอบ
                        </th>
                        <th class="table-thead-2-data-1" style="width: 55px;">คาบ</th>
                        <th class="table-thead-2-data-1" style="width: 125px;">ห้องสอบ</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${getRepSlip}"   begin="0" varStatus="loop" end="${noColAmount}" var="getRepSlip">
                        <tr>
                            <td class="bg-color-border-data" id="subject">${getRepSlip.COURSE_NO}</td>
                            <td class="bg-color-border-data" id="credit">${getRepSlip.CREDIT}</td>
                            <td class="bg-color-border-data" id="exam-date">${getRepSlip.EXAM_DATE}</td>
                            <td class="bg-color-border-data" id="period">${getRepSlip.SECTION_NO}</td>
                            <td class="bg-color-border-data" id="exam-room">
                                <c:choose>
                                    <c:when test = "${getRepSlip.YEAR ne null}">
                                        SKB801
                                    </c:when>
                                    <c:otherwise>
                                        &nbsp;
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr> 
                    </c:forEach>
                    <c:forEach  begin="1" end="${chkColAmount}" varStatus="loop">
                        <tr>
                            <td class="bg-color-border-data" id="subject">&nbsp; </td>
                            <td class="bg-color-border-data" id="credit">&nbsp; </td>
                            <td class="bg-color-border-data" id="exam-date">&nbsp; </td>
                            <td class="bg-color-border-data" id="period">&nbsp; </td>
                            <td class="bg-color-border-data" id="exam-room">&nbsp; </td>
                        </tr> 
                    </c:forEach>  
                </tbody>

                <!-- ไว้ด้านนอก <tbody> เพื่อจัดการ border ให้เป็นกล่องเดียว -->
                <tr style="border: 1px solid black;">
                    <td></td>
                    <td></td>
                    <td style="padding: 4px 2px 4px 2px; text-align: center;" id="total-subject">จำนวน &nbsp; 
                        <b style="font-family: 'Times New Roman', Times, serif; font-size: 14px;">${cntCourse}</b> &nbsp; วิชา</td>
                    <td></td>
                    <td style="padding: 4px 2px 4px 2px; text-align: center;"><b style="font-family: 'Times New Roman', Times, serif; font-size: 14px;">${sumCredit}</b> &nbsp; หน่วยกิต</td>
                </tr>
                <!-- เพิ่ม <tr> นี้เพื่อให้จำนวน row เท่ากันกับตาราง "รับเงิน" ถ้าไม่เท่ากันหัวตารางนี้จะตกลงมา ทำให้หัวตารางจะไม่เท่ากัน -->
                <tr style="border: 1px solid black;">
                    <td style="padding: 5px 2px 5px 2px;">&nbsp;</td>
                    <td style="padding: 5px 2px 5px 2px;"></td>
                    <td style="padding: 5px 2px 5px 2px;"></td>
                    <td style="padding: 5px 2px 5px 2px;"></td>
                    <td style="padding: 5px 2px 5px 2px;"></td>
                </tr>
            </table>
            <!-- ------------------------------- จบส่วนข้อมูลใบเสร็จ --------------------------- -->
            <br />

            <div class="square-bottom">แบบ มร. 18/E</div>
            <pre>
        <span style="float: left; color: black; margin: -60px 0 0 435px; font-weight: bold;">(${strTotalThai})</span>
            </pre>
            <p style="margin: -25px 0 0 80px; font-size: 12px;">ต้องแสดงเอกสารฉบับนี้เมื่อเข้าสอบ หรือเมื่อต้องการติดต่อกับมหาวิทยาลัยทุกครั้ง</p>
            <p style="margin: 5px 0 0 50px; font-size: 12px;">หากมีการปลอมแปลงใบเสร็จรับเงิน จะดำเนินการตามข้อบังคับมหาวิทยาลัยว่าด้วยวินัยนักศึกษา</p>
            <pre style="margin: -50px 120px 0 0; float: right; font-size: 12px;">
            <font style="margin: -13px -108px; float: right; font-size: 16px;">${chkDigit}</font>
            <img
                src="images/icons/signature.png"
                alt="ลายเซ็นการเงิน"
                style="width: 35px; float: right; margin-right: 100px; margin-top: -21px;"
                />
        ..................................................................................................
                              (นางสาวทิวาภรณ์ สิงห์ประทาน)
                            นักวิชาการเงินและบัญชีปฏิบัติการ

        <span style="font-size: 16px;"> &nbsp;&nbsp;ผู้รับเงิน (ผู้รับมอบอำนาจจากอธิการบดี)</span>
            </pre>
            <%

                String stdcode = request.getParameter("STD_CODE");
                if (stdcode == null) {
                    stdcode = "6207500064";
                }
                String sem = request.getParameter("sem");
                if (sem == null) {
                    sem = "1";
                }
                String year = request.getParameter("refkey");;
                if (year == null) {
                    year = "2563";
                }
                String refkey = request.getParameter("refkey");
                if (refkey == null) {
                    refkey = "DRKEE201003235900615";
                }

                String codecheck = request.getParameter("codecheck");
                if (codecheck == null) {
                    codecheck = "0000";
                }
                String encodedString = Base64.getEncoder().encodeToString(stdcode.getBytes());
                // out.print("encode" + encodedString);

                byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
                String decodedString = new String(decodedBytes);

                //  out.print("decode" + decodedString);
%> 
            <img
                src="https://chart.googleapis.com/chart?cht=qr&chl=http://sevkn.ru.ac.th:8888/etestgbackend/VerifySlipt.jsp?stdcode=${getHeaderRepSlip.STD_CODE}&sem=${sem}&year=${year}&refkey=${refkey}&chs=180x180&choe=UTF-8&chld=L|2"
                alt="qr code"
                style="width: 100px; margin: -205px 0 20px 870px;"
                />
            <br /><br />
            <p style="margin: -45px 0 10px 10px; font-size: 12px;">
                กองคลัง สำนักงานอธิการบดี
            </p>
        </div>
        <br>
        <div style="width: 1000px; margin: 0 auto;">
            <pre style="margin-left: -45px;">
            วิธีชำระเงิน Payment Method &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; :   QR Code (นักศึกษาชำระ)
            วันชำระ Payment Date &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:   ${getHeaderRepSlip.INSERT_DATE_TIME}
            วันที่พิมพ์ Printed Date &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:   ${getHeaderRepSlip.DATE_GENERATED}
            หมายเหตุ Remark &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:   1. เอกสารฉบับนี้ออกด้วยระบบคอมพิวเตอร์จะต้องไม่มีรอยแก้ไข ขูด ขีดฆ่ายกเว้น เจ้าหน้าที่ผู้รับเงินได้ลงลายมือชื่อ
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;กำกับแก้ไขไว้ทุกแห่ง
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2. ใบเสร็จรับเงินฉบับนี้ผู้รับเงินลงลายมือชื่อด้วยลายเซ็นอิเล็กทรอนิกส์ ซึ่งได้รับอนุมัติจากกรมบัญชีกลางตามหนังสือ
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;กค 0422.3/ว.130 ลงวันที่ 19 พ.ย. 2556
                                  
            </pre
            >
        </div>
        <div  id="printPageButton" style="text-align:center; padding-bottom: 50px;">   
            <button type="button" class="btn" onClick="window.print();">&nbsp;<i class="fa fa-print"></i>&nbsp;&nbsp;&nbsp;พิมพ์ใบเสร็จรับเงิน&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</button>
        </div>
    </body>
</html>
