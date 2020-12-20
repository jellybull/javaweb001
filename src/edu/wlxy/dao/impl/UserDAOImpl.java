package edu.wlxy.dao.impl;

import java.sql.*;
import java.util.ArrayList;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import edu.wlxy.dao.UserDAO;
import edu.wlxy.entity.User;
import edu.wlxy.util.C3P0Utils;
import edu.wlxy.util.JDBCUtils;

public class UserDAOImpl implements UserDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	@Override
	public boolean loginCheck(String username, String userpassword) {
		boolean flag = false;
		User user = new User();
		try {
			QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
			String sql = "select userid from usertable  where  username=?  and userpassword=?";
			Object[] params={username,userpassword};
			user=(User)runner.query(sql, new BeanHandler(User.class), params);
		if(user!=null){
			flag = true;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean addUser(User user) {
		boolean flag = false;
		try {
			QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
			String sql = "insert into  usertable(username,userpassword,userage)  values(?,?,?)";
			Object[] params={user.getUsername(),user.getUserpassword(),user.getUserage()};
			int count;
			count = runner.update(sql, params);
			if (count > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.print(user.getUsername());
		return flag;
	}

	@Override
	public boolean modifyUser(User user) {
		boolean flag = false;
		try {
			QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
			String sql = "update  usertable  set username=?,userpassword=?,userage=?  where userid=?";
			Object[] params={user.getUsername(),user.getUserpassword(),user.getUserage(),user.getUserid()};
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
	public boolean deleteUser(int userid) {
		boolean flag = false;
		try {
			QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
			String sql = "delete from   usertable where userid=?";
			Object[] params={userid};
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
	public ArrayList<User> findAllUser() {
		ArrayList<User> userlist = new ArrayList<User>();
		try {
			QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
			String sql = "select userid,username,userpassword,userage from usertable ";
			Object[] params={};
			userlist=(ArrayList<User>)runner.query(sql, new BeanListHandler(User.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userlist;
	}

	@Override
	public User findUserByUserid(int userid) {
		User user = new User();
		try {
			QueryRunner runner=new QueryRunner(C3P0Utils.getDataSource());
			String sql = "select userid,username,userpassword,userage from usertable where userid=?";
			Object[] params={userid};
			user=(User)runner.query(sql, new BeanHandler(User.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
