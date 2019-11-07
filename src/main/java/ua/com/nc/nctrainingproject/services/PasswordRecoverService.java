package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.persistance.dao.AdministratorDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AdministratorPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.CodePostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

import javax.mail.internet.MimeMessage;
import java.nio.charset.Charset;
import java.util.Random;
@Service
public class PasswordRecoverService {
    @Autowired
    private UserPostgreDAO userPostgreDAO;
    @Autowired
    private  CodePostgreDAO codePostgreDAO;
    @Autowired
    private AdministratorPostgreDAO administratorPostgreDAO;
    @Autowired
    private JavaMailSender sender;
    public String generateCode(String userName){
        String generatedString = new String();
        int[] array = new int[6];
        Random rn = new Random();
        for(int i = 0; i < array.length; i++){
             array[i] = rn.nextInt(9) + 1;
             generatedString = generatedString+array[i];
        }
        codePostgreDAO.createCode(generatedString,userName);

        return generatedString;
    }
    public void makeEmail(String email,String userName) throws Exception {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
       // helper.setTo("druocab@gmail.com");

        helper.setText(generateCode(userName));
        helper.setSubject("Password recover email");

        sender.send(message);
    }
    public boolean verifyEmailUser(String userName,String email){
        if (userPostgreDAO.getUserEmailByUserName(userName,email)== null){
            return false;
        }
        return true;
    }
    public boolean verifyEmailAdmin(String adminName,String email){
        if (administratorPostgreDAO.getAdminEmailByAdminName(adminName,email)==null){
            return false;
        }
        return true;
    }

    public boolean passwordRecover(String code,String newPassword,String userName){
        String codeDB = codePostgreDAO.getCodeByUserName(userName);
        if(code.equals(codeDB)){
        userPostgreDAO.updatePassword(newPassword,userName);
        codePostgreDAO.deleteByUserName(userName);
        return true;
        }
        return false;

    }
    public boolean passwordRecoverAdmin(String code,String newPassword,String adminName){
        String codeDB = codePostgreDAO.getCodeByUserName(adminName);
        if(code.equals(codeDB)){
            administratorPostgreDAO.updatePassword(newPassword,adminName);
            codePostgreDAO.deleteByUserName(adminName);
            return true;
        }
        return false;

    }
}
