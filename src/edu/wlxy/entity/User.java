package edu.wlxy.entity;

public class User {
private int userid;
private String username;
private String userpassword;
private int userage;
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUserpassword() {
	return userpassword;
}
public void setUserpassword(String userpassword) {
	this.userpassword = userpassword;
}
public int getUserage() {
	return userage;
}
public void setUserage(int userage) {
	this.userage = userage;
}

public User() {
	super();
}
public User(String username, String userpassword, int userage) {
	super();
	this.username = username;
	this.userpassword = userpassword;
	this.userage = userage;
}
public User(int userid, String username, String userpassword, int userage) {
	super();
	this.userid = userid;
	this.username = username;
	this.userpassword = userpassword;
	this.userage = userage;
}





	
	
}
