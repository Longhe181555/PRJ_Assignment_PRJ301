/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;


public class SessionListener implements HttpSessionListener {
    private static int activeSessions = 0;

    static int getActiveSessions() {
     
        return activeSessions;
    }
    @Override
public void sessionCreated(HttpSessionEvent se) {
    
    activeSessions++;
    
      
    se.getSession().setAttribute("activeSessions", activeSessions);
}

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        if (activeSessions > 0) {
            activeSessions--;
           
            se.getSession().setAttribute("activeSessions", activeSessions);
        }
    }

}
