package com.revature.presentation;

import java.util.Scanner;

import com.revature.classes.Customer;
import com.revature.repo.BankImp;
import com.revature.repo.UserImp;
import com.revature.service.Service;

public class Menu {
	
	private void mainMenu() {
		System.out.println("1) Log in");
		System.out.println("2) Employee log in");
		System.out.println("3) New user");
		
	}
	
	private void employeeMenu() {
		System.out.println("1) View user data");
		System.out.println("2) Approve user");
		System.out.println("3) View transaction log");
		System.out.println("4) Log out");
	}
	
	public void customerMenu() {
		System.out.println("1) Check balance");
		System.out.println("2) Withdraw");
		System.out.println("3) Deposit");
		System.out.println("4) Internal transfer");
		System.out.println("5) External transfer");
		System.out.println("6) Open savings");
		System.out.println("7) Close account");
		System.out.println("8) Change username");
		System.out.println("9) Change password");
		System.out.println("10) Log out");
			
	}
	
	public void displayMain() {
		
		Service service = new Service();
		BankImp bi = new BankImp();
		UserImp ui = new UserImp();
		Scanner scanner = new Scanner(System.in);
		boolean runningMain = true;
		boolean runningLogin = true;
		
		
		
		while(runningMain) {
			
			mainMenu();
			
			String input1 = scanner.nextLine();
			
			switch(input1) {
			
			case "1":
				
				boolean loggedIn = false;
				
				while(runningLogin && !loggedIn) {
				
					System.out.println("Username");
					String usernameInput = scanner.nextLine(); 
					
					System.out.println("Password");
					String passwordInput = scanner.nextLine();
					
					
					
					try {
						loggedIn = service.login(usernameInput, passwordInput);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(loggedIn) {
						break;
					} else {
						System.out.println("Login insuccessful");
					}
				}
				
				while(loggedIn) {
					
					customerMenu();
				
					String input2 = scanner.nextLine();
					
					System.out.println("Input your pin");
					String pin = scanner.nextLine();
					
					switch(input2) {
					
					case "1":
						
						System.out.println("Account type");
						String typeTargetCb = scanner.nextLine();
						
						service.checkBalance(pin, typeTargetCb);
						
						break;
					
					case "2":
						
						System.out.println("Input amount");
						int amountWithdraw = scanner.nextInt();
						
						System.out.println("Input account type");
						String typeOriginWithdraw = scanner.nextLine();
						
						bi.withdraw(amountWithdraw, pin, typeOriginWithdraw);
						
						break;
						
					case "3": 
						
						System.out.println("Input amount");
						int amountDeposit = scanner.nextInt();
						
						System.out.println("Input account type");
						String typeTargetDeposit = scanner.nextLine();
						
						bi.deposit(amountDeposit, pin, typeTargetDeposit);
						
						break;
						
					case "4":
						
						System.out.println("Input amount");
						int amountIt = scanner.nextInt();
						
						System.out.println("Input account type to transfer from");
						String typeOriginIt = scanner.nextLine();
						
						System.out.println("Input account type to transfer to");
						String typeTargetIt = scanner.nextLine();
						
						bi.internalTransfer(amountIt, pin, typeOriginIt, typeTargetIt);
						
						break;
						
					case "5":
						
						System.out.println("Input amount");
						int amountEt = scanner.nextInt();
				
						System.out.println("Input the username of the account to be transfered into");
						String usernameTargetEt = scanner.nextLine();
						
						System.out.println("Input account type to transfer from");
						String typeOriginEt = scanner.nextLine();
						
						System.out.println("Input account type to transfer to");
						String typeTargetEt = scanner.nextLine();
						
						bi.externalTransfer(amountEt, pin, typeOriginEt, usernameTargetEt, typeTargetEt);
						
						break;
						
					case "6":
						
						System.out.println("Input opening balance");
						int openingBalance = scanner.nextInt();
						
						ui.openSavings(openingBalance, pin);
						
						break;
						
					case "7":
						
						System.out.println("Input username");
						String usernameInput = scanner.nextLine();
						
						System.out.println("Input Password");
						String passwordInput = scanner.nextLine();
						
						System.out.println("Account type to close");
						String typeTargetCo = scanner.nextLine();
						
						System.out.println("Type 'Confirm' to close account or 'Cancel' to go back");
						String confirmation = scanner.nextLine();
						
						if(confirmation == "Confirm") {
							
							Customer customer = new Customer();
						
							customer.setPin(pin);
							customer.setUser(usernameInput);
							customer.setPass(passwordInput);
							customer.setBalance(0);
							customer.setType(typeTargetCo);
							
							ui.closeAccount(customer);
						} else {
							System.out.println("Account closure cancelled");
						}
						break;
						
					case "8":
						
						System.out.println("Input new username");
						String newUsername = scanner.nextLine();
						
						ui.changeUsername(newUsername, pin);
						
						break;
						
					case "9":
						
						System.out.println("Input new password");
						String newPassword = scanner.nextLine();
						
						ui.changePassword(newPassword, pin);
						
						break;
						
					case "10":
						
						loggedIn = false;
						
						break;				
				}
				}
				
				break;
				
			case "2":
				
				boolean loggedInEmployee = false;
				
				while(runningLogin && !loggedInEmployee) {
				
					System.out.println("Username");
					String usernameInputEmployee = scanner.nextLine(); 
					
					System.out.println("Password");
					String passwordInputEmployee = scanner.nextLine();
					
					
					
					try {
						loggedInEmployee = service.login(usernameInputEmployee, passwordInputEmployee);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(loggedInEmployee) {
						break;
					} else {
						System.out.println("Login insuccessful");
					}
				}
			 
				while(loggedInEmployee) {
					
					employeeMenu();
					String input3 = scanner.nextLine();
					
					switch(input3) {
				
					case "1":
						
						System.out.println("Input customer pin");
						String pinCustomerRai = scanner.nextLine();
						
						service.retrieveAccountInfo(pinCustomerRai);
						
						break;
						
					case "2":
						
						System.out.println("Input customer pin");
						String pinCustomerApprove = scanner.nextLine();
						
						ui.approveUser(pinCustomerApprove);
						
						break;
						
					case "3":
						
						service.retrieveLog();
						
						break;
						
					case "4":
						
						loggedInEmployee = false;
						
						break;
					
				
				}
				}
				
				
			case "3":
				
			boolean status1 = false;
			String newPin = null;
			
			while(!status1) {
				
				System.out.println("Choose a pin");
				newPin = scanner.nextLine();
				if(service.authenticatePIN(newPin)) {
					System.out.println("Pin already in use");
				} else {
					status1 = true;
				}
				
			}
			
			boolean status2 = false;
			
			while(!status2) {
				
				System.out.println("Choose a username");
				String newUsername = scanner.nextLine();
				
				if(service.validate(newUsername)) {
					System.out.println("Pin already in use");
				} else {
					status2 = true;
				}
				
			}
		
			System.out.println("Choose a password");
			String newPassword = scanner.nextLine();
				
			System.out.println("Enter starting balance");
			int startingBalance = scanner.nextInt();
			
			
			Customer customer = new Customer();
			
			customer.setPin(newPin);
			customer.setUser(newPassword);
			customer.setPass(newPassword);
			customer.setBalance(startingBalance);
			customer.setType("checking");
			
			if(status1 && status2) {
				ui.addUser(customer);
			}
	
			}
			
			
			
		}
		
	}

}
