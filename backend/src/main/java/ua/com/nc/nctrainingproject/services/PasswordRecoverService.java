package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.nc.nctrainingproject.models.RecoverCode;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private static final String THEME = "Password recover email";
    private static final int LENGTH_CODE = 6;
    private static final int FROM = 0;
    private static final int TO = 9;
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Autowired
    public PasswordRecoverService(JavaMailSender sender, CodePostgreDAO codePostgreDAO, UserPostgreDAO userPostgreDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.sender = sender;
        this.codePostgreDAO = codePostgreDAO;
        this.userPostgreDAO = userPostgreDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional(isolation = Isolation.DEFAULT)
    public boolean passwordRecover(String code, String newPassword) {
        RecoverCode codeDB = getCode(code);
        if (codeDB != null) {
            userPostgreDAO.updatePassword(bCryptPasswordEncoder.encode(newPassword), codeDB.getEmail());
            deleteCode(code);
            return true;
        }
        return false;
    }

    @Transactional(isolation = Isolation.DEFAULT)
    public void resend(String email) throws MessagingException {
        deleteCodeEmail(email);
        makeEmail(email);

    }

    public void deleteALLDeadCodes() {
        codePostgreDAO.deleteAll();
    }

    public void makeEmail(String email) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);

        helper.setText(generateCode(email));
        helper.setSubject(THEME);

        sender.send(message);
    }

    public boolean checkEmail(String email) {
        return isEmail(email) && verifyEmail(email);
    }


    private String generateCode(String email) {
        String generatedString = "";
        if (codePostgreDAO.getCodeByEmail(email) != null) {
            deleteCodeEmail(email);
        }
        generatedString = generateCode();
        codePostgreDAO.createCode(generatedString, email);

        return generatedString;
    }

    private String generateCode() {
        String generatedString = "";
        int[] array = new int[LENGTH_CODE];
        Random rn = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rn.nextInt(TO) + FROM;
            generatedString = String.format("%s%d", generatedString, array[i]);
        }
        return generatedString;
    }

    private boolean verifyEmail(String email) {
        return userPostgreDAO.getUserEmail(email) != null;
    }


    private boolean isEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
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


}
