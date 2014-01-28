package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellFundForm extends FormBean{
	private String fund;
	private String share;
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

	public double getShare() {
		return Double.parseDouble(share);
	}
	
	public void setFund(String s) {
		this.fund = trimAndConvert(s,"<>>\"]");
	}
	
	public void setShare(String a) {
		this.share = trimAndConvert(a,"<>>\"]");
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (fund != null && fund.matches(".*[<>\"].*")) errors.add("fund may not contain angle brackets or quotes");
		if (share == null
				|| (!share.matches("^(([0-9]+[\\.]?[0-9]+)|[1-9])$") || share
						.matches(".*[<>\"].*")))
			errors.add("Illegal Amount");
		if (fundName == null || fundName.matches(".*[<>\"].*"))
			errors.add("fund name may not contain angle brackets or quotes");
		return errors;
	}
}
