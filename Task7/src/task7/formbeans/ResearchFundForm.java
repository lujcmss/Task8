package task7.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ResearchFundForm extends FormBean {
	private String fundName;
	private String button;
	private String researchFund;

	
	public String getResearchFund() {
		return researchFund;
	}

	public void setResearchFund(String researchFund) {
		this.researchFund = researchFund;
	}

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

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		return errors;
	}
}
