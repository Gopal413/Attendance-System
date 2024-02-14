package com.attendance.service;

import java.util.List;

import com.attendance.bean.AttendanceBean;
import com.attendance.bean.UserBean;

public interface UserService {
	public UserBean getUserSave(UserBean user);
	
	public boolean validateUser(UserBean user);
	
	public List<UserBean> getUserList();
	
	public UserBean getUserDetailByName(String userName);
	
	public String insertUserData();
	
	public String checkSignedIn(long userId);
	
	public String updateSignInAndSignOut(long userId,String flag);
	
	public List<AttendanceBean> attendanceAdminservice(long userId);
}
