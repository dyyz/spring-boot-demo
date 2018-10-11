package com.example.demo.user.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import com.example.demo.shiro.entity.UserInfo;
import com.example.demo.user.enumcommo.Sex;
import com.example.demo.util.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class UserDetails extends BaseEntity {
	private static final long serialVersionUID = -3059969960881883335L;

	@OneToOne
	@JsonIgnore
	private UserInfo userInfo;
	
	private String address;
	
	@Email
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	@Pattern(regexp ="1[34578]\\d{9}", message="手机格式不对")
	private String phone;
	
	@Min(18)
	private int age;

	public UserDetails() {
		super();
	}
	
	public UserDetails(UserInfo userInfo) {
		super();
		this.userInfo = userInfo;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
