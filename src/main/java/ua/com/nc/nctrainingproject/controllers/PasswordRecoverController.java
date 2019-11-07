package ua.com.nc.nctrainingproject.controllers;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.nc.nctrainingproject.services.PasswordRecoverService;

@Controller
public class PasswordRecoverController {


    @Autowired
    private PasswordRecoverService passwordRecoverService;

    @RequestMapping("/email")
    @ResponseBody
    public ResponseEntity<?> emailSender(@RequestParam String email, @RequestParam String userName) {
        try {
            if(passwordRecoverService.verifyEmailUser(userName,email)
                    || passwordRecoverService.verifyEmailAdmin(userName,email)) {
                passwordRecoverService.makeEmail(email, userName);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping("/userrecover")
    @ResponseBody
    public boolean passwordRecoverUser(@RequestParam String code,
                                @RequestParam String userName,
                                @RequestParam String newPassword) {
    return passwordRecoverService.passwordRecover(code,newPassword,userName);
    }
    @RequestMapping("/adminrecover")
    @ResponseBody
    public boolean passwordRecoverAdmin(@RequestParam String code,
                                       @RequestParam String AdminName,
                                       @RequestParam String newPassword) {
         return passwordRecoverService.passwordRecoverAdmin(code,newPassword,AdminName);
    }


}
