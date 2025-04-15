package com.ankur.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ankur.bindings.UnlockAccountForm;
import com.ankur.bindings.UserAccountForm;
import com.ankur.entities.UserEntity;
import com.ankur.repository.UserRepo;
import com.ankur.utils.EmailUtils;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public boolean createUserAccount(UserAccountForm accForm) {
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(accForm, entity);

		// Set Random pwd
		entity.setPwd(genratedPwd());
		entity.setAccStatus("LOCKED");
		entity.setAccStatus("Y");
		userRepo.save(entity);

		// send email
		String subject = "User Registration";
		String body = readEmailBody("REG_EMAIL_BODY.txt", entity);
		return emailUtils.sendEmail(subject, body, accForm.getEmail());
	}

	@Override
	public List<UserAccountForm> fetchUserAccounts() {
		List<UserEntity> userEntities = userRepo.findAll();
		List<UserAccountForm> users = new ArrayList<UserAccountForm>();

		for (UserEntity userEntity : userEntities) {
			UserAccountForm user = new UserAccountForm();
			BeanUtils.copyProperties(userEntities, users);
			users.add(user);
		}
		return users;
	}

	@Override
	public UserAccountForm getUserAccById(Long accId) {
		Optional<UserEntity> optional = userRepo.findById(accId);
		if (optional.isPresent()) {
			UserEntity userEntity = optional.get();
			UserAccountForm user = new UserAccountForm();
			BeanUtils.copyProperties(userEntity, user);
			return user;
		}
		return null;
	}

	@Override
	public String changeAccStatus(Integer accId, String status) {
		Integer cnt = userRepo.updateAccstatus(accId, status);
		if (cnt > 0) {
			return "Status Changed";
		}
		return "Failed to Change";
	}

	@Override
	public String unlockAccount(UnlockAccountForm unlockAccForm) {
		UserEntity entity = userRepo.findByEmail(unlockAccForm.getEmail());

		entity.setPwd(unlockAccForm.getNewPwd());
		entity.setAccStatus("UNLOCKED");
		userRepo.save(entity);
		return "Account Unlocked";
	}

	// genratedPwd()----
	private String genratedPwd() {
		String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";

		// combine all String
		String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;

		// create random String builder

		StringBuilder sb = new StringBuilder();

		// create an object of random class

		Random random = new Random();

		// specific length of random String
		int length = 6;

		for (int i = 0; i < length; i++) {
			// generate random index number
			int index = random.nextInt(alphaNumeric.length());

			// get character specified by index
			// from the string
			char randomChar = alphaNumeric.charAt(index);
			// append the character to StringBuilder

		}
		return sb.toString();
	}

	private String readEmailBody(String fileNAme, UserEntity user) {
		StringBuilder sb = new StringBuilder();
		try (Stream<String> lines = Files.lines(Paths.get(fileNAme))) {
			lines.forEach(line -> {
				line = line.replace("${FNAME}", user.getFullName());
				line = line.replace("${TEMP_PWD}", user.getPwd());
				line = line.replace("${EMAIL}", user.getEmail());
				sb.append(line);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
