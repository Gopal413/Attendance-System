package com.attendance.bean;

public class AttendanceBean {
	
	private int attId;
	private int userId;
	private String userName;
	private String attDate;
	private String signIn;
	private String signOut;
	
	
	public AttendanceBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AttendanceBean(int attId, int userId, String userName, String attDate, String signIn, String signOut) {
		super();
		this.attId = attId;
		this.userId = userId;
		this.userName = userName;
		this.attDate = attDate;
		this.signIn = signIn;
		this.signOut = signOut;
	}


	public int getAttId() {
		return attId;
	}


	public void setAttId(int attId) {
		this.attId = attId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getAttDate() {
		return attDate;
	}


	public void setAttDate(String attDate) {
		this.attDate = attDate;
	}


	public String getSignIn() {
		return signIn;
	}


	public void setSignIn(String signIn) {
		this.signIn = signIn;
	}


	public String getSignOut() {
		return signOut;
	}


	public void setSignOut(String signOut) {
		this.signOut = signOut;
	}


	@Override
	public String toString() {
		return "AttendanceBean [attId=" + attId + ", userId=" + userId + ", userName=" + userName + ", attDate="
				+ attDate + ", signIn=" + signIn + ", signOut=" + signOut + "]";
	}
	
	
	
	
}
