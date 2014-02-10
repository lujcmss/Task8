package task8.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;
 

public class LoginForm extends FormBean{
	private String pin;
	private String button;

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
	 		
		return errors;
	}}
