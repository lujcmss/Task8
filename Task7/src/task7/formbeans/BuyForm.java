package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyForm extends FormBean {
	private String fund;
	private String amount;
	private String button;
	private String fundName;
	
	public String getFundName() {
		return fundName;
	}

	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	public String getButton() {
		return button;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public String getFund() {
		return fund;
	}

	public double getAmount() {
		return Double.parseDouble(amount);
	}
	
	public void setFund(String s) {
		this.fund = trimAndConvert(s,"<>>\"]");
	}
	
	public void setAmount(String a) {
		this.amount = trimAndConvert(a,"<>>\"]");
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (fund != null && fund.matches(".*[<>\"].*")) errors.add("fund may not contain angle brackets or quotes");
		//if (amount != null && (!amount.matches("^[1-9]d*$")||amount.matches(".*[<>\"].*"))) errors.add("Illegal Amount");
		return errors;
	}
}
