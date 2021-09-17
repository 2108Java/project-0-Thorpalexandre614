package com.revature.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.revature.classes.TransactionObject;
import com.revature.service.Service;

public class BankImp implements BankDAO {
	
	String server = "localhost";
	String url = "jdbc:postgresql://" + server + "/postgres";
	String usernameServer = "postgres";
	String passwordServer = "Sixfourteen614";

	@Override
	public boolean withdraw(int amount, String pin, String typeOrigin) {

		Service service = new Service();
		boolean status1 = false;
		boolean status2 = false;		
		
		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
			
			int balance = service.checkBalance(pin, typeOrigin);
			boolean approved = service.checkAccountStatus(pin, typeOrigin);
			
			int newBalance = 0;
			
			if(approved) {
				if(balance > amount) {
					newBalance = (balance - amount);
					status1 = true;
				}else {
					 
					System.out.println("Insufficient Funds");
					
				}
			}else {
				System.out.println("Account has not been approved.");
			}
			if(status1) {
				
				TransactionObject transaction = new TransactionObject("withdrawl", pin, typeOrigin, "external", "external", amount);
			
				status2 = service.logTransaction(transaction);
				//Update the balance with new amount
				String balanceUpdate = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
				
				PreparedStatement ps3 = connection.prepareStatement(balanceUpdate);
			
				ps3.setInt(1, newBalance);
				ps3.setString(2, pin);
				ps3.setString(3, typeOrigin);
			
				ps3.execute();

			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return status2;

	}

	@Override
	public boolean deposit(int amount, String pin, String typeTarget) {

		Service service = new Service();
		boolean status1 = false;
		boolean status2 = false;
		
		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
			
			int balance = service.checkBalance(pin, typeTarget);
			boolean approved = service.checkAccountStatus(pin, typeTarget);			
			int newBalance = 0;
			
			if(approved) {
				newBalance = (balance + amount);	
			
				status1 = true;
			}else {
				System.out.println("Account has not been approved.");
			}
			
			if(status1) {
				
				TransactionObject transaction = new TransactionObject("deposit", "external", "external", pin, typeTarget, amount);
				
				status2 = service.logTransaction(transaction);
			
				String balanceUpdate = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
			
				PreparedStatement ps3 = connection.prepareStatement(balanceUpdate);
			
				ps3.setInt(1, newBalance);
				ps3.setString(2, pin);
				ps3.setString(3, typeTarget);
			
				ps3.execute();
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return status2;
	}
	
	@Override
	public boolean internalTransfer(int amount, String pin, String typeOrigin, String typeTarget){

		Service service = new Service();
		boolean status1 = false;
		boolean status2 = false;
		boolean status3 = false;
		
		
		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
			
			boolean originApproved = service.checkAccountStatus(pin, typeOrigin);
			int originBalance = service.checkBalance(pin, typeOrigin);
			boolean targetApproved = service.checkAccountStatus(pin, typeTarget);
			int targetBalance = service.checkBalance(pin, typeTarget);
			
			int newBalanceOrigin = 0;
			int newBalanceTarget = 0;
				
			if(originApproved && targetApproved) {
				if(originBalance >= amount) {
					newBalanceOrigin = originBalance - amount;
					newBalanceTarget = targetBalance + amount;
					status1 = true;
				}else {
						System.out.println("Insufficient funds.");
				}
				status2 = true;
			}else {
				System.out.println("Account has not been approved.");
				
				
				
				if(status1 && status2) {
					
					TransactionObject transaction = new TransactionObject("internal transfer", pin, typeOrigin, "internal", typeTarget, amount);
					status3 = service.logTransaction(transaction);
				
					String balanceUpdateOrigin = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
					
					PreparedStatement ps4 = connection.prepareStatement(balanceUpdateOrigin);
					
					ps4.setInt(1, newBalanceOrigin);
					ps4.setString(2, pin);
					ps4.setString(3, typeOrigin);
					
					ps4.execute();
					
					String balanceUpdateTarget = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
					
					PreparedStatement ps5 = connection.prepareStatement(balanceUpdateTarget);
					
					ps5.setInt(1, newBalanceTarget);
					ps5.setString(2, pin);
					ps5.setString(3, typeTarget);
					
					ps5.execute();
					
				}
			}
			

			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return status3;
	}

	@Override
	public boolean externalTransfer(int amount, String pinOrigin, String typeOrigin, String pinTarget, String typeTarget) {
		
		Service service = new Service();
		boolean status1 = false;
		boolean status2 = false;
		boolean status3 = false;
		
		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
			
			boolean originApproved = service.checkAccountStatus(pinOrigin, typeOrigin);
			int originBalance = service.checkBalance(pinOrigin, typeOrigin);
			boolean targetApproved = service.checkAccountStatus(pinTarget, typeTarget);
			int targetBalance = service.checkBalance(pinTarget, typeTarget);
		
			int newBalanceOrigin = 0;
			int newBalanceTarget = 0;
			
			if(originApproved && targetApproved) {
				if(originBalance >= amount) {
					newBalanceOrigin = originBalance - amount;
					newBalanceTarget = targetBalance + amount;
					status1 = true;
				}else {
						System.out.println("Insufficient funds.");
				}
				status2 = true;
			}else {
				System.out.println("Account has not been approved.");
			}	
				
			if(status1 && status2) {	
				
				TransactionObject transaction = new TransactionObject("external transfer", pinOrigin, typeOrigin, pinTarget, typeTarget, amount);
				status3 = service.logTransaction(transaction);
			
				String balanceUpdateOrigin = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
				
				PreparedStatement ps4 = connection.prepareStatement(balanceUpdateOrigin);
				
				ps4.setInt(1, newBalanceOrigin);
				ps4.setString(2, pinOrigin);
				ps4.setString(3, typeOrigin);
				
				ps4.execute();
				
				String balanceUpdateTarget = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
				
				PreparedStatement ps5 = connection.prepareStatement(balanceUpdateTarget);
				
				ps5.setInt(1, newBalanceTarget);
				ps5.setString(2, pinTarget);
				ps5.setString(3, typeTarget);
				
				ps5.execute();
	
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return status3;
	}

	
	//move over to the userDAO
	
//	@Override
//	public boolean addUser(User newUser) {
//		// TODO Auto-generated method stub
//		boolean status = false;
//		
//		try(Connection connection = DriverManager.getConnection(url,usernameServer,passwordServer)){
//			
//			String addUser = "INSERT INTO user_data(pin, username, pass, balance, account_type) VALUES(?, ?, ?, ?, ?)";
//			
//			PreparedStatement ps = connection.prepareStatement(addUser);
//			
//			ps.setString(1, newUser.getPin());
//			ps.setString(2, newUser.getUser());
//			ps.setString(3, newUser.getPass());
//			ps.setInt(4, newUser.getBalance());
//			ps.setString(5, newUser.getType());
//			
//			ps.execute();
//			
//			status = true;
//			
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return status;
//	}
//
//	@Override
//	public boolean openSavings(int openingBalance, String pin) {
//		boolean status1 = false;
//		
//		boolean statusFinal = false;
//		
//		
//		try(Connection connection = DriverManager.getConnection(url,usernameServer,passwordServer)){
//			
//			//Check if the user has an account already before they can make another
//			
//			String checkPin = "SELECT * FROM user_data WHERE pin = ?";
//			
//			PreparedStatement ps1 = connection.prepareStatement(checkPin);
//			
//			ps1.setString(1, pin);
//			
//			ResultSet rs = ps1.executeQuery();
//			
//			String username = null;
//			String password = null;
//			String account = null;
//			boolean approved = false;
//			
//			while(rs.next()) {
//				username = rs.getString("username");
//				password = rs.getString("pass");
//				account = rs.getString("account_type");
//				approved = rs.getBoolean("approved");
//			}
//			
//			if(approved) {
//				
//		
//				String openAccount = "INSERT INTO user_data(pin, username, pass, balance, account_type) VALUES(?,?,?,?,'savings')";
//				
//				PreparedStatement ps2 = connection.prepareStatement(openAccount);
//				
//				ps2.setString(1, pin);
//				ps2.setString(2, username);
//				ps2.setString(3, password);
//				ps2.setInt(4, openingBalance);
//				
//				ps2.execute();
//				
//
//			}
//
//			
//		} catch (SQLException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		return statusFinal;
//	}
//	
//	@Override
//	public boolean closeAccount(User username) {
//		
//		boolean status = false;
//		
//		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
//			
//			String closeAccount = "DELETE FROM user_data WHERE pin = ?";
//			
//			PreparedStatement ps = connection.prepareStatement(closeAccount);
//			
//			ps.setString(1, username.getPin());
//			
//			ps.execute();		
//			
//			status = true;
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return status;
//	}
//
//	@Override
//	public boolean approveUser(String pin) {
//		
//		boolean status = false;
//		
//		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
//			
//			String approveUser = "UPDATE user_data SET approved = true WHERE pin = ?";
//			
//			PreparedStatement ps = connection.prepareStatement(approveUser);
//			
//			ps.setString(1, pin);
//			
//			ps.execute();
//			
//			status = true;
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return status;
//	}

}
