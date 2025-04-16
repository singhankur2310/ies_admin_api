package com.ankur.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ankur.bindings.UserAccountForm;
import com.ankur.service.AccountService;

@RestController
public class AccountRestController {

	private Logger logger = LoggerFactory.getLogger(AccountRestController.class);

	@Autowired
	private AccountService accService;

	@PostMapping("/user")
	public ResponseEntity<String> createAccount(@RequestBody UserAccountForm userAccForm) {
		logger.debug("Account Creation Process Started ...");

		boolean status = accService.createUserAccount(userAccForm);
		logger.debug("Account creation Process Completed...");

		if (status) {
			logger.info("Account crerated Successfully...");
			return new ResponseEntity<>("Account Created", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Account Creation Failed...", HttpStatus.INTERNAL_SERVER_ERROR);// 500
		}

	}

	@GetMapping("/users")
	public ResponseEntity<List<UserAccountForm>> getUsers() {
		logger.debug("Fetching User Account Process started...");

		List<UserAccountForm> userAccForm = accService.fetchUserAccounts();

		logger.info("User Account fetch Successfully ....");

		return new ResponseEntity<List<UserAccountForm>>(userAccForm, HttpStatus.OK);

	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<UserAccountForm> getUser(@PathVariable("userId") Long userId) {
		logger.debug("user account fetch is process...");
		UserAccountForm userAccById = accService.getUserAccById(userId);
		logger.info("User Account Fetch successfully....");
		return new ResponseEntity<>(userAccById, HttpStatus.OK);
	}

	@PutMapping("/user/{userId}/{status}")
	public ResponseEntity<List<UserAccountForm>> updateUserAcc(@PathVariable("userId") Integer userId,
			@PathVariable("status") String status) {

		logger.debug("user account update process started");

		accService.changeAccStatus(userId, status);

		logger.debug("user account status update process completed ....");

		List<UserAccountForm> userAccForm = accService.fetchUserAccounts();

		logger.info("user account status updated successfully.....");

		return new ResponseEntity<>(userAccForm, HttpStatus.OK);
	}

}
