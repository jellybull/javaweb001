package edu.wlxy.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import edu.wlxy.util.OnlineUser;

public class OnlineListener implements HttpSessionListener, HttpSessionAttributeListener {

    public OnlineListener() {
        // TODO Auto-generated constructor stub
    }

    public void sessionCreated(HttpSessionEvent arg0)  { 
//         System.out.println("会话已创建");
    }

    public void sessionDestroyed(HttpSessionEvent arg0)  { 
//    	  System.out.println("会话已销毁");
    }

    public void attributeAdded(HttpSessionBindingEvent se)  { 
//      OnlineUser.addUser(String.valueOf(se.getValue()));
//      System.out.println("编号为("+se.getSession().getId()+"");
    }

    public void attributeRemoved(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }

    public void attributeReplaced(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }
	
}
