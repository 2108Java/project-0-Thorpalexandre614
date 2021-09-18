package com.revature.classes;

public abstract class User {
	
	private String pin;
	private String username;
	private String password;
	private int balance;
	private String accountType;
	//Constructors by username and password or by PIN
	
	public User() {
		
	}
	public User(String pin, String username, String password, String type) {
		this.username = username;
		this.password = password;
		this.accountType = type;
		this.balance = 0;
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
	
	public abstract int getBalance();
	
	public String getType() {
		return this.accountType;
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
	
	public abstract void setBalance(int amount);

	public void setType(String accountType) {
		this.accountType = accountType;
	}
	
	
	
	
	

}
