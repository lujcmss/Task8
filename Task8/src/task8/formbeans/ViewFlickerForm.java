package task8.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ViewFlickerForm extends FormBean{
	private String tags;
	private String button;
	private String lat;
	private String lon;
	private String photoId;
	private String title;
	private String imageSource;
	private String imageSourceOri;
	private String comment;

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
	
	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageSource() {
		return imageSource;
	}

	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}

	public String getImageSourceOri() {
		return imageSourceOri;
	}

	public void setImageSourceOri(String imageSourceOri) {
		this.imageSourceOri = imageSourceOri;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if (tags == null || tags.equals("")) {
			errors.add("Tags cannot be empty.");
		}
		if (lat == null || lat.equals("")) {
			errors.add("Latitude cannot be empty, and must be a number between -90 to 90.");
		} else if (Double.parseDouble(lat) > 90 || Double.parseDouble(lat) < -90) {
			errors.add("Latitude must be a number between -90 to 90.");
		}
 		if (lon == null || lon.equals("")) {
			errors.add("Longitude cannot be empty, and must be a number between -180 and 180.");
		} else if (Double.parseDouble(lon) > 180 || Double.parseDouble(lon) < -180) {
			errors.add("Longitude must be a number between -180 and 180.");
		}
		return errors;
	}
}
