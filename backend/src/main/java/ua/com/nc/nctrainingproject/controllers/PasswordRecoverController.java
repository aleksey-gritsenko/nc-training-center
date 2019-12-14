package ua.com.nc.nctrainingproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.com.nc.nctrainingproject.services.PasswordRecoverService;

import javax.mail.MessagingException;

@CrossOrigin
@Controller
public class PasswordRecoverController {
	private final PasswordRecoverService passwordRecoverService;

	@Autowired
	public PasswordRecoverController(PasswordRecoverService passwordRecoverService) {
		this.passwordRecoverService = passwordRecoverService;
	}

	@RequestMapping("/email")
	@ResponseBody
	public ResponseEntity<String> emailSender(@RequestParam String email) {
		if (!email.trim().isEmpty()) {
			try {

				if (passwordRecoverService.checkEmail(email)) {
					passwordRecoverService.makeEmail(email);
					return new ResponseEntity<>(HttpStatus.OK);
				}
			} catch (MessagingException ex) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping("/change")
	@ResponseBody
	public ResponseEntity<String> passwordRecoverUser(@RequestParam String recoverCode,
												 @RequestParam String newPassword) {
		if (!recoverCode.trim().isEmpty() && !newPassword.trim().isEmpty()) {

			if (passwordRecoverService.passwordRecover(recoverCode, newPassword)) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping("/resend")
	public ResponseEntity<String> resend(@RequestParam String email) {
		try {

			passwordRecoverService.resend(email);
			return new ResponseEntity<>(HttpStatus.OK);


		} catch (MessagingException ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/clean", method = RequestMethod.GET)
	@ResponseBody

	@Scheduled(fixedRate = 3600000)
	public void cleanCodes() {
		passwordRecoverService.deleteALL();
	}


}
