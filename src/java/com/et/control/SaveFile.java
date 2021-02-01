/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.control;

import com.et.model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import org.apache.commons.io.FilenameUtils;

@WebServlet("/upload")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
@SuppressWarnings("unused")
public class SaveFile extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String relativeWebPath = "C:\\java\\";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int batchSize = 40;
        // stmt
        InputStream inputStream = null;
        // String csvFilePath = "Reviews-simple.csv";
        Part file_coursefilePart = request.getPart("file_course");
        Part file_seatPart = request.getPart("file_seat");
        Part file_rowseatPart = request.getPart("file_rowseat");
        Part file_buildPart = request.getPart("file_build");
        // String fileName =
        // Paths.get(file_seat.getSubmittedFileName()).getFileName().toString(); // MSIE
        // fix.
        // InputStream fileContent = file_seat.getInputStream();
        // ArrayList<String> AllFile = new ArrayList<String>();

        Database db = new Database();
        if ((file_coursefilePart != null && file_seatPart != null && file_rowseatPart != null
                && file_buildPart != null)) {

            ET_EXAM_SEAT_TABLE getExamSeatTable = new ET_EXAM_SEAT_TABLE(db);
            ET_COURSE_OPEN_TABLE getCourseOPTable = new ET_COURSE_OPEN_TABLE(db);
            ET_EXAM_DATE_TABLE getExamDateTable = new ET_EXAM_DATE_TABLE(db);
            ET_BUILE_ROW_TABLE getBuildRowTable = new ET_BUILE_ROW_TABLE(db);

            // add Data
            ET_EXAM_SEAT AddExamSeat = new ET_EXAM_SEAT();
            ET_COURSE_OPEN AddCourseOP = new ET_COURSE_OPEN();
            ET_EXAM_DATE AddExamDate = new ET_EXAM_DATE();
            ET_BUILE_ROW AddBuildRow = new ET_BUILE_ROW();

            boolean checkAddCourseOP = false;
            boolean checkAddExamSeat = false;
            boolean checkAddExamDate = false;
            boolean checkAddBuildRow = false;

            // get the InputStream to store the file somewhere
            // InputStream fileInputStream = file_coursefilePart.getInputStream();
            // File fileToSave = new File(relativeWebPath +
            // filePart.getSubmittedFileName());
            // Files.copy(fileInputStream, fileToSave.toPath(),
            // StandardCopyOption.REPLACE_EXISTING);

            if (file_coursefilePart != null) {
                inputStream = file_coursefilePart.getInputStream();
                File fileToSave = new File(relativeWebPath + file_coursefilePart.getSubmittedFileName());
                Files.copy(inputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
                String pathfile_course = "C:\\java\\ET_COURSE_OPEN.csv";
                BufferedReader lineReader = new BufferedReader(new FileReader(pathfile_course));
                // BufferedReader lineReader = Files.newBufferedReader(Paths.get(path));
                String lineText = null;

                lineReader.readLine(); // skip header line
                while ((lineText = lineReader.readLine()) != null) {
                    try {
                        String[] arr = lineText.split(","); // TODO Auto-generated catch block
                        AddCourseOP.setYEAR(arr[0]);
                        AddCourseOP.setSEMESTER(arr[1]);
                        AddCourseOP.setCOURSE_NO(arr[2]);
                        // AddCourseOP.setCREDIT(new BigDecimal(arr[3]));
                        AddCourseOP.setSTATUS_COURSE(arr[4]);
                        // AddCourseOP.(arr[9]);
                        checkAddCourseOP = getCourseOPTable.insert(AddCourseOP);

                        if (checkAddCourseOP) {
                            db.commit();

                            // RequestDispatcher rs =
                            // request.getRequestDispatcher("admin/ShowsCounterAdmin.jsp");
                            // rs.forward(request, response);
                        } else {
                            db.rollback();
                            request.setAttribute("err", "1");
                            RequestDispatcher rs = request.getRequestDispatcher("admin/faild.jsp");
                            rs.forward(request, response);
                            // response.sendRedirect("admin/faild.jsp");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    lineReader.close();
                }
            }
            if (file_seatPart != null) {
                inputStream = file_seatPart.getInputStream();
                File fileToSave = new File(relativeWebPath + file_seatPart.getSubmittedFileName());
                Files.copy(inputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
                String pathfile_seat = "C:\\java\\ET_EXAM_SEAT.csv";
                BufferedReader lineReader = new BufferedReader(new FileReader(pathfile_seat));
                // BufferedReader lineReader = Files.newBufferedReader(Paths.get(path));
                String lineText = null;

                lineReader.readLine(); // skip header line
                while ((lineText = lineReader.readLine()) != null) {
                    try {
                        String[] arr = lineText.split(","); // TODO Auto-generated catch block
                        AddExamSeat.setPERIOD(arr[1]);
                        AddExamSeat.setEXAM_DATE(arr[2]);
                        AddExamSeat.setEXAM_SEAT(arr[3]);
                        AddExamSeat.setYEAR(arr[7]);
                        AddExamSeat.setSEMESTER(arr[8]);
                        // AddExamSeat.setBUILD(arr[9]);
                        checkAddExamSeat = getExamSeatTable.insert(AddExamSeat);

                        if (checkAddExamSeat) {
                            db.commit();

                            // RequestDispatcher rs =
                            // request.getRequestDispatcher("admin/ShowsCounterAdmin.jsp");
                            // rs.forward(request, response);
                        } else {
                            db.rollback();
                            request.setAttribute("err", "2");
                            RequestDispatcher rs = request.getRequestDispatcher("admin/faild.jsp");
                            rs.forward(request, response);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                lineReader.close();
            }
            if (file_rowseatPart != null) {
                inputStream = file_rowseatPart.getInputStream();
                File fileToSave = new File(relativeWebPath + file_rowseatPart.getSubmittedFileName());
                Files.copy(inputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
                String pathfile_rowseat = "C:\\java\\ET_EXAM_DATE.csv";
                BufferedReader lineReader = new BufferedReader(new FileReader(pathfile_rowseat));
                // BufferedReader lineReader = Files.newBufferedReader(Paths.get(path));
                String lineText = null;

                lineReader.readLine(); // skip header line
                while ((lineText = lineReader.readLine()) != null) {
                    try {
                        String[] arr = lineText.split(","); // TODO Auto-generated catch block
                        AddExamDate.setYEAR(arr[0]);
                        AddExamDate.setSEMESTER(arr[1]);
                        AddExamDate.setEXAM_DATE(arr[2]);
                        AddExamDate.setPERIOD(arr[3]);
                        checkAddExamDate = getExamDateTable.insert(AddExamDate);

                        if (checkAddExamDate) {
                            db.commit();

                            // RequestDispatcher rs =
                            // request.getRequestDispatcher("admin/ShowsCounterAdmin.jsp");
                            // rs.forward(request, response);
                        } else {
                            db.rollback();
                            request.setAttribute("err", "3");
                            RequestDispatcher rs = request.getRequestDispatcher("admin/faild.jsp");
                            rs.forward(request, response);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                lineReader.close();
            }
            if (file_buildPart != null) {
                inputStream = file_buildPart.getInputStream();
                File fileToSave = new File(relativeWebPath + file_buildPart.getSubmittedFileName());
                Files.copy(inputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
                String pathfile_build = "C:\\java\\ET_BUILE_ROW.csv";
                BufferedReader lineReader = new BufferedReader(new FileReader(pathfile_build));
                // BufferedReader lineReader = Files.newBufferedReader(Paths.get(path));
                String lineText = null;

                lineReader.readLine(); // skip header line
                while ((lineText = lineReader.readLine()) != null) {
                    try {
                        String[] arr = lineText.split(","); // TODO Auto-generated catch block
                        AddBuildRow.setYEAR(arr[0]);
                        AddBuildRow.setSEMESTER(arr[1]);
                        AddBuildRow.setBUILD_NO(arr[2]);
                        AddBuildRow.setROW_EXAM(arr[3]);
                        AddBuildRow.setSEAT_EXAM(new BigDecimal(arr[4]));
                        checkAddBuildRow = getBuildRowTable.insert(AddBuildRow);

                        if (checkAddBuildRow) {
                            db.commit();

                            // RequestDispatcher rs =
                            // request.getRequestDispatcher("admin/ShowsCounterAdmin.jsp");
                            // rs.forward(request, response);
                        } else {
                            db.rollback();
                            request.setAttribute("err", "4");
                            RequestDispatcher rs = request.getRequestDispatcher("admin/faild.jsp");
                            rs.forward(request, response);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                lineReader.close();
            }
            request.setAttribute("err", "4");
            RequestDispatcher rs = request.getRequestDispatcher("/ShowEditSeat");

        } else {
            RequestDispatcher rs = request.getRequestDispatcher("admin/faild.jsp");
            rs.forward(request, response);
        }
        db.close();
        // String absoluteFilePath = getServletContext().getRealPath(relativeWebPath);
        // File uploadedFile = new File(absoluteFilePath,
        // FilenameUtils.getName(filePart.getName()));
        // get the URL of the uploaded file
        // String fileUrl = "http://localhost:8080/uploaded-files/" +
        // filePart.getSubmittedFileName();

        /*
         * PrintWriter out = response.getWriter(); try { out.println("<!DOCTYPE html>");
         * out.println("<html>"); out.println("<head>");
         * out.println("<title>Servlet SaveFile</title>"); out.println("</head>");
         * out.println("<body>"); out.println("<h1>Servlet SaveFile at " +
         * request.getContextPath() + "</h1>"); out.println("</body>");
         * out.println("</html>"); } finally { out.close(); }
         */
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
