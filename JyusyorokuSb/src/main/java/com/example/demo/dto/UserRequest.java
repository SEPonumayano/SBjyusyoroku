package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//エラー表示内容
public class UserRequest implements Serializable {

	private String name;
	private String address;
	private String tel;

	//名前エラー
	@NotEmpty(message = "名前は必須項目です")
	@Size(max = 20, message = "名前は全角20文字以内で入力してください")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//住所エラー
	@NotEmpty(message = "住所は必須項目です")
	@Size(max = 20, message = "住所は全角40文字以内で入力してください")
	//private String address;
    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	//電話番号エラー/空文字のエラー残ってる
	@Pattern(regexp ="^[0-9]{3}-[0-9]{4}-[0-9]{4}$" , message="電話番号は「000-0000-0000」の形式で入力してください")
	//private String tel;
    public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel=tel;
	}



}
