<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.ResultSetMetaData"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.util.*" %> 
<%@page import="java.sql.*, javax.sql.*, javax.naming.*"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Success</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    </head>
    <body>
        <%
            String stdcode = request.getParameter("stdcode");
            if (stdcode == null) {
                stdcode = "";
            }
            String sem = request.getParameter("sem");
            if (sem == null) {
                sem = "1";
            }
            String year = request.getParameter("year");
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

           /* String encodedString = Base64.getEncoder().encodeToString(stdcode.getBytes());
            out.print("encode" + encodedString);

            byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
            String decodedString = new String(decodedBytes);

            out.print("decode" + decodedString);*/


        %>   
        <%            Connection connect = null;
            //Statement sConnCount = null;
            Statement sConn = null;
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connect = DriverManager.getConnection("jdbc:oracle:thin:@10.2.1.98:1521:RUBRAM", "TR100 ", "txt6r9r3");

            try {

                sConn = connect.createStatement();
                String tmpref = "";
                String sql = "SELECT COUNT(STD_CODE)REF_KEY FROM ET_REceipt "
                        + " where STD_CODE = '" + stdcode + "' and RECEIPT_SEMESTER = '" + sem + "'  and RECEIPT_YEAR = '" + year + "'  ";
                ResultSet rec2 = sConn.executeQuery(sql);
                //out.print(sql);
                while ((rec2 != null) && (rec2.next())) {

                    //   out.print(rec2.getString("QRID"));
                    tmpref = rec2.getString("REF_KEY");

                } //out.print(tmpref);

                if (tmpref.equals("1")) {
                    out.print("<center>");
                    out.print("<img src='images/0.png'>");
                    out.print("<p> ใบเสร็จถูกต้อง ท่านสามารถนำใบเสร็จนี้ไปเป็นหลักฐานได้");

                    out.print("</center>");
                } else {
                    out.print("<center>");
                    out.print("<img src='images/close.jpg' width='200px'>");
                    out.print("<p> ใบเสร็จไม่ถูกต้อง ท่านไม่สามารถนำใบเสร็จนี้ไปเป็นหลักฐานได้");

                    out.print("</center>");
                }

                sConn.close();
                connect.close();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                if (sConn != null) {
                    sConn.close();
                    connect.close();
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                out.println(e.getMessage());
                e.printStackTrace();
            }
        %> 
    </body>
</html>
