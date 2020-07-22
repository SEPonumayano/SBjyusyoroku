package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dto.PageWrapper;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.dto.aGroup;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;


@Controller
@SessionAttributes("keyword")
public class UserController {
	@Autowired
	UserService userService;

	//一覧表示
	@RequestMapping (value = "/user/list",method = RequestMethod.GET)
	public String listpage(@Validated User user,BindingResult result,Model model, UserRequest userRequest,@PageableDefault(size=10)Pageable pageable) {

		//一覧取得
		UserRequest word =new UserRequest();
		String key=word.setKeyword(userRequest.getKeyword());

		Page<User> wordpage;

		if(key==null) {
			wordpage=userService.getfindAllCnt(pageable);
		}
		else {
			wordpage = userService.getsearchPoint(userRequest,pageable);
		}

		//電話番号ハイフン
		//Pattern p = Pattern.compile("(\\d{3})(\\d{4})(\\d{4})");
		//Matcher m =p.matcher(user.getTel());
		//String tel1=m.replaceAll("$1-$2-$3");

		//ページング
		PageWrapper<User> page = new PageWrapper<User>(wordpage, "/user/list");

		model.addAttribute("userlist", wordpage);
		model.addAttribute("page", page);
		//model.addAttribute("tel", tel1);
		model.addAttribute("keyword", key);
		model.addAttribute("words",page.getContent());

		return "/user/list";
	}

	//住所検索
	@RequestMapping(value ="/user/{keyword}/list" ,method = RequestMethod.POST)
	public String search(@ModelAttribute UserRequest userRequest,@Validated User user,BindingResult result, Model model,@PageableDefault(size=10)Pageable pageable ) {

		UserRequest word =new UserRequest();
		String keyword=word.setKeyword(userRequest.getKeyword());

		Page<User> seachpage=null;

		if(keyword.isEmpty()) {
			seachpage=userService.getfindAllCnt(pageable);
		}
		else {
			seachpage = userService.getsearchPoint(userRequest,pageable);
		}

		//Page<User> seachpage = userService.getsearchPoint(userRequest,pageable);
		PageWrapper<User> page = new PageWrapper<User>(seachpage, "/user/list");

		model.addAttribute("userlist",seachpage );
		model.addAttribute("keyword",keyword );
		model.addAttribute("page", page);
		model.addAttribute("words",page.getContent());

		return "user/list";
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
	public String create(@Validated(aGroup.class) @ModelAttribute UserRequest userRequest, BindingResult result, Model model) {
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
		String tel=user.getTel();

		//電話番号ハイフン
		Pattern p = Pattern.compile("(\\d{3})(\\d{4})(\\d{4})");
		Matcher m =p.matcher(tel);
		String tel1=m.replaceAll("$1-$2-$3");

		model.addAttribute("userUpdateRequest",user);
		model.addAttribute("tel1",tel1);
		return "user/edit";
	}

	//編集エラー出力
	@RequestMapping(value="/user/{id}/createe",method=RequestMethod.POST)
	public String createe(@Validated(aGroup.class) @ModelAttribute UserUpdateRequest userUpdateRequest,BindingResult result,@PathVariable Long id,Model model) {

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


