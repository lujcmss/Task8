package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class DepositForm extends FormBean {
	private String depositAmount;
	private String button;
	private String customerEmail;

	public double getDepositAmount() {
		if (depositAmount == null || depositAmount.equals(""))
			return 0;
		return Double.parseDouble(depositAmount);
	}

	public void setDepositAmount(String depositAmount) {
		this.depositAmount = trimAndConvert(depositAmount, "<>>\"]");
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
		this.customerEmail = trimAndConvert(customerEmail, "<>>\"]");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		// if (!customerEmail
		// .matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"))
		// errors.add("Illegal email");

		if (depositAmount != null
				&& (!depositAmount.matches("^(([0-9]+[\\.]?[0-9]+)|[1-9])$") || depositAmount
						.matches(".*[<>\"].*"))) {
			errors.add("Illegal Amount");
		} else if (depositAmount != null && !depositAmount.equals("")) {
			double tmp = Double.parseDouble(depositAmount);
			if (tmp < 0.01 || tmp > Long.valueOf("10000000000")) errors.add("Deposit amount should be between $0.01 and $10,000,000,000");
		}

		return errors;
	}

}
