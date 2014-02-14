package task8.databeans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

public class TopCommentBean {
	private String imageSource;
	private String imageSourceOri;
	private int count;
	
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}