package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestForm extends FormBean{
	private String requestAmount;
	
	public double getRequestAmount() {
		return Double.parseDouble(requestAmount);
	}

	public void setRequestAmount(String requestAmount) {
		this.requestAmount = trimAndConvert(requestAmount, "<>>\"]");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		return errors;
	}	
}
