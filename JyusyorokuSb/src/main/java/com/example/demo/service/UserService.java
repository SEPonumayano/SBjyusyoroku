package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserUpdateRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {
	//一覧情報
	@Autowired
	private UserRepository userRepository;

	public List<User> searchAll() {
		return userRepository.searchAll();
	}

	//総件数
	public int findAllCnt() {
		return userRepository.findAllCnt();
	}

	//ページング
	public List<User> findListPaging(int startIndex, int pageSize) {
		return userRepository.findListPaging(startIndex,pageSize);
	}

	//主キー
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	//検索
	public  List<User> searchpoint(String keyword) {
		List<User> result = userRepository.searchPoint(keyword);
		return result;
	}



	//-----------------------------------------------------

	//登録確認
	public void create(UserRequest userRequest) {
		User user = new User();
		user.setName(userRequest.getName());
		user.setAddress(userRequest.getAddress());
		user.setTel(userRequest.getTel());
		//userRepository.save(user);
	}

	//DB登録
	public void creat(UserRequest userRequest) {
		userRepository.save(CreateUser(userRequest));
	}

	//登録データ
	public User CreateUser(UserRequest userRequest) {
		User user = new User();
		user.setName(userRequest.getName());
		user.setAddress(userRequest.getAddress());
		String tel1=userRequest.getTel();
		String tel=tel1.replace("-","");
		user.setTel(tel);
		user.setDelete_flg("0");

		return user;
	}

	//---------------------------------------------------------

	//編集確認
	public void createe(UserUpdateRequest userUpdateRequest) {
		User user = new User();

	    //user.setId(userUpdateRequest.getId());
		user.setName(userUpdateRequest.getName());
		user.setAddress(userUpdateRequest.getAddress());
		user.setTel(userUpdateRequest.getTel());
	}

	//DB登録
		public void creatt(UserUpdateRequest userUpdateRequest) {
			User user=findById(userUpdateRequest.getId());

			user.setName(userUpdateRequest.getName());
			user.setAddress(userUpdateRequest.getAddress());
			String tel1=userUpdateRequest.getTel();
			String tel=tel1.replace("-","");
			user.setTel(tel);
			user.setDelete_flg("0");

			userRepository.save(user);
		}

	//-----------------------------------------------------

	//削除
		public void creattt(UserUpdateRequest userUpdateRequest) {
			User user=findById(userUpdateRequest.getId());

			user.setName(userUpdateRequest.getName());
			user.setAddress(userUpdateRequest.getAddress());
			String tel1=userUpdateRequest.getTel();
			String tel=tel1.replace("-","");
			user.setTel(tel);
			user.setDelete_flg("1");
			userRepository.save(user);
		}





}
