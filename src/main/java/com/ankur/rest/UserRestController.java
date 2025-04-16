package com.ankur.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ankur.bindings.DashboardCards;
import com.ankur.bindings.LoginForm;
import com.ankur.bindings.UserAccountForm;
import com.ankur.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {

		String status = userService.login(loginForm);

		if (status.equals("success")) {
			return "redirect:/dashboard?email=" + loginForm.getEmail();
		} else {
			return status;
		}
	}

	@GetMapping("/dashboard")
	public ResponseEntity<DashboardCards> buildDashboard(@RequestParam("email") String email) {
		UserAccountForm user = userService.getUserByEmail(email);
		DashboardCards dashBoardCard = userService.fetchdashboardinfo();
		dashBoardCard.setUser(user);
		return new ResponseEntity<>(dashBoardCard, HttpStatus.OK);
	}

}
