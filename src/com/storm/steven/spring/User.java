package com.storm.steven.spring;

public class User {
	private int Id;
	private String username;
	private int age;
	public User() {
	}
	public User(int id, String username, int age) {
		super();
		Id = id;
		this.username = username;
		this.age = age;
	}
	public User(String username, int age) {
		super();
		this.username = username;
		this.age = age;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "User [Id=" + Id + ", username=" + username + ", age=" + age + "]";
	} 	
}
