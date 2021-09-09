package Classes;

public class User {
	
	private boolean isEmployee;
	private String username;
	private int pin;
	private String password;
	
	//Constructors by username and password or PIN
	
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
	
	public boolean getEmployee() {
		return this.isEmployee;
	}
	
	//Setters
	
	public void setEmployee() {
		this.isEmployee = true;
	}

}
