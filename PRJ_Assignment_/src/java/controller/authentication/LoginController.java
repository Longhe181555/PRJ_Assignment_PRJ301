/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import dal.AccountDBContext;
import dal.RoleDBContext;
import entity.Account;
import entity.Role;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginController extends HttpServlet {

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
        request.getRequestDispatcher("login/login.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RoleDBContext dbr = new RoleDBContext();
        AccountDBContext db = new AccountDBContext();
        Account account = db.getByUsernamePassword(username, password);

        if (account != null) {
            HttpSession session = request.getSession();
            session.setAttribute("account", account);

            // Setting cookies
            Cookie c_user = new Cookie("username", username);
            Cookie c_pass = new Cookie("password", password);
            c_user.setMaxAge(5000);
            c_pass.setMaxAge(5000);
            response.addCookie(c_pass);
            response.addCookie(c_user);

            Role role = dbr.getByUsername(username);
            if (role.getName().equals("student")) {
                response.sendRedirect("student?id=" + account.getId());
            } else if (role.getName().equals("teacher") || role.getName().equals("supervisor")) {
                response.sendRedirect("teacher?id=" + account.getId());
            }

        } else {
            //login failed!
            String loginResult = "Login failed, please try again";// Store the login result in the session
            request.getSession().setAttribute("loginResult", loginResult);
            // Redirect to the login_auth page
            response.sendRedirect("login_auth");
        }

    }

}
