package task8.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ViewFlickerForm extends FormBean{
	private String tags;
	private String button;
	private String lat;
	private String lon;

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
	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (tags == null || tags.equals("")) {
			errors.add("Tags cannot be empty.");
		}
		if (lat == null || lat.equals("") || !lat.matches("^(([0-9]+[\\.]?[0-9]+)|[1-9])$")) {
			errors.add("Latitude cannot be empty, and must be a number between -90 to 90.");
		} else if (Double.parseDouble(lat) > 90 || Double.parseDouble(lat) < -90) {
			errors.add("Latitude must be a number between -90 to 90.");
		}
 		if (lon == null || lon.equals("") || !lon.matches("^(([0-9]+[\\.]?[0-9]+)|[1-9])$")) {
			errors.add("Longitude cannot be empty, and must be a number between -180 and 180.");
		} else if (Double.parseDouble(lon) > 180 || Double.parseDouble(lon) < -180) {
			errors.add("Longitude must be a number between -180 and 180.");
		}
		return errors;
	}
}
