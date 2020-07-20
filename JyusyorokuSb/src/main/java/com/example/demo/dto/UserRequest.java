package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//エラー表示内容
public class UserRequest implements Serializable {

	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public String setKeyword(String keyword) {
		return this.keyword = keyword;
	}


	//名前エラー
	@NotEmpty(groups={aGroup.class},message = "名前は必須項目です")
	@Size(groups={aGroup.class},max = 20, message = "名前は全角20文字以内で入力してください")
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//住所エラー
	@NotEmpty(groups={aGroup.class},message = "住所は必須項目です")
	@Size(groups={aGroup.class},max = 20, message = "住所は全角40文字以内で入力してください")
	private String address;
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	//電話番号エラー/空文字のエラー残ってる
	//@Pattern(groups={aGroup.class},regexp ="^[0-9]{3}-[0-9]{4}-[0-9]{4}$" , message="電話番号は「000-0000-0000」の形式で入力してください")
	private String tel;
	@AssertTrue(groups={aGroup.class}, message="電話番号は「000-0000-0000」の形式で入力してください")
	public boolean getValid() {
		if(tel.isEmpty()) {
			return true;
		}
		else if(tel.matches("^[0-9]{3}-[0-9]{4}-[0-9]{4}$")) {
			return true;
		}
		return false;
	}


	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel=tel;
	}



}
