package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellFundForm extends FormBean{
	private String shares;
	public String getShares() {
		return shares;
	}

	public void setShares(String s) {
		this.shares= trimAndConvert(s,"<>>\"]");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		try {
			int x = Integer.parseInt(shares);
		} catch (NumberFormatException e) {
			errors.add("Share amount data should be whole numbers");
			
		}
		
		

	
		if (errors.size() > 0)
		{return errors;}
		
		
		
		return errors;
	}
	
	
	
}
