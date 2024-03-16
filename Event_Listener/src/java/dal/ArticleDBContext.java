/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticleDBContext extends DBContext {

    public ArrayList<Article> list() {
        ArrayList<Article> as = new ArrayList<>();
        try {
            String sql = "SELECT a.[aid]\n"
                    + "      ,a.[title]\n"
                    + "      ,a.[published_date]\n"
                    + "      ,a.[online]\n"
                    + "      ,a.[username]\n"
                    + "      ,a.[catid]\n"
                    + "	  ,c.catname\n"
                    + "	  ,ac.displayname\n"
                    + "	  ,ac.username\n"
                    + "	  ,ac.password\n"
                    + "  FROM [Article] a\n"
                    + "  join Category c on a.catid = c.catid \n"
                    + "  join Account ac on a.username = ac.username ";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                Article a = new Article();
                a.setAid(rs.getInt("aid"));
                a.setTitle(rs.getString("title"));
                a.setPublished_date(rs.getDate("published_date"));
                a.setOnline(rs.getBoolean("online"));
                Account account = new Account();
                account.setPassword(rs.getString("password"));
                account.setUsername(rs.getString("username"));
                account.setDisplayname(rs.getString("displayname"));
                a.setAccount(account);
                Category cat = new Category();
                cat.setCatid(rs.getInt("catid"));
                cat.setCatname(rs.getString("catname"));
                a.setCategory(cat);
                as.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return as;
    }

    
    public void insert(Article article) {
        PreparedStatement statement = null;
        
        try {
 
            // Prepare INSERT statement
            String query = "INSERT INTO Article (title, published_date, online, username, catid) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            
            // Set parameter values
            statement.setString(1, article.getTitle());
            statement.setDate(2, article.getPublished_date());
            statement.setBoolean(3, article.isOnline());
            statement.setString(4, article.getAccount().getUsername()); 
            statement.setInt(5, article.getCategory().getCatid()); 
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log exception
        } 
    }

    @Override
    public void update(IEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(IEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public IEntity get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(IEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
