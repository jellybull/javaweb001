package edu.wlxy.util;

import java.util.Vector;

public class OnlineUser {
private static Vector online=new Vector();
public static void addUser(String loginname){
	online.addElement(loginname);
}

public static void removedUser(String loginname){
	online.removeElement(loginname);
}
public static int getUserCount(String loginname){
	return online.size();
}
public static Vector getVector(){
	return online;
}
}
