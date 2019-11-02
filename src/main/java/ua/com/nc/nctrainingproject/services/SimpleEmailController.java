package ua.com.nc.nctrainingproject.services;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleEmailController {


    @Autowired
    private PasswordRecoverService passwordRecoverService;

    @RequestMapping("/simpleemail")
    @ResponseBody
    public void emailSender(String email) {
        try {
            passwordRecoverService.makeEmail("j.s.alpaeva@gmail.com");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }




}
