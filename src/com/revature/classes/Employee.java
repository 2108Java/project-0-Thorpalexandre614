package com.revature.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employee extends User {
	
	String server = "localhost";
	String url = "jdbc:postgresql://" + server + "/postgres";
	String usernameServer = "postgres";
	String passwordServer = "Sixfourteen614";
	
	public boolean approveUser(String pin) {

		boolean status = false;
		
		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
			
			String approveUser = "UPDATE user_data SET approved = true WHERE pin = ?";
			
			PreparedStatement ps = connection.prepareStatement(approveUser);
			
			ps.setString(1, pin);
			
			ps.execute();
			
			status = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int getBalance() {
		return 0;
	}

	@Override
	public void setBalance(int amount) {
		System.out.println("Employees can not have a balance");
		
	}

}
