package com.ankur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ankur.entities.PlanEntity;

@Repository
public interface PlanRepo extends JpaRepository<PlanEntity, Long> {

}
