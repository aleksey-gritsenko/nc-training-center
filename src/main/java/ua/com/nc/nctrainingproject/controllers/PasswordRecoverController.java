package ua.com.nc.nctrainingproject.controllers;


import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.nc.nctrainingproject.services.PasswordRecoverService;

@Controller
public class PasswordRecoverController {
    private final PasswordRecoverService passwordRecoverService;

    @Autowired
    public PasswordRecoverController(PasswordRecoverService passwordRecoverService) {
        this.passwordRecoverService = passwordRecoverService;
    }

    @RequestMapping("/email")
    @ResponseBody
    public ResponseEntity<?> emailSender(@RequestParam String login, @RequestParam String email) {
        try {
            if (passwordRecoverService.verifyEmailUser(login,email)) {
                passwordRecoverService.makeEmail(email, login);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (MessagingException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/change")
    @ResponseBody
    public ResponseEntity<?> passwordRecoverUser(
                                @RequestParam String login,
                                @RequestParam String recoverCode,
                                @RequestParam String newPassword) {
    if (passwordRecoverService.passwordRecover(recoverCode,newPassword,login)){
        return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
