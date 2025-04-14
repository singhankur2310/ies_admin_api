package com.ankur.service;

import com.ankur.bindings.DashboardCards;
import com.ankur.bindings.LoginForm;

public interface UserService {
//String ya boolean dono return type ho skta hai

	public String login(LoginForm loginform);

	// String ya boolean dono return type ho skta hai
	public String recoverPwd(String email);

	public DashboardCards fetchdashboardinfo();

}
