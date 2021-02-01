/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.et.login;

import com.et.model.*;
import java.io.IOException;
// import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ru-com7
 */
public class Login extends HttpServlet { 
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        String usr = request.getParameter("usr");
        String pwd = request.getParameter("pwd");
        // String acct = "เข้าสู่ระบบ";

        Database db = new Database();
        

        ET_USERS_TABLE userTable = new ET_USERS_TABLE(db);
        ET_USERS user = userTable.findByUserName(usr);

        if (user != null) {
            if (user.getUSER_NAME().equals(usr)) {
                if (user.getUSER_PASSWORD().equals(pwd)) {
                     HttpSession session = request.getSession();
                        session.setAttribute("user", user);
                        RequestDispatcher rs = request.getRequestDispatcher("admin/main.jsp");
                        rs.forward(request, response);
                } else {
                    String x = "1";
                    request.setAttribute("x", x);
                    RequestDispatcher rs = request.getRequestDispatcher("LoginFailed.jsp");
                    rs.forward(request, response);
                }
            } else {
                String x = "2";
                request.setAttribute("x", x);
                RequestDispatcher rs = request.getRequestDispatcher("LoginFailed.jsp");
                rs.forward(request, response);
            }
        } else {
            String x = "0";
            request.setAttribute("x", x);
            RequestDispatcher rs = request.getRequestDispatcher("LoginFailed.jsp");
            rs.forward(request, response);
        }

        db.close();
        
        
       /* PrintWriter out = response.getWriter();
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }*/
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
