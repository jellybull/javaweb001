package edu.wlxy.dao;

import java.util.ArrayList;

import edu.wlxy.entity.User;

/*
 * 提供对usertable表操作的接口方法
 * 只是约束，有哪些方法，需要哪些参数，返回值类型
 * 
 */
public interface UserDAO {
	// 添加用户
	public boolean addUser(User user);
	// 修改用户
	public boolean modifyUser(User user);
		// 删除用户
	public boolean deleteUser(int userid);
		// 查找所有用户
	public ArrayList<User> findAllUser();
		//根据userid查找用户
	public User findUserByUserid(int userid);
	// 用于登录判断
	public boolean loginCheck(String username,String userpassword);
}
