package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateFundForm extends FormBean {
	private String fund;
	private String ticker;

	public String getFund() {
		return fund;
	}

	public void setFund(String fund) {
		this.fund = trimAndConvert(fund, "<>>\"]").toLowerCase();
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = trimAndConvert(ticker, "<>>\"]").toUpperCase();
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (fund == null || fund.length() == 0) {
			errors.add("Fund name is required");
		}

		if (ticker == null || ticker.length() == 0) {
			errors.add("Ticker is required");
		}
		if (errors.size() > 0)
			return errors;

		if (fund.matches(".*[<>\"].*"))
			errors.add("fund may not contain angle brackets or quotes");
		if (ticker.matches(".*[<>\"].*"))
			errors.add(" ticker may not contain angle brackets or quotes");
		if (!ticker.matches("^[A-Za-z0-9]\\w{1,5}$"))
			errors.add("Illegal ticker");
		
		if (fund.length()>30) {
			errors.add("Fund name is too long");
		}

		if (ticker.length()>5) {
			errors.add("Ticker is too long");
		}
		return errors;
	}

}
