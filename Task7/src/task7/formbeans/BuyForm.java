package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyForm extends FormBean {
	private String fund;
	//not sure to store string or int amount, may be we
	private String amount;
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

		if (fund == null || fund.length() == 0) {
			errors.add("fund is required");
		}

		if (amount == null || amount.length() == 0) {
			errors.add("Password is required");
		}
		if (errors.size() > 0)
		return errors;
		
		if (fund.matches(".*[<>\"].*")) errors.add("fund may not contain angle brackets or quotes");
		if ((!amount.matches("^[1-9]d*$"))||amount.matches(".*[<>\"].*")) errors.add("Illegal Amount");
		return errors;
	}
	
	
}
