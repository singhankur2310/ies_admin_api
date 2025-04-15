package com.ankur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ankur.entities.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

	@Query("update UserEntity set accStatus=:status where userId=:userId")
	public Integer updateAccstatus(Integer userId, String status);

	public UserEntity findByEmail(String email);
}
