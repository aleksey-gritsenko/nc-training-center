package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.AdministratorPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.CodePostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;

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


    public String generateCode(String userName){
        String generatedString = "";
        int[] array = new int[6];
        Random rn = new Random();
        for (int i = 0; i < array.length; i++) {
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

    public boolean passwordRecover(String code,String newPassword,String userName){
        String codeDB = codePostgreDAO.getCodeByUserName(userName);

        if (code.equals(codeDB)) {
            userPostgreDAO.updatePassword(newPassword,userName);
            codePostgreDAO.deleteByUserName(userName);
            return true;
        }
        return false;
    }

    public boolean passwordRecoverAdmin(String code,String newPassword,String adminName){
        String codeDB = codePostgreDAO.getCodeByUserName(adminName);

        if (code.equals(codeDB)) {
            administratorPostgreDAO.updateAdminPassword(newPassword,adminName);
            codePostgreDAO.deleteByUserName(adminName);
            return true;
        }
        return false;
    }
}
