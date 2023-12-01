package com.eightbit.books.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eightbit.books.entity.User;
import com.eightbit.books.model.UserUpdateQuery;
import com.eightbit.books.service.UserService;

@Controller
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(path = "/user/index")
	public String routeToUserIndex(Model model) {
		List<User> userList = userService.findAll();
		model.addAttribute("userList", userList);
		
		return "userIndex";
	}
	
	@GetMapping("/user/search")
	public String searchUser(Model model, @RequestParam("name") String name) {
		List<User> userList = userService.searchUser(name);
		model.addAttribute("userList", userList);
		model.addAttribute("searchName", name);
		return "userIndex";
	}
	
	@GetMapping("/user/search/id")
	public String searchUserById(Model model, @RequestParam("id") int userId) {
		User user = userService.searchUserById(userId);
		model.addAttribute("user", user);
		UserUpdateQuery uuq = userService.getUserDto(user);
		model.addAttribute("userUpdateQuery", uuq);
		
		return "userDetail";
	}
	
	@PostMapping("/user/delete")
	public String deleteUser(@RequestParam("id") int userId) {
		System.out.println(userId);
		userService.deleteUserAndHistoryData(userId);
		return "redirect:/user/index";
	}
	
	@PostMapping("/user/update")
	public String updateUser(Model model, @ModelAttribute UserUpdateQuery userUpdateQuery) {
		userService.updateUser(userUpdateQuery);
		String param = "?id=" + userUpdateQuery.getUserId();
		return "redirect:/user/search/id" + param;
	}
	
	@GetMapping("/user/json")
	@ResponseBody
	public List<User> getUserJson() {
		List<User> userList = userService.findAll();
		return userList;
	}
	
	@GetMapping("/user/info")
	@ResponseBody
	public User getUser(@RequestParam("id") int userId) {
		User user = userService.searchUserById(userId);
		return user;
	}
	
	@GetMapping("/user/regist")
	public String bookRegist(Model model) {
		model.addAttribute("userEntity", new User());
		return "userRegist";
	}
	
	@PostMapping("/user/registExecute")
	public String userRegistExcute(@ModelAttribute User user, @RequestParam("birthday") String birth) {
		userService.userRegist(user, birth);
		
		return "redirect:/user/index";
	}
}
