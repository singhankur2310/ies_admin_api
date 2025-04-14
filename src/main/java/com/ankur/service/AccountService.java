package com.ankur.service;

import java.util.List;

import com.ankur.bindings.UnlockAccountForm;
import com.ankur.bindings.UserAccountForm;

public interface AccountService {

	public boolean createUserAccount(UserAccountForm accForm);

	public List<UserAccountForm> fetchUserAccounts();

	public UserAccountForm getUserAccById(Integer accId);

	public String changeAccStatus(Integer accId, String status);

	public String unlockAccount(UnlockAccountForm unlockAccForm);

}
