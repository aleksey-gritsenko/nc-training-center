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
    public ResponseEntity<?> emailSender( @RequestParam String email) {
      if(email !=null) {
        try {
          if (passwordRecoverService.isEmail(email) &&
            passwordRecoverService.verifyEmail(email)) {
            passwordRecoverService.makeEmail(email);
            return new ResponseEntity<>(HttpStatus.OK);
          }
        } catch (MessagingException ex) {

          ex.printStackTrace();
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
      }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


  @RequestMapping("/change")
  @ResponseBody
  public ResponseEntity<?> passwordRecoverUser(
    @RequestParam String recoverCode,
    @RequestParam String newPassword) {
    if ( recoverCode != null && newPassword != null) {
      if (passwordRecoverService.passwordRecover(recoverCode, newPassword)) {
        return new ResponseEntity<>(HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

}
