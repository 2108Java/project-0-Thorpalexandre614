package com.revature.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction implements TransactionInterface{
	
	String server = "localhost";
	String url = "jdbc:postgresql://" + server + "/postgres";
	String username = "postgres";
	String password = "Sixfourteen614";
	
	
	public boolean withdraw(int amount, String pin) {

		boolean status1 = false;
		boolean status2 = false;
		boolean status3 = false;
		boolean statusFinal = false;
		
		try(Connection connection = DriverManager.getConnection(url, username, password)){
			
			String balanceQuery = "SELECT balance FROM user_data WHERE pin = ?";
			
			PreparedStatement ps1 = connection.prepareStatement(balanceQuery);

			ps1.setString(1, pin);
			
			
			ResultSet rs = ps1.executeQuery();
			
			status1 = true;
			
			int balance = 0;
			int newBalance = 0;
			
			while(rs.next()) {
				 balance = rs.getInt("balance");
				 newBalance = (balance - amount);
			}
			
			String inputQuery = "INSERT INTO transaction_log(transaction_type, transfer_origin, "
					+ "transfer_target, balance_origin, balance_target, approved) "
					+ "VALUES('withdrawl', ?, ?, ?, ?, false)";
			
			PreparedStatement ps2 = connection.prepareStatement(inputQuery);
			
			ps2.setString(1, pin);
			ps2.setString(2, pin);
			ps2.setInt(3, balance);
			ps2.setInt(4, newBalance);
			
			ps2.execute();
			
			status2 = true;
			
			String balanceUpdate = "UPDATE user_data SET balance = ? WHERE pin = ? ";
			
			PreparedStatement ps3 = connection.prepareStatement(balanceUpdate);
			
			ps3.setInt(1, newBalance);
			ps3.setString(2, pin);
			
			ps3.execute();
			
			status3 = true;
			
			if(status1 && status2 && status3) {
				statusFinal = true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return statusFinal;
	}

	
	public boolean deposit(int amount, String pin) {
		
		return false;
	}

	
	public boolean transfer(int amount, String transactionOrigin, String transactionTarget) {
		
		return false;
	}

}
