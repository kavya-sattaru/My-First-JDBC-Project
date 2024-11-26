package userprofileapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import userprofileapp.dao.UserDao;
import userprofileapp.model.Users;



@Controller
public class MainController {

	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/home")
	public String viewHome(Model model) {
		List<Users> userList = userDao.getUsers();
		model.addAttribute("user", userList);
		return "home";
	}
	
	@RequestMapping("/adduser")
	public String addUser(Model model) {
		model.addAttribute("title","Add User");
		return "add_user";	
	}
	
	@RequestMapping(value = "/handle-user", method = RequestMethod.POST)
	public RedirectView handleUser(@ModelAttribute Users user, HttpServletRequest request) {
		System.out.println(user);
		userDao.createUser(user);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		return redirectView;
		
	}
	
	@RequestMapping("/delete/{userId}")
	public RedirectView deleteUser(@PathVariable("userId") int userId, HttpServletRequest request) {
		this.userDao.deleteUser(userId);
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl(request.getContextPath() + "/");
		return redirectView;
	}
	
	@RequestMapping("/update/{userId}")
	public String updateUser(@PathVariable("userId")int userId, Model model) {
		model.addAttribute("title","Update User");
		Users user = this.userDao.getUser(userId);
		model.addAttribute("user", user);
		return "update_user";
	}
}
