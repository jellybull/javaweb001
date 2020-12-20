package edu.wlxy.dao;

import java.util.ArrayList;

import edu.wlxy.entity.Book;

public interface BookDAO {
	// 添加图书
		public boolean addBook(Book book);
		// 修改图书
		public boolean modifyBook(Book book);
			// 删除图书
		public boolean deleteBook(int bookid);
			// 查找所有图书
		public ArrayList<Book> findAllBook();
			//根据bookid查找图书
		public Book findBookByBookid(int bookid);
}
