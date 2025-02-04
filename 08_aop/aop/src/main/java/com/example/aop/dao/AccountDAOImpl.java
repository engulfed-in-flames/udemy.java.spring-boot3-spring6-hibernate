package com.example.aop.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.example.aop.entity.Account;

// Repository is a sub annotation of @Component.
@Repository
public class AccountDAOImpl implements AccountDAO {

	String name;
	Boolean isVip;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsVip() {
		return isVip;
	}

	public void setIsVip(Boolean isVip) {
		this.isVip = isVip;
	}

	@Override
	public void addAccount() {
		
		System.out.println(getClass().getSimpleName() + " (0) : DOING SOME STUFFS\n");
	}

	@Override
	public void addAccount(Account account) {
		
		System.out.println(getClass().getSimpleName() + " (1) : DOING SOME STUFFS\n");
	}

	@Override
	public void addAccount(Account account, boolean isBoolean) {

		System.out.println(getClass().getSimpleName() + " (2) : DOING SOME STUFFS\n");
	}

	@Override
	public List<Account> findAccounts() {
		
		System.out.println("---- Method Body ----");
		List<Account> accounts = new ArrayList<Account>();
		Account account1 = new Account("John Doe", Account.Level.VIP);
		Account account2 = new Account("Mary Jane", Account.Level.PLATINUM);
		
		accounts.add(account1);
		accounts.add(account2);
		
		for (Account account : accounts) {
			System.out.println("The created account: " + account);
		}
		System.out.println("---- Method Body ----\n");
		
		return accounts;
	}

	@Override
	public List<Account> findAccounts(Boolean throwError) {
		
		if (throwError) {
			throw new RuntimeException("Oops! There is an error.");
		}
		
		return findAccounts();
	}

	@Override
	public int getRandomNumber() {
		
		Random random = new Random();
		int number = random.nextInt();
		
		return number;
	}

	@Override
	public int getRandomNumber(Boolean throwError) {
		
		if (throwError) {
			throw new RuntimeException("Oops! Something is wrong.");
		}
		
		return getRandomNumber();
	}
}
