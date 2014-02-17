package task8.databeans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TASK8_DISLIKE")
public class DislikeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int dislikeId;

	@Column(nullable = false)
	private UserBean userBean;

	@Column(nullable = false)
	private String photoId;
	
	private String imageSource;
	private String imageSourceOri;
	private String title;

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}