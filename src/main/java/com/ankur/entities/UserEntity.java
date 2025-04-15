package com.ankur.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "IES_USERS")
@Data
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	private String fullName;
	private String email;
	private String pwd;
	private Long mobileNo;
	private String gender;
	private LocalDate dob;
	private Long ssn;
	private String accStatus;
	private String activeSw;

	private Integer roleId;

}
