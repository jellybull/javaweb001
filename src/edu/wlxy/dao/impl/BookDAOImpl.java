package edu.wlxy.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import edu.wlxy.dao.BookDAO;
import edu.wlxy.entity.Book;
import edu.wlxy.entity.User;
import edu.wlxy.util.C3P0Utils;

public class BookDAOImpl implements BookDAO{

	@Override
	public boolean addBook(Book book) {
		boolean flag = false;
		try {
			QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
			String sql = "insert into  booktable(bookname,bookauthor,bookpublish,bookprice,bookpubdate,booktype)  values(?,?,?,?,?,?)";
			Object[] params={book.getBookname(),book.getBookauthor(),book.getBookpublish(),book.getBookprice(),book.getBookpubdate(),book.getBooktype()};
			int count;
			count = runner.update(sql, params);
			if (count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean modifyBook(Book book) {
		boolean flag = false;
		try {
			QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
			String sql = "update  booktable  set bookname=?,bookauthor=?,bookpublish=?,bookprice=?,bookpubdate=?,booktype=?  where bookid=?";
			Object[] params={book.getBookname(),book.getBookauthor(),book.getBookpublish(),book.getBookprice(),book.getBookpubdate(),book.getBooktype(),book.getBookid()};
			int count;
			count = runner.update(sql, params);
			if (count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteBook(int bookid) {
		boolean flag = false;
		try {
			QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
			String sql = "delete from   booktable where bookid=?";
			Object[] params={bookid};
			int count;
			count = runner.update(sql, params);
			if (count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public ArrayList<Book> findAllBook() {
		ArrayList<Book> booklist = new ArrayList<Book>();
		try {
			QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
			String sql = "select bookid,bookname,bookauthor,bookpublish,bookprice,bookpubdate,booktype,bookimge from booktable ";
			Object[] params={};
			booklist=(ArrayList<Book>)runner.query(sql, new BeanListHandler(Book.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booklist;
	}

	@Override
	public Book findBookByBookid(int bookid) {
		Book book = new Book();
		try {
			QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
			String sql = "select bookid,bookname,bookauthor,bookpublish,bookprice,bookpubdate,booktype,bookimge from booktable where bookid=?";
			Object[] params={bookid};
			book=(Book)runner.query(sql, new BeanHandler(Book.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
}
