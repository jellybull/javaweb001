package edu.wlxy.dao.impl;

import java.sql.*;
import java.util.ArrayList;

import edu.wlxy.dao.UserDAO;
import edu.wlxy.entity.User;
import edu.wlxy.util.JDBCUtils;

public class UserDAOImpl2 implements UserDAO {
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	@Override
	public boolean loginCheck(String username, String userpassword) {
		boolean flag = false;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select userid from usertable  where  username=?  and userpassword=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, userpassword);

			rs = pst.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("userid");
				if (id > 0)
					flag = true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(conn, pst, rs);
		}
		return flag;
	}

	@Override
	public boolean addUser(User user) {
		boolean flag = false;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "insert into  usertable(username,userpassword,userage)  values(?, ?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getUserpassword());
			pst.setInt(3, user.getUserage());
			int count = pst.executeUpdate();
			if (count > 0) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(conn, pst);
		}
		return flag;
	}

	@Override
	public boolean modifyUser(User user) {
		boolean flag = false;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "update  usertable  set username=?,userpassword=?,userage=?  where userid=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getUserpassword());
			pst.setInt(3, user.getUserage());
			pst.setInt(4, user.getUserid());
			int count = pst.executeUpdate();
			if (count > 0) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(conn, pst);
		}
		return flag;
	}

	@Override
	public boolean deleteUser(int userid) {
		boolean flag = false;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "delete from   usertable where userid=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userid);
			int count = pst.executeUpdate();
			if (count > 0) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(conn, pst);
		}
		return flag;
	}

	@Override
	public ArrayList<User> findAllUser() {
		ArrayList<User> userlist = new ArrayList<User>();
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select userid,username,userpassword,userage from usertable";
			pst = conn.prepareStatement(sql);

			rs = pst.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setUserpassword(rs.getString("userpassword"));
				user.setUserage(rs.getInt("userage"));
				userlist.add(user);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(conn, pst, rs);
		}
		return userlist;
	}

	@Override
	public User findUserByUserid(int userid) {
		User user = new User();
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select userid,username,userpassword,userage from usertable where userid=?";
			pst = conn.prepareStatement(sql);
            pst.setInt(1, userid);
			rs = pst.executeQuery();
			while (rs.next()) {
				
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setUserpassword(rs.getString("userpassword"));
				user.setUserage(rs.getInt("userage"));
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.release(conn, pst, rs);
		}
		return user;
	}

}
