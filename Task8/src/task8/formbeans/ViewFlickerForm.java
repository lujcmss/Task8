package task8.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ViewFlickerForm extends FormBean{
	private String tags;
	private String button;

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
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
