package com.ssafy.happy.model.dto;

public class User {
	private String userid;
	private String password;
	private String name;
	private String role;
	private String authtoken;
	
	public User() {
	}

	public User(String userid, String password, String name, String role, String authtoken) {
		super();
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.role = role;
		this.authtoken = authtoken;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAuthtoken() {
		return authtoken;
	}

	public void setAuthtoken(String authtoken) {
		this.authtoken = authtoken;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", password=" + password + ", name=" + name + ", role=" + role
				+ ", authtoken=" + authtoken + "]";
	}
	
	
}
