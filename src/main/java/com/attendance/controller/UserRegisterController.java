package com.attendance.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.attendance.bean.AttendanceBean;
import com.attendance.bean.UserBean;
import com.attendance.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserRegisterController {
	@Autowired
	private UserService userservice;

	@RequestMapping("/")
	public String name() {

		return "redirect:/loginPage";
	}

	@RequestMapping("/registrationPage")
	public String registrationPage() {

		return "registrationPage.jsp";
	}

	@PostMapping("/registration")
	public String registration(UserBean user, Model model) {
		if (!user.getMobileNo().equals("") && !user.getUserName().equals("") && !user.getEmaiId().equals("")
				&& !user.getPassword().equals("")) {
			UserBean userTemp = userservice.getUserSave(user);
			if (userTemp.getStatus() != null && userTemp.getStatus().equalsIgnoreCase("EXISTS")) {
				model.addAttribute("userStatus", "User already exists please login");
				return "registrationPage.jsp";
			}

		} else {
			model.addAttribute("userStatus", "please not Empty text box");
			return "registrationPage.jsp";
		}

		return "login.jsp";

	}

	public boolean checkEmptyValue(UserBean user, Model model) {
		UserBean usercheck = new UserBean();
		if (usercheck.getStatus() != null && usercheck.getStatus().equalsIgnoreCase("EXISTS")) {
			model.addAttribute("userstatus", "user Already exists please login");
		} else if (!usercheck.getEmaiId().isEmpty() && !usercheck.getPassword().isEmpty()) {

		}
		return false;
	}

	@RequestMapping("/loginPage")
	public String loginPage() {

		return "login.jsp";
	}

	@PostMapping("/login")
	public String login(UserBean user, HttpSession session, Model model) {
		boolean result = userservice.validateUser(user);
		if (result == true) {
			UserBean userDetail = userservice.getUserDetailByName(user.getEmaiId());
			session.setAttribute("USER", userDetail);
			if (user.getEmaiId().equalsIgnoreCase("admin@gmail.com")) {
				return "admin.jsp";
			} else {
				String pattern = "dd-MM-yyyy HH:mm:ss";
				String dateInString = new SimpleDateFormat(pattern).format(new Date());
				String checkSignIn = userservice.checkSignedIn(userDetail.getUserId());
				if (checkSignIn.equalsIgnoreCase("P"))
					model.addAttribute("status", "I");
				else
					model.addAttribute("status", "O");
				model.addAttribute("currentDate", dateInString);
				return "signInAndSignOutPage.jsp";
			}
		} else
			return "login.jsp";
	}

	@PostMapping("/signIn")
	public String signIn(Model model, HttpSession session) {
		UserBean user = (UserBean) session.getAttribute("USER");
		userservice.updateSignInAndSignOut(user.getUserId(), "I");
		String pattern = "dd-MM-yyyy HH:mm:ss";
		String dateInString = new SimpleDateFormat(pattern).format(new Date());
		model.addAttribute("currentDate", dateInString);
		model.addAttribute("status", "I");
		return "signInAndSignOutPage.jsp";
	}

	@PostMapping("/viewReport")
	public String viewReport(Model model, HttpSession session) {
		UserBean user = (UserBean) session.getAttribute("USER");
		List<AttendanceBean> list = userservice.attendanceAdminservice(user.getUserId());
		model.addAttribute("report", list);
		return "signInAndSignOutPage.jsp";
	}

	@PostMapping("/signOut")
	public String signOut(Model model, HttpSession session) {
		UserBean user = (UserBean) session.getAttribute("USER");
		userservice.updateSignInAndSignOut(user.getUserId(), "O");
		String pattern = "dd-MM-yyyy HH:mm:ss";
		String dateInString = new SimpleDateFormat(pattern).format(new Date());
		model.addAttribute("currentDate", dateInString);
		model.addAttribute("status", "O");
		return "signInAndSignOutPage.jsp";
	}

	@PostMapping("/insertAllUserData")
	public String insertAllUserData(Model model, HttpSession session) {
		String result = userservice.insertUserData();
		model.addAttribute("status", "All User Data inserted Successfully ");
		return "admin.jsp";
	}

	@PostMapping("/viewAllUserReport")
	public String viewAllUserReport(Model model, HttpSession session) {

		List<UserBean> userListTemp = userservice.getUserList();
		List<UserBean> userList = userListTemp.stream().filter(usr->!usr.getEmaiId().equals("admin@gmail.com"))
								.collect(Collectors.toList());
		if (userList != null && userList.size() > 0)
			model.addAttribute("UserList", userList);
		else
			model.addAttribute("status", "No Data Found");
		return "admin.jsp";
	}

	@PostMapping("/adminViewReport")
	public String adminViewReport(Model model, HttpSession session,@RequestParam("userId") long userId) {

		List<AttendanceBean> list = userservice.attendanceAdminservice(userId);
		model.addAttribute("report", list);
		return "admin.jsp";
	}

}
