package edu.wlxy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.wlxy.dao.impl.UserDAOImpl;
import edu.wlxy.entity.User;

public class ServletUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 2 获取参数
		String mymethod = request.getParameter("mymethod");
		UserDAOImpl  userdao=new UserDAOImpl();
		PrintWriter out =response.getWriter();
		if (mymethod.equals("login")) {
			String username=request.getParameter("username");
			String userpassword=request.getParameter("pwd");
			boolean  flag=userdao.loginCheck(username, userpassword);
			if(flag) {
				HttpSession session=request.getSession();
				session.setAttribute("uname", username);
				response.sendRedirect(request.getContextPath()+"/manager/index.html");
				
				//request.getRequestDispatcher("/index.html").forward(request, response);
			}else {
				out.print("你输入的用户名或密码有误");
			}
		}
		else if (mymethod.equals("addUser")) {
			String username=request.getParameter("username");
			String userpassword=request.getParameter("userpassword");
			int userage=Integer.parseInt(request.getParameter("userage"));
			User user=new User();
			user.setUsername(username);
			user.setUserpassword(userpassword);
			user.setUserage(userage);
			boolean flag=userdao.addUser(user);
			if(flag) {
				out.print("用户添加成功");
			}else {
				out.print("用户添加失败");
			}
		}
		else if (mymethod.equals("modifyUser")) {
			String username=request.getParameter("username");
			String userpassword=request.getParameter("userpassword");
			int userage=Integer.parseInt(request.getParameter("userage"));
			int userid=Integer.parseInt(request.getParameter("userid"));
			User user=new User();
			user.setUsername(username);
			user.setUserpassword(userpassword);
			user.setUserage(userage);
			user.setUserid(userid);
			boolean flag=userdao.modifyUser(user);
			if(flag) {
				out.print("用户修改成功");
			}else {
				out.print("用户修改失败");
			}
		}
		else if (mymethod.equals("deleteUser")) {
			int userid=Integer.parseInt(request.getParameter("userid"));
			boolean flag=userdao.deleteUser(userid);
			if(flag) {
				out.print("用户删除成功");
			}else {
				out.print("用户删除失败");
			}
		}
		else if (mymethod.equals("findUserbyid")) {
			int userid=Integer.parseInt(request.getParameter("userid"));
			User user=userdao.findUserByUserid(userid);
			if( user!=null) {
				request.setAttribute("myuser", user);
				request.getRequestDispatcher("/manager/usermodify.jsp").forward(request, response);
			}else {
				out.print("用户不存在");
			}
		}
		else  if (mymethod.equals("findAllUser")) {
			ArrayList<User> userlist=userdao.findAllUser();
			if(userlist!=null) {
				request.setAttribute("ulist", userlist);
				request.getRequestDispatcher("/manager/userlist.jsp").forward(request, response);
			}else {
				out.print("用户不存在");
			}
		}
	}

}
