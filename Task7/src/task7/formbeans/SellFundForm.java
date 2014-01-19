package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellFundForm extends FormBean{
	private String tickerOrFund;
	public String getTickerOrFund() {
		return tickerOrFund;
	}

	public void setTickerOrFund(String s) {
		this.tickerOrFund= trimAndConvert(s,"<>>\"]");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (tickerOrFund == null || tickerOrFund.length() == 0) {
			errors.add("Ticker or Fund name is required");
		}

	
		if (errors.size() > 0)
		return errors;
		
		if (tickerOrFund.matches(".*[<>\"].*")) errors.add("Ticker or Fund name may not contain angle brackets or quotes");
		
		return errors;
	}
	
	
	
}
