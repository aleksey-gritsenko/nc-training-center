package ua.com.nc.nctrainingproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
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
	@Autowired
	public PasswordRecoverService(JavaMailSender sender, CodePostgreDAO codePostgreDAO, UserPostgreDAO userPostgreDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.sender = sender;
		this.codePostgreDAO = codePostgreDAO;
		this.userPostgreDAO = userPostgreDAO;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public boolean passwordRecover(String code, String newPassword) {
		RecoverCode codeDB = getCode(code);
		if (codeDB != null) {
			userPostgreDAO.updatePassword(bCryptPasswordEncoder.encode(newPassword), codeDB.getEmail());
			deleteCode(code);
			return true;
		}
		return false;
	}

	public void resend(String user) throws MessagingException {
		deleteCodeEmail(user);
		makeEmail(user);

	}

	public void deleteALL() {
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
		return verifyEmail(email) && isEmail(email);
	}



	private String generateCode(String email) {
		String generatedString = "";
		if (codePostgreDAO.getCodeByEmail(email) != null) {
			deleteCodeEmail(email);
		}
		int[] array = new int[LENGTH_CODE];
		Random rn = new Random();

		for (int i:array) {
            i = rn.nextInt(TO) + FROM;
			generatedString = String.format("%s%d", generatedString, i);
		}
		codePostgreDAO.createCode(generatedString, email);

		return generatedString;
	}



	private boolean verifyEmail(String email) {
		return userPostgreDAO.getUserEmail(email) != null;
	}

	public boolean checkDB() {
		return codePostgreDAO.checkDB() > CodeRecoverQuery.MAX_VAL;
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
	private final UserPostgreDAO userPostgreDAO;
	private final CodePostgreDAO codePostgreDAO;
	private final JavaMailSender sender;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final String THEME = "Password recover email";
	private final int LENGTH_CODE = 6;
	private final int FROM = 9;
	private final int TO = 1;
	private final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
}
