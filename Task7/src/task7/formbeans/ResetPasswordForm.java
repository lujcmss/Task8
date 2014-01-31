package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ResetPasswordForm extends FormBean {
	private String newpsw;
	private String confirmpsw;

	public String getNewpsw() {
		return newpsw;
	}

	public void setNewpsw(String newpsw) {
		this.newpsw = trimAndConvert(newpsw, "<>>\"]");
	}

	public String getConfirmpsw() {
		return confirmpsw;
	}

	public void setConfirmpsw(String confirmpsw) {
		this.confirmpsw= trimAndConvert(confirmpsw, "<>>\"]");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (newpsw == null || newpsw.length() == 0) {
			errors.add("New password is required");
		}
		if (newpsw.length()>30) {
			errors.add("New password is too long");
		}
		
		if (errors.size() > 0)
			return errors;

		if (newpsw.matches(".*[<>\"].*"))
			errors.add("Password may not contain angle brackets or quotes");
		
		if (!newpsw.equals(confirmpsw))
			errors.add("New password must be same with confirm password");

		return errors;
	}

}
