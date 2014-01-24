package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestForm extends FormBean{
	private String usr;
	private int requestAmount;
	
	
	public int getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(int requestAmount) {
		this.requestAmount = requestAmount;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String s) {
		this.usr= trimAndConvert(s,"<>>\"]");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (requestAmount<1) {
			errors.add("Amount to deposit must be greater or equal to  1");
		}

	
		if (errors.size() > 0)
		return errors;
		
		if (usr.matches(".*[<>\"].*")) errors.add("Ticker or Fund name may not contain angle brackets or quotes");
		
		return errors;
	}

	
	
	
}
