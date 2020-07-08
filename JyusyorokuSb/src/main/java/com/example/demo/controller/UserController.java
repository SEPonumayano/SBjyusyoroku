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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserUpdateRequest;
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

	//住所検索
	@RequestMapping(value ="/user/search" ,method = RequestMethod.POST)
	public ModelAndView search(ModelAndView mav,@RequestParam(name="keyword") String keyword) {

		mav.setViewName("/user/list");
		mav.addObject("keyword",keyword);
		List<User> result = userService.searchpoint(keyword);
		mav.addObject("userlist",result);
		//mav.addObject("resultSize",result.size());

		return mav;
	}

	//-----------------------------------------------------

	//登録ページ
	@GetMapping(value = "/user/add")
	public String displayAdd(Model model) {
		model.addAttribute("userRequest", new UserRequest());
		return "user/add";
	}

	//登録エラー出力
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
		public String addCheck(@ModelAttribute UserRequest userRequest, Model model) {

		model.addAttribute("UserRequest",  userRequest);
			return "user/addCheck";
		}

	//登録完了
	@RequestMapping(value = "/user/creat", method = RequestMethod.POST)
	public String creat(@ModelAttribute UserRequest userRequest, Model model) {

		//model.addAttribute("UserRequest",  userRequest);
		userService.creat(userRequest);
		return "redirect:/user/list";
	}

	//-----------------------------------------------------

	//編集ページ
	@RequestMapping("/user/{id}/edit")
	public String displayEdit(@PathVariable("id") Long id,Model model) {
		User user =userService.findById(id);

		model.addAttribute("userUpdateRequest",user);
		return "user/edit";
	}

	//編集エラー出力
	@RequestMapping(value="/user/{id}/createe",method=RequestMethod.POST)
	public String createe(@Validated @ModelAttribute UserUpdateRequest userUpdateRequest,BindingResult result,@PathVariable Long id,Model model) {

		//User user =userService.findById(id);

		if(result.hasErrors()) {
			List<String>errorList=new ArrayList<String>();
			for(ObjectError error:result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}

			model.addAttribute("validationError",errorList);
			return "user/edit";
		}
		userService.createe(userUpdateRequest);
		return "user/editCheck";
	}

	//編集確認ページ
	@RequestMapping(value="/user/{id}/editCheck" ,method=RequestMethod.POST)
	public String editCheck(@PathVariable Long id,@ModelAttribute UserUpdateRequest userUpdateRequest, Model model) {
		//User user =userService.findById(id);

		model.addAttribute("userUpdateRequest",  userUpdateRequest);
		return "user/editCheck";
	}

	//更新完了
		@RequestMapping(value = "/user/creatt", method = RequestMethod.POST)
		public String creatt(@Validated @ModelAttribute UserUpdateRequest userUpdateRequest,BindingResult result, Model model) {
			userService.creatt(userUpdateRequest);
			return String.format("redirect:/user/list");
		}

	//-----------------------------------------------------

	//削除ページ
		@RequestMapping("/user/{id}/delete")
		public String displaydelete(@PathVariable("id") Long id,Model model) {
			User user =userService.findById(id);

			model.addAttribute("userUpdateRequest",user);
			return "user/delete";
		}

	//更新完了
		@RequestMapping(value = "/user/dateDelete", method = RequestMethod.POST)
		public String dateDelete(@Validated @ModelAttribute UserUpdateRequest userUpdateRequest,BindingResult result, Model model) {
			userService.creattt(userUpdateRequest);
			return String.format("redirect:/user/list");
		}
}


