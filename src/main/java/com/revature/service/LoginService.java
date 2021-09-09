package com.revature.service;

public interface LoginService {
	
	public boolean validate(String username);
	public boolean login(String username, String password);
	public boolean authenticatePIN(int pin);

}
