package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyForm extends FormBean {
	private String fund;
	private String amount;
	private String button;
	
	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public String getFund() {
		return fund;
	}

	public String getAmount() {
		return amount;
	}
	
	public void setFund(String s) {
		this.fund = trimAndConvert(s,"<>>\"]");
	}
	
	public void setAmount(String a) {
		this.amount = trimAndConvert(a,"<>>\"]");
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		System.out.println("Enters buyform");
		if (fund == null || fund.length() == 1) {
			errors.add("fund is required");
		}

		if (amount == null || amount.length() == 0) {
			errors.add("Amount  is required");
		}
		if (errors.size() > 0)
		return errors;
		
		if (fund.matches(".*[<>\"].*")) errors.add("fund may not contain angle brackets or quotes");
		if ((!amount.matches("^[1-9]d*$"))||amount.matches(".*[<>\"].*")) errors.add("Illegal Amount");
		return errors;
	}
	
	
}
