package com.ankur.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ankur.bindings.DashboardCards;
import com.ankur.bindings.LoginForm;
import com.ankur.bindings.UserAccountForm;
import com.ankur.constraints.AppConstant;
import com.ankur.entities.EligEntity;
import com.ankur.entities.UserEntity;
import com.ankur.repository.EligRepo;
import com.ankur.repository.PlanRepo;
import com.ankur.repository.UserRepo;
import com.ankur.utils.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private EligRepo eligRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String login(LoginForm loginform) {
		UserEntity entity = userRepo.findByEmailAndPwd(loginform.getEmail(), loginform.getPwd());

		if (entity == null) {
			return "invalid credinatial";
		}

		if (AppConstant.Y_STR.equals(entity.getActiveSw()) && AppConstant.UNLOCKED.equals(entity.getAccStatus())) {
			return AppConstant.SUCCESS;
		} else {
			return AppConstant.ACC_LOCKED;
		}
	}

	@Override
	public boolean recoverPwd(String email) {
		UserEntity userEntity = userRepo.findByEmail(email);
		if (null == userEntity) {
			return false;

		} else {
			String subject = AppConstant.RECOVER_SUB;
			String body = readEmailBody(AppConstant.PWD_BODY_FILE, userEntity);
			return emailUtils.sendEmail(subject, body, email);
		}
	}

	@Override
	public DashboardCards fetchdashboardinfo() {
		long plansCount = planRepo.count();

		List<EligEntity> eligList = eligRepo.findAll();
		Long approvedcnt = eligList.stream().filter(ed -> ed.getPlanStatus().equals(AppConstant.AP)).count();

		Long deniedCnt = eligList.stream().filter(ed -> ed.getPlanStatus().equals(AppConstant.DN)).count();

		Double total = eligList.stream().mapToDouble(ed -> ed.getBenefitAmt()).sum();

		DashboardCards card = new DashboardCards();

		card.setPlansCnt(plansCount);
		card.setApprovedCnt(approvedcnt);
		card.setDeniedCnt(deniedCnt);
		card.setBeniftAmtGiven(total);
		return card;
	}

	@Override
	public UserAccountForm getUserByEmail(String email) {
		UserEntity userEntity = userRepo.findByEmail(email);
		UserAccountForm user = new UserAccountForm();
		BeanUtils.copyProperties(userEntity, user);
		return user;
	}

	// readEmail To bOdy
	private String readEmailBody(String filename, UserEntity user) {
		StringBuilder sb = new StringBuilder();
		try (Stream<String> lines = Files.lines(Paths.get(filename))) {
			lines.forEach(line -> {

				line = line.replace(AppConstant.FNAME, user.getFullName());
				line = line.replace(AppConstant.PWD, user.getPwd());
				line = line.replace(AppConstant.EMAIL, user.getEmail());
				sb.append(line);
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
