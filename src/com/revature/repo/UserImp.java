package com.revature.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.classes.User;

public class UserImp implements UserDAO {
	
	String server = "localhost";
	String url = "jdbc:postgresql://" + server + "/postgres";
	String usernameServer = "postgres";
	String passwordServer = "Sixfourteen614";

	@Override
	public boolean addUser(User newUser) {
		
		boolean status = false;
		
		try(Connection connection = DriverManager.getConnection(url,usernameServer,passwordServer)){
			
			String addUser = "INSERT INTO user_data(pin, username, pass, balance, account_type) VALUES(?, ?, ?, ?, ?)";
			
			PreparedStatement ps = connection.prepareStatement(addUser);
			
			ps.setString(1, newUser.getPin());
			ps.setString(2, newUser.getUser());
			ps.setString(3, newUser.getPass());
			ps.setInt(4, newUser.getBalance());
			ps.setString(5, newUser.getType());
			
			ps.execute();
			
			status = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public boolean openSavings(int openingBalance, String pin) {
		boolean status1 = false;
		
		boolean statusFinal = false;
		
		
		try(Connection connection = DriverManager.getConnection(url,usernameServer,passwordServer)){
			
			//Check if the user has an account already before they can make another
			
			String checkPin = "SELECT * FROM user_data WHERE pin = ?";
			
			PreparedStatement ps1 = connection.prepareStatement(checkPin);
			
			ps1.setString(1, pin);
			
			ResultSet rs = ps1.executeQuery();
			
			String username = null;
			String password = null;
			String account = null;
			boolean approved = false;
			
			while(rs.next()) {
				username = rs.getString("username");
				password = rs.getString("pass");
				account = rs.getString("account_type");
				approved = rs.getBoolean("approved");
			}
			
			if(approved) {
				
		
				String openAccount = "INSERT INTO user_data(pin, username, pass, balance, account_type) VALUES(?,?,?,?,'savings')";
				
				PreparedStatement ps2 = connection.prepareStatement(openAccount);
				
				ps2.setString(1, pin);
				ps2.setString(2, username);
				ps2.setString(3, password);
				ps2.setInt(4, openingBalance);
				
				ps2.execute();
				
				status1 = true;
			}

			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return status1;
	}

	@Override
	public boolean closeAccount(User username) {

		boolean status = false;
		
		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
			
			String closeAccount = "DELETE FROM user_data WHERE pin = ?";
			
			PreparedStatement ps = connection.prepareStatement(closeAccount);
			
			ps.setString(1, username.getPin());
			
			ps.execute();		
			
			status = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean changeUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePassword(String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
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
	public boolean makeEmployee(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}
