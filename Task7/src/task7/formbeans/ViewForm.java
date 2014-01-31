package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ViewForm extends FormBean {
	private String customerEmail;
	private String button;
	
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = trimAndConvert(customerEmail, "<>>\"]");
	}
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = button;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (customerEmail == null || customerEmail.length() == 0) {
			errors.add("username is required");
		}

		//if (!customerEmail.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"))
		//	errors.add("Illegal email");
		
		return errors;
}
}