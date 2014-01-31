package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateEmployeeForm extends FormBean{
	private String email;
	private String psw;
	private String firstName;
	private String lastName;
	private String confirm;
	
	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = trimAndConvert(confirm, "<>>\"]");
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = trimAndConvert(email, "<>>\"]");
	}
	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = trimAndConvert(psw, "<>>\"]");
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = trimAndConvert(firstName, "<>>\"]");
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = trimAndConvert(lastName, "<>>\"]");
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (firstName == null || firstName.length() == 0) {
			errors.add("Firstname is required");
		}

		if (lastName == null || lastName.length() == 0) {
			errors.add("Lastname is required");
		}

		if (confirm == null || confirm.length() == 0) {
			errors.add("Confirm Password is required");
		}

		if (email == null || email.length() == 0) {
			errors.add("Username is required");
		}

		if (psw == null || psw.length() == 0) {
			errors.add("Password is required");
		}


		if (errors.size() > 0)
			return errors;

		//if (!email.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"))
		//	errors.add("Illegal email");
		if (psw.matches(".*[<>\"].*"))
			errors.add("Password may not contain angle brackets or quotes");
		if (!confirm.equals(psw))
			errors.add("Password doesn't match password confirmation");

		if (firstName.length()>30) {
			errors.add("Firstname is too long");
		}

		if (lastName.length()>30) {
			errors.add("Lastname is  too long");
		}

		if (confirm.length()>30) {
			errors.add("Confirm Password is too long");
		}

		if (email.length()>30) {
			errors.add("Username is  too long");
		}

		if (psw.length()>30) {
			errors.add("Password is too long");
		}
		

		return errors;
	}
	
}
