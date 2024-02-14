package com.attendance.bean;

import org.aspectj.lang.annotation.RequiredTypes;

public class UserBean {
	
	
	private long userId;
	private String userName;
	private String mobileNo;
	private String password;
	private String emaiId;
	private String status;
	
	
	
	public UserBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public UserBean(long userId, String userName, String mobileNo, String password, String emaiId, String status) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.mobileNo = mobileNo;
		this.password = password;
		this.emaiId = emaiId;
		this.status = status;
	}





	public long getUserId() {
		return userId;
	}



	public void setUserId(long userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}


	
	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getMobileNo() {
		return mobileNo;
	}



	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmaiId() {
		return emaiId;
	}



	public void setEmaiId(String emaiId) {
		this.emaiId = emaiId;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}
	
	


	
	
	
	
	
	
	

}
