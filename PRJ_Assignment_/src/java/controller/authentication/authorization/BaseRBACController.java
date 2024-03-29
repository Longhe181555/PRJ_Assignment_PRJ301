/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication.authorization;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.RoleDBContext;
import entity.Account;
import entity.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author sonnt
 */
public abstract class BaseRBACController extends BaseRequiredAuthenticationController {

    private ArrayList<Role> getRoles(HttpServletRequest req, Account account) {
        String url = req.getServletPath();
        RoleDBContext db = new RoleDBContext();
        return db.getByUsernameAndUrl(account.getUsername(), url);
    }

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles)
            throws ServletException, IOException;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        ArrayList<Role> roles = getRoles(req, account);
        if (roles.size() < 1) {
            req.getSession().setAttribute("loginResult", "Session timer run out/ Access denied please login and try again!");
            resp.sendRedirect(req.getContextPath() +"/login_auth");
        } else {
            doPost(req, resp, account, roles);
        }
    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, Account account, ArrayList<Role> roles)
            throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException {
        ArrayList<Role> roles = getRoles(req, account);
        if (roles.size() < 1) {
            req.getSession().setAttribute("loginResult", "Session timer run out/ Access denied please login and try again!");
            resp.sendRedirect(req.getContextPath() +"/login_auth");
        } else {
            doGet(req, resp, account, roles);
        }
    }

}
