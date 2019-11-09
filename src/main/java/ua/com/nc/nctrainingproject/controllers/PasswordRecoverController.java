package ua.com.nc.nctrainingproject.controllers;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> emailSender(@RequestParam String email, @RequestParam String userName) {
        try {
            if(passwordRecoverService.verifyEmailUser(userName,email)
                    || passwordRecoverService.verifyEmailAdmin(userName,email)) {
                passwordRecoverService.makeEmail(email, userName);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (MessagingException ex) {

            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


    @RequestMapping("/userrecover")
    @ResponseBody
    public ResponseEntity<?> passwordRecoverUser(@RequestParam String code,
                                @RequestParam String userName,
                                @RequestParam String newPassword) {
    if (passwordRecoverService.passwordRecover(code,newPassword,userName)){
        return new ResponseEntity<>(HttpStatus.OK);
        }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/adminrecover")
    @ResponseBody
    public ResponseEntity<?> passwordRecoverAdmin(@RequestParam String code,
                                       @RequestParam String AdminName,
                                       @RequestParam String newPassword) {
         if( passwordRecoverService.passwordRecoverAdmin(code,newPassword,AdminName)){
             return new ResponseEntity<>(HttpStatus.OK);
         }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


}
