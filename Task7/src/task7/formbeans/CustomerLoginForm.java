package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;
 

public class CustomerLoginForm extends FormBean{
	private String email;
	private String psw;
	private String userType;
	public String getEmail() {
		return email;
	}

	public String getPsw() {
		return psw;
	}

	public void setEmail(String s) {
		this.email = trimAndConvert(s,"<>>\"]");
	}

	public void setPsw(String s) {
		psw = trimAndConvert(s,"<>>\"]");
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (email == null || email.length() == 0) {
			errors.add("Email is required");
		}
		if (userType == null ) {
			errors.add("Account Type is required");
		}

		if (psw == null || psw.length() == 0) {
			errors.add("Password is required");
		}
		if (errors.size() > 0)
		return errors;
		
		if (email.matches(".*[<>\"].*")) errors.add("email may not contain angle brackets or quotes");
		if (psw.matches(".*[<>\"].*")) errors.add("Password may not contain angle brackets or quotes");
		return errors;
	}

	
	
}
