package com.ankur.bindings;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlanForm {
	private String planCategory;
	private String planName;
	private LocalDate planStartDate;
	private LocalDate planEndDate;

}
