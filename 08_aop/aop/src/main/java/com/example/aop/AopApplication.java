package com.example.aop;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.aop.dao.AccountDAO;
import com.example.aop.entity.Account;

@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(AccountDAO dao) {
		
		return runner -> {
			
			aroundDemo(dao);
		};
	}

	private void beforeAdviceDemo(AccountDAO dao) {
		
		Account account = new Account();
		
		System.out.println("First time call");
		dao.addAccount();
		
		System.out.println("Second time call");
		dao.addAccount(account);
		
		System.out.println("Third time call");
		dao.addAccount(account, true);
	}
	
	private void beforeAdviceDemoV2(AccountDAO dao) {
		
		Account account = new Account();
		
		System.out.println("First time call");
		dao.addAccount();
		
		System.out.println("Second time call");
		dao.addAccount(account);
		
		System.out.println("Third time call");
		dao.addAccount(account, true);
		
		dao.setName("John Doe");
		dao.setIsVip(true);
		
		String name = dao.getName();
		Boolean isVip = dao.getIsVip();
		
		System.out.println("\n---- Account Info ----\n");
		System.out.println("Name : " + name);
		System.out.println("VIP : " + isVip);
	}
	
	private void beforeAdviceDemoV3(AccountDAO dao) {
		
		Account account = new Account("Richard James", Account.Level.SIVLER);
		
		dao.addAccount(account, true);
	}
	
	private void afterReturningDemo(AccountDAO dao) {
		
		List<Account> accounts = dao.findAccounts();
		
		for (Account account : accounts) {
			System.out.println("The modified result: " + account);
		}
		System.out.println();
	}
	
	private void afterThrowingDemo(AccountDAO dao) {
		
		try {
			List<Account> accounts = dao.findAccounts(true);
			System.out.println(accounts);
		} 
		catch (Exception exc) {
			System.out.println("Handling an error...");
		}
	}
	
	private void afterDemo(AccountDAO dao) {
		
		afterReturningDemo(dao);
		afterThrowingDemo(dao);
	}
	
	private void aroundDemo(AccountDAO dao) {
		
		System.out.println("\n---- Main Application ----");
		
		int number = dao.getRandomNumber(true);
		System.out.println("Number: " + number);
		
		System.out.println("---- Main Application ----\n");
	}
	
	private void aroundHandleExceptionDemo(AccountDAO dao) {
		
		System.out.println("\n---- Main Application ----");
		
		int number = dao.getRandomNumber(true);
		System.out.println("Number: " + number);
		
		System.out.println("---- Main Application ----\n");
	}
}
