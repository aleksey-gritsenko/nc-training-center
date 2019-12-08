package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.com.nc.nctrainingproject.models.RecoverCode;
import ua.com.nc.nctrainingproject.models.User;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.CodePostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.UserPostgreDAO;
import ua.com.nc.nctrainingproject.persistance.dao.postgre.queries.CodeRecoverQuery;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PasswordRecoverService {

    private final UserPostgreDAO userPostgreDAO;
    private final CodePostgreDAO codePostgreDAO;
    private final JavaMailSender sender;

    @Autowired
    public PasswordRecoverService(JavaMailSender sender, CodePostgreDAO codePostgreDAO, UserPostgreDAO userPostgreDAO) {
        this.sender = sender;
        this.codePostgreDAO = codePostgreDAO;
        this.userPostgreDAO = userPostgreDAO;
    }


    private String generateCode(String email) {
        String generatedString = "";
        if (checkDB()) {
            deleteALL();
        }
        if (codePostgreDAO.getCodeByEmail(email) != null) {
            deleteCodeEmail(email);
        }
        int[] array = new int[6];
        Random rn = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rn.nextInt(9) + 1;
            generatedString = String.format("%s%d", generatedString, array[i]);
        }
        codePostgreDAO.createCode(generatedString, email);

        return generatedString;
    }

    public void makeEmail(String email) throws MessagingException {
        System.out.println(email);
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);

        helper.setText(generateCode(email));
        helper.setSubject("Password recover email");

        sender.send(message);
    }

    public boolean checkEmail(String email) {
        return verifyEmail(email) && isEmail(email);
    }

    private boolean verifyEmail(String email) {
        return userPostgreDAO.getUserEmail(email) != null;
    }

    public boolean checkDB() {
        return codePostgreDAO.checkDB() > CodeRecoverQuery.MAX_VAL;
    }


    private boolean isEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void deleteCode(String code) {
        codePostgreDAO.deleteByCode(code);
    }

    private void deleteCodeEmail(String code) {
        codePostgreDAO.deleteByCodeEmail(code);
    }


    private RecoverCode getCode(String code) {
        return codePostgreDAO.getCodeBy(code);
    }

    public boolean passwordRecover(String code, String newPassword) {

        RecoverCode codeDB = getCode(code);
        if (codeDB != null) {
            userPostgreDAO.updatePassword(newPassword, codeDB.getEmail());
            deleteCode(code);
            return true;
        }
        return false;
    }

    public void reSend(String user) throws MessagingException {
        deleteCodeEmail(user);
        makeEmail(user);

    }

    public void deleteALL() {
        codePostgreDAO.deleteAll();
    }

}
