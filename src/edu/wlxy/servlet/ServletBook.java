package edu.wlxy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.wlxy.dao.impl.BookDAOImpl;
import edu.wlxy.entity.Book;
import edu.wlxy.entity.User;

/**
 * Servlet implementation class ServletBook
 */
@WebServlet("/ServletBook")
public class ServletBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				// 2 获取参数
				String mymethod = request.getParameter("mymethod");
				BookDAOImpl  bookdao=new BookDAOImpl();
				PrintWriter out =response.getWriter();
				if(mymethod.equals("findAllBook")){
					ArrayList<Book> booklist=bookdao.findAllBook();
					if(booklist!=null) {
						request.setAttribute("blist", booklist);
						request.getRequestDispatcher("/manager/booklist.jsp").forward(request, response);
					}else {
						out.print("图书不存在");
					}
				}else if(mymethod.equals("addBook")){
					String bookname=request.getParameter("bookname");
					String bookauthor=request.getParameter("bookauthor");
					String bookpublish=request.getParameter("bookpublish");
					String bookprice=request.getParameter("bookprice");
					String bookpubdate=request.getParameter("bookpubdate");
					String booktype=request.getParameter("booktype");
					String bookimge=request.getParameter("bookimge");
					Book book=new Book();
					book.setBookname(bookname);
					book.setBookauthor(bookauthor);
					book.setBookpublish(bookpublish);
					book.setBookprice(Double.parseDouble(bookprice));
					book.setBookpubdate(bookpubdate);
					book.setBooktype(booktype);
					book.setBookimge(bookimge);
					boolean flag=bookdao.addBook(book);
					if(flag) {
						out.print("图书添加成功");
					}else {
						out.print("图书添加失败");
					}
				}else if(mymethod.equals("deleteBook")){
					int bookid=Integer.parseInt(request.getParameter("bookid"));
					boolean flag=bookdao.deleteBook(bookid);
					if(flag) {
						out.print("图书删除成功");
					}else {
						out.print("图书删除失败");
					}
				}else if(mymethod.equals("findBookbyid")){
					int bookid=Integer.parseInt(request.getParameter("bookid"));
					Book book=bookdao.findBookByBookid(bookid);
					if( book!=null) {
						request.setAttribute("mybook", book);
						request.getRequestDispatcher("/manager/bookmodify.jsp").forward(request, response);
					}else {
						out.print("图书不存在");
					}
				}else if(mymethod.equals("modifyBook")){
					String bookid=request.getParameter("bookid");
					String bookname=request.getParameter("bookname");
					String bookauthor=request.getParameter("bookauthor");
					String bookpublish=request.getParameter("bookpublish");
					String bookprice=request.getParameter("bookprice");
					String bookpubdate=request.getParameter("bookpubdate");
					String booktype=request.getParameter("booktype");
					String bookimge=request.getParameter("bookimge");
					Book book=new Book();
					book.setBookid(Integer.parseInt(bookid));
					book.setBookname(bookname);
					book.setBookauthor(bookauthor);
					book.setBookpublish(bookpublish);
					book.setBookprice(Double.parseDouble(bookprice));
					book.setBookpubdate(bookpubdate);
					book.setBooktype(booktype);
					//book.setBookimge(bookimge);
					boolean flag=bookdao.modifyBook(book);
					System.out.print(bookname);
					if(flag) {
						out.print("图书修改成功");
					}else {
						out.print("图书修改失败");
					}
				}
	}

}
