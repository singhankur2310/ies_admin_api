package com.ankur.bindings;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UnlockAccountForm {

	private String email;
	private String tempPwd;
	private String newPwd;
	private String confirPwd;
}
