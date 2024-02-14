package com.attendance.dao;

import java.util.List;

import com.attendance.bean.AttendanceBean;
import com.attendance.bean.UserBean;
import com.attendance.bean.VoterBean;

public interface UserDao {

	public UserBean getUserSave(UserBean user);
		
	public boolean validateUser(UserBean user);
	
	public List<UserBean> getUserList();
	
	public String insertUserData();
	
	public String checkSignedIn(long userId);
	
	public String updateSignInAndSignOut(long userId,String flag);
	
	public List<AttendanceBean> attendanceAdmin(long userId);
}
