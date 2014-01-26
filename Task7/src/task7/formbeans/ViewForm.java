package task7.formbeans;

import org.mybeans.form.FormBean;

public class ViewForm extends FormBean {
	private String customerEmail;
	private String button;
	
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getButton() {
		return button;
	}
	public void setButton(String button) {
		this.button = button;
	}
}
