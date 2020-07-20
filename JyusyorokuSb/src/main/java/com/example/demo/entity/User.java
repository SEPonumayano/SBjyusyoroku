package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "jyusyoroku")
public class User implements Serializable {

	//DBの情報引き出す用
	private static final long serialVersionUID = -870708489937857961L;

	//id generator="seqTable"
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@TableGenerator(name="seqTable", table="seq_table", pkColumnName="seq_name", pkColumnValue="word_seq", valueColumnName="seq_value")
	@Column(name = "id")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	//名前
	@Column(name = "name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//住所
	@Column(name = "address")
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	//電話番号
	@Column(name = "tel")
	private String tel;

	//ハイフン入れ不要
	//Pattern p = Pattern.compile("(\\d{3})(\\d{4})(\\d{4})");
	//Matcher m =p.matcher(tel);
	//Matcher m =p.matcher(rs.getString("tel"));
	//String tel1=m.replaceAll("$1-$2-$3");


	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	//削除プラグ
	@Column(name = "delete_flg")
	private String delete_flg;


	public String getDelete_flg() {
		return delete_flg;
	}

	public void setDelete_flg(String delete_flg) {
		this.delete_flg = delete_flg;
	}

}
