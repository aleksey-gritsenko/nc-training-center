package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.RecoverCode;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AdministratorPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.CodePostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;
@Service
public class PasswordRecoverService {

    private final UserPostgreDAO userPostgreDAO;
    private final CodePostgreDAO codePostgreDAO;
    private final AdministratorPostgreDAO administratorPostgreDAO;
    private final JavaMailSender sender;

    @Autowired
    public PasswordRecoverService(JavaMailSender sender, AdministratorPostgreDAO administratorPostgreDAO, CodePostgreDAO codePostgreDAO, UserPostgreDAO userPostgreDAO) {
        this.sender = sender;
        this.administratorPostgreDAO = administratorPostgreDAO;
        this.codePostgreDAO = codePostgreDAO;
        this.userPostgreDAO = userPostgreDAO;
    }


    public String generateCode(String email){
        String generatedString = "";
        int[] array = new int[6];
        Random rn = new Random();
        for (int i = 0; i < array.length; i++) {
           array[i] = rn.nextInt(9) + 1;
           generatedString = generatedString+array[i];
        }
        codePostgreDAO.createCode(generatedString,email);

        return generatedString;
    }
    public void makeEmail(String email) throws MessagingException {


        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);

        helper.setText(generateCode(email));
        helper.setSubject("Password recover email");

        sender.send(message);
    }
    public boolean verifyEmail(String email){
        if (userPostgreDAO.getUserEmail(email)== null){
            return false;
        }
        return true;
    }



    public boolean passwordRecover(String code,String newPassword){
        RecoverCode codeDB = codePostgreDAO.getCodeByUserName(code);

        if (code.equals(codeDB.getCode())) {
            userPostgreDAO.updatePassword(newPassword,codeDB.getEmail());
            codePostgreDAO.deleteByCode(code);
            return true;
        }
        return false;
    }

}
