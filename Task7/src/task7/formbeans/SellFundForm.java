package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellFundForm extends FormBean {
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
		if (share == null || share.equals(""))
			return 0;
		return Double.parseDouble(share);
	}

	public void setFund(String s) {
		this.fund = trimAndConvert(s, "<>>\"]");
	}

	public void setShare(String a) {
		this.share = trimAndConvert(a, "<>>\"]");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fund != null && fund.matches(".*[<>\"].*"))
			errors.add("fund may not contain angle brackets or quotes");
		if (share != null
				&& (!share.matches("^(([0-9]+[\\.]?[0-9]+)|[1-9])$") || share
						.matches(".*[<>\"].*"))) {
			errors.add("Illegal Amount");
		} else if (share != null && !share.equals("")) {
			double tmp = Double.parseDouble(share);
			if (tmp < 0.001) errors.add("The share should be larger than 0.001");
		}

		return errors;
	}
}
