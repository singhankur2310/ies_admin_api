package com.ankur.bindings;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DashboardCards {

	private Long plansCnt;
	private Long approvedCnt;
	private Long deniedCnt;
	private Double beniftAmtGiven;

	private UserAccountForm user;
}
