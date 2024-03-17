/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import dal.AccountDBContext;
import entity.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class LoginController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login/login.jsp").forward(request, response);
    }


@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    AccountDBContext db = new AccountDBContext();
    Account account = db.getByUsernamePassword(username, password);

    if (account != null) {
        HttpSession session = request.getSession();
        session.setAttribute("account", account);

        // Increment the activeSessions count
        Integer activeSessions = (Integer) session.getServletContext().getAttribute("activeSessions");
        if (activeSessions == null) {
            activeSessions = 1;
        } else {
            activeSessions++;
        }
        session.getServletContext().setAttribute("activeSessions", activeSessions);

        // Setting cookies
        Cookie c_user = new Cookie("username", username);
        Cookie c_pass = new Cookie("password", password);
        c_user.setMaxAge(1000);
        c_pass.setMaxAge(1000);
        response.addCookie(c_pass);
        response.addCookie(c_user);

        // Redirect based on role
       
        response.getWriter().println("login successful");
    } else {
        response.getWriter().println("login failed");
    }
}



}
