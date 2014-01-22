package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class DepositForm extends FormBean{
	private String usr;
	private int depositAmount;
	
	
	public int getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(int depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String s) {
		this.usr= trimAndConvert(s,"<>>\"]");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (depositAmount<1) {
			errors.add("Amount to deposit must be greater or equal to  1");
		}

	
		if (errors.size() > 0)
		return errors;
		
		if (usr.matches(".*[<>\"].*")) errors.add("Ticker or Fund name may not contain angle brackets or quotes");
		
		return errors;
	}

	
	
	
}
