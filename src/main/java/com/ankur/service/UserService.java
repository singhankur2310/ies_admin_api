package com.ankur.service;

import com.ankur.bindings.DashboardCards;
import com.ankur.bindings.LoginForm;
import com.ankur.bindings.UserAccountForm;

public interface UserService {
//String ya boolean dono return type ho skta hai

	public String login(LoginForm loginform);

	// String ya boolean dono return type ho skta hai
	public boolean recoverPwd(String email);

	public UserAccountForm getUserByEmail(String email);

	public DashboardCards fetchdashboardinfo();

}
