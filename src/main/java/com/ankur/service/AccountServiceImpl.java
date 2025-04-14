package com.ankur.service;

import java.util.List;

import com.ankur.bindings.UnlockAccountForm;
import com.ankur.bindings.UserAccountForm;

public class AccountServiceImpl implements AccountService {

	@Override
	public boolean createUserAccount(UserAccountForm accForm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserAccountForm> fetchUserAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserAccountForm getUserAccById(Integer accId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String changeAccStatus(Integer accId, String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String unlockAccount(UnlockAccountForm unlockAccForm) {
		// TODO Auto-generated method stub
		return null;
	}

}
