package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {
	//一覧情報
	@Autowired
	private UserRepository userRepository;

	public List<User> searchAll() {
		return userRepository.findAll();
	}

	//登録確認
	public void create(UserRequest userRequest) {
		User user = new User();
		user.setName(userRequest.getName());
		user.setAddress(userRequest.getAddress());
		user.setTel(userRequest.getTel());
		//userRepository.save(user);
	}

	//登録
	public void creat(UserRequest userRequest) {
		User user = new User();
		user.setName(userRequest.getName());
		user.setAddress(userRequest.getAddress());
		user.setTel(userRequest.getTel());
		userRepository.save(user);
	}


}
