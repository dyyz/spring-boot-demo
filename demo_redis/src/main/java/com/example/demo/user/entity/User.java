package com.example.demo.user.entity;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 3196455615668971788L;
	private String name;
	private String nickName;
	private int age;
	private Long id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
