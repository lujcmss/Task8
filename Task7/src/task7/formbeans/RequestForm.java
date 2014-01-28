package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestForm extends FormBean {
	private String requestAmount;

	public double getRequestAmount() {
		return Double.parseDouble(requestAmount);
	}

	public void setRequestAmount(String requestAmount) {
		this.requestAmount = trimAndConvert(requestAmount, "<>>\"]");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (requestAmount == null
				|| (!requestAmount.matches("^(([0-9]+[\\.]?[0-9]+)|[1-9])$") || requestAmount
						.matches(".*[<>\"].*"))) {
			errors.add("Illegal Amount");
		} else {
			double tmp = Double.parseDouble(requestAmount);
			if (tmp < 0.01) errors.add("Requset amount should be larger than $0.01");
		}
		
		return errors;
	}
}
