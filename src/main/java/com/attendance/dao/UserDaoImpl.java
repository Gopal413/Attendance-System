package com.attendance.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.attendance.bean.AttendanceBean;
import com.attendance.bean.UserBean;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbctemp;

	@Override
	public UserBean getUserSave(UserBean user) {
		if (!validateUser(user)) {
			String query = "insert into TBL_USER_MASTER(USER_NAME ,USER_PASSWORD ,USER_EMAIL_ID ,USER_MOBILE_NO) values(?,?,?,?)";

			int result = jdbctemp.update(query, user.getUserName(), user.getPassword(), user.getEmaiId(),
					user.getMobileNo());

			if (result == 1) {
				System.out.println("successfully run");
				
				return user;
			}
		} else {
			UserBean remark = new UserBean();
			remark.setStatus("EXISTS");
			return remark;
		}

		return null;
	}

	/*private void insertUserAttendanceData(long userId) {
		List<String> dateList = getcurrentMonthDate();
		for (int j = 0; j < dateList.size(); j++) {
			if (!checkAlreadyDataPresent(userId, dateList.get(j))) {
				System.out.println("Date " + dateList.get(j));
				String query = "insert into TBL_ATTENDANCE_MASTER(ATT_USER_ID ,ATT_DATE) values(?,?)";
				jdbctemp.update(query, userId, dateList.get(j));
			} else
				break;
		}
	}*/

	@Override
	public boolean validateUser(UserBean user) {
		String query = "SELECT COUNT(1) FROM TBL_USER_MASTER WHERE USER_EMAIL_ID =? and USER_PASSWORD=?";
		long count = jdbctemp.queryForObject(query, new Object[] { user.getEmaiId(), user.getPassword() }, Long.class);
		if (count == 1) {
			return true;
		}
		return false;
	}

	@Override
	public List<UserBean> getUserList() {
		String query = "SELECT * FROM TBL_USER_MASTER ";

		List<Map<String, Object>> rows = jdbctemp.queryForList(query);
		List<UserBean> userList = new ArrayList<UserBean>();

		for (Map<String, Object> row : rows) {
			UserBean user = new UserBean();
			user.setUserId(Integer.parseInt(String.valueOf(row.get("USER_ID"))));
			user.setUserName(String.valueOf(row.get("USER_NAME")));
			user.setEmaiId(String.valueOf(row.get("USER_EMAIL_ID")));
			user.setMobileNo(String.valueOf(row.get("USER_MOBILE_NO")));
			user.setStatus(String.valueOf(row.get("USER_STATUS")));
			userList.add(user);
		}

		return userList;
	}

	@Override
	public String insertUserData() {
		List<UserBean> userList = getUserList();
		List<String> dateList = getcurrentMonthDate();
		for (int i = 0; i < userList.size(); i++) {
			if (!userList.get(i).getEmaiId().equals("admin@gmail.com")) {
				for (int j = 0; j < dateList.size(); j++) {
					if (!checkAlreadyDataPresent(userList.get(i).getUserId(), dateList.get(j))) {
						System.out.println("Date " + dateList.get(j));
						String query = "insert into TBL_ATTENDANCE_MASTER(ATT_USER_ID ,ATT_DATE) values(?,?)";
						jdbctemp.update(query, userList.get(i).getUserId(), dateList.get(j));
					} else
						break;
				}
			}
		}

		return "SUCCESS";
	}

	public boolean checkAlreadyDataPresent(long userId, String currentDate) {
		String query = "SELECT COUNT(1) FROM TBL_ATTENDANCE_MASTER " + "WHERE ATT_USER_ID =? and ATT_DATE =?";

		long result = jdbctemp.queryForObject(query, new Object[] { userId, currentDate }, Long.class);
		if (result == 1)
			return true;
		else
			return false;
	}

	public List<String> getcurrentMonthDate() {

		List<String> dateList = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd");
		System.out.print(df.format(cal.getTime()));
		for (int i = 1; i <= maxDay; i++) {
			cal.set(Calendar.DAY_OF_MONTH, i);
			dateList.add(df.format(cal.getTime()));
			System.out.print(", " + df.format(cal.getTime()));
		}
		System.out.println(" dateList Size " + dateList.size());
		return dateList;
	}

	@Override
	public String checkSignedIn(long userId) {

		String pattern = "yyyy-MM-dd";
		String currentDate = new SimpleDateFormat(pattern).format(new Date());
		System.out.println(" checkSignedIn Current Date " + currentDate);
		String query = "SELECT COUNT(1) FROM TBL_ATTENDANCE_MASTER WHERE ATT_USER_ID =? and ATT_DATE =?";

		long result = jdbctemp.queryForObject(query, new Object[] { userId, currentDate }, Long.class);
		if (result == 1)
			return "P";

		return "A";
	}

	@Override
	public String updateSignInAndSignOut(long userId, String flag) {
		String query = "";

		String pattern = "HH:mm:ss";
		String time = new SimpleDateFormat(pattern).format(new Date());
		String datePattern = "yyyy-MM-dd";
		String currentDate = new SimpleDateFormat(datePattern).format(new Date());
		if (flag.equals("I") && checkSignedIn(userId).equals("A"))
			query = "insert into TBL_ATTENDANCE_MASTER(att_sign_in,ATT_USER_ID,ATT_DATE) values(?,?,?)";
		else if(flag.equals("O") )
			query = "UPDATE TBL_ATTENDANCE_MASTER SET att_sign_out=? WHERE ATT_USER_ID=? AND ATT_DATE=?";

		int result = jdbctemp.update(query, time, userId, currentDate);
		if (result == 1)
			return "SUCCESS";

		return "FAIL";
	}

	public List<AttendanceBean> attendanceAdmin(long userId) {
		String query = "SELECT calendar.date as ATT_DATE ,COALESCE(attendance.att_sign_in,'Absent') as signIn \r\n"
				+ ",COALESCE(attendance.att_sign_out,'Absent') signOut\r\n"
				+ "FROM (\r\n"
				+ "    SELECT DATE_ADD((select DATE(created_on) from tbl_user_master WHERE USER_ID=? LIMIT 1), INTERVAL a + b * 10 + c * 100 DAY) AS date\r\n"
				+ "    FROM (SELECT 0 AS a UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS a\r\n"
				+ "    CROSS JOIN (SELECT 0 AS b UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS b\r\n"
				+ "    CROSS JOIN (SELECT 0 AS c UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9) AS c\r\n"
				+ ") calendar\r\n"
				+ "LEFT outer JOIN (\r\n"
				+ "    SELECT DATE(att_date) AS att_date,att_sign_in,att_sign_out\r\n"
				+ "    FROM tbl_attendance_master att \r\n"
				+ "    WHERE att_user_id = ?\r\n"
				+ ") attendance ON calendar.date = attendance.att_date\r\n"
				+ "WHERE calendar.date <= DATE(current_date())";
		List<AttendanceBean> list = new ArrayList<>();
		List<Map<String, Object>> attlist = jdbctemp.queryForList(query,userId,userId);

		for (Map<String, Object> row : attlist) {
			AttendanceBean attbean = new AttendanceBean();
			attbean.setAttDate(String.valueOf(row.get("ATT_DATE")));
			attbean.setSignIn(String.valueOf(row.get("signIn")));
			attbean.setSignOut(String.valueOf(row.get("signOut")));
			list.add(attbean);
		}

		return list;

	}

}
