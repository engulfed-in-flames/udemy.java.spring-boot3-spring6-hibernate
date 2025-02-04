package com.example.aop.dao;

import java.util.List;

import com.example.aop.entity.Account;

public interface AccountDAO {

	public String getName();
	
	public void setName(String name);

	public Boolean getIsVip();

	public void setIsVip(Boolean isVip);
	
	void addAccount();
	
	void addAccount(Account account);
	
	void addAccount(Account account, boolean isBoolean);
	
	List<Account> findAccounts();
	 
	List<Account> findAccounts(Boolean throwError);
	 
	int getRandomNumber();
	
	int getRandomNumber(Boolean throwError);
}
