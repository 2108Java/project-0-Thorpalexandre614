package com.revature.presentation;

import java.util.Scanner;

import com.revature.service.Service;

public class Menu {
	
	private void mainMenu() {
		System.out.println("1) Log in");
		System.out.println("2) Employee log in");
		
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
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		
		
		
		while(running) {
			
			mainMenu();
			
			String input1 = scanner.nextLine();
			
			switch(input1) {
			
			case "1":
				
				System.out.println("Username");
				
				String usernameInput = scanner.nextLine(); 
				
				System.out.println("Password");
				
				String passwordInput = scanner.nextLine();
				
	//			boolean success = Service.login(usernameInput, passwordInput);
				
				customerMenu();
				
				if(success) {
				
					String input2 = scanner.nextLine();
					
					switch(input2) {
					
					case "1":
						
						break;
						
					case "2":
						
						break;
						
					case "3": 
						
						break;
						
					case "4":
						
						break;
						
					case "5":
						
						break;
						
					case "6":
						
						break;
						
					case "7":
						
						break;
						
					case "8":
						
						break;
						
					case "9":
						
						break;
						
					case "10":
						
						break;
						
				} else {
					System.out.println("Login unsuccessful");
				}
				
				}
				
				break;
				
			case "2":
				
				//Add login section 
				
				switch(sucess) {
				
				case "1":
					
					break;
					
				case "2":
					
					break;
					
				case "3":
					
					break;
					
				case "4":
					
					break;
					
				
				}
			
			}
			
			
			
		}
		
	}

}
