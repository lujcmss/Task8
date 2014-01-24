package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class DepositForm extends FormBean{
	private String depositAmount;
	private String button;
	private String customerEmail;
	
	public double getDepositAmount() {
		return Double.parseDouble(depositAmount);
	}
	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = button;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
	
		return errors;
	}

	
	
	
}
