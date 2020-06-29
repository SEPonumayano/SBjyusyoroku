package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	//一覧ページ
	@GetMapping(value = "/user/list")
	public String displayList(Model model) {
		List<User> userlist = userService.searchAll();
		model.addAttribute("userlist", userlist);
		return "user/list";
	}

	//登録ページ
	@GetMapping(value = "/user/add")
	public String displayAdd(Model model) {
		model.addAttribute("userRequest", new UserRequest());
		return "user/add";
	}

	//エラー出力
	@RequestMapping(value = "/user/create", method = RequestMethod.POST)
	public String create(@Validated @ModelAttribute UserRequest userRequest, BindingResult result, Model model) {
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}

			model.addAttribute("validationError", errorList);
			return "user/add";
		}

		userService.create(userRequest);
		return "user/addCheck";
	}

	//登録確認ページ
	@RequestMapping(value="/user/addCheck" ,method=RequestMethod.POST)
		public String addCheck(@ModelAttribute User user, Model model) {

		model.addAttribute("User",user);
			return "addCheck";
		}

	//登録完了
	@RequestMapping(value = "/user/creat", method = RequestMethod.POST)
	public String creat(@Validated @ModelAttribute UserRequest userRequest, BindingResult result, Model model) {
		userService.creat(userRequest);
		return "redirect:/user/list";
	}
}


