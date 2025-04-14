package com.ankur.service;

import java.util.List;

import com.ankur.bindings.PlanForm;

public interface PlanService {

	public boolean createPlan(PlanForm planform);

	public List<PlanForm> fetchPlans();

	public PlanForm getPlanById(Integer planId);

	// softDelete
	public String changePlanStatus(Integer planId, String status);
}
