package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.nio.charset.Charset;
import java.util.Random;
@Service
public class PasswordRecoverService {
    @Autowired
    private JavaMailSender sender;
    public String generateCode(){
        String generatedString = new String();
        int[] array = new int[6];
        Random rn = new Random();
        for(int i = 0; i < array.length; i++){
             array[i] = rn.nextInt(10) + 1;
             generatedString = generatedString+array[i];
        }


        return generatedString;
    }
    public void makeEmail(String email) throws Exception {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
       // helper.setTo("druocab@gmail.com");
        helper.setText(generateCode());
        helper.setSubject("Password recover email");

        sender.send(message);
    }

}
