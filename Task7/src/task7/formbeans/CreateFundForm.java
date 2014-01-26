package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateFundForm extends FormBean{
	private String fund;
	private String ticker;
	
	public String getFund() {
		return fund;
	}
	public void setFund(String fund) {
		this.fund =trimAndConvert(fund,"<>>\"]");
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = trimAndConvert(ticker,"<>>\"]");
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
		
		if (fund.matches(".*[<>\"].*")) errors.add("email may not contain angle brackets or quotes");
		if (ticker.matches(".*[<>\"].*")) errors.add("Illegal may not contain angle brackets or quotes");
		return errors;
	}
	
	
	
	
}
