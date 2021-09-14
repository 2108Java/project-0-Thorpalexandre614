package com.revature.classes;

public class User {
	
	private String pin;
	private String username;
	private String password;
	private int balance;
	private boolean isEmployee;
	//Constructors by username and password or by PIN
	
	public User() {
		
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.isEmployee = false;
	}
	public User(String pin) {
		this.pin = pin;
	}
	
	//Getters
	
	public String getPin() {
		return this.pin;
	}
	
	public String getUser() {
		return this.username;
	}
	
	public String getPass() {
		return this.password;
	}
	
	public int getBalance() {
		return this.balance;
	}
	
	public boolean getEmployee() {
		return this.isEmployee;
	}
	//Setters
	
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	public void setUser(String username) {
		this.username = username;
	}
	
	public void setPass(String password) {
		this.password = password;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
	
	

}
