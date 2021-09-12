package com.revature.classes;

public class User {
	
	private int pin;
	private String username;
	private String password;
	private boolean isEmployee;
	//Constructors by username and password or by PIN
	
	public User() {
		
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.isEmployee = false;
	}
	public User(int pin) {
		this.pin = pin;
	}
	
	//Getters
	
	public int getPin() {
		return this.pin;
	}
	
	public String getUser() {
		return this.username;
	}
	
	public String getPass() {
		return this.password;
	}
	
	public boolean getEmployee() {
		return this.isEmployee;
	}
	//Setters
	
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	public void setUser(String username) {
		this.username = username;
	}
	
	public void setPass(String password) {
		this.password = password;
	}
	
	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
	
	

}
