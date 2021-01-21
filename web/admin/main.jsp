<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<jsp:include page="header.jsp" />

<style>
    .card {
        height: 30vw; 
        background: linear-gradient(90deg, rgba(253,187,45,1) 0%, rgba(13,145,147,1) 100%);
    }

    .card-body {
        display: flex;
        justify-content: center;
        align-items: center;
    }

    p {
        font-family: 'Sarabun', sans-serif;
        color: #fff;
        font-size: 2em;
        text-shadow: 2px 2px 2px #000;
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

                <div class="card">

                    <div class="card-body">
                        <img class="img-logo" src="/etestgbackend/admin/images/logo2.png">&nbsp;&nbsp;&nbsp;
                        <p>ยินดีต้อนรับเข้าสู่ระบบ จัดเตรียมการสอบ E-Testing</p>
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
    <jsp:include page="footer.jsp" />