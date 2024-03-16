/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.ArticleDBContext;
import dal.CategoryDBContext;
import java.io.IOException;
import java.sql.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entity.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 *
 * @author ADMIN
 */
@WebServlet(name="InsertController", urlPatterns={"/InsertController"})
public class InsertController extends BaseRequiredAuthenticationController {
  
   

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
    throws ServletException, IOException {
        CategoryDBContext dbc = new CategoryDBContext();
        ArrayList<Category> cats = dbc.list();
        request.setAttribute("cats",cats);
        request.getRequestDispatcher("insert.jsp").forward(request, response);
    } 

   
  @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
        throws ServletException, IOException {
    ArticleDBContext dba = new ArticleDBContext();
    String title = request.getParameter("title");
    String publishedDateStr = request.getParameter("publishedDate");
    boolean online = request.getParameter("online") != null; // Checkbox value
    int categoryId = Integer.parseInt(request.getParameter("category"));
    
    java.sql.Date publishedDate = null;
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = dateFormat.parse(publishedDateStr);
        publishedDate = new java.sql.Date(parsedDate.getTime());
    } catch (ParseException e) {
        e.printStackTrace();
    }
    Category cat = new Category();
    cat.setCatid(categoryId);
    
    Article article = new Article();
    article.setCategory(cat);
    article.setTitle(title);
    article.setPublished_date(publishedDate);
    article.setOnline(online);
    article.setAccount(account);
   
    dba.insert(article);
    
    response.sendRedirect("insert");
}


 
}
