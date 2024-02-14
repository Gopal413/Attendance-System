package com.attendance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.bean.AttendanceBean;
import com.attendance.bean.UserBean;
import com.attendance.dao.UserDao;
import com.attendance.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserBean getUserSave(UserBean user) {
		// TODO Auto-generated method stub
		
		
		return userDao.getUserSave(user);
	}

	@Override
	public boolean validateUser(UserBean user) {
		// TODO Auto-generated method stub
		return userDao.validateUser(user);
	}

	@Override
	public List<UserBean> getUserList() {
		// TODO Auto-generated method stub
		return userDao.getUserList();
	}

	@Override
	public UserBean getUserDetailByName(String emaiId) {
		
		List<UserBean> userList = getUserList();
		
		UserBean user = userList.stream().filter(usr->usr.getEmaiId().equalsIgnoreCase(emaiId))
				.findFirst().orElse(new UserBean());
		
		return user;
	}

	@Override
	public String insertUserData() {
		// TODO Auto-generated method stub
		return userDao.insertUserData();
	}

	@Override
	public String checkSignedIn(long userId) {
		// TODO Auto-generated method stub
		return userDao.checkSignedIn(userId);
	}

	@Override
	public String updateSignInAndSignOut(long userId, String flag) {
		// TODO Auto-generated method stub
		return userDao.updateSignInAndSignOut(userId,flag);
	}

	@Override
	public List<AttendanceBean> attendanceAdminservice(long userId) {
		// TODO Auto-generated method stub
		return userDao.attendanceAdmin(userId);
	}

}
