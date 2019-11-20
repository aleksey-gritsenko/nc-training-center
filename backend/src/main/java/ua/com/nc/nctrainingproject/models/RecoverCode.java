package ua.com.nc.nctrainingproject.models;

import java.util.Date;

public class RecoverCode {
	private String code;
	private Date date;
	private String email;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String userName) {
		this.email = userName;
	}

	@Override
	public String toString() {
		return "RecoverCode{" +
				"code='" + code + '\'' +
				", date=" + date +
				", userName='" + email + '\'' +
				'}';
	}
}
